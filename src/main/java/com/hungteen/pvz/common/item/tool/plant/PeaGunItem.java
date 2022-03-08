package com.hungteen.pvz.common.item.tool.plant;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.container.PeaGunContainer;
import com.hungteen.pvz.common.container.inventory.ItemInventory;
import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.common.event.PVZPlayerEvents;
import com.hungteen.pvz.common.event.events.PeaGunShootEvent;
import com.hungteen.pvz.common.impl.plant.OtherPlants;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.common.item.spawn.card.PlantCardItem;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.common.misc.tag.PVZItemTags;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.world.item.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class PeaGunItem extends Item {

	public static final int PEA_GUN_SLOT_NUM = 28;
	// register shoot mode here.
	private static final HashSet<IPlantType> SHOOT_MODES = new HashSet<>(
			Arrays.asList(PVZPlants.PEA_SHOOTER, PVZPlants.SNOW_PEA, PVZPlants.REPEATER, PVZPlants.THREE_PEATER,
					PVZPlants.SPLIT_PEA, PVZPlants.GATLING_PEA, PVZPlants.STAR_FRUIT, OtherPlants.ANGEL_STAR_FRUIT));

	public PeaGunItem() {
		super(new Properties().tab(PVZItemGroups.PVZ_USEFUL).stacksTo(1).durability(1200));
	}

	/**
	 * add new shoot mode.
	 */
	public static void registerPeaGunShootMode(IPlantType type) {
		SHOOT_MODES.add(type);
	}

	@Nonnull
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag oldCapNbt) {
		return new InvProvider(stack);
	}

	public static Inventory getInventory(ItemStack stack) {
		return new ItemInventory(stack, PEA_GUN_SLOT_NUM) {
			@Override
			public boolean canPlaceItem(int slot, @Nonnull ItemStack stack) {
				if (slot == 0) {
					return isValidMode(stack);
				} else {
					return stack.getItem().is(PVZItemTags.PEA_GUN_BULLETS);
				}
			}
		};
	}

	@Override
	public ActionResult<ItemStack> use(Level worldIn, Player playerIn, Hand handIn) {
		final ItemStack itemStack = playerIn.getItemInHand(handIn);
		final Inventory inv = getInventory(itemStack);

		if (handIn == Hand.MAIN_HAND) {
			if (itemStack.getDamageValue() == itemStack.getMaxDamage()) {
				if (!worldIn.isClientSide) {
					PlayerUtil.sendMsgTo(playerIn,
							new TranslatableComponent("help.pvz.broken").withStyle(ChatFormatting.RED));
				}
				playerIn.getCooldowns().addCooldown(this, 20);
				return ActionResult.fail(itemStack);
			}
			if (!hasBullet(itemStack)) {// no bullet.
				if (!worldIn.isClientSide) {
					PlayerUtil.sendMsgTo(playerIn,
							new TranslatableComponent("help.pvz.no_bullet").withStyle(ChatFormatting.RED));
				}
				playerIn.getCooldowns().addCooldown(this, 20);
				return ActionResult.fail(itemStack);
			}
			if (!hasShootMode(inv.getItem(0))) {// no mode.
				if (!worldIn.isClientSide) {
					PlayerUtil.sendMsgTo(playerIn,
							new TranslatableComponent("help.pvz.no_shoot_mode").withStyle(ChatFormatting.RED));
				}
				playerIn.getCooldowns().addCooldown(this, 20);
				return ActionResult.fail(itemStack);
			}
			playerIn.startUsingItem(handIn);
		} else {
			if (!worldIn.isClientSide && playerIn instanceof ServerPlayer) {
				NetworkHooks.openGui((ServerPlayer) playerIn, new INamedContainerProvider() {

					@Override
					public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_,
							Player p_createMenu_3_) {
						return new PeaGunContainer(p_createMenu_1_, p_createMenu_3_);
					}

					@Override
					public Component getDisplayName() {
						return new TranslatableComponent("gui.pvz.pea_gun.show");
					}
				});
			}
		}
		return ActionResult.success(itemStack);
	}

	@Override
	public void onUseTick(Level world, LivingEntity living, ItemStack stack, int tick) {
		final int cd = getShootCD(living, stack);
		final Inventory inv = getInventory(stack);
		final IPlantType type = getShootMode(inv.getItem(0));
		
		if (living instanceof Player && tick + 5 < this.getUseDuration(stack) && tick % cd == 0) {
			if (!MinecraftForge.EVENT_BUS.post(new PeaGunShootEvent((Player) living, stack, type))) {
				this.performShoot(world, (Player) living, stack, type);
			}
		}
	}
	
	/**
	 * {@link PVZPlayerEvents#tickPlayer(net.minecraftforge.event.TickEvent.PlayerTickEvent)}
	 */
	public static void checkHeadShoot(Player player) {
		final ItemStack stack = player.getItemBySlot(EquipmentSlotType.HEAD);
		final Inventory inv = getInventory(stack);
		
		if(stack.getItem() instanceof PeaGunItem && ! player.getCooldowns().isOnCooldown(stack.getItem())) {
			if(stack.getDamageValue() < stack.getMaxDamage() && hasBullet(stack) && hasShootMode(inv.getItem(0))) {
				final IPlantType mode = getShootMode(inv.getItem(0));
				((PeaGunItem)stack.getItem()).performShoot(player.level, player, stack, mode);
				player.getCooldowns().addCooldown(stack.getItem(), Math.max(5, PeaGunItem.getShootCD(player, stack)));
			} else {
				player.getCooldowns().addCooldown(stack.getItem(), 200);//cool down for no pea
			}
		}
	}

	/**
	 * @param itemStack : pea gun stack.
	 */
	public void performShoot(Level world, Player player, ItemStack itemStack, IPlantType mode) {
		final ItemStack stack = getFirstBullets(itemStack);

		if (mode == PVZPlants.PEA_SHOOTER) {
			this.shootPea(world, player, mode, stack, 0.5, 0, 0);
		} else if (mode == PVZPlants.SNOW_PEA) {
			this.shootPea(world, player, mode, stack, 0.5, 0, 0);
		} else if (mode == PVZPlants.REPEATER) {
			this.shootPea(world, player, mode, stack, 0.5, 0, 0);
			this.shootPea(world, player, mode, stack, 0, 0, 0);
		} else if (mode == PVZPlants.THREE_PEATER) {
			this.shootPea(world, player, mode, stack, 0.25, -0.25, -15);
			this.shootPea(world, player, mode, stack, 0.25, 0, 0);
			this.shootPea(world, player, mode, stack, 0.25, 0.25, 15);
		} else if (mode == PVZPlants.SPLIT_PEA) {
			this.shootPea(world, player, mode, stack, 0.25, 0, 0);
			this.shootPea(world, player, mode, stack, 0, 0, 180);
			this.shootPea(world, player, mode, stack, -0.5, 0, 180);
		} else if (mode == PVZPlants.STAR_FRUIT) {
			final int base = player.getRandom().nextInt(72);
			for (int i = 0; i < 5; ++i) {
				this.shootPea(world, player, mode, stack, 0.25, 0, base + 72 * i);
			}
		} else if (mode == OtherPlants.ANGEL_STAR_FRUIT) {
			for (int i = 0; i < 5; ++i) {
				this.shootPea(world, player, mode, stack, 0.25, 0, 72 * i);
			}
		} else if (mode == PVZPlants.GATLING_PEA) {
			this.shootPea(world, player, mode, stack, 1.5, 0, 0);
			this.shootPea(world, player, mode, stack, 1, 0, 0);
			this.shootPea(world, player, mode, stack, 0.5, 0, 0);
			this.shootPea(world, player, mode, stack, 0, 0, 0);
		}

		final SoundEvent sound = (mode == PVZPlants.SNOW_PEA || stack.getItem().equals(ItemRegister.SNOW_PEA.get())) ? SoundRegister.SNOW_SHOOT.get() :
				SoundEvents.SNOW_GOLEM_SHOOT;
		EntityUtil.playSound(player, sound);

		this.shrinkItemStack(player, itemStack);

		if(PlayerUtil.isPlayerSurvival(player)) {
			itemStack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(Hand.MAIN_HAND));
		}
	}

	public void shootPea(Level world, Player player, IPlantType mode, ItemStack stack, double forwardOffset,
                         double rightOffset, float angle) {
		final Vector3d vec = player.getLookAngle();
		final double deltaX = forwardOffset * vec.x - rightOffset * vec.z;
		final double deltaZ = forwardOffset * vec.z + rightOffset * vec.x;
		
		final PeaEntity pea = EntityRegister.PEA.get().create(world);
		pea.setPos(player.getX() + deltaX, player.getEyeY() - 0.4, player.getZ() + deltaZ);
		pea.shootPea(player.getLookAngle(), 1.3F, angle);

		pea.setPeaType(this.getPeaType(player));
		pea.setPeaState(this.getPeaState(mode, stack.getItem()));
		pea.setPower(EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, player.getMainHandItem()));

		pea.summonByOwner(player);
		pea.setAttackDamage(1.5F);
		world.addFreshEntity(pea);
	}

	private void shrinkItemStack(Player player, ItemStack stack) {
		final Inventory inv = getInventory(stack);
		final int pos = getFirstPos(stack);
		final int lvl = PlayerUtil.getResource(player, Resources.TREE_LVL);
		boolean flag = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0;
		if (player.hasEffect(EffectRegister.ENERGETIC_EFFECT.get()) || ! PlayerUtil.isPlayerSurvival(player) || (flag && player.getRandom().nextInt(100) < 50 + (lvl + 9) / 10)) {
		} else {
			inv.removeItem(pos, 1);
		}
	}

	private PeaEntity.State getPeaState(IPlantType plant, Item item) {
		if (plant == PVZPlants.SNOW_PEA) {
			return PeaEntity.State.ICE;
		} else if (item == ItemRegister.SNOW_PEA.get()) {
			return PeaEntity.State.ICE;
		} else if (item == ItemRegister.FLAME_PEA.get()) {
			return PeaEntity.State.FIRE;
		} else {
			return PeaEntity.State.NORMAL;
		}
	}

	private PeaEntity.Type getPeaType(Player player) {
		final int lvl = PlayerUtil.getResource(player, Resources.TREE_LVL);
		final int bigChance = (lvl + 4) / 5;
		final int hugeChance = bigChance + (lvl + 19) / 20;
		final int tmp = random.nextInt(1000);
		return tmp < bigChance ? PeaEntity.Type.BIG : tmp < hugeChance ? PeaEntity.Type.HUGE : PeaEntity.Type.NORMAL;
	}

	@Override
	public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(new TranslatableComponent("tooltip.pvz.pea_gun").withStyle(ChatFormatting.GREEN));
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return enchantment == Enchantments.QUICK_CHARGE || enchantment == Enchantments.INFINITY_ARROWS
				|| enchantment == Enchantments.POWER_ARROWS || enchantment == Enchantments.UNBREAKING;
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}

	@Override
	public int getEnchantmentValue() {
		return 1;
	}

	@Override
	public int getUseDuration(ItemStack p_77626_1_) {
		return 100000;
	}

	@Override
	public UseAction getUseAnimation(ItemStack p_77661_1_) {
		return UseAction.BOW;
	}

	@Override
	public boolean canEquip(ItemStack stack, EquipmentSlotType armorType, Entity entity) {
		return armorType == EquipmentSlotType.HEAD;
	}

	/**
	 * check card item stack.
	 */
	public static IPlantType getShootMode(ItemStack stack) {
		if (isValidMode(stack)) {
			return ((PlantCardItem) stack.getItem()).plantType;
		}
		return null;
	}

	/**
	 * check card item stack.
	 */
	public static boolean isValidMode(ItemStack stack) {
		if (stack.getItem() instanceof PlantCardItem) {
			return SHOOT_MODES.contains(((PlantCardItem) stack.getItem()).plantType) && ! ((PlantCardItem) stack.getItem()).isEnjoyCard;
		}
		return false;
	}

	public static boolean hasShootMode(ItemStack stack) {
		return getShootMode(stack) != null;
	}

	public static boolean hasBullet(ItemStack stack) {
		return !getFirstBullets(stack).isEmpty();
	}

	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack stack1) {
		return stack1.getItem().equals(ItemRegister.APPEASE_ESSENCE.get());
	}

	public static ItemStack getFirstBullets(ItemStack stack) {
		final Inventory inv = getInventory(stack);
		final int pos = getFirstPos(stack);
		return pos < 0 ? new ItemStack(Items.AIR) : inv.getItem(pos);
	}

	public static int getFirstPos(ItemStack stack) {
		final Inventory inv = getInventory(stack);
		for (int i = 1; i < PEA_GUN_SLOT_NUM; ++i) {
			if (!inv.getItem(i).isEmpty()) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 30 20 15 10
	 */
	public static int getShootCD(LivingEntity living, ItemStack stack) {
		if (living.hasEffect(EffectRegister.ENERGETIC_EFFECT.get())) {
			return 2;
		}
		final int lvl = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.QUICK_CHARGE, stack);
		return lvl == 0 ? 30 : 25 - Math.min(3, lvl) * 5;
	}

	private static class InvProvider implements ICapabilityProvider {

		private final LazyOptional<IItemHandler> opt;

		private InvProvider(ItemStack stack) {
			opt = LazyOptional.of(() -> new InvWrapper(getInventory(stack)));
		}

		@Nonnull
		@Override
		public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(capability, opt);
		}
	}

}

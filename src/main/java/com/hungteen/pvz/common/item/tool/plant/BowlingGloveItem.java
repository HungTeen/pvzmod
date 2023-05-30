package com.hungteen.pvz.common.item.tool.plant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.client.render.itemstack.BowlingGloveISTER;
import com.hungteen.pvz.common.entity.misc.bowling.AbstractBowlingEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.utils.PlayerUtil;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Rarity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

/**
 * TODO bowling glove
 * @author 86152
 *
 */
public class BowlingGloveItem extends Item {

	public static final String BOWLING_STRING = "bowling_type";
	private static final Map<String, BowlingType> BOWLINGS = new HashMap<>();
	
	static {
		registerBowling(PVZPlants.WALL_NUT, () -> EntityRegister.WALL_NUT_BOWLING.get(), 1F);
	}

	public BowlingGloveItem() {
		super(new Item.Properties().tab(PVZItemGroups.PVZ_USEFUL).rarity(Rarity.UNCOMMON).defaultDurability(666).setISTER(() -> BowlingGloveISTER::new));
	}

	@Override
	public ActionResultType useOn(ItemUseContext context) {
		final World world = context.getLevel();
		final PlayerEntity player = context.getPlayer();
		final Hand hand = context.getHand();
		final ItemStack stack = player.getItemInHand(hand);
		final BlockPos pos = context.getClickedPos();
		Optional<BowlingType> type = getBowlingType(stack);
		if(! type.isPresent()) {
			if(! world.isClientSide) {
				PlayerUtil.sendMsgTo(player, new TranslationTextComponent("help.pvz.bowling_glove.empty").withStyle(TextFormatting.RED));
				player.getCooldowns().addCooldown(this, 20);
			}
			return ActionResultType.FAIL;
		}
			
		BlockPos spawnPos = pos;
		if (!world.getBlockState(pos).getCollisionShape(world, pos).isEmpty()) {
			spawnPos = pos.relative(context.getClickedFace());
		}
		if (context.getClickedFace() == Direction.UP && world.isEmptyBlock(pos.above())) {// can plant here
			final EntityType<? extends Entity> entityType = type.get().getEntity();
			if (entityType == null) {
				PVZMod.LOGGER.error("BowlingGloveItem Error : no such bowling entity !");
				return ActionResultType.FAIL;
			}
			if(! world.isClientSide) {
				final Entity entity = entityType.spawn((ServerWorld) player.level, stack, player, spawnPos, SpawnReason.SPAWN_EGG, true, true);
			    if (entity == null || ! (entity instanceof AbstractBowlingEntity)) {
			    	PVZMod.LOGGER.error("BowlingGloveItem Error : bowling entity spawn error !");
				    return ActionResultType.FAIL;
			    }
			    ((AbstractBowlingEntity) entity).summonByOwner(player);
			    ((AbstractBowlingEntity) entity).shoot(player);
			    if (PlayerUtil.isPlayerSurvival(player)) {// reset
				    setEmpty(stack);
			    }
			    if(PlayerUtil.isPlayerSurvival(player)) {
			    	player.getCooldowns().addCooldown(this, 100);
			    	stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(Hand.MAIN_HAND));
				}
			}
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.FAIL;
	}
	
	public static void onPickUp(PlayerInteractEvent.EntityInteractSpecific ev) {
//		if(ev.getItemStack().getItem().equals(ItemRegister.BOWLING_GLOVE.get())) {
//			if(ev.getTarget() instanceof PVZPlantEntity) {
//			    final PVZPlantEntity plantEntity = (PVZPlantEntity) ev.getTarget();
//			    if(isBowlingPlant(plantEntity)) {
//				    setBowlingType(ev.getItemStack(), plantEntity.getPlantType());
//				    ev.getTarget().remove();
//				    return ;
//			    }
//			}
//		    if(! ev.getSide().isClient()) {
//			    PlayerUtil.sendMsgTo(ev.getPlayer(), new TranslationTextComponent("help.pvz.bowling_glove.fail").withStyle(TextFormatting.RED));
//			    ev.getPlayer().getCooldowns().addCooldown(ev.getItemStack().getItem(), 20);
//		    }
//		}
	}
	
	/**
	 * get bowling type of certain stack.
	 */
	public static Optional<BowlingType> getBowlingType(ItemStack stack) {
		final String type = stack.getOrCreateTag().getString(BOWLING_STRING);
		if(BOWLINGS.containsKey(type)) {
			return Optional.ofNullable(BOWLINGS.get(type));
		}
		return Optional.empty();
	}

	public static ItemStack setBowlingType(ItemStack stack, IPlantType type) {
		stack.getOrCreateTag().putString(BOWLING_STRING, type.getIdentity());
		return stack;
	}
	
	public static boolean isBowlingPlant(PVZPlantEntity entity) {
		return BOWLINGS.containsKey(entity.getPlantType().getIdentity());
	}
	
	public static ItemStack setEmpty(ItemStack stack) {
		stack.getOrCreateTag().putString(BOWLING_STRING, "");
		return stack;
	}

	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
		if (this.allowdedIn(group)) {
			items.add(new ItemStack(this));
			BOWLINGS.forEach((s, type) -> {
				items.add(setBowlingType(new ItemStack(this), type.getType()));
			});
		}
	}
	
	@Override
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		final Optional<BowlingType> plant = getBowlingType(stack);
		if(! plant.isPresent()) {
			tooltip.add(new TranslationTextComponent("tooltip.pvz.bowling_glove.empty").withStyle(TextFormatting.GOLD));
		} else {
			tooltip.add(new TranslationTextComponent("tooltip.pvz.bowling_glove.full").withStyle(TextFormatting.GOLD).append(plant.get().getType().getText().withStyle(TextFormatting.GREEN)));
		}
	}
	
	/**
	 * make sure the name of key equals to the entity's registry name who can be pick up by this item.
	 */
	public static void registerBowling(IPlantType type, Supplier<EntityType<? extends Entity>> supplier, float size) {
		BOWLINGS.put(type.getIdentity(), new BowlingType(type, supplier, size));
	}
	
	public static class BowlingType {
		
		private final Supplier<EntityType<? extends Entity>> supplier;
		private final IPlantType type;
		private final float renderSize;
		
		public BowlingType(IPlantType type, Supplier<EntityType<? extends Entity>> supplier, float size) {
			this.supplier = supplier;
			this.type = type;
			this.renderSize = size;
		}
		
		public EntityType<? extends Entity> getEntity(){
			return this.supplier.get();
		}
		
		public IPlantType getType() {
			return this.type;
		}
		
		public float getSize() {
			return this.renderSize;
		}
		
	}

}

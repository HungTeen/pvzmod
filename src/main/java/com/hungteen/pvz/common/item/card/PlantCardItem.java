package com.hungteen.pvz.common.item.card;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.advancement.trigger.PlayerPlacePlantTrigger;
import com.hungteen.pvz.common.capability.CapabilityHandler;
import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.enchantment.BreakOutEnchantment;
import com.hungteen.pvz.common.enchantment.SoillessPlantEnchantment;
import com.hungteen.pvz.common.enchantment.SunReduceEnchantment;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantDefenderEntity;
import com.hungteen.pvz.common.event.events.SummonCardUseEvent;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.EnchantmentRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.ConfigUtil;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;

/**
 * Normal plant type : place on top face of block.
 * Outer plant type : place on plant entity. 
 * Pumpkin heal type : heal pumpkin outer plant. 
 * Defender plant heal type : Heal Defender Plant. 
 * Upgrade plant type : base plant upgrade. 
 * CatTail upgrade type : cattail upgrade on lilypad. 
 * CoffeeBean place type : coffee bean ride on plant entity. 
 * GraveBuster place type : gravebuster ride on tombstone. 
 * Place block in water type : lilypad place in water. 
 * Place block on ground type : flower pot place on ground. 
 */
public class PlantCardItem extends SummonCardItem {

	public static final ITextComponent UPGRADE_ERROR = new TranslationTextComponent("help.pvz.upgrade").withStyle(TextFormatting.RED);
	public static final ITextComponent GROUND_ERROR = new TranslationTextComponent("help.pvz.ground").withStyle(TextFormatting.RED);
	public static final ITextComponent OUTER_ERROR = new TranslationTextComponent("help.pvz.outer").withStyle(TextFormatting.RED);
	public static final ITextComponent OUTER_FULL = new TranslationTextComponent("help.pvz.outer_full").withStyle(TextFormatting.RED);
	public final PlantType plantType;

	public PlantCardItem(PlantType plant, boolean isFragment) {
		super(isFragment);
		this.plantType = plant;
	}
	
	public PlantCardItem(Properties properties, PlantType plant, boolean isFragment) {
		super(properties, isFragment);
		this.plantType = plant;
	}
	
	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> list) {
		if(this.allowdedIn(group)) {
			list.add(new ItemStack(this));
			if(group == PVZItemGroups.PVZ_PLANT_CARD) {
				// insert sort.
				for(int i = list.size() - 1; i >= 1; -- i) {
					final ItemStack pre = list.get(i - 1);
					final ItemStack now = list.get(i);
					if(needExchange(pre, now)) {
						final ItemStack tmp = pre.copy();
						list.set(i - 1, now);
						list.set(i, tmp);
					} else {
						break;
					}
				}
			}
		}
	}
	
	/**
	 * first check plantType ID, then check enjoy card. <br>
	 * large id put forward and enjoy card put backward. <br>
	 */
	public static boolean needExchange(ItemStack pre, ItemStack now) {
		//both are plant card.
		if(pre.getItem() instanceof PlantCardItem && now.getItem() instanceof PlantCardItem) {
			if(((PlantCardItem) pre.getItem()).isEnjoyCard && ! ((PlantCardItem) now.getItem()).isEnjoyCard) {
				return true;
			}
			if(! ((PlantCardItem) pre.getItem()).isEnjoyCard && ((PlantCardItem) now.getItem()).isEnjoyCard) {
				return false;
			}
			PlantType preType = ((PlantCardItem) pre.getItem()).plantType;
			PlantType nowType = ((PlantCardItem) now.getItem()).plantType;
			return preType.getId() > nowType.getId();
		}
		if(pre.getItem() instanceof PlantCardItem) {
			return false;
		}
		return true;
	}

	/**
	 * only consider placement in water.
	 */
	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand handIn) {
		final ItemStack stack = player.getItemInHand(handIn);
		/* check cool down */
		if(player.getCooldowns().isOnCooldown(this)) {
			this.notifyPlayerAndCD(player, stack, CD_ERROR);
			return ActionResult.fail(stack);
		}
		if(world.isClientSide) {
			return ActionResult.success(stack);
		}
		/* check plant type that can place in water */
		if(! plantType.isWaterPlant() || (plantType == PVZPlants.CAT_TAIL && ! SoillessPlantEnchantment.isSoilless(stack))) {
			this.notifyPlayerAndCD(player, stack, GROUND_ERROR);
			return ActionResult.fail(stack);
		}
		final BlockRayTraceResult result = getPlayerPOVHitResult(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);
		if (result.getType() != RayTraceResult.Type.BLOCK) {
			return ActionResult.pass(stack);
		} else {
			BlockRayTraceResult raytraceResult = result.withPosition(result.getBlockPos().above());
			BlockPos pos = raytraceResult.getBlockPos();
			/* can not place here */
			if (world.getFluidState(pos.below()).getType() != Fluids.WATER || raytraceResult.getDirection() != Direction.UP || ! world.isEmptyBlock(pos)) {
				this.notifyPlayerAndCD(player, stack, GROUND_ERROR);
				return ActionResult.pass(stack);
		    }
			if(plantType.isBlockPlant()) {
				if(PlantCardItem.checkSunAndPlaceBlock(player, this, stack, pos)) {
					return ActionResult.success(stack);
				}
			} else {
				if(checkSunAndSummonPlant(player, stack, this, pos, (l)->{})) {
					return ActionResult.success(stack);
				}
			}
			return ActionResult.fail(stack);
		}
	}

	/**
	 * only consider common plants that can place on suitable ground.
	 * not include imitater, outer plants, water plants, 
	 */
	@Override
	public ActionResultType useOn(ItemUseContext context) {
		final World world = context.getLevel();
		final PlayerEntity player = context.getPlayer();
		final Hand hand = context.getHand();
		final ItemStack stack = player.getItemInHand(hand);
		final BlockPos pos = context.getClickedPos();
		final boolean isSoilless = SoillessPlantEnchantment.isSoilless(stack);
		/* check cool down */
		if(player.getCooldowns().isOnCooldown(this)) {
			this.notifyPlayerAndCD(player, stack, CD_ERROR);
			return ActionResultType.FAIL;
		}
		/* check outer plants */
		if(plantType.isOuterPlant()) {
			this.notifyPlayerAndCD(player, stack, OUTER_ERROR);
			return ActionResultType.FAIL;
		}
		/* check water plants */
		if(plantType.isWaterPlant()) {
			if(plantType == PVZPlants.CAT_TAIL) {
				if(isSoilless) {
				    return this.use(world, player, hand).getResult();
				}
			} else if(! isSoilless || world.getFluidState(pos.above()).getType() == Fluids.WATER) {
			    return this.use(world, player, hand).getResult();
			}
		}
		if(world.isClientSide) {
			return ActionResultType.SUCCESS;
		}
		/* can not place here */
		if(context.getClickedFace() != Direction.UP || ! world.isEmptyBlock(pos.above())) {
			this.notifyPlayerAndCD(player, stack, GROUND_ERROR);
			return ActionResultType.FAIL;
		}
		/* check advance plants without Cat Tail */
		if(! isSoilless && plantType.getUpgradeFrom().isPresent()) {
			this.notifyPlayerAndCD(player, stack, UPGRADE_ERROR);
			return ActionResultType.FAIL;
		}
		/* check placement match with current block */
		if(! isSoilless && ! plantType.getPlacement().canPlaceOnBlock(world.getBlockState(pos).getBlock())) {
			this.notifyPlayerAndCD(player, stack, GROUND_ERROR);
			return ActionResultType.FAIL;
		}
		if(plantType.isBlockPlant()) {
			if(world.getBlockState(pos).canBeReplaced(new BlockItemUseContext(context))) {
				checkSunAndPlaceBlock(player, this, stack, pos);
				return ActionResultType.SUCCESS;
			} else if(world.isEmptyBlock(pos.above()) && world.getBlockState(pos).canOcclude()) {// can plant here
			    checkSunAndPlaceBlock(player, this, stack, pos.above());
			    return ActionResultType.SUCCESS;
			}
		} else {
			BlockPos spawnPos = pos;
			if(! world.getBlockState(pos).getCollisionShape(world, pos).isEmpty()) {
				spawnPos = pos.relative(context.getClickedFace());
			}
			if(checkSunAndSummonPlant(player, stack, this, spawnPos, (l)->{})) {
			    return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.FAIL;
	}
	
	/**
	 * check sunCost and spawn plantEntity.
	 */
	public static boolean checkSunAndSummonPlant(PlayerEntity player, ItemStack stack, PlantCardItem cardItem, BlockPos pos, Consumer<PVZPlantEntity> consumer) {
		final PlantType plantType = cardItem.plantType;
		if(checkSunAndCD(player, cardItem, stack, false, p -> true)){
		    if(! plantType.getEntityType().isPresent()) {
		        PVZMod.LOGGER.error("Plant Card : Summon wrong plant entity !");
			    return false;
		    }
		    PVZPlantEntity plantEntity = (PVZPlantEntity) plantType.getEntityType().get().spawn((ServerWorld) player.level, stack, player, pos, SpawnReason.SPAWN_EGG, true, true);
	    	if (plantEntity == null) {
	    		PVZMod.LOGGER.error("Plant Card : No such plant entity !");
    			return false;
	    	}
		    /* update level and its owner */
		    plantEntity.onSpawnedByPlayer(player, PlayerUtil.getPlantLvl(player, plantType), cardItem.getBasisSunCost(stack));
		    /* other operations */
		    consumer.accept(plantEntity);
		    /* enchantment effects */
		    enchantPlantEntityByCard(plantEntity, stack);
	    	/* handle cd and misc */
		    PlantCardItem.onUsePlantCard(player, stack, cardItem, plantEntity.getPlantLvl());
		    return true;
		}
		return false;
	}
	
	/**
	 * check sunCost and place plantBlock.
	 */
	public static boolean checkSunAndPlaceBlock(PlayerEntity player, PlantCardItem cardItem, ItemStack stack, BlockPos pos) {
		final PlantType plantType = cardItem.plantType;
		final BlockState state = PlantCardItem.getBlockState(player, plantType);
		if(checkSunAndCD(player, cardItem, stack, true, (p) -> {
		    if(state == null) {
			    PVZMod.LOGGER.error("Plant Card : No such plant block !");
			    return false;
		    }
		    return true;
		})) {
		    /* handle cd and misc */
		    PlantCardItem.onUsePlantCard(player, stack, cardItem, 1);
		    player.level.setBlock(pos, state, 11);
		    if (player instanceof ServerPlayerEntity) {
		        CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) player, pos, stack);
		    }
		    player.level.playSound((PlayerEntity) null, pos.getX(), pos.getY(), pos.getZ(), plantType.isWaterPlant() ? SoundRegister.PLANT_IN_WATER.get() : SoundRegister.PLANT_ON_GROUND.get(), SoundCategory.BLOCKS, 1F, 1F);
		    return true;
		}
		return false;
	}
	
	/**
	 * check sunCost and add outerplant for plantEntity
	 */
	public static boolean checkSunAndOuterPlant(PlayerEntity player, PVZPlantEntity plantEntity, PlantCardItem cardItem,
			ItemStack stack) {
		final PlantType plantType = cardItem.plantType;
		/* not consider surrounding plants number */
		if(checkSunAndCD(player, cardItem, stack, true, p -> {
			if(! plantEntity.canPlaceOuterPlant()) {
				cardItem.notifyPlayerAndCD(player, stack, OUTER_FULL);
				return false;
			}
			return true;
		})) {
			final int lvl = PlayerUtil.getPlantLvl(player, plantType);
			plantEntity.onPlaceOuterPlant(plantType, lvl, cardItem.getBasisSunCost(stack));
			/* check break out enchantment */
			if (plantEntity.canStartSuperMode() && BreakOutEnchantment.canBreakOut(plantEntity.getRandom(), stack)) {
				plantEntity.startSuperMode(false);
			}
		    onUsePlantCard(player, stack, cardItem, lvl);
		    return true;
		}
		return false;
	}
	
	/**
	 * check sunCost and heal defender plantEntity.
	 * {@link PVZPlantEntity#interactAt(PlayerEntity, net.minecraft.util.math.vector.Vector3d, Hand)}
	 */
	public static boolean checkSunAndHealPlant(PlayerEntity player, PVZPlantEntity plantEntity, PlantCardItem cardItem,
			ItemStack stack) {
		final PlantType plantType = cardItem.plantType;
		if(plantEntity instanceof PlantDefenderEntity) {
			if(EntityUtil.canAttackEntity(plantEntity, player) || ! plantType.equals(plantEntity.getPlantType())) {
			    return false;
			}
		}
		/* not consider surrounding plants number */
		if(checkSunAndCD(player, cardItem, stack, true, p -> true)){
			onUsePlantCard(player, stack, cardItem, PlayerUtil.getPlantLvl(player, plantType));
			plantEntity.addEffect(new EffectInstance(Effects.HEAL, 40, 255, true, false));
			EntityUtil.playSound(plantEntity, plantEntity.getSpawnSound());
			return true;
		}
		return false;
	}
	
	/**
	 * check sunCost and heal defender plantEntity.
	 * {@link PVZPlantEntity#interactAt(PlayerEntity, net.minecraft.util.math.vector.Vector3d, Hand)}
	 */
	public static boolean checkSunAndUpgradePlant(PlayerEntity player, PVZPlantEntity plantEntity, PlantCardItem cardItem,
			ItemStack stack) {
		PlantType plantType = cardItem.plantType;
		/* not consider surrounding plants number */
		if(plantType.getUpgradeFrom().isPresent() && plantEntity.getPlantType().equals(plantType.getUpgradeFrom().get())) {
			if(PlantCardItem.checkSunAndSummonPlant(player, stack, cardItem, plantEntity.blockPosition(), (plant) -> {
				plantEntity.onPlantUpgrade(plant);
			})) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * check card cd and sun cost, and other predicates, finally consume sun.
	 * {@link #checkSunAndSummonPlant(PlayerEntity, ItemStack, PlantCardItem, BlockPos, Consumer)}
	 */
	private static boolean checkSunAndCD(PlayerEntity player, PlantCardItem cardItem, ItemStack stack, boolean ignore, Predicate<PlayerEntity> pre) {
		/* check cool down */
		if(player.getCooldowns().isOnCooldown(cardItem)) {
			cardItem.notifyPlayerAndCD(player, stack, CD_ERROR);
			return false;
		}
		/* whether consider surrounding plants number */
		final int sunCost = ignore ? cardItem.getBasisSunCost(stack) : cardItem.getCardSunCost(player, stack);
		/* check sun */
		if(sunCost > PlayerUtil.getResource(player, Resources.SUN_NUM)) {
			cardItem.notifyPlayerAndCD(player, stack, SUN_ERROR);
			return false;
		}
		if(pre.test(player)) {
			PlayerUtil.addResource(player, Resources.SUN_NUM, - sunCost);
			return true;
		}
		return false;
	}
	
	/**
	 * {@link #checkSunAndSummonPlant(PlayerEntity, ItemStack, PlantCardItem, BlockPos, Consumer)}
	 */
	private static void enchantPlantEntityByCard(PVZPlantEntity plantEntity, ItemStack stack) {
		/* check break out enchantment */
		if (plantEntity.canStartSuperMode() && BreakOutEnchantment.canBreakOut(plantEntity.getRandom(), stack)) {
			plantEntity.startSuperMode(false);
		}
		/* check charm enchantment */
		if(EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegister.CHARM.get(), stack) > 0) {
			plantEntity.onCharmedBy(null);
		}
		/* check soilless enchantment */
		if(EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegister.SOILLESS_PLANT.get(), stack) > 0) {
			plantEntity.setImmunneToWeak(true);
		}
	}

	/**
	 * deal with cd and misc.
	 */
    public static void onUsePlantCard(PlayerEntity player, ItemStack stack, PlantCardItem item, int plantLvl) {
		MinecraftForge.EVENT_BUS.post(new SummonCardUseEvent(player, stack));
		if(PlayerUtil.isPlayerSurvival(player)) {
			if(item.isEnjoyCard) {
				stack.shrink(1);
			} else {
				handlePlantCardCoolDown(player, stack, item, plantLvl);
			}
		}
		if(player instanceof ServerPlayerEntity) {
		    PlayerPlacePlantTrigger.INSTANCE.trigger((ServerPlayerEntity) player, item.plantType.getId());
		}
		player.awardStat(Stats.ITEM_USED.get(item));
	}
    
	/**
	 * set PlantCard Item cool down.
	 */
	public static void handlePlantCardCoolDown(PlayerEntity player, ItemStack stack, PlantCardItem item, int plantLvl) {
		final int cd = getPlantCardCD(player, stack, item, plantLvl);
		player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
			l.getPlayerData().setPlantCardCD(item.plantType, cd);
		});
		player.getCooldowns().addCooldown(stack.getItem(), cd);
	}
	
	@Nullable
	public static BlockState getBlockState(PlayerEntity player, PlantType plant) {
		return plant == PVZPlants.LILY_PAD ? BlockRegister.LILY_PAD.get().getStateForPlacement(player) :
			   plant == PVZPlants.FLOWER_POT ? BlockRegister.FLOWER_POT.get().getStateForPlacement(player) :
			   null;
	}
	
	@Nullable
	public static BlockState getBlockState(Direction direction, PlantType plant) {
		return plant == PVZPlants.LILY_PAD ? BlockRegister.LILY_PAD.get().getStateForPlacement(direction) :
			   plant == PVZPlants.FLOWER_POT ? BlockRegister.FLOWER_POT.get().getStateForPlacement(direction) :
			   null;
	}
	
	@Override
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add(new TranslationTextComponent("tooltip.pvz.plant_card").withStyle(TextFormatting.GREEN));
		PlantCardItem item = (PlantCardItem) stack.getItem();
		if(item == null) {
			System.out.println("ERROR : Wrong Plant Card Item !");
			return ;
		}
		PlantType plant = item.plantType;
		if(plant.getUpgradeFrom().isPresent()) {
			tooltip.add(new TranslationTextComponent("tooltip.pvz." + plant.toString().toLowerCase() + "_card").withStyle(TextFormatting.RED));
		} else if(plant == PVZPlants.DOOM_SHROOM) {
			tooltip.add(new TranslationTextComponent("tooltip.pvz." + plant.toString().toLowerCase() + "_card").withStyle(TextFormatting.DARK_RED));
		}
	}
	
	/**
	 * send helpful info.
	 */
	public void notifyPlayerAndCD(PlayerEntity player, ItemStack stack, ITextComponent text) {
		if(! player.level.isClientSide) {
			PlayerUtil.sendMsgTo(player, text);
		    player.getCooldowns().addCooldown(stack.getItem(), 10);
		}
	}
	
	/**
	 * get the final cost when placing plant.
	 */
	public int getCardSunCost(PlayerEntity player, ItemStack stack) {
		final int range = 30;
		final long count = EntityUtil.getFriendlyLivings(player, EntityUtil.getEntityAABB(player, range, range))
		    .stream().filter(entity -> entity instanceof PVZPlantEntity).count();
		final long multipy = Math.max(1, (count - ConfigUtil.getLimitPlantCount()) / 10);
		return (int) Math.min(100000, this.getBasisSunCost(stack) * multipy);
	}
	
	/**
	 * the cost of plant card without consider range plants count.
	 */
	public int getBasisSunCost(ItemStack stack) {
		if(stack.getItem() instanceof PlantCardItem) {
			PlantType plantType = ((PlantCardItem) stack.getItem()).plantType;
			return Math.max(plantType.getCost() - SunReduceEnchantment.getSunReduceNum(stack), 1);
		}
		return 1;
	}
	
	/**
	 * get cooldown for current plant
	 */
	public static int getPlantCardCD(PlayerEntity player, ItemStack stack, PlantCardItem item, int plantLvl) {
		int cd = item.getPlantCardCD(player, item.plantType, plantLvl);
		if (player.hasEffect(EffectRegister.EXCITE_EFFECT.get())) {
			int lvl = player.getEffect(EffectRegister.EXCITE_EFFECT.get()).getAmplifier();
			float mult = Math.max(0, 0.9f - 0.1f * lvl);
			cd = (int) Math.floor(cd * mult);
		}
		return cd;
	}
	
	public int getPlantCardCD(PlayerEntity player, PlantType plant, int lvl) {
		return plant.getCD().getCD(lvl);
	}

	@Override
	public int getEnchantmentValue() {
		return plantType.getRank().enchantPoint;
	}
	
}

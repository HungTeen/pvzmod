package com.hungteen.pvz.common.item.spawn.card;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.common.advancement.trigger.PlayerPlacePlantTrigger;
import com.hungteen.pvz.common.core.ICoolDown;
import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.enchantment.EnchantmentRegister;
import com.hungteen.pvz.common.enchantment.card.ImmediateCDEnchantment;
import com.hungteen.pvz.common.enchantment.card.LevelUpEnchantment;
import com.hungteen.pvz.common.enchantment.card.SunReduceEnchantment;
import com.hungteen.pvz.common.enchantment.card.plantcard.BreakOutEnchantment;
import com.hungteen.pvz.common.enchantment.card.plantcard.DenselyPlantEnchantment;
import com.hungteen.pvz.common.enchantment.card.plantcard.SoillessPlantEnchantment;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantDefenderEntity;
import com.hungteen.pvz.common.entity.plant.magic.ImitaterEntity;
import com.hungteen.pvz.common.entity.plant.magic.ImitaterEntity.ImitateType;
import com.hungteen.pvz.common.event.events.SummonCardUseEvent;
import com.hungteen.pvz.common.impl.plant.OtherPlants;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.ConfigUtil;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
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
	private static final Set<PlantType> TOOL_TIP_TYPES = new HashSet<>(Arrays.asList(
			PVZPlants.DOOM_SHROOM, OtherPlants.GOLD_LEAF
	));
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
	 * only consider placement in water. <br>
	 * 1. check cool down. <br>
	 * 2. check can place in water. <br>
	 * 3. place water plants. <br>
	 */
	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand handIn) {
		final ItemStack heldStack = getHeldStack(player.getItemInHand(handIn));
		final ItemStack plantStack = getPlantStack(heldStack);
		final PlantCardItem cardItem = (PlantCardItem) plantStack.getItem();
		final PlantType plantType = cardItem.plantType;
		if(world.isClientSide) {
			return ActionResult.success(heldStack);
		}
		/* check cool down */
		if(player.getCooldowns().isOnCooldown(heldStack.getItem())) {
			this.notifyPlayerAndCD(player, heldStack, CD_ERROR);
			return ActionResult.fail(heldStack);
		}
		/* do ray check */
		final BlockRayTraceResult result = getPlayerPOVHitResult(world, player, RayTraceContext.FluidMode.SOURCE_ONLY);
		if (result.getType() == RayTraceResult.Type.BLOCK) {
			final BlockRayTraceResult raytraceResult = result.withPosition(result.getBlockPos().above());
			final BlockPos pos = raytraceResult.getBlockPos();
			/* can not place here */
			if (world.getFluidState(pos.below()).getType() != Fluids.WATER || raytraceResult.getDirection() != Direction.UP || ! world.isEmptyBlock(pos)) {
				return ActionResult.pass(heldStack);
		    }
			/* check plant type that can not place in water */
			if(! plantType.isWaterPlant() || (plantType == PVZPlants.CAT_TAIL && ! SoillessPlantEnchantment.isSoilless(plantStack))) {
			    this.notifyPlayerAndCD(player, heldStack, GROUND_ERROR);
			    return ActionResult.fail(heldStack);
		    }
			if(plantType.isBlockPlant()) {
				if(PlantCardItem.checkSunAndPlaceBlock(player, heldStack, plantStack, cardItem, pos)) {
					return ActionResult.success(heldStack);
				}
			} else {
				if(checkSunAndSummonPlant(player, heldStack, plantStack, cardItem, pos, (l)->{})) {
					return ActionResult.success(heldStack);
				}
			}
			return ActionResult.fail(heldStack);
		} else {
			return ActionResult.pass(heldStack);
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
		final ItemStack heldStack = context.getItemInHand();
		final ItemStack plantStack = getPlantStack(context.getItemInHand());
		final PlantCardItem cardItem = (PlantCardItem) plantStack.getItem();
		final PlantType plantType = cardItem.plantType;
		final BlockPos pos = context.getClickedPos();
		final boolean isSoilless = SoillessPlantEnchantment.isSoilless(plantStack);
		if(world.isClientSide) {
			return ActionResultType.SUCCESS;
		}
		if(plantType == null) {
			PVZMod.LOGGER.error("Plant Card Use : Error Card !");
			return ActionResultType.FAIL;
		}
		/* check cool down */
		if(player.getCooldowns().isOnCooldown(heldStack.getItem())) {
			this.notifyPlayerAndCD(player, heldStack, CD_ERROR);
			return ActionResultType.FAIL;
		}
		/* check outer plants */
		if(plantType.isOuterPlant()) {
			this.notifyPlayerAndCD(player, heldStack, OUTER_ERROR);
			return ActionResultType.FAIL;
		}
		
		/* check water plants */
		if(plantType.isWaterPlant()) {
			/* special placement for cat tail */
			if(plantType == PVZPlants.CAT_TAIL) {
				if(isSoilless && world.getFluidState(pos.above()).getType() == Fluids.WATER) {
				    return this.use(world, player, hand).getResult();
				}
			} else if(! isSoilless || world.getFluidState(pos.above()).getType() == Fluids.WATER) {
			    return this.use(world, player, hand).getResult();
			}
		}
		/* can not place here */
		if(context.getClickedFace() != Direction.UP || ! world.isEmptyBlock(pos.above())) {
			this.notifyPlayerAndCD(player, heldStack, GROUND_ERROR);
			return ActionResultType.FAIL;
		}
		/* check advance plants without Cat Tail */
		if(! isSoilless && plantType.getUpgradeFrom().isPresent()) {
			this.notifyPlayerAndCD(player, heldStack, UPGRADE_ERROR);
			return ActionResultType.FAIL;
		}
		/* check placement match with current block */
		if(! isSoilless && ! plantType.getPlacement().canPlaceOnBlock(world.getBlockState(pos).getBlock())) {
			this.notifyPlayerAndCD(player, heldStack, GROUND_ERROR);
			return ActionResultType.FAIL;
		}
		if(plantType.isBlockPlant()) {
			if(world.getBlockState(pos).canBeReplaced(new BlockItemUseContext(context))) {
				checkSunAndPlaceBlock(player, heldStack, plantStack, cardItem, pos);
				return ActionResultType.SUCCESS;
			} else if(world.isEmptyBlock(pos.above()) && world.getBlockState(pos).canOcclude()) {// can plant here
			    checkSunAndPlaceBlock(player, heldStack, plantStack, cardItem, pos.above());
			    return ActionResultType.SUCCESS;
			}
		} else {
			BlockPos spawnPos = pos;
			if(! world.getBlockState(pos).getCollisionShape(world, pos).isEmpty()) {
				spawnPos = pos.relative(context.getClickedFace());
			}
			if(checkSunAndSummonPlant(player, heldStack, plantStack, cardItem, spawnPos, (l)->{})) {
			    return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.FAIL;
	}
	
	/**
	 * check sunCost and spawn plantEntity.
	 */
	public static boolean checkSunAndSummonPlant(PlayerEntity player, ItemStack heldStack, ItemStack plantStack, PlantCardItem cardItem, BlockPos pos, Consumer<PVZPlantEntity> consumer) {
		final PlantType plantType = cardItem.plantType;
		if(checkSunAndCD(player, cardItem, plantStack, false, p -> true)){
			/* handle imitater card */
			if(heldStack.getItem() instanceof ImitaterCardItem) {
				return ImitaterCardItem.summonImitater(player, heldStack, plantStack, cardItem, pos, i -> consumer.accept(i));
			}
			/* other plant card */
			final int lvl = PlayerUtil.getPlantLvl(player, plantType);
			if(! handlePlantEntity(player, plantType, plantStack, pos, plantEntity -> {
				/* update level and its owner */
		        plantEntity.onSpawnedByPlayer(player, lvl, cardItem.getBasisSunCost(plantStack));
		        /* other operations */
		        consumer.accept(plantEntity);
		        /* enchantment effects */
				enchantPlantEntityByCard(plantEntity, plantStack);
			})) {
				return false;
			}
	    	/* handle cd and misc */
		    PlantCardItem.onUsePlantCard(player, heldStack, cardItem, lvl);
		    return true;
		}
		return false;
	}
	
	/**
	 * {@link #checkSunAndSummonPlant(PlayerEntity, ItemStack, ItemStack, PlantCardItem, BlockPos, Consumer)}
	 * {@link ImitaterCardItem#summonImitater(PlayerEntity, ItemStack, ItemStack, PlantCardItem, BlockPos, Consumer)}
	 */
	public static boolean handlePlantEntity(PlayerEntity player, PlantType plantType, ItemStack plantStack, BlockPos pos, Consumer<PVZPlantEntity> consumer) {
		if(! plantType.getEntityType().isPresent()) {
	        PVZMod.LOGGER.error("Plant Card : Summon wrong plant entity !");
		    return false;
	    }
	    PVZPlantEntity plantEntity = (PVZPlantEntity) plantType.getEntityType().get().spawn((ServerWorld) player.level, plantStack, player, pos, SpawnReason.SPAWN_EGG, true, true);
    	if (plantEntity == null) {
    		PVZMod.LOGGER.error("Plant Card : No such plant entity !");
			return false;
    	}
    	consumer.accept(plantEntity);
		return true;
	}
	
	/**
	 * check sunCost and place plantBlock.
	 */
	public static boolean checkSunAndPlaceBlock(PlayerEntity player, ItemStack plantStack, ItemStack heldStack, PlantCardItem cardItem, BlockPos pos) {
		final PlantType plantType = cardItem.plantType;
		final BlockState state = PlantCardItem.getBlockState(player, plantType);
		if(checkSunAndCD(player, cardItem, plantStack, true, p -> {
		    if(state == null) {
			    PVZMod.LOGGER.error("Plant Card : No such plant block !");
			    return false;
		    }
		    return true;
		})) {
		    /* handle cd and misc */
		    PlantCardItem.onUsePlantCard(player, heldStack, (PlantCardItem) heldStack.getItem(), 1);
		    if(heldStack.getItem() instanceof ImitaterCardItem) {
		    	if(! ImitaterCardItem.summonImitater(player, heldStack, plantStack, cardItem, pos, (imitater) -> {})){
		    		return false;
		    	}
		    } else {
		    	handlePlantBlock(player.level, plantType, state, pos);
		    }
		    if (player instanceof ServerPlayerEntity) {
		        CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) player, pos, heldStack);
		    }
		    return true;
		}
		return false;
	}
	
	/**
	 * {@link #checkSunAndPlaceBlock(PlayerEntity, ItemStack, ItemStack, PlantCardItem, BlockPos)}
	 * {@link ImitaterEntity#imitate(PlantType)}
	 */
	public static void handlePlantBlock(World world, PlantType plantType, BlockState state, BlockPos pos) {
		world.setBlock(pos, state, 11);
		world.playSound((PlayerEntity) null, pos.getX(), pos.getY(), pos.getZ(), plantType.isWaterPlant() ? SoundRegister.PLANT_IN_WATER.get() : SoundRegister.PLANT_ON_GROUND.get(), SoundCategory.BLOCKS, 1F, 1F);
	}
	
	/**
	 * check sunCost and add outerplant for plantEntity
	 */
	public static boolean checkSunAndOuterPlant(PlayerEntity player, PVZPlantEntity plantEntity, PlantCardItem cardItem,
			ItemStack heldStack) {
		/* check held stack */
		if(! checkItemStackAndCD(player, heldStack, stack -> ((PlantCardItem) stack.getItem()).plantType.isOuterPlant())) {
			return false;
		}
		final ItemStack plantStack = getPlantStack(heldStack);
		final PlantType plantType = ((PlantCardItem) plantStack.getItem()).plantType;
		final int lvl = PlayerUtil.getPlantLvl(player, plantType);
		final int cost = cardItem.getBasisSunCost(plantStack);
		if(heldStack.getItem() instanceof ImitaterCardItem) {
			if(checkSunAndSummonPlant(player, heldStack, plantStack, cardItem, plantEntity.blockPosition(), p -> {
				if(p instanceof ImitaterEntity) {
					((ImitaterEntity) p).setImitateType(ImitateType.OUTER);
					((ImitaterEntity) p).setTargetEntity(plantEntity);
					((ImitaterEntity) p).setImitateAction(pp -> {
						pp.onPlaceOuterPlant(plantType, lvl, cost);
						BreakOutEnchantment.checkAndBreakOut(pp, plantStack);
					});
				}
			})){
				return true;
			}
			return true;
		} else {
			/* not consider surrounding plants number */
			if(checkSunAndCD(player, cardItem, plantStack, true, p -> {
			    if(! plantEntity.canPlaceOuterPlant()) {
				    cardItem.notifyPlayerAndCD(player, heldStack, OUTER_FULL);
				    return false;
			    }
			    return true;
		    })) {
			    plantEntity.onPlaceOuterPlant(plantType, lvl, cost);
			    /* check break out enchantment */
			    BreakOutEnchantment.checkAndBreakOut(plantEntity, plantStack);
		        onUsePlantCard(player, heldStack, cardItem, lvl);
		        return true;
			}
		}
		return false;
	}
	
	/**
	 * check sunCost and heal defender plantEntity.
	 * {@link PVZPlantEntity#interactAt(PlayerEntity, net.minecraft.util.math.vector.Vector3d, Hand)}
	 */
	public static boolean checkSunAndHealPlant(PlayerEntity player, PVZPlantEntity plantEntity, PlantCardItem cardItem,
			ItemStack heldStack) {
		/* check held stack */
		if(! checkItemStackAndCD(player, heldStack, stack -> true)) {
			return false;
		}
		final ItemStack plantStack = getPlantStack(heldStack);
		final PlantType plantType = ((PlantCardItem) plantStack.getItem()).plantType;
		/* can plant entity be heal or not */
		if(plantEntity instanceof PlantDefenderEntity) {
			if(EntityUtil.canAttackEntity(plantEntity, player) || ! plantType.equals(plantEntity.getPlantType())) {
			    return false;
			}
		} else {
			return false;
		}
		if(heldStack.getItem() instanceof ImitaterCardItem) {
			if(checkSunAndSummonPlant(player, heldStack, plantStack, cardItem, plantEntity.blockPosition(), p -> {
				if(p instanceof ImitaterEntity) {
					((ImitaterEntity) p).setImitateType(ImitateType.HEAL);
					((ImitaterEntity) p).setTargetEntity(plantEntity);
				}
			})){
				return true;
			}
			return true;
		} else {/* not consider surrounding plants number */
		    if(checkSunAndCD(player, cardItem, plantStack, true, p -> true)){
			    onUsePlantCard(player, heldStack, cardItem, PlayerUtil.getPlantLvl(player, plantType));
			    plantEntity.onHealByCard();
			    return true;
		    }
		}
		return false;
	}
	
	/**
	 * check sunCost and heal defender plantEntity.
	 * {@link PVZPlantEntity#interactAt(PlayerEntity, net.minecraft.util.math.vector.Vector3d, Hand)}
	 */
	public static boolean checkSunAndUpgradePlant(PlayerEntity player, PVZPlantEntity plantEntity, PlantCardItem cardItem,
			ItemStack heldStack) {
		/* check held stack */
		if(! checkItemStackAndCD(player, heldStack, stack -> true)) {
			return false;
		}
		
		final ItemStack plantStack = getPlantStack(heldStack);
		final PlantType plantType = ((PlantCardItem) plantStack.getItem()).plantType;
		if(plantType.getUpgradeFrom().isPresent() && plantEntity.getPlantType().equals(plantType.getUpgradeFrom().get())) {
			if(plantEntity.canBeUpgrade(player) && PlantCardItem.checkSunAndSummonPlant(player, heldStack, plantStack, cardItem, plantEntity.blockPosition(), (plant) -> {
				if(plant instanceof ImitaterEntity) {
					((ImitaterEntity) plant).setImitateAction((p) -> plantEntity.onPlantUpgrade(p));
				} else {
					plantEntity.onPlantUpgrade(plant);
				}
			})) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * check sunCost and heal defender plantEntity.
	 * {@link PVZPlantEntity#interactAt(PlayerEntity, net.minecraft.util.math.vector.Vector3d, Hand)}
	 */
	public static boolean checkSunAndInteractEntity(PlayerEntity player, Entity entity, PlantCardItem cardItem,
			ItemStack heldStack, Predicate<PlantType> pre, Consumer<PVZPlantEntity> con) {
		/* check held stack */
		if(! checkItemStackAndCD(player, heldStack, stack -> true)) {
			return false;
		}
		final ItemStack plantStack = getPlantStack(heldStack);
		final PlantType plantType = ((PlantCardItem) plantStack.getItem()).plantType;
		if(! pre.test(plantType)) {
			return false;
		}
		if(PlantCardItem.checkSunAndSummonPlant(player, heldStack, plantStack, cardItem, entity.blockPosition(), plantEntity -> {
			if(plantEntity instanceof ImitaterEntity) {
				((ImitaterEntity) plantEntity).setImitateAction(p -> con.accept(p));
			} else {
				con.accept(plantEntity);
			}
		})) {
			return true;
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
		/* check lock */
		if(PlayerUtil.isPlantLocked(player, cardItem.plantType)) {
			cardItem.notifyPlayerAndCD(player, stack, LOCK_ERROR);
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
	 * does imitater has a correct card.
	 */
	private static boolean checkItemStackAndCD(PlayerEntity player, ItemStack heldStack, Predicate<ItemStack> predicate) {
		ItemStack stack = heldStack;
		if(heldStack.getItem() instanceof ImitaterCardItem) {
			stack = getPlantStack(heldStack);
		}
		return stack.getItem() instanceof PlantCardItem && ! player.getCooldowns().isOnCooldown(stack.getItem()) && predicate.test(stack);
	}
	
	/**
	 * {@link #checkSunAndSummonPlant(PlayerEntity, ItemStack, PlantCardItem, BlockPos, Consumer)}
	 */
	public static void enchantPlantEntityByCard(PVZPlantEntity plantEntity, ItemStack stack) {
		/* check break out enchantment */
		BreakOutEnchantment.checkAndBreakOut(plantEntity, stack);
		/* check charm enchantment */
		if(EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegister.CHARM.get(), stack) > 0) {
			plantEntity.onCharmedBy(null);
		}
		/* check soilless enchantment */
		if(EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegister.SOILLESS_PLANT.get(), stack) > 0) {
			plantEntity.setImmunneToWeak(true);
		}
		/* check level up enchantment */
		LevelUpEnchantment.levelUp(plantEntity, stack);
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
		} else {
			player.getCooldowns().addCooldown(stack.getItem(), 20);
		}
		if(player instanceof ServerPlayerEntity) {
			/* wait for improve */
		    PlayerPlacePlantTrigger.INSTANCE.trigger((ServerPlayerEntity) player, item.plantType.getId());
		}
		player.awardStat(Stats.ITEM_USED.get(item));
	}
    
	/**
	 * set PlantCard Item cool down.
	 */
	public static void handlePlantCardCoolDown(PlayerEntity player, ItemStack stack, PlantCardItem item, int plantLvl) {
		/* handle immediate cool down enchantment */
		if(ImmediateCDEnchantment.canImmediateCD(stack, player.getRandom())) {
			PlayerUtil.setCardCD(player, item, 20);
		} else {
			PlayerUtil.setCardCD(player, item, getPlantCardCD(player, stack, item, plantLvl));
		}
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
		tooltip.add(new TranslationTextComponent("tooltip.pvz.plant_card_info").withStyle(TextFormatting.GREEN));
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		final PlantCardItem item = (PlantCardItem) stack.getItem();
		if(item != null) {
		    final PlantType plant = item.plantType;
		    /* upgrade plant without soilless plant enchantment */
		    if(! SoillessPlantEnchantment.isSoilless(stack)) {
		    	if(plant.getUpgradeFrom().isPresent()) {
		    	    if(plant == PVZPlants.COB_CANNON) {
		    		    tooltip.add(new TranslationTextComponent("tooltip.pvz.cob_cannon_card").withStyle(TextFormatting.RED));
		    	    }  else {
		    		    tooltip.add(new TranslationTextComponent("tooltip.pvz.upgrade_card").append(plant.getUpgradeFrom().get().getText().withStyle(TextFormatting.UNDERLINE)).withStyle(TextFormatting.RED));
		    	    }
		    	} else if(plant == PVZPlants.CAT_TAIL) {
		    		tooltip.add(new TranslationTextComponent("tooltip.pvz.upgrade_card").append(PVZPlants.LILY_PAD.getText().withStyle(TextFormatting.UNDERLINE)).withStyle(TextFormatting.RED));
		    	}
		    }
		    /* misc */
		    if(TOOL_TIP_TYPES.contains(plant)) {
			    tooltip.add(new TranslationTextComponent("tooltip.pvz." + plant.toString().toLowerCase() + "_card").withStyle(TextFormatting.DARK_RED));
		    }
		}
	}
	
	/**
	 * send helpful info.
	 */
	public void notifyPlayerAndCD(PlayerEntity player, ItemStack stack, ITextComponent text) {
		if(! player.level.isClientSide) {
			if(text != null) {
				PlayerUtil.sendMsgTo(player, text);
			}
		    player.getCooldowns().addCooldown(stack.getItem(), 10);
		}
	}
	
	/**
	 * get the final cost when placing plant.
	 */
	public int getCardSunCost(PlayerEntity player, ItemStack stack) {
		final int range = 30;
		final long count = EntityUtil.getFriendlyLivings(player, EntityUtil.getEntityAABB(player, range, range))
		    .stream().filter(entity -> entity instanceof PVZPlantEntity).count() + 1;
		final long multipy = Math.max(0, (count - ConfigUtil.getLimitPlantCount() - DenselyPlantEnchantment.getExtraPlantNum(stack) + 4) / 5);
		return (int) Math.min(100000L, this.getBasisSunCost(stack) * (1L << multipy));
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
	 * the cd of plant card without consider other condition.
	 */
	public ICoolDown getBasisCoolDown(ItemStack stack) {
		if(stack.getItem() instanceof PlantCardItem) {
			PlantType plantType = ((PlantCardItem) stack.getItem()).plantType;
			return plantType.getCD();
		}
		return ICoolDown.DEFAULT;
	}
	
	/**
	 * get cool down for current plant card.
	 */
	private static int getPlantCardCD(PlayerEntity player, ItemStack stack, PlantCardItem item, int plantLvl) {
		int cd = item.getBasisCoolDown(stack).getCD(plantLvl);
		if (player.hasEffect(EffectRegister.EXCITE_EFFECT.get())) {
			int lvl = player.getEffect(EffectRegister.EXCITE_EFFECT.get()).getAmplifier();
			float mult = Math.max(0, 0.9f - 0.1f * lvl);
			cd = (int) Math.floor(cd * mult);
		}
		return cd;
	}
	
	private static ItemStack getHeldStack(ItemStack stack) {
		return ImitaterCardItem.getDoubleStack(stack).getFirst();
	}
	
	private static ItemStack getPlantStack(ItemStack stack) {
		return ImitaterCardItem.getDoubleStack(stack).getSecond();
	}
	
	@Override
	public int getEnchantmentValue() {
		return plantType.getRank().enchantPoint;
	}
	
}

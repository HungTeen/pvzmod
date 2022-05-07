package com.hungteen.pvz.common.item.spawn.card;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.advancement.trigger.SummonCardUseTrigger;
import com.hungteen.pvz.common.block.PVZBlocks;
import com.hungteen.pvz.common.enchantment.card.plant.SoillessPlantEnchantment;
import com.hungteen.pvz.common.entity.plant.base.PVZPlant;
import com.hungteen.pvz.common.impl.type.plant.PVZPlants;
import com.hungteen.pvz.common.sound.PVZSounds;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-27 20:30
 *
 * Normal plant type : place on top face of block. <br>
 * Outer plant type : place on plant entity. <br>
 * Pumpkin heal type : heal pumpkin outer plant. <br>
 * Defender plant heal type : Heal Defender Plant. <br>
 * Upgrade plant type : base plant upgrade. <br>
 * CatTail upgrade type : cattail upgrade on lilypad. <br>
 * CoffeeBean place type : coffee bean ride on plant entity. <br>
 * GraveBuster place type : gravebuster ride on tombstone. <br>
 * Place block in water type : lilypad place in water. <br>
 * Place block on ground type : flower pot place on ground.
 **/
public class PlantCardItem extends SummonCardItem {

    private static final Set<IPlantType> TOOL_TIP_TYPES = new HashSet<>(Arrays.asList(
//            PVZPlants.DOOM_SHROOM, OtherPlants.GOLD_LEAF
    ));
    public final IPlantType plantType;

    public PlantCardItem(IPlantType plant, boolean isFragment) {
        super(plant, isFragment);
        this.plantType = plant;
    }

    public PlantCardItem(Properties properties, IPlantType plant, boolean isFragment) {
        super(properties, plant, isFragment);
        this.plantType = plant;
    }

    /**
     * only consider placement in water. <br>
     * 1. check cool down. <br>
     * 2. check can place in water. <br>
     * 3. place water plants. <br>
     */
    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand handIn) {
        final ItemStack heldStack = getHeldStack(player.getItemInHand(handIn));
        final ItemStack plantStack = getSummonStack(heldStack);
        final PlantCardItem cardItem = (PlantCardItem) plantStack.getItem();
        final IPlantType plantType = cardItem.plantType;
        if(world.isClientSide) {
            return InteractionResultHolder.success(heldStack);
        }
        /* check cool down */
        if(player.getCooldowns().isOnCooldown(heldStack.getItem())) {
            this.notifyPlayerAndCD(player, heldStack, PlacementErrors.CD_ERROR);
            return InteractionResultHolder.fail(heldStack);
        }
        /* do ray check */
        final BlockHitResult result = getPlayerPOVHitResult(world, player, ClipContext.Fluid.SOURCE_ONLY);
        if (result.getType() == BlockHitResult.Type.BLOCK) {
            final BlockHitResult raytraceResult = result.withPosition(result.getBlockPos().above());
            final BlockPos pos = raytraceResult.getBlockPos();
            /* can not place here */
            if (world.getFluidState(pos.below()).getType() != Fluids.WATER || raytraceResult.getDirection() != Direction.UP || ! world.isEmptyBlock(pos)) {
                return InteractionResultHolder.pass(heldStack);
            }
            /* check plant type that can not place in water */
//            if(! plantType.isWaterPlant() || (plantType == PVZPlants.CAT_TAIL && ! SoillessPlantEnchantment.isSoilless(plantStack))) {
//                this.notifyPlayerAndCD(player, heldStack, PlacementErrors.GROUND_ERROR);
//                return InteractionResultHolder.fail(heldStack);
//            }
            if(plantType.getPlantBlock().isPresent()) {
                if(PlantCardItem.checkSunAndPlaceBlock(player, heldStack, plantStack, cardItem, pos)) {
                    return InteractionResultHolder.success(heldStack);
                }
            } else {
                if(checkSunAndSummonPlant(player, heldStack, plantStack, cardItem, pos, (l)->{})) {
                    return InteractionResultHolder.success(heldStack);
                }
            }
            return InteractionResultHolder.fail(heldStack);
        } else {
            return InteractionResultHolder.pass(heldStack);
        }
    }

    /**
     * only consider common plants that can place on suitable ground.
     * not include imitater, outer plants, water plants,
     */
    @Override
    public InteractionResult useOn(UseOnContext context) {
        final Level world = context.getLevel();
        final Player player = context.getPlayer();
        final InteractionHand hand = context.getHand();
        final ItemStack heldStack = context.getItemInHand();
        final ItemStack plantStack = getSummonStack(context.getItemInHand());
        final PlantCardItem cardItem = (PlantCardItem) plantStack.getItem();
        final IPlantType plantType = cardItem.plantType;
        final BlockPos pos = context.getClickedPos();
        final boolean isSoilless = SoillessPlantEnchantment.isSoilless(plantStack);
        if(world.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        if(plantType == null) {
            PVZMod.LOGGER.error("Plant Card Use : Error Card !");
            return InteractionResult.FAIL;
        }
        /* check cool down */
        if(player.getCooldowns().isOnCooldown(heldStack.getItem())) {
            this.notifyPlayerAndCD(player, heldStack, PlacementErrors.CD_ERROR);
            return InteractionResult.FAIL;
        }
        /* check outer plants */
        if(plantType.isOuterPlant()) {
            this.notifyPlayerAndCD(player, heldStack, PlacementErrors.OUTER_ERROR);
            return InteractionResult.FAIL;
        }

        /* check water plants */
        if(plantType.isWaterPlant()) {
            /* special placement for cat tail */
//            if(plantType == PVZPlants.CAT_TAIL) {
//                if(isSoilless && world.getFluidState(pos.above()).getType() == Fluids.WATER) {
//                    return this.use(world, player, hand).getResult();
//                }
//            } else if(! isSoilless || world.getFluidState(pos.above()).getType() == Fluids.WATER) {
//                return this.use(world, player, hand).getResult();
//            }
        }
        /* can not place here */
        if(context.getClickedFace() != Direction.UP || ! world.isEmptyBlock(pos.above())) {
            this.notifyPlayerAndCD(player, heldStack, PlacementErrors.GROUND_ERROR);
            return InteractionResult.FAIL;
        }
        /* check advance plants without Cat Tail */
        if(! isSoilless && plantType.getUpgradeFrom().isPresent()) {
            this.notifyPlayerAndCD(player, heldStack, PlacementErrors.UPGRADE_ERROR);
            return InteractionResult.FAIL;
        }
        /* check placement match with current block */
        if(! isSoilless && ! plantType.getPlacement().canPlaceOnBlock(world.getBlockState(pos).getBlock())) {
            this.notifyPlayerAndCD(player, heldStack, PlacementErrors.GROUND_ERROR);
            return InteractionResult.FAIL;
        }
        if(plantType.getPlantBlock().isPresent()) {
            if(world.getBlockState(pos).canBeReplaced(new BlockPlaceContext(context))) {
                checkSunAndPlaceBlock(player, heldStack, plantStack, cardItem, pos);
                return InteractionResult.SUCCESS;
            } else if(world.isEmptyBlock(pos.above()) && world.getBlockState(pos).canOcclude()) {// can plant here
                checkSunAndPlaceBlock(player, heldStack, plantStack, cardItem, pos.above());
                return InteractionResult.SUCCESS;
            }
        } else {
            BlockPos spawnPos = pos;
            if(! world.getBlockState(pos).getCollisionShape(world, pos).isEmpty()) {
                spawnPos = pos.relative(context.getClickedFace());
            }
            if(checkSunAndSummonPlant(player, heldStack, plantStack, cardItem, spawnPos, (l)->{})) {
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.FAIL;
    }

    /**
     * check sunCost and spawn plantEntity.
     */
    public static boolean checkSunAndSummonPlant(Player player, ItemStack heldStack, ItemStack plantStack, PlantCardItem cardItem, BlockPos pos, Consumer<PVZPlant> consumer) {
        final IPlantType plantType = cardItem.plantType;
        if(checkSunAndCD(player, cardItem, plantStack, p -> true)){
            /* handle imitater card */
//            if(heldStack.getItem() instanceof ImitaterCardItem) {
//                return ImitaterCardItem.summonImitater(player, heldStack, plantStack, cardItem, pos, i -> consumer.accept(i));
//            }
            /* other plant card */
            if(! handlePlantEntity(player, plantType, plantStack, pos, plantEntity -> {
				/* update maxLevel and its owner */
                plantEntity.onSpawnedByPlayer(player, getCardCost(player, plantStack));
                /* other operations */
                consumer.accept(plantEntity);
                /* enchantment effects */
                enchantPlantEntityByCard(plantEntity, plantStack);
            })) {
                return false;
            }
            /* handle cd and misc */
            PlantCardItem.onUsePlantCard(player, heldStack, plantStack, cardItem);
            return true;
        }
        return false;
    }

    /**
     * {@link #checkSunAndSummonPlant(Player, ItemStack, ItemStack, PlantCardItem, BlockPos, Consumer)}
     */
    public static boolean handlePlantEntity(Player player, IPlantType plantType, ItemStack plantStack, BlockPos pos, Consumer<PVZPlant> consumer) {
        if(! plantType.getEntityType().isPresent()) {
            PVZMod.LOGGER.error("Plant Card : Summon wrong plant entity !");
            return false;
        }
        PVZPlant plantEntity = (PVZPlant) plantType.getEntityType().get().spawn((ServerLevel) player.level, plantStack, player, pos, MobSpawnType.SPAWN_EGG, true, true);
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
    public static boolean checkSunAndPlaceBlock(Player player, ItemStack plantStack, ItemStack heldStack, PlantCardItem cardItem, BlockPos pos) {
        final IPlantType plantType = cardItem.plantType;
        final BlockState state = PlantCardItem.getBlockState(player, plantType);
        if(checkSunAndCD(player, cardItem, plantStack, p -> {
            if(state == null) {
                PVZMod.LOGGER.error("Plant Card : No such plant block !");
                return false;
            }
            return true;
        })) {
            /* handle cd and misc */
            PlantCardItem.onUsePlantCard(player, heldStack, plantStack, (PlantCardItem) heldStack.getItem());
            if(heldStack.getItem() instanceof ImitaterCardItem) {
//                if(! ImitaterCardItem.summonImitater(player, heldStack, plantStack, cardItem, pos, (imitater) -> {})){
//                    return false;
//                }
            } else {
                handlePlantBlock(player.level, plantType, state, pos);
            }
            if (player instanceof ServerPlayer) {
                CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) player, pos, heldStack);
            }
            return true;
        }
        return false;
    }

    /**
     * {@link #checkSunAndPlaceBlock(Player, ItemStack, ItemStack, PlantCardItem, BlockPos)}
     */
    public static void handlePlantBlock(Level world, IPlantType plantType, BlockState state, BlockPos pos) {
        world.setBlock(pos, state, 11);
        world.playSound((Player) null, pos.getX(), pos.getY(), pos.getZ(), plantType.isWaterPlant() ? PVZSounds.PLACE_PLANT_WATER.get() : PVZSounds.PLACE_PLANT_GROUND.get(), SoundSource.BLOCKS, 1F, 1F);
    }

//    /**
//     * check sunCost and add outerplant for plantEntity
//     */
//    public static boolean checkSunAndOuterPlant(Player player, PVZPlant plantEntity, PlantCardItem cardItem,
//                                                ItemStack heldStack) {
//        /* check held stack */
//        if(! checkItemStackAndCD(player, heldStack, stack -> ((PlantCardItem) stack.getItem()).plantType.getOuterPlant().isPresent())) {
//            return false;
//        }
//        final ItemStack plantStack = getSummonStack(heldStack);
//        final IPlantType plantType = ((PlantCardItem) plantStack.getItem()).plantType;
//        final int cost = cardItem.getBasisSunCost(plantStack);
//        if(heldStack.getItem() instanceof ImitaterCardItem) {
//            if(checkSunAndSummonPlant(player, heldStack, plantStack, cardItem, plantEntity.blockPosition(), p -> {
////                if(p instanceof ImitaterEntity) {
////                    ((ImitaterEntity) p).setImitateType(ImitateType.OUTER);
////                    ((ImitaterEntity) p).setTargetEntity(plantEntity);
////                    ((ImitaterEntity) p).setImitateAction(pp -> {
////                        pp.onPlaceOuterPlant(plantType, cost);
////                        BreakOutEnchantment.checkAndBreakOut(pp, plantStack);
////                    });
////                }
//            })){
//                return true;
//            }
//            return true;
//        } else {
//            /* not consider surrounding plants number */
//            if(checkSunAndCD(player, cardItem, plantStack, true, p -> {
//                if(! plantEntity.canPlaceOuterPlant()) {
//                    cardItem.notifyPlayerAndCD(player, heldStack, PlacementErrors.OUTER_FULL);
//                    return false;
//                }
//                return true;
//            })) {
//                plantEntity.onPlaceOuterPlant(plantType, cost);
//                /* check break out enchantment */
//                BreakOutEnchantment.checkAndBreakOut(plantEntity, plantStack);
//                onUsePlantCard(player, heldStack, plantStack, cardItem);
//                return true;
//            }
//        }
//        return false;
//    }

//    /**
//     * check sunCost and heal defender plantEntity.
//     * {@link PVZPlantEntity#interactAt(Player, net.minecraft.util.math.vector.Vector3d, Hand)}
//     */
//    public static boolean checkSunAndHealPlant(Player player, PVZPlant plantEntity, PlantCardItem cardItem,
//                                               ItemStack heldStack) {
//        /* check held stack */
//        if(! checkItemStackAndCD(player, heldStack, stack -> true)) {
//            return false;
//        }
//        final ItemStack plantStack = getSummonStack(heldStack);
//        final IPlantType plantType = ((PlantCardItem) plantStack.getItem()).plantType;
//        final float percent = BandageEnchantment.getHealPercent(plantStack);
//        /* the same type or specific outer plant card */
//        if(! plantType.equals(plantEntity.getPlantType()) && ! (plantType.getOuterPlant().isPresent() && plantEntity.getOuterPlantInfo().isPresent() && plantEntity.getOuterPlantInfo().get().getType().equals(plantType))) {
//            return false;
//        }
//        if(checkSunAndCD(player, cardItem, plantStack, true, p -> true)){
//            onUsePlantCard(player, heldStack, plantStack, cardItem);
//            plantEntity.onHealBy(plantType, percent);
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * check sunCost and heal defender plantEntity.
//     * {@link PVZPlantEntity#interactAt(PlayerEntity, net.minecraft.util.math.vector.Vector3d, Hand)}
//     */
//    public static boolean checkSunAndUpgradePlant(PlayerEntity player, PVZPlantEntity plantEntity, PlantCardItem cardItem,
//                                                  ItemStack heldStack) {
//        /* check held stack */
//        if(! checkItemStackAndCD(player, heldStack, stack -> true)) {
//            return false;
//        }
//
//        final ItemStack plantStack = getSummonStack(heldStack);
//        final IPlantType plantType = ((PlantCardItem) plantStack.getItem()).plantType;
//        if(plantType.getUpgradeFrom().isPresent() && plantEntity.getPlantType().equals(plantType.getUpgradeFrom().get())) {
//            if(plantEntity.canBeUpgrade(player) && PlantCardItem.checkSunAndSummonPlant(player, heldStack, plantStack, cardItem, plantEntity.blockPosition(), (plant) -> {
//                if(plant instanceof ImitaterEntity) {
//                    ((ImitaterEntity) plant).setImitateAction((p) -> plantEntity.onPlantUpgrade(p));
//                } else {
//                    plantEntity.onPlantUpgrade(plant);
//                }
//            })) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * check sunCost and heal defender plantEntity.
//     * {@link PVZPlantEntity#interactAt(PlayerEntity, net.minecraft.util.math.vector.Vector3d, Hand)}
//     */
//    public static boolean checkSunAndInteractEntity(PlayerEntity player, Entity entity, PlantCardItem cardItem,
//                                                    ItemStack heldStack, Predicate<IPlantType> pre, Consumer<PVZPlantEntity> con) {
//        /* check held stack */
//        if(! checkItemStackAndCD(player, heldStack, stack -> true)) {
//            return false;
//        }
//        final ItemStack plantStack = getSummonStack(heldStack);
//        final IPlantType plantType = ((PlantCardItem) plantStack.getItem()).plantType;
//        if(! pre.test(plantType)) {
//            return false;
//        }
//        if(PlantCardItem.checkSunAndSummonPlant(player, heldStack, plantStack, cardItem, entity.blockPosition(), plantEntity -> {
//            if(plantEntity instanceof ImitaterEntity) {
//                ((ImitaterEntity) plantEntity).setImitateAction(p -> con.accept(p));
//            } else {
//                con.accept(plantEntity);
//            }
//        })) {
//            return true;
//        }
//        return false;
//    }

    /**
     * check card cd and sun cost, and other predicates, finally consume sun.
     */
    private static boolean checkSunAndCD(Player player, PlantCardItem cardItem, ItemStack stack, Predicate<Player> pre) {
        /* check cool down */
        if(player.getCooldowns().isOnCooldown(cardItem)) {
            cardItem.notifyPlayerAndCD(player, stack, PlacementErrors.CD_ERROR);
            return false;
        }
        /* check lock */
        if(! cardItem.isEnjoyCard && PlayerUtil.isPAZLocked(player, cardItem.plantType)) {
            cardItem.notifyPlayerAndCD(player, stack, PlacementErrors.LOCK_ERROR);
            return false;
        }
        final int sunCost = getCardCost(player, stack);
        /* check sun */
        if(sunCost > PlayerUtil.getResource(player, Resources.SUN_NUM)) {
            cardItem.notifyPlayerAndCD(player, stack, PlacementErrors.SUN_ERROR, sunCost);
            return false;
        }
        if(pre.test(player)) {
            PlayerUtil.addResource(player, Resources.SUN_NUM, - sunCost);
            return true;
        }
        return false;
    }

    /**
     * does imitater have a correct card.
     */
    private static boolean checkItemStackAndCD(Player player, ItemStack heldStack, Predicate<ItemStack> predicate) {
        ItemStack stack = heldStack;
        if(heldStack.getItem() instanceof ImitaterCardItem) {
            stack = getSummonStack(heldStack);
        }
        return stack.getItem() instanceof PlantCardItem && ! player.getCooldowns().isOnCooldown(stack.getItem()) && predicate.test(stack);
    }

    /**
     */
    public static void enchantPlantEntityByCard(PVZPlant plantEntity, ItemStack stack) {
//        /* check break out enchantment */
//        BreakOutEnchantment.checkAndBreakOut(plantEntity, stack);
//        /* check charm enchantment */
//        if(EnchantmentHelper.getItemEnchantmentLevel(EnchantmentRegister.CHARM.get(), stack) > 0) {
//            plantEntity.onCharmedBy(null);
//        }
//        /* check soilless enchantment */
//        if(EnchantmentHelper.getItemEnchantmentLevel(PVZEnchantments.SOILLESS_PLANT.get(), stack) > 0) {
//            plantEntity.setImmuneToWeak(true);
//        }
    }

    /**
     * deal with cd and misc.
     */
    public static void onUsePlantCard(Player player, ItemStack heldStack, ItemStack plantStack, PlantCardItem item) {
//        MinecraftForge.EVENT_BUS.post(new SummonCardUseEvent(player, heldStack, plantStack));
        if(PlayerUtil.isPlayerSurvival(player)) {
            if(item.isEnjoyCard) {
                heldStack.shrink(1);
            } else {
                handlePlantCardCoolDown(player, heldStack, plantStack, item);
            }
        } else {
            player.getCooldowns().addCooldown(heldStack.getItem(), 10);
        }
        if(player instanceof ServerPlayer) {
            SummonCardUseTrigger.INSTANCE.trigger((ServerPlayer) player, heldStack, plantStack);
        }
        player.awardStat(Stats.ITEM_USED.get(item));
    }

    /**
     * set PlantCard Item cool down.
     */
    public static void handlePlantCardCoolDown(Player player, ItemStack heldStack, ItemStack plantStack, PlantCardItem item) {
        PlayerUtil.setItemStackCD(player, heldStack, getCardCD(player, plantStack));
    }

    @Nullable
    public static BlockState getBlockState(Player player, IPlantType plant) {
        return plant == PVZPlants.LILY_PAD ? PVZBlocks.LILY_PAD.get().getStateForPlacement(player) :
                plant == PVZPlants.FLOWER_POT ? PVZBlocks.FLOWER_POT.get().getStateForPlacement(player) :
                        null;
    }

    @Nullable
    public static BlockState getBlockState(Direction direction, IPlantType plant) {
        return plant == PVZPlants.LILY_PAD ? PVZBlocks.LILY_PAD.get().getStateForPlacement(direction) :
                plant == PVZPlants.FLOWER_POT ? PVZBlocks.FLOWER_POT.get().getStateForPlacement(direction) :
                        null;
    }

    @Override
    public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TranslatableComponent("tooltip.pvz.plant_card_info").withStyle(ChatFormatting.GREEN));
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        final PlantCardItem item = (PlantCardItem) stack.getItem();
        if(item != null) {
//            final IPlantType plant = item.plantType;
//            /* upgrade plant without soilless plant enchantment */
//            if(! SoillessPlantEnchantment.isSoilless(stack)) {
//                if(plant.getUpgradeFrom().isPresent()) {
//                    if(plant == PVZPlants.COB_CANNON) {
//                        tooltip.add(new TranslationTextComponent("tooltip.pvz.cob_cannon_card").withStyle(TextFormatting.RED));
//                    }  else {
//                        tooltip.add(new TranslationTextComponent("tooltip.pvz.upgrade_card").append(plant.getUpgradeFrom().get().getText().withStyle(TextFormatting.UNDERLINE)).withStyle(TextFormatting.RED));
//                    }
//                } else if(plant == PVZPlants.CAT_TAIL) {
//                    tooltip.add(new TranslationTextComponent("tooltip.pvz.upgrade_card").append(PVZPlants.LILY_PAD.getText().withStyle(TextFormatting.UNDERLINE)).withStyle(TextFormatting.RED));
//                }
//            }
//            /* misc */
//            if(TOOL_TIP_TYPES.contains(plant)) {
//                tooltip.add(new TranslationTextComponent("tooltip.pvz." + plant.toString().toLowerCase() + "_card").withStyle(TextFormatting.DARK_RED));
//            }
        }
        appendSkillToolTips(stack, tooltip);
    }

}

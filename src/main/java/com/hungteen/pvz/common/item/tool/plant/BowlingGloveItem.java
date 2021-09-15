package com.hungteen.pvz.common.item.tool.plant;

import java.util.List;
import java.util.Optional;

import com.hungteen.pvz.client.render.itemstack.BowlingGloveISTER;
import com.hungteen.pvz.common.core.PlantType;
import com.hungteen.pvz.common.entity.misc.bowling.AbstractBowlingEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.item.PVZItemGroups;
import com.hungteen.pvz.register.EntityRegister;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
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

public class BowlingGloveItem extends Item {

	public static final String BOWLING_STRING = "bowling_type";

	public BowlingGloveItem() {
		super(new Item.Properties().tab(PVZItemGroups.PVZ_MISC).stacksTo(1).setISTER(() -> BowlingGloveISTER::new));
	}

	@Override
	public ActionResultType useOn(ItemUseContext context) {
		World world = context.getLevel();
		PlayerEntity player = context.getPlayer();
		Hand hand = context.getHand();
		ItemStack stack = player.getItemInHand(hand);
		BlockPos pos = context.getClickedPos();
		Optional<PlantType> plantType = getBowlingType(stack);
		if (!plantType.isPresent())
			return ActionResultType.FAIL;
		PlantType plant = plantType.get();
		BlockPos spawnPos = pos;
		if (!world.getBlockState(pos).getCollisionShape(world, pos).isEmpty()) {
			spawnPos = pos.relative(context.getClickedFace());
		}
		if (context.getClickedFace() == Direction.UP && world.isEmptyBlock(pos.above())) {// can plant here
			EntityType<? extends AbstractBowlingEntity> entityType = getEntityTypeByPlant(plant);
			if (entityType == null) {
				System.out.println("Error : no such bowling entity !");
				return ActionResultType.FAIL;
			}
			if(! world.isClientSide) {
				AbstractBowlingEntity entity = (AbstractBowlingEntity) entityType.spawn((ServerWorld) player.level, stack, player,
					spawnPos, SpawnReason.SPAWN_EGG, true, true);
			    if (entity == null) {
				    System.out.println("Error : bowling entity spawn error!");
				    return ActionResultType.FAIL;
			    }
			    entity.summonByOwner(player);
			    entity.shoot(player);
			    if (!player.abilities.instabuild) {// reset
				    setBowlingType(stack, PVZPlants.PEA_SHOOTER);
			    }
			}
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.FAIL;
	}
	
	public static void onPickUpBowlingPlant(PVZPlantEntity plantEntity, ItemStack stack) {
		BowlingGloveItem.setBowlingType(stack, plantEntity.getPlantType());
		plantEntity.remove();
	}

	private static EntityType<? extends AbstractBowlingEntity> getEntityTypeByPlant(PlantType plant) {
		if(plant == PVZPlants.WALL_NUT) return EntityRegister.WALL_NUT_BOWLING.get();
		if(plant == PVZPlants.EXPLODE_O_NUT) return EntityRegister.EXPLOSION_BOWLING.get();
		if (plant == PVZPlants.GIANT_WALL_NUT) return EntityRegister.GIANT_NUT_BOWLING.get();
		return null;
	}

	public static Optional<PlantType> getBowlingType(ItemStack stack) {
		int type = stack.getOrCreateTag().getInt(BOWLING_STRING);
		return getPlantTypeForBowling(type);
	}

	public static ItemStack setBowlingType(ItemStack stack, PlantType plant) {
		stack.getOrCreateTag().putInt(BOWLING_STRING, getBowlingTypeForPlants(plant));
		return stack;
	}

	public static int getBowlingTypeForPlants(PlantType plant) {
		if (plant == PVZPlants.WALL_NUT) return 1;
		if (plant == PVZPlants.EXPLODE_O_NUT) return 2;
		if (plant == PVZPlants.GIANT_WALL_NUT) return 3;
		return -1;
	}

	public static Optional<PlantType> getPlantTypeForBowling(int type) {
		PlantType res = null;
		if (type == 1) res = PVZPlants.WALL_NUT;
		else if (type == 2) res = PVZPlants.EXPLODE_O_NUT;
		else if (type == 3) res = PVZPlants.GIANT_WALL_NUT;
		return Optional.ofNullable(res);
	}

	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
		if (this.allowdedIn(group)) {
			items.add(new ItemStack(this));
			items.add(setBowlingType(new ItemStack(this), PVZPlants.WALL_NUT));
			items.add(setBowlingType(new ItemStack(this), PVZPlants.EXPLODE_O_NUT));
			items.add(setBowlingType(new ItemStack(this), PVZPlants.GIANT_WALL_NUT));
		}
	}
	
	@Override
	public void appendHoverText(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		Optional<PlantType> plant = getBowlingType(stack);
		if(plant.isPresent()) {
			PlantType p = plant.get();
			tooltip.add(new TranslationTextComponent("tooltip.pvz.bowling_glove." + p.toString().toLowerCase()).withStyle(TextFormatting.GOLD));
		} else {
			tooltip.add(new TranslationTextComponent("tooltip.pvz.bowling_glove.empty").withStyle(TextFormatting.GOLD));
		}
	}

}

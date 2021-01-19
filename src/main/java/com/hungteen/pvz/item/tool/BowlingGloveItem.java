package com.hungteen.pvz.item.tool;

import java.util.List;
import java.util.Optional;

import com.hungteen.pvz.entity.misc.bowling.AbstractBowlingEntity;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.GroupRegister;
import com.hungteen.pvz.render.itemstack.BowlingGloveISTER;
import com.hungteen.pvz.utils.enums.Plants;

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

public class BowlingGloveItem extends Item {

	public static final String BOWLING_STRING = "bowling_type";

	public BowlingGloveItem() {
		super(new Item.Properties().group(GroupRegister.PVZ_MISC).maxStackSize(1).setISTER(() -> BowlingGloveISTER::new));
	}

	@Override
	public ActionResultType onItemUse(ItemUseContext context) {
		World world = context.getWorld();
		PlayerEntity player = context.getPlayer();
		Hand hand = context.getHand();
		ItemStack stack = player.getHeldItem(hand);
		BlockPos pos = context.getPos();
		Optional<Plants> plantType = getBowlingType(stack);
		if (!plantType.isPresent())
			return ActionResultType.FAIL;
		Plants plant = plantType.get();
		BlockPos spawnPos = pos;
		if (!world.getBlockState(pos).getCollisionShape(world, pos).isEmpty()) {
			spawnPos = pos.offset(context.getFace());
		}
		if (context.getFace() == Direction.UP && world.isAirBlock(pos.up())) {// can plant here
			EntityType<? extends AbstractBowlingEntity> entityType = getEntityTypeByPlant(plant);
			if (entityType == null) {
				System.out.println("Error : no such bowling entity !");
				return ActionResultType.FAIL;
			}
			if(! world.isRemote) {
				AbstractBowlingEntity entity = (AbstractBowlingEntity) entityType.spawn(player.world, stack, player,
					spawnPos, SpawnReason.SPAWN_EGG, true, true);
			    if (entity == null) {
				    System.out.println("Error : bowling entity spawn error!");
				    return ActionResultType.FAIL;
			    }
			    entity.setOwner(player);
			    entity.shoot(player);
			    if (!player.abilities.isCreativeMode) {// reset
				    setBowlingType(stack, Plants.PEA_SHOOTER);
			    }
			}
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.FAIL;
	}
	
	public static void onPickUpBowlingPlant(PVZPlantEntity plantEntity, ItemStack stack) {
		BowlingGloveItem.setBowlingType(stack, plantEntity.getPlantEnumName());
		plantEntity.remove();
	}

	private static EntityType<? extends AbstractBowlingEntity> getEntityTypeByPlant(Plants plant) {
		if(plant == Plants.WALL_NUT) return EntityRegister.WALL_NUT_BOWLING.get();
		if(plant == Plants.EXPLODE_O_NUT) return EntityRegister.EXPLOSION_BOWLING.get();
		if (plant == Plants.GIANT_WALL_NUT) return EntityRegister.GIANT_NUT_BOWLING.get();
		return null;
	}

	public static Optional<Plants> getBowlingType(ItemStack stack) {
		int type = stack.getOrCreateTag().getInt(BOWLING_STRING);
		return getPlantTypeForBowling(type);
	}

	public static ItemStack setBowlingType(ItemStack stack, Plants plant) {
		stack.getOrCreateTag().putInt(BOWLING_STRING, getBowlingTypeForPlants(plant));
		return stack;
	}

	public static int getBowlingTypeForPlants(Plants plant) {
		if (plant == Plants.WALL_NUT) return 1;
		if (plant == Plants.EXPLODE_O_NUT) return 2;
		if (plant == Plants.GIANT_WALL_NUT) return 3;
		return -1;
	}

	public static Optional<Plants> getPlantTypeForBowling(int type) {
		Plants res = null;
		if (type == 1) res = Plants.WALL_NUT;
		else if (type == 2) res = Plants.EXPLODE_O_NUT;
		else if (type == 3) res = Plants.GIANT_WALL_NUT;
		return Optional.ofNullable(res);
	}

	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
		if (this.isInGroup(group)) {
			items.add(new ItemStack(this));
			items.add(setBowlingType(new ItemStack(this), Plants.WALL_NUT));
			items.add(setBowlingType(new ItemStack(this), Plants.EXPLODE_O_NUT));
			items.add(setBowlingType(new ItemStack(this), Plants.GIANT_WALL_NUT));
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		Optional<Plants> plant = getBowlingType(stack);
		if(plant.isPresent()) {
			Plants p = plant.get();
			tooltip.add(new TranslationTextComponent("tooltip.pvz.bowling_glove." + p.toString().toLowerCase()).applyTextStyle(TextFormatting.GOLD));
		} else {
			tooltip.add(new TranslationTextComponent("tooltip.pvz.bowling_glove.empty").applyTextStyle(TextFormatting.GOLD));
		}
	}

}

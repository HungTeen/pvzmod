package com.hungteen.pvz.common.entity.plant.defence;

import com.hungteen.pvz.common.entity.plant.base.PlantDefenderEntity;
import com.hungteen.pvz.common.item.tool.BowlingGloveItem;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class WallNutEntity extends PlantDefenderEntity{

	public WallNutEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public ActionResultType interactAt(PlayerEntity player, Vector3d vec3d, Hand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if(stack.getItem() instanceof BowlingGloveItem) {
			if(! level.isClientSide) {
				BowlingGloveItem.onPickUpBowlingPlant(this, stack);
			}
			return ActionResultType.SUCCESS;
		}
		return super.interactAt(player, vec3d, hand);
	}

	@Override
	public float getPlantHealth() {
		return MathUtil.getProgressAverage(this.getPlantLvl(), PlantUtil.MAX_PLANT_LEVEL, 400, 800);
	}
	
	@Override
	public float getSuperLife() {
		return this.isPlantInStage(1) ? 500 : this.isPlantInStage(2) ? 750 : 1000;
	}

	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.8f, 1.3f);
	}
	
	@Override
	public Plants getUpgradePlantType() {
		return Plants.GIANT_WALL_NUT;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.WALL_NUT;
	}

}

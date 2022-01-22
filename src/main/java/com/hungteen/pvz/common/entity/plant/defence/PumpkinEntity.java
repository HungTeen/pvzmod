package com.hungteen.pvz.common.entity.plant.defence;

import com.hungteen.pvz.api.paz.IPlantEntity;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.PlantInfo;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class PumpkinEntity extends PVZPlantEntity{

	public PumpkinEntity(EntityType<? extends CreatureEntity> p_i48575_1_, World p_i48575_2_) {
		super(p_i48575_1_, p_i48575_2_);
	}
	
	@Override
	public IPlantType getPlantType() {
		return PVZPlants.PUMPKIN;
	}

	@Override
	public int getSuperTimeLength() {
		return 0;
	}
	
	public static class PumpkinInfo extends PlantInfo{
		
		private static final float NORMAL_PUMPKIN_LIFE = 400;
		private static final float SUPER_PUMPKIN_LIFE = 800;

		@Override
		public void onSuper(IPlantEntity plantEntity) {
			super.onSuper(plantEntity);
			plantEntity.setPumpkin(true);
			plantEntity.setOuterDefenceLife(SUPER_PUMPKIN_LIFE);
		}
		
		@Override
		public void placeOn(IPlantEntity plantEntity, int sunCost) {
			super.placeOn(plantEntity, sunCost);
			plantEntity.setPumpkin(true);
			plantEntity.setOuterDefenceLife(NORMAL_PUMPKIN_LIFE);
		}
		
	}
	

}

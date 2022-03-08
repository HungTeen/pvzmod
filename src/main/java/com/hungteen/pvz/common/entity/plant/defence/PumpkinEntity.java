package com.hungteen.pvz.common.entity.plant.defence;

import com.hungteen.pvz.api.paz.IPlantEntity;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.PlantInfo;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import net.minecraft.world.entity.CreatureEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.level.Level;

public class PumpkinEntity extends PVZPlantEntity{

	public PumpkinEntity(EntityType<? extends CreatureEntity> p_i48575_1_, Level p_i48575_2_) {
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

		@Override
		public void onHeal(IPlantEntity plantEntity, float percent) {
			final float max = plantEntity.getOuterDefenceLife() > NORMAL_PUMPKIN_LIFE ? SUPER_PUMPKIN_LIFE : NORMAL_PUMPKIN_LIFE;
			plantEntity.setOuterDefenceLife(MathHelper.clamp(plantEntity.getOuterDefenceLife() * (1 + percent), 0, max));
		}
	}
	

}

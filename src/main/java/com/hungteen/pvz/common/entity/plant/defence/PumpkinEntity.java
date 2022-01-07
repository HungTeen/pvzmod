package com.hungteen.pvz.common.entity.plant.defence;

import com.hungteen.pvz.api.paz.IPlantEntity;
import com.hungteen.pvz.api.paz.IPlantInfo;
import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.PlantInfo;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
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
		
		protected float absorbDamage;
		protected boolean pumpkinSuper;
		
		/**
		 * pumpkin reduce the hurt damage.
		 */
		public static float pumpkinReduceDamage(PVZPlantEntity plant, DamageSource source, float amount) {
			final IPlantInfo info = plant.getOuterPlantInfo().orElseGet(() -> null);
			/* can not protect throw damage */
			if(! (info instanceof PumpkinInfo) || (source instanceof PVZDamageSource && ((PVZDamageSource) source).isParabola())) {
				return amount;
			}
			PumpkinInfo pInfo = (PumpkinInfo) info;
			if(pInfo.absorbDamage + amount <= pInfo.getMaxAbsorbDamage()) {
				pInfo.absorbDamage += amount;
				return 0;
			} else {
				amount -= pInfo.getMaxAbsorbDamage() - pInfo.absorbDamage;
				pInfo.absorbDamage = pInfo.getMaxAbsorbDamage();
				plant.removeOuterPlant();//pumpkin disappear
			}
			return amount;
		}
		
		@Override
		public void onSuper() {
			super.onSuper();
			this.absorbDamage = 0;
			this.pumpkinSuper = true;
		}
		
		@Override
		public void placeOn(IPlantEntity plantEntity, int sunCost) {
			super.placeOn(plantEntity, sunCost);
			plantEntity.setPumpkin(true);
		}
		
		@Override
		public void read(CompoundNBT nbt) {
			super.read(nbt);
			if(nbt.contains("pumpkin_life")) {
				this.absorbDamage = nbt.getFloat("pumpkin_life");
			}
			if(nbt.contains("pumpkin_super")) {
				this.pumpkinSuper = nbt.getBoolean("pumpkin_super");
			}
		}
		
		@Override
		public void write(CompoundNBT nbt) {
			super.write(nbt);
			nbt.putFloat("pumpkin_life", this.absorbDamage);
			nbt.putBoolean("pumpkin_super", this.pumpkinSuper);
		}
		
		public float getMaxAbsorbDamage() {
			return this.pumpkinSuper ? 800 : 400;
		}
		
		public float getExistHealth() {
			return this.getMaxAbsorbDamage() - this.absorbDamage;
		}
		
	}
	

}

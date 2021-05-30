package com.hungteen.pvz.common.entity.plant.defence;

import java.util.Collections;
import java.util.List;

import com.hungteen.pvz.common.entity.plant.base.PlantDefenderEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.common.misc.damage.PVZDamageType;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class GarlicEntity extends PlantDefenderEntity {

	protected final AlgorithmUtil.EntitySorter sorter;
	private GarlicEntity garlic;
	
	public GarlicEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.canBeCharmed = false;
		this.sorter = new AlgorithmUtil.EntitySorter(this);
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if(source instanceof PVZDamageSource && ((PVZDamageSource) source).getPVZDamageType() == PVZDamageType.EAT && source.getEntity() instanceof MobEntity) {
			this.updateGarlic();
			if(this.garlic != null) {
				EntityUtil.playSound(((MobEntity) source.getEntity()), SoundRegister.YUCK.get());
				((MobEntity) source.getEntity()).setTarget(this.garlic);
			}
		}
		return super.hurt(source, amount);
	}
	
	private void updateGarlic() {
		if(! EntityUtil.isEntityValid(garlic) || ! this.getSensing().canSee(garlic)) {
			this.garlic = null;
			float range = this.getChangeRange();
			List<GarlicEntity> list = level.getEntitiesOfClass(GarlicEntity.class, EntityUtil.getEntityAABB(this, range, range), (target) -> {
				return !target.is(this) && EntityUtil.isEntityValid(target) && this.getSensing().canSee(target);
			});
			if(list.isEmpty()) return ;
			Collections.sort(list, this.sorter);
			this.garlic = list.get(0);
		}
	}
	
	public float getChangeRange() {
		if(this.isPlantInStage(1)) return 10;
		if(this.isPlantInStage(2)) return 15;
		return 20;
	}
	
	@Override
	public float getPlantHealth() {
		int lvl = this.getPlantLvl();
		if(lvl <= 20) return 95 + 5 * lvl;
		return 200;
	}
	
	@Override
	public float getSuperLife() {
		return 0;
	}
	
	@Override
	public float getAttractRange() {
		return 2F;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.8F, 1.2F);
	}

	@Override
	public int getSuperTimeLength() {
		return 0;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.GARLIC;
	}

}

package com.hungteen.pvz.common.entity.plant.defence;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.entity.plant.base.PlantDefenderEntity;
import com.hungteen.pvz.common.impl.SkillTypes;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.entity.*;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.List;

public class GarlicEntity extends PlantDefenderEntity {

	protected final AlgorithmUtil.EntitySorter sorter;
	private GarlicEntity garlic;
	
	public GarlicEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.sorter = new AlgorithmUtil.EntitySorter(this);
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if(source instanceof PVZEntityDamageSource && ((PVZEntityDamageSource) source).isEatDamage() && source.getEntity() instanceof MobEntity) {
			this.updateGarlic();
			if(this.garlic != null) {
				EntityUtil.playSound(source.getEntity(), SoundRegister.YUCK.get());
				((MobEntity) source.getEntity()).setTarget(this.garlic);
			}
		}
		return super.hurt(source, amount);
	}
	
	private void updateGarlic() {
		if(! EntityUtil.isEntityValid(garlic) || ! this.getSensing().canSee(garlic)) {
			this.garlic = null;
			final float range = this.getChangeRange();
			List<GarlicEntity> list = level.getEntitiesOfClass(GarlicEntity.class, EntityUtil.getEntityAABB(this, range, range), target -> {
				return ! target.is(this) && EntityUtil.isEntityValid(target) && this.getSensing().canSee(target) && ! EntityUtil.canTargetEntity(this, target);
			});
			if(list.isEmpty()) {
				return ;
			}
			Collections.sort(list, this.sorter);
			this.garlic = list.get(0);
		}
	}
	
	public float getChangeRange() {
		return 10;
	}
	
	@Override
	public float getLife() {
		return this.getSkillValue(SkillTypes.MORE_GARLIC_LIFE);
	}
	
	@Override
	public float getSuperLife() {
		return 0;
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.8F, 1.2F);
	}
	
	@Override
	public IPlantType getPlantType() {
		return PVZPlants.GARLIC;
	}

}

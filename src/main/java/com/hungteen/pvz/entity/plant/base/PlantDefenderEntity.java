package com.hungteen.pvz.entity.plant.base;

import com.hungteen.pvz.entity.ai.DefenderAttractGoal;
import com.hungteen.pvz.entity.ai.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.interfaces.IDefender;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public abstract class PlantDefenderEntity extends PVZPlantEntity implements IDefender{

	public PlantDefenderEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new DefenderAttractGoal(this, 20));
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 2.5f, 2f) {
			@Override
			protected boolean checkPlant(Entity entity) {
				return entity instanceof MobEntity;
			}
		});
	}

	@Override
	protected void normalPlantTick() {
		super.normalPlantTick();
		if(!world.isRemote) {
			if(this.isPlantInSuperMode()) {
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(this.getLife()+this.getSuperLife());
				this.heal(this.getMaxHealth());
			}
		}
	}
	
	@Override
	public void attract() {
		for(LivingEntity target:EntityUtil.getEntityAttackableTarget(this, EntityUtil.getEntityAABB(this, 3, 3))) {
			if(target instanceof MobEntity) {
				if(!(((MobEntity) target).getAttackTarget() instanceof PlantDefenderEntity)) {
					((MobEntity) target).setAttackTarget(this);
				}
			}
		}
	}
	
	@Override
	public int getSuperTimeLength() {
		return 20;
	}

}

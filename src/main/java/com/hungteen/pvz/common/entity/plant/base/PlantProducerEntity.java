package com.hungteen.pvz.common.entity.plant.base;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.common.entity.misc.drop.SunEntity;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public abstract class PlantProducerEntity extends PVZPlantEntity {

	public PlantProducerEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.setAttackTime(200);//the first gen just need 10 seconds CD.
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new ProducerGenGoal(this));
	}

	@Override
	public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
		super.addAlmanacEntries(list);
		list.addAll(Arrays.asList(
				Pair.of(PAZAlmanacs.GEN_CD, this.getGenCD())
		));
	}

	/**
	 * sun produce plant gen sun
	 * such as sunflower or sunshroom
	 */
	protected void genSun(int num, int cnt){
		SunEntity.spawnSunsByAmount(level, this.blockPosition(), num, num / cnt, 2);
		EntityUtil.playSound(this, SoundEvents.EXPERIENCE_ORB_PICKUP);
	}
	
	/**
	 * produce something ,like sunflower produce sun.
	 * {@link ProducerGenGoal#tick()}
	 */
	protected abstract void genSomething();
	
	/**
	 * super produce.
	 * {@link ProducerGenGoal#tick()}
	 */
	protected abstract void genSuper();
	
	/**
	 * get next produce CD.
	 * {@link ProducerGenGoal#tick()}
	 */
	public abstract int getGenCD();
	
	public int getAnimGenCD() {
		return 20;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 30;
	}
	
	/**
	 * is producer going to gen, use for render sunflower sun layer.
	 */
	public boolean isPlantInGen() {
		return this.getAttackTime() <= 10 || (this.isPlantInSuperMode() && this.getSuperTime() < 10);
	}
	
	static class ProducerGenGoal extends Goal{

		private final PlantProducerEntity producer;
		
		public ProducerGenGoal(PlantProducerEntity entity) {
			this.producer = entity;
		}
		
		@Override
		public boolean canUse() {
			return true;
		}

		@Override
		public boolean canContinueToUse() {
			return true;
		}
		
		@Override
		public void stop() {
		}
		
		@Override
		public void tick() {
			if(! this.producer.canNormalUpdate()) {
				return ;
			}
			if(this.producer.isPlantInSuperMode()){//Be Super Mode
				if(this.producer.getSuperTime() == 1) {
				    this.producer.genSuper();
				}
				return ;
	        }
			final int time = this.producer.getAttackTime();
			if(time <= 1) {
				this.producer.genSomething();
				this.producer.setAttackTime(this.producer.getGenCD());
			} else {
				this.producer.setAttackTime(Math.max(0, time - 1));
			}
		}
	}
	
}

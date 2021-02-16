package com.hungteen.pvz.entity.creature;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.hungteen.pvz.utils.AlgorithmUtil;
import com.hungteen.pvz.utils.AlgorithmUtil.EntitySorter;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.world.World;

public class SnailEntity extends AnimalEntity {

	protected Optional<Entity> targetEntity = Optional.empty();
	
	protected SnailEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(3, new SnailNearestTargetGoal(this, 15));
	}

	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		return null;
	}
	
	public final class SnailNearestTargetGoal extends Goal {

		private final SnailEntity owner;
		private final World world;
		private final float range;
		private final int targetChance = 10;
		private final EntitySorter sorter;
		
		public SnailNearestTargetGoal(SnailEntity snail, float range) {
			this.owner = snail;
			this.range = range;
			this.world = this.owner.world; 
			this.sorter = new AlgorithmUtil.EntitySorter(this.owner);
		}
		
		@Override
		public boolean shouldExecute() {
			if(this.owner.getRNG().nextInt(this.targetChance) != 0) return false;
			List<Entity> list = this.world.getEntitiesWithinAABB(Entity.class, EntityUtil.getEntityAABB(this.owner, this.range, this.range), (entity) -> {
				return true;
			});
			if(list.isEmpty()) return false;
			Collections.sort(list, this.sorter);
			this.owner.targetEntity = Optional.of(list.get(0));
			return true;
		}
		
	}

}

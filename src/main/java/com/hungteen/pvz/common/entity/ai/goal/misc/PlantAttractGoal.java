package com.hungteen.pvz.common.entity.ai.goal.misc;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.interfaces.ICanAttract;

import net.minecraft.entity.ai.goal.Goal;

public class PlantAttractGoal extends Goal{

	private final ICanAttract attracter;
	private final PVZPlantEntity plantEntity;
	private final int attractCD;
	private int attractTick;
	
	public PlantAttractGoal(PVZPlantEntity plantEntity, ICanAttract attracter, int cd) {
		this.attracter = attracter;
		this.plantEntity = plantEntity;
		this.attractCD = cd;
	}
	
	@Override
	public boolean canUse() {
		return this.plantEntity.canNormalUpdate();
	}
	
	@Override
	public boolean canContinueToUse() {
		return this.canUse();
	}
	
	@Override
	public void tick() {
		++ attractTick;
		if(attractTick >= attractCD) {
			this.attract();
			this.attractTick = 0;
		}
	}
	
	protected void attract() {
		float range = this.attracter.getAttractRange();
		EntityUtil.getTargetableLivings(this.plantEntity, EntityUtil.getEntityAABB(this.plantEntity, range, range))
		.stream().filter(target -> this.attracter.canAttract(target)).forEach(target -> {
			this.attracter.attract(target);
		});
	}

}

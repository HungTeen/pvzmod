package com.hungteen.pvz.common.entity.plant.base;

import com.hungteen.pvz.api.interfaces.IAlmanacEntry;
import com.hungteen.pvz.api.interfaces.ICanBeAttracted;
import com.hungteen.pvz.common.entity.ai.goal.misc.PlantAttractGoal;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.enums.PAZAlmanacs;
import com.hungteen.pvz.utils.interfaces.ICanAttract;
import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;

public abstract class PlantDefenderEntity extends PVZPlantEntity implements ICanAttract {

	public PlantDefenderEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(0, new PlantAttractGoal(this, this, 20));
	}

	@Override
	public void addAlmanacEntries(List<Pair<IAlmanacEntry, Number>> list) {
		super.addAlmanacEntries(list);
		list.addAll(Arrays.asList(
				Pair.of(PAZAlmanacs.ARMOR, this.getArmor()),
				Pair.of(PAZAlmanacs.ARMOR_TOUGHNESS, this.getArmorToughness())
		));
	}

	@Override
	public void startSuperMode(boolean first) {
		super.startSuperMode(first);
		this.setInnerDefenceLife(this.getSuperLife());
	}
	
	@Override
	public boolean canAttract(LivingEntity entity) {
		if(entity instanceof ICanBeAttracted && ! ((ICanBeAttracted) entity).canBeAttractedBy(this)) {
			return false;
		}
		if(! this.getSensing().canSee(entity)) {
			return false;
		}
		return true;
	}
	
	@Override
	public void attract(LivingEntity target) {
		if(target instanceof MobEntity && (! (((MobEntity) target).getTarget() instanceof ICanAttract))) {
			((MobEntity) target).setTarget(this);
		}
		if(target instanceof ICanBeAttracted) {
			((ICanBeAttracted) target).attractBy(this);
		}
	}
	
	@Override
	public float getAttractRange() {
		return 2.5F;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 20;
	}
	
	/**
	 * for extra life.
	 */
	public abstract float getSuperLife();
	
}

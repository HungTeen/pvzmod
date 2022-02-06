package com.hungteen.pvz.common.entity.zombie.roof;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantDefenderEntity;
import com.hungteen.pvz.common.entity.zombie.base.DefenceZombieEntity;
import com.hungteen.pvz.common.entity.zombie.body.ZombieDropBodyEntity;
import com.hungteen.pvz.common.entity.zombie.part.PVZHealthPartEntity;
import com.hungteen.pvz.common.impl.zombie.RoofZombies;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.remove.MetalTypes;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.interfaces.IHasMetal;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class LadderZombieEntity extends DefenceZombieEntity implements IHasMetal {
	
	public LadderZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void resetParts() {
		removeParts();
		this.part = new PVZHealthPartEntity(this, 1f, 1.7f);
		this.part.setOwner(this);
	}
	
	@Override
	public boolean doHurtTarget(Entity entityIn) {
		if(this.hasMetal() && canTargetPutLadder(entityIn)) {
			this.putLadderOn(entityIn);
		}
		return super.doHurtTarget(entityIn);
	}
	
	/**
	 * {@link #doHurtTarget(Entity)}
	 */
	public void putLadderOn(Entity entity) {
		if(entity instanceof PVZPlantEntity) {
			((PVZPlantEntity) entity).increaseMetal();
		}
		this.decreaseMetal();
	}
	
	public static boolean canTargetPutLadder(Entity target) {
		//can not put ladder or already has ladder on.
		if(! (target instanceof PVZPlantEntity) || hasLadderOnEntity(target)) {
			return false;
		}
		if(target instanceof PlantDefenderEntity) {
			return true;
		}
		PVZPlantEntity plant = (PVZPlantEntity) target;
		return plant.getOuterPlantInfo().isPresent();
	}
	
	/**
	 * {@link #canTargetPutLadder(Entity)}
	 */
	private static boolean hasLadderOnEntity(Entity target) {
		if(! (target instanceof PVZPlantEntity)) {
			return false;
		}
		return ((PVZPlantEntity) target).hasMetal();
	}
	
	@Override
	public boolean hasMetal() {
		return this.getOuterDefenceLife() > 0;
	}
	
	@Override
	public boolean canLostHand() {
		return super.canLostHand() && ! this.hasMetal();
	}
	
	@Override
	protected void setBodyStates(ZombieDropBodyEntity body) {
		super.setBodyStates(body);
		body.setHandDefence(this.hasMetal());
	}

	@Override
	public void decreaseMetal() {
		this.setOuterDefenceLife(0);
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_NORMAL);
	}

	@Override
	public void increaseMetal() {
		this.setOuterDefenceLife(this.getOuterLife());
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_FAST);
		this.resetParts();
	}

	@Override
	public MetalTypes getMetalType() {
		return MetalTypes.LADDER;
	}
	
	@Override
	public SoundEvent getPartHurtSound() {
		return SoundRegister.METAL_HIT.get();
	}
	
	@Override
	public float getLife() {
		return 65;
	}
	
	@Override
	public float getOuterLife() {
		return 250;
	}

	@Override
	public float getWalkSpeed() {
		return ZombieUtil.WALK_FAST;
	}

	@Override
    public ZombieType getZombieType() {
	    return RoofZombies.LADDER_ZOMBIE;
    }

}

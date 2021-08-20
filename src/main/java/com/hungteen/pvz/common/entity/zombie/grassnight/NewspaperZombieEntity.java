package com.hungteen.pvz.common.entity.zombie.grassnight;

import com.hungteen.pvz.common.entity.zombie.base.DefenceZombieEntity;
import com.hungteen.pvz.common.entity.zombie.body.ZombieDropBodyEntity;
import com.hungteen.pvz.common.entity.zombie.part.PVZHealthPartEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.remove.Zombies;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class NewspaperZombieEntity extends DefenceZombieEntity {

	public NewspaperZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.updateAngry(false);
	}
	
	@Override
	public void resetParts() {
		removeParts();
		this.part = new PVZHealthPartEntity(this, 1f, 1f);
		this.part.setOwner(this);
	}
	
	@Override
	protected float getPartHeightOffset() {
		if(this.isMiniZombie()) return 0.2F;
		return 0.7f;
	}
	
	@Override
	public void onSyncedDataUpdated(DataParameter<?> data) {
		super.onSyncedDataUpdated(data);
		if(data.equals(DEFENCE_LIFE)) {
			this.updateAngry(this.isAngry());
			if(this.isAngry()) {
				EntityUtil.playSound(this, SoundRegister.ANGRY.get());
			}
		}
	}
	
	protected void updateAngry(boolean is) {
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(is ? ZombieUtil.WALK_LITTLE_FAST : ZombieUtil.WALK_LITTLE_SLOW);
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(is ? ZombieUtil.LOW : ZombieUtil.LITTLE_LOW);
	}
	
	@Override
	public boolean canLostHand() {
		return super.canLostHand() && this.isAngry();
	}
	
	@Override
	protected void setBodyStates(ZombieDropBodyEntity body) {
		super.setBodyStates(body);
		body.setHandDefence(! this.isAngry());
	}
	
	@Override
	public SoundEvent getPartDeathSound() {
		return SoundRegister.PAPER_GONE.get();
	}
	
	@Override
	public float getLife() {
		return 22;
	}
	
	@Override
	public float getPartLife() {
		return 10;
	}
	
	public boolean isAngry() {
		return this.getDefenceLife() == 0;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.NEWSPAPER_ZOMBIE;
	}

}

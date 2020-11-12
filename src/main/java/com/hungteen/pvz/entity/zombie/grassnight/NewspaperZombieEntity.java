package com.hungteen.pvz.entity.zombie.grassnight;

import com.hungteen.pvz.entity.zombie.base.DefenceZombieEntity;
import com.hungteen.pvz.entity.zombie.part.PVZHealthPartEntity;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public class NewspaperZombieEntity extends DefenceZombieEntity {

	private static final float DEFENCE_HEALTH = 10;
	protected boolean isAngry = false;
	
	public NewspaperZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.setDefenceLife(DEFENCE_HEALTH);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.updateAngry(false);
	}
	
	@Override
	public void resetParts() {
		removeParts();
		this.part = new PVZHealthPartEntity(this, 1f, 1.5f);
		this.part.setOwner(this);
	}
	
	@Override
	public void tick() {
		super.tick();
		if(!world.isRemote) {
			if(this.canPartsBeRemoved() && !this.isAngry) {
				this.playSound(SoundRegister.ANGRY.get(), 1f, 1f);
				this.updateAngry(true);
			}
		}
	}
	
	protected void updateAngry(boolean is) {
		this.isAngry = is;
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(is ? ZombieUtil.LITTLE_FAST : ZombieUtil.LITTLE_SLOW);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(is ? ZombieUtil.LOW : ZombieUtil.LITTLE_LOW);
	}
	
	@Override
	public float getLife() {
		return 30;
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.isAngry = compound.getBoolean("is_zombie_angry");
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("is_zombie_angry", this.isAngry);
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.NEWSPAPER_ZOMBIE;
	}

}

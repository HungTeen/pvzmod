package com.hungteen.pvz.entity.zombie.zombotany;

import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.register.EffectRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class PeaShooterZombieEntity extends AbstractZombotanyEntity {

	private int shootTick = 0;
	
	public PeaShooterZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(! level.isClientSide) {
			++ shootTick;
			if(this.shootTick >= this.getFixedShootCD()) {
				this.setAttackTime(this.getShootNum());
				this.shootTick = 0;
			}
			if(this.getAttackTime() > 0) {
				this.setAttackTime(this.getAttackTime() - 1);
				this.shootPea();
			}
		}
	}
	
	public float getAttackDamage() {
		return 3;
	}
	
	private void shootPea() {
		LivingEntity target = this.getTarget();
		if(target == null) return ;
		PeaEntity pea = new PeaEntity(level, this, PeaEntity.Type.NORMAL, PeaEntity.State.NORMAL);
		pea.setPos(getX(), getY() + this.getEyeHeight(), getZ());
		pea.shootToTarget(target, 1.5F);
		level.addFreshEntity(pea);
		EntityUtil.playSound(this, SoundEvents.SNOW_GOLEM_SHOOT);
	}
	
	@Override
	public float getLife() {
		return 20;
	}

	protected int getFixedShootCD() {
		int now = this.getShootCD();
		if (this.hasEffect(EffectRegister.COLD_EFFECT.get())) {
			int lvl = this.getEffect(EffectRegister.COLD_EFFECT.get()).getAmplifier();
			now += 3 * lvl;
		}
		return now;
	}
	
	protected int getShootCD() {
		return 30;
	}
	
	protected int getShootNum() {
		return 1;
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("shoot_tick")) {
			this.shootTick = compound.getInt("shoot_tick");
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("shoot_tick", this.shootTick);
	}
	
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return PVZLoot.PEASHOOTER_ZOMBIE;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.PEASHOOTER_ZOMBIE;
	}

}

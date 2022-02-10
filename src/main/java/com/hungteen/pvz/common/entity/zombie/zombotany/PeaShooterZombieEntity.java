package com.hungteen.pvz.common.entity.zombie.zombotany;

import com.hungteen.pvz.common.entity.bullet.itembullet.PeaEntity;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.impl.zombie.Zombotanies;
import com.hungteen.pvz.common.potion.EffectRegister;
import com.hungteen.pvz.common.misc.PVZLoot;
import com.hungteen.pvz.utils.EntityUtil;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class PeaShooterZombieEntity extends AbstractZombotanyEntity {

	private int shootTick = 0;
	
	public PeaShooterZombieEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
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
	
	private void shootPea() {
		LivingEntity target = this.getTarget();
		if(target == null) {
			return ;
		}
		PeaEntity pea = new PeaEntity(level, this, PeaEntity.Type.NORMAL, PeaEntity.State.NORMAL);
		pea.setPos(getX(), getY() + this.getEyeHeight(), getZ());
		pea.shootToTarget(target, 1.5F);
		pea.summonByOwner(this);
		pea.setAttackDamage(this.getAttackDamage());
		level.addFreshEntity(pea);
		EntityUtil.playSound(this, SoundEvents.SNOW_GOLEM_SHOOT);
	}
	
	public float getAttackDamage() {
		return 2;
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
	public ZombieType getZombieType() {
		return Zombotanies.PEASHOOTER_ZOMBIE;
	}

}

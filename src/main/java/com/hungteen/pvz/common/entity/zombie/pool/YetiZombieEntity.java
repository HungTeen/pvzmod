package com.hungteen.pvz.common.entity.zombie.pool;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.core.ZombieType;
import com.hungteen.pvz.common.entity.drop.JewelEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.impl.zombie.PoolZombies;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class YetiZombieEntity extends PVZZombieEntity{

	private final int DROP_JEWEL_NUM = 4;
	private final double [] DD = new double[]{- 0.5D, 0.5D, 0.5D, -0.5D};
	private int live_tick = 0;
	private boolean hasInvis = false;
	
	public YetiZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.canBeFrozen = false;
	}

	@Override
	public float getLife() {
		return 135;
	}
	
	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.LITTLE_LOW);
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_LITTLE_FAST);
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(! level.isClientSide) {
			++ this.live_tick;
			if(this.live_tick > this.getYetiMaxLiveTick() / 2) {
				if(! this.hasInvis) {
				    this.hasInvis = true;
				    this.addEffect(new EffectInstance(Effects.INVISIBILITY, 2000, 2, false, true));
				}
				this.heal(0.5f);
			} else if(this.live_tick >= this.getYetiMaxLiveTick()) {
				this.remove();
			}
		}
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		if(this.isMiniZombie()) return EntitySize.scalable(0.6F, 1.2F);
		return EntitySize.scalable(1f, 2.6f);
	}
	
	@Override
	public boolean hurt(DamageSource source, float amount) {
		if(amount >= 100) {
			amount /= 10;
		}
		return super.hurt(source, amount);
	}
	
	@Override
	protected void spawnSpecialDrops() {
		for(int i = 0; i < this.DROP_JEWEL_NUM; ++ i) {
			JewelEntity jewel = EntityRegister.JEWEL.get().create(level);
		    EntityUtil.onEntitySpawn(level, jewel, blockPosition().offset(this.DD[i], getRandom().nextDouble(), this.DD[(i + 1) % this.DROP_JEWEL_NUM]));
		}
	}

	public int getYetiMaxLiveTick() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.EntityLiveTick.YetiLiveTick.get();
	}
	
	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("yeti_live_tick", this.live_tick);
		compound.putBoolean("yeti_invis", this.hasInvis);
	}
	
	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if(compound.contains("yeti_live_tick")) {
			this.live_tick = compound.getInt("yeti_live_tick");
		}
		if(compound.contains("yeti_invis")) {
			this.hasInvis = compound.getBoolean("yeti_invis");
		}
	}
	
	@Override
    public ZombieType getZombieType() {
	    return PoolZombies.YETI_ZOMBIE;
    }

}

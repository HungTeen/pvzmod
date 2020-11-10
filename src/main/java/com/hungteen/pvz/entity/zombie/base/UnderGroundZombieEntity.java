package com.hungteen.pvz.entity.zombie.base;

import java.util.Random;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.register.SoundRegister;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public abstract class UnderGroundZombieEntity extends PVZZombieEntity{

	protected boolean isRiseType = true;
	protected int particleNum = 1;
	
	public UnderGroundZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if(!world.isRemote) {
			if(this.isRiseType) {
				this.playSound(SoundRegister.DIRT_RISE.get(), 1f, 1f);
				this.setAttackTime(- this.getSpawnTime());
				this.addPotionEffect(new EffectInstance(Effects.SLOWNESS, this.getSpawnTime(), 20, false, false));
			}
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	public void setRiseType(boolean is) {
		this.isRiseType = is;
	}
	
	@Override
	public void tick() {
		super.tick();
		if(this.getAttackTime() < 0) {
			this.setAttackTime(this.getAttackTime() + 1);
			if(world.isRemote) {
				for(int i = 0;i < this.particleNum;i ++) {
					Random rand=this.getRNG();
					this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), this.getPosX()+0.5d, this.getPosY(), this.getPosZ()+0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
					this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), this.getPosX()+0.5d, this.getPosY(), this.getPosZ()-0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
					this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), this.getPosX()-0.5d, this.getPosY(), this.getPosZ()+0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
					this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), this.getPosX()-0.5d, this.getPosY(), this.getPosZ()-0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
				}
			}
		}
	}
	
	public int getSpawnTime() {
		return 20;
	}
	
}

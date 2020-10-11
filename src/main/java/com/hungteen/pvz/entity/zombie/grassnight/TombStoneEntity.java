package com.hungteen.pvz.entity.zombie.grassnight;

import java.util.Random;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class TombStoneEntity extends PVZZombieEntity{

	public int spawnTick = 0;
	public static final int SPAWN_TIME = 20;
	
	public TombStoneEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0);
	}
	
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if(!world.isRemote) {
			this.playSound(SoundRegister.DIRT_RISE.get(), 1f, 1f);
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	public void livingTick() {
		super.livingTick();
		if(this.spawnTick < SPAWN_TIME) {
			this.spawnTick ++;
			if(world.isRemote) {
				for(int i = 0;i < 5;i ++) {
					Random rand=this.getRNG();
					this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), this.getPosX()+0.5d, this.getPosY(), this.getPosZ()+0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
					this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), this.getPosX()+0.5d, this.getPosY(), this.getPosZ()-0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
					this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), this.getPosX()-0.5d, this.getPosY(), this.getPosZ()+0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
					this.world.addParticle(ParticleRegister.DIRT_BURST_OUT.get(), this.getPosX()-0.5d, this.getPosY(), this.getPosZ()-0.5d, (rand.nextFloat()-0.5)/10,0.05d,(rand.nextFloat()-0.5)/10);
				}
			}
		}
	}
	
	@Override
	protected void registerGoals() {
		//no goal
	}
	
	@Override
	protected Type getSpawnType() {
		return Type.NORMAL;
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.8f, 1.6f);
	}
	
	@Override
	public float getLife() {
		return 70;
	}

	@Override
	public double getMountedYOffset() {
		return 0;
	}
	
	@Override
	public boolean canBeButter() {
		return false;
	}
	
	@Override
	public boolean canBeCold() {
		return false;
	}
	
	@Override
	public boolean canBeFrozen() {
		return false;
	}
	
	@Override
	public boolean canBeInvis() {
		return false;
	}
	
	@Override
	public boolean canBeSmall() {
		return false;
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}
	
	@Override
	protected ResourceLocation getLootTable() {
		return null;
	}
	
	@Override
	public Zombies getZombieEnumName() {
		return Zombies.TOMB_STONE;
	}

}

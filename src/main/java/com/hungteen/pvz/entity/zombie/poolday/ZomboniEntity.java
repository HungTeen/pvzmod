package com.hungteen.pvz.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.entity.zombie.ZomboniPartEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class ZomboniEntity extends PVZZombieEntity{
	
	private ZomboniPartEntity part;
	
	public ZomboniEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		resetParts();
	}
	
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.LITTLE_SLOW);
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(world.isRemote&&this.getHealth()<=this.getMaxHealth()/4) {
			for(int i=1;i<=3;i++) {
			    this.world.addParticle(ParticleTypes.SMOKE, this.getPosX(), this.getPosY(), this.getPosZ(), (this.getRNG().nextFloat()-0.5)/10, 0.05, (this.getRNG().nextFloat()-0.5) / 10);
			}
		}
		if(!world.isRemote) {//produce snow layer block
			BlockPos blockpos = this.getPosition();
			BlockState state = Blocks.SNOW.getDefaultState();
            if (this.world.isAirBlock(blockpos) && state.isValidPosition(this.world, blockpos)) {
               this.world.setBlockState(blockpos, state);
            }
		}
	}
	
	@Override
	public void tick() {
		super.tick();
		updateParts();
	}
	
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if(!world.isRemote) {
			this.playSound(SoundRegister.CAR_SPAWN.get(), 1f, 1f);
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	private void resetParts() {
		removeParts();
		part = new ZomboniPartEntity(this, 1.2f, 1.5f);
		part.setOwner(this);
	}
	
	private void removeParts() {
		if(part != null) {
			part.remove();
			part = null;
		}
	}
	
	private void updateParts() {
		if(part != null) {
			if(!part.shouldContinuePersisting()) {
				world.addEntity(part);
			}
			part.setOwner(this);
		}
	}
	
	@Override
	protected void onDeathUpdate() {
		super.onDeathUpdate();
		if(this.deathTime==1) {
			if(!world.isRemote) {
//				float tmp = 4f;
			    this.playSound(SoundRegister.CAR_EXPLOSION.get(), 1f, 1f);
//			    this.setMotion((getRNG().nextFloat()-0.5)/tmp, getRNG().nextFloat()/tmp, (getRNG().nextFloat()-0.5)/tmp);
			}
			else {
				for(int i=0;i<4;i++) {
				    this.world.addParticle(ParticleTypes.EXPLOSION, this.getPosX(), this.getPosY(), this.getPosZ(), 0, 0, 0);
				}
			}
		}
	}
	
	@Override
	public void remove() {
		removeParts();
		super.remove();
	}
	
	@Override
	protected PVZDamageSource getZombieAttackDamageSource() {
		return PVZDamageSource.causeCrushDamage(this, this);
	}
	
	public float getPartOffset() {
		return 1.4f;
	}
	
	@Override
	protected float getModifyAttackDamage(Entity entity, float f) {
		if(entity instanceof LivingEntity) {
			return ((LivingEntity) entity).getMaxHealth();
		}
		return f;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundRegister.METAL_HIT.get();
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(1f, 2f);
	}
	
	@Override
	public float getLife() {
		return 120;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.ZOMBONI;
	}

}

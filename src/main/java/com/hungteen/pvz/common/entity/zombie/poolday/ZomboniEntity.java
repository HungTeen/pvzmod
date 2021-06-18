package com.hungteen.pvz.common.entity.zombie.poolday;

import java.util.Optional;

import com.hungteen.pvz.common.entity.PVZMultiPartEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.body.ZombieDropBodyEntity;
import com.hungteen.pvz.common.entity.zombie.body.ZombieDropBodyEntity.BodyType;
import com.hungteen.pvz.common.entity.zombie.part.PVZZombiePartEntity;
import com.hungteen.pvz.common.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Zombies;
import com.hungteen.pvz.utils.interfaces.IMultiPartEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class ZomboniEntity extends PVZZombieEntity implements IMultiPartEntity{
	
	private PVZZombiePartEntity part;
	
	public ZomboniEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.setImmuneAllEffects();
		this.setIsWholeBody();
		this.resetParts();
		this.maxDeathTime = 1;
	}
	
	@Override
	protected void updateAttributes() {
		super.updateAttributes();
		this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.WALK_LITTLE_SLOW);
	}
	
	@Override
	public void zombieTick() {
		super.zombieTick();
		if(level.isClientSide && this.isZomboniShaking()) {
			for(int i = 1; i <= 3; i ++) {
			    this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), (this.getRandom().nextFloat() - 0.5) / 10, 0.05, (this.getRandom().nextFloat() - 0.5) / 10);
			}
		}
		if(! level.isClientSide) {
			FrostWalkerEnchantment.onEntityMoved(this, level, this.blockPosition(), 1);
			BlockPos blockpos = this.blockPosition();
			BlockState state = Blocks.SNOW.defaultBlockState().setValue(SnowBlock.LAYERS, 2);
            if ((this.level.isEmptyBlock(blockpos) || level.getBlockState(blockpos).getBlock() == Blocks.SNOW) && state.canSurvive(this.level, blockpos)) {
               this.level.setBlockAndUpdate(blockpos, state);
            }
		}
	}
	
	@Override
	public void normalZombieTick() {
		super.normalZombieTick();
		if(! level.isClientSide) {//produce snow layer block
			
		}
	}
	
	@Override
	protected void onFallBody(DamageSource source) {
		ZombieDropBodyEntity body = EntityRegister.ZOMBIE_DROP_BODY.get().create(level);
		body.specialDropBody(this, source, BodyType.HEAD);
		this.setBodyStates(body);
		level.addFreshEntity(body);
	}
	
	public boolean isZomboniShaking() {
		return this.getHealth() <= this.getMaxHealth() / 4;
	}
	
	@Override
	public void tick() {
		super.tick();
		updateParts();
	}
	
	@Override
	public void resetParts() {
		removeParts();
		this.part = new PVZZombiePartEntity(this, 1.2f, 1.5f);
		this.part.setOwner(this);
	}
	
	@Override
	public void removeParts() {
		if(this.part != null) {
			this.part.remove();
			this.part = null;
		}
	}
	
	@Override
	public void updateParts() {
		if(this.part != null) {
			if(! this.part.isAddedToWorld()) {
				this.level.addFreshEntity(this.part);
			}
			float j = 2 * 3.14159f * this.yRot / 360;
			float dis = this.getPartOffset();
			Vector3d pos = this.position();
			this.part.yRotO = this.yRot;
			this.part.xRotO = this.xRot;
			this.part.moveTo(pos.x() - Math.sin(j) * dis, pos.y() + 0.2f, pos.z() + Math.cos(j) * dis, this.yRot, this.xRot);
			this.part.setOwner(this);
		}
	}
	
	public PVZMultiPartEntity[] getMultiParts() {
		return new PVZMultiPartEntity[] {this.part};
	}
	
	@Override
	protected void onZombieRemove() {
		if(! level.isClientSide) {
			EntityUtil.playSound(this, SoundRegister.CAR_EXPLOSION.get());
		}
		else {
			for(int i = 0; i < 4; ++ i) {
			    this.level.addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
			}
		}
		super.onZombieRemove();
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
		if(this.isMiniZombie()) return 0.4F;
		return 1.2f;
	}
	
	@Override
	protected float getModifyAttackDamage(Entity entity, float f) {
		if(entity instanceof LivingEntity) {
			return EntityUtil.getMaxHealthDamage(((LivingEntity) entity));
		}
		return f;
	}
	
	@Override
	public SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundRegister.METAL_HIT.get();
	}
	
	@Override
	protected Optional<SoundEvent> getSpawnSound() {
		return Optional.ofNullable(SoundRegister.CAR_SPAWN.get());
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		if(this.isMiniZombie()) return EntitySize.scalable(0.7F, 1.2F);
		return EntitySize.scalable(0.8f, 2.3f);
	}
	
	@Override
	public float getLife() {
		return 130;
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.ZOMBONI;
	}

}

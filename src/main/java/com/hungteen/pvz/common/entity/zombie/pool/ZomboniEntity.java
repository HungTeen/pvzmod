package com.hungteen.pvz.common.entity.zombie.pool;

import com.hungteen.pvz.api.interfaces.IHasWheel;
import com.hungteen.pvz.common.entity.PVZMultiPartEntity;
import com.hungteen.pvz.common.entity.zombie.base.CarZombieEntity;
import com.hungteen.pvz.common.entity.zombie.part.PVZZombiePartEntity;
import com.hungteen.pvz.common.impl.zombie.PoolZombies;
import com.hungteen.pvz.common.impl.zombie.ZombieType;
import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.interfaces.IHasMultiPart;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowBlock;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.*;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.Optional;

public class ZomboniEntity extends CarZombieEntity implements IHasMultiPart, IHasWheel{
	
	private PVZZombiePartEntity part;
	
	public ZomboniEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.setImmuneAllEffects();
		this.resetParts();
	}
	
	@Override
	public void zombieTick() {
		super.zombieTick();
		if(! level.isClientSide) {
			FrostWalkerEnchantment.onEntityMoved(this, level, this.blockPosition(), 1);
			BlockPos blockpos = this.blockPosition();
			BlockState state = Blocks.SNOW.defaultBlockState().setValue(SnowBlock.LAYERS, 1);
            if ((this.level.isEmptyBlock(blockpos) || level.getBlockState(blockpos).getBlock() == Blocks.SNOW) && state.canSurvive(this.level, blockpos)) {
               this.level.setBlockAndUpdate(blockpos, state);
            }
		}
	}
	
	@Override
	public void tick() {
		super.tick();
		updateParts();
	}
	
	@Override
	public void spikeWheelBy(LivingEntity entity) {
		this.hurt(PVZEntityDamageSource.thorns(entity), EntityUtil.getMaxHealthDamage(this, 2));
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
	public void remove() {
		removeParts();
		super.remove();
	}
	
	public float getPartOffset() {
		return 1.2f;
	}

	@Override
	public float getWalkSpeed() {
		return ZombieUtil.WALK_SLOW;
	}

	@Override
	public float getLife() {
		return 130;
	}

	@Override
	public int getArmorToughness() {
		return 12;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundRegister.METAL_HIT.get();
	}
	
	@Override
	public Optional<SoundEvent> getSpawnSound() {
		return Optional.ofNullable(SoundRegister.CAR.get());
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.8f, 2.3f);
	}

	@Override
	public ZombieType getZombieType() {
		return PoolZombies.ZOMBONI;
	}

}

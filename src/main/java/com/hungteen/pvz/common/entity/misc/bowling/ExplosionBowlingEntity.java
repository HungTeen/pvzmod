package com.hungteen.pvz.common.entity.misc.bowling;

import com.hungteen.pvz.common.misc.PVZEntityDamageSource;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.client.particle.ParticleRegister;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class ExplosionBowlingEntity extends AbstractBowlingEntity {

	public ExplosionBowlingEntity(EntityType<?> type, World worldIn) {
		super(type, worldIn);
	}

	public ExplosionBowlingEntity(EntityType<?> type, World worldIn, PlayerEntity entity) {
		super(type, worldIn, entity);
	}
	
	@Override
	protected void tickCollision() {
		if(this.getBowlingFacing() == BowlingFacings.BOMB) {
			this.bomb();
			this.remove();
		} else {
			super.tickCollision();
		}
	}
	
	private void bomb() {
		if(! level.isClientSide) {
		} else {
			this.level.addParticle(ParticleRegister.RED_BOMB.get(), this.getX(), this.getY(), this.getZ(), 0, 0, 0);
		}
	}
	
	@Override
	protected void changeDiretion() {
		float len = 2.5F;
		AxisAlignedBB aabb = EntityUtil.getEntityAABB(this, len, len);
		EntityUtil.getTargetableEntities(this.getOwnerOrSelf(), aabb).forEach((target) -> {
			target.hurt(PVZEntityDamageSource.explode(this), 180);
		});
		EntityUtil.playSound(this, SoundRegister.CHERRY_BOMB.get());
		this.setBowlingFacing(BowlingFacings.BOMB);
	}

	@Override
	protected void dealDamageTo(Entity entity) {
		
	}

}

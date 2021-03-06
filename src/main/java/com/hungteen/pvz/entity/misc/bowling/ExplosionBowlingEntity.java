package com.hungteen.pvz.entity.misc.bowling;

import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.register.SoundRegister;
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
		EntityUtil.getAttackEntities(this, aabb).forEach((target) -> {
			target.hurt(PVZDamageSource.causeExplosionDamage(this, this), 180);
		});
		EntityUtil.playSound(this, SoundRegister.BOWLING_BOMB.get());
		this.setBowlingFacing(BowlingFacings.BOMB);
	}

	@Override
	protected void dealDamageTo(Entity entity) {
		
	}

}

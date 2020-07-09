package com.hungteen.pvzmod.entities.zombies.plantzombies;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.SoundsHandler;
import com.hungteen.pvzmod.util.enums.Zombies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityJalapenoZombie extends EntityZombieBase {

	public EntityJalapenoZombie(World worldIn) {
		super(worldIn);
		this.setSize(0.8f, 2.3f);
	}

	@Override
	protected void setSmallSize() {
		this.setSize(0.3f, 0.5f);
	}
	
	@Override
	public float getLife() {
		return 35;
	}

	@Override
	public void onDeath(DamageSource cause) {
		if (!this.getIsCold() && !this.getIsFrozen()) {
			this.startBoom();
		}
		super.onDeath(cause);
	}

	protected void startBoom() {
		int len = 20 + this.getRNG().nextInt(11);
		BlockPos pos = new BlockPos(this.posX, this.posY, this.posZ);
		for (int i = pos.getZ() - len; i <= pos.getZ() + len; i++) {
			for (int j = 1; j <= 30; j++) {
				this.world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5, pos.getY(), i + 0.5,
						(this.getRNG().nextFloat() - 0.5) / 10, this.getRNG().nextFloat() / 8,
						(this.getRNG().nextFloat() - 0.5) / 10);
			}
		}
		for (int i = pos.getX() - len; i <= pos.getX() + len; i++) {
			for (int j = 1; j <= 30; j++) {
				this.world.spawnParticle(EnumParticleTypes.FLAME, i + 0.5, pos.getY(), pos.getZ() + 0.5,
						(this.getRNG().nextFloat() - 0.5) / 10, this.getRNG().nextFloat() / 8,
						(this.getRNG().nextFloat() - 0.5) / 10);
			}
		}
		fireMob(EnumFacing.NORTH, len);
		fireMob(EnumFacing.EAST, len);
	}

	private void fireMob(EnumFacing facing, int len) {
		AxisAlignedBB aabb = null;
		if (facing == EnumFacing.NORTH || facing == EnumFacing.SOUTH) {
			aabb = new AxisAlignedBB(this.posX - 0.5, this.posY, this.posZ - len, this.posX + 0.5, this.posY + 2,
					this.posZ + len);
		} else {
			aabb = new AxisAlignedBB(this.posX - len, this.posY, this.posZ - 0.5, this.posX + len, this.posY + 2,
					this.posZ + 0.5);
		}
		if (!this.world.isRemote) {
			for (Entity entity : this.world.getEntitiesWithinAABB(EntityLivingBase.class, aabb)) {
				if(EntityUtil.checkCanEntityAttack(this, entity)&&entity instanceof EntityZombieBase) {
						entity.attackEntityFrom(PVZDamageSource.causeFireDamage(this, this), ((EntityLivingBase) entity).getMaxHealth());
				}
			}
			this.playSound(SoundsHandler.JALAPENO, 4,
					1);
		}
	}

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.JALAPENO_ZOMBIE;
	}
}

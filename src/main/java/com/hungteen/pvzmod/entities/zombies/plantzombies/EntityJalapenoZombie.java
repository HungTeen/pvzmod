package com.hungteen.pvzmod.entities.zombies.plantzombies;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.registry.PotionRegister;
import com.hungteen.pvzmod.util.SoundsHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
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
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(35.0D);
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
				if (!(entity instanceof EntityMob)) {
					if (entity instanceof EntityPlantBase) {
						entity.attackEntityFrom(PVZDamageSource.causeFireDamage(this, this), 1000);
					} else {
						entity.attackEntityFrom(PVZDamageSource.causeFireDamage(this, this), 20);
					}
				}
			}
			this.world.playSound(null, this.posX, this.posY, this.posZ, SoundsHandler.JALAPENO, SoundCategory.VOICE, 4,
					1);
		}
	}
}

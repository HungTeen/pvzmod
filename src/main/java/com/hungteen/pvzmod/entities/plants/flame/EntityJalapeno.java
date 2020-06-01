package com.hungteen.pvzmod.entities.plants.flame;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.plants.base.EntityBombBase;
import com.hungteen.pvzmod.particles.base.PVZParticleType;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.SoundsHandler;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityJalapeno extends EntityBombBase {

	public EntityJalapeno(World worldIn) {
		super(worldIn);
		this.setSize(0.5f, 1.4f);
	}

	@Override
	public float getAttackDamage() {
		int lvl = this.getPlantLvl();
		if (lvl <= 6)
			return 8 + 2 * lvl;
		else if (lvl <= 14)
			return 20f + 2.5f * (lvl - 6);
		else if (lvl <= 20)
			return 40 + 5f * (lvl - 14);
		return 10;
	}

	@Override
	public void startBoom() {
		int lvl = this.getPlantLvl();
		int len = (lvl <= 6) ? 15 : ((lvl <= 13) ? 20 : 30);
		BlockPos pos = new BlockPos(this);
		for (int i = pos.getZ() - len; i <= pos.getZ() + len; i++) {
			if (!this.world.isRemote) {
				if (this.world.getBlockState(new BlockPos(this.posX, this.posY, i)).getBlock() == Blocks.SNOW_LAYER) {
					this.world.setBlockToAir(new BlockPos(this.posX, this.posY, i));
				}
			}
			for (int j = 1; j <= 30; j++) {
				this.world.spawnParticle(EnumParticleTypes.FLAME, pos.getX() + 0.5, pos.getY(), i + 0.5,
						(this.getRNG().nextFloat() - 0.5) / 10, this.getRNG().nextFloat() / 8,
						(this.getRNG().nextFloat() - 0.5) / 10);
			}
//			Main.proxy.spawnParticle(PVZParticleType.YELLOW_FIRE, pos.getX() + 0.5, pos.getY(), i + 0.5, 0,0,0);
		}
		for (int i = pos.getX() - len; i <= pos.getX() + len; i++) {
			if (!this.world.isRemote) {
				if (this.world.getBlockState(new BlockPos(i, this.posY, this.posZ)).getBlock() == Blocks.SNOW_LAYER) {
					this.world.setBlockToAir(new BlockPos(i, this.posY, this.posZ));
				}
			}
			for (int j = 1; j <= 30; j++) {
				this.world.spawnParticle(EnumParticleTypes.FLAME, i + 0.5, pos.getY(), pos.getZ() + 0.5,
						(this.getRNG().nextFloat() - 0.5) / 10, this.getRNG().nextFloat() / 8,
						(this.getRNG().nextFloat() - 0.5) / 10);
			}
//			Main.proxy.spawnParticle(PVZParticleType.YELLOW_FIRE, i + 0.5, pos.getY(), pos.getZ() + 0.5, 0,0,0);
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
			for (Entity entity : EntityUtil.getEntityAttackableTarget(this, aabb)) {
					entity.attackEntityFrom(PVZDamageSource.causeFireDamage(this, this), this.getAttackDamage());
			}
			this.world.playSound(null, this.posX, this.posY, this.posZ, SoundsHandler.JALAPENO, SoundCategory.VOICE, 4,
					1);
		}
	}

	@Override
	public int getReadyTime() {
		return 30;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.JALAPENO;
	}
}

package com.hungteen.pvz.entity.plant.flame;

import com.hungteen.pvz.entity.plant.base.PlantBomberEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class JalapenoEntity extends PlantBomberEntity{

	public JalapenoEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void startBomb() {
		int range = this.getFireRange();
		for(int i=-range;i<=range;i++) {
			clearSnowAndSpawnFlame(i, 0);
			clearSnowAndSpawnFlame(0, i);
		}
		if(!world.isRemote) {
			this.playSound(SoundRegister.JALAPENO.get(), 1f, 1f);
		}
		fireMob(range,0.5f);
		fireMob(0.5f,range);
		killFireBall();
	}
	
	/**
	 * kill zomboss fireball
	 */
	private void killFireBall() {
		
	}
	
	private void fireMob(float dx, float dz) {
		BlockPos pos = new BlockPos(this);
		double x = pos.getX() + 0.5f;
		double y = pos.getY() + 0.5f;
		double z = pos.getZ() + 0.5f;
		AxisAlignedBB aabb = new AxisAlignedBB(x+dx, y+1, z+dz, x-dx, y-1, z-dz);
		for(LivingEntity target:EntityUtil.getEntityTargetableEntity(this, aabb)) {
			target.attackEntityFrom(PVZDamageSource.causeFireDamage(this, this), this.getAttackDamage());
		}
	}
	
	private void clearSnowAndSpawnFlame(int dx, int dz) {
		BlockPos pos = this.getPosition().add(dx, 0, dz);
		if(world.getBlockState(pos).getBlock()==Blocks.SNOW||world.getBlockState(pos).getBlock()==Blocks.SNOW_BLOCK) {
			world.setBlockState(pos, Blocks.AIR.getDefaultState());
		}
		if(world.isRemote) {
			for(int i=0;i<30;i++) {
				world.addParticle(ParticleTypes.FLAME, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, 
						(this.getRNG().nextFloat() - 0.5) / 10, this.getRNG().nextFloat() / 8, (this.getRNG().nextFloat() - 0.5) / 10);
			}
		}
	}

	public float getAttackDamage(){
		int lvl = this.getPlantLvl();
		if(lvl<=20) {
			int now = (lvl-1)/4;
			return 130 + now * 5;
		}
		return 130;
	}
	
	public int getFireRange() {
		int lvl = this.getPlantLvl();
		if(lvl<=6) return 10;
		if(lvl<=13) return 15;
		if(lvl<=20) return 20;
		return 10;
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

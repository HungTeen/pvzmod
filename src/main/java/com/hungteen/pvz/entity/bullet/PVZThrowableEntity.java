package com.hungteen.pvz.entity.bullet;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.utils.EntityUtil;

import net.minecraft.block.Block;
import net.minecraft.block.BushBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class PVZThrowableEntity extends ThrowableEntity implements IRendersAsItem{

	public PVZThrowableEntity(EntityType<? extends ThrowableEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	public PVZThrowableEntity(EntityType<? extends ThrowableEntity> type, World worldIn,LivingEntity shooter) {
		super(type, shooter, worldIn);
	}

	@Override
	protected void registerData() {
		
	}
	
	@Override
	public void tick() {
		super.tick();
		if(this.ticksExisted>=PVZConfig.COMMON_CONFIG.EntitySettings.EntityLiveTick.BulletLiveTick.get()) {
			this.remove();
		}
	}
	
	protected boolean checkLive(RayTraceResult result){
    	if(result.getType()==RayTraceResult.Type.ENTITY) {//打到实体
        	if(EntityUtil.checkCanEntityAttack(getThrower(), ((EntityRayTraceResult)result).getEntity())){
        		return false;
        	}
        	return true;
        }
    	else if(result.getType()==RayTraceResult.Type.BLOCK) {
    		Block block = world.getBlockState(((BlockRayTraceResult)result).getPos()).getBlock();
    		if(block instanceof BushBlock) return true;
    	}
    	return false;
    }

	/**
	 * shoot bullet such as pea or spore
	 */
	public void shootPea(double dx, double dy, double dz, double speed) {
		double down = this.getShootPeaAngle();
		double dxz = Math.sqrt(dx * dx + dz * dz);
		dy = MathHelper.clamp(dy, -dxz/down, dxz/down);
		double dis = Math.sqrt(dx * dx + dy* dy + dz * dz);
		double vx = dx / dis * speed;
		double vy = dy / dis * speed;
		double vz = dz / dis * speed;
		this.setMotion(vx, vy, vz);
	}
	
	/**
	 * get how much angle can shoot by thrower
	 */
	protected double getShootPeaAngle() {
		if(this.getThrower() instanceof PlantShooterEntity) {
			return ((PlantShooterEntity)this.getThrower()).getMaxShootAngle();
		}
		return 10;
	}
	
	protected boolean checkCanAttack(Entity target){
		return EntityUtil.checkCanEntityAttack(getThrower(), target);
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}

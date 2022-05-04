package com.hungteen.pvz.common.entity.bullet;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.api.interfaces.ICanBePushBack;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-29 11:20
 **/
public class PultProjectile extends PVZProjectile implements ICanBePushBack {

    protected int targetChance = 5;
    protected Optional<Entity> lockTarget = Optional.empty();
    protected Optional<BlockPos> lockPos = Optional.empty();
    protected float height = 12;
    protected boolean isPushedBack = false;

    public PultProjectile(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
    }

    public PultProjectile(EntityType<? extends Projectile> type, Level level, LivingEntity livingEntity) {
        super(type, level, livingEntity);
    }

    @Override
    public void tick() {
        super.tick();
        if(! this.level.isClientSide && ! this.isPushedBack && this.tickCount % this.targetChance == 0) {
            if(this.lockTarget.isPresent() && EntityUtil.isEntityValid(lockTarget.get())) {
                final Entity target = this.lockTarget.get();
                final Vec3 speed = this.getDeltaMovement();
                final double g = this.getGravity();
                final double t1 = speed.y / g;
                final double height = speed.y * speed.y / 2 / g;
                final double downHeight = this.getY() + height - target.getY() - target.getBbHeight();
                if(downHeight < 0){
                    return ;
                }
                final double t2 = Math.sqrt(2 * downHeight / g);
                final double dx = target.getX() + target.getDeltaMovement().x() * (t1 + t2) - this.getX();
                final double dz = target.getZ() + target.getDeltaMovement().z() * (t1 + t2) - this.getZ();
                final double dxz = Math.sqrt(dx * dx + dz * dz);
                final double vxz = dxz / (t1 + t2);
                if(dxz == 0) {
                    this.setDeltaMovement(0, speed.y, 0);
                } else {
                    this.setDeltaMovement(vxz * dx / dxz, speed.y, vxz * dz / dxz);
                }
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        this.canExist = false;
    }

    @Override
    public void pushBack() {
        this.isPushedBack = true;
        this.setDeltaMovement(MathUtil.getRandomFloat(random), this.random.nextFloat() * 2, MathUtil.getRandomFloat(random));
    }

    /**
     * Pult shoot to entity.
     */
    public void shootPultBullet(Entity target) {
        if(target == null) {
            PVZMod.LOGGER.warn("No pult target at all !");
            return ;
        }
        this.lockTarget = Optional.ofNullable(target);
        final double g = this.getGravity();
        final double t1 = Math.sqrt(2 * height / g);//go up time.
        double t2 = 0;
        if(this.getY() + height - target.getY() - target.getBbHeight() >= 0) {//random pult.
            t2 = Math.sqrt(2 * (this.getY() + height - target.getY() - target.getBbHeight()) / g);//go down time.
        }
        final double dx = target.getX() + target.getDeltaMovement().x() * (t1 + t2) - this.getX();
        final double dz = target.getZ() + target.getDeltaMovement().z() * (t1 + t2) - this.getZ();
        setPultSpeed(g, t1, t2, dx, dz);
    }

    /**
     * Pult shoot.
     */
    public void shootPultBullet(BlockPos pos) {
        if(pos == null) {
            PVZMod.LOGGER.warn("No pult target at all !");
            return ;
        }
        this.lockPos = Optional.ofNullable(pos);
        final double g = this.getGravity();
        final double t1 = Math.sqrt(2 * height / g);//go up time.
        double t2 = 0;
        if(this.getY() + height - pos.getY() - 1 >= 0) {//random pult.
            t2 = Math.sqrt(2 * (this.getY() + height - pos.getY() - 1) / g);//go down time.
        }
        final double dx = pos.getX() - this.getX();
        final double dz = pos.getZ() - this.getZ();
        setPultSpeed(g, t1, t2, dx, dz);
    }

    private void setPultSpeed(double g, double t1, double t2, double dx, double dz) {
        final double dxz = Math.sqrt(dx * dx + dz * dz);
        final double vxz = dxz / (t1 + t2);
        final double vy = g * t1;
        if(dxz == 0) {
            this.setDeltaMovement(0, vy, 0);
        } else {
            this.setDeltaMovement(vxz * dx / dxz, vy, vxz * dz / dxz);
        }
    }

    @Override
    protected float getGravity() {
        return 0.1F;
    }

    @Override
    public boolean isNoGravity() {
        return false;
    }

    @Override
    public int getMaxLiveTick() {
        return 400;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if(compound.contains("TargetEntityId")) {
            this.lockTarget = Optional.ofNullable((LivingEntity) level.getEntity(compound.getInt("TargetEntityId")));
        }
        if(compound.contains("IsPushedBack")) {
            this.isPushedBack = compound.getBoolean("IsPushedBack");
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if(this.lockTarget.isPresent()) {
            compound.putInt("TargetEntityId", this.lockTarget.get().getId());
        }
        compound.putBoolean("IsPushedBack", this.isPushedBack);
    }

}

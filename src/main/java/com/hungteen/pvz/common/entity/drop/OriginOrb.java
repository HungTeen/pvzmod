package com.hungteen.pvz.common.entity.drop;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-13 08:48
 **/
public class OriginOrb extends PVZDrop {

    private Player followingPlayer;
    private int xpCount;

    public OriginOrb(EntityType<? extends Mob> type, Level worldIn) {
        super(type, worldIn);
    }

    public void tick() {
        super.tick();
//        this.xo = this.getX();
//        this.yo = this.getY();
//        this.zo = this.getZ();
//        if (this.isEyeInFluid(FluidTags.WATER)) {
//            this.setUnderwaterMovement();
//        } else if (!this.isNoGravity()) {
//            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.03D, 0.0D));
//        }
//
//        if (this.level.getFluidState(this.blockPosition()).is(FluidTags.LAVA)) {
//            this.setDeltaMovement((double)((this.random.nextFloat() - this.random.nextFloat()) * 0.2F), (double)0.2F, (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.2F));
//        }
//
//        if (!this.level.noCollision(this.getBoundingBox())) {
//            this.moveTowardsClosestSpace(this.getX(), (this.getBoundingBox().minY + this.getBoundingBox().maxY) / 2.0D, this.getZ());
//        }
//
//        if (this.tickCount % 20 == 1) {
//            this.scanForEntities();
//        }
//
//        if (this.followingPlayer != null && (this.followingPlayer.isSpectator() || this.followingPlayer.isDeadOrDying())) {
//            this.followingPlayer = null;
//        }
//
//        if (this.followingPlayer != null) {
//            Vec3 vec3 = new Vec3(this.followingPlayer.getX() - this.getX(), this.followingPlayer.getY() + (double)this.followingPlayer.getEyeHeight() / 2.0D - this.getY(), this.followingPlayer.getZ() - this.getZ());
//            double d0 = vec3.lengthSqr();
//            if (d0 < 64.0D) {
//                double d1 = 1.0D - Math.sqrt(d0) / 8.0D;
//                this.setDeltaMovement(this.getDeltaMovement().add(vec3.normalize().scale(d1 * d1 * 0.1D)));
//            }
//        }
//
//        this.move(MoverType.SELF, this.getDeltaMovement());
//        float f = 0.98F;
//        if (this.onGround) {
//            BlockPos pos =new BlockPos(this.getX(), this.getY() - 1.0D, this.getZ());
//            f = this.level.getBlockState(pos).getFriction(this.level, pos, this) * 0.98F;
//        }
//
//        this.setDeltaMovement(this.getDeltaMovement().multiply((double)f, 0.98D, (double)f));
//        if (this.onGround) {
//            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, -0.9D, 1.0D));
//        }
//
//        ++this.age;
//        if (this.age >= 6000) {
//            this.discard();
//        }

    }

    @Override
    public void onCollect(LivingEntity living) {
        if(! level.isClientSide){

        }
        this.discard();
    }

    /**
     * get render picture index.
     */
    public int getIcon() {
        final int value = this.getAmount();
        return value < 100 ? (value / 20) : value < 1200 ? value / 200 + 4 : 10;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.xpCount = compound.getInt("xp_count");
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("xp_count", this.xpCount);
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return EntityDimensions.scalable(0.6F, 0.6F);
    }

    @Override
    protected int getMaxLiveTick() {
        return 1000000;
    }

    @Override
    protected int getDefaultAmount() {
        return 10;
    }
}

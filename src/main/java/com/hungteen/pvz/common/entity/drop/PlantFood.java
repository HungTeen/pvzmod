package com.hungteen.pvz.common.entity.drop;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.PVZSounds;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-13 08:41
 **/
public class PlantFood extends DropEntityBase {

    private static final int CHANGE_SPEED_CD = 40;

    public PlantFood(EntityType<? extends Mob> type, Level worldIn) {
        super(type, worldIn);
        this.setNoGravity(true);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.existTick % CHANGE_SPEED_CD == 0) {
            final double speed = 0.2f;
            final int dx = MathUtil.getRandomInRange(this.random, 500);
            final int dy = MathUtil.getRandomInRange(this.random, 500);
            final int dz = MathUtil.getRandomInRange(this.random, 500);
            final Vec3 motion = new Vec3( dx, dy, dz).normalize().scale(speed);
            this.setDeltaMovement(motion.x ,motion.y / 3, motion.z);
        }
    }

    @Override
    public void onCollect(LivingEntity living) {
        if(! this.level.isClientSide) {
            if(living instanceof Player){
                PlayerUtil.addResource((Player) living, Resources.ENERGY_NUM, this.getAmount());
                PlayerUtil.playClientSound((Player) living, PVZSounds.JEWEL_PICK.get());
            }
        }
        this.discard();
    }

    @Override
    public EntityDimensions getDimensions(Pose poseIn) {
        return EntityDimensions.scalable(1F, 1.2F);
    }

    @Override
    protected int getMaxLiveTick() {
        return PVZConfig.getPlantFoodLiveTick();
    }

    @Override
    protected int getDefaultAmount() {
        return 1;
    }
}

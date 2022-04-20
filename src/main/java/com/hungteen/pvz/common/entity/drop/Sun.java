package com.hungteen.pvz.common.entity.drop;

import java.util.Random;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.sound.PVZSounds;
import com.hungteen.pvz.common.entity.PVZEntities;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-11 09:16
 **/
public class Sun extends PVZDrop {

    private static final float SUN_FALL_SPEED = 0.03F;

    public Sun(EntityType<? extends Mob> type, Level worldIn) {
        super(type, worldIn);
        this.setNoGravity(true);
    }

    @Override
    public void tick() {
        super.tick();
        if(! this.onGround && ! this.isInWater()) {
            double speedY = this.getDeltaMovement().y;
            if(speedY > - SUN_FALL_SPEED){
                speedY -= SUN_FALL_SPEED / 2;
            } else{
                speedY = -SUN_FALL_SPEED;
            }
            this.setDeltaMovement(this.getDeltaMovement().x() * 0.94, speedY, this.getDeltaMovement().z() * 0.94);
        } else{
            this.setDeltaMovement(Vec3.ZERO);
        }
    }

    @Override
    protected int getDefaultAmount() {
        return 25;
    }

    @Override
    public void onCollect(LivingEntity living) {
        if(living instanceof Player){
            if(! level.isClientSide) {
                //TODO Sun mending.
//            Map.Entry<EquipmentSlotType, ItemStack> entry = EnchantmentHelper.getRandomItemWith(EnchantmentRegister.SUN_MENDING.get(), player);
//            if(entry != null) {
//                SunMendingEnchantment.repairItem(entry.getValue(), this.getAmount());
//            } else {
//                PlayerUtil.addResource(player, Resources.SUN_NUM, this.getAmount());
//                if(MissionManager.getPlayerMission(player) == MissionManager.MissionType.COLLECT_SUN){
//                    PlayerUtil.addResource(player, Resources.MISSION_VALUE, this.getAmount());
//                }
//            }
                PlayerUtil.addResource((Player) living, Resources.SUN_NUM, this.getAmount());
                PlayerUtil.playClientSound((Player) living, PVZSounds.SUN_PICK.get());
            }
        }
        this.discard();
    }

    @Override
    public EntityDimensions getDimensions(Pose poseIn) {
        int amount = this.getAmount(); //25 0.6
        float w = amount * 1f / 200 + 0.3f, h = amount * 1f / 75 + 0.1f;
        return EntityDimensions.scalable(w, h);//max(0.8w,1.5h), min(0.4w,0.3h).
    }

    public static void spawnSunsByAmount(Level world, BlockPos pos, int amount) {
        spawnSunsByAmount(world, pos, amount, 75, 1);
    }

    /**
     * spawn sun in range, each is set to a specific amount.
     */
    public static void spawnSunsByAmount(Level world, BlockPos pos, int amount, int each, double speed) {
        while(amount >= each) {
            amount -= each;
            dropSunRandomly(world, pos, each, speed);
        }
        if(amount != 0) {
            dropSunRandomly(world, pos, amount, speed);
            amount = 0;
        }
    }

//    /**
//     * spawn sun entity in range randomly with specific amount.
//     */
//    public static void spawnSunRandomly(Level world, BlockPos pos, int amount, int dis) {
//        final Sun sun = PVZEntities.SUN.get().create(world);
//        sun.setAmount(amount);
//        EntityUtil.onEntityRandomPosSpawn(world, sun, pos, dis);
//    }

    /**
     * spawn random speed sun entity with specific amount.
     */
    public static void dropSunRandomly(Level world, BlockPos pos, int amount, double speed) {
        final Sun sun = PVZEntities.SUN.get().create(world);
        sun.setAmount(amount);
        EntityUtil.onEntitySpawn(world, sun, pos);
        final double dy = speed * 0.3+0.2;
        final double dx = MathUtil.getRandomFloat(world.getRandom());
        final double dz = MathUtil.getRandomFloat(world.getRandom());
        sun.setDeltaMovement(new Vec3(dx, 0, dz).scale(speed).add(0, dy, 0));
    }

    public static boolean canSunSpawn(EntityType<? extends Sun> zombieType, LevelAccessor worldIn, MobSpawnType reason, BlockPos pos, Random rand) {
        if(worldIn instanceof ServerLevel) {
            return ! ((ServerLevel) worldIn).isRainingAt(pos) && ((ServerLevel) worldIn).isDay() && worldIn.getBrightness(LightLayer.SKY, pos) >= 15;
        }
        return worldIn.getBrightness(LightLayer.SKY, pos) >= 15;
    }

    @Override
    protected int getMaxLiveTick() {
        return PVZConfig.getSunLiveTick();
    }


}

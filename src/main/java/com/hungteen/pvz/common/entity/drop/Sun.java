package com.hungteen.pvz.common.entity.drop;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.PVZSounds;
import com.hungteen.pvz.common.entity.PVZEntities;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-11 09:16
 **/
public class Sun extends DropEntityBase {

    private static final float FALL_SPEED = 0.03F;

    public Sun(EntityType<? extends Mob> type, Level worldIn) {
        super(type, worldIn);
        this.setNoGravity(true);
    }

    @Override
    public void tick() {
        super.tick();
        if(! this.onGround && ! this.isInWater()) {
            this.setDeltaMovement(this.getDeltaMovement().x(), - FALL_SPEED, this.getDeltaMovement().z());
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
    public static void spawnSunsByAmount(Level world, BlockPos pos, int amount, int each, int range) {
        while(amount >= each) {
            amount -= each;
            spawnSunRandomly(world, pos, each, range);
        }
        if(amount != 0) {
            spawnSunRandomly(world, pos, amount, range);
            amount = 0;
        }
    }

    /**
     * spawn sun entity in range randomly with specific amount.
     */
    public static void spawnSunRandomly(Level world, BlockPos pos, int amount, int dis) {
        Sun sun = PVZEntities.SUN.get().create(world);
        sun.setAmount(amount);
        EntityUtil.onEntityRandomPosSpawn(world, sun, pos, dis);
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

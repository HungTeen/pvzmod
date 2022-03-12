package com.hungteen.pvz.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;

import java.util.Random;
import java.util.function.Predicate;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 09:15
 **/
public class EntityUtil {

    /**
     * has the predicated entity nearby.
     */
    public static boolean hasNearBy(Level world, BlockPos pos, double r, Predicate<Entity> pre) {
        return world.getEntitiesOfClass(Entity.class, BlockUtil.getAABB(pos, r, r)).stream().filter(target -> {
            return pre.test(target);
        }).count() > 0;
    }

    /**
     * use to spawn entity in world.
     */
    public static void onEntitySpawn(LevelAccessor world, Entity entity, BlockPos pos) {
        entity.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
        if(entity instanceof Mob && world instanceof ServerLevelAccessor) {
            ((Mob) entity).finalizeSpawn((ServerLevelAccessor) world, world.getCurrentDifficultyAt(entity.blockPosition()), MobSpawnType.SPAWNER, null, null);
        }
        world.addFreshEntity(entity);
    }

    /**
     * spawn in random range position.
     */
    public static void onEntityRandomPosSpawn(LevelAccessor world, Entity entity, BlockPos pos, int dis) {
        pos = pos.offset(MathUtil.getRandomInRange(world.getRandom(), dis), world.getRandom().nextInt(dis) + 1, MathUtil.getRandomInRange(world.getRandom(), dis));
        onEntitySpawn(world, entity, pos);
    }

    public static void playSound(Entity entity, SoundEvent ev) {
        if(ev != null) {
            entity.playSound(ev, 1.0F, rand(entity).nextFloat() * 0.2F + 0.9F);
        }
    }

    public static Random rand(Entity entity){
        return entity.level.random;
    }

    public static boolean isEntityValid(Entity target) {
        return target != null && target.isAlive();
    }

}

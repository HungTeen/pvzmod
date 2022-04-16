package com.hungteen.pvz.utils;

import com.hungteen.pvz.api.interfaces.IHasOwner;
import com.hungteen.pvz.common.effect.PVZEffects;
import com.hungteen.pvz.common.entity.EntityGroupHandler;
import com.hungteen.pvz.common.entity.PVZPAZ;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.scores.Team;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 09:15
 **/
public class EntityUtil {

    /**
     * get the owner of entity
     */
    @Nullable
    public static Player getEntityOwner(Level world, @Nullable Entity entity) {
        UUID uuid = null;
        if(entity instanceof IHasOwner) {
            uuid = ((IHasOwner) entity).getOwnerUUID().orElse(null);
        }
        return uuid == null ? null : world.getPlayerByUUID(uuid);
    }

    /**
     * use for TargetGoal to check if attacker can set target as AttackTarget.
     */
    public static boolean canTargetEntity(Entity attacker, Entity target) {
        if(attacker instanceof PVZPAZ) {
            return ((PVZPAZ) attacker).checkCanPAZTarget(target);
        }
        return checkCanEntityBeTarget(attacker, target);
    }

    public static boolean canAttackEntity(Entity attacker, Entity target) {
        if(attacker instanceof PVZPAZ) {
            return ((PVZPAZ) attacker).checkCanPAZAttack(target);
        }
        return checkCanEntityBeAttack(attacker, target);
    }

    /**
     * check can TargetGoal select target as attack target.
     */
    public static boolean checkCanEntityBeTarget(Entity attacker, Entity target) {
        if(attacker == null || target == null || isOpEntity(target)) {//prevent crash
            return false;
        }
//        if(target instanceof PVZMultiPartEntity) {//can attack its owner then can attack it
//            return checkCanEntityBeTarget(attacker, ((PVZMultiPartEntity) target).getOwner());
//        }
//        if(ConfigUtil.isTeamAttackEnable()) {//enable team attack
//            final Team team1 = getEntityTeam(attacker.level, attacker);
//            final Team team2 = getEntityTeam(attacker.level, target);
//            if(team1 != null && team2 != null) {
//                return isEntityCharmed(attacker) ^ isEntityCharmed(target) ? team1.isAlliedTo(team2) : ! team1.isAlliedTo(team2);
//            }
//        }
        if(attacker instanceof LivingEntity) {//target the entity who attack it before.
            if(target.is(((LivingEntity) attacker).getLastHurtMob()) && checkCanEntityBeAttack(attacker, target)) {
                return true;
            }
        }
        return EntityGroupHandler.checkCanTarget(attacker, target);
    }

    /**
     * check can AttackGoal continue to attack target.
     */
    public static boolean checkCanEntityBeAttack(Entity attacker, Entity target) {
        if(attacker == null || target == null) {//prevent crash
            return false;
        }
//        if(target instanceof PVZMultiPartEntity) {//can attack its owner then can attack it
//            return checkCanEntityBeAttack(attacker, ((PVZMultiPartEntity) target).getOwner());
//        }
        if(target instanceof Player && !PlayerUtil.isPlayerSurvival((Player) target)) {
            return false;
        }
//        if(ConfigUtil.isTeamAttackEnable()) {//enable team attack
//            final Team team1 = getEntityTeam(attacker.level, attacker);
//            final Team team2 = getEntityTeam(attacker.level, target);
//            if(team1 != null && team2 != null) {
//                return isEntityCharmed(attacker) ^ isEntityCharmed(target) ? team1.isAlliedTo(team2) : ! team1.isAlliedTo(team2);
//            }
//        }
        return EntityGroupHandler.checkCanAttack(attacker, target);
    }

    /**
     * get targetable livingentity in range.
     */
    public static List<LivingEntity> getTargetableLivings(@Nonnull Entity attacker, AABB aabb){
        return getPredicateEntities(attacker, aabb, LivingEntity.class, target -> canTargetEntity(attacker, target));
    }

//    /**
//     * get targetable entities by original check function.
//     * {@link #getWholeTargetableEntities(Entity, AABB)}
//     */
//    private static List<Entity> getTargetableEntitiesIngoreCheck(@Nonnull Entity attacker, AABB aabb){
//        return getPredicateEntities(attacker, aabb, Entity.class, target -> checkCanEntityBeTarget(attacker, target));
//    }

    /**
     * base predicate target method.
     */
    public static <T extends Entity> List<T> getPredicateEntities(@Nonnull Entity attacker, AABB aabb, Class<T> tClass, Predicate<T> predicate){
        if(attacker == null) {
            return new ArrayList<>();
        }
        return attacker.level.getEntitiesOfClass(tClass, aabb).stream().filter(target -> {
            return ! attacker.equals(target) && predicate.test(target);
        }).collect(Collectors.toList());
    }

    /**
     * get final attack entities for explosion or other range attack.
     * use for every range attack condition.
     */
    public static List<Entity> getWholeTargetableEntities(@Nonnull Entity attacker, AABB aabb) {
        IntOpenHashSet set = new IntOpenHashSet();
        List<Entity> list = new ArrayList<>();
        if(attacker == null) return list;//prevent crash.
        List<Entity> targets = getTargetableEntitiesIngoreCheck(attacker, aabb);
        //choose owner first.
        targets.stream().filter(target ->
//                ! (target instanceof PVZMultiPartEntity) &&
                ! set.contains(target.getId())).forEach(target -> {
            set.addAll(getOwnerAndPartsID(target));
            list.add(target);
        });
        //deal with part.
//        targets.stream().filter(target -> target instanceof PVZMultiPartEntity
//                && ! set.contains(target.getId())).forEach(target -> {
//            set.addAll(getOwnerAndPartsID(target));
//            list.add(target);
//        });
        return list;
    }

    /**
     * get targetable entities by original check function, often used in explosion. <br>
     * {@link #getWholeTargetableEntities(Entity, AABB)}
     */
    private static List<Entity> getTargetableEntitiesIngoreCheck(@Nonnull Entity attacker, AABB aabb){
        return getPredicateEntities(attacker, aabb, Entity.class, target -> checkCanEntityBeTarget(attacker, target));
    }

    /**
     * get all parts of an entity, so that you can only hit them once.
     */
    public static List<Integer> getOwnerAndPartsID(Entity entity){
        List<Integer> list = new ArrayList<>();
//        if(entity instanceof PVZMultiPartEntity) {//the entity is a part.
//            LivingEntity owner = ((PVZMultiPartEntity) entity).getOwner();
//            if(owner == null) {// no owner
//                list.add(entity.getId());
//            } else {// get all id for owner's parts
//                IHasMultiPart parent = ((PVZMultiPartEntity) entity).getParent();
//                for(Entity target : parent.getMultiParts()) {
//                    if(target != null) {
//                        list.add(target.getId());
//                    }
//                }
//                list.add(owner.getId());
//            }
//        } else if(entity instanceof IHasMultiPart){
//            for(Entity target : ((IHasMultiPart) entity).getMultiParts()) {
//                if(target != null) {
//                    list.add(target.getId());
//                }
//            }
//            list.add(entity.getId());
//        } else {
            list.add(entity.getId());
//        }
        return list;
    }

    /**
     * no need to target or attack an op entity.
     */
    public static boolean isOpEntity(Entity entity){
        if(entity instanceof Player && ! PlayerUtil.isPlayerSurvival((Player) entity)){
            return true;
        }
        return entity.isInvulnerable();
    }

    public static boolean inEnergetic(LivingEntity entity){
        return entity.hasEffect(PVZEffects.ENERGETIC_EFFECT.get());
    }

    public static int getEnergeticTime(LivingEntity entity){
        return inEnergetic(entity) ? entity.getEffect(PVZEffects.ENERGETIC_EFFECT.get()).getDuration() : 0;
    }

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

//    /**
//     * spawn in random range position.
//     */
//    public static void onRandomSpeedSpawn(LevelAccessor world, Entity entity, BlockPos pos, double speed) {
//        onEntitySpawn(world, entity, pos);
//        final double dy = world.getRandom().nextDouble();
//        final double dx = MathUtil.getRandomFloat(world.getRandom());
//        final double dz = MathUtil.getRandomFloat(world.getRandom());
//        entity.setDeltaMovement(new Vec3(dx, dy, dz).scale(speed));
//    }

    public static void playSound(Entity entity, SoundEvent ev) {
        if(ev != null) {
            entity.playSound(ev, 1.0F, rand(entity).nextFloat() * 0.2F + 0.9F);
        }
    }

    /**
     * get AABB by entity's width and height.
     */
    public static AABB getEntityAABB(Entity entity, double w, double h){
        return BlockUtil.getAABB(entity.blockPosition(), w, h);
    }

    public static Random rand(Entity entity){
        return entity.level.random;
    }

    public static boolean isEntityValid(Entity target) {
        return target != null && target.isAlive();
    }

}

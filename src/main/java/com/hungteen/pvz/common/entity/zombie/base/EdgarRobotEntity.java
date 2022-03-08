package com.hungteen.pvz.common.entity.zombie.base;

import com.hungteen.pvz.api.enums.BodyType;
import com.hungteen.pvz.api.types.IZombieType;
import com.hungteen.pvz.common.entity.EntityRegister;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.misc.DestroyCarEntity;
import com.hungteen.pvz.common.entity.misc.ElementBallEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.body.ZombieDropBodyEntity;
import com.hungteen.pvz.common.entity.zombie.roof.BungeeZombieEntity;
import com.hungteen.pvz.common.impl.zombie.*;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.*;
import com.hungteen.pvz.utils.others.WeightList;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.CreatureEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-02-04 11:32
 **/
public abstract class EdgarRobotEntity extends AbstractBossZombieEntity {

    private static final DataParameter<Integer> STATES = EntityDataManager.defineId(EdgarRobotEntity.class, DataSerializers.INT);
    private static final WeightList<ZombieType> ZOMBIES_1 = new WeightList<>();
    private static final WeightList<ZombieType> ZOMBIES_2 = new WeightList<>();
    private static final WeightList<ZombieType> ZOMBIES_3 = new WeightList<>();
    private static final WeightList<ZombieType> ZOMBIES_4 = new WeightList<>();
    private static final WeightList<ZombieType> ZOMBIES_5 = new WeightList<>();
    protected int throwCarTick;
    protected int shootBallTick;
    protected int stealPlantTick;

    static {
        {//stage 1 zombies.
            ZOMBIES_1.addItem(GrassZombies.NORMAL_ZOMBIE, 100);
            ZOMBIES_1.addItem(GrassZombies.NEWSPAPER_ZOMBIE, 90);
            ZOMBIES_1.addItem(GrassZombies.SCREENDOOR_ZOMBIE, 70);
            ZOMBIES_1.addItem(PoolZombies.SNORKEL_ZOMBIE, 100);
            ZOMBIES_1.addItem(PoolZombies.BALLOON_ZOMBIE, 80);
            ZOMBIES_1.addItem(RoofZombies.IMP, 80);
        }
        {//stage 2 zombies.
            ZOMBIES_2.addItem(GrassZombies.CONEHEAD_ZOMBIE, 100);
            ZOMBIES_2.addItem(GrassZombies.POLE_ZOMBIE, 90);
            ZOMBIES_2.addItem(GrassZombies.DANCING_ZOMBIE, 70);
            ZOMBIES_2.addItem(GrassZombies.OLD_ZOMBIE, 80);
            ZOMBIES_2.addItem(PoolZombies.JACK_IN_BOX_ZOMBIE, 85);
            ZOMBIES_2.addItem(PoolZombies.DIGGER_ZOMBIE, 70);
            ZOMBIES_2.addItem(PoolZombies.POGO_ZOMBIE, 85);
            ZOMBIES_2.addItem(PoolZombies.BALLOON_ZOMBIE, 80);
        }
        {//stage 3 zombies.
            ZOMBIES_3.addItem(GrassZombies.BUCKETHEAD_ZOMBIE, 100);
            ZOMBIES_3.addItem(GrassZombies.FOOTBALL_ZOMBIE, 90);
            ZOMBIES_3.addItem(PoolZombies.ZOMBONI, 85);
            ZOMBIES_3.addItem(PoolZombies.JACK_IN_BOX_ZOMBIE, 85);
            ZOMBIES_3.addItem(PoolZombies.DIGGER_ZOMBIE, 75);
            ZOMBIES_3.addItem(RoofZombies.LADDER_ZOMBIE, 85);
            ZOMBIES_3.addItem(RoofZombies.CATAPULT_ZOMBIE, 80);
        }
        {//stage 4 zombies.
            ZOMBIES_4.addItem(GrassZombies.FOOTBALL_ZOMBIE, 100);
            ZOMBIES_4.addItem(GrassZombies.GIGA_FOOTBALL_ZOMBIE, 80);
            ZOMBIES_4.addItem(PoolZombies.ZOMBONI, 90);
            ZOMBIES_4.addItem(CustomZombies.LAVA_ZOMBIE, 85);
            ZOMBIES_4.addItem(PoolZombies.DIGGER_ZOMBIE, 75);
            ZOMBIES_4.addItem(RoofZombies.CATAPULT_ZOMBIE, 90);
            ZOMBIES_4.addItem(RoofZombies.GARGANTUAR, 85);
        }
        {//stage 5 zombies.
            ZOMBIES_5.addItem(GrassZombies.GIGA_FOOTBALL_ZOMBIE, 100);
            ZOMBIES_5.addItem(GrassZombies.SUNDAY_EDITION_ZOMBIE, 85);
            ZOMBIES_5.addItem(CustomZombies.LAVA_ZOMBIE, 95);
            ZOMBIES_5.addItem(RoofZombies.CATAPULT_ZOMBIE, 110);
            ZOMBIES_5.addItem(RoofZombies.GARGANTUAR, 100);
            ZOMBIES_5.addItem(RoofZombies.GIGA_GARGANTUAR, 70);
        }
    }

    public EdgarRobotEntity(EntityType<? extends CreatureEntity> type, Level worldIn) {
        super(type, worldIn);
        this.setIsWholeBody();
        this.shootBallTick = this.getShootBallCD();
        this.stealPlantTick = this.getStealPlantCD();
        this.throwCarTick = this.getThrowCarCD();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STATES, EdgarStates.NORMAL.ordinal());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(7, new LookAtGoal(this, Player.class, 40.0F));
        registerTargetGoals();
        registerAttackGoals();
    }

    @Override
    protected void registerAttackGoals() {
        this.goalSelector.addGoal(4, new EdgarShootBallGoal(this));
        this.goalSelector.addGoal(5, new EdgarStealPlantGoal(this));
        this.goalSelector.addGoal(3, new EdgarThrowCarGoal(this));
    }

    @Override
    protected void registerTargetGoals() {
        this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, false, true, ZombieUtil.LITTLE_CLOSE_TARGET_RANGE, ZombieUtil.LITTLE_HIGH_TARGET_HEIGHT));
    }

    @Override
    public void normalZombieTick() {
        super.normalZombieTick();
        if (!level.isClientSide) {
            if(this.getExistTick() % 100 == 0){
                this.summonZombieByBungee();
            }
        }
    }

    @Override
    public boolean canPAZTarget(Entity target) {
        return true;
    }

    /**
     * Skill : Summon Zombie by Bungee
     */
    public void summonZombieByBungee() {
        if (this.nearbyZombieCount < this.maxZombieSurround) {//too many zombies nearby
            final int count = this.getSpawnCount();
            for (int i = 0; i < count; ++i) {
            this.getSummonZombie().ifPresent(entity -> {
                if(entity instanceof PVZZombieEntity){
                    this.onBossSummon((PVZZombieEntity) entity, blockPosition().above(10));

                    BungeeZombieEntity bungee = EntityRegister.BUNGEE_ZOMBIE.get().create(this.level);
                    bungee.setBungeeType(BungeeZombieEntity.BungeeTypes.SUMMON);
                    bungee.setBungeeState(BungeeZombieEntity.BungeeStates.DOWN);
                    bungee.setStealTarget(entity);
                    Mth pos = this.getRandom().nextInt(5) == 0 ?
                            WorldUtil.getSuitableHeightRandomPos(this.level, blockPosition(), 20, 40) :
                            WorldUtil.getSuitableHeightRandomPos(this.level, blockPosition(), 3, 8);
                    this.onBossSummon(bungee, pos.above(20));

                    EntityUtil.playSound(bungee, SoundRegister.BUNGEE_SCREAM.get());
                }
            });
            }
        }
    }

    @Override
    protected void tickDeath() {
        super.tickDeath();
        if (this.deathTime % 20 == 1) {
            if (level.isClientSide) {
                level.addParticle(ParticleTypes.EXPLOSION_EMITTER, getX(), getY(), getZ(), 0, 0, 0);
                level.addParticle(ParticleTypes.EXPLOSION_EMITTER, getX(), getY() + 5, getZ(), 0, 0, 0);
            }
        }
    }

    @Override
    protected int getDeathTime() {
        return 60;
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
        if (!level.isClientSide) {
            this.bossInfo.getPlayers().forEach((player) -> {
                CriteriaTriggers.PLAYER_KILLED_ENTITY.trigger(player, this, source);
            });
        }
    }

    @Override
    protected void onFallBody(DamageSource source) {
        Arrays.asList(BodyType.HEAD, BodyType.BODY, BodyType.LEFT_HAND,
                        BodyType.RIGHT_HAND, BodyType.LEFT_LEG, BodyType.RIGHT_LEG)
                .forEach(type -> {
                    ZombieDropBodyEntity body = EntityRegister.ZOMBIE_DROP_BODY.get().create(level);
                    body.updateInfo(this, type);
                    body.setMaxLiveTick(60);
                    this.setBodyStates(body);
                    final float dx = MathUtil.getRandomFloat(getRandom());
                    final float dy = this.getRandom().nextFloat();
                    final float dz = MathUtil.getRandomFloat(getRandom());
                    body.setDeltaMovement(dx, dy, dz);
                    body.setPos(position().x, position().y + this.getBbHeight() / 2, position().z);
                    level.addFreshEntity(body);
                });
    }

    /**
     * Skill : Shoot ElementBall To Target
     * {@link EdgarShootBallGoal#tick()}
     */
    public void shootElementBall() {
        final ElementBallEntity ball = EntityRegister.ELEMENT_BALL.get().create(level);
        ball.summonByOwner(this);
        ball.setSpeed(this.getElementBallSpeed());
        ball.setAuto(this.shootAutoBall());
        ball.setPos(getX(), getY() + this.getEyeHeight(), getZ());
        ball.shoot(this.getTarget());
        ball.setElementBallType(this.getRobotState() == EdgarRobotEntity.EdgarStates.ICE ? ElementBallEntity.ElementTypes.ICE : ElementBallEntity.ElementTypes.FLAME);
        this.level.addFreshEntity(ball);
        this.setRobotState(EdgarRobotEntity.EdgarStates.NORMAL);

        this.shootBallTick = this.getShootBallCD() + MathUtil.getRandomInRange(this.getRandom(), 80);
    }

    /**
     * Skill : Steal Target By Bungee.
     * {@link EdgarStealPlantGoal#tick()}
     */
    public void stealRandomTargets() {
        this.setRobotState(EdgarRobotEntity.EdgarStates.NORMAL);
        final int cnt = this.getStealCount();
        final float range = 50;
        List<LivingEntity> list = EntityUtil.getTargetableLivings(this, EntityUtil.getEntityAABB(this, range, range));
        if (! list.isEmpty()) {
            for (int i = 0; i < cnt; ++i) {
                LivingEntity target = list.get(this.getRandom().nextInt(list.size()));
                BungeeZombieEntity zombie = EntityRegister.BUNGEE_ZOMBIE.get().create(level);
                zombie.setBungeeType(BungeeZombieEntity.BungeeTypes.STEAL);
                zombie.setStealTarget(target);
                zombie.setBungeeState(BungeeZombieEntity.BungeeStates.DOWN);
                ZombieUtil.copySummonZombieData(this, zombie);
                EntityUtil.onEntitySpawn(level, zombie, blockPosition().above(18));
            }
        }

        this.stealPlantTick= this.getStealPlantCD() + MathUtil.getRandomInRange(this.getRandom(), 120);
    }

    /**
     * Skill : throw destroy car to random target.
     * {@link EdgarThrowCarGoal#tick()}
     */
    public void throwDestroyCar() {
        this.setRobotState(EdgarRobotEntity.EdgarStates.NORMAL);
        final int max = Math.max(1, (this.nearbyPlantCount - this.maxPlantSurround) / 10);
        final int throwNum = MathUtil.getRandomMinMax(getRandom(), max / 2 + 1, max + 1);
        final float range = 50;
        final List<LivingEntity> list = EntityUtil.getTargetableLivings(this, EntityUtil.getEntityAABB(this, range, range));
        if (! list.isEmpty()) {
            for (int i = 0; i < throwNum; ++i) {
                final int pos = this.getRandom().nextInt(list.size());
                DestroyCarEntity car = EntityRegister.DESTROY_CAR.get().create(this.level);
                car.summonByOwner(this);
                car.setPos(getX(), getY() + this.getEyeHeight(), getZ());
                car.shootPultBullet(list.get(pos));
                level.addFreshEntity(car);
            }
        }

        this.throwCarTick = this.getThrowCarCD() + MathUtil.getRandomInRange(this.getRandom(), 160);
    }

    protected Optional<CreatureEntity> getSummonZombie() {
        final int stage = getBossStage();
        IZombieType zombieType;
        if (stage == 1) zombieType = ZOMBIES_1.getRandomItem(this.getRandom()).get();
        else if (stage == 2) zombieType = ZOMBIES_2.getRandomItem(this.getRandom()).get();
        else if (stage == 3) zombieType = ZOMBIES_3.getRandomItem(this.getRandom()).get();
        else if (stage == 4) zombieType = ZOMBIES_4.getRandomItem(this.getRandom()).get();
        else if (stage == 5) zombieType = ZOMBIES_5.getRandomItem(this.getRandom()).get();
        else {
            System.out.println("Error : Wrong Boss Stage !");
            return Optional.empty();
        }
        if (zombieType.getEntityType().isPresent()) {
            return Optional.ofNullable(zombieType.getEntityType().get().create(this.level));
        }
        return Optional.empty();
    }

    public abstract int getBossStage();

    public float getElementBallSpeed(){
        return 0.18F;
    }

    public int getStealCount(){
        return 3;
    }

    public int getSpawnCount(){
        return 2;
    }

    public boolean shootAutoBall(){
        return false;
    }

    public int getShootBallCD() {
        return 600;
    }

    public int getThrowCarCD() {
        return 800;
    }

    public int getStealPlantCD() {
        return 400;
    }

    public int getAnimShootCD() {
        return 40;
    }

    public int getAnimThrowCD() {
        return 30;
    }

    public int getAnimStealCD() {
        return 30;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("zomboss_state")) {
            this.setRobotState(EdgarStates.values()[compound.getInt("zomboss_state")]);
        }
        if (this.hasCustomName()) {
            this.bossInfo.setName(this.getDisplayName());
        }
        if (compound.contains("zomboss_throw_car_tick")) {
            this.throwCarTick = compound.getInt("zomboss_throw_car_tick");
        }
        if (compound.contains("zomboss_shoot_ball_cd")) {
            this.shootBallTick = compound.getInt("zomboss_shoot_ball_cd");
        }
        if (compound.contains("zomboss_steal_cd")) {
            this.stealPlantTick = compound.getInt("zomboss_steal_cd");
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("zomboss_state", this.getRobotState().ordinal());
        compound.putInt("zomboss_throw_car_tick", this.throwCarTick);
        compound.putInt("zomboss_shoot_ball_cd", this.shootBallTick);
        compound.putInt("zomboss_steal_cd", this.stealPlantTick);
    }

    public void setRobotState(EdgarStates state) {
        this.entityData.set(STATES, state.ordinal());
    }

    public EdgarStates getRobotState() {
        return EdgarStates.values()[this.entityData.get(STATES)];
    }

    @Override
    public Optional<SoundEvent> getSpawnSound() {
        return Optional.ofNullable(SoundRegister.EDGAR_LAUGH.get());
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundRegister.EDGAR_EXPLOSION.get();
    }

    public enum EdgarStates {
        NORMAL,//yellow eyes
        FLAME,//red eyes
        ICE,//blue eyes
        STEAL,
        CAR,
    }

    protected static class EdgarShootBallGoal extends Goal {

        private final EdgarRobotEntity edgarRobot;

        public EdgarShootBallGoal(EdgarRobotEntity edgarRobot){
            this.edgarRobot = edgarRobot;
        }

        @Override
        public boolean canUse() {
            if(-- this.edgarRobot.shootBallTick > 0){
                return false;
            }
            if(this.edgarRobot.getTarget() != null && this.edgarRobot.getRobotState() == EdgarStates.NORMAL){
                return true;
            }
            return false;
        }

        @Override
        public boolean canContinueToUse() {
            return this.edgarRobot.getRobotState() == EdgarStates.FLAME || this.edgarRobot.getRobotState() == EdgarStates.ICE;
        }

        @Override
        public void start() {
            this.edgarRobot.setRobotState(this.edgarRobot.getRandom().nextInt(2) == 0 ? EdgarStates.FLAME : EdgarStates.ICE);
            this.edgarRobot.bossInfo.getPlayers().forEach(p -> {
                PlayerUtil.playClientSound(p, SoundRegister.EDGAR_SHOOT.get());
            });
            this.edgarRobot.setAttackTime(0);
        }

        @Override
        public void tick() {
            this.edgarRobot.setAttackTime(this.edgarRobot.getAttackTime() + 1);
            if(this.edgarRobot.getAttackTime() > this.edgarRobot.getAnimShootCD()){
                this.edgarRobot.shootElementBall();
            }
        }

        @Override
        public void stop() {
            this.edgarRobot.setAttackTime(0);
        }
    }

    protected static class EdgarStealPlantGoal extends Goal {

        private final EdgarRobotEntity edgarRobot;

        public EdgarStealPlantGoal(EdgarRobotEntity edgarRobot){
            this.edgarRobot = edgarRobot;
        }

        @Override
        public boolean canUse() {
            if(-- this.edgarRobot.stealPlantTick > 0){
                return false;
            }
            if(this.edgarRobot.getTarget() != null && this.edgarRobot.getRobotState() == EdgarStates.NORMAL && this.edgarRobot.nearbyPlantCount > 10){
                return true;
            }
            return false;
        }

        @Override
        public boolean canContinueToUse() {
            return this.edgarRobot.getRobotState() == EdgarStates.STEAL;
        }

        @Override
        public void start() {
            this.edgarRobot.setRobotState(EdgarStates.STEAL);
            this.edgarRobot.setAttackTime(0);
        }

        @Override
        public void tick() {
            this.edgarRobot.setAttackTime(this.edgarRobot.getAttackTime() + 1);
            if(this.edgarRobot.getAttackTime() > this.edgarRobot.getAnimStealCD()){
                this.edgarRobot.stealRandomTargets();
            }
        }

        @Override
        public void stop() {
            this.edgarRobot.setAttackTime(0);
        }
    }

    protected static class EdgarThrowCarGoal extends Goal {

        private final EdgarRobotEntity edgarRobot;

        public EdgarThrowCarGoal(EdgarRobotEntity edgarRobot){
            this.edgarRobot = edgarRobot;
        }

        @Override
        public boolean canUse() {
            if(-- this.edgarRobot.throwCarTick > 0){
                return false;
            }
            if(this.edgarRobot.getTarget() != null && this.edgarRobot.getRobotState() == EdgarStates.NORMAL && this.edgarRobot.nearbyPlantCount > 40){
                return true;
            }
            return false;
        }

        @Override
        public boolean canContinueToUse() {
            return this.edgarRobot.getRobotState() == EdgarStates.CAR;
        }

        @Override
        public void start() {
            this.edgarRobot.setRobotState(EdgarStates.CAR);
            this.edgarRobot.setAttackTime(0);
        }

        @Override
        public void tick() {
            this.edgarRobot.setAttackTime(this.edgarRobot.getAttackTime() + 1);
            if(this.edgarRobot.getAttackTime() > this.edgarRobot.getAnimThrowCD()){
                this.edgarRobot.throwDestroyCar();
            }
        }

        @Override
        public void stop() {
            this.edgarRobot.setAttackTime(0);
        }
    }

}

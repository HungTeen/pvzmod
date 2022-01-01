package com.hungteen.pvz.common.entity.zombie.roof;

import com.hungteen.pvz.api.IZombieEntity;
import com.hungteen.pvz.api.enums.BodyType;
import com.hungteen.pvz.common.entity.ai.goal.target.PVZNearestTargetGoal;
import com.hungteen.pvz.common.entity.misc.DestroyCarEntity;
import com.hungteen.pvz.common.entity.misc.ElementBallEntity;
import com.hungteen.pvz.common.entity.misc.ElementBallEntity.ElementTypes;
import com.hungteen.pvz.common.entity.misc.drop.JewelEntity;
import com.hungteen.pvz.common.entity.zombie.base.AbstractBossZombieEntity;
import com.hungteen.pvz.common.entity.zombie.body.ZombieDropBodyEntity;
import com.hungteen.pvz.common.entity.zombie.roof.BungeeZombieEntity.BungeeStates;
import com.hungteen.pvz.common.entity.zombie.roof.BungeeZombieEntity.BungeeTypes;
import com.hungteen.pvz.common.impl.ZombieType;
import com.hungteen.pvz.common.impl.zombie.CustomZombies;
import com.hungteen.pvz.common.impl.zombie.GrassZombies;
import com.hungteen.pvz.common.impl.zombie.PoolZombies;
import com.hungteen.pvz.common.impl.zombie.RoofZombies;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.common.world.data.PVZFlagData;
import com.hungteen.pvz.data.loot.PVZLoot;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Edgar090505Entity extends AbstractBossZombieEntity {

    private static final DataParameter<Integer> STATES = EntityDataManager.defineId(Edgar090505Entity.class, DataSerializers.INT);
    private static final DataParameter<BlockPos> ORIGIN_POS = EntityDataManager.defineId(Edgar090505Entity.class, DataSerializers.BLOCK_POS);
    private static final List<ZombieType> ZOMBIES_1 = Arrays.asList(GrassZombies.NORMAL_ZOMBIE, GrassZombies.NEWSPAPER_ZOMBIE, GrassZombies.SCREENDOOR_ZOMBIE, PoolZombies.SNORKEL_ZOMBIE, PoolZombies.BALLOON_ZOMBIE);
    private static final List<ZombieType> ZOMBIES_2 = Arrays.asList(GrassZombies.CONEHEAD_ZOMBIE, GrassZombies.POLE_ZOMBIE, GrassZombies.DANCING_ZOMBIE, GrassZombies.OLD_ZOMBIE, PoolZombies.JACK_IN_BOX_ZOMBIE, PoolZombies.DIGGER_ZOMBIE, PoolZombies.BALLOON_ZOMBIE, PoolZombies.POGO_ZOMBIE);
    private static final List<ZombieType> ZOMBIES_3 = Arrays.asList(GrassZombies.BUCKETHEAD_ZOMBIE, GrassZombies.FOOTBALL_ZOMBIE, PoolZombies.ZOMBONI, PoolZombies.JACK_IN_BOX_ZOMBIE, PoolZombies.DIGGER_ZOMBIE, RoofZombies.LADDER_ZOMBIE, RoofZombies.CATAPULT_ZOMBIE);
    private static final List<ZombieType> ZOMBIES_4 = Arrays.asList(GrassZombies.FOOTBALL_ZOMBIE, GrassZombies.GIGA_FOOTBALL_ZOMBIE, PoolZombies.ZOMBONI, CustomZombies.LAVA_ZOMBIE, RoofZombies.CATAPULT_ZOMBIE, RoofZombies.GARGANTUAR);
    private static final List<ZombieType> ZOMBIES_5 = Arrays.asList(GrassZombies.GIGA_FOOTBALL_ZOMBIE, GrassZombies.SUNDAY_EDITION_ZOMBIE, CustomZombies.LAVA_ZOMBIE, RoofZombies.CATAPULT_ZOMBIE, RoofZombies.GARGANTUAR, RoofZombies.GIGA_GARGANTUAR);
    private int summonZombieTick = 0;
    public int shootBallCD = 100;
    public int stealPlantCD = 100;

    public Edgar090505Entity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        this.maxDeathTime = 60;
        this.refreshCountCD = 10;
        this.hasDirectDefence = true;
        this.maxZombieSurround = 60;
        this.maxPlantSurround = 60;
        this.kickRange = 6;
        this.setIsWholeBody();
        this.resetShootBallCD();
        this.resetStealCD();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(STATES, ZomBossStates.NORMAL.ordinal());
        this.entityData.define(ORIGIN_POS, BlockPos.ZERO);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 40.0F));
        this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, false, true, ZombieUtil.LITTLE_FAR_TARGET_RANGE, ZombieUtil.HIGH_TARGET_HEIGHT));
    }

    @Override
    protected void updateAttributes() {
        super.updateAttributes();
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0);
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.LITTLE_HIGH);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void zombieTick() {
        super.zombieTick();
        if (this.shootBallCD > 0) {
            --this.shootBallCD;
            --this.stealPlantCD;
        }
        if (!level.isClientSide) {
            if (this.getOriginPos() == BlockPos.ZERO) {
                this.setOriginPos(this.blockPosition());
            } else {
                if (MathUtil.getPosDisToVec(getOriginPos(), position()) >= 10) {
                    final int range = 4;
                    for (int i = -range; i <= range; ++i) {
                        for (int j = -range; j <= range; ++j) {
                            final BlockPos tmp = getOriginPos().offset(i, -1, j);
                            if (level.getBlockState(tmp).isAir()) {
                                level.setBlockAndUpdate(tmp, Blocks.GRASS_BLOCK.defaultBlockState());
                            }
                            for (int k = 0; k <= 10; ++k) {
                                level.setBlockAndUpdate(getOriginPos().offset(i, k, j), Blocks.AIR.defaultBlockState());
                            }
                        }
                    }
                    this.setPos(getOriginPos().getX(), getOriginPos().getY() + 1, getOriginPos().getZ());
                }
            }
        }
    }

    @Override
    public void normalZombieTick() {
        super.normalZombieTick();
        if (!level.isClientSide) {
            ++this.summonZombieTick;
            if (this.summonZombieTick >= this.getSummonZombieCD()) {
                this.summonZombieTick = 0;
                this.summonZombieByBungee();
            }
            if (this.getZomBossState() == ZomBossStates.NORMAL) {
                final int flag = this.getRandom().nextInt(10);
                if (this.tickCount % 100 == 0 && this.nearbyPlantCount >= this.maxPlantSurround && this.random.nextInt(2) == 0) {
                    this.setZomBossState(ZomBossStates.CAR);
                } else if (flag < 4 && this.stealPlantCD <= 0) {
                    this.setZomBossState(ZomBossStates.STEAL);
                    EntityUtil.playSound(this, SoundRegister.DRAG.get());
                    this.resetStealCD();
                } else if (this.shootBallCD <= 0) {// 5 - 10
                    this.setZomBossState(this.getRandom().nextInt(2) == 0 ? ZomBossStates.FLAME : ZomBossStates.ICE);
                    EntityUtil.playSound(this, SoundRegister.ZOMBOSS_SHOOT.get());
                    this.resetShootBallCD();
                }
            } else if (this.getZomBossState() == ZomBossStates.FLAME || this.getZomBossState() == ZomBossStates.ICE) {
                this.setAttackTime(this.getAttackTime() + 1);
                if (this.getAttackTime() >= this.getAnimShootCD()) {
                    this.shootElementBall();
                }
            } else if (this.getZomBossState() == ZomBossStates.CAR) {
                this.setAttackTime(this.getAttackTime() + 1);
                if (this.getAttackTime() >= this.getAnimThrowCD()) {
                    this.throwDestroyCar();
                }
            } else if (this.getZomBossState() == ZomBossStates.STEAL) {
                this.setAttackTime(this.getAttackTime() + 1);
                if (this.getAttackTime() >= this.getAnimThrowCD()) {
                    this.stealRandomTargets();
                }
            }
        }
    }

    /**
     * Skill 1 : Summon Zombie by Bungee
     */
    public void summonZombieByBungee() {
        if (this.nearbyZombieCount >= this.maxZombieSurround) {//too many zombies nearby
            return;
        }
        for (int i = 0; i < this.bossInfo.getPlayers().size() / 2 + this.getRandom().nextInt(2) + 1; ++i) {
            //TODO 召唤蹦极
//            this.getSummonZombie().ifPresent(zombie -> {
//                this.onBossSummon(zombie, blockPosition().above(10));
//                BungeeZombieEntity bungee = EntityRegister.BUNGEE_ZOMBIE.get().create(maxLevel);
//                bungee.setBungeeType(BungeeTypes.SUMMON);
//                bungee.setBungeeState(BungeeStates.DOWN);
//                bungee.setStealTarget(zombie);
//                BlockPos pos = this.getRandom().nextInt(5) == 0 ?
//                        WorldUtil.getSuitableHeightRandomPos(maxLevel, blockPosition(), 20, 40) :
//                        WorldUtil.getSuitableHeightRandomPos(maxLevel, blockPosition(), 3, 8);
//                this.onBossSummon(bungee, pos.above(20));
//                EntityUtil.playSound(bungee, SoundRegister.BUNGEE_SCREAM.get());
//            });
        }
    }

    /**
     * Skill 2 : Shoot ElementBall To Target
     */
    public void shootElementBall() {
        final int now = getBossStage();
        float speed = (now < 2 ? 0.16F : (now < 4 ? 0.2F : 0.25F));
        ElementBallEntity ball = EntityRegister.ELEMENT_BALL.get().create(level);
        ball.summonByOwner(this);
        ball.speed = speed;
        ball.setPos(getX(), getY() + this.getEyeHeight(), getZ());
        ball.setElementBallType(this.getZomBossState() == ZomBossStates.ICE ? ElementTypes.ICE : ElementTypes.FLAME);
        level.addFreshEntity(ball);
        this.setAttackTime(0);
        this.setZomBossState(ZomBossStates.NORMAL);
    }

    /**
     * Skill 3 : throw destroy car to random target
     */
    public void throwDestroyCar() {
        this.setZomBossState(ZomBossStates.NORMAL);
        final int max = Math.max(1, (this.nearbyPlantCount - this.maxPlantSurround) / 5);
        final int throwNum = MathUtil.getRandomMinMax(getRandom(), max / 2 + 1, max + 1);
        final float range = 50;
        List<LivingEntity> list = EntityUtil.getTargetableLivings(this, EntityUtil.getEntityAABB(this, range, range));
        if (list.isEmpty()) {
            return;
        }
        for (int i = 0; i < throwNum; ++i) {
            int pos = this.getRandom().nextInt(list.size());
            DestroyCarEntity car = new DestroyCarEntity(level, this);
            car.summonByOwner(this);
            car.setPos(getX(), getY() + this.getEyeHeight(), getZ());
            car.shootPultBullet(list.get(pos));
            level.addFreshEntity(car);
        }
    }

    /**
     * Skill 4 : if no target, then heal for 100 life per second
     */
    public void checkAndHeal(float percent) {
        if (this.getTarget() == null) {
            this.heal(10);
        }
    }

    /**
     * Skill 6 : Steal Target By Bungee
     */
    public void stealRandomTargets() {
        this.setZomBossState(ZomBossStates.NORMAL);
        if (this.nearbyPlantCount <= 5) {
            return;
        }
        final int now = this.getBossStage();
        final int minCnt = 3 + now / 2;
        final int maxCnt = 3 + now;
        final int cnt = MathUtil.getRandomMinMax(getRandom(), minCnt, maxCnt);
        final float range = 50;
        List<LivingEntity> list = EntityUtil.getTargetableLivings(this, EntityUtil.getEntityAABB(this, range, range));
        if (list.isEmpty()) {
            return; // no target at all
        }
        for (int i = 0; i < cnt; ++i) {
            LivingEntity target = list.get(this.getRandom().nextInt(list.size()));
            BungeeZombieEntity zombie = EntityRegister.BUNGEE_ZOMBIE.get().create(level);
            zombie.setBungeeType(BungeeTypes.STEAL);
            zombie.setStealTarget(target);
            zombie.setBungeeState(BungeeStates.DOWN);
            ZombieUtil.copySummonZombieData(this, zombie);
            EntityUtil.onEntitySpawn(level, zombie, blockPosition().above(18));
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
    public void die(DamageSource cause) {
        super.die(cause);
        if (!level.isClientSide) {
            this.bossInfo.getPlayers().forEach((player) -> {
                CriteriaTriggers.PLAYER_KILLED_ENTITY.trigger(player, this, cause);
            });
            PVZFlagData data = PVZFlagData.getGlobalFlagData(level);
            data.addAdgarDefeatedCount();
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

    @Override
    protected void spawnSpecialDrops() {
        float range = 50;
        int playerCnt = level.getEntitiesOfClass(PlayerEntity.class, EntityUtil.getEntityAABB(this, range, range), (player) -> {
            return PlayerUtil.isPlayerSurvival(player);
        }).size();
        for (int i = 0; i < 3 + 2 * playerCnt; ++i) {
            JewelEntity jewel = EntityRegister.JEWEL.get().create(level);
            EntityUtil.onMobEntityRandomPosSpawn(level, jewel, blockPosition().above(5), 4);
        }
    }

    private Optional<IZombieEntity> getSummonZombie() {
        //TODO 僵王召唤僵尸
//        final int stage = getBossStage();
//        List<ZombieType> summon_zombie_list = new ArrayList<>();
//        if (stage == 1) summon_zombie_list = ZOMBIES_1;
//        else if (stage == 2) summon_zombie_list = ZOMBIES_2;
//        else if (stage == 3) summon_zombie_list = ZOMBIES_3;
//        else if (stage == 4) summon_zombie_list = ZOMBIES_4;
//        else if (stage == 5) summon_zombie_list = ZOMBIES_5;
//        if (summon_zombie_list.isEmpty()) {
//            System.out.println("Error : Wrong Boss Stage !");
//            return Optional.empty();
//        }
//        final int pos = this.getRandom().nextInt(summon_zombie_list.size());
//        final IZombieType type = summon_zombie_list.get(pos);
//        if (type.getEntityType().isPresent()) {
//            return Optional.ofNullable(type.getEntityType().get().create(maxLevel));
//        }
        return Optional.empty();
    }

    public EntitySize getDimensions(Pose poseIn) {
        return EntitySize.scalable(2F, 9F);
    }

    private void resetShootBallCD() {
        int minCD = 600;
        int maxCD = 1000;// 30s ~ 50s
        if (this.getBossStage() > 4) {// 15s ~ 30s
            minCD -= 300;
            maxCD -= 400;
        } else if (this.getBossStage() > 2) {// 20s ~ 40S
            minCD -= 200;
            maxCD -= 200;
        }
        this.shootBallCD = this.getRandom().nextInt(maxCD - minCD + 1) + minCD;
    }

    private void resetStealCD() {
        int now = this.getBossStage();
        int maxCD = (now <= 3 ? 800 : 600); // 40s 30s
        int minCD = (now <= 3 ? 500 : 400); // 25s 20s
        this.stealPlantCD = this.getRandom().nextInt(maxCD - minCD + 1) + minCD;
    }

    public int getBossStage() {
        final float percent = this.bossInfo.getPercent();
        return percent > 4F / 5 ? 1 :
                percent > 3F / 5 ? 2 :
                        percent > 2F / 5 ? 3 :
                                percent > 1F / 5 ? 4 : 5;
    }

    @Override
    public float getLife() {
        return 1000;
    }

    @Override
    public float getExtraLife() {
        return 10000;
    }

    protected int getSummonZombieCD() {
        return 100;
    }

    public int getAnimShootCD() {
        return 40;
    }

    public int getAnimThrowCD() {
        return 30;
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("zomboss_state")) {
            this.setZomBossState(ZomBossStates.values()[compound.getInt("zomboss_state")]);
        }
        if (this.hasCustomName()) {
            this.bossInfo.setName(this.getDisplayName());
        }
        if (compound.contains("zomboss_summon_tick")) {
            this.summonZombieTick = compound.getInt("zomboss_summon_tick");
        }
        if (compound.contains("zomboss_shoot_ball_cd")) {
            this.shootBallCD = compound.getInt("zomboss_shoot_ball_cd");
        }
        if (compound.contains("zomboss_steal_cd")) {
            this.stealPlantCD = compound.getInt("zomboss_steal_cd");
        }
        if (compound.contains("origin_pos")) {
            CompoundNBT nbt = compound.getCompound("origin_pos");
            this.setOriginPos(new BlockPos(nbt.getInt("origin_pos_x"), nbt.getInt("origin_pos_y"), nbt.getInt("origin_pos_z")));
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("zomboss_state", this.getZomBossState().ordinal());
        compound.putInt("zomboss_summon_tick", this.summonZombieTick);
        compound.putInt("zomboss_shoot_ball_cd", this.shootBallCD);
        compound.putInt("zomboss_steal_cd", this.stealPlantCD);
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("origin_pos_x", this.getOriginPos().getX());
        nbt.putInt("origin_pos_y", this.getOriginPos().getY());
        nbt.putInt("origin_pos_z", this.getOriginPos().getZ());
        compound.put("origin_pos", nbt);
    }

    public void setZomBossState(ZomBossStates state) {
        this.entityData.set(STATES, state.ordinal());
    }

    public ZomBossStates getZomBossState() {
        return ZomBossStates.values()[this.entityData.get(STATES)];
    }

    public BlockPos getOriginPos() {
        return this.entityData.get(ORIGIN_POS);
    }

    public void setOriginPos(BlockPos pos) {
        this.entityData.set(ORIGIN_POS, pos);
    }

    @Override
    public Optional<SoundEvent> getSpawnSound() {
        return Optional.ofNullable(SoundRegister.ZOMBOSS_LAUGH.get());
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundRegister.ZOMBOSS_DEATH.get();
    }

    @Override
    protected ResourceLocation getDefaultLootTable() {
        return PVZLoot.EDGAR_090505;
    }

    @Override
    public ZombieType getZombieType() {
        return RoofZombies.EDGAR_090505;
    }

    public static enum ZomBossStates {
        NORMAL,//yellow eyes
        FLAME,//red eyes
        ICE,//blue eyes
        STEAL,
        CAR,
    }

}

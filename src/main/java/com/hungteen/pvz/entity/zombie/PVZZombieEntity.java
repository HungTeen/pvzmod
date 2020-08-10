package com.hungteen.pvz.entity.zombie;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.entity.ai.PVZNearestTargetGoal;
import com.hungteen.pvz.entity.ai.ZombieMeleeAttackGoal;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.ZombieUtil;
import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.interfaces.IPVZZombie;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.GameRules;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public abstract class PVZZombieEntity extends MonsterEntity implements IPVZZombie {

	private static final DataParameter<Integer> ZOMBIE_TYPE = EntityDataManager.createKey(PVZZombieEntity.class,
			DataSerializers.VARINT);
	// 主人ID
	private static final DataParameter<Optional<UUID>> OWNER_UUID = EntityDataManager.createKey(PVZZombieEntity.class,
			DataSerializers.OPTIONAL_UNIQUE_ID);
	// 以下均为僵尸状态
	private static final DataParameter<Boolean> IS_FROZEN = EntityDataManager.createKey(PVZZombieEntity.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_COLD = EntityDataManager.createKey(PVZZombieEntity.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_CHARMED = EntityDataManager.createKey(PVZZombieEntity.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_SMALL = EntityDataManager.createKey(PVZZombieEntity.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_INVIS = EntityDataManager.createKey(PVZZombieEntity.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_BUTTER = EntityDataManager.createKey(PVZZombieEntity.class,
			DataSerializers.BOOLEAN);

	public PVZZombieEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
		super(type, worldIn);
		this.setZombieAttributes();
		this.isImmuneToFire();
	}

	/**
	 * get zombie type : super normal beard
	 */
	protected Type getSpawnType() {
		int t = this.getRNG().nextInt(100);
		if (t <= PVZConfig.COMMON_CONFIG.ENTITY_SETTINGS.zombieSuperChance.get())
			return Type.SUPER;
		return Type.NORMAL;
	}

	protected void setZombieAttributes() {
		this.setZombieMaxHealth(this.getLife());
	}

	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		zombieSpawnInit();
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	/**
	 * 代替鸡肋的onInitialSpawn 利于代码层面的召唤僵尸
	 */
	public void zombieSpawnInit() {
//		OverworldData data = OverworldData.getGlobalData(world);
//		if (data.hasEvent(SpecialEvents.INVIS_ZOMBIE)) {
//			if (this.getCanBeInvis()) {
//				this.setIsInvis(true);
//				this.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 9999999, 0, false, false));// 可随难度而调
//			}
//		}
//		if (data.hasEvent(SpecialEvents.MINI_ZOMBIE)) {
//			if (this.getCanBeSmall()) {
//				this.setIsSmall(true);
//				this.setSize(0.2f, 0.3f);
//				// PacketHandler.CHANNEL.sendToAll(new
//				// PacketSetEntitySize(this.getEntityId(),0.2f,0.3f));
//				this.addPotionEffect(new PotionEffect(MobEffects.SPEED, 9999999, 0, false, false));// 可随难度而调
//				this.addPotionEffect(new PotionEffect(PotionRegister.SMALL_LIFE_EFFECT, 9999999, 4, false, false));// 可随难度而调
//			}
//		}
	}

	@Override
	protected void registerData() {
		super.registerData();
		dataManager.register(ZOMBIE_TYPE, this.getSpawnType().ordinal());
		dataManager.register(OWNER_UUID, Optional.empty());
		dataManager.register(IS_FROZEN, false);
		dataManager.register(IS_COLD, false);
		dataManager.register(IS_CHARMED, false);
		dataManager.register(IS_SMALL, false);
		dataManager.register(IS_INVIS, false);
		dataManager.register(IS_BUTTER, false);
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.LOW);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(ZombieUtil.ZOMBIE_FOLLOW_RANGE);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.NORMAL_SPEED);
		this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1D);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new SwimGoal(this));
		this.goalSelector.addGoal(0, new ZombieMeleeAttackGoal(this, 1.0, false));
		this.targetSelector.addGoal(0, new PVZNearestTargetGoal(this, true, 80, 60));
	}

	/**
	 * 给僵尸设置最大生命值 并回血
	 */
	public void setZombieMaxHealth(float health) {
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(health);
		this.heal(health);
	}

	public int getAttackCD() {
//		if (this.isPotionActive(PotionRegister.COLD_EFFECT)) {
//			int lvl = this.getActivePotionEffect(PotionRegister.COLD_EFFECT).getAmplifier();
//			return 4 * lvl + 10;
//		}
		return 15;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source instanceof PVZDamageSource) {
			this.hurtResistantTime = 0;
		}
		return super.attackEntityFrom(source, amount);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		entityIn.hurtResistantTime = 0;
		//add
		float f = (float) this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
		float f1 = (float) this.getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).getValue();
		if (entityIn instanceof LivingEntity) {
			f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(),
					((LivingEntity) entityIn).getCreatureAttribute());
			f1 += (float) EnchantmentHelper.getKnockbackModifier(this);
		}

		int i = EnchantmentHelper.getFireAspectModifier(this);
		if (i > 0) {
			entityIn.setFire(i * 4);
		}

		boolean flag = entityIn.attackEntityFrom(getZombieAttackDamageSource(), f);
		if (flag) {
			if (f1 > 0.0F && entityIn instanceof LivingEntity) {
				((LivingEntity) entityIn).knockBack(this, f1 * 0.5F,
						(double) MathHelper.sin(this.rotationYaw * ((float) Math.PI / 180F)),
						(double) (-MathHelper.cos(this.rotationYaw * ((float) Math.PI / 180F))));
				this.setMotion(this.getMotion().mul(0.6D, 1.0D, 0.6D));
			}

			if (entityIn instanceof PlayerEntity) {
				PlayerEntity playerentity = (PlayerEntity) entityIn;
				ItemStack itemstack = this.getHeldItemMainhand();
				ItemStack itemstack1 = playerentity.isHandActive() ? playerentity.getActiveItemStack()
						: ItemStack.EMPTY;
				if (!itemstack.isEmpty() && !itemstack1.isEmpty()
						&& itemstack.canDisableShield(itemstack1, playerentity, this)
						&& itemstack1.isShield(playerentity)) {
					float f2 = 0.25F + (float) EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;
					if (this.rand.nextFloat() < f2) {
						playerentity.getCooldownTracker().setCooldown(itemstack.getItem(), 100);
						this.world.setEntityState(playerentity, (byte) 30);
					}
				}
			}

			this.applyEnchantments(this, entityIn);
			this.setLastAttackedEntity(entityIn);
		}
		return flag;
	}

	protected PVZDamageSource getZombieAttackDamageSource() {
		return PVZDamageSource.causeEatDamage(this, this);
	}

	/**
	 * 能否被黄油黏住
	 */
	public boolean getCanBeButter() {
		return true;
	}

	/**
	 * 能否被冰封
	 */
	public boolean getCanBeFrozen() {
		return true;
	}

	/**
	 * 能否变小
	 */
	public boolean getCanBeSmall() {
		return true;
	}

	/**
	 * 能否隐身
	 */
	public boolean getCanBeInvis() {
		return true;
	}

	public Ranks getZombieRank() {
		return ZombieUtil.getZombieRank(getZombieEnumName());
	}

	@Override
	public int getZombieXp() {
		return ZombieUtil.getZombieXp(getZombieEnumName());
	}
	
	@Override
	public void applyEntityCollision(Entity entityIn) {
		if(this.isSleeping()) return;
		if (!this.isRidingSameEntity(entityIn)){
            if (!entityIn.noClip && !this.noClip){
                double d0 = entityIn.getPosX() - this.getPosX();
                double d1 = entityIn.getPosZ() - this.getPosZ();
                double d2 = MathHelper.absMax(d0, d1);
                if (d2 >= 0.009999999776482582D){//collide from out to in,add velocity to out
                    d2 = (double)MathHelper.sqrt(d2);
                    d0 = d0 / d2;
                    d1 = d1 / d2;
                    double d3 = 1.0D / d2;
                    if (d3 > 1.0D){
                        d3 = 1.0D;
                    }
                    d0 = d0 * d3;
                    d1 = d1 * d3;
                    d0 = d0 * 0.05000000074505806D;
                    d1 = d1 * 0.05000000074505806D;
                    d0 = d0 * (double)(1.0F - this.entityCollisionReduction);
                    d1 = d1 * (double)(1.0F - this.entityCollisionReduction);
                    if (!this.isBeingRidden()){
                    	if(!(entityIn instanceof PVZZombieEntity)) {
                            this.addVelocity(-d0, 0.0D, -d1);
                    	}
                    }
                    if (!entityIn.isBeingRidden()){
                    	if(!(entityIn instanceof PVZPlantEntity)) {
                            entityIn.addVelocity(d0, 0.0D, d1);
                    	}
                    }
                }
                else {//collide in body,both add velocity
                	this.addVelocity(this.getRNG().nextFloat()-0.5f, 0, this.getRNG().nextFloat()-0.5f);
                	entityIn.addVelocity(this.getRNG().nextFloat()-0.5f, 0, this.getRNG().nextFloat()-0.5f);
                }
            }
        }
	}
	
	@Override
	protected void collideWithNearbyEntities() {
		List<LivingEntity> list = this.world.getEntitiesWithinAABB(LivingEntity.class, this.getBoundingBox());
		if (!list.isEmpty()){
            int i = this.world.getGameRules().getInt(GameRules.MAX_ENTITY_CRAMMING);
            if (i > 0 && list.size() > i - 1 && this.rand.nextInt(4) == 0){
                int j = 0;
                for (int k = 0; k < list.size(); ++k){
                    if (!((Entity)list.get(k)).isPassenger()){
                        ++j;
                    }
                }
                if (j > i - 1){
                    this.attackEntityFrom(DamageSource.CRAMMING, 6.0F);
                }
            }
            for (int l = 0; l < list.size(); ++l){
                LivingEntity target = list.get(l);
                if(EntityUtil.checkShouldApplyCollision(this, target)) {//can collide with
                    this.collideWithEntity(target);
                }
            }
        }
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("zombie_type", this.getZombieType().ordinal());
		if (this.getOwnerUUID() == null) {
			compound.putString("OwnerUUID", "");
		} else {
			compound.putString("OwnerUUID", this.getOwnerUUID().toString());
		}
		compound.putBoolean("is_zombie_cold", this.getIsCold());
		compound.putBoolean("is_zombie_frozen", this.getIsFrozen());
		compound.putBoolean("is_zombie_butter", this.getIsButter());
		compound.putBoolean("is_zombie_small", this.getIsSmall());
		compound.putBoolean("is_zombie_invis", this.getIsInivs());
		compound.putBoolean("is_zombie_charmed", this.getIsCharmed());
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setZombieType(Type.values()[compound.getInt("zombie_type")]);
		String s;
		if (compound.contains("OwnerUUID", 8)) {
			s = compound.getString("OwnerUUID");
		} else {
			String s1 = compound.getString("Owner");
			s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
		}
		if (!s.isEmpty()) {
			try {
				this.setOwnerUUID(UUID.fromString(s));
			} catch (Throwable var4) {
			}
		}
		this.setIsCold(compound.getBoolean("is_zombie_cold"));
		this.setIsFrozen(compound.getBoolean("is_zombie_frozen"));
		this.setIsButter(compound.getBoolean("is_zombie_butter"));
		this.setIsSmall(compound.getBoolean("is_zombie_small"));
		this.setIsInvis(compound.getBoolean("is_zombie_invis"));
		this.setIsCharmed(compound.getBoolean("is_zombie_charmed"));
	}

	@Nullable
	public UUID getOwnerUUID() {
		return dataManager.get(OWNER_UUID).orElse((UUID) null);
	}

	public void setOwnerUUID(UUID uuid) {
		this.dataManager.set(OWNER_UUID, Optional.ofNullable(uuid));
	}

	public void setIsCharmed(boolean is) {
		dataManager.set(IS_CHARMED, is);
	}

	public void setIsInvis(boolean is) {
		dataManager.set(IS_INVIS, is);
	}

	public void setIsSmall(boolean is) {
		dataManager.set(IS_SMALL, is);
	}

	public void setIsCold(boolean is) {
		dataManager.set(IS_COLD, is);
	}

	public void setIsFrozen(boolean is) {
		dataManager.set(IS_FROZEN, is);
	}

	public void setIsButter(boolean is) {
		dataManager.set(IS_BUTTER, is);
	}

	public void setZombieType(Type type) {
		dataManager.set(ZOMBIE_TYPE, type.ordinal());
	}

	public boolean getIsCharmed() {
		return dataManager.get(IS_CHARMED);
	}

	public boolean getIsInivs() {
		return dataManager.get(IS_INVIS);
	}

	public boolean getIsSmall() {
		return dataManager.get(IS_SMALL);
	}

	public boolean getIsButter() {
		return dataManager.get(IS_BUTTER);
	}

	public boolean getIsCold() {
		return dataManager.get(IS_COLD);
	}

	public boolean getIsFrozen() {
		return dataManager.get(IS_FROZEN);
	}

	public Type getZombieType() {
		return Type.values()[dataManager.get(ZOMBIE_TYPE)];
	}

//	@Override
//	protected SoundEvent getAmbientSound() {
//		return SoundsHandler.ZOMBIE_AMBIENT;
//	}
//
//	@Override
//	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
//		return SoundEvents.ENTITY_ZOMBIE_HURT;
//	}
//
//	@Override
//	protected SoundEvent getDeathSound() {
//		return SoundEvents.ENTITY_ZOMBIE_DEATH;
//	}

	public enum Type {
		NORMAL, // 普通
		SUPER, // 能量豆
		BEARD // 胡子
	}
}

package com.hungteen.pvzmod.entities.zombies.base;

import java.util.List;

import javax.annotation.Nullable;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.damage.PVZDamageType;
import com.hungteen.pvzmod.entities.ai.EntityAIZombieEat;
import com.hungteen.pvzmod.entities.ai.target.PVZAIZombieGlobalTarget;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.entities.plants.common.EntityKernelPult;
import com.hungteen.pvzmod.registry.PotionRegister;
import com.hungteen.pvzmod.util.ConfigurationUtil;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.PlantsUtil;
import com.hungteen.pvzmod.util.PlayerUtil;
import com.hungteen.pvzmod.util.SoundsHandler;
import com.hungteen.pvzmod.util.ZombieUtil;
import com.hungteen.pvzmod.util.enums.Plants;
import com.hungteen.pvzmod.util.enums.Ranks;
import com.hungteen.pvzmod.util.interfaces.IZombie;
import com.hungteen.pvzmod.world.data.OverworldData;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public abstract class EntityZombieBase extends EntityMob implements IZombie{

	// 僵尸种类 普通 能量豆 胡子[成就]
	private static final DataParameter<Integer> ZOMBIE_TYPE = EntityDataManager.createKey(EntityZombieBase.class,
			DataSerializers.VARINT);
	// 主人ID
	private static final DataParameter<String> OWNER_NAME = EntityDataManager.createKey(EntityZombieBase.class,
			DataSerializers.STRING);
	// 以下均为僵尸状态
	private static final DataParameter<Boolean> IS_FROZEN = EntityDataManager.createKey(EntityZombieBase.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_COLD = EntityDataManager.createKey(EntityZombieBase.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_CHARMED = EntityDataManager.createKey(EntityZombieBase.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_SMALL = EntityDataManager.createKey(EntityZombieBase.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_INVIS = EntityDataManager.createKey(EntityZombieBase.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_BUTTER = EntityDataManager.createKey(EntityZombieBase.class,
			DataSerializers.BOOLEAN);

	public EntityZombieBase(World worldIn) {
		super(worldIn);
		this.getType();
		this.setZombieAttributes();
		this.isImmuneToFire = true;
	}
	
	protected void setZombieAttributes()
	{
		this.setZombieMaxHealth(this.getLife());
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(ZOMBIE_TYPE, this.getType().ordinal());
		dataManager.register(OWNER_NAME, "");// 主人ID
		dataManager.register(IS_FROZEN, false);
		dataManager.register(IS_COLD, false);
		dataManager.register(IS_CHARMED, false);
		dataManager.register(IS_SMALL, false);
		dataManager.register(IS_INVIS, false);
		dataManager.register(IS_BUTTER, false);
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(0, new EntityAIZombieEat(this, 1.0, false));
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		initAITargetTask();
	}

	protected void initAITargetTask() {
		this.targetTasks.addTask(2, new PVZAIZombieGlobalTarget(this, 100, 100));
//		this.targetTasks.addTask(0, new PVZAIZombieGlobalTarget(this, EntityCrazyDave.class, 100, 100)); //打戴夫
	}

	/**
	 * 能量豆：疯狂模式 1% 困难模式 2% 普通模式 3% 简单模式4%
	 */
	protected Type getType() {
		int t = this.getRNG().nextInt(100);
		if (t <= 3-ConfigurationUtil.getPVZDifficulty()) 
			return Type.SUPER;
		else
			return Type.NORMAL;
	}

	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		OverworldData data = OverworldData.getGlobalData(world);
//		if(data.hasEvent(SpecialEvents.INVISZOMBIE_DAY)) {
//			if(this.getCanBeInvis()) {
//			    this.setIsInvis(true);
//			    this.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 9999999, 0,false,false));//可随难度而调
//			}
//		}
//		
//		if(data.hasEvent(SpecialEvents.SMALLZOMBIE_DAY)) {
//			if(this.getCanBeSmall()) {
//			    this.setIsSmall(true);
//			    this.setSize(0.2f, 0.3f);
//			    //PacketHandler.CHANNEL.sendToAll(new PacketSetEntitySize(this.getEntityId(),0.2f,0.3f));
//			    this.addPotionEffect(new PotionEffect(MobEffects.SPEED, 9999999, 0,false,false));//可随难度而调
//			    this.addPotionEffect(new PotionEffect(PotionRegister.SMALL_LIFE_EFFECT, 9999999, 4,false,false));//可随难度而调
//			}
//		}
		if (this.getRNG().nextInt(ConfigurationUtil.MainConfig.overworldEvents.invisZombieDayChance) == 0) {
			if (this.getCanBeInvis()) {
				this.setIsInvis(true);
				this.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 9999999, 0, false, false));// 可随难度而调
			}
		}
		if (this.getRNG().nextInt(ConfigurationUtil.MainConfig.overworldEvents.smallZombieDayChance) == 0) {
			if (this.getCanBeSmall()) {
				this.setIsSmall(true);
				this.setSize(0.2f, 0.3f);
				// PacketHandler.CHANNEL.sendToAll(new
				// PacketSetEntitySize(this.getEntityId(),0.2f,0.3f));
				this.addPotionEffect(new PotionEffect(MobEffects.SPEED, 9999999, 0, false, false));// 可随难度而调
				this.addPotionEffect(new PotionEffect(PotionRegister.SMALL_LIFE_EFFECT, 9999999, 4, false, false));// 可随难度而调
			}
		}
		return super.onInitialSpawn(difficulty, livingdata);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		float f = (float) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
		int i = 0;

		if (entityIn instanceof EntityLivingBase) {
			f += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(),
					((EntityLivingBase) entityIn).getCreatureAttribute());
			i += EnchantmentHelper.getKnockbackModifier(this);
		}

		boolean flag = dealAttackDamage(f, entityIn);

		if (flag) {
			if (i > 0 && entityIn instanceof EntityLivingBase) {
				((EntityLivingBase) entityIn).knockBack(this, (float) i * 0.5F,
						(double) MathHelper.sin(this.rotationYaw * 0.017453292F),
						(double) (-MathHelper.cos(this.rotationYaw * 0.017453292F)));
				this.motionX *= 0.6D;
				this.motionZ *= 0.6D;
			}

			int j = EnchantmentHelper.getFireAspectModifier(this);

			if (j > 0) {
				entityIn.setFire(j * 4);
			}

			if (entityIn instanceof EntityPlayer) {
				EntityPlayer entityplayer = (EntityPlayer) entityIn;
				ItemStack itemstack = this.getHeldItemMainhand();
				ItemStack itemstack1 = entityplayer.isHandActive() ? entityplayer.getActiveItemStack()
						: ItemStack.EMPTY;

				if (!itemstack.isEmpty() && !itemstack1.isEmpty()
						&& itemstack.getItem().canDisableShield(itemstack, itemstack1, entityplayer, this)
						&& itemstack1.getItem().isShield(itemstack1, entityplayer)) {
					float f1 = 0.25F + (float) EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

					if (this.rand.nextFloat() < f1) {
						entityplayer.getCooldownTracker().setCooldown(itemstack1.getItem(), 100);
						this.world.setEntityState(entityplayer, (byte) 30);
					}
				}
			}
			this.applyEnchantments(this, entityIn);
		}
		return flag;
	}

	protected boolean dealAttackDamage(float damage, Entity target) {
		return target.attackEntityFrom(PVZDamageSource.causeEatDamage(this, this), damage);
	}

	@Override
	public boolean canBeCollidedWith() {
		return this.getHealth() > 0;
	}

	public void applyEntityCollision(Entity entityIn) {
		if (!this.isRidingSameEntity(entityIn)) {
			if (!entityIn.noClip && !this.noClip) {
				double d0 = entityIn.posX - this.posX;
				double d1 = entityIn.posZ - this.posZ;
				double d2 = MathHelper.absMax(d0, d1);
				if (d2 >= 0.009999999776482582D) {
					d2 = (double) MathHelper.sqrt(d2);
					d0 = d0 / d2;
					d1 = d1 / d2;
					double d3 = 1.0D / d2;

					if (d3 > 1.0D) {
						d3 = 1.0D;
					}

					d0 = d0 * d3;
					d1 = d1 * d3;
					d0 = d0 * 0.05000000074505806D;
					d1 = d1 * 0.05000000074505806D;
					d0 = d0 * (double) (1.0F - this.entityCollisionReduction);
					d1 = d1 * (double) (1.0F - this.entityCollisionReduction);

					if (!this.isBeingRidden()) {
						if (!(entityIn instanceof EntityZombieBase))
							this.addVelocity(-d0, 0.0D, -d1);
					}

					if (!entityIn.isBeingRidden()) {
						if (!(entityIn instanceof EntityPlantBase))
							entityIn.addVelocity(d0, 0.0D, d1);
					}
				} else {
					// System.out.println(entityIn);
					this.addVelocity(this.getRNG().nextFloat() - 0.5f, 0, this.getRNG().nextFloat() - 0.5f);
					entityIn.addVelocity(this.getRNG().nextFloat() - 0.5f, 0, this.getRNG().nextFloat() - 0.5f);
				}
			}
		}
	}

	@Override
	protected void collideWithNearbyEntities() {
		List<Entity> list = this.world.getEntitiesWithinAABB(EntityLiving.class, this.getEntityBoundingBox());
		// (this,
		// this.getEntityBoundingBox(),EntitySelectors.getTeamCollisionPredicate(this));

		if (!list.isEmpty()) {
			int i = this.world.getGameRules().getInt("maxEntityCramming");

			if (i > 0 && list.size() > i - 1 && this.rand.nextInt(4) == 0) {
				int j = 0;

				for (int k = 0; k < list.size(); ++k) {
					if (!((Entity) list.get(k)).isRiding()) {
						++j;
					}
				}

				if (j > i - 1) {
					this.attackEntityFrom(DamageSource.CRAMMING, 6.0F);
				}
			}

			for (int l = 0; l < list.size(); ++l) {
				Entity entity = list.get(l);
				// System.out.println(entity);
				if (EntityUtil.checkShouldApplyCollision(this, entity))
					this.collideWithEntity(entity);
			}
		}
	}

	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if (!this.world.isRemote) {
			if (this.isPotionActive(PotionRegister.FROZEN_EFFECT)) {
				this.setIsFrozen(true);
			} else {
				this.setIsFrozen(false);
			}
			if (this.isPotionActive(PotionRegister.COLD_EFFECT)) {
				this.setIsCold(true);
			} else {
				this.setIsCold(false);
			}
			if (this.isPotionActive(PotionRegister.BUTTER_EFFECT)) {
				this.setIsButter(true);
			} else {
				this.setIsButter(false);
			}
		}
		if (this.getIsSmall()) {
			setSmallSize();
		}
		if (this.getIsFrozen() || this.getIsButter()) {
			return;
		}
//		if(this.ticksExisted%20==0) {
//			System.out.println(this.getIsSmall());
//		}
		this.onNormalZombieUpdate();

	}

	/**
	 * 僵尸的逻辑更新
	 * 冰封时不执行
	 */
	protected void onNormalZombieUpdate() {
		;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ZombieUtil.EAT);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(ZombieUtil.ZOMBIE_FOLLOW_RANGE);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.NORMAL_SPEED);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1D);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger("zombie_type", this.getZombieType().ordinal());
		if (!this.getOwnerName().equals("")) {
			compound.setString("zombie_owner_name", this.getOwnerName());
		}
		compound.setBoolean("is_zombie_cold", this.getIsCold());
		compound.setBoolean("is_zombie_frozen", this.getIsFrozen());
		compound.setBoolean("is_zombie_butter", this.getIsButter());
		compound.setBoolean("is_zombie_small", this.getIsSmall());
		compound.setBoolean("is_zombie_invis", this.getIsInivs());
		compound.setBoolean("is_zombie_charmed", this.getIsCharmed());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compund) {
		super.readEntityFromNBT(compund);
		this.setZombieType(Type.values()[compund.getInteger("zombie_type")]);
		if (compund.hasKey("ownerName")) {
			this.setOwnerName(compund.getString("zombie_owner_name"));
		} else {
			this.setOwnerName("");
		}
		this.setIsCold(compund.getBoolean("is_zombie_cold"));
		this.setIsFrozen(compund.getBoolean("is_zombie_frozen"));
		this.setIsButter(compund.getBoolean("is_zombie_butter"));
		this.setIsSmall(compund.getBoolean("is_zombie_small"));
		this.setIsInvis(compund.getBoolean("is_zombie_invis"));
		this.setIsCharmed(compund.getBoolean("is_zombie_charmed"));
	}

	@Nullable
	public String getOwnerName() {
		return dataManager.get(OWNER_NAME);
	}

	public void setOwnerName(String name) {
		this.dataManager.set(OWNER_NAME, name);
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

	public int getAttackTick() {
		if (this.isPotionActive(PotionRegister.COLD_EFFECT)) {
			int lvl = this.getActivePotionEffect(PotionRegister.COLD_EFFECT).getAmplifier();
			return 4 * lvl + 10;
		}
		return 10;
	}

	public Type getZombieType() {
		return Type.values()[dataManager.get(ZOMBIE_TYPE)];
	}

	//
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsHandler.ZOMBIE_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_ZOMBIE_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_ZOMBIE_DEATH;
	}

	protected void setSmallSize() {
		this.setSize(0.3f, 0.4f);
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		if (cause instanceof PVZDamageSource) {
			Entity entity = cause.getTrueSource();
			if (entity instanceof EntityPlantBase) {
				// 植物击杀僵尸
				if (((EntityPlantBase) entity).getOwnerName() != "") {
					EntityPlayer player = this.world.getPlayerEntityByName(((EntityPlantBase) entity).getOwnerName());
					if (player != null) {
						PlayerUtil.addPlantCardNum(player, ((EntityPlantBase) entity).getPlantEnumName(), 1);
					}
				}
			}
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (!net.minecraftforge.common.ForgeHooks.onLivingAttack(this, source, amount))
			return false;
		if (this.isEntityInvulnerable(source))
			return false;
		if (this.world.isRemote)
			return false;
		this.idleTime = 0;

		if (this.getHealth() <= 0.0F) {
			return false;
		} else if (source.isFireDamage() && this.isPotionActive(MobEffects.FIRE_RESISTANCE)) {
			return false;
		} else {
			float f = amount;
			// 对头盔造成损伤
			if ((source == DamageSource.ANVIL || source == DamageSource.FALLING_BLOCK)
					&& !this.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty()) {
				this.getItemStackFromSlot(EntityEquipmentSlot.HEAD)
						.damageItem((int) (amount * 4.0F + this.rand.nextFloat() * amount * 2.0F), this);
				amount *= 0.75F;
			}
			if (source.getDamageType() == "drown") {
				amount *= 5;
			}
			boolean flag = false;

			this.limbSwingAmount = 1.5F;
			boolean flag1 = true;

			this.lastDamage = amount;
			// 扣血
			this.damageEntity(source, amount);
			if (source instanceof PVZDamageSource) {
				PVZDamageSource src = (PVZDamageSource) source;
				if (src.getTrueSource() instanceof EntityPlantBase) {
					EntityPlantBase entityPlant = (EntityPlantBase) src.getTrueSource();
					Plants plant = entityPlant.getPlantEnumName();
					if (src.damageType == PVZDamageType.SNOW) {// 寒冷攻击
						PotionEffect coldEffect = PlantsUtil.getColdPotionEffect(entityPlant);
						if (coldEffect != null) {
							this.addPotionEffect(coldEffect);
						}
						this.playSound(SoundsHandler.FROZEN_PEA, 0.5f, 1);
					} else if (src.damageType == PVZDamageType.ICE) {// 冰冻攻击
						PotionEffect coldEffect = PlantsUtil.getColdPotionEffect(entityPlant);
						if (coldEffect != null) {
							this.addPotionEffect(coldEffect);
						}
						this.playSound(SoundsHandler.FROZEN_PEA, 0.5f, 1);
						PotionEffect frozenEffect = PlantsUtil.getFrozenPotionEffect(entityPlant);
						if(frozenEffect!=null&&this.getCanBeFrozen()) {
							this.addPotionEffect(frozenEffect);
						}
					} else if (src.damageType == PVZDamageType.FIRE) {
						this.playSound(SoundsHandler.FIRE_PEA, 4, 1);
						if (this.isPotionActive(PotionRegister.COLD_EFFECT))
							this.removePotionEffect(PotionRegister.COLD_EFFECT);
						if (this.isPotionActive(PotionRegister.FROZEN_EFFECT)) {
							this.removePotionEffect(PotionRegister.FROZEN_EFFECT);
						}
					} else if (src.damageType == PVZDamageType.BUTTER) {
						if (this.getCanBeButter()) {
							if (src.getTrueSource() instanceof EntityKernelPult) {
								this.addPotionEffect(((EntityKernelPult) src.getTrueSource()).getButterEffect());
							}
						}
					}
				}
			}
			this.attackedAtYaw = 0.0F;
			Entity entity1 = source.getTrueSource();

			if (entity1 != null) {
				if (entity1 instanceof EntityLivingBase) {
					this.setRevengeTarget((EntityLivingBase) entity1);
				}

				if (entity1 instanceof EntityPlayer) {
					this.recentlyHit = 100;
					this.attackingPlayer = (EntityPlayer) entity1;
				} else if (entity1 instanceof net.minecraft.entity.passive.EntityTameable) {
					net.minecraft.entity.passive.EntityTameable entitywolf = (net.minecraft.entity.passive.EntityTameable) entity1;

					if (entitywolf.isTamed()) {
						this.recentlyHit = 100;
						this.attackingPlayer = null;
					}
				}
			}

			if (flag1) {
				if (flag) {
					this.world.setEntityState(this, (byte) 29);
				} else if (source instanceof EntityDamageSource && ((EntityDamageSource) source).getIsThornsDamage()) {
					this.world.setEntityState(this, (byte) 33);
				} else {
					byte b0;

					if (source == DamageSource.DROWN) {
						b0 = 36;
					} else if (source.isFireDamage()) {
						b0 = 37;
					} else {
						b0 = 2;
					}

					this.world.setEntityState(this, b0);
				}

				if (source != DamageSource.DROWN && (!flag || amount > 0.0F)) {
					this.markVelocityChanged();
				}

				if (entity1 != null) {
					double d1 = entity1.posX - this.posX;
					double d0;

					for (d0 = entity1.posZ - this.posZ; d1 * d1
							+ d0 * d0 < 1.0E-4D; d0 = (Math.random() - Math.random()) * 0.01D) {
						d1 = (Math.random() - Math.random()) * 0.01D;
					}

					this.attackedAtYaw = (float) (MathHelper.atan2(d0, d1) * (180D / Math.PI)
							- (double) this.rotationYaw);
					this.knockBack(entity1, 0.4F, d1, d0);
				} else {
					this.attackedAtYaw = (float) ((int) (Math.random() * 2.0D) * 180);
				}
			}
			if (this.getHealth() <= 0.0F) {
				SoundEvent soundevent = this.getDeathSound();

				if (flag1 && soundevent != null) {
					this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());
				}

				this.onDeath(source);
			} else if (flag1) {
				this.playHurtSound(source);
			}

			boolean flag2 = !flag || amount > 0.0F;

			if (entity1 instanceof EntityPlayerMP) {
				CriteriaTriggers.PLAYER_HURT_ENTITY.trigger((EntityPlayerMP) entity1, this, source, f, amount, flag);
			}

			return flag2;
		}
	}

	/**
	 * 给僵尸设置最大生命值
	 * 并回血
	 */
	public void setZombieMaxHealth(float health) {
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(health);
		this.heal(health);
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

	public Ranks getZombieRank()
	{
		return ZombieUtil.getZombieRank(getZombieEnumName());
	}
	
	@Override
	public int getZombieXp() {
		return ZombieUtil.getZombieXp(getZombieEnumName());
	}
	
	@Override
	protected boolean isValidLightLevel() {
		return true;
	}

	@Override
	public float getBlockPathWeight(BlockPos pos) {
		return 0.5f;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected ResourceLocation getLootTable() {
		// return LootTableHandler.NORMAL_ZOMBIE;
		return null;
	}

	public enum Type {
		NORMAL, // 普通
		SUPER, // 能量豆
		BEARD // 胡子
	}
}

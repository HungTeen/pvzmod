package com.hungteen.pvz.entity.plant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.capability.CapabilityHandler;
import com.hungteen.pvz.capability.player.PlayerDataManager;
import com.hungteen.pvz.entity.ai.PVZLookRandomlyGoal;
import com.hungteen.pvz.entity.plant.enforce.SquashEntity;
import com.hungteen.pvz.entity.plant.magic.CoffeeBeanEntity;
import com.hungteen.pvz.entity.plant.spear.SpikeWeedEntity;
import com.hungteen.pvz.entity.zombie.grassnight.TombStoneEntity;
import com.hungteen.pvz.item.tool.card.PlantCardItem;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.register.ParticleRegister;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Essences;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.enums.Resources;
import com.hungteen.pvz.utils.interfaces.IPVZPlant;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.GameRules;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public abstract class PVZPlantEntity extends CreatureEntity implements IPVZPlant{

	private static final DataParameter<Integer> SUPER_TIME = EntityDataManager.createKey(PVZPlantEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> PLANT_LVL = EntityDataManager.createKey(PVZPlantEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Optional<UUID>> OWNER_UUID = EntityDataManager.createKey(PVZPlantEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
//	private static final DataParameter<Boolean> IS_SUPER_OUT = EntityDataManager.createKey(PVZPlantEntity.class,DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> ATTACK_TIME = EntityDataManager.createKey(PVZPlantEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> GOLD_TIME = EntityDataManager.createKey(PVZPlantEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> BOOST_TIME = EntityDataManager.createKey(PVZPlantEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> IS_CHARMED = EntityDataManager.createKey(PVZPlantEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_GARDEN_PLANT = EntityDataManager.createKey(PVZPlantEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> SLEEP_TIME = EntityDataManager.createKey(PVZPlantEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> LIVE_TICK = EntityDataManager.createKey(PVZPlantEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Float> PUMPKIN_LIFE = EntityDataManager.createKey(PVZPlantEntity.class, DataSerializers.FLOAT);
	protected int weakTime = 0;
	protected boolean isImmuneToWeak = false;
	private final int weakCD = 10;
	private final int weakDamage = 15;
	protected Plants outerPlant = null;
	public boolean canCollideWithPlant = true;
	protected boolean isUpgradePlant = false;
	protected boolean canBeCharmed = true;
	
	public PVZPlantEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.recalculateSize();
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		dataManager.register(SUPER_TIME, 0);
		dataManager.register(PLANT_LVL, 1);
		dataManager.register(OWNER_UUID, Optional.empty());
//		dataManager.register(IS_SUPER_OUT, false);
		dataManager.register(ATTACK_TIME, 0);
		dataManager.register(GOLD_TIME, 0);
		dataManager.register(BOOST_TIME, 0);
		dataManager.register(IS_CHARMED, false);
		dataManager.register(IS_GARDEN_PLANT, false);
		dataManager.register(SLEEP_TIME, 0);
		this.dataManager.register(LIVE_TICK, 0);
		this.dataManager.register(PUMPKIN_LIFE, 0f);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0);
		this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(0);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0d);
        this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1d);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(2, new PVZLookRandomlyGoal(this));
	}
	
	/**
	 * init attributes with plant lvl
	 */
	public void onSpawnedByPlayer(PlayerEntity player, int lvl){
		this.setPlantLvl(lvl);
		this.setOwnerUUID(player.getUniqueID());
//		System.out.println(lvl);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(this.getPlantHealth());
		this.heal(this.getMaxHealth());
	}
	
	@Override
	public float getPlantHealth() {
		int lvl = this.getPlantLvl();
		if(lvl <= 14) {
			return 27.5f + 2.5f * lvl;
		} 
		return 5 * lvl - 10;
	}
	
	@Override
	public void livingTick() {
		super.livingTick();
		if(!this.isAlive()) {
			return ;
		}
		if(this.isGardenPlant()) {
			this.gardenPlantTick();
		}else {
			this.plantBaseTick();
			if(this.canPlantNormalUpdate()) {
			    this.normalPlantTick();
			}
		}
	}
	
	public boolean canPlantNormalUpdate() {
		return !this.isPlantSleeping();
	}
	
	/**
	 * base tick for normal plant
	 */
    protected void plantBaseTick() {
    	//when plant stand on wrong block
		if(!this.world.isRemote && !this.isImmuneToWeak && this.getRidingEntity() == null) {
			if(this.checkNormalPlantWeak() && this.weakTime == 0) {
				this.weakTime = this.weakCD;
				this.attackEntityFrom(PVZDamageSource.causeWeakDamage(this, this), this.weakDamage);
			}
			if(this.weakTime > 0) {
				this.weakTime --;
			}
		}
		//super mode or boost time or sleep time
		if(!this.world.isRemote) {
			//super 
		    if(this.getSuperTime()>0) {
			    this.setSuperTime(this.getSuperTime()-1);
		    }
		    //boost
		    if(this.getBoostTime()>0) {
		    	this.setBoostTime(this.getBoostTime()-1);
		    }
		    //sleep
		    if(this.shouldPlantRegularSleep()) {
		    	if(this.getSleepTime() < 0) {
		    		this.setSleepTime(this.getSleepTime() + 1);
		    	}else {
		    		this.setSleepTime(Math.max(1, this.getSleepTime()));
		    	}
		    }else {
		    	if(this.getSleepTime() > 0) {
		    		this.setSleepTime(this.getSleepTime() - 1);
		    	}
		    }
		}
		//spawn sleep particle
		if(world.isRemote && this.isPlantSleeping() && this.ticksExisted % 20 == 0) {
			world.addParticle(ParticleRegister.SLEEP.get(), this.getPosX(), this.getPosY() + this.getEyeHeight(), this.getPosZ(), 0.05, 0.05, 0.05);
		}
		//max live tick
		this.setLiveTick(this.getLiveTick() + 1);
		if(this.getLiveTick() >= this.getMaxLiveTick()) {//it's time to disappear 
			this.remove();
		}
		//lock the x and z of plant
		if(this.shouldLockXZ()) {
		    if(this.getRidingEntity()!=null) {
		    }else {
			    BlockPos pos=this.getPosition();
		        this.setPosition(pos.getX()+0.5, this.getPosY(), pos.getZ()+0.5);
		    }
		}
    }
    
	/**
	 * tick for garden plant
	 */
	protected void gardenPlantTick(){
	}
	
	/**
	 * tick for normal plant
	 */
	protected void normalPlantTick(){
//		if(!this.world.isRemote&&this.getGoldTime()>0) {
//			Block block =this.world.getBlockState(new BlockPos(posX,posY-1,posZ)).getBlock();
//			int amount=0;
//			if(block == BlockRegister.GOLDENTILE1) {
//				this.setGoldTime(this.getGoldTime()-EntityGoldLeaf.MINUS1);
//				amount=25;
//			}
//			else if(block == BlockRegister.GOLDENTILE2) {
//				this.setGoldTime(this.getGoldTime()-EntityGoldLeaf.MINUS2);
//				amount=35;
//			}
//			else if(block == BlockRegister.GOLDENTILE3) {
//				this.setGoldTime(this.getGoldTime()-EntityGoldLeaf.MINUS3);
//				amount=50;
//			}
//			if(this.getGoldTime()<=0) {
//				this.setGoldTime(EntityGoldLeaf.CD);
//				EntitySun sun=new EntitySun(world, posX, posY+3, posZ, amount);
//				this.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
//				this.world.spawnEntity(sun);
//			}
//		}
	}
	
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if(!worldIn.isRemote()) {
			EntityUtil.playSound(this, this.getSpawnSound());
			this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(this.getPlantHealth());
			this.heal(this.getMaxHealth());
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	
	/**
	 * check if the plant can stand on the current position
	 */
	protected boolean checkNormalPlantWeak(){
		if(this.isImmuneToWeak) {
			return false;
		}
		BlockPos pos = this.getPosition();
		Block upBlock = this.world.getBlockState(pos).getBlock();
		Block downBlock = this.world.getBlockState(pos.down()).getBlock();
		if(upBlock == BlockRegister.LILY_PAD.get()) {
			return false;
		}
		if(this.world.isAirBlock(pos.down()) || downBlock == Blocks.GRASS_BLOCK || downBlock == BlockRegister.LILY_PAD.get()) {
			return false;
		}
		if(this.getPlantEnumName().isShroomPlant) {
			if(downBlock == Blocks.MYCELIUM) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkCanPlantTarget(LivingEntity entity){
		return EntityUtil.checkCanEntityAttack(this, entity);
	}
	
	/**
	 * use for shroom's sleep ,need check for later coffee bean update
	 */
	protected boolean shouldPlantRegularSleep() {
		if(this.getPlantEnumName().isShroomPlant) {
			return world.isDaytime();
		}
		return false;
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(source instanceof PVZDamageSource) {
			this.hurtResistantTime=0;
		}
		amount = this.pumpkinReduceDamage(source, amount);
		return super.attackEntityFrom(source, amount);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		entityIn.hurtResistantTime=0;
		return super.attackEntityAsMob(entityIn);
	}
	
	/**
	 * lock the movement of plant
	 */
	protected boolean shouldLockXZ() {
		return true;
	}
	
	@Override
	public void applyEntityCollision(Entity entityIn) {
		if (!this.isRidingSameEntity(entityIn)){
            if (!entityIn.noClip && !this.noClip){
            	if(entityIn instanceof PVZPlantEntity&&!EntityUtil.checkCanEntityAttack(this, entityIn)) {
            		this.attackEntityFrom(DamageSource.CRAMMING, 6.0F);
            		entityIn.attackEntityFrom(DamageSource.CRAMMING, 6.0F);
            	}
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
                    if (!entityIn.isBeingRidden()){
                        entityIn.addVelocity(d0, 0.0D, d1);
                    }
                }else {
                	if(this instanceof PVZPlantEntity && entityIn instanceof PVZPlantEntity) {
                	    this.attackEntityFrom(DamageSource.CRAMMING, 10);
                	    entityIn.attackEntityFrom(DamageSource.CRAMMING, 10);
                	}
                }
            }
        }
	}
	
	@Override
	public boolean canBePushed() {
		return false;
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
                if(target != this && shouldCollideWithEntity(target)) {//can collide with
                    this.collideWithEntity(target);
                }
            }
        }
	}
	
	/**
	 * common plants collide with common plants,
	 * mobs who target them,tombstone.
	 */
	protected boolean shouldCollideWithEntity(LivingEntity target) {
		if(target instanceof PVZPlantEntity) {
			if(! this.canCollideWithPlant || ! ((PVZPlantEntity) target).canCollideWithPlant) {
				return false;
			}
			if(target instanceof SquashEntity) {
				return !EntityUtil.checkCanEntityAttack(this, target);
			}
			if(target instanceof SpikeWeedEntity) {
				return !EntityUtil.checkCanEntityAttack(this, target);
			}
			return true;
		}
		if(target instanceof MobEntity) {
			if(((MobEntity) target).getAttackTarget() == this) {
				return true;
			}
			if(target instanceof TombStoneEntity) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return this.isPlantImmuneTo(source) && source != DamageSource.OUT_OF_WORLD && !source.isCreativePlayer();
	}
	
	/**
	 * a function that replace isinvulnerable
	 */
	public boolean isPlantImmuneTo(DamageSource source) {
		if(this.isPlantInSuperMode()) {
			return true;
		}
		return false;
	}
	
	@Nullable
	public Plants getUpgradePlantType() {
		return null;
	}
	
	public void onPlantBeCharmed() {
		if(! this.canBeCharmed) return ;
		this.setCharmed(! this.isCharmed());
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("plant_weak_time", this.weakTime);
		compound.putInt("plant_super_time", this.getSuperTime());
		compound.putInt("plant_lvl", this.getPlantLvl());
//		if(!this.getOwnerName().equals(""))
//        {
//            compound.putString("plant_owner_name", this.getOwnerName());
//        }
		if (this.getOwnerUUID() == null) {
	         compound.putString("OwnerUUID", "");
	      } else {
	         compound.putString("OwnerUUID", this.getOwnerUUID().toString());
	      }
//        compound.putBoolean("is_super_out", this.getIsSuperOut());
        compound.putInt("plant_attack_time", this.getAttackTime());
        compound.putInt("plant_gold_time", this.getGoldTime());
        compound.putInt("plant_boost_time", this.getBoostTime());
        compound.putBoolean("is_plant_charmed", this.isCharmed());
        compound.putBoolean("is_garden_plant", this.isGardenPlant());
        compound.putInt("plant_sleep_time", this.getSleepTime());
        compound.putInt("plant_live_tick", this.getLiveTick());
        if(this.outerPlant!=null) {
        	compound.putInt("outer_plant_type", this.outerPlant.ordinal());
        }
        compound.putFloat("pumpkin_life", this.getPumpkinLife());
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.weakTime=compound.getInt("plant_weak_time");
		this.setSuperTime(compound.getInt("plant_super_time"));
		this.setPlantLvl(compound.getInt("plant_lvl"));
//		if (compound.contains("ownerName")){
//            this.setOwnerName(compound.getString("plant_owner_name"));
//        }
//        else {
//        	this.setOwnerName("");
//        }
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
//		this.setIsSuperOut(compound.getBoolean("is_super_out"));
		this.setAttackTime(compound.getInt("plant_attack_time"));
        this.setGoldTime(compound.getInt("plant_gold_time"));
        this.setBoostTime(compound.getInt("plant_boost_time"));
        this.setCharmed(compound.getBoolean("is_plant_charmed"));
        this.setGardenPlant(compound.getBoolean("is_garden_plant"));
        this.setSleepTime(compound.getInt("plant_sleep_time"));
        this.setLiveTick(compound.getInt("plant_live_tick"));
        if(compound.contains("outer_plant_type")) {
        	this.outerPlant = Plants.values()[compound.getInt("outer_plant_type")];
        }
        this.setPumpkinLife(compound.getFloat("pumpkin_life"));
    }
	
	/**
	 * use to start plant super mode 
	 */
	public void startSuperMode(boolean first) {
		this.setSuperTime(this.getSuperTimeLength());
		this.heal(this.getMaxHealth());
		this.setLiveTick(0);
		if(first) {
			if(this.getOwnerUUID() != null) {
			    PlayerEntity player = this.world.getPlayerByUuid(this.getOwnerUUID());
		        if(player != null) {
		    	    PlayerUtil.addPlantXp(player, this.getPlantEnumName(), 5);
		        }
			}
		    if(this.outerPlant == Plants.PUMPKIN) {
			    this.setPumpkinLife(PlantUtil.PUMPKIN_LIFE + PlantUtil.PUMPKIN_SUPER_LIFE);
		    }
		}
	}
	
	/**
	 * pumpkin reduce the hurt damage
	 */
	protected float pumpkinReduceDamage(DamageSource source, float amount) {
		if(!world.isRemote) {
			if(this.outerPlant == Plants.PUMPKIN) {
				if(this.getPumpkinLife() > amount) { // damage pumpkin health first
					this.setPumpkinLife(this.getPumpkinLife() - amount);
					amount = 0;
				}else {
					amount -= this.getPumpkinLife();
					this.setPumpkinLife(0f);
					this.outerPlant = null;
				}
			}
		}
		return amount;
	}
	
	/**
	 * how many tick can plant live
	 */
	public int getMaxLiveTick() {
		int tick = PVZConfig.COMMON_CONFIG.EntitySettings.EntityLiveTick.PlantLiveTick.get();
		return this.isUpgradePlant ? 2 * tick : tick;
	}
	
	public boolean isPlantInSuperMode(){
		return this.getSuperTime() > 0;
	}
	
	/**
	 * check can start super mode currently
	 */
	public boolean canStartSuperMode(){
		return ! this.isPlantSleeping() && this.hasSuperMode() && ! this.isPlantInSuperMode();
	}
	
	private boolean hasSuperMode() {
		return this.getSuperTimeLength()>0;
	}
	
	public boolean isPlantInBoost(){
		return this.getBoostTime()>0;
	}
	
	public int getCoolDownTime(){
		return PlantUtil.getPlantCoolDownTime(getPlantEnumName(),getPlantLvl());
	}
	
	public int getSunCost(){
		return PlantUtil.getPlantSunCost(getPlantEnumName());
	}
	
	@Override
	public Essences getPlantEssenceType() {
		return PlantUtil.getPlantEssenceType(getPlantEnumName());
	}
	
	@Override
	public Ranks getPlantRank(Plants plant) {
		return PlantUtil.getPlantRankByName(plant);
	}
	
	public Plants getOuterPlantType() {
		return this.outerPlant;
	}
	
	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;
	}

	/**
	 * is Plant Sleeping
	 */
	public boolean isPlantSleeping() {
		return this.getSleepTime() > 0;
	}
    
	public void setOuterPlantType(Plants p) {
		this.outerPlant = p;
	}
	
	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand) {
		if(!world.isRemote) {
			if(hand == Hand.MAIN_HAND) {
				ItemStack itemstack = player.getHeldItemMainhand();
				if(itemstack.getItem() instanceof PlantCardItem && ! player.getCooldownTracker().hasCooldown(itemstack.getItem())) {
					PlantCardItem item = (PlantCardItem) itemstack.getItem();
					if(item.getPlant() == Plants.PUMPKIN) {
						if(this.outerPlant != null) {
							if(this.outerPlant == Plants.PUMPKIN && this.getPumpkinLife() < PlantUtil.PUMPKIN_LIFE) {
								PlantCardItem.plantPumpkin(player, this, item, itemstack);
							}
						}else {
//							System.out.println("1");
							PlantCardItem.plantPumpkin(player, this, item, itemstack);
						}
					}else if(item.getPlant() == Plants.COFFEE_BEAN) {
						player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
							PlayerDataManager manager = l.getPlayerData();
							int num = manager.getPlayerStats().getPlayerStats(Resources.SUN_NUM);
							int sunCost = item.getSunCost(player.getHeldItem(hand));
							if(num >= sunCost) {//sun is enough
								CoffeeBeanEntity bean = EntityRegister.COFFEE_BEAN.get().create(world);
								l.getPlayerData().getPlayerStats().addPlayerStats(Resources.SUN_NUM, - sunCost);
								int lvl = manager.getPlantStats().getPlantLevel(Plants.COFFEE_BEAN);
								PlantCardItem.onUsePlantCard(player, player.getHeldItem(hand), item, lvl);
								bean.setPlantLvl(lvl);
								bean.setOwnerUUID(player.getUniqueID());
								EntityUtil.onMobEntitySpawn(world, bean, getPosition().add(0, this.getEyeHeight() + 0.5f, 0));
							}
						});
					}else if(this.getUpgradePlantType() == item.getPlant()) {
						player.getCapability(CapabilityHandler.PLAYER_DATA_CAPABILITY).ifPresent((l)->{
							PlayerDataManager manager = l.getPlayerData();
							int num = manager.getPlayerStats().getPlayerStats(Resources.SUN_NUM);
							int sunCost = item.getSunCost(player.getHeldItem(hand));
							if(num >= sunCost) {//sun is enough
								PVZPlantEntity plant = PlantUtil.getPlantEntityType(item.getPlant()).create(world);
								l.getPlayerData().getPlayerStats().addPlayerStats(Resources.SUN_NUM, - sunCost);
								int lvl = manager.getPlantStats().getPlantLevel(item.getPlant());
								PlantCardItem.onUsePlantCard(player, player.getHeldItem(hand), item, lvl);
								plant.setPlantLvl(lvl);
								plant.setOwnerUUID(player.getUniqueID());
								EntityUtil.onMobEntitySpawn(world, plant, getPosition());
								this.remove();
							}
						});
					}
				}
			}
		}
		return true;
	}
	
	protected boolean isPlantInStage(int stage) {
		int lvl = this.getPlantLvl();
		if(stage == 1) return lvl <= 6;
		if(stage == 2) return 7 <= lvl && lvl <= 13;
		if(stage == 3) return 14 <= lvl && lvl <= 20;
		return false;
	}
	
	public int getBoostTime(){
    	return dataManager.get(BOOST_TIME);
    }
    
    public void setBoostTime(int time){
    	dataManager.set(BOOST_TIME, time);
    }
    
    public boolean isCharmed(){
    	return dataManager.get(IS_CHARMED);
    }
    
    public void setCharmed(boolean is){
    	dataManager.set(IS_CHARMED,is);
    }
    
    public boolean isGardenPlant(){
    	return dataManager.get(IS_GARDEN_PLANT);
    }
    
    public void setGardenPlant(boolean is){
    	dataManager.set(IS_GARDEN_PLANT, is);
    }
    
    public int getGoldTime(){
    	return dataManager.get(GOLD_TIME);
    }
    
    public void setGoldTime(int cd){
    	dataManager.set(GOLD_TIME, cd);
    }
    
    public int getAttackTime(){
    	return dataManager.get(ATTACK_TIME);
    }
    
    public void setAttackTime(int cd){
    	dataManager.set(ATTACK_TIME, cd);
    }
    
    public int getSleepTime(){
    	return dataManager.get(SLEEP_TIME);
    }
    
    public void setSleepTime(int cd){
    	dataManager.set(SLEEP_TIME, cd);
    }
    
	public void setPlantLvl(int lvl){
		dataManager.set(PLANT_LVL, lvl);
	}
	
	public int getPlantLvl(){
		return dataManager.get(PLANT_LVL);
	}
	
	@Nullable
    public UUID getOwnerUUID(){
        return dataManager.get(OWNER_UUID).orElse((UUID)null);
    }

    public void setOwnerUUID(UUID uuid){
        this.dataManager.set(OWNER_UUID, Optional.ofNullable(uuid));
    }
	
    public void setSuperTime(int time){
		dataManager.set(SUPER_TIME, time);
	}
	
	public int getSuperTime(){
		return dataManager.get(SUPER_TIME);
	}
	
	public int getLiveTick() {
		return this.dataManager.get(LIVE_TICK);
	}
	
	public void setLiveTick(int tick) {
		this.dataManager.set(LIVE_TICK, tick);
	}
	
	public float getPumpkinLife()
	{
		return this.dataManager.get(PUMPKIN_LIFE);
	}
	
	public void setPumpkinLife(float life)
	{
		this.dataManager.set(PUMPKIN_LIFE, life);
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundRegister.PLANT_HURT.get();
	}
	
	protected SoundEvent getSpawnSound() {
		return SoundRegister.PLANT_ON_GROUND.get();
	}
	
}

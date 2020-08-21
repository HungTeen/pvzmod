package com.hungteen.pvz.entity.plant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.misc.damage.PVZDamageSource;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Essences;
import com.hungteen.pvz.utils.enums.Plants;
import com.hungteen.pvz.utils.enums.Ranks;
import com.hungteen.pvz.utils.interfaces.IPVZPlant;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.GameRules;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public abstract class PVZPlantEntity extends CreatureEntity implements IPVZPlant{

	protected int weakTime;
	protected boolean isImmuneToWeak;
	private final int weakCD = 10;
	private final int weakDamage = 15;
	private static final DataParameter<Integer> SUPER_TIME = EntityDataManager.createKey(PVZPlantEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> PLANT_LVL = EntityDataManager.createKey(PVZPlantEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Optional<UUID>> OWNER_UUID = EntityDataManager.createKey(PVZPlantEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
//	private static final DataParameter<Boolean> IS_SUPER_OUT = EntityDataManager.createKey(PVZPlantEntity.class,DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> ATTACK_TIME = EntityDataManager.createKey(PVZPlantEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> GOLD_TIME = EntityDataManager.createKey(PVZPlantEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> BOOST_TIME = EntityDataManager.createKey(PVZPlantEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> IS_CHARMED = EntityDataManager.createKey(PVZPlantEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_GARDEN_PLANT = EntityDataManager.createKey(PVZPlantEntity.class, DataSerializers.BOOLEAN);
	
	public PVZPlantEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		this.weakTime=0;
		this.isImmuneToWeak=false;
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
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(this.getLife());
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0d);
        this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1d);
	}
	
	@Override
	protected void registerGoals() {
		
	}
	
	@Override
	public void livingTick() {
		super.livingTick();
		if(!this.isAlive()) return ;
		if(this.getIsGardenPlant()) {
			this.gardenPlantTick();
		}else {
			this.normalPlantTick();
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
		//when plant stand on wrong block
		if(!this.world.isRemote&&!this.isImmuneToWeak) {
			if(this.checkWeak()&&this.weakTime==0) {
				this.weakTime=this.weakCD;
				this.attackEntityFrom(PVZDamageSource.causeWeakDamage(this, this), this.weakDamage);
			}
			if(this.weakTime>0) {
				this.weakTime--;
			}
		}
		if(!this.world.isRemote) {
			//super 
		    if(this.getSuperTime()>0) {
			    this.setSuperTime(this.getSuperTime()-1);
		    }
		    else{
//		    	this.setIsSuperOut(true);
		    }
		    //boost
		    if(this.getBoostTime()>0) {
		    	this.setBoostTime(this.getBoostTime()-1);
		    }
		}
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
		updateAttributes();
		if(!worldIn.isRemote()) {
			this.playSound(SoundEvents.BLOCK_GRASS_PLACE, 1f,1f);
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	protected boolean checkWeak(){
		if(this.isImmuneToWeak) return false;
        return !PlantUtil.checkCanPlantLiveHere(this);
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(source instanceof PVZDamageSource) {
			this.hurtResistantTime=0;
		}
		return super.attackEntityFrom(source, amount);
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		entityIn.hurtResistantTime=0;
		return super.attackEntityAsMob(entityIn);
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
                        entityIn.addVelocity(d0, 0.0D, d1);
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
                if(EntityUtil.checkShouldApplyCollision(this, target)) {//can collide with
                    this.collideWithEntity(target);
                }
            }
        }
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
        compound.putBoolean("is_plant_charmed", this.getIsCharmed());
        compound.putBoolean("is_garden_plant", this.getIsGardenPlant());
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
        this.setIsCharmed(compound.getBoolean("is_plant_charmed"));
        this.setIsGardenPlant(compound.getBoolean("is_garden_plant"));
    }
	
	public void updateAttributes(){
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(this.getLife());
		this.heal(this.getMaxHealth());
	}
	
	public void startSuperMode() {
		this.setSuperTime(this.getSuperTimeLength());
//		this.setIsSuperOut(false);
	}
	
	public boolean isPlantInSuperMode(){
		return this.getSuperTime()>0;
	}
	
	public boolean canStartSuperMode(){
		return this.hasSuperMode()&&!this.isPlantInSuperMode();
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
	
	public float getLife(){
		return PlantUtil.getPlantMaxHealth(getPlantEnumName(),getPlantLvl());
	}
	
	@Override
	public Essences getPlantEssenceType() {
		return PlantUtil.getPlantEssenceType(getPlantEnumName());
	}
	
	@Override
	public Ranks getPlantRank(Plants plant) {
		return PlantUtil.getPlantRankByName(plant);
	}
	
	public float getAttackDamage() {
		return 0;
	}
	
//	@Nullable
//    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
//    {
//        return SoundsHandler.PLANT_HURT;
//    }
	
	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand) {
		return true;
	}
    
	public int getBoostTime(){
    	return dataManager.get(BOOST_TIME);
    }
    
    public void setBoostTime(int time){
    	dataManager.set(BOOST_TIME, time);
    }
    
    public boolean getIsCharmed(){
    	return dataManager.get(IS_CHARMED);
    }
    
    public void setIsCharmed(boolean is){
    	dataManager.set(IS_CHARMED,is);
    }
    
    public boolean getIsGardenPlant(){
    	return dataManager.get(IS_GARDEN_PLANT);
    }
    
    public void setIsGardenPlant(boolean is){
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
    
//	public boolean getIsSuperOut()
//	{
//		return dataManager.get(IS_SUPER_OUT);
//	}
//	
//	public void setIsSuperOut(boolean is)
//	{
//		dataManager.set(IS_SUPER_OUT, is);
//	}
	
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
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundRegister.PLANT_HURT.get();
	}
}

package com.hungteen.pvzmod.entities.plants.base;

import java.util.List;

import javax.annotation.Nullable;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.drops.EntitySun;
import com.hungteen.pvzmod.entities.plants.light.EntityGoldLeaf;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.particles.base.PVZParticleType;
import com.hungteen.pvzmod.registry.BlockRegister;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.PlantsUtil;
import com.hungteen.pvzmod.util.SoundsHandler;
import com.hungteen.pvzmod.util.interfaces.IPlant;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public abstract class EntityPlantBase extends EntityCreature implements IPlant{

	protected int weakTime;//虚弱时间
	protected boolean isImmuneToWeak;//是否免疫虚弱
	private final int weakCD = 10;
	private final int weakDamage = 15;
	//大招时间
	private static final DataParameter<Integer> SUPER_TIME = EntityDataManager.createKey(EntityPlantBase.class, DataSerializers.VARINT);
	//植物等级
	private static final DataParameter<Integer> PLANT_LVL = EntityDataManager.createKey(EntityPlantBase.class, DataSerializers.VARINT);
	//主人ID
	private static final DataParameter<String> OWNER_NAME = EntityDataManager.createKey(EntityPlantBase.class, DataSerializers.STRING);
	//大招放没放，例如大豌豆
	private static final DataParameter<Boolean> IS_SUPER_OUT = EntityDataManager.createKey(EntityPlantBase.class,DataSerializers.BOOLEAN);
	//攻击、运作时间
	private static final DataParameter<Integer> ATTACK_TIME = EntityDataManager.createKey(EntityPlantBase.class, DataSerializers.VARINT);
	//黄金时间、用于黄金瓷砖
	private static final DataParameter<Integer> GOLD_TIME = EntityDataManager.createKey(EntityPlantBase.class, DataSerializers.VARINT);
	//被强化时间、薄荷功效
	private static final DataParameter<Integer> BOOST_TIME = EntityDataManager.createKey(EntityPlantBase.class, DataSerializers.VARINT);
	//是否被魅惑
	private static final DataParameter<Boolean> IS_CHARMED = EntityDataManager.createKey(EntityPlantBase.class, DataSerializers.BOOLEAN);
	//是否为花园植物
	private static final DataParameter<Boolean> IS_GARDEN_PLANT = EntityDataManager.createKey(EntityPlantBase.class, DataSerializers.BOOLEAN);
//	protected static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityPlantBase.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	
	public EntityPlantBase(World worldIn) {
		super(worldIn);
		this.weakTime=0;
		this.isImmuneToWeak=false;
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(SUPER_TIME, 0);//大招时间
		dataManager.register(PLANT_LVL, 1);//植物等级
		dataManager.register(OWNER_NAME, "");//主人ID
//		this.dataManager.register(OWNER_UNIQUE_ID, Optional.absent());
		dataManager.register(IS_SUPER_OUT, false);//大招是否放了，用于投手的大招判断
		dataManager.register(ATTACK_TIME, 0);//攻击时间，一个时间参数，不仅仅用于攻击
		dataManager.register(GOLD_TIME, EntityGoldLeaf.CD);//黄金时间，用于黄金瓷砖
		dataManager.register(BOOST_TIME, 0);
		dataManager.register(IS_CHARMED, false);
		dataManager.register(IS_GARDEN_PLANT, false);
	}
	
	@Override
	protected void applyEntityAttributes()//一般植物1级初始的数据
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(this.getLife());
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0d);
        this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1d);
    }

	@Override
	public void onEntityUpdate()
	{
		super.onEntityUpdate();	
		if(this.getIsGardenPlant()) {//花园植物的处理 1.1更新
			return ;
		}
		this.onNormalPlantUpdate();
	}
	
	/**
	 * 非花园种 植物的实时更新
	 * 请别再使用entityupdate
	 */
	public void onNormalPlantUpdate() {
		//水土不服的虚弱处理
		if(!this.world.isRemote&&!this.isImmuneToWeak) {//不免疫虚弱则下一步
			if(this.checkWeak()&&this.weakTime==0) {
				this.weakTime=this.weakCD;
				this.attackEntityFrom(PVZDamageSource.causeWeakDamage(this, this), this.weakDamage);
			}
			if(this.weakTime>0) {
				this.weakTime--;
			}
		}
		//大招的处理、强化的处理
		if(!this.world.isRemote) {
		    if(this.getSuperTime()>0) {
			    this.setSuperTime(this.getSuperTime()-1);
		    }
		    else{
		    	this.setIsSuperOut(true);
		    }
		    if(this.getBoostTime()>0) {
		    	this.setBoostTime(this.getBoostTime()-1);
		    }
		}
		//黄金瓷砖的处理
		if(!this.world.isRemote&&this.getGoldTime()>0) {
			Block block =this.world.getBlockState(new BlockPos(posX,posY-1,posZ)).getBlock();
			int amount=0;
			if(block == BlockRegister.GOLDENTILE1) {
				this.setGoldTime(this.getGoldTime()-EntityGoldLeaf.MINUS1);
				amount=25;
			}
			else if(block == BlockRegister.GOLDENTILE2) {
				this.setGoldTime(this.getGoldTime()-EntityGoldLeaf.MINUS2);
				amount=35;
			}
			else if(block == BlockRegister.GOLDENTILE3) {
				this.setGoldTime(this.getGoldTime()-EntityGoldLeaf.MINUS3);
				amount=50;
			}
			if(this.getGoldTime()<=0) {
				this.setGoldTime(EntityGoldLeaf.CD);
				EntitySun sun=new EntitySun(world, posX, posY+3, posZ, amount);
				this.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
				this.world.spawnEntity(sun);
			}
		}
//		if(!this.world.isRemote&&this.getOwnerName()!="") {
//			EntityPlayer player=this.world.getPlayerEntityByName(this.getOwnerName());
//			//System.out.println("hhh");
//			if(player!=null) {
//				this.setPlantLvl(PlayerUtil.getPlantLvl(player, this.getPlantEnumName()));
//				//System.out.println(PlayerUtil.getPlantLvl(player, this.getPlantEnumName()));
//			}
//		}
	}
	
	protected boolean checkWeak()//检测是否应该因为离开土壤而虚弱
	{
		if(this.isImmuneToWeak) return false;
		Entity entity =this.getRidingEntity();
		int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor(this.posY-1);
        int k = MathHelper.floor(this.posZ);
    	BlockPos blockpos = new BlockPos(i, j, k);
    	IBlockState iblockstate = world.getBlockState(blockpos);
        Block block = iblockstate.getBlock();
        return !PlantsUtil.checkCanPlantLiveHere(this,this.getIsGardenPlant());
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (!net.minecraftforge.common.ForgeHooks.onLivingAttack(this, source, amount)) return false;
        if (this.isEntityInvulnerable(source))//无敌状态
        {
            return false;
        }
        else if (this.world.isRemote)//只在逻辑端处理
        {
            return false;
        }
        else
        {
            this.idleTime = 0;
            if (this.getHealth() <= 0.0F)
            {
                return false;
            }
            else if (source.isFireDamage() && this.isPotionActive(MobEffects.FIRE_RESISTANCE))
            {
                return false;
            }
            else
            {
                float f = amount;

                if ((source == DamageSource.ANVIL || source == DamageSource.FALLING_BLOCK) && !this.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty())
                {
                    this.getItemStackFromSlot(EntityEquipmentSlot.HEAD).damageItem((int)(amount * 4.0F + this.rand.nextFloat() * amount * 2.0F), this);
                    amount *= 0.75F;
                }

                boolean flag = false;

                this.limbSwingAmount = 1.5F;
                boolean flag1 = true;

                this.lastDamage = amount;
                this.damageEntity(source, amount);

                this.attackedAtYaw = 0.0F;
                Entity entity1 = source.getTrueSource();

                if(entity1 != null)
                {
                    if (entity1 instanceof EntityLivingBase)
                    {
                        this.setRevengeTarget((EntityLivingBase)entity1);
                    }

                    if (entity1 instanceof EntityPlayer)
                    {
                        this.recentlyHit = 100;
                        this.attackingPlayer = (EntityPlayer)entity1;
                    }
                    else if (entity1 instanceof net.minecraft.entity.passive.EntityTameable)
                    {
                        net.minecraft.entity.passive.EntityTameable entitywolf = (net.minecraft.entity.passive.EntityTameable)entity1;
                        if (entitywolf.isTamed())
                        {
                            this.recentlyHit = 100;
                            this.attackingPlayer = null;
                        }
                    }
                }

                if (flag1)
                {
                    if (flag)
                    {
                        this.world.setEntityState(this, (byte)29);
                    }
                    else if (source instanceof EntityDamageSource && ((EntityDamageSource)source).getIsThornsDamage())
                    {
                        this.world.setEntityState(this, (byte)33);
                    }
                    else
                    {
                        byte b0;

                        if (source == DamageSource.DROWN)
                        {
                            b0 = 36;
                        }
                        else if (source.isFireDamage())
                        {
                            b0 = 37;
                        }
                        else
                        {
                            b0 = 2;
                        }

                        this.world.setEntityState(this, b0);
                    }

                    if (source != DamageSource.DROWN && (!flag || amount > 0.0F))
                    {
                        this.markVelocityChanged();
                    }

                    if (entity1 != null)
                    {
                        double d1 = entity1.posX - this.posX;
                        double d0;

                        for (d0 = entity1.posZ - this.posZ; d1 * d1 + d0 * d0 < 1.0E-4D; d0 = (Math.random() - Math.random()) * 0.01D)
                        {
                            d1 = (Math.random() - Math.random()) * 0.01D;
                        }

                        this.attackedAtYaw = (float)(MathHelper.atan2(d0, d1) * (180D / Math.PI) - (double)this.rotationYaw);
                        this.knockBack(entity1, 0.4F, d1, d0);
                    }
                    else
                    {
                        this.attackedAtYaw = (float)((int)(Math.random() * 2.0D) * 180);
                    }
                }

                if (this.getHealth() <= 0.0F)
                {
                        SoundEvent soundevent = this.getDeathSound();

                        if (flag1 && soundevent != null)
                        {
                            this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());
                        }

                        this.onDeath(source);
                }
                else if (flag1)
                {
                    this.playHurtSound(source);
                }

                boolean flag2 = !flag || amount > 0.0F;

                if (entity1 instanceof EntityPlayerMP)
                {
                    CriteriaTriggers.PLAYER_HURT_ENTITY.trigger((EntityPlayerMP)entity1, this, source, f, amount, flag);
                }

                return flag2;
            }
        }
    }
    
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("plant_weak_time", this.weakTime);
        compound.setInteger("plant_lvl", this.getPlantLvl());
        compound.setInteger("plant_super_time", this.getSuperTime());
        compound.setBoolean("is_super_out", this.getIsSuperOut());
        compound.setInteger("plant_attack_time", this.getAttackTime());
        compound.setInteger("plant_gold_time", this.getGoldTime());
        if(!this.getOwnerName().equals(""))
        {
            compound.setString("plant_owner_name", this.getOwnerName());
        }
        compound.setInteger("plant_boost_time", this.getBoostTime());
        compound.setBoolean("is_plant_charmed", this.getIsCharmed());
        compound.setBoolean("is_garden_plant", this.getIsGardenPlant());
//        if (this.getOwnerId() == null)
//        {
//            compound.setString("OwnerUUID", "");
//        }
//        else
//        {
//            compound.setString("OwnerUUID", this.getOwnerId().toString());
//        }
    }
    
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        this.weakTime=compound.getInteger("plant_weak_time");
        this.setPlantLvl(compound.getInteger("plant_lvl"));
        this.setSuperTime(compound.getInteger("plant_super_time"));
        this.setIsSuperOut(compound.getBoolean("is_plant_out"));
        this.setAttackTime(compound.getInteger("plant_attack_time"));
        this.setGoldTime(compound.getInteger("plant_gold_time"));
        if (compound.hasKey("ownerName")){
            this.setOwnerName(compound.getString("plant_owner_name"));
        }
        else {
        	this.setOwnerName("");
        }
        this.setBoostTime(compound.getInteger("plant_boost_time"));
        this.setIsCharmed(compound.getBoolean("is_plant_charmed"));
        this.setIsGardenPlant(compound.getBoolean("is_garden_plant"));
//        String s;
//        if (compound.hasKey("OwnerUUID", 8))
//        {
//            s = compound.getString("OwnerUUID");
//        }
//        else
//        {
//            String s1 = compound.getString("Owner");
//            s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
//        }
//
//        if (!s.isEmpty())
//        {
//            try
//            {
//                this.setOwnerId(UUID.fromString(s));
//            }
//            catch (Throwable var4)
//            {
//            }
//        }
    }
    
    public int getBoostTime()
    {
    	return dataManager.get(BOOST_TIME);
    }
    
    public void setBoostTime(int time)
    {
    	dataManager.set(BOOST_TIME, time);
    }
    
    public boolean getIsCharmed()
    {
    	return dataManager.get(IS_CHARMED);
    }
    
    public void setIsCharmed(boolean is)
    {
    	dataManager.set(IS_CHARMED,is);
    }
    
    public boolean getIsGardenPlant()
    {
    	return dataManager.get(IS_GARDEN_PLANT);
    }
    
    public void setIsGardenPlant(boolean is)
    {
    	dataManager.set(IS_GARDEN_PLANT, is);
    }
    
    public int getGoldTime()
    {
    	return dataManager.get(GOLD_TIME);
    }
    
    public void setGoldTime(int cd)
    {
    	dataManager.set(GOLD_TIME, cd);
    }
    
    public int getAttackTime()
    {
    	return dataManager.get(ATTACK_TIME);
    }
    
    public void setAttackTime(int cd)
    {
    	dataManager.set(ATTACK_TIME, cd);
    }
    
	public boolean getIsSuperOut()
	{
		return dataManager.get(IS_SUPER_OUT);
	}
	
	public void setIsSuperOut(boolean is)
	{
		dataManager.set(IS_SUPER_OUT, is);
	}
	
	public void setPlantLvl(int lvl)
	{
		dataManager.set(PLANT_LVL, lvl);
	}
	
	public int getPlantLvl()
	{
		return dataManager.get(PLANT_LVL);
	}
	
	@Nullable
    public String getOwnerName()
    {
        return dataManager.get(OWNER_NAME);
    }

    public void setOwnerName(String name)
    {
        this.dataManager.set(OWNER_NAME, name);
    }
	
    public void setSuperTime(int time)
	{
		dataManager.set(SUPER_TIME, time);
	}
	
	public int getSuperTime()
	{
		return dataManager.get(SUPER_TIME);
	}
	
//	@Nullable
//    public UUID getOwnerId()
//    {
//        return (UUID)((Optional)this.dataManager.get(OWNER_UNIQUE_ID)).orNull();
//    }
//
//    public void setOwnerId(@Nullable UUID p_184754_1_)
//    {
//        this.dataManager.set(OWNER_UNIQUE_ID, Optional.fromNullable(p_184754_1_));
//    }
	
	public void updateAttributes()
	{
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(this.getLife());
		this.heal(this.getMaxHealth());
		//System.out.println(this.getHealth());
	}
	
	@Override
	public boolean canBePushed() {
		return false;
	}
	
	@Override
	public void applyEntityCollision(Entity entityIn)
    {
        if (!this.isRidingSameEntity(entityIn))
        {
            if (!entityIn.noClip && !this.noClip)
            {
                double d0 = entityIn.posX - this.posX;
                double d1 = entityIn.posZ - this.posZ;
                double d2 = MathHelper.absMax(d0, d1);
                if (d2 >= 0.009999999776482582D)
                {
                    d2 = (double)MathHelper.sqrt(d2);
                    d0 = d0 / d2;
                    d1 = d1 / d2;
                    double d3 = 1.0D / d2;

                    if (d3 > 1.0D)
                    {
                        d3 = 1.0D;
                    }

                    d0 = d0 * d3;
                    d1 = d1 * d3;
                    d0 = d0 * 0.05000000074505806D;
                    d1 = d1 * 0.05000000074505806D;
                    d0 = d0 * (double)(1.0F - this.entityCollisionReduction);
                    d1 = d1 * (double)(1.0F - this.entityCollisionReduction);

                    if (!this.isBeingRidden())
                    {
                    	if(!(entityIn instanceof EntityZombieBase))
                        this.addVelocity(-d0, 0.0D, -d1);
                    }

                    if (!entityIn.isBeingRidden())
                    {
                        entityIn.addVelocity(d0, 0.0D, d1);
                    }
                }
                else {
                	//System.out.println(entityIn);
                	this.addVelocity(this.getRNG().nextFloat()-0.5f, 0, this.getRNG().nextFloat()-0.5f);
                	entityIn.addVelocity(this.getRNG().nextFloat()-0.5f, 0, this.getRNG().nextFloat()-0.5f);
                }
            }
        }
    }
	
	@Override
	protected void collideWithNearbyEntities() {
		List<Entity> list = this.world.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox());
		if (!list.isEmpty())
        {
            int i = this.world.getGameRules().getInt("maxEntityCramming");

            if (i > 0 && list.size() > i - 1 && this.rand.nextInt(4) == 0)
            {
                int j = 0;

                for (int k = 0; k < list.size(); ++k)
                {
                    if (!((Entity)list.get(k)).isRiding())
                    {
                        ++j;
                    }
                }

                if (j > i - 1)
                {
                    this.attackEntityFrom(DamageSource.CRAMMING, 6.0F);
                }
            }

            for (int l = 0; l < list.size(); ++l)
            {
                Entity entity = list.get(l);
                //System.out.println(entity);
                if(EntityUtil.checkShouldApplyCollision(this, entity)) {
                    this.collideWithEntity(entity);
                }
            }
        }
	}
	
	public void startSuperMode() {
		this.setSuperTime(this.getSuperTimeLength());
		this.setIsSuperOut(false);
		for(int i=1;i<=10;i++) {
		    Main.proxy.spawnParticle(PVZParticleType.SUPER_PLANT_FOOD, posX, posY, posZ, (this.rand.nextFloat()-0.5f)/4, 0.15f, (this.rand.nextFloat()-0.5f)/4);
		}
	}
	
	public boolean isPlantInSuperMode()
	{
		return this.getSuperTime()>0;
	}
	
	public boolean canStartSuperMode()
	{
		return !this.isPlantInSuperMode();
	}
	
	public boolean isPlantInBoost()
	{
		return this.getBoostTime()>0;
	}
	
	public int getCoolDownTime()
	{
		return PlantsUtil.getPlantCoolDownTime(getPlantEnumName(),getPlantLvl());
	}
	
	public int getSunCost()
	{
		return PlantsUtil.getPlantSunCost(getPlantEnumName());
	}
	
	public float getLife()//一般植物的生命
	{
		return PlantsUtil.getPlantMaxHealth(getPlantEnumName(),getPlantLvl());
	}
	
	public float getAttackDamage() {
		return 0;
	}
	
	//声音
	@Nullable
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundsHandler.PLANT_HURT;
    }
	
	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		return true;
	}
}

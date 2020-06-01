package com.hungteen.pvzmod.entities.bullets;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.drops.EntityCoin;
import com.hungteen.pvzmod.entities.drops.EntityPlantFood;
import com.hungteen.pvzmod.entities.drops.EntitySun;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.entities.plants.fight.EntitySpikeWeed;
import com.hungteen.pvzmod.entities.plants.flame.EntityTorchWood;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.particles.base.PVZParticleType;
import com.hungteen.pvzmod.util.ConfigurationUtil;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.SoundsHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityPea extends PVZThrowable{

	private static final DataParameter<Integer> PEA_STATE =EntityDataManager.createKey(EntityPea.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> PEA_TYPE =EntityDataManager.createKey(EntityPea.class, DataSerializers.VARINT);
	private EntityTorchWood torchWood=null;
	
	public EntityPea(World worldIn) {
		super(worldIn);
	}
	
	public EntityPea(World worldIn, EntityLivingBase throwerIn,Type type,State state)
    {
        super(worldIn, throwerIn);
        this.setPeaState(state);
        this.setPeaType(type);
        //System.out.println(type);
        if(type==Type.NORMAL) {
        	this.setSize(0.25f, 0.25f);
        }
        else if(type==Type.BIG) {
        	this.setSize(0.4f, 0.4f);
        }
        else if(type==Type.HUGE) {
        	this.setSize(0.6f, 0.6f);
        }
    }
	
	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(PEA_STATE, State.NORMAL.ordinal());
		dataManager.register(PEA_TYPE, Type.NORMAL.ordinal());
	}
    
    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
        compound.setInteger("peaState", this.getPeaState().ordinal());
        compound.setInteger("peaType", this.getPeaType().ordinal());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compund)
    {
        super.readEntityFromNBT(compund);
        this.setPeaState(State.values()[compund.getInteger("peaState")]);
        this.setPeaType(Type.values()[compund.getInteger("peaType")]);
    }

    public static void registerFixesPea(DataFixer fixer)
    {
        EntityThrowable.registerFixesThrowable(fixer, "Pea");
    }
    
    @Override
    public void onUpdate() {
    	super.onUpdate();
    	this.playParticle();
    }
    
    private void playParticle()
    {
    	if(this.shooter instanceof EntityPlantBase) {
    		int lvl=((EntityPlantBase) this.shooter).getPlantLvl();
    		if(lvl>4&&lvl<=12) {
    			Main.proxy.spawnParticle(PVZParticleType.SPEED_PEA_ONE, this.prevPosX, this.prevPosY, this.prevPosZ, 0,0,0);
    		}
    		else if(lvl>=13&&lvl<=20) {
    			Main.proxy.spawnParticle(PVZParticleType.SPEED_PEA_TWO, this.prevPosX, this.prevPosY, this.prevPosZ,0,0,0);
    		}
    	}
    }
    
	@Override
	protected void onImpact(RayTraceResult result){
		Entity target=result.entityHit;
        if (checkCanAttack(target))
        {
        	if(this.getPeaState()==State.NORMAL) {
        		//System.out.println(this.getThrower());
                target.attackEntityFrom(PVZDamageSource.causeNormalDamage(this, this.getThrower()), this.getAttackDamage());//damage
        	}
        	else if(this.getPeaState()==State.SNOW) {
        		if(target instanceof EntityZombieBase) {
        			if(!this.world.isRemote&&!((EntityZombieBase) target).getIsCold()) {
						this.world.playSound(null, this.posX, this.posY, this.posZ, SoundsHandler.FROZEN_PEA, SoundCategory.VOICE, 4, 1);
        			}
        		}
        		target.attackEntityFrom(PVZDamageSource.causeSnowDamage(this, this.getThrower()), this.getAttackDamage());//damage
        	}
        	else if(this.getPeaState()==State.FIRE||this.getPeaState()==State.BLUE_FIRE) {
        		if(!this.world.isRemote) {
					this.world.playSound(null, this.posX, this.posY, this.posZ, SoundsHandler.FIRE_PEA, SoundCategory.VOICE, 4, 1);
        		}
        		target.attackEntityFrom(PVZDamageSource.causeFireDamage(this, this.getThrower()), this.getAttackDamage());//damage
        	}
//        	else if(this.getPeaState()==State.ELECTRICITY) {
//        		mob.attackEntityFrom(PVZDamageSource.causeElectricityDamage(this, this.getThrower()), this.getAttackDamage());//damage
//        	}
        }
        if(!this.world.isRemote&&target instanceof EntityTorchWood) {//»ð¾æÊ÷×®ºÍÍã¶¹µÄ»¥¶¯
        	EntityTorchWood tmp=(EntityTorchWood) target;
        	if(this.torchWood==null||!this.torchWood.isEntityEqual(tmp)) {//ÊÇ·ñÎªÖ®Ç°ÄÇ¸ö»ð¾æÊ÷×®[·ÀÖ¹Ò»¸ö»ð¾æÊ÷×®»¥¶¯Á½´Î]
        		this.torchWood=tmp;
        	    if(this.torchWood.isPlantInSuperMode()) {//À¶»ð
        		    if(this.getPeaState()==State.SNOW) {//±ùÍã¶¹±ä»ðÍã¶¹
        			    this.setPeaState(State.FIRE);
        		    }
        		    else if(this.getPeaState().ordinal()<State.BLUE_FIRE.ordinal()) {//Íã¶¹¡¢»ðÍã¶¹±äÀ¶ÑæÍã¶¹
        			    this.setPeaState(State.BLUE_FIRE);
        		    }
        	    }
        	    else {//»Æ»ð
        		    if(this.getPeaState()==State.SNOW) {
        			    this.setPeaState(State.NORMAL);
        		    }
        		    else if(this.getPeaState()==State.NORMAL) {
        			    this.setPeaState(State.FIRE);
        		    }
        	    }
        	}
        }
        if (!this.world.isRemote)
        {
//        	if(result.entityHit instanceof Entity) {
//        		System.out.println(result.entityHit);
//        	}
            this.world.setEntityState(this, (byte)3);
            if(!this.checkLive(result)) this.setDead();
        }
	}
	
	@Override
	protected boolean checkCanAttack(Entity target) {
		if(target instanceof EntityTorchWood) {
			return false;
		}
		return super.checkCanAttack(target);
	}
	
	private float getAttackDamage()
	{
		float damage=0;
		
		//ÉËº¦ÏÈ¿´Ö÷ÈË
		if(this.shooter instanceof EntityPlantBase) {
			damage=((EntityPlantBase) this.shooter).getAttackDamage();
		}
		else if(this.shooter instanceof EntityZombieBase) {//Ö²Îï½©Ê¬µÄ¹¥»÷£¬ºóÆÚÒªÔö¼Ó£¡
			damage=3;
		}
		else if(this.shooter instanceof EntityPlayer) {
			damage=4;
		}
		
		//È»ºó´óÐ¡ÅÐ¶Ï
		if(this.getPeaType()==Type.BIG) {
			damage+=20f;
		}
		else if(this.getPeaType()==Type.HUGE) {
			damage+=75f;
		}
		
		//È»ºó»ðÑæ·­±¶
		if(this.getPeaState()==State.FIRE) {
			damage*=2;
		}
		else if(this.getPeaState()==State.BLUE_FIRE) {
			damage*=3;
		}
		return damage;
	}
	
	public State getPeaState()
	{
		return State.values()[dataManager.get(PEA_STATE)];
	}
	
	public void setPeaState(State state)
	{
		dataManager.set(PEA_STATE, state.ordinal());
	}
	
	public Type getPeaType()
	{
		return Type.values()[dataManager.get(PEA_TYPE)];
	}
	
	public void setPeaType(Type type)
	{
		dataManager.set(PEA_TYPE, type.ordinal());
	}
	
	@SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
//        if (id == 3)
//        {
//            for (int i = 0; i < 4; ++i)
//            {
//            	if(this.getPeaState()==State.NORMAL) {
//                    this.world.spawnParticle(EnumParticleTypes.SLIME, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
//            	}
//            	else if(this.getPeaState()==State.FIRE) {
//            		this.world.spawnParticle(EnumParticleTypes.FLAME, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
//            	}
//            	else{
//            		this.world.spawnParticle(EnumParticleTypes.SLIME, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
//            	}
//            }
//        }
    }
	
	@Override
	protected float getGravityVelocity()
    {
        return 0.003F;
    }
	
	public enum Type{
		NORMAL,//Ò»°ã
		BIG,//´óÍã¶¹
		HUGE,//¾Þ´óÍã¶¹
	}
	
	public enum State{
		SNOW,//±ùÍã¶¹
		NORMAL,//Ò»°ãÍã¶¹
		FIRE,//»ðÍã¶¹
		BLUE_FIRE,//À¶»ðÍã¶¹
		ELECTRICITY,//µçÍã¶¹
	}
}

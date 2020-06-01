package com.hungteen.pvzmod.entities.zombies.night;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.entities.zombies.special.EntityNormalDefence;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.SoundsHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class EntityPaperZombie extends EntityZombieBase implements IEntityMultiPart{

	private static final DataParameter<Boolean> IS_ANGRY =EntityDataManager.createKey(EntityPaperZombie.class, DataSerializers.BOOLEAN);
	public EntityNormalDefence paper = new EntityNormalDefence(this, "paper", 1f, 1.5f);
	
	public EntityPaperZombie(World worldIn) {
		super(worldIn);
		this.setSize(0.8f, 2.0f);
	}
	
	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(IS_ANGRY, false);
	}
	
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(EntityUtil.SLOW_WALK);
    }
	
	@Override
	protected void setSmallSize() {
		this.setSize(0.3f, 0.4f);
		this.paper.setSmallSize(0.3f, 0.5f);
	}
	
	protected float getDefenceDis()
	{
		if(this.getIsSmall()) return 0.2f;
		else return 0.7f;
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(this.getHealth()<=this.getAngryLife()&&!this.getIsAngry()) {
			this.world.playSound(null, this.posX, this.posY, this.posZ, SoundsHandler.PAPER_OUT,SoundCategory.VOICE,4f, 1f);
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(this.getAngrySpeed());
			this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(this.getAngryDamage());
			this.world.playSound(null, this.posX, this.posY, this.posZ, SoundsHandler.PAPER_ANGRY,SoundCategory.VOICE,4f, 1f);
			this.setIsAngry(true);
		}
		this.paper.onUpdate();
		float j=2*3.14159f*this.rotationYaw/360;
		//System.out.println(j+" "+MathHelper.sin(j));
		float dis=this.getDefenceDis();
		this.paper.setLocationAndAngles(this.posX-Math.sin(j)*dis, this.posY+0.2f, this.posZ+Math.cos(j)*dis, this.rotationYaw, this.rotationPitch);
	}
	
	protected float getAngryLife()
	{
		return 10;
	}
	
	protected float getAngryDamage()
	{
		return 20;
	}
	
	protected float getAngrySpeed()
	{
		return EntityUtil.POLE_SPEED;
	}
	
	public void setIsAngry(boolean is)
	{
		dataManager.set(IS_ANGRY, is);
	}
	
	public boolean getIsAngry()
	{
		return dataManager.get(IS_ANGRY);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compund) {
		super.readEntityFromNBT(compund);
		this.setIsAngry(compund.getBoolean("is_paper_out"));
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean("is_paper_out", this.getIsAngry());
	}

	@Override
	public Entity[] getParts() {
		return new Entity[] {this.paper};
	}
	
	@Override
	public World getWorld() {
		return this.world;
	}

	@Override
	public boolean attackEntityFromPart(MultiPartEntityPart dragonPart, DamageSource source, float damage) {
		return this.attackEntityFrom(PVZDamageSource.causeNormalDamage(source.getImmediateSource(), source.getTrueSource()), damage);
	}
}

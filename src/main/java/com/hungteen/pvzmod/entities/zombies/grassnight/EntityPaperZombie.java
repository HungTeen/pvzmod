package com.hungteen.pvzmod.entities.zombies.grassnight;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.entities.zombies.special.EntityNormalDefence;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.SoundsHandler;
import com.hungteen.pvzmod.util.ZombieUtil;
import com.hungteen.pvzmod.util.enums.Zombies;

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
	public float getLife() {
		return 36;
	}
	
	@Override
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ZombieUtil.LITTLE_SLOW);
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
		this.paper.onUpdate();
		float j=2*3.14159f*this.rotationYaw/360;
		//System.out.println(j+" "+MathHelper.sin(j));
		float dis=this.getDefenceDis();
		this.paper.setLocationAndAngles(this.posX-Math.sin(j)*dis, this.posY+0.2f, this.posZ+Math.cos(j)*dis, this.rotationYaw, this.rotationPitch);
	}
	
	@Override
	protected void onNormalZombieUpdate() {
		super.onNormalZombieUpdate();
		if(this.getHealth()<=this.getMaxHealth()/3&&!this.getIsAngry()) {
			this.playSound(SoundsHandler.PAPER_OUT,4f, 1f);
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(this.getAngrySpeed());
			this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(this.getAngryDamage());
			this.playSound(SoundsHandler.PAPER_ANGRY,4f, 1f);
			this.setIsAngry(true);
		}
	}
	
	protected float getAngryDamage()
	{
		return ZombieUtil.BEAT;
	}
	
	protected float getAngrySpeed()
	{
		return ZombieUtil.BIT_FAST;
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

	@Override
	public Zombies getZombieEnumName() {
		return Zombies.PAPER_ZOMBIE;
	}
}

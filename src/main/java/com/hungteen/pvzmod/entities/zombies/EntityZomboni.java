package com.hungteen.pvzmod.entities.zombies;

import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.entities.zombies.special.EntityNormalDefence;
import com.hungteen.pvzmod.registry.PotionRegister;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.SoundsHandler;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityZomboni extends EntityZombieBase implements IEntityMultiPart{

	public EntityNormalDefence car =new EntityNormalDefence(this, "car", 1.2f, 1.5f);
	
	public EntityZomboni(World worldIn) {
		super(worldIn);
		this.setSize(1f, 2.0f);
	}
	
	protected boolean dealAttackDamage(float damage,Entity target)
	{
		if(target instanceof EntityPlayer) damage=20;
		return target.attackEntityFrom(PVZDamageSource.causeDeadDamage(this, this), damage);
	}
	
	@Override
	protected void setSmallSize() {
		this.setSize(0.4f, 0.5f);
		this.car.setSmallSize(0.4f, 0.4f);
	}
	
	protected float getDefenceDis()
	{
		if(this.getIsSmall()) return 0.5f;
		else return 1.7f;
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(140);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(EntityUtil.ZOMBIE_CAR_DAMAGE);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(EntityUtil.FLAG_SPEED);
	}
	
	@Override
	public void onEntityUpdate() {
		super.onEntityUpdate();
		if(!this.world.isRemote) {
			BlockPos pos=new BlockPos(this);
		    if (this.world.getBlockState(pos).getMaterial() == Material.AIR && this.world.getBiome(pos).getTemperature(pos) < 0.8F && Blocks.SNOW_LAYER.canPlaceBlockAt(this.world, pos))
            {
                this.world.setBlockState(pos, Blocks.SNOW_LAYER.getDefaultState());
            }
		}
		if(this.getHealth()<=30) {
			for(int i=1;i<=3;i++) {
			this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, (this.getRNG().nextFloat()-0.5)/10,0.05,(this.getRNG().nextFloat()-0.5)/10);
			}
		}
		this.car.onUpdate();
		float j=2*3.14159f*this.rotationYawHead/360;
		this.rotationYaw=this.rotationYawHead;
		//System.out.println(j+" "+MathHelper.sin(j));
		float dis=this.getDefenceDis();
		this.car.setLocationAndAngles(this.posX-Math.sin(j)*dis, this.posY+0.2f, this.posZ+Math.cos(j)*dis, this.rotationYaw, this.rotationPitch);
	}
	
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		if(!this.world.isRemote) {
			this.world.playSound(null, new BlockPos(this), SoundsHandler.CAR_START, SoundCategory.VOICE, 4f, 1f);
		}
		return super.onInitialSpawn(difficulty, livingdata);
	}
	
	@Override
	protected void onDeathUpdate() {
		super.onDeathUpdate();
		if(this.deathTime==1) {
			if(!this.world.isRemote) {
				this.world.playSound(null, this.posX, this.posY, this.posZ, SoundsHandler.CAR_EXPLOSION, SoundCategory.VOICE, 4f, 1f);
			}
			this.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.posX, this.posY+1, this.posZ, 0,0,0);
		}
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundsHandler.METAL_HURT;
	}

	@Override
	public World getWorld() {
		return this.world;
	}

	@Override
	public Entity[] getParts() {
		return new Entity[] {this.car};
	}
	
	@Override
	public boolean attackEntityFromPart(MultiPartEntityPart dragonPart, DamageSource source, float damage) {
		return this.attackEntityFrom(PVZDamageSource.causeNormalDamage(source.getImmediateSource(), source.getTrueSource()), damage);
	}
}

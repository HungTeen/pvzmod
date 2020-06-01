package com.hungteen.pvzmod.entities.plants.ice;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.damage.PVZDamageType;
import com.hungteen.pvzmod.entities.plants.base.EntityShroomBase;
import com.hungteen.pvzmod.particles.base.PVZParticleType;
import com.hungteen.pvzmod.registry.PotionRegister;
import com.hungteen.pvzmod.util.EntityUtil;
import com.hungteen.pvzmod.util.SoundsHandler;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityIceShroom extends EntityShroomBase{

	public EntityIceShroom(World worldIn) {
		super(worldIn);
		this.setSize(0.75f, 1.2f);
	}
	
	@Override
	public void onNormalPlantUpdate() {
		super.onNormalPlantUpdate();
		if(!this.getIsSleeping()) {
			this.setAttackTime(this.getAttackTime()+1);
			if(this.getAttackTime()>=this.getReadyTime()) {
				this.startBoom();
				this.setDead();
				this.setAttackTime(0);
			}
		}else {
			this.setAttackTime(0);
		}
	}
	
	private void startBoom()
	{
		if(!this.world.isRemote) {
			float len=this.getAttackLen();
			AxisAlignedBB aabb=new AxisAlignedBB(this.posX+len, this.posY+30, this.posZ+len,this.posX-len, this.posY-5, this.posZ-len);
			for(Entity entity:EntityUtil.getEntityAttackableTarget(this, aabb)) {
				    entity.attackEntityFrom(PVZDamageSource.causeIceDamage(this, this), this.getAttackDamage());
			}
			this.world.playSound(null,this.posX,this.posY,this.posZ,SoundsHandler.FROZEN_PEA,SoundCategory.VOICE, 4, 1);
		}
		this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX, this.posY, this.posZ, 0, 0, 0);
		for(int i=1;i<=15;i++) {
			Main.proxy.spawnParticle(PVZParticleType.SNOW, this.posX, this.posY, this.posZ, (this.getRNG().nextFloat()-0.5f)/4,this.getRNG().nextFloat()/5,(this.getRNG().nextFloat()-0.5f)/4);
		}
	}
	
	private int getAttackLen()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 10;
		else if(lvl<=13) return 15;
		else if(lvl<=20) return 20;
		return 10;
	}
	
	private int getReadyTime()
	{
		return 30;
	}
	
	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		if(this.getIsSleeping()) return false;
		if(source instanceof PVZDamageSource) {
			if(((PVZDamageSource) source).getPVZDamageType()==PVZDamageType.WEAK) {
				return false;
			}
		}
		return true;
	}

	public PotionEffect getFrozenPotionEffect()
	{
		int time=60;
		int lvl=this.getPlantLvl();
		if(lvl<=20) {
			int now=(lvl-1)/4;
			time=60+20*now;
		}
		return new PotionEffect(PotionRegister.FROZEN_EFFECT, time, 1 ,false,false);
	}
	
	
	public PotionEffect getColdPotionEffect()
	{
		int time=100;
		int lvl=this.getPlantLvl();
		if(lvl<=18) {
			int now=(lvl-1)/2;
			time=40+10*now+80;
		}
		else if(lvl<=20) time=280+80;
		return new PotionEffect(PotionRegister.COLD_EFFECT, time, 2 ,false,false);
	}
	
	@Override
	public boolean canStartSuperMode() {
		return false;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 0;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.ICE_SHROOM;
	}
}

package com.hungteen.pvzmod.entities.plants.ice;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.damage.PVZDamageSource;
import com.hungteen.pvzmod.damage.PVZDamageType;
import com.hungteen.pvzmod.entities.plants.base.EntityNearPlantBase;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.particles.base.PVZParticleType;
import com.hungteen.pvzmod.registry.PotionRegister;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityIcebergLettuce extends EntityNearPlantBase{

	public EntityIcebergLettuce(World worldIn) {
		super(worldIn);
		this.range=1.5f;
		setSize(0.5f, 0.5f);
	}
	
	@Override
	public void onNormalPlantUpdate() {
		super.onNormalPlantUpdate();
		if(this.isPlantInSuperMode()) {
			this.frozeAll();
		}
	}
	
	@Override
	protected void performAttack() {
		EntityLivingBase target=this.getAttackTarget();
		if(target!=null) {
			if(!world.isRemote) {
			    target.attackEntityFrom(PVZDamageSource.causeIceDamage(this, this), 0.01f);
			    this.setDead();
			}
			for(int i=1;i<=5;i++) {
			    Main.proxy.spawnParticle(PVZParticleType.SNOW, this.posX, this.posY, this.posZ, (this.getRNG().nextFloat()-0.5f)/4,this.getRNG().nextFloat()/5,(this.getRNG().nextFloat()-0.5f)/4);
		    }
		}
	}
	
	private PotionEffect getSuperEffect1()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=6) return new PotionEffect(PotionRegister.FROZEN_EFFECT, 200, 1);
		else if(lvl<=13) return new PotionEffect(PotionRegister.FROZEN_EFFECT, 240, 1);
		else if(lvl<=20) return new PotionEffect(PotionRegister.FROZEN_EFFECT, 300, 1);
		return new PotionEffect(PotionRegister.FROZEN_EFFECT, 200, 1);
	}
	
	private PotionEffect getSuperEffect2()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=6) return new PotionEffect(PotionRegister.COLD_EFFECT, 400, 4);
		else if(lvl<=13) return new PotionEffect(PotionRegister.COLD_EFFECT, 480, 4);
		else if(lvl<=20) return new PotionEffect(PotionRegister.COLD_EFFECT, 600, 4);
		return new PotionEffect(PotionRegister.COLD_EFFECT, 400, 4);
	}
	
	private void frozeAll()
	{
		if(!this.world.isRemote) {
			int r=this.getR();
			AxisAlignedBB aabb=new AxisAlignedBB(this.posX-r, this.posY-10, this.posZ-r, this.posX+r, this.posY+20, this.posZ+r);
			for(EntityLivingBase target:this.world.getEntitiesWithinAABB(EntityMob.class, aabb)) {
				if(target instanceof EntityZombieBase) {
					if(!((EntityZombieBase) target).getIsCharmed()) {
						target.addPotionEffect(this.getSuperEffect1());
						target.addPotionEffect(this.getSuperEffect2());
					}
				}
			}
		}
	}
	
	public int getR()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 10;
		else if(lvl<=13) return 15;
		else if(lvl<=20) return 20;
		return 10;
	}
	
	public PotionEffect getFrozenEffect()
	{
		int lvl=this.getPlantLvl();
		int time=0;
		if(lvl<=19) time=90+lvl*10;
		else if(lvl<=20) time=300;
		return new PotionEffect(PotionRegister.FROZEN_EFFECT, time, 1,false,false);
	}
	
	public PotionEffect getColdEffect()
	{
		int lvl=this.getPlantLvl();
		int time=0;
		if(lvl<=19) time=90+lvl*10;
		else if(lvl<=20) time=300;
		if(lvl>=7) {
			time+=(lvl-6)*20;
		}
		return new PotionEffect(PotionRegister.COLD_EFFECT, time, 3,false,false);
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.ICEBERG_LETTUCE;
	}
}

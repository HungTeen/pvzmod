package com.hungteen.pvzmod.entities.plants.light;

import com.hungteen.pvzmod.Main;
import com.hungteen.pvzmod.entities.drops.EntitySun;
import com.hungteen.pvzmod.entities.plants.base.EntityGenPlantBase;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.particles.base.PVZParticleType;
import com.hungteen.pvzmod.util.SoundsHandler;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntitySunFlower extends EntityGenPlantBase{
	
	public EntitySunFlower(World worldIn) {
		super(worldIn);
		this.setSize(0.5f, 1.55f);
	}
	
	@Override
	public void genSomething() {
		this.genSun(this.getSunAmount());
	}
	
	public void genSuper()
	{
		int lvl=this.getPlantLvl();
		int ge=(lvl<=6)?3:4;
		int amount=(lvl<=13)?50:75;
		for(int i=1;i<=ge;i++) {
		    this.genSun(amount);
		}
	}
	
	protected void genSun(int num)
	{
		EntitySun sun=new EntitySun(this.world,num);
		sun.setPosition(this.posX+this.rand.nextInt(10)-5,this.posY+this.rand.nextInt(3)+1,this.posZ+this.rand.nextInt(10)-5);
		this.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
        this.world.spawnEntity(sun);
	}
	
//	@Override
//	protected void playGenEffect(int num) {
//		for(int i=0;i<num*3;i++) {
//		    Main.proxy.spawnParticle(PVZParticleType.SUN_LIGHT, posX, posY+getEyeHeight(), posZ, (this.rand.nextFloat()-0.5f)/5, 0.1f, (this.rand.nextFloat()-0.5f)/5);
//		}
//	}
	
	@Override
	public int getAttackCD() {
		if(this.world.isDaytime()) return 800;
		else return 2400;
	}
    
	protected int getSunAmount()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 25;
		else if(lvl<=13) return 35;
		else if(lvl<=20) return 50;
		return 25;
	}
	
	@Override
	public Plants getPlantEnumName() {
		return Plants.SUN_FLOWER;
	}
}

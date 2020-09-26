package com.hungteen.pvz.entity.plant.light;

import com.hungteen.pvz.entity.drop.SunEntity;
import com.hungteen.pvz.entity.plant.base.PlantProducerEntity;
import com.hungteen.pvz.register.EntityRegister;
import com.hungteen.pvz.utils.enums.Plants;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class SunFlowerEntity extends PlantProducerEntity{

	public SunFlowerEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public void genSomething() {
		this.genSun(this.getSunAmount());
	}

	@Override
	public void genSuper() {
		int lvl=this.getPlantLvl();
		int ge=(lvl<=13)?3:4;
		int amount=(lvl<=6)?35:50;
		for(int i=1;i<=ge;i++) {
		    this.genSun(amount);
		}
	}

	protected void genSun(int num){
		SunEntity sun=EntityRegister.SUN.get().create(this.world);
		sun.setAmount(num);
		sun.setPosition(this.getPosX()+this.rand.nextInt(10)-5,this.getPosY()+this.rand.nextInt(3)+1,this.getPosZ()+this.rand.nextInt(10)-5);
		this.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
        this.world.addEntity(sun);
	}
	
	protected int getSunAmount(){
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 25;
		if(lvl<=13) return 35;
		if(lvl<=20) return 50;
		return 25;
	}
	
	@Override
	public int getGenCD() {
		int time = this.getAttackCD();
		if(this.world.isDaytime()) return time;
		return time * 4;
	}
	
	public static int getAttackCD(int lvl) {
		return 500;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.SUN_FLOWER;
	}

}

package com.hungteen.pvzmod.entities.plants.magic;

import com.hungteen.pvzmod.entities.drops.EntityCoin;
import com.hungteen.pvzmod.entities.plants.base.EntityGenPlantBase;
import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.util.SoundsHandler;
import com.hungteen.pvzmod.util.enums.Plants;

import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityMariGold extends EntityGenPlantBase{

	private final int CD=960;
	
	public EntityMariGold(World worldIn) {
		super(worldIn);
		setSize(0.6f, 1.5f);
	}

	@Override
	public void genSomething() {
		int lvl=this.getPlantLvl();
		int a=-1,b=-1,c=-1,d=-1;
		if(lvl<=5) {a=1000;b=1100;c=1110;d=1111;}
		else if(lvl<=10) {a=500;b=500;c=1110;d=1111;}
		else if(lvl<=15) {a=400;b=700;d=1110;d=1111;}
		else if(lvl<=20) {a=300;b=700;d=1109;d=1111;}
		if(a!=-1&&b!=-1&&c!=-1&&d!=-1) {
			int tmp=this.getRNG().nextInt(1111);
			int type=-1;
			if(tmp<a) type=0;
			else if(tmp<b) type=1;
			else if(tmp<c) type=2;
			else if(tmp<d) type=3;
			if(type!=-1) {
				EntityCoin coin=new EntityCoin(this.world,type);
				coin.setPosition(this.posX+this.getRNG().nextInt(5)-2, this.posY+3, this.posZ+this.getRNG().nextInt(5)-2);
				if(type<=2) this.world.playSound(null, this.posX, this.posY, this.posZ, SoundsHandler.DROP_COIN,SoundCategory.VOICE,4f,1f);
				else if(type==3) this.world.playSound(null, this.posX, this.posY, this.posZ, SoundsHandler.DROP_JEWEL,SoundCategory.VOICE,4f,1f); 
				this.world.spawnEntity(coin);
			}
		}
	}

	@Override
	public void genSuper() {
		for(int i=1;i<=this.getTimes();i++) {
			this.genSomething();
		}
	}

	@Override
	public int getAttackCD() {
		return 960;
	}

	public int getTimes()
	{
		int lvl=this.getPlantLvl();
		if(lvl<=6) return 3;
		else if(lvl<=13) return 4;
		else if(lvl<=20) return 5;
		return 3;
	}
	
	@Override
	public int getSuperTimeLength() {
		return 2;
	}

	@Override
	public Plants getPlantEnumName() {
		return Plants.MARIGOLD;
	}
}

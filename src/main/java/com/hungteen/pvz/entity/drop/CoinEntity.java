package com.hungteen.pvz.entity.drop;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class CoinEntity extends DropEntity{

	public CoinEntity(EntityType<? extends MobEntity> type, World worldIn) {
		super(type, worldIn);
		this.setAmount(this.getRandomAmount());
	}

	@Override
	public void onCollideWithPlayer(PlayerEntity entityIn) {
		if(!this.world.isRemote) {
			PlayerUtil.addPlayerStats(entityIn, Resources.MONEY, this.getAmount());
		}
		else {
			if(this.getAmount()==1000) {
				this.world.playSound(entityIn, getPosition(), SoundRegister.JEWEL_PICK.get(), SoundCategory.NEUTRAL, 1f,1f);
			}else {
				this.world.playSound(entityIn, getPosition(), SoundRegister.COIN_PICK.get(), SoundCategory.NEUTRAL, 1f,1f);
			}
		}
		this.remove();
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		//1000 <-> 1 1 <-> 0.5 
		float t=(float) Math.log10(this.getAmount());//0 1 2 3
		float w=t*0.18f+0.4f;
		return new EntitySize(w, w, false); //max (0.4w,0.4h) min(0.9w,0.9h)
	}
	
	public void setTypeAmount(int type){
		this.setAmount(this.getAmountByType(type));
	}
	
	protected int getRandomAmount(){
		int type=this.rand.nextInt(4);
		return this.getAmountByType(type);
	}
	
	/**
	 * Copper Coin = 1
	 * Silver Coin = 10
	 * Gold Coin = 100
	 * Jewel = 1000
	 */
	private int getAmountByType(int type){
		if(type==0) return 1;
		else if(type==1) return 10;
		else if(type==2) return 100;
		else if(type==3) return 1000;
		PVZMod.LOGGER.debug("Coin Type Error!!!");
		return 1;
	}
	
	@Override
	protected int getMaxLiveTick() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.EntityLiveTick.CoinLiveTick.get();
	}

}
package com.hungteen.pvz.entity.drop;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.register.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class CoinEntity extends DropEntity{

	public static final int COIN_TYPES = 3;
	
	public CoinEntity(EntityType<? extends MobEntity> type, World worldIn) {
		super(type, worldIn);
		this.setAmountByType(this.getRandomType());
	}
	
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if(! world.isRemote) {
			EntityUtil.playSound(this, SoundRegister.COIN_DROP.get());
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	public void onCollideWithPlayer(PlayerEntity entityIn) {
		if(!this.world.isRemote) {
			PlayerUtil.addPlayerStats(entityIn, Resources.MONEY, this.getAmount());
		} else {
			EntityUtil.playSound(entityIn, SoundRegister.COIN_PICK.get());
		}
		this.remove();
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		//1000 <-> 1 1 <-> 0.5 
		float t = (float) Math.log10(this.getAmount());//0 1 2 3
		float w = t * 0.18f + 0.4f;
		return new EntitySize(w, w, false); //max (0.4w,0.4h) min(0.9w,0.9h)
	}
	
	public void setAmountByType(CoinType type){
		this.setAmount(type.money);
	}
	
	protected CoinType getRandomType(){
		return CoinType.values()[this.rand.nextInt(COIN_TYPES)];
	}
	
	@Override
	protected int getMaxLiveTick() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.EntityLiveTick.CoinLiveTick.get();
	}
	
	public static enum CoinType {
		COPPER(1),
		SILVER(10),
		GOLD(100);
		
		public final int money;
		
		private CoinType(int money) {
			this.money = money;
		}
	}

}
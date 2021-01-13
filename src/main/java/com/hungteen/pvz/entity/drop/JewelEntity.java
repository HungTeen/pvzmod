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

public class JewelEntity extends DropEntity{

	public JewelEntity(EntityType<? extends MobEntity> type, World worldIn) {
		super(type, worldIn);
		this.setAmount(1);
	}

	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if(! world.isRemote) {
			EntityUtil.playSound(this, SoundRegister.JEWEL_DROP.get());
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	public void onCollideWithPlayer(PlayerEntity entityIn) {
		if(! this.world.isRemote) {
			PlayerUtil.addPlayerStats(entityIn, Resources.GEM_NUM, this.getAmount());
		}
		else {
			EntityUtil.playSound(entityIn, SoundRegister.JEWEL_PICK.get());
		}
		this.remove();
	}
	
	@Override
	public EntitySize getSize(Pose poseIn) {
		return EntitySize.flexible(0.9f, 0.9f);
	}
	
	@Override
	protected int getMaxLiveTick() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.EntityLiveTick.JewelLiveTick.get();
	}

}

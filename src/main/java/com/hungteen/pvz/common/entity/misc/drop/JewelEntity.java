package com.hungteen.pvz.common.entity.misc.drop;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.common.misc.sound.SoundRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class JewelEntity extends DropEntity{

	public JewelEntity(EntityType<? extends MobEntity> type, World worldIn) {
		super(type, worldIn);
		this.setAmount(1);
	}
	
	@Override
	protected void onDropped() {
		super.onDropped();
		if(! level.isClientSide) {
			EntityUtil.playSound(this, SoundRegister.JEWEL_DROP.get());
		}
	}
	
	@Override
	public void onCollectedByPlayer(PlayerEntity player) {
		if(! this.level.isClientSide) {
			PlayerUtil.addResource(player, Resources.GEM_NUM, this.getAmount());
			PlayerUtil.playClientSound(player, SoundRegister.JEWEL_PICK.get());
		}
	}
	
	@Override
	public EntitySize getDimensions(Pose poseIn) {
		return EntitySize.scalable(0.9f, 0.9f);
	}
	
	@Override
	protected int getMaxLiveTick() {
		return PVZConfig.COMMON_CONFIG.EntitySettings.EntityLiveTick.JewelLiveTick.get();
	}

}

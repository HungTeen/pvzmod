package com.hungteen.pvz.entity.drop;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class SunEntity extends DropEntity{
	
	public SunEntity(EntityType<? extends Entity> type, World worldIn) {
		super(type, worldIn);
		this.setAmount(25);//default sun amount (nature spawn)
	}

	@Override
	public void onCollideWithPlayer(PlayerEntity entityIn) {
		if(!this.world.isRemote) {
			PlayerUtil.addPlayerStats(entityIn, Resources.SUN_NUM, this.getAmount());
		}
		this.remove();
	}
	
	@Override
	protected int getMaxLiveTick() {
		return PVZConfig.COMMON_CONFIG.ENTITY_SETTINGS.dropLiveTick.sunLiveTick.get();
	}
}

package com.hungteen.pvz.render.entity.zombie.poolnight;

import com.hungteen.pvz.entity.zombie.poolnight.BalloonZombieEntity;
import com.hungteen.pvz.model.entity.zombie.poolnight.BalloonZombieModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class BalloonZombieRender extends PVZZombieRender<BalloonZombieEntity> {

	public BalloonZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new BalloonZombieModel(), 0);
	}

	@Override
	protected float getScaleByEntity(BalloonZombieEntity entity) {
		if(entity.isMiniZombie()) return 0.15F;
		return 0.5F;
	}
	
	@Override
	public ResourceLocation getTextureLocation(BalloonZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/poolnight/balloon_zombie.png");
	}

}

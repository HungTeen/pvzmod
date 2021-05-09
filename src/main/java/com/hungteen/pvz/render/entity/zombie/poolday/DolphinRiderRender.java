package com.hungteen.pvz.render.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.poolday.DolphinRiderEntity;
import com.hungteen.pvz.model.entity.zombie.poolday.DolphinRiderModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class DolphinRiderRender extends PVZZombieRender<DolphinRiderEntity>{

	public DolphinRiderRender(EntityRendererManager rendererManager) {
		super(rendererManager, new DolphinRiderModel(), 0);
	}

	@Override
	protected float getScaleByEntity(DolphinRiderEntity entity) {
		if(entity.isMiniZombie()) return 0.15F;
		return 0.5f;
	}

	@Override
	public ResourceLocation getTextureLocation(DolphinRiderEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/poolday/dolphin_rider.png");
	}

}

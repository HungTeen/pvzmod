package com.hungteen.pvz.render.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.poolday.DolphinRiderZombieEntity;
import com.hungteen.pvz.model.entity.zombie.poolday.DolphinRiderZombieModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class DolphinRiderZombieRender extends PVZZombieRender<DolphinRiderZombieEntity>{

	public DolphinRiderZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new DolphinRiderZombieModel(), 0.4f);
	}

	@Override
	protected float getScaleByEntity(DolphinRiderZombieEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getEntityTexture(DolphinRiderZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/poolday/dolphin_rider_zombie.png");
	}

}

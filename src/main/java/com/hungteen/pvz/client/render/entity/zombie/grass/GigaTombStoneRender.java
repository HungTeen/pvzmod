package com.hungteen.pvz.client.render.entity.zombie.grass;

import com.hungteen.pvz.client.model.entity.zombie.grass.TombStoneModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.custom.GigaTombStoneEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class GigaTombStoneRender extends PVZZombieRender<GigaTombStoneEntity>{

	public GigaTombStoneRender(EntityRendererManager rendererManager) {
		super(rendererManager, new TombStoneModel<>(), 0.5f);
	}

	@Override
	protected float getScaleByEntity(GigaTombStoneEntity entity) {
		return 0.7f;
	}
	
	@Override
	protected float getOffsetRisingHeight() {
		return 1.1f;
	}

}

package com.hungteen.pvz.client.render.entity.zombie.pool;

import com.hungteen.pvz.client.model.entity.zombie.pool.DolphinRiderModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.pool.DolphinRiderEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class DolphinRiderRender extends PVZZombieRender<DolphinRiderEntity>{

	public DolphinRiderRender(EntityRendererManager rendererManager) {
		super(rendererManager, new DolphinRiderModel(), 0);
	}

}

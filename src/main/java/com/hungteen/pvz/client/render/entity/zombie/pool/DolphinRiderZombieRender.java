package com.hungteen.pvz.client.render.entity.zombie.pool;

import com.hungteen.pvz.client.model.entity.zombie.pool.DolphinRiderZombieModel;
import com.hungteen.pvz.common.entity.zombie.pool.DolphinRiderZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class DolphinRiderZombieRender extends AbstractSwimmerRender<DolphinRiderZombieEntity>{

	public DolphinRiderZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new DolphinRiderZombieModel(), 0.4f);
	}

}

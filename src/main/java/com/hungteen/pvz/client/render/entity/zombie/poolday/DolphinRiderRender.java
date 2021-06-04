package com.hungteen.pvz.client.render.entity.zombie.poolday;

import com.hungteen.pvz.client.model.entity.zombie.poolday.DolphinRiderModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.poolday.DolphinRiderEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class DolphinRiderRender extends PVZZombieRender<DolphinRiderEntity>{

	public DolphinRiderRender(EntityRendererManager rendererManager) {
		super(rendererManager, new DolphinRiderModel(), 0);
	}

}

package com.hungteen.pvz.client.render.entity.zombie.zombotany;

import com.hungteen.pvz.client.model.entity.zombie.zombotany.SquashZombieModel;
import com.hungteen.pvz.common.entity.zombie.zombotany.SquashZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class SquashZombieRender extends AbstractZombotanyRender<SquashZombieEntity> {

	public SquashZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new SquashZombieModel(), 0.4F);
	}

}

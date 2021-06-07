package com.hungteen.pvz.client.render.entity.zombie.zombotany;

import com.hungteen.pvz.client.model.entity.zombie.zombotany.TallNutZombieModel;
import com.hungteen.pvz.common.entity.zombie.zombotany.TallNutZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class TallNutZombieRender extends AbstractZombotanyRender<TallNutZombieEntity> {

	public TallNutZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new TallNutZombieModel(), 0.4F);
	}

}

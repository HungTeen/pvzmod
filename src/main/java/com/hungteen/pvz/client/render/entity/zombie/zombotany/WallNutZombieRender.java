package com.hungteen.pvz.client.render.entity.zombie.zombotany;

import com.hungteen.pvz.client.model.entity.zombie.zombotany.WallNutZombieModel;
import com.hungteen.pvz.common.entity.zombie.zombotany.WallNutZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class WallNutZombieRender extends AbstractZombotanyRender<WallNutZombieEntity> {

	public WallNutZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new WallNutZombieModel(), 0.4F);
	}
	
}

package com.hungteen.pvz.client.render.entity.plant.spear;

import com.hungteen.pvz.client.model.entity.plant.spear.CatTailModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.spear.CatTailEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.math.vector.Vector3d;

public class CatTailRender extends PVZPlantRender<CatTailEntity>{

	public CatTailRender(EntityRendererManager rendererManager) {
		super(rendererManager, new CatTailModel(), 0);
	}

	@Override
	public Vector3d getTranslateVec(CatTailEntity entity) {
		if(entity.isInWater()) {
			return new Vector3d(0, - 0.5D, 0);
		}
		return new Vector3d(0, 0, 0);
	}

}

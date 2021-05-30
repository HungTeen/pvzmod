package com.hungteen.pvz.client.render.entity.plant.spear;

import com.hungteen.pvz.client.model.entity.plant.spear.CatTailModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.spear.CatTailEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;

public class CatTailRender extends PVZPlantRender<CatTailEntity>{

	public CatTailRender(EntityRendererManager rendererManager) {
		super(rendererManager, new CatTailModel(), 0);
	}

	@Override
	public float getScaleByEntity(CatTailEntity entity) {
		return 0.18F;
	}
	
	@Override
	public Vector3d getTranslateVec(CatTailEntity entity) {
		return new Vector3d(0, - 2D, 0);
	}

	@Override
	public ResourceLocation getTextureLocation(CatTailEntity entity) {
		return StringUtil.prefix("textures/entity/plant/spear/cat_tail.png");
	}

}

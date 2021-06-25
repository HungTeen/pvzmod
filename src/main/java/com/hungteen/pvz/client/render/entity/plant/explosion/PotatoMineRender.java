package com.hungteen.pvz.client.render.entity.plant.explosion;

import com.hungteen.pvz.client.model.entity.plant.explosion.PotatoMineModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.explosion.PotatoMineEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PotatoMineRender extends PVZPlantRender<PotatoMineEntity>{

	public PotatoMineRender(EntityRendererManager rendererManager) {
		super(rendererManager, new PotatoMineModel() ,0.3f);
	}

	@Override
	public Vector3d getTranslateVec(PotatoMineEntity entity) {
		return entity.isMineReady() ? new Vector3d(0, 0, 0) : new Vector3d(0, 0.6f, 0);
	}
	
}

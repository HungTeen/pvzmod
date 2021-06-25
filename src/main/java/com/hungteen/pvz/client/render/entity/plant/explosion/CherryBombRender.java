package com.hungteen.pvz.client.render.entity.plant.explosion;

import com.hungteen.pvz.client.model.entity.plant.explosion.CherryBombModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.explosion.CherryBombEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CherryBombRender extends PVZPlantRender<CherryBombEntity>{

	public CherryBombRender(EntityRendererManager rendererManager){
		super(rendererManager, new CherryBombModel(), 0.5f);
	}

	@Override
	public float getScaleByEntity(CherryBombEntity entity) {
		float plus = entity.getAttackTime() * 1f / entity.getReadyTime();
		return 0.5f + plus * 0.05f;
	}

}

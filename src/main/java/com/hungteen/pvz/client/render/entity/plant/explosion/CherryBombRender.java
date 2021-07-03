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
		super(rendererManager, new CherryBombModel(), 0.5F);
	}

	@Override
	public float getScaleByEntity(CherryBombEntity entity) {
		final float plus = entity.getAttackTime() * 1F / entity.getReadyTime();
		return super.getScaleByEntity(entity) + plus * 0.15F;
	}

}

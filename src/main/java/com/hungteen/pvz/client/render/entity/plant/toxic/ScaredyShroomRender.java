package com.hungteen.pvz.client.render.entity.plant.toxic;

import com.hungteen.pvz.client.model.entity.plant.toxic.ScaredyShroomModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.toxic.ScaredyShroomEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ScaredyShroomRender extends PVZPlantRender<ScaredyShroomEntity>{

	public ScaredyShroomRender(EntityRendererManager rendererManager) {
		super(rendererManager, new ScaredyShroomModel(), 0.4f);
	}

	@Override
	public Vector3d getTranslateVec(ScaredyShroomEntity entity) {
		if(entity.isScared()) {
			final double percent = entity.getScareTime() * 1.0 / ScaredyShroomEntity.SCARE_ANIM_CD;
		    final double change = 1.38;
		    return new Vector3d(0, change * percent, 0);
		}
		return super.getTranslateVec(entity);
	}
	
}

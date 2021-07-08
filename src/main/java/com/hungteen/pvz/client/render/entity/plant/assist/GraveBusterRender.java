package com.hungteen.pvz.client.render.entity.plant.assist;

import com.hungteen.pvz.client.model.entity.plant.assist.GraveBusterModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.assist.GraveBusterEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GraveBusterRender extends PVZPlantRender<GraveBusterEntity>{

	public GraveBusterRender(EntityRendererManager rendererManager) {
		super(rendererManager, new GraveBusterModel(), 0);
	}

	@Override
	public Vector3d getTranslateVec(GraveBusterEntity entity) {
		if(entity.isEatingTomb()) {
			final float height = 1.5f;
			final float downOffset = (1 - entity.getAttackTime() * 1.0f / entity.getEatTombCD()) * height;
		    return new Vector3d(0, - downOffset, 0);
		}
		return super.getTranslateVec(entity);
	}
	
}

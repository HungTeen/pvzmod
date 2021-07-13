package com.hungteen.pvz.client.render.entity.plant.ice;

import com.hungteen.pvz.client.model.entity.plant.ice.IceShroomModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.ice.IceShroomEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IceShroomRender extends PVZPlantRender<IceShroomEntity>{

	public IceShroomRender(EntityRendererManager rendererManager) {
		super(rendererManager, new IceShroomModel(), 0.5f);
	}
	
	@Override
	public float getScaleByEntity(IceShroomEntity entity) {
		final float plus = entity.getAttackTime() * 1F / entity.getReadyTime();
		return super.getScaleByEntity(entity) + plus * 0.15F;
	}

}

package com.hungteen.pvz.render.entity.plant.ice;

import com.hungteen.pvz.entity.plant.ice.IceShroomEntity;
import com.hungteen.pvz.model.entity.plant.ice.IceShroomModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IceShroomRender extends PVZPlantRender<IceShroomEntity>{

	public IceShroomRender(EntityRendererManager rendererManager) {
		super(rendererManager, new IceShroomModel(), 0.5f);
	}

	@Override
	public float getScaleByEntity(IceShroomEntity entity) {
		return 0.8f;
	}

	@Override
	public ResourceLocation getTextureLocation(IceShroomEntity entity) {
		return StringUtil.prefix("textures/entity/plant/ice/ice_shroom.png");
	}

}

package com.hungteen.pvz.render.entity.plant.toxic;

import com.hungteen.pvz.entity.plant.toxic.FumeShroomEntity;
import com.hungteen.pvz.model.entity.plant.toxic.FumeShroomModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FumeShroomRender extends PVZPlantRender<FumeShroomEntity>{

	public FumeShroomRender(EntityRendererManager rendererManager) {
		super(rendererManager, new FumeShroomModel(), 0.7f);
	}

	@Override
	public float getScaleByEntity(FumeShroomEntity entity) {
		return 0.9f;
	}

	@Override
	public ResourceLocation getEntityTexture(FumeShroomEntity entity) {
		return StringUtil.prefix("textures/entity/plant/toxic/fume_shroom.png");
	}

}

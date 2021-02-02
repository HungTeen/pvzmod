package com.hungteen.pvz.render.entity.plant.ice;

import com.hungteen.pvz.entity.plant.ice.WinterMelonEntity;
import com.hungteen.pvz.model.entity.plant.ice.WinterMelonModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WinterMelonRender extends PVZPlantRender<WinterMelonEntity> {

	private static final ResourceLocation WINTER_MELON_TEX = StringUtil.prefix("textures/entity/plant/ice/winter_melon.png");
	
	public WinterMelonRender(EntityRendererManager rendererManager) {
		super(rendererManager, new WinterMelonModel(), 0.5F);
	}

	@Override
	public float getScaleByEntity(WinterMelonEntity entity) {
		return 0.9F;
	}

	@Override
	public ResourceLocation getEntityTexture(WinterMelonEntity entity) {
		return WINTER_MELON_TEX;
	}

}

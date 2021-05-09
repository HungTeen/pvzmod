package com.hungteen.pvz.render.entity.plant.arma;

import com.hungteen.pvz.entity.plant.arma.MelonPultEntity;
import com.hungteen.pvz.model.entity.plant.arma.MelonPultModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MelonPultRender extends PVZPlantRender<MelonPultEntity> {

	private static final ResourceLocation MELON_PULT_TEX = StringUtil.prefix("textures/entity/plant/arma/melon_pult.png");
	
	public MelonPultRender(EntityRendererManager rendererManager) {
		super(rendererManager, new MelonPultModel(), 0.5F);
	}

	@Override
	public float getScaleByEntity(MelonPultEntity entity) {
		return 0.9F;
	}

	@Override
	public ResourceLocation getTextureLocation(MelonPultEntity entity) {
		return MELON_PULT_TEX;
	}

}

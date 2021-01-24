package com.hungteen.pvz.render.entity.plant.arma;

import com.hungteen.pvz.entity.plant.arma.CabbagePultEntity;
import com.hungteen.pvz.model.entity.plant.arma.CabbagePultModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CabbagePultRender extends PVZPlantRender<CabbagePultEntity> {

	public CabbagePultRender(EntityRendererManager rendererManager) {
		super(rendererManager, new CabbagePultModel(), 0.5F);
	}

	@Override
	public float getScaleByEntity(CabbagePultEntity entity) {
		return 0.9F;
	}

	@Override
	public ResourceLocation getEntityTexture(CabbagePultEntity entity) {
		return StringUtil.prefix("textures/entity/plant/arma/cabbage_pult.png");
	}

}

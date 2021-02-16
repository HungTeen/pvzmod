package com.hungteen.pvz.render.entity.plant.explosion;

import com.hungteen.pvz.entity.plant.explosion.CobCannonEntity;
import com.hungteen.pvz.model.entity.plant.explosion.CobCannonModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CobCannonRender extends PVZPlantRender<CobCannonEntity> {
	
	private static final ResourceLocation COBCANNON_TEX = StringUtil.prefix("textures/entity/plant/explosion/cob_cannon.png");
	
	public CobCannonRender(EntityRendererManager rendererManager) {
		super(rendererManager, new CobCannonModel(), 0.5F);
	}

	@Override
	public float getScaleByEntity(CobCannonEntity entity) {
		return 1.1F;
	}

	@Override
	public ResourceLocation getEntityTexture(CobCannonEntity entity) {
		return COBCANNON_TEX;
	}

}

package com.hungteen.pvz.render.entity.plant.light;

import javax.annotation.Nullable;

import com.hungteen.pvz.entity.plant.light.PlanternEntity;
import com.hungteen.pvz.model.entity.plant.light.PlanternModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.render.layer.PlanternLayerRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PlanternRender extends PVZPlantRender<PlanternEntity> {

	public PlanternRender(EntityRendererManager rendererManager) {
		super(rendererManager, new PlanternModel(), 0.4f);
	}

	@Override
	protected void addPlantLayers() {
		super.addPlantLayers();
		this.addLayer(new PlanternLayerRender(this));
	}
	
	@Nullable
	protected RenderType func_230042_a_(PlanternEntity p_230042_1_, boolean p_230042_2_, boolean p_230042_3_) {
		ResourceLocation resourcelocation = this.getEntityTexture(p_230042_1_);
		return RenderType.getEntityTranslucentCull(resourcelocation);
	}
	
	@Override
	public float getScaleByEntity(PlanternEntity entity) {
		return 0.7f;
	}

	@Override
	public ResourceLocation getEntityTexture(PlanternEntity entity) {
		return StringUtil.prefix("textures/entity/plant/light/plantern.png");
	}

}

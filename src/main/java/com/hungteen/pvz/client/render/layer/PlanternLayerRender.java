package com.hungteen.pvz.client.render.layer;

import com.hungteen.pvz.client.render.layer.fullskin.PVZFullSkinLayer;
import com.hungteen.pvz.common.entity.plant.light.PlanternEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.resources.ResourceLocation;

public class PlanternLayerRender extends PVZFullSkinLayer<PlanternEntity, EntityModel<PlanternEntity>>{

	public PlanternLayerRender(IEntityRenderer<PlanternEntity, EntityModel<PlanternEntity>> entityRendererIn) {
		super(entityRendererIn);
	}

	@Override
	protected boolean canRender(PlanternEntity entity) {
		return true;
	}

	@Override
	protected ResourceLocation getResourceLocation(PlanternEntity entity) {
		return StringUtil.prefix("textures/entity/layer/plantern_light.png");
	}

}

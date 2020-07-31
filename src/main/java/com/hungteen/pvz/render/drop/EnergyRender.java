package com.hungteen.pvz.render.drop;

import com.hungteen.pvz.entity.drop.EnergyEntity;
import com.hungteen.pvz.model.drop.EnergyModel;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class EnergyRender extends DropRender<EnergyEntity>{

	public EnergyRender(EntityRendererManager renderManager) {
		super(renderManager, new EnergyModel());
	}

	@Override
	protected float getRenderSize(EnergyEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getEntityTexture(EnergyEntity entity) {
		return StringUtil.prefix("textures/entity/drop/energy.png");
	}

}

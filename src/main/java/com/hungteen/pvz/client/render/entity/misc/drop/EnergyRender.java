package com.hungteen.pvz.client.render.entity.misc.drop;

import com.hungteen.pvz.client.model.entity.misc.DropModel;
import com.hungteen.pvz.common.entity.misc.drop.EnergyEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnergyRender extends DropRender<EnergyEntity>{

	public EnergyRender(EntityRendererManager renderManager) {
		super(renderManager, new DropModel<>());
	}

	@Override
	public ResourceLocation getTextureLocation(EnergyEntity entity) {
		return StringUtil.prefix("textures/entity/drop/energy.png");
	}

	@Override
	protected float getScaleByEntity(EnergyEntity entity) {
		return 0.5F;
	}

}

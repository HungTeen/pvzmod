package com.hungteen.pvz.render.entity.drop;

import com.hungteen.pvz.entity.drop.EnergyEntity;
import com.hungteen.pvz.model.entity.drop.EnergyModel;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EnergyRender extends DropRender<EnergyEntity>{

	public EnergyRender(EntityRendererManager renderManager) {
		super(renderManager, new EnergyModel());
	}

	@Override
	protected float getRenderSize(EnergyEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getTextureLocation(EnergyEntity entity) {
		return StringUtil.prefix("textures/entity/drop/energy.png");
	}

}

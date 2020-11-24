package com.hungteen.pvz.render.entity.drop;

import com.hungteen.pvz.entity.drop.JewelEntity;
import com.hungteen.pvz.model.entity.drop.CoinModel;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JewelRender extends DropRender<JewelEntity>{

	public JewelRender(EntityRendererManager renderManager) {
		super(renderManager, new CoinModel());
	}

	@Override
	protected float getRenderSize(JewelEntity entity) {
		return 0.3f;
	}

	@Override
	public ResourceLocation getEntityTexture(JewelEntity entity) {
		return StringUtil.prefix("textures/entity/drop/jewel.png");
	}

}
package com.hungteen.pvz.client.render.entity.misc.drop;

import com.hungteen.pvz.client.model.entity.misc.DropModel;
import com.hungteen.pvz.common.entity.misc.drop.JewelEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JewelRender extends DropRender<JewelEntity>{

	public JewelRender(EntityRendererManager renderManager) {
		super(renderManager, new DropModel<>());
	}

	@Override
	public ResourceLocation getTextureLocation(JewelEntity entity) {
		return StringUtil.prefix("textures/entity/drop/jewel.png");
	}

	@Override
	protected float getScaleByEntity(JewelEntity entity) {
		return 0.32F;
	}

}
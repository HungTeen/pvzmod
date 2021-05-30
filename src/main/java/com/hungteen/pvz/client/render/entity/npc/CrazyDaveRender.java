package com.hungteen.pvz.client.render.entity.npc;

import com.hungteen.pvz.client.model.entity.npc.CrazyDaveModel;
import com.hungteen.pvz.client.render.entity.PVZCreatureRender;
import com.hungteen.pvz.common.entity.npc.CrazyDaveEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CrazyDaveRender extends PVZCreatureRender<CrazyDaveEntity>{

	public CrazyDaveRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new CrazyDaveModel(), 0.4f);
	}

	@Override
	protected float getScaleByEntity(CrazyDaveEntity entity) {
		return 0.6f;
	}

	@Override
	public ResourceLocation getTextureLocation(CrazyDaveEntity entity) {
		return StringUtil.prefix("textures/entity/npc/crazy_dave.png");
	}

}

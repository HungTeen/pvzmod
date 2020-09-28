package com.hungteen.pvz.render.entity.npc;

import com.hungteen.pvz.entity.npc.CrazyDaveEntity;
import com.hungteen.pvz.model.entity.npc.CrazyDaveModel;
import com.hungteen.pvz.render.entity.PVZCreatureRender;
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
	protected float getRenderSize(CrazyDaveEntity entity) {
		return 0.6f;
	}

	@Override
	public ResourceLocation getEntityTexture(CrazyDaveEntity entity) {
		return StringUtil.prefix("textures/entity/npc/crazy_dave.png");
	}

}

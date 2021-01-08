package com.hungteen.pvz.render.entity.npc;

import com.hungteen.pvz.entity.npc.PennyEntity;
import com.hungteen.pvz.model.entity.npc.PanneyModel;
import com.hungteen.pvz.render.entity.PVZCreatureRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PanneyRender extends PVZCreatureRender<PennyEntity>{

	public PanneyRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new PanneyModel(), 1f);
	}

	@Override
	protected float getScaleByEntity(PennyEntity entity) {
		return 1.5f;
	}

	@Override
	public ResourceLocation getEntityTexture(PennyEntity entity) {
		return StringUtil.prefix("textures/entity/npc/panney.png");
	}

}

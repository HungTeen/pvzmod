package com.hungteen.pvz.client.render.entity.npc;

import com.hungteen.pvz.client.model.entity.npc.PennyModel;
import com.hungteen.pvz.client.render.entity.PVZCreatureRender;
import com.hungteen.pvz.common.entity.npc.PennyEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PennyRender extends PVZCreatureRender<PennyEntity>{

	public static final ResourceLocation PENNY_TEX = StringUtil.prefix("textures/entity/npc/panney.png");
	
	public PennyRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new PennyModel(), 1f);
	}

	@Override
	protected float getScaleByEntity(PennyEntity entity) {
		return 1.5f;
	}

	@Override
	public ResourceLocation getTextureLocation(PennyEntity entity) {
		return PENNY_TEX;
	}

}

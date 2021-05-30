package com.hungteen.pvz.client.render.entity.npc;

import com.hungteen.pvz.client.model.entity.npc.SunDaveModel;
import com.hungteen.pvz.client.render.entity.PVZCreatureRender;
import com.hungteen.pvz.common.entity.npc.SunDaveEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SunDaveRender extends PVZCreatureRender<SunDaveEntity> {

	public SunDaveRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new SunDaveModel(), 0.5F);
	}

	@Override
	protected float getScaleByEntity(SunDaveEntity entity) {
		return 0.55F;
	}

	@Override
	public ResourceLocation getTextureLocation(SunDaveEntity entity) {
		return StringUtil.prefix("textures/entity/npc/sun_dave.png");
	}

}

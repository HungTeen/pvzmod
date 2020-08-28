package com.hungteen.pvz.render.entity.misc;

import com.hungteen.pvz.entity.misc.SmallChomperEntity;
import com.hungteen.pvz.model.entity.misc.SmallChomperModel;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SmallChomperRender extends MobRenderer<SmallChomperEntity,EntityModel<SmallChomperEntity>>{

	public SmallChomperRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new SmallChomperModel(), 0.1f);
	}

	@Override
	protected void preRenderCallback(SmallChomperEntity entitylivingbaseIn, MatrixStack matrixStackIn,
			float partialTickTime) {
		int tick = entitylivingbaseIn.getTick();//1 - 20
		matrixStackIn.translate(0, 1f-0.05f*tick, 0);
	}
	
	@Override
	public ResourceLocation getEntityTexture(SmallChomperEntity entity) {
		return StringUtil.prefix("textures/entity/plant/enforce/chomper.png");
	}

}

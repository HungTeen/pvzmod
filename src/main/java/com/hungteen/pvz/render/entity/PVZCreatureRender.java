package com.hungteen.pvz.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.MobEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class PVZCreatureRender<T extends MobEntity> extends MobRenderer<T, EntityModel<T>>{

	public PVZCreatureRender(EntityRendererManager renderManagerIn, EntityModel<T> entityModelIn,
			float shadowSizeIn) {
		super(renderManagerIn, entityModelIn, shadowSizeIn);
	}

	@Override
	protected void preRenderCallback(T entitylivingbaseIn, MatrixStack matrixStackIn,
			float partialTickTime) {
		super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
		float scale = getRenderSize(entitylivingbaseIn);
		matrixStackIn.scale(scale, scale, scale);
	}
	
	protected abstract float getRenderSize(T entity);

}

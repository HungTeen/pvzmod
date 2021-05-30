package com.hungteen.pvz.client.render.entity.zombie;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.MobEntity;

public abstract class PVZZombieToolRender<T extends MobEntity> extends MobRenderer<T, EntityModel<T>>{

	public PVZZombieToolRender(EntityRendererManager renderManagerIn, EntityModel<T> entityModelIn,
			float shadowSizeIn) {
		super(renderManagerIn, entityModelIn, shadowSizeIn);
	}

	@Override
	protected void scale(T entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
		float sz=getScaleByEntity(entitylivingbaseIn);
		matrixStackIn.scale(sz,sz,sz);
	}
	
	protected abstract float getScaleByEntity(T entity);
	
}

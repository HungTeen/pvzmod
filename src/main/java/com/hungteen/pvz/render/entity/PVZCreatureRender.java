package com.hungteen.pvz.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class PVZCreatureRender<T extends MobEntity> extends MobRenderer<T, EntityModel<T>>{

	public PVZCreatureRender(EntityRendererManager renderManagerIn, EntityModel<T> entityModelIn,
			float shadowSizeIn) {
		super(renderManagerIn, entityModelIn, shadowSizeIn);
	}

	@Override
	protected void preRenderCallback(T entity, MatrixStack matrixStackIn, float partialTickTime) {
		float sz = getScaleByEntity(entity);
		matrixStackIn.scale(sz,sz,sz);
		Vec3d vec = getTranslateVec(entity);
		matrixStackIn.translate(vec.x, vec.y, vec.z);
	}
	
	protected abstract float getScaleByEntity(T entity);
	
	public Vec3d getTranslateVec(T entity) {
		return new Vec3d(0, 0, 0);
	}

}

package com.hungteen.pvz.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class PVZCreatureRender<T extends MobEntity> extends MobRenderer<T, EntityModel<T>>{

	public PVZCreatureRender(EntityRendererManager renderManagerIn, EntityModel<T> entityModelIn,
			float shadowSizeIn) {
		super(renderManagerIn, entityModelIn, shadowSizeIn);
	}

	@Override
	protected void scale(T entity, MatrixStack matrixStackIn, float partialTickTime) {
		float sz = getScaleByEntity(entity);
		matrixStackIn.scale(sz, sz, sz);
		Vector3d vec = this.getTranslateVec(entity);
		matrixStackIn.translate(vec.x, vec.y, vec.z);
	}
	
	protected abstract float getScaleByEntity(T entity);
	
	public Vector3d getTranslateVec(T entity) {
		return new Vector3d(0, 0, 0);
	}

}

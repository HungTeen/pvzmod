package com.hungteen.pvz.render.entity.plant;

import com.hungteen.pvz.entity.plant.PlantEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class PlantRender<T extends PlantEntity> extends MobRenderer<T, EntityModel<T>>{

	public PlantRender(EntityRendererManager rendererManager, EntityModel<T> entityModelIn, float shadowSizeIn) {
		super(rendererManager, entityModelIn, shadowSizeIn);
		this.addPlantLayers();
	}

	@Override
	protected void preRenderCallback(T entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
		float sz=getScaleByEntity(entitylivingbaseIn);
		matrixStackIn.scale(sz,sz,sz);
	}
	
	protected void addPlantLayers()
	{
		
	}
	
	protected abstract float getScaleByEntity(T entity);
	
}

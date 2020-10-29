package com.hungteen.pvz.render.entity.plant;

import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.render.layer.CharmLayer;
import com.hungteen.pvz.render.layer.EnergyLayer;
import com.hungteen.pvz.render.layer.PumpkinLayer;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class PVZPlantRender<T extends PVZPlantEntity> extends MobRenderer<T, EntityModel<T>>{

	public PVZPlantRender(EntityRendererManager rendererManager, EntityModel<T> entityModelIn, float shadowSizeIn) {
		super(rendererManager, entityModelIn, shadowSizeIn);
		this.addPlantLayers();
	}

	@Override
	protected void preRenderCallback(T entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
		float sz = getScaleByEntity(entitylivingbaseIn);
		Vec3d vec = getTranslateVec(entitylivingbaseIn);
		matrixStackIn.scale(sz,sz,sz);
		matrixStackIn.translate(vec.x, vec.y, vec.z);
	}
	
	protected void addPlantLayers(){
		this.addLayer(new EnergyLayer<>(this));
		this.addLayer(new CharmLayer<>(this));
		this.addLayer(new PumpkinLayer<>(this));
	}
	
	public abstract float getScaleByEntity(T entity);
	
	public Vec3d getTranslateVec(T entity) {
		return new Vec3d(0, 0, 0);
	}
	
}

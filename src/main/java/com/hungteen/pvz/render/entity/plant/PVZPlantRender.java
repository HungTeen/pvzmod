package com.hungteen.pvz.render.entity.plant;

import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.render.layer.PlantLadderLayer;
import com.hungteen.pvz.render.layer.PumpkinLayer;
import com.hungteen.pvz.render.layer.fullskin.CharmLayer;
import com.hungteen.pvz.render.layer.fullskin.EnergyLayer;
import com.hungteen.pvz.render.layer.fullskin.HealLightLayer;
import com.hungteen.pvz.render.layer.fullskin.SunLightLayer;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class PVZPlantRender<T extends PVZPlantEntity> extends MobRenderer<T, EntityModel<T>>{

	public PVZPlantRender(EntityRendererManager rendererManager, EntityModel<T> entityModelIn, float shadowSizeIn) {
		super(rendererManager, entityModelIn, shadowSizeIn);
		this.addPlantLayers();
	}

	@Override
	protected void scale(T entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
		float sz = getScaleByEntity(entitylivingbaseIn);
		Vector3d vec = getTranslateVec(entitylivingbaseIn);
		matrixStackIn.scale(sz, sz, sz);
		matrixStackIn.translate(vec.x, vec.y, vec.z);
	}
	
	protected void addPlantLayers(){
		this.addLayer(new EnergyLayer<>(this));
		this.addLayer(new CharmLayer<>(this));
		this.addLayer(new PumpkinLayer<>(this));
		this.addLayer(new SunLightLayer<>(this));
		this.addLayer(new HealLightLayer<>(this));
		this.addLayer(new PlantLadderLayer<>(this));
	}
	
	public abstract float getScaleByEntity(T entity);
	
	public Vector3d getTranslateVec(T entity) {
		return new Vector3d(0, 0, 0);
	}
	
}

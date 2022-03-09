package com.hungteen.pvz.client.render.entity.plant;

import com.hungteen.pvz.client.render.layer.PlantLadderLayer;
import com.hungteen.pvz.client.render.layer.PumpkinArmorLayer;
import com.hungteen.pvz.client.render.layer.fullskin.CharmLayer;
import com.hungteen.pvz.client.render.layer.fullskin.EnergyLayer;
import com.hungteen.pvz.client.render.layer.fullskin.HealLightLayer;
import com.hungteen.pvz.client.render.layer.fullskin.SunLightLayer;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.utils.AnimationUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class PVZPlantRender<T extends PVZPlantEntity> extends MobRenderer<T, EntityModel<T>>{

	private static final int BREATH_ANIM_CD = 40;
	
	public PVZPlantRender(EntityRendererManager rendererManager, EntityModel<T> entityModelIn, float shadowSizeIn) {
		super(rendererManager, entityModelIn, shadowSizeIn);
		this.addPlantLayers();
	}

	@Override
	protected void scale(T plant, MatrixStack matrixStackIn, float partialTickTime) {
		int live = plant.getExistTick() % BREATH_ANIM_CD;
		final float scaleOffset = AnimationUtil.upDown(live, BREATH_ANIM_CD, 0.01F);
		final float sz1 = getScaleByEntity(plant);
		final float sz = sz1 * (1 + scaleOffset);
		final Vector3d vec = getTranslateVec(plant);
		matrixStackIn.scale(sz, sz, sz);
		matrixStackIn.translate(vec.x, vec.y, vec.z);
	}
	
	protected void addPlantLayers(){
		this.addLayer(new EnergyLayer<>(this));
		this.addLayer(new CharmLayer<>(this));
		this.addLayer(new PumpkinArmorLayer<>(this));
		this.addLayer(new SunLightLayer<>(this));
		this.addLayer(new HealLightLayer<>(this));
		this.addLayer(new PlantLadderLayer<>(this));
	}
	
	public float getScaleByEntity(T entity) {
		return entity.getPlantType().getRenderScale();
	}
	
	public Vector3d getTranslateVec(T entity) {
		return new Vector3d(0, 0, 0);
	}
	
	@Override
	public ResourceLocation getTextureLocation(T entity) {
		return entity.getPlantType().getRenderResource();
	}
	
}

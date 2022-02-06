package com.hungteen.pvz.client.render.entity.zombie;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.render.entity.PVZCreatureRender;
import com.hungteen.pvz.client.render.layer.fullskin.CharmLayer;
import com.hungteen.pvz.client.render.layer.fullskin.EnergyLayer;
import com.hungteen.pvz.client.render.layer.fullskin.SunLayer;
import com.hungteen.pvz.client.render.layer.fullskin.SunLightLayer;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.potion.EffectRegister;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;

public abstract class PVZZombieRender <T extends PVZZombieEntity> extends PVZCreatureRender<T> {

	public static final float MINI_SCALE = 0.32F;
	
	public PVZZombieRender(EntityRendererManager rendererManager, EntityModel<T> entityModelIn, float shadowSizeIn) {
		super(rendererManager, entityModelIn, shadowSizeIn);
		this.addZombieLayers();
	}

	@Override
	protected boolean isBodyVisible(T livingEntityIn) {
		return super.isBodyVisible(livingEntityIn) || PVZMod.PROXY.getPlayer().hasEffect(EffectRegister.LIGHT_EYE_EFFECT.get());
	}
	
	protected void addZombieLayers() {
		this.addLayer(new EnergyLayer<>(this));
		this.addLayer(new CharmLayer<>(this));
		this.addLayer(new SunLightLayer<>(this));
		this.addLayer(new SunLayer<>(this));
	}
	
	@Override
	public Vector3d getTranslateVec(T entity) {
		float height = this.getOffsetRisingHeight();
		float downOffset = entity.getAnimTime() < 0 ? (- entity.getAnimTime() * 1.0f / PVZZombieEntity.RISING_CD) * height : 0;
		return new Vector3d(0, downOffset, 0);
	}
	
	@Override
	protected float getScaleByEntity(T entity) {
		final float sz = entity.getZombieType().getRenderScale();
		return entity.isMiniZombie() ? sz * MINI_SCALE : sz;
	}
	
	/**
	 * the height zombie rising from.
	 */
	protected float getOffsetRisingHeight() {
		return 1.6f;
	}
	
	@Override
	public ResourceLocation getTextureLocation(T entity) {
		return entity.getZombieType().getRenderResource();
	}
	
}
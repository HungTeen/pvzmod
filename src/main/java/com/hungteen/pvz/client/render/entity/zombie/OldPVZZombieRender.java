package com.hungteen.pvz.client.render.entity.zombie;

import com.hungteen.pvz.client.cache.ClientPlayerResources;
import com.hungteen.pvz.client.render.entity.PVZCreatureRender;
import com.hungteen.pvz.client.render.layer.fullskin.CharmLayer;
import com.hungteen.pvz.client.render.layer.fullskin.EnergyLayer;
import com.hungteen.pvz.client.render.layer.fullskin.SunLayer;
import com.hungteen.pvz.client.render.layer.fullskin.SunLightLayer;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class OldPVZZombieRender<T extends PVZZombieEntity> extends PVZCreatureRender<T>{

	public OldPVZZombieRender(EntityRendererManager rendererManager, EntityModel<T> entityModelIn, float shadowSizeIn) {
		super(rendererManager, entityModelIn, shadowSizeIn);
		this.addZombieLayers();
	}

	@Override
	protected boolean isBodyVisible(T livingEntityIn) {
		return super.isBodyVisible(livingEntityIn) || ClientPlayerResources.lightLevel > 1;
	}
	
	protected void addZombieLayers() {
		this.addLayer(new EnergyLayer<>(this));
		this.addLayer(new CharmLayer<>(this));
		this.addLayer(new SunLightLayer<>(this));
		this.addLayer(new SunLayer<>(this));
	}
	
}

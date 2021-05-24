package com.hungteen.pvz.render.entity.zombie;

import com.hungteen.pvz.capability.player.ClientPlayerResources;
import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.render.entity.PVZCreatureRender;
import com.hungteen.pvz.render.layer.fullskin.CharmLayer;
import com.hungteen.pvz.render.layer.fullskin.EnergyLayer;
import com.hungteen.pvz.render.layer.fullskin.SunLayer;
import com.hungteen.pvz.render.layer.fullskin.SunLightLayer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class PVZZombieRender<T extends PVZZombieEntity> extends PVZCreatureRender<T>{

	public PVZZombieRender(EntityRendererManager rendererManager, EntityModel<T> entityModelIn, float shadowSizeIn) {
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

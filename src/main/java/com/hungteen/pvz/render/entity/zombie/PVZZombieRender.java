package com.hungteen.pvz.render.entity.zombie;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.model.entity.IHasDefence;
import com.hungteen.pvz.render.entity.PVZCreatureRender;
import com.hungteen.pvz.render.layer.FrozenIceLayer;
import com.hungteen.pvz.render.layer.fullskin.CharmLayer;
import com.hungteen.pvz.render.layer.fullskin.ColdLayer;
import com.hungteen.pvz.render.layer.fullskin.EnergyLayer;
import com.mojang.blaze3d.matrix.MatrixStack;

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
	protected void preRenderCallback(T entity, MatrixStack matrixStackIn, float partialTickTime) {
		if(this.getEntityModel() instanceof IHasDefence) {
			((IHasDefence) this.getEntityModel()).setDestroyed(entity);
		}
		super.preRenderCallback(entity, matrixStackIn, partialTickTime);
	}
	
	protected void addZombieLayers(){
		this.addLayer(new ColdLayer<>(this));
		this.addLayer(new EnergyLayer<>(this));
		this.addLayer(new CharmLayer<>(this));
		this.addLayer(new FrozenIceLayer<>(this));
	}
	
}

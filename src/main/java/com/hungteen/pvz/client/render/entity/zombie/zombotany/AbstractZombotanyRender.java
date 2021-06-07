package com.hungteen.pvz.client.render.entity.zombie.zombotany;

import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.client.render.layer.DuckyTubeLayer;
import com.hungteen.pvz.common.entity.zombie.zombotany.AbstractZombotanyEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractZombotanyRender<T extends AbstractZombotanyEntity> extends PVZZombieRender<T>{

	public AbstractZombotanyRender(EntityRendererManager rendererManager, EntityModel<T> entityModelIn,
			float shadowSizeIn) {
		super(rendererManager, entityModelIn, shadowSizeIn);
	}
	
	@Override
	protected void addZombieLayers() {
		super.addZombieLayers();
		this.addLayer(new DuckyTubeLayer<T>(this));
	}
	
	@Override
	public Vector3d getTranslateVec(T entity) {
		if(entity.getAttackTime() >= 0 && entity.isInWater()) {
			return new Vector3d(0, 0.6f, 0);
		}
		return super.getTranslateVec(entity);
	}
	
}

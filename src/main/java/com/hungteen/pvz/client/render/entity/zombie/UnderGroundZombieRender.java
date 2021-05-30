package com.hungteen.pvz.client.render.entity.zombie;

import com.hungteen.pvz.common.entity.zombie.base.UnderGroundZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.math.vector.Vector3d;

public abstract class UnderGroundZombieRender<T extends UnderGroundZombieEntity> extends PVZZombieRender<T>{

	public UnderGroundZombieRender(EntityRendererManager rendererManager, EntityModel<T> entityModelIn,
			float shadowSizeIn) {
		super(rendererManager, entityModelIn, shadowSizeIn);
	}

	@Override
	public Vector3d getTranslateVec(T entity) {
		float height = this.getOffsetHeight();
		float downOffset = entity.getAttackTime() < 0 ? (- entity.getAttackTime() * 1.0f / entity.getSpawnTime()) * height : 0;
		return new Vector3d(0, downOffset, 0);
	}
	
	protected float getOffsetHeight() {
		return 1.6f;
	}

}

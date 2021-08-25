package com.hungteen.pvz.client.render.entity.zombie.pool;

import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.base.SwimmerZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Pose;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractSwimmerRender<T extends SwimmerZombieEntity> extends PVZZombieRender<T>{

	public AbstractSwimmerRender(EntityRendererManager rendererManager, EntityModel<T> entityModelIn,
			float shadowSizeIn) {
		super(rendererManager, entityModelIn, shadowSizeIn);
	}
	
	@Override
	public Vector3d getTranslateVec(T entity) {
		if(! entity.isMiniZombie()) {
			if(entity.getPose() == Pose.SWIMMING) return new Vector3d(0, 2.5f, 0);
			if(entity.getPose() == Pose.SPIN_ATTACK) return new Vector3d(0, 1.25f, 0);
		}
		return super.getTranslateVec(entity);
	}

}

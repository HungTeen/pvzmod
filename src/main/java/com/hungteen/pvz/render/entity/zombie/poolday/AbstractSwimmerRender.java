package com.hungteen.pvz.render.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.base.SwimmerZombieEntity;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Pose;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractSwimmerRender<T extends SwimmerZombieEntity> extends PVZZombieRender<T>{

	public AbstractSwimmerRender(EntityRendererManager rendererManager, EntityModel<T> entityModelIn,
			float shadowSizeIn) {
		super(rendererManager, entityModelIn, shadowSizeIn);
	}
	
	@Override
	public Vec3d getTranslateVec(T entity) {
		if(! entity.isMiniZombie()) {
			if(entity.getPose() == Pose.SWIMMING) return new Vec3d(0, 2.5f, 0);
			if(entity.getPose() == Pose.SPIN_ATTACK) return new Vec3d(0, 1.25f, 0);
		}
		return super.getTranslateVec(entity);
	}

	@Override
	protected float getScaleByEntity(T entity) {
		if(entity.isMiniZombie()) return 0.15F;
		return 0.5f;
	}

}

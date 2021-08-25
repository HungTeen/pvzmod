package com.hungteen.pvz.client.render.entity.zombie.pool;

import com.hungteen.pvz.client.model.entity.zombie.pool.PogoZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.pool.PogoZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.math.vector.Vector3d;

public class PogoZombieRender extends PVZZombieRender<PogoZombieEntity>{

	public PogoZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new PogoZombieModel(), 0.45f);
	}

	@Override
	public Vector3d getTranslateVec(PogoZombieEntity entity) {
		if(! entity.hasMetal()) return new Vector3d(0, 0.4, 0);
		return super.getTranslateVec(entity);
	}
	
}

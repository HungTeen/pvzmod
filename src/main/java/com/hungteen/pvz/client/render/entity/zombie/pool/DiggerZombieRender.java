package com.hungteen.pvz.client.render.entity.zombie.pool;

import com.hungteen.pvz.client.model.entity.zombie.pool.DiggerZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.pool.DiggerZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DiggerZombieRender extends PVZZombieRender<DiggerZombieEntity>{

	public DiggerZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new DiggerZombieModel(), 0.3f);
	}

	@Override
	public Vector3d getTranslateVec(DiggerZombieEntity entity) {
		double maxOffset = 4D;
		int now = entity.getAttackTime();
		int max = DiggerZombieEntity.MAX_OUT_TIME;
		return new Vector3d(0, maxOffset * ( 1 - now * 1.0D / max), 0);
	}

}

package com.hungteen.pvz.client.render.entity.zombie.poolnight;

import com.hungteen.pvz.client.model.entity.zombie.poolnight.BalloonZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.poolnight.BalloonZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class BalloonZombieRender extends PVZZombieRender<BalloonZombieEntity> {

	public BalloonZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new BalloonZombieModel(), 0);
	}

}

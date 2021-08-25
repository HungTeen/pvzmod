package com.hungteen.pvz.client.render.entity.zombie.pool;

import com.hungteen.pvz.client.model.entity.zombie.pool.SnorkelZombieModel;
import com.hungteen.pvz.common.entity.zombie.pool.SnorkelZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SnorkelZombieRender extends AbstractSwimmerRender<SnorkelZombieEntity>{

	public SnorkelZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new SnorkelZombieModel(), 0.5f);
	}
	
}

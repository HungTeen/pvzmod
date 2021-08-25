package com.hungteen.pvz.client.render.entity.zombie.pool;

import com.hungteen.pvz.client.model.entity.zombie.pool.ZomboniModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.pool.ZomboniEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZomboniRender extends PVZZombieRender<ZomboniEntity>{

	public ZomboniRender(EntityRendererManager rendererManager) {
		super(rendererManager, new ZomboniModel(), 0.5f);
	}
	
	@Override
	protected boolean isShaking(ZomboniEntity zombie) {
		return zombie.isCarShaking();
	}

}

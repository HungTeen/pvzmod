package com.hungteen.pvz.client.render.entity.zombie.roof;

import com.hungteen.pvz.client.model.entity.zombie.roof.CatapultZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.roof.CatapultZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CatapultZombieRender extends PVZZombieRender<CatapultZombieEntity> {

	public CatapultZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new CatapultZombieModel(), 0.5F);
	}

	@Override
	protected boolean isShaking(CatapultZombieEntity zombie) {
		return zombie.isCarShaking();
	}
}

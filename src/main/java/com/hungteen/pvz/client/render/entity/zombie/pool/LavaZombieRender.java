package com.hungteen.pvz.client.render.entity.zombie.pool;

import com.hungteen.pvz.client.model.entity.zombie.pool.LavaZombieModel;
import com.hungteen.pvz.common.entity.zombie.custom.LavaZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LavaZombieRender extends AbstractSwimmerRender<LavaZombieEntity>{

	public LavaZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new LavaZombieModel(), 0.5f);
	}

}

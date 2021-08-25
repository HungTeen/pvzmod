package com.hungteen.pvz.client.render.entity.zombie.pool;

import com.hungteen.pvz.client.model.entity.zombie.pool.JackInBoxZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.pool.JackInBoxZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JackInBoxZombieRender extends PVZZombieRender<JackInBoxZombieEntity>{

	public JackInBoxZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new JackInBoxZombieModel(), 0.45f);
	}
	
	@Override
	protected boolean isShaking(JackInBoxZombieEntity p_230495_1_) {
		return true;
	}

}

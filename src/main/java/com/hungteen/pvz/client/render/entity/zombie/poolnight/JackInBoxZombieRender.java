package com.hungteen.pvz.client.render.entity.zombie.poolnight;

import com.hungteen.pvz.client.model.entity.zombie.poolnight.JackInBoxZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.poolnight.JackInBoxZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class JackInBoxZombieRender extends PVZZombieRender<JackInBoxZombieEntity>{

	public JackInBoxZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new JackInBoxZombieModel(), 0.45f);
	}

}

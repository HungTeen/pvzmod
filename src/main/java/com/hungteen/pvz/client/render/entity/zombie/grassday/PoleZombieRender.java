package com.hungteen.pvz.client.render.entity.zombie.grassday;

import com.hungteen.pvz.client.model.entity.zombie.grassday.PoleZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.grassday.PoleZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PoleZombieRender extends PVZZombieRender<PoleZombieEntity>{

	public PoleZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new PoleZombieModel(), 0.5f);
	}

}

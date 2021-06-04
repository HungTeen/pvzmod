package com.hungteen.pvz.client.render.entity.zombie.poolday;

import com.hungteen.pvz.client.model.entity.zombie.poolday.BobsleZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.poolday.BobsleZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BobsleZombieRender extends PVZZombieRender<BobsleZombieEntity>{

	public BobsleZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new BobsleZombieModel(), 0.5f);
	}

}

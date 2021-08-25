package com.hungteen.pvz.client.render.entity.zombie.grass;

import com.hungteen.pvz.client.model.entity.zombie.grass.ScreenDoorZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.grass.ScreenDoorZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ScreenDoorZombieRender extends PVZZombieRender<ScreenDoorZombieEntity>{

	public ScreenDoorZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new ScreenDoorZombieModel(), 0.5f);
	}

}

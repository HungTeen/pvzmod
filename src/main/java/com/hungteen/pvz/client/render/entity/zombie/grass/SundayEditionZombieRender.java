package com.hungteen.pvz.client.render.entity.zombie.grass;

import com.hungteen.pvz.client.model.entity.zombie.grass.SundayEditionZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.grass.SundayEditionZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SundayEditionZombieRender extends PVZZombieRender<SundayEditionZombieEntity>{

	public SundayEditionZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new SundayEditionZombieModel(), 0.51F);
	}

	
}

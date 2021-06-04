package com.hungteen.pvz.client.render.entity.zombie.poolnight;

import com.hungteen.pvz.client.model.entity.zombie.poolnight.YetiZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.poolnight.YetiZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class YetiZombieRender extends PVZZombieRender<YetiZombieEntity>{

	public YetiZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new YetiZombieModel(), 0.5f);
	}

}

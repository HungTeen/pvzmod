package com.hungteen.pvz.client.render.entity.zombie.poolday;

import com.hungteen.pvz.client.model.entity.zombie.poolday.ZombieDolphinModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.poolday.ZombieDolphinEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZombieDolphinRender extends PVZZombieRender<ZombieDolphinEntity>{

	public ZombieDolphinRender(EntityRendererManager rendererManager) {
		super(rendererManager, new ZombieDolphinModel(), 0);
	}

}

package com.hungteen.pvz.client.render.entity.zombie.roof;

import com.hungteen.pvz.client.model.entity.zombie.roof.ZomBossModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.roof.ZomBossEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZomBossRender extends PVZZombieRender<ZomBossEntity> {

	public ZomBossRender(EntityRendererManager rendererManager) {
		super(rendererManager, new ZomBossModel(), 0);
	}
	
}

package com.hungteen.pvz.client.render.entity.zombie.poolday;

import com.hungteen.pvz.client.model.entity.zombie.poolday.BobsleTeamModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.poolday.BobsleTeamEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BobsleTeamRender extends PVZZombieRender<BobsleTeamEntity>{

	public BobsleTeamRender(EntityRendererManager rendererManager) {
		super(rendererManager, new BobsleTeamModel(), 0.6f);
	}

}

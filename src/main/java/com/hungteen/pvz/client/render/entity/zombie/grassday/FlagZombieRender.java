package com.hungteen.pvz.client.render.entity.zombie.grassday;

import com.hungteen.pvz.client.model.entity.zombie.grassday.FlagZombieModel;
import com.hungteen.pvz.common.entity.zombie.grassday.FlagZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlagZombieRender extends AbstractNormalRender<FlagZombieEntity>{

	public FlagZombieRender(EntityRendererManager rendererManager){
		super(rendererManager, new FlagZombieModel(), 0.5f);
	}

}

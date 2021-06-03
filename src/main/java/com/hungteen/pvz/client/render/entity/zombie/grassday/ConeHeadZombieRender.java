package com.hungteen.pvz.client.render.entity.zombie.grassday;

import com.hungteen.pvz.client.model.entity.zombie.grassday.ConeHeadZombieModel;
import com.hungteen.pvz.common.entity.zombie.grassday.ConeHeadZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ConeHeadZombieRender extends AbstractNormalRender<ConeHeadZombieEntity>{

	public ConeHeadZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new ConeHeadZombieModel() ,0.5f);
	}

}

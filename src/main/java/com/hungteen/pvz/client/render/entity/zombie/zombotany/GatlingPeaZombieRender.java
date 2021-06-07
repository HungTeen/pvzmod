package com.hungteen.pvz.client.render.entity.zombie.zombotany;

import com.hungteen.pvz.client.model.entity.zombie.zombotany.GatlingPeaZombieModel;
import com.hungteen.pvz.common.entity.zombie.zombotany.GatlingPeaZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GatlingPeaZombieRender extends AbstractZombotanyRender<GatlingPeaZombieEntity> {

	public GatlingPeaZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new GatlingPeaZombieModel(), 0.4F);
	}

}

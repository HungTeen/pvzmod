package com.hungteen.pvz.client.render.entity.zombie.zombotany;

import com.hungteen.pvz.client.model.entity.zombie.zombotany.PeaShooterZombieModel;
import com.hungteen.pvz.common.entity.zombie.zombotany.PeaShooterZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PeaShooterZombieRender extends AbstractZombotanyRender<PeaShooterZombieEntity> {

	public PeaShooterZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new PeaShooterZombieModel(), 0.4F);
	}

}

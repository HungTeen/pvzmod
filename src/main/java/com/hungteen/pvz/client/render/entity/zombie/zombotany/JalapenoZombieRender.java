package com.hungteen.pvz.client.render.entity.zombie.zombotany;

import com.hungteen.pvz.client.model.entity.zombie.zombotany.JalapenoZombieModel;
import com.hungteen.pvz.common.entity.zombie.zombotany.JalapenoZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;

public class JalapenoZombieRender extends AbstractZombotanyRender<JalapenoZombieEntity> {

	public JalapenoZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new JalapenoZombieModel(), 0.4F);
	}

}

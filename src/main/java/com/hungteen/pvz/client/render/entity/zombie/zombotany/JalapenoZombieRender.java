package com.hungteen.pvz.client.render.entity.zombie.zombotany;

import com.hungteen.pvz.client.model.entity.zombie.zombotany.JalapenoZombieModel;
import com.hungteen.pvz.common.entity.zombie.zombotany.JalapenoZombieEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class JalapenoZombieRender extends AbstractZombotanyRender<JalapenoZombieEntity> {

	private static final ResourceLocation JALAPENO_ZOMBIE_TEX = StringUtil.prefix("textures/entity/zombie/plantzombie/jalapeno_zombie.png");
	
	public JalapenoZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new JalapenoZombieModel(), 0.4F);
	}

	@Override
	public ResourceLocation getTextureLocation(JalapenoZombieEntity entity) {
		return JALAPENO_ZOMBIE_TEX;
	}

}

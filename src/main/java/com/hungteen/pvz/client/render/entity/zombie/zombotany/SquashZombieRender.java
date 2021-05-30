package com.hungteen.pvz.client.render.entity.zombie.zombotany;

import com.hungteen.pvz.client.model.entity.zombie.zombotany.SquashZombieModel;
import com.hungteen.pvz.common.entity.zombie.zombotany.SquashZombieEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class SquashZombieRender extends AbstractZombotanyRender<SquashZombieEntity> {

	private static final ResourceLocation SQUASH_ZOMBIE_TEX = StringUtil.prefix("textures/entity/zombie/plantzombie/squash_zombie.png");
	
	public SquashZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new SquashZombieModel(), 0.4F);
	}

	@Override
	public ResourceLocation getTextureLocation(SquashZombieEntity entity) {
		return SQUASH_ZOMBIE_TEX;
	}

}

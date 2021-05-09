package com.hungteen.pvz.render.entity.zombie.zombotany;

import com.hungteen.pvz.entity.zombie.zombotany.TallNutZombieEntity;
import com.hungteen.pvz.model.entity.zombie.plantzombie.TallNutZombieModel;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class TallNutZombieRender extends AbstractZombotanyRender<TallNutZombieEntity> {

	private static final ResourceLocation TALLNUT_ZOMBIE_TEX = StringUtil.prefix("textures/entity/zombie/plantzombie/tallnut_zombie.png");
	
	public TallNutZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new TallNutZombieModel(), 0.4F);
	}

	@Override
	public ResourceLocation getTextureLocation(TallNutZombieEntity entity) {
		return TALLNUT_ZOMBIE_TEX;
	}

}

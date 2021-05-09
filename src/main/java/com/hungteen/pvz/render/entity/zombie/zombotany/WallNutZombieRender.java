package com.hungteen.pvz.render.entity.zombie.zombotany;

import com.hungteen.pvz.entity.zombie.zombotany.WallNutZombieEntity;
import com.hungteen.pvz.model.entity.zombie.plantzombie.WallNutZombieModel;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class WallNutZombieRender extends AbstractZombotanyRender<WallNutZombieEntity> {

	private static final ResourceLocation WALLNUT_ZOMBIE_TEX = StringUtil.prefix("textures/entity/zombie/plantzombie/wallnut_zombie.png");
	
	public WallNutZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new WallNutZombieModel(), 0.4F);
	}

	@Override
	public ResourceLocation getTextureLocation(WallNutZombieEntity entity) {
		return WALLNUT_ZOMBIE_TEX;
	}

}

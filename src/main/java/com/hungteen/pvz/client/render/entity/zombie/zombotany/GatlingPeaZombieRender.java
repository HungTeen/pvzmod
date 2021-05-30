package com.hungteen.pvz.client.render.entity.zombie.zombotany;

import com.hungteen.pvz.client.model.entity.zombie.zombotany.GatlingPeaZombieModel;
import com.hungteen.pvz.common.entity.zombie.zombotany.GatlingPeaZombieEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GatlingPeaZombieRender extends AbstractZombotanyRender<GatlingPeaZombieEntity> {

	private static final ResourceLocation GATLINGPEA_ZOMBIE_TEX = StringUtil.prefix("textures/entity/zombie/plantzombie/gatlingpea_zombie.png");
	
	public GatlingPeaZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new GatlingPeaZombieModel(), 0.4F);
	}

	@Override
	public ResourceLocation getTextureLocation(GatlingPeaZombieEntity entity) {
		return GATLINGPEA_ZOMBIE_TEX;
	}

}

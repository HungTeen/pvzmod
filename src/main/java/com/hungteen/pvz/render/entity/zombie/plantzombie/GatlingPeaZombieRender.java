package com.hungteen.pvz.render.entity.zombie.plantzombie;

import com.hungteen.pvz.entity.zombie.plantzombie.GatlingPeaZombieEntity;
import com.hungteen.pvz.model.entity.zombie.plantzombie.GatlingPeaZombieModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GatlingPeaZombieRender extends PVZZombieRender<GatlingPeaZombieEntity> {

	private static final ResourceLocation GATLINGPEA_ZOMBIE_TEX = StringUtil.prefix("textures/entity/zombie/plantzombie/gatlingpea_zombie.png");
	
	public GatlingPeaZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new GatlingPeaZombieModel(), 0.4F);
	}

	@Override
	protected float getScaleByEntity(GatlingPeaZombieEntity entity) {
		return 0.5F;
	}

	@Override
	public ResourceLocation getEntityTexture(GatlingPeaZombieEntity entity) {
		return GATLINGPEA_ZOMBIE_TEX;
	}

}

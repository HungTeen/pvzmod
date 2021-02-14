package com.hungteen.pvz.render.entity.zombie.plantzombie;

import com.hungteen.pvz.entity.zombie.plantzombie.PeaShooterZombieEntity;
import com.hungteen.pvz.model.entity.zombie.plantzombie.PeaShooterZombieModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PeaShooterZombieRender extends PVZZombieRender<PeaShooterZombieEntity> {

	private static final ResourceLocation PEASHOOTER_ZOMBIE_TEX = StringUtil.prefix("textures/entity/zombie/plantzombie/peashooter_zombie.png");
	
	public PeaShooterZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new PeaShooterZombieModel(), 0.4F);
	}

	@Override
	protected float getScaleByEntity(PeaShooterZombieEntity entity) {
		return 0.5F;
	}

	@Override
	public ResourceLocation getEntityTexture(PeaShooterZombieEntity entity) {
		return PEASHOOTER_ZOMBIE_TEX;
	}

}

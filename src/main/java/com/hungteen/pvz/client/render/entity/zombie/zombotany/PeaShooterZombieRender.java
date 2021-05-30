package com.hungteen.pvz.client.render.entity.zombie.zombotany;

import com.hungteen.pvz.client.model.entity.zombie.zombotany.PeaShooterZombieModel;
import com.hungteen.pvz.common.entity.zombie.zombotany.PeaShooterZombieEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PeaShooterZombieRender extends AbstractZombotanyRender<PeaShooterZombieEntity> {

	private static final ResourceLocation PEASHOOTER_ZOMBIE_TEX = StringUtil.prefix("textures/entity/zombie/plantzombie/peashooter_zombie.png");
	
	public PeaShooterZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new PeaShooterZombieModel(), 0.4F);
	}

	@Override
	public ResourceLocation getTextureLocation(PeaShooterZombieEntity entity) {
		return PEASHOOTER_ZOMBIE_TEX;
	}

}

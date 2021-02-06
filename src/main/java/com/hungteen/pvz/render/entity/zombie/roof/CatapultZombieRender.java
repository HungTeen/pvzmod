package com.hungteen.pvz.render.entity.zombie.roof;

import com.hungteen.pvz.entity.zombie.roof.CatapultZombieEntity;
import com.hungteen.pvz.model.entity.zombie.roof.CatapultZombieModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CatapultZombieRender extends PVZZombieRender<CatapultZombieEntity> {

	private static final ResourceLocation CATAPULT_TEX = StringUtil.prefix("textures/entity/zombie/roof/catapult_zombie.png");
	
	public CatapultZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new CatapultZombieModel(), 0.5F);
	}

	@Override
	protected float getScaleByEntity(CatapultZombieEntity entity) {
		if(entity.isMiniZombie()) return 0.25F;
		return 0.5F;
	}

	@Override
	public ResourceLocation getEntityTexture(CatapultZombieEntity entity) {
		return CATAPULT_TEX;
	}

}

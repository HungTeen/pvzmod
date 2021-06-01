package com.hungteen.pvz.client.render.entity.zombie.roof;

import com.hungteen.pvz.client.model.entity.zombie.roof.CatapultZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.OldPVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.roof.CatapultZombieEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CatapultZombieRender extends OldPVZZombieRender<CatapultZombieEntity> {

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
	public ResourceLocation getTextureLocation(CatapultZombieEntity entity) {
		return CATAPULT_TEX;
	}

}

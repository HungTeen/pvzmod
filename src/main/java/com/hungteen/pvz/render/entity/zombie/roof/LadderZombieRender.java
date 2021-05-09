package com.hungteen.pvz.render.entity.zombie.roof;

import com.hungteen.pvz.entity.zombie.roof.LadderZombieEntity;
import com.hungteen.pvz.model.entity.zombie.roof.LadderZombieModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LadderZombieRender extends PVZZombieRender<LadderZombieEntity> {

	private static final ResourceLocation LADDER_TEX = StringUtil.prefix("textures/entity/zombie/roof/ladder_zombie.png");
	
	public LadderZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new LadderZombieModel(), 0.5F);
	}

	@Override
	protected float getScaleByEntity(LadderZombieEntity entity) {
		if(entity.isMiniZombie()) return 0.15F;
		return 0.5F;
	}

	@Override
	public ResourceLocation getTextureLocation(LadderZombieEntity entity) {
		return LADDER_TEX;
	}
	
}

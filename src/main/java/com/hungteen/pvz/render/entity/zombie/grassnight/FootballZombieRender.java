package com.hungteen.pvz.render.entity.zombie.grassnight;

import com.hungteen.pvz.entity.zombie.grassnight.FootballZombieEntity;
import com.hungteen.pvz.model.entity.zombie.grassnight.FootballZombieModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FootballZombieRender extends PVZZombieRender<FootballZombieEntity>{

	public FootballZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new FootballZombieModel(), 0.5f);
	}

	@Override
	protected float getScaleByEntity(FootballZombieEntity entity) {
		if(entity.isMiniZombie()) return 0.17F;
		return 0.5f;
	}

	@Override
	public ResourceLocation getEntityTexture(FootballZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/grassnight/football_zombie.png");
	}

}

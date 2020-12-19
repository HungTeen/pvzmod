package com.hungteen.pvz.render.entity.zombie.poolnight;

import com.hungteen.pvz.entity.zombie.poolnight.PogoZombieEntity;
import com.hungteen.pvz.model.entity.zombie.poolnight.PogoZombieModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class PogoZombieRender extends PVZZombieRender<PogoZombieEntity>{

	public PogoZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new PogoZombieModel(), 0.45f);
	}

	@Override
	protected float getScaleByEntity(PogoZombieEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getEntityTexture(PogoZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/poolnight/pogo_zombie.png");
	}

}

package com.hungteen.pvz.render.entity.zombie.poolday;

import com.hungteen.pvz.entity.misc.DuckyTubeEntity;
import com.hungteen.pvz.model.entity.zombie.poolday.DuckyTubeModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieToolRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class DuckyTubeRender extends PVZZombieToolRender<DuckyTubeEntity>{

	public DuckyTubeRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new DuckyTubeModel(), 0);
	}

	@Override
	protected float getScaleByEntity(DuckyTubeEntity entity) {
		return 0.6f;
	}

	@Override
	public ResourceLocation getEntityTexture(DuckyTubeEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/poolday/ducky_tube.png");
	}

}

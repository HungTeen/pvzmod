package com.hungteen.pvz.render.entity.zombie.poolnight;

import com.hungteen.pvz.entity.zombie.poolnight.PogoZombieEntity;
import com.hungteen.pvz.model.entity.zombie.poolnight.PogoZombieModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class PogoZombieRender extends PVZZombieRender<PogoZombieEntity>{

	public PogoZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new PogoZombieModel(), 0.45f);
	}

	@Override
	protected float getScaleByEntity(PogoZombieEntity entity) {
		return 0.5f;
	}

	@Override
	public Vec3d getTranslateVec(PogoZombieEntity entity) {
		return super.getTranslateVec(entity);
	}
	
	@Override
	public ResourceLocation getEntityTexture(PogoZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/poolnight/pogo_zombie.png");
	}

}

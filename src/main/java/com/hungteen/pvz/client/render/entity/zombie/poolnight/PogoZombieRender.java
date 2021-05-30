package com.hungteen.pvz.client.render.entity.zombie.poolnight;

import com.hungteen.pvz.client.model.entity.zombie.poolnight.PogoZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.poolnight.PogoZombieEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;

public class PogoZombieRender extends PVZZombieRender<PogoZombieEntity>{

	public PogoZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new PogoZombieModel(), 0.45f);
	}

	@Override
	protected float getScaleByEntity(PogoZombieEntity entity) {
		if(entity.isMiniZombie()) return 0.15F;
		return 0.5f;
	}

	@Override
	public Vector3d getTranslateVec(PogoZombieEntity entity) {
		if(! entity.hasMetal()) return new Vector3d(0, 0.4, 0);
		return super.getTranslateVec(entity);
	}
	
	@Override
	public ResourceLocation getTextureLocation(PogoZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/poolnight/pogo_zombie.png");
	}

}

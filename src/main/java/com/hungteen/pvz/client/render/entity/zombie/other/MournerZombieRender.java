package com.hungteen.pvz.client.render.entity.zombie.other;

import com.hungteen.pvz.client.model.entity.zombie.other.MournerZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.other.MournerZombieEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class MournerZombieRender extends PVZZombieRender<MournerZombieEntity>{

	public MournerZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new MournerZombieModel(), 0.5f);
	}

	@Override
	protected float getScaleByEntity(MournerZombieEntity entity) {
		if(entity.isMiniZombie()) return 0.15F;
		return 0.5f;
	}

	@Override
	public ResourceLocation getTextureLocation(MournerZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/other/mourner_zombie.png");
	}

}

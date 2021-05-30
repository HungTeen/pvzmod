package com.hungteen.pvz.client.render.entity.zombie.grassday;

import com.hungteen.pvz.client.model.entity.zombie.grassday.PoleZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.grassday.PoleZombieEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PoleZombieRender extends PVZZombieRender<PoleZombieEntity>{

	public PoleZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new PoleZombieModel(), 0.5f);
	}

	@Override
	protected float getScaleByEntity(PoleZombieEntity entity) {
		if(entity.isMiniZombie()) return 0.15F;
		return 0.5f;
	}

	@Override
	public ResourceLocation getTextureLocation(PoleZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/grassday/pole_zombie.png");
	}

}

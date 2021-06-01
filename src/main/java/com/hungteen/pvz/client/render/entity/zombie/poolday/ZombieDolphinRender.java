package com.hungteen.pvz.client.render.entity.zombie.poolday;

import com.hungteen.pvz.client.model.entity.zombie.poolday.ZombieDolphinModel;
import com.hungteen.pvz.client.render.entity.zombie.OldPVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.poolday.ZombieDolphinEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZombieDolphinRender extends OldPVZZombieRender<ZombieDolphinEntity>{

	public ZombieDolphinRender(EntityRendererManager rendererManager) {
		super(rendererManager, new ZombieDolphinModel(), 0);
	}

	@Override
	protected float getScaleByEntity(ZombieDolphinEntity entity) {
		return 1f;
	}

	@Override
	public ResourceLocation getTextureLocation(ZombieDolphinEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/poolday/zombie_dolphin.png");
	}

}

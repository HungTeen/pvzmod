package com.hungteen.pvz.client.render.entity.zombie.poolnight;

import com.hungteen.pvz.client.model.entity.zombie.poolnight.YetiZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.OldPVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.poolnight.YetiZombieEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class YetiZombieRender extends OldPVZZombieRender<YetiZombieEntity>{

	public YetiZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new YetiZombieModel(), 0.5f);
	}

	@Override
	protected float getScaleByEntity(YetiZombieEntity entity) {
		if(entity.isMiniZombie()) return 0.2F;
		return 0.42f;
	}

	@Override
	public ResourceLocation getTextureLocation(YetiZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/poolnight/yeti_zombie.png");
	}

}

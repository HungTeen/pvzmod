package com.hungteen.pvz.render.entity.zombie.poolnight;

import com.hungteen.pvz.entity.zombie.poolnight.YetiZombieEntity;
import com.hungteen.pvz.model.entity.zombie.poolnight.YetiZombieModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class YetiZombieRender extends PVZZombieRender<YetiZombieEntity>{

	public YetiZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new YetiZombieModel(), 0.5f);
	}

	@Override
	protected float getScaleByEntity(YetiZombieEntity entity) {
		return 0.42f;
	}

	@Override
	public ResourceLocation getEntityTexture(YetiZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/poolnight/yeti_zombie.png");
	}

}

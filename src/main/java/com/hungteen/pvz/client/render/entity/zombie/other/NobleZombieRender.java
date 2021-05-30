package com.hungteen.pvz.client.render.entity.zombie.other;

import com.hungteen.pvz.client.model.entity.zombie.other.NobleZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.other.NobleZombieEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NobleZombieRender extends PVZZombieRender<NobleZombieEntity>{

	public NobleZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new NobleZombieModel(), 0.5f);
	}

	@Override
	protected float getScaleByEntity(NobleZombieEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getTextureLocation(NobleZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/other/noble_zombie.png");
	}

}

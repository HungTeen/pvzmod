package com.hungteen.pvz.render.entity.zombie.other;

import com.hungteen.pvz.entity.zombie.other.TrickZombieEntity;
import com.hungteen.pvz.model.entity.zombie.other.TrickZombieModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TrickZombieRender extends PVZZombieRender<TrickZombieEntity>{

	public TrickZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new TrickZombieModel(), 0.3f);
	}

	@Override
	protected float getScaleByEntity(TrickZombieEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getEntityTexture(TrickZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/other/trick_zombie.png");
	}

}

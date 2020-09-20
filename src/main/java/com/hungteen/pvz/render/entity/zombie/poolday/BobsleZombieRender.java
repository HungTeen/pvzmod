package com.hungteen.pvz.render.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.poolday.BobsleZombieEntity;
import com.hungteen.pvz.model.entity.zombie.poolday.BobsleZombieModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BobsleZombieRender extends PVZZombieRender<BobsleZombieEntity>{

	public BobsleZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new BobsleZombieModel(), 0.5f);
	}

	@Override
	protected float getScaleByEntity(BobsleZombieEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getEntityTexture(BobsleZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/poolday/bobsle_zombie.png");
	}

}

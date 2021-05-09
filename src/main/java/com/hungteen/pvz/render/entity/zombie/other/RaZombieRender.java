package com.hungteen.pvz.render.entity.zombie.other;

import com.hungteen.pvz.entity.zombie.other.RaZombieEntity;
import com.hungteen.pvz.model.entity.zombie.other.RaZombieModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RaZombieRender extends PVZZombieRender<RaZombieEntity> {

	public RaZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new RaZombieModel(), 0.5F);
	}

	@Override
	protected float getScaleByEntity(RaZombieEntity entity) {
		if(entity.isMiniZombie()) return 0.15F;
		return 0.5F;
	}

	@Override
	public ResourceLocation getTextureLocation(RaZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/other/ra_zombie.png");
	}

}

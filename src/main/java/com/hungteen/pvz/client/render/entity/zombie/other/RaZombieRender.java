package com.hungteen.pvz.client.render.entity.zombie.other;

import com.hungteen.pvz.client.model.entity.zombie.other.RaZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.OldPVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.other.RaZombieEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RaZombieRender extends OldPVZZombieRender<RaZombieEntity> {

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

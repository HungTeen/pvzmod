package com.hungteen.pvz.render.entity.zombie.plantzombie;

import com.hungteen.pvz.entity.zombie.plantzombie.PumpkinZombieEntity;
import com.hungteen.pvz.model.entity.zombie.plantzombie.PumpkinZombieModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PumpkinZombieRender extends PVZZombieRender<PumpkinZombieEntity>{

	public PumpkinZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new PumpkinZombieModel(), 0.5f);
	}

	@Override
	protected float getScaleByEntity(PumpkinZombieEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getEntityTexture(PumpkinZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/plantzombie/pumpkin_zombie.png");
	}

}

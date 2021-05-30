package com.hungteen.pvz.client.render.entity.zombie.zombotany;

import com.hungteen.pvz.client.model.entity.zombie.zombotany.PumpkinZombieModel;
import com.hungteen.pvz.common.entity.zombie.zombotany.PumpkinZombieEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PumpkinZombieRender extends AbstractZombotanyRender<PumpkinZombieEntity>{

	public PumpkinZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new PumpkinZombieModel(), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(PumpkinZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/plantzombie/pumpkin_zombie.png");
	}

}

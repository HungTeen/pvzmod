package com.hungteen.pvz.render.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.poolday.SnorkelZombieEntity;
import com.hungteen.pvz.model.entity.zombie.poolday.SnorkelZombieModel;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SnorkelZombieRender extends AbstractSwimmerRender<SnorkelZombieEntity>{

	public SnorkelZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new SnorkelZombieModel(), 0.5f);
	}
	
	@Override
	public ResourceLocation getEntityTexture(SnorkelZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/poolday/snorkel_zombie.png");
	}

}

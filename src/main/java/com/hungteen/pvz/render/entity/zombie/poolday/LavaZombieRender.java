package com.hungteen.pvz.render.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.poolday.LavaZombieEntity;
import com.hungteen.pvz.model.entity.zombie.poolday.LavaZombieModel;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LavaZombieRender extends AbstractSwimmerRender<LavaZombieEntity>{

	public LavaZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new LavaZombieModel(), 0.5f);
	}

	@Override
	public ResourceLocation getEntityTexture(LavaZombieEntity entity) {
		float life = entity.getHealth();
		float max = entity.getMaxHealth();
		if(life>=max*2/3) return StringUtil.prefix("textures/entity/zombie/poolday/lava_zombie1.png");
		if(life>=max/3) return StringUtil.prefix("textures/entity/zombie/poolday/lava_zombie2.png");
		return StringUtil.prefix("textures/entity/zombie/poolday/lava_zombie3.png");
	}

}

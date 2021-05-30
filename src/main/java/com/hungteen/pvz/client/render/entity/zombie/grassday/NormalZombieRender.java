package com.hungteen.pvz.client.render.entity.zombie.grassday;

import com.hungteen.pvz.client.model.entity.zombie.grassday.NormalZombieModel;
import com.hungteen.pvz.common.entity.zombie.grassday.NormalZombieEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NormalZombieRender extends AbstractNormalRender<NormalZombieEntity>{

	public static final ResourceLocation TEXTURE = StringUtil.prefix("textures/entity/zombie/grassday/normal_zombie.png");
	
	public NormalZombieRender(EntityRendererManager rendererManager){
		super(rendererManager, new NormalZombieModel(), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(NormalZombieEntity entity) {
		return TEXTURE;
	}

}

package com.hungteen.pvz.client.render.entity.zombie.grassday;

import com.hungteen.pvz.client.model.entity.zombie.grassday.ConeHeadZombieModel;
import com.hungteen.pvz.common.entity.zombie.grassday.ConeHeadZombieEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ConeHeadZombieRender extends AbstractNormalRender<ConeHeadZombieEntity>{

	public ConeHeadZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new ConeHeadZombieModel() ,0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(ConeHeadZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/grassday/conehead_zombie.png");
	}

}

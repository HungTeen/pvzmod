package com.hungteen.pvz.render.entity.zombie.grassday;

import com.hungteen.pvz.entity.zombie.grassday.ConeHeadZombieEntity;
import com.hungteen.pvz.model.entity.zombie.grassday.ConeHeadZombieModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ConeHeadZombieRender extends PVZZombieRender<ConeHeadZombieEntity>{

	public ConeHeadZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new ConeHeadZombieModel() ,0.5f);
	}

	@Override
	protected float getScaleByEntity(ConeHeadZombieEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getEntityTexture(ConeHeadZombieEntity entity) {
		double hp=entity.getHealth();
		double max=entity.getMaxHealth();
		if(hp<=max/4) return StringUtil.prefix("textures/entity/zombie/grassday/conehead_zombie4.png");
		if(hp<=max/2) return StringUtil.prefix("textures/entity/zombie/grassday/conehead_zombie3.png");
		if(hp<=max*3/4) return StringUtil.prefix("textures/entity/zombie/grassday/conehead_zombie2.png");
		return StringUtil.prefix("textures/entity/zombie/grassday/conehead_zombie1.png");
	}

}

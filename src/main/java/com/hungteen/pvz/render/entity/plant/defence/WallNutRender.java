package com.hungteen.pvz.render.entity.plant.defence;

import com.hungteen.pvz.entity.plant.defence.WallNutEntity;
import com.hungteen.pvz.model.entity.plant.defence.WallNutModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class WallNutRender extends PVZPlantRender<WallNutEntity>{

	public WallNutRender(EntityRendererManager rendererManager) {
		super(rendererManager, new WallNutModel(), 0.6f);
	}

	@Override
	protected float getScaleByEntity(WallNutEntity entity) {
		return 0.4f;
	}

	@Override
	public ResourceLocation getEntityTexture(WallNutEntity entity) {
		float life=entity.getHealth();
    	float normalLife=entity.getLife();
    	float superLife=entity.getSuperLife();
    	if(life>normalLife) {
    		life-=normalLife;
    		if(life>2*superLife/3) return StringUtil.prefix("textures/entity/plant/defence/wall_nut4.png");
    		else if(life>superLife/3) return StringUtil.prefix("textures/entity/plant/defence/wall_nut5.png");
    		return StringUtil.prefix("textures/entity/plant/defence/wall_nut6.png");
    	}
    	else {
    		if(life>2*normalLife/3) return StringUtil.prefix("textures/entity/plant/defence/wall_nut1.png");
    		else if(life>normalLife/3) return StringUtil.prefix("textures/entity/plant/defence/wall_nut2.png");
    		return StringUtil.prefix("textures/entity/plant/defence/wall_nut3.png");
    	}
	}

}

package com.hungteen.pvz.render.entity.plant.defence;

import com.hungteen.pvz.entity.plant.defence.WallNutEntity;
import com.hungteen.pvz.model.entity.plant.defence.WallNutModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WallNutRender extends PVZPlantRender<WallNutEntity>{

	public WallNutRender(EntityRendererManager rendererManager) {
		super(rendererManager, new WallNutModel(), 0.6f);
	}

	@Override
	public float getScaleByEntity(WallNutEntity entity) {
		return 0.4f;
	}

	@Override
	public ResourceLocation getEntityTexture(WallNutEntity entity) {
		float life=entity.getHealth();
		float maxLife = entity.getMaxHealth();
		float defLife = entity.getDefenceLife();
		float maxDefLife = entity.getSuperLife();
    	if(defLife>0) {
    		if(defLife>2*maxDefLife/3) return StringUtil.prefix("textures/entity/plant/defence/wall_nut4.png");
    		else if(defLife>maxDefLife/3) return StringUtil.prefix("textures/entity/plant/defence/wall_nut5.png");
    		return StringUtil.prefix("textures/entity/plant/defence/wall_nut6.png");
    	}
    	else {
    		if(life>2*maxLife/3) return StringUtil.prefix("textures/entity/plant/defence/wall_nut1.png");
    		else if(life>maxLife/3) return StringUtil.prefix("textures/entity/plant/defence/wall_nut2.png");
    		return StringUtil.prefix("textures/entity/plant/defence/wall_nut3.png");
    	}
	}

}

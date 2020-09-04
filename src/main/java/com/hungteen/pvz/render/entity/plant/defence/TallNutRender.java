package com.hungteen.pvz.render.entity.plant.defence;

import com.hungteen.pvz.entity.plant.defence.TallNutEntity;
import com.hungteen.pvz.model.entity.plant.defence.TallNutModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TallNutRender extends PVZPlantRender<TallNutEntity>{

	public TallNutRender(EntityRendererManager rendererManager) {
		super(rendererManager, new TallNutModel(), 0.45f);
	}

	@Override
	protected float getScaleByEntity(TallNutEntity entity) {
		return 0.4f;
	}

	@Override
	public ResourceLocation getEntityTexture(TallNutEntity entity) {
		float life=entity.getHealth();
		float maxLife = entity.getMaxHealth();
		float defLife = entity.getDefenceLife();
		float maxDefLife = entity.getSuperLife();
		if(defLife>0) {
    		if(defLife>2*maxDefLife/3) return StringUtil.prefix("textures/entity/plant/defence/tall_nut4.png");
    		else if(defLife>maxDefLife/3) return StringUtil.prefix("textures/entity/plant/defence/tall_nut5.png");
    		return StringUtil.prefix("textures/entity/plant/defence/tall_nut6.png");
    	}
    	else {
    		if(life>2*maxLife/3) return StringUtil.prefix("textures/entity/plant/defence/tall_nut1.png");
    		else if(life>maxLife/3) return StringUtil.prefix("textures/entity/plant/defence/tall_nut2.png");
    		return StringUtil.prefix("textures/entity/plant/defence/tall_nut3.png");
    	}
	}

}

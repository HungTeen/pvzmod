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

	public static final ResourceLocation TEXTURE1 = StringUtil.prefix("textures/entity/plant/defence/wall_nut1.png");
	public static final ResourceLocation TEXTURE2 = StringUtil.prefix("textures/entity/plant/defence/wall_nut2.png");
	public static final ResourceLocation TEXTURE3 = StringUtil.prefix("textures/entity/plant/defence/wall_nut3.png");
	public static final ResourceLocation TEXTURE4 = StringUtil.prefix("textures/entity/plant/defence/wall_nut4.png");
	public static final ResourceLocation TEXTURE5 = StringUtil.prefix("textures/entity/plant/defence/wall_nut5.png");
	public static final ResourceLocation TEXTURE6 = StringUtil.prefix("textures/entity/plant/defence/wall_nut6.png");
	
	public WallNutRender(EntityRendererManager rendererManager) {
		super(rendererManager, new WallNutModel<WallNutEntity>(), 0.6f);
	}

	@Override
	public float getScaleByEntity(WallNutEntity entity) {
		return 0.4f;
	}

	@Override
	public ResourceLocation getEntityTexture(WallNutEntity entity) {
		float life = entity.getHealth();
		float maxLife = entity.getMaxHealth();
		float defLife = entity.getDefenceLife();
		float maxDefLife = entity.getSuperLife();
    	if(defLife > 0) {
    		if(defLife > 2 * maxDefLife / 3) return TEXTURE4;
    		else if(defLife > maxDefLife / 3) return TEXTURE5;
    		return TEXTURE6;
    	} else {
    		if(life > 2 * maxLife / 3) return TEXTURE1;
    		else if(life > maxLife / 3) return TEXTURE2;
    		return TEXTURE3;
    	}
	}

}

package com.hungteen.pvz.render.entity.plant.explosion;

import com.hungteen.pvz.entity.plant.explosion.ExplodeONutEntity;
import com.hungteen.pvz.model.entity.plant.defence.WallNutModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ExplodeONutRender extends PVZPlantRender<ExplodeONutEntity> {

	public static final ResourceLocation TEXTURE1 = StringUtil.prefix("textures/entity/plant/explosion/explode_o_nut1.png");
	private static final ResourceLocation TEXTURE2 = StringUtil.prefix("textures/entity/plant/explosion/explode_o_nut2.png");
	private static final ResourceLocation TEXTURE3 = StringUtil.prefix("textures/entity/plant/explosion/explode_o_nut3.png");
	private static final ResourceLocation TEXTURE4 = StringUtil.prefix("textures/entity/plant/explosion/explode_o_nut4.png");
	private static final ResourceLocation TEXTURE5 = StringUtil.prefix("textures/entity/plant/explosion/explode_o_nut5.png");
	private static final ResourceLocation TEXTURE6 = StringUtil.prefix("textures/entity/plant/explosion/explode_o_nut6.png");
	
	public ExplodeONutRender(EntityRendererManager rendererManager) {
		super(rendererManager, new WallNutModel<ExplodeONutEntity>(), 0.6F);
	}

	@Override
	public float getScaleByEntity(ExplodeONutEntity entity) {
		return 0.4F;
	}

	@Override
	public ResourceLocation getEntityTexture(ExplodeONutEntity entity) {
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

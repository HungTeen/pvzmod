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
	
	public ExplodeONutRender(EntityRendererManager rendererManager) {
		super(rendererManager, new WallNutModel<ExplodeONutEntity>(), 0.6F);
	}

	@Override
	public float getScaleByEntity(ExplodeONutEntity entity) {
		return 0.4F;
	}

	@Override
	public ResourceLocation getTextureLocation(ExplodeONutEntity entity) {
		float life = entity.getHealth();
		float maxLife = entity.getMaxHealth();
    	if(life > 2 * maxLife / 3) return TEXTURE1;
    	else if(life > maxLife / 3) return TEXTURE2;
    	return TEXTURE3;
	}

}

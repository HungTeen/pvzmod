package com.hungteen.pvz.render.entity.plant.defence;

import com.hungteen.pvz.entity.plant.defence.GiantWallNutEntity;
import com.hungteen.pvz.model.entity.plant.defence.WallNutModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GiantWallNutRender extends PVZPlantRender<GiantWallNutEntity> {

	public GiantWallNutRender(EntityRendererManager rendererManager) {
		super(rendererManager, new WallNutModel<>(), 2F);
	}

	@Override
	public float getScaleByEntity(GiantWallNutEntity entity) {
		return 1F;
	}

	@Override
	public ResourceLocation getEntityTexture(GiantWallNutEntity entity) {
		float life = entity.getHealth();
		float maxLife = entity.getMaxHealth();
    	if(life > 2 * maxLife / 3) return WallNutRender.TEXTURE1;
    	else if(life > maxLife / 3) return WallNutRender.TEXTURE2;
    	return WallNutRender.TEXTURE3;
	}

}

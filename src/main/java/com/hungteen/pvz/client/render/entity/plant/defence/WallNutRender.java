package com.hungteen.pvz.client.render.entity.plant.defence;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.model.entity.plant.defence.WallNutModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.defence.WallNutEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WallNutRender extends PVZPlantRender<WallNutEntity>{

	public WallNutRender(EntityRendererManager rendererManager) {
		super(rendererManager, new WallNutModel<WallNutEntity>(), 0.55f);
	}

	@Override
	public ResourceLocation getTextureLocation(WallNutEntity entity) {
		if (entity.getCurrentHealth() >= 200) {
			return new ResourceLocation(PVZMod.MOD_ID, "textures/entity/plant/defence/wall_nut.png");
		}
		else if (entity.getCurrentHealth() >= 100) {
			return new ResourceLocation(PVZMod.MOD_ID, "textures/entity/plant/defence/wall_nut_1.png");
		}
		else {
			return new ResourceLocation(PVZMod.MOD_ID, "textures/entity/plant/defence/wall_nut_2.png");
		}
	}
}
package com.hungteen.pvz.client.render.entity.plant.defence;

import com.hungteen.pvz.client.model.entity.plant.defence.WallNutModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.client.render.layer.component.WallNutArmorLayer;
import com.hungteen.pvz.common.entity.plant.defence.WallNutEntity;
import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WallNutRender extends PVZPlantRender<WallNutEntity>{

	private final ResourceLocation TEX1 = StringUtil.prefix("textures/entity/plant/defence/wall_nut.png");
	private final ResourceLocation TEX2 = StringUtil.prefix("textures/entity/plant/defence/wall_nut_1.png");
	private final ResourceLocation TEX3 = StringUtil.prefix("textures/entity/plant/defence/wall_nut_2.png");

	public WallNutRender(EntityRendererManager rendererManager) {
		super(rendererManager, new WallNutModel<>(), 0.55f);
	}

	@Override
	protected void addPlantLayers() {
		super.addPlantLayers();
		this.addLayer(new WallNutArmorLayer(this));
	}

	@Override
	public ResourceLocation getTextureLocation(WallNutEntity entity) {
		final double percent = entity.getHealth() / entity.getMaxHealth();
		return percent > 2 / 3F ? TEX1 : percent > 1 / 3F ? TEX2 : TEX3;
	}
}
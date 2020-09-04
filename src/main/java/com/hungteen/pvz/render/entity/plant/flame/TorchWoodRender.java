package com.hungteen.pvz.render.entity.plant.flame;

import com.hungteen.pvz.entity.plant.flame.TorchWoodEntity;
import com.hungteen.pvz.model.entity.plant.flame.TorchWoodModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TorchWoodRender extends PVZPlantRender<TorchWoodEntity>{

	public TorchWoodRender(EntityRendererManager rendererManager) {
		super(rendererManager, new TorchWoodModel(), 0.4f);
	}

	@Override
	protected float getScaleByEntity(TorchWoodEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getEntityTexture(TorchWoodEntity entity) {
		if(entity.IsSuperFlame()) return StringUtil.prefix("textures/entity/plant/flame/blue_torch_wood.png");
		return StringUtil.prefix("textures/entity/plant/flame/yellow_torch_wood.png");
	}

}

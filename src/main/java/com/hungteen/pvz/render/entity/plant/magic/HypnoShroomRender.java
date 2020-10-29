package com.hungteen.pvz.render.entity.plant.magic;

import com.hungteen.pvz.entity.plant.magic.HypnoShroomEntity;
import com.hungteen.pvz.model.entity.plant.magic.HypnoShroomModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.render.layer.CharmLayer;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class HypnoShroomRender extends PVZPlantRender<HypnoShroomEntity>{

	public HypnoShroomRender(EntityRendererManager rendererManager) {
		super(rendererManager, new HypnoShroomModel(), 0.5f);
	}

	@Override
	public float getScaleByEntity(HypnoShroomEntity entity) {
		return 0.6f;
	}

	@Override
	protected void addPlantLayers(){
		this.addLayer(new CharmLayer<>(this));
	}
	
	@Override
	public ResourceLocation getEntityTexture(HypnoShroomEntity entity) {
		return StringUtil.prefix("textures/entity/plant/magic/hypno_shroom.png");
	}

}

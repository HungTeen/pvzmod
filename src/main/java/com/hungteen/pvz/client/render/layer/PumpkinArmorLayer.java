package com.hungteen.pvz.client.render.layer;

import com.hungteen.pvz.client.model.entity.plant.defence.PumpkinModel;
import com.hungteen.pvz.client.render.layer.component.ComponentLayer;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;

public class PumpkinArmorLayer<T extends PVZPlantEntity> extends ComponentLayer<T>{
	
	public PumpkinArmorLayer(IEntityRenderer<T, EntityModel<T>> entityRendererIn) {
		super(entityRendererIn, new PumpkinModel.PumpkinArmorModel<>());
	}

	@Override
	public boolean canRender(T entity) {
		return entity.getOuterDefenceLife() > 0;
	}

	public ResourceLocation getRenderTexture(T plant) {
		return PVZPlants.PUMPKIN.getRenderResource();
	}
	
}

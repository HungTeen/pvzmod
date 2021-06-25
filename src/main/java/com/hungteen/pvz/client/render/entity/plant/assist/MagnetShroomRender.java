package com.hungteen.pvz.client.render.entity.plant.assist;

import javax.annotation.Nullable;

import com.hungteen.pvz.client.model.entity.plant.assist.MagnetShroomModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.client.render.layer.MetalItemLayer;
import com.hungteen.pvz.common.entity.plant.assist.MagnetShroomEntity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MagnetShroomRender extends PVZPlantRender<MagnetShroomEntity> {

	public MagnetShroomRender(EntityRendererManager rendererManager) {
		super(rendererManager, new MagnetShroomModel(), 0.3f);
	}

	@Override
	protected void addPlantLayers() {
		super.addPlantLayers();
		this.addLayer(new MetalItemLayer<>(this));
	}
	
	@Nullable
	protected RenderType func_230042_a_(MagnetShroomEntity entity, boolean p_230042_2_, boolean p_230042_3_) {
		ResourceLocation resourcelocation = this.getTextureLocation(entity);
		return RenderType.entityTranslucentCull(resourcelocation);
	}
	
}

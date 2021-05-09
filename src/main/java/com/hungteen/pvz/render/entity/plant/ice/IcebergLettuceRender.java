package com.hungteen.pvz.render.entity.plant.ice;

import com.hungteen.pvz.entity.plant.ice.IcebergLettuceEntity;
import com.hungteen.pvz.model.entity.plant.ice.IcebergLettuceModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IcebergLettuceRender extends PVZPlantRender<IcebergLettuceEntity> {

	private static final ResourceLocation ICEBERG_LETTUCE_TEX = StringUtil.prefix("textures/entity/plant/ice/iceberg_lettuce.png");
	
	public IcebergLettuceRender(EntityRendererManager rendererManager) {
		super(rendererManager, new IcebergLettuceModel(), 0.3F);
	}

	@Override
	public float getScaleByEntity(IcebergLettuceEntity entity) {
		return 0.5F;
	}

	@Override
	public ResourceLocation getTextureLocation(IcebergLettuceEntity entity) {
		return ICEBERG_LETTUCE_TEX;
	}

}

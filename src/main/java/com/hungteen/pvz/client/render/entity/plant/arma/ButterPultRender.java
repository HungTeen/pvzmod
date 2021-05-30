package com.hungteen.pvz.client.render.entity.plant.arma;

import com.hungteen.pvz.client.model.entity.plant.arma.KernelPultModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.arma.ButterPultEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ButterPultRender extends PVZPlantRender<ButterPultEntity> {

	public ButterPultRender(EntityRendererManager rendererManager) {
		super(rendererManager, new KernelPultModel<>(), 0.5F);
	}

	@Override
	public float getScaleByEntity(ButterPultEntity entity) {
		return 0.9F;
	}

	@Override
	public ResourceLocation getTextureLocation(ButterPultEntity entity) {
		return KernelPultRender.KERNEL_PULT_TEX;
	}

}

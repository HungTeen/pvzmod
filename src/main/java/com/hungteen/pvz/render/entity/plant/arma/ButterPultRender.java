package com.hungteen.pvz.render.entity.plant.arma;

import com.hungteen.pvz.entity.plant.arma.ButterPultEntity;
import com.hungteen.pvz.model.entity.plant.arma.KernelPultModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;

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
	public ResourceLocation getEntityTexture(ButterPultEntity entity) {
		return KernelPultRender.KERNEL_PULT_TEX;
	}

}

package com.hungteen.pvz.client.render.entity.plant.arma;

import com.hungteen.pvz.client.model.entity.plant.arma.KernelPultModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.arma.KernelPultEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KernelPultRender extends PVZPlantRender<KernelPultEntity> {

	public static final ResourceLocation KERNEL_PULT_TEX = StringUtil.prefix("textures/entity/plant/arma/kernel_pult.png");
	
	public KernelPultRender(EntityRendererManager rendererManager) {
		super(rendererManager, new KernelPultModel<>(), 0.5F);
	}

	@Override
	public float getScaleByEntity(KernelPultEntity entity) {
		return 0.9F;
	}

	@Override
	public ResourceLocation getTextureLocation(KernelPultEntity entity) {
		return KERNEL_PULT_TEX;
	}

}

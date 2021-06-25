package com.hungteen.pvz.client.render.entity.plant.enforce;

import com.hungteen.pvz.client.model.entity.plant.enforce.UmbrellaLeafModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.enforce.UmbrellaLeafEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class UmbrellaLeafRender extends PVZPlantRender<UmbrellaLeafEntity> {

	public UmbrellaLeafRender(EntityRendererManager rendererManager) {
		super(rendererManager, new UmbrellaLeafModel(), 0.4F);
	}

	@Override
	public void render(UmbrellaLeafEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.pushPose();
		float percent = entityIn.getAttackTime() * 1F / UmbrellaLeafEntity.ANIM_TICK;
		float height = 2F * percent;
		float width = 2F * percent;
		matrixStackIn.scale(1 + width, 1 + height, 1 + width);
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.popPose();
	}
	
}

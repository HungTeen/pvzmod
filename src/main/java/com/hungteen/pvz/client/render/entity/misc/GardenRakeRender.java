package com.hungteen.pvz.client.render.entity.misc;

import com.hungteen.pvz.client.model.entity.misc.GardenRakeModel;
import com.hungteen.pvz.client.render.entity.PVZEntityRender;
import com.hungteen.pvz.common.entity.misc.GardenRakeEntity;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GardenRakeRender extends PVZEntityRender<GardenRakeEntity> {

	public GardenRakeRender(EntityRendererManager renderManager) {
		super(renderManager, new GardenRakeModel());
	}

	@Override
	public void render(GardenRakeEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.pushPose();
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F - entityYaw));
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.popPose();
	}
	
	@Override
	protected float getScaleByEntity(GardenRakeEntity entity) {
		return 0.9F;
	}

	@Override
	public ResourceLocation getTextureLocation(GardenRakeEntity entity) {
		return StringUtil.prefix("textures/entity/misc/garden_rake.png");
	}

}

package com.hungteen.pvz.client.render.entity.bullet;

import com.hungteen.pvz.client.model.entity.bullet.TargetArrowModel;
import com.hungteen.pvz.client.render.entity.PVZEntityRender;
import com.hungteen.pvz.common.entity.bullet.TargetArrowEntity;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TargetArrowRender extends PVZEntityRender<TargetArrowEntity> {

	private static final ResourceLocation TARGET_ARROW = StringUtil.prefix("textures/entity/misc/target_arrow.png");
	
	public TargetArrowRender(EntityRendererManager renderManager) {
		super(renderManager, new TargetArrowModel());
	}

	@Override
	public void render(TargetArrowEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.pushPose();
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.yRotO, entityIn.yRot) + 180.0F));
		matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.xRotO, entityIn.xRot)));
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.popPose();
	}

	@Override
	protected float getScaleByEntity(TargetArrowEntity entity) {
		return 1.5F;
	}

	@Override
	public ResourceLocation getTextureLocation(TargetArrowEntity entity) {
		return TARGET_ARROW;
	}

}

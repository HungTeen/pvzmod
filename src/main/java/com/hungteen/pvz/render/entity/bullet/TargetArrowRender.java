package com.hungteen.pvz.render.entity.bullet;

import com.hungteen.pvz.entity.bullet.TargetArrowEntity;
import com.hungteen.pvz.model.entity.bullet.TargetArrowModel;
import com.hungteen.pvz.render.entity.PVZEntityRender;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
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
		matrixStackIn.push();
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) + 180.0F));
		matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch)));
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.pop();
	}

	@Override
	protected float getScaleByEntity(TargetArrowEntity entity) {
		return 1.5F;
	}

	@Override
	public ResourceLocation getEntityTexture(TargetArrowEntity entity) {
		return TARGET_ARROW;
	}

}

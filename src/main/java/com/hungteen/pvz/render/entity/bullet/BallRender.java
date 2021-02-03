package com.hungteen.pvz.render.entity.bullet;

import com.hungteen.pvz.entity.bullet.BallEntity;
import com.hungteen.pvz.model.entity.bullet.BallModel;
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
public class BallRender extends PVZEntityRender<BallEntity> {

	public BallRender(EntityRendererManager renderManager) {
		super(renderManager, new BallModel());
	}

	@Override
	public void render(BallEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.push();
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) + 180.0F));
		matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch)));
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.pop();
	}

	@Override
	protected float getScaleByEntity(BallEntity entity) {
		return 1.2F;
	}

	@Override
	public ResourceLocation getEntityTexture(BallEntity entity) {
		return StringUtil.prefix("textures/entity/misc/ball.png");
	}

}

package com.hungteen.pvz.client.render.entity.misc.drop;

import com.hungteen.pvz.common.entity.misc.drop.SunEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-11 11:44
 **/

public class SunRender extends EntityRenderer<SunEntity> {

	private static final ResourceLocation SUN_LOCATION = new ResourceLocation("pvz", "textures/entity/drop/sun.png");
	private static final RenderType RENDER_TYPE = RenderType.itemEntityTranslucentCull(SUN_LOCATION);

	public SunRender(EntityRendererManager p_174110_) {
		super(p_174110_);
		this.shadowRadius = 0.15F;
		this.shadowStrength = 0.75F;
	}

	protected int getBlockLightLevel(SunEntity p_114606_, BlockPos p_114607_) {
		return MathHelper.clamp(super.getBlockLightLevel(p_114606_, p_114607_) + 7, 0, 15);
	}

	@Override
	public void render(SunEntity sun, float p_114600_, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferSource, int p_114604_) {
		stack.pushPose();
		final int i = sun.getIcon();
		final float x = (float) (i % 2 * 32 + 16) / 64.0F;
		final float y = (float) (i / 2 * 32 + 16) / 64.0F;
		final float tick = ((float) sun.tickCount + partialTicks) / 20.0F + 0.79F;

		int red;
		int blue;
		int green;
		int alpha;
		red = (int) (sun.ColorBase.x + MathHelper.sin(tick) * sun.ColorChange.x);
		green = (int) (sun.ColorBase.y + MathHelper.sin(tick) * sun.ColorChange.y);
		blue = (int) (sun.ColorBase.z + MathHelper.sin(tick) * sun.ColorChange.z);
		int tmp = sun.getMaxLiveTick() - sun.tickCount;
		if (tmp >= 90) {
			alpha = 255;
		} else if (tmp > 10) {
			alpha = 159 - (int) ((MathHelper.sin((float) ((tmp + 5) / 3.183))) * 96);
		} else {
			alpha = 127 - (int) ((MathHelper.sin((float) ((tmp + 5) / 3.183))) * 127);
		}

		stack.translate(0.0D, (double) 0.1F, 0.0D);
		stack.mulPose(this.entityRenderDispatcher.cameraOrientation());
		stack.mulPose(Vector3f.YP.rotationDegrees(180.0F));
		final float size = 0.4F;
		stack.scale(size, size, size);
		IVertexBuilder vertexconsumer = bufferSource.getBuffer(RENDER_TYPE);//majiang buzhidao gaizhemeduo mingzi ganma woxiang nengsitayade
		MatrixStack.Entry posestack$pose = stack.last();
		Matrix4f matrix4f = posestack$pose.pose();
		Matrix3f matrix3f = posestack$pose.normal();
		vertex(vertexconsumer, matrix4f, matrix3f, -1F, -1.0F, red, green, blue, x + 12.01F * MathHelper.cos(tick) / 64, y + 12.01F * MathHelper.sin(tick) / 64, p_114604_, alpha);
		vertex(vertexconsumer, matrix4f, matrix3f, 1F, -1.0F, red, green, blue, x + 12.01F * MathHelper.cos(tick + 1.57F) / 64, y + 12.01F * MathHelper.sin(tick + 1.57F) / 64, p_114604_, alpha);
		vertex(vertexconsumer, matrix4f, matrix3f, 1F, 1.0F, red, green, blue, x - 12.01F * MathHelper.cos(tick) / 64, y - 12.01F * MathHelper.sin(tick) / 64, p_114604_, alpha);
		vertex(vertexconsumer, matrix4f, matrix3f, -1F, 1.0F, red, green, blue, x - 12.01F * MathHelper.cos(tick + 1.57F) / 64, y - 12.01F * MathHelper.sin(tick + 1.57F) / 64, p_114604_, alpha);
		stack.popPose();
		super.render(sun, p_114600_, partialTicks, stack, bufferSource, p_114604_);
	}

	private static void vertex(IVertexBuilder p_114609_, Matrix4f p_114610_, Matrix3f p_114611_, float p_114612_, float p_114613_, int p_114614_, int p_114615_, int p_114616_, float p_114617_, float p_114618_, int p_114619_, int alpha) {
		p_114609_.vertex(p_114610_, p_114612_, p_114613_ + 0.5F, 0.0F).color(p_114614_, p_114615_, p_114616_, alpha).uv(p_114617_, p_114618_).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(p_114619_).normal(p_114611_, 0.0F, 1.0F, 0.0F).endVertex();
	}

	public ResourceLocation getTextureLocation(SunEntity p_114597_) {
		return SUN_LOCATION;
	}

}
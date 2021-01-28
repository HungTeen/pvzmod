package com.hungteen.pvz.render.entity.zombie.roof;

import com.hungteen.pvz.entity.zombie.roof.BungeeZombieEntity;
import com.hungteen.pvz.model.entity.zombie.roof.BungeeZombieModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.LightType;

public class BungeeZombieRender extends PVZZombieRender<BungeeZombieEntity> {

	private static final ResourceLocation BUNGEE_TEX = StringUtil.prefix("textures/entity/zombie/roof/bungee_zombie.png");
	
	public BungeeZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new BungeeZombieModel(), 0);
	}

	@Override
	public void render(BungeeZombieEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		this.renderLine(entityIn, partialTicks, matrixStackIn, bufferIn);
	}
	
	private void renderLine(BungeeZombieEntity entityLivingIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn) {
	      matrixStackIn.push();
	      double d0 = (double)(MathHelper.lerp(partialTicks * 0.5F, 0, 0) * ((float)Math.PI / 180F));
	      double d1 = (double)(MathHelper.lerp(partialTicks * 0.5F, 0, 0) * ((float)Math.PI / 180F));
	      double d2 = Math.cos(d0);
	      double d3 = Math.sin(d0);
	      double d4 = Math.sin(d1);
	      double d5 = Math.cos(d1);
	      double d6 = entityLivingIn.getOriginPos().getX() - d2 * 0.7D - d3 * 0.5D * d5;
	      double d7 = entityLivingIn.getOriginPos().getY() - d4 * 0.5D - 0.25D;
	      double d8 = entityLivingIn.getOriginPos().getZ() - d3 * 0.7D + d2 * 0.5D * d5;
	      double d9 = (double)(MathHelper.lerp(partialTicks, entityLivingIn.renderYawOffset, entityLivingIn.prevRenderYawOffset) * ((float)Math.PI / 180F)) + (Math.PI / 2D);
	      d2 = Math.cos(d9) * (double)entityLivingIn.getWidth() * 0.4D;
	      d3 = Math.sin(d9) * (double)entityLivingIn.getWidth() * 0.4D;
	      double d10 = MathHelper.lerp((double)partialTicks, entityLivingIn.prevPosX, entityLivingIn.getPosX()) + d2;
	      double d11 = MathHelper.lerp((double)partialTicks, entityLivingIn.prevPosY, entityLivingIn.getPosY());
	      double d12 = MathHelper.lerp((double)partialTicks, entityLivingIn.prevPosZ, entityLivingIn.getPosZ()) + d3;
	      matrixStackIn.translate(d2, -(1.6D - (double)entityLivingIn.getHeight()) * 0.5D, d3);
	      float f = (float)(d6 - d10);
	      float f1 = (float)(d7 - d11);
	      float f2 = (float)(d8 - d12);
	      IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getLeash());
	      Matrix4f matrix4f = matrixStackIn.getLast().getMatrix();
	      float f4 = MathHelper.fastInvSqrt(f * f + f2 * f2) * 0.025F / 2.0F;
	      float f5 = f2 * f4;
	      float f6 = f * f4;
	      int i = this.getBlockLight(entityLivingIn, partialTicks);
	      int j = entityLivingIn.world.getLightFor(LightType.BLOCK, entityLivingIn.getOriginPos());
	      int k = entityLivingIn.world.getLightFor(LightType.SKY, new BlockPos(entityLivingIn.getEyePosition(partialTicks)));
	      int l = entityLivingIn.world.getLightFor(LightType.SKY, entityLivingIn.getOriginPos());
	      renderSide(ivertexbuilder, matrix4f, f, f1, f2, i, j, k, l, 0.025F, 0.025F, f5, f6);
	      renderSide(ivertexbuilder, matrix4f, f, f1, f2, i, j, k, l, 0.025F, 0.0F, f5, f6);
	      matrixStackIn.pop();
	   }
	
	@Override
	protected float getScaleByEntity(BungeeZombieEntity entity) {
		return 0.5F;
	}

	@Override
	public ResourceLocation getEntityTexture(BungeeZombieEntity entity) {
		return BUNGEE_TEX;
	}

}

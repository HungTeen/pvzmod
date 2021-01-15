package com.hungteen.pvz.render.entity.misc;

import com.hungteen.pvz.entity.misc.WallNutBowlingEntity;
import com.hungteen.pvz.entity.plant.defence.WallNutEntity;
import com.hungteen.pvz.model.entity.plant.defence.WallNutModel;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class WallNutBowlingRender extends EntityRenderer<WallNutBowlingEntity> {

	protected final EntityModel<WallNutEntity> model = new WallNutModel();
	
	public WallNutBowlingRender(EntityRendererManager renderManager) {
		super(renderManager);
	}
	
	@Override
	public void render(WallNutBowlingEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.push();
		matrixStackIn.scale(1, - 1, 1);
		float f = getRenderSize(entityIn);
		matrixStackIn.scale(f, f, f);
		matrixStackIn.translate(0.0, - 1.501, 0.0);
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) + 180.0F));
		matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch)));
		matrixStackIn.rotate(Vector3f.XN.rotationDegrees(- entityIn.ticksExisted * 15));
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.model.getRenderType(this.getEntityTexture(entityIn)));
        this.model.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.pop();
	}
	
	protected float getRenderSize(WallNutBowlingEntity entity) {
		return 0.5F;
	}
	
	@Override
	public ResourceLocation getEntityTexture(WallNutBowlingEntity entity) {
		return StringUtil.prefix("textures/entity/plant/defence/wall_nut1.png");
	}

}

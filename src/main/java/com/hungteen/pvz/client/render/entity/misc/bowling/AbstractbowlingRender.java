package com.hungteen.pvz.client.render.entity.misc.bowling;

import com.hungteen.pvz.client.model.entity.plant.defence.WallNutModel;
import com.hungteen.pvz.common.entity.misc.bowling.AbstractBowlingEntity;
import com.hungteen.pvz.common.entity.plant.defence.WallNutEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AbstractbowlingRender<T extends AbstractBowlingEntity> extends EntityRenderer<T> {

	protected final EntityModel<WallNutEntity> model = new WallNutModel<>();
	
	public AbstractbowlingRender(EntityRendererManager renderManager) {
		super(renderManager);
	}
	
	@Override
	public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.pushPose();
		matrixStackIn.scale(- 1, - 1, 1);
		float f = getRenderSize(entityIn);
		matrixStackIn.scale(f, f, f);
		matrixStackIn.translate(0.0, - 0.0, 0.0);
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.yRotO, entityIn.yRot) + 0.0F));
//		matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch)));
		matrixStackIn.mulPose(Vector3f.XN.rotationDegrees(- entityIn.tickCount * 15));
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.model.renderType(this.getTextureLocation(entityIn)));
        this.model.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.popPose();
	}
	
	protected float getRenderSize(T entity) {
		return 1F;
	}
	
	@Override
	public ResourceLocation getTextureLocation(T entity) {
		return PVZPlants.WALL_NUT.getRenderResource();
	}

}

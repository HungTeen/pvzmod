package com.hungteen.pvz.client.render.tileentity;

import com.hungteen.pvz.client.model.entity.te.OriginModel;
import com.hungteen.pvz.common.tileentity.EssenceAltarTileEntity;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EssenceAltarTER extends TileEntityRenderer<EssenceAltarTileEntity> {

	private static final ResourceLocation RES = StringUtil.prefix("textures/tileentity/origin.png");
	private final OriginModel origin = new OriginModel();

	public EssenceAltarTER(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	@Override
	public void render(EssenceAltarTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		matrixStackIn.pushPose();
		matrixStackIn.scale(- 1, - 1, 1);
		float size = 1F;
		matrixStackIn.scale(size, size, size);
		matrixStackIn.translate(- 0.5 / size, - 2.3D - 0.15 * Math.sin(tileEntityIn.tick * 0.1), 0.5 / size);
		IVertexBuilder builder = bufferIn.getBuffer(RenderType.entityTranslucentCull(RES));
		origin.renderToBuffer(matrixStackIn, builder, combinedLightIn, OverlayTexture.NO_OVERLAY);
		origin.setupAnim(null, 0, 0, tileEntityIn.tick + partialTicks, 0, 0);
		matrixStackIn.popPose();
	}

}

package com.hungteen.pvz.client.render.tileentity;

import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.tileentity.SunConverterTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SunConverterTER extends TileEntityRenderer<SunConverterTileEntity> {

	public SunConverterTER(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}

	@Override
	public void render(SunConverterTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		matrixStackIn.pushPose();
		ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack stack = new ItemStack(ItemRegister.RESOURCE_COLLECTOR.get());
        float scale = 1.2F;
        matrixStackIn.scale(scale, scale, scale);
        matrixStackIn.translate(0.5D / scale, 1D / scale, 0.5D / scale);
        if(tileEntityIn.array.get(0) == 1) {
        	matrixStackIn.translate(0, (0.1D * MathHelper.sin(tileEntityIn.tickExist / 10F)) / scale, 0);
        	matrixStackIn.mulPose(Vector3f.YP.rotation(tileEntityIn.tickExist / 10F));
        }
        IBakedModel ibakedmodel = itemRenderer.getModel(stack, tileEntityIn.getLevel(), null);
        itemRenderer.render(stack, ItemCameraTransforms.TransformType.GROUND, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
        matrixStackIn.popPose();
	}

}

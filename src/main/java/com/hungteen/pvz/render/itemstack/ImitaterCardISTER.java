package com.hungteen.pvz.render.itemstack;

import java.util.Optional;

import com.hungteen.pvz.item.tool.card.ImitaterCardItem;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.utils.PlantUtil;
import com.hungteen.pvz.utils.enums.Plants;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;

public class ImitaterCardISTER extends ItemStackTileEntityRenderer {

	@Override
	public void renderByItem(ItemStack stack, ItemCameraTransforms.TransformType tansformType, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn,
			int combinedLightIn, int combinedOverlayIn) {
		matrixStackIn.pushPose();
		ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        matrixStackIn.translate(0.5F, 0.5F, 0.5F);
        ItemStack itemstack = new ItemStack(ItemRegister.IMITATER_CARD.get());
		if(stack.getItem() instanceof ImitaterCardItem) {
			Optional<Plants> opt = ImitaterCardItem.getImitatePlantType(stack);
			if(opt.isPresent() && opt.get() != Plants.IMITATER) {
				itemstack = new ItemStack(PlantUtil.getPlantSummonCard(opt.get()));
			}
		}
		itemRenderer.renderStatic(itemstack, TransformType.NONE, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
        matrixStackIn.popPose();
		
	}
	
}

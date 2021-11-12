package com.hungteen.pvz.client.render.itemstack;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.item.spawn.card.ImitaterCardItem;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;

import java.util.Optional;

public class ImitaterCardISTER extends ItemStackTileEntityRenderer {

	@Override
	public void renderByItem(ItemStack stack, ItemCameraTransforms.TransformType tansformType, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn,
			int combinedLightIn, int combinedOverlayIn) {
		matrixStackIn.pushPose();
		ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        matrixStackIn.translate(0.5F, 0.5F, 0.5F);
        ItemStack itemstack = new ItemStack(ItemRegister.IMITATER_CARD.get());
		if(stack.getItem() instanceof ImitaterCardItem) {
			Optional<IPlantType> opt = ImitaterCardItem.getImitatePlantType(stack);
			if(opt.isPresent() && opt.get() != PVZPlants.IMITATER && opt.get().getSummonCard().isPresent()) {
				itemstack = new ItemStack(opt.get().getSummonCard().get());
			}
		}
		itemRenderer.renderStatic(itemstack, TransformType.NONE, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
        matrixStackIn.popPose();
		
	}
	
}

package com.hungteen.pvz.client.render.itemstack;

import java.util.Optional;

import com.hungteen.pvz.api.types.IPlantType;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.hungteen.pvz.common.item.ItemRegister;
import com.hungteen.pvz.common.item.spawn.card.ImitaterCardItem;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
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
			Optional<IPlantType> opt = ImitaterCardItem.getImitatePlantType(stack);
			if(opt.isPresent() && opt.get() != PVZPlants.IMITATER && opt.get().getSummonCard().isPresent()) {
				itemstack = new ItemStack(opt.get().getSummonCard().get());
			}
		}
		
        IBakedModel ibakedmodel = itemRenderer.getModel(itemstack, null, null);
        matrixStackIn.pushPose();
        itemRenderer.render(itemstack, ItemCameraTransforms.TransformType.NONE, false, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel.getBakedModel());
        matrixStackIn.popPose();
        matrixStackIn.popPose();
		
	}
	
}

package com.hungteen.pvz.client.render.layer;

import com.hungteen.pvz.common.entity.plant.assist.MagnetShroomEntity;
import com.hungteen.pvz.remove.MetalTypes;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MetalItemLayer <T extends MagnetShroomEntity> extends LayerRenderer<T, EntityModel<T>> {

	private ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
	
    public MetalItemLayer(IEntityRenderer<T, EntityModel<T>> entityRendererIn) {
		super(entityRendererIn);
	}
    
	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn,
			MagnetShroomEntity entityIn, float limbSwing, float limbSwingAmount, float partialTicks,
			float ageInTicks, float netHeadYaw, float headPitch) {
		if(entityIn.getMetalType() == MetalTypes.EMPTY || entityIn.isInvisible()) {
			return ;
		}
		matrixStackIn.pushPose();
		matrixStackIn.scale(-1, -1, 1);
		float percent = entityIn.getAttackTime() * 1.0F / entityIn.getWorkCD();
		matrixStackIn.scale(percent, percent, percent);
		ItemStack itemstack = entityIn.getMetalRenderItem();
		IBakedModel ibakedmodel = this.itemRenderer.getModel(itemstack, entityIn.level, (LivingEntity) null);
		this.itemRenderer.render(itemstack, ItemCameraTransforms.TransformType.GROUND, false, matrixStackIn,
				bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY, ibakedmodel);
		matrixStackIn.popPose();
	}

}

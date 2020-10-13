package com.hungteen.pvz.render.layer;

import com.hungteen.pvz.utils.EntityUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;

public class FrozenIceLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, EntityModel<T>>{

	public static final BlockState ICE_BLOCK = Blocks.FROSTED_ICE.getDefaultState();
	
	public FrozenIceLayer(IEntityRenderer<T, EntityModel<T>> entityRendererIn) {
		super(entityRendererIn);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entity,
			float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw,
			float headPitch) {
		if(!EntityUtil.isEntityFrozen(entity)) {
			return ;
		}
		matrixStackIn.push();
		float entityWidth = entity.getWidth();
		float scale = 1.5f;
		matrixStackIn.scale(scale, scale, scale);
		matrixStackIn.translate(-entityWidth / 2, 0, -entityWidth / 2);
		Minecraft.getInstance().getBlockRendererDispatcher().renderBlock(ICE_BLOCK, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
		matrixStackIn.pop();
	}

}

package com.hungteen.pvz.model.entity.drop;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class CoinModel extends EntityModel<Entity> {
	private final ModelRenderer coin;

	public CoinModel() {
		textureWidth = 128;
		textureHeight = 128;

		coin = new ModelRenderer(this);
		coin.setRotationPoint(0.0F, -8.0F, 0.0F);
		coin.setTextureOffset(0, 0).addBox(-32.0F, -32.0F, 0.0F, 64.0F, 64.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.coin.rotateAngleY = ageInTicks / 10;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		coin.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
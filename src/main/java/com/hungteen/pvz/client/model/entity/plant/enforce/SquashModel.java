package com.hungteen.pvz.client.model.entity.plant.enforce;

import com.hungteen.pvz.common.entity.plant.enforce.SquashEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class SquashModel extends EntityModel<SquashEntity> {
	private final ModelRenderer body;
	private final ModelRenderer bone;

	public SquashModel() {
		texWidth = 256;
		texHeight = 256;

		body = new ModelRenderer(this);
		body.setPos(0.0F, 24.0F, 0.0F);
		body.texOffs(125, 202).addBox(-16.0F, -16.0F, -16.0F, 32.0F, 16.0F, 32.0F, 0.0F, false);
		body.texOffs(138, 153).addBox(-14.0F, -32.0F, -14.0F, 28.0F, 16.0F, 28.0F, 0.0F, false);
		body.texOffs(40, 148).addBox(-11.0F, -44.0F, -11.0F, 22.0F, 12.0F, 22.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, -50.0F, 0.0F);
		body.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, 0.5236F);
		bone.texOffs(235, 130).addBox(1.0F, -1.8038F, -2.0F, 4.0F, 9.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(SquashEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
package com.hungteen.pvz.client.model.entity.bullet;

import com.hungteen.pvz.common.entity.bullet.TargetArrowEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class TargetArrowModel extends EntityModel<TargetArrowEntity> {
	private final ModelRenderer total;
	private final ModelRenderer cube_r1;
	private final ModelRenderer bone;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;

	public TargetArrowModel() {
		texWidth = 16;
		texHeight = 16;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, -1.0F);
		total.texOffs(0, 1).addBox(-0.5F, -4.5F, -2.3F, 1.0F, 1.0F, 6.0F, -0.3F, false);
		total.texOffs(0, 8).addBox(-4.0F, -8.0F, -2.0F, 8.0F, 8.0F, 0.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(0.0F, -4.0F, -2.0F);
		total.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -0.7854F, 0.0F);
		cube_r1.texOffs(12, 0).addBox(-0.5F, -0.5F, -0.55F, 1.0F, 1.0F, 1.0F, -0.3F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, -4.0F, 3.25F);
		total.addChild(bone);
		bone.texOffs(10, 3).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 2.0F, -0.45F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(0.0F, 0.0F, 0.0F);
		bone.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, 1.5708F);
		cube_r2.texOffs(10, 3).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 2.0F, -0.45F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(0.0F, 0.0F, 0.0F);
		bone.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, -3.1416F);
		cube_r3.texOffs(10, 3).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 2.0F, -0.45F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(0.0F, 0.0F, 0.0F);
		bone.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, -1.5708F);
		cube_r4.texOffs(10, 4).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 2.0F, -0.45F, false);
	}

	@Override
	public void setupAnim(TargetArrowEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		total.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
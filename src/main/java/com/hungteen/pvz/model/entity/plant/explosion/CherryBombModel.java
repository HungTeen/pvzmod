package com.hungteen.pvz.model.entity.plant.explosion;

import com.hungteen.pvz.entity.plant.explosion.CherryBombEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class CherryBombModel extends EntityModel<CherryBombEntity> {
	private final ModelRenderer body;
	private final ModelRenderer bone;
	private final ModelRenderer bone3;
	private final ModelRenderer bone2;
	private final ModelRenderer head1;
	private final ModelRenderer head2;

	public CherryBombModel() {
		textureWidth = 256;
		textureHeight = 256;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-9.0F, -18.0F, 0.0F);
		body.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, 0.7854F);
		bone.setTextureOffset(52, 105).addBox(-2.0F, -17.0F, -1.0F, 2.0F, 20.0F, 2.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, -16.0F, 0.0F);
		bone.addChild(bone3);
		setRotationAngle(bone3, 0.0F, -0.3491F, 0.6981F);
		bone3.setTextureOffset(36, 113).addBox(-1.0F, -8.0F, -3.0F, 1.0F, 8.0F, 6.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(9.0F, -18.0F, 0.0F);
		body.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, -0.7854F);
		bone2.setTextureOffset(23, 108).addBox(0.0F, -13.0F, -1.0F, 2.0F, 16.0F, 2.0F, 0.0F, false);

		head1 = new ModelRenderer(this);
		head1.setRotationPoint(-11.0F, -18.0F, 0.0F);
		body.addChild(head1);
		setRotationAngle(head1, 0.0F, 0.2618F, 0.0F);
		head1.setTextureOffset(63, 61).addBox(-8.0F, 1.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);
		head1.setTextureOffset(97, 30).addBox(8.0F, 2.0F, -7.0F, 1.0F, 14.0F, 14.0F, 0.0F, false);
		head1.setTextureOffset(64, 31).addBox(-9.0F, 2.0F, -7.0F, 1.0F, 14.0F, 14.0F, 0.0F, false);
		head1.setTextureOffset(97, 14).addBox(-7.0F, 2.0F, -9.0F, 14.0F, 14.0F, 1.0F, 0.0F, false);
		head1.setTextureOffset(64, 13).addBox(-7.0F, 2.0F, 8.0F, 14.0F, 14.0F, 1.0F, 0.0F, false);
		head1.setTextureOffset(0, 85).addBox(-7.0F, 0.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);
		head1.setTextureOffset(1, 68).addBox(-7.0F, 17.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);

		head2 = new ModelRenderer(this);
		head2.setRotationPoint(11.0F, -18.0F, 0.0F);
		body.addChild(head2);
		setRotationAngle(head2, 0.0F, -0.2618F, 0.0F);
		head2.setTextureOffset(63, 95).addBox(-8.0F, 1.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);
		head2.setTextureOffset(29, 37).addBox(8.0F, 2.0F, -7.0F, 1.0F, 14.0F, 14.0F, 0.0F, false);
		head2.setTextureOffset(3, 7).addBox(-9.0F, 2.0F, -7.0F, 1.0F, 14.0F, 14.0F, 0.0F, false);
		head2.setTextureOffset(4, 140).addBox(-7.0F, 2.0F, -9.0F, 14.0F, 14.0F, 1.0F, 0.0F, false);
		head2.setTextureOffset(44, 139).addBox(-7.0F, 2.0F, 8.0F, 14.0F, 14.0F, 1.0F, 0.0F, false);
		head2.setTextureOffset(84, 138).addBox(-7.0F, 0.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);
		head2.setTextureOffset(8, 163).addBox(-7.0F, 17.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(CherryBombEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
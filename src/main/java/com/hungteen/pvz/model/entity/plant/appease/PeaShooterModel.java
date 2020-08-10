package com.hungteen.pvz.model.entity.plant.appease;

import com.hungteen.pvz.entity.plant.appease.PeaShooterEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class PeaShooterModel extends EntityModel<PeaShooterEntity> {
	private final ModelRenderer total;
	private final ModelRenderer head;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer down;
	private final ModelRenderer body;

	public PeaShooterModel() {
		textureWidth = 64;
		textureHeight = 64;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -15.0F, 0.0F);
		total.addChild(head);
		head.setTextureOffset(38, 15).addBox(-2.0F, -4.0F, -7.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(1, 19).addBox(-1.0F, -3.0F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(0, 1).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -7.0F, 4.0F);
		head.addChild(bone);
		setRotationAngle(bone, 0.6109F, 0.0F, 0.0F);
		bone.setTextureOffset(3, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.8192F, 0.4264F);
		bone.addChild(bone2);
		setRotationAngle(bone2, -0.48F, 0.0F, 0.0F);
		bone2.setTextureOffset(54, 10).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 2.0F);
		bone2.addChild(bone3);
		setRotationAngle(bone3, 0.3927F, 0.0F, 0.0F);
		bone3.setTextureOffset(33, 57).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 6.0F, 1.0F, 0.0F, false);

		down = new ModelRenderer(this);
		down.setRotationPoint(1.0F, 0.0F, 0.0F);
		total.addChild(down);
		down.setTextureOffset(14, 30).addBox(0.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.setTextureOffset(13, 36).addBox(-2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.setTextureOffset(1, 30).addBox(-2.0F, -1.0F, -3.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.setTextureOffset(1, 35).addBox(-4.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.setTextureOffset(2, 45).addBox(2.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		down.setTextureOffset(0, 55).addBox(-3.0F, -1.0F, 3.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		down.setTextureOffset(41, 1).addBox(-7.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		down.setTextureOffset(42, 30).addBox(-3.0F, -1.0F, -6.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(-1.0F, -13.0F, 0.0F);
		total.addChild(body);
		body.setTextureOffset(25, 36).addBox(0.0F, -2.0F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.setTextureOffset(24, 40).addBox(1.0F, -2.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.setTextureOffset(23, 44).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.setTextureOffset(25, 31).addBox(0.0F, -2.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.setTextureOffset(55, 47).addBox(0.0F, -1.0F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(PeaShooterEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		total.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
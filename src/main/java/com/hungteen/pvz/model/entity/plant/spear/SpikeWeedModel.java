package com.hungteen.pvz.model.entity.plant.spear;

import com.hungteen.pvz.entity.plant.spear.SpikeWeedEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class SpikeWeedModel extends EntityModel<SpikeWeedEntity> {
	private final ModelRenderer body;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer bone13;

	public SpikeWeedModel() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(0, 0).addBox(-12.0F, -2.0F, -12.0F, 24.0F, 2.0F, 24.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -2.0F, 0.0F);
		body.addChild(bone);
		setRotationAngle(bone, 0.7854F, 0.0F, -0.6109F);
		bone.setTextureOffset(104, 4).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-9.0F, -2.0F, 9.0F);
		body.addChild(bone2);
		setRotationAngle(bone2, -0.7854F, -0.7854F, 0.7854F);
		bone2.setTextureOffset(105, 14).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, -2.0F, 8.0F);
		body.addChild(bone3);
		setRotationAngle(bone3, -0.7854F, 0.0F, 0.6109F);
		bone3.setTextureOffset(106, 23).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(9.0F, -2.0F, 9.0F);
		body.addChild(bone4);
		setRotationAngle(bone4, -0.7854F, -0.7854F, 0.7854F);
		bone4.setTextureOffset(107, 32).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(-9.0F, -2.0F, -9.0F);
		body.addChild(bone5);
		setRotationAngle(bone5, 0.7854F, 0.7854F, -0.7854F);
		bone5.setTextureOffset(108, 42).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(9.0F, -2.0F, -9.0F);
		body.addChild(bone6);
		setRotationAngle(bone6, -0.7854F, -0.7854F, 0.7854F);
		bone6.setTextureOffset(110, 50).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(-8.0F, -2.0F, 0.0F);
		body.addChild(bone7);
		setRotationAngle(bone7, 0.0F, -0.7854F, 0.6109F);
		bone7.setTextureOffset(114, 58).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(7.0F, -2.0F, 0.0F);
		body.addChild(bone8);
		setRotationAngle(bone8, 0.0F, 0.7854F, -0.6109F);
		bone8.setTextureOffset(113, 70).addBox(-0.4208F, -0.4264F, -0.4208F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, -2.0F, -8.0F);
		body.addChild(bone9);
		setRotationAngle(bone9, -0.7854F, 0.0F, -0.6109F);
		bone9.setTextureOffset(118, 82).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(-5.0F, -2.0F, -4.0F);
		body.addChild(bone10);
		setRotationAngle(bone10, 0.7854F, 0.0F, -0.6109F);
		bone10.setTextureOffset(116, 92).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(-5.0F, -2.0F, 5.0F);
		body.addChild(bone11);
		setRotationAngle(bone11, -0.7854F, 0.0F, 0.6109F);
		bone11.setTextureOffset(115, 102).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(4.0F, -2.0F, 5.0F);
		body.addChild(bone12);
		setRotationAngle(bone12, 0.7854F, 0.0F, -0.6109F);
		bone12.setTextureOffset(114, 111).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(4.0F, -2.0F, -4.0F);
		body.addChild(bone13);
		setRotationAngle(bone13, -0.7854F, 0.0F, 0.6109F);
		bone13.setTextureOffset(107, 119).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(SpikeWeedEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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
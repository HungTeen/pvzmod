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
		texWidth = 128;
		texHeight = 128;

		body = new ModelRenderer(this);
		body.setPos(0.0F, 24.0F, 0.0F);
		body.texOffs(0, 0).addBox(-12.0F, -2.0F, -12.0F, 24.0F, 2.0F, 24.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, -2.0F, 0.0F);
		body.addChild(bone);
		setRotationAngle(bone, 0.7854F, 0.0F, -0.6109F);
		bone.texOffs(104, 4).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(-9.0F, -2.0F, 9.0F);
		body.addChild(bone2);
		setRotationAngle(bone2, -0.7854F, -0.7854F, 0.7854F);
		bone2.texOffs(105, 14).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, -2.0F, 8.0F);
		body.addChild(bone3);
		setRotationAngle(bone3, -0.7854F, 0.0F, 0.6109F);
		bone3.texOffs(106, 23).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(9.0F, -2.0F, 9.0F);
		body.addChild(bone4);
		setRotationAngle(bone4, -0.7854F, -0.7854F, 0.7854F);
		bone4.texOffs(107, 32).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(-9.0F, -2.0F, -9.0F);
		body.addChild(bone5);
		setRotationAngle(bone5, 0.7854F, 0.7854F, -0.7854F);
		bone5.texOffs(108, 42).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(9.0F, -2.0F, -9.0F);
		body.addChild(bone6);
		setRotationAngle(bone6, -0.7854F, -0.7854F, 0.7854F);
		bone6.texOffs(110, 50).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setPos(-8.0F, -2.0F, 0.0F);
		body.addChild(bone7);
		setRotationAngle(bone7, 0.0F, -0.7854F, 0.6109F);
		bone7.texOffs(114, 58).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setPos(7.0F, -2.0F, 0.0F);
		body.addChild(bone8);
		setRotationAngle(bone8, 0.0F, 0.7854F, -0.6109F);
		bone8.texOffs(113, 70).addBox(-0.4208F, -0.4264F, -0.4208F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setPos(0.0F, -2.0F, -8.0F);
		body.addChild(bone9);
		setRotationAngle(bone9, -0.7854F, 0.0F, -0.6109F);
		bone9.texOffs(118, 82).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone10 = new ModelRenderer(this);
		bone10.setPos(-5.0F, -2.0F, -4.0F);
		body.addChild(bone10);
		setRotationAngle(bone10, 0.7854F, 0.0F, -0.6109F);
		bone10.texOffs(116, 92).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone11 = new ModelRenderer(this);
		bone11.setPos(-5.0F, -2.0F, 5.0F);
		body.addChild(bone11);
		setRotationAngle(bone11, -0.7854F, 0.0F, 0.6109F);
		bone11.texOffs(115, 102).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone12 = new ModelRenderer(this);
		bone12.setPos(4.0F, -2.0F, 5.0F);
		body.addChild(bone12);
		setRotationAngle(bone12, 0.7854F, 0.0F, -0.6109F);
		bone12.texOffs(114, 111).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone13 = new ModelRenderer(this);
		bone13.setPos(4.0F, -2.0F, -4.0F);
		body.addChild(bone13);
		setRotationAngle(bone13, -0.7854F, 0.0F, 0.6109F);
		bone13.texOffs(107, 119).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(SpikeWeedEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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
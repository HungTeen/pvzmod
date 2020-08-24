package com.hungteen.pvz.model.entity.plant.ice;

import com.hungteen.pvz.entity.plant.ice.SnowPeaEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class SnowPeaModel extends EntityModel<SnowPeaEntity> {
	private final ModelRenderer total;
	private final ModelRenderer head;
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
	private final ModelRenderer down;
	private final ModelRenderer body;

	public SnowPeaModel() {
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
		bone.setRotationPoint(0.0F, -4.0F, 4.0F);
		head.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, -0.7854F);
		

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone2);
		bone2.setTextureOffset(37, 46).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 5.0F);
		bone2.addChild(bone3);
		setRotationAngle(bone3, 0.0F, -0.7854F, 0.0F);
		bone3.setTextureOffset(37, 46).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(-1.0F, 0.0F, 0.0F);
		bone.addChild(bone4);
		setRotationAngle(bone4, 0.0F, -0.7854F, 0.0F);
		bone4.setTextureOffset(37, 46).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 0.0F, 5.0F);
		bone4.addChild(bone5);
		setRotationAngle(bone5, 0.0F, -0.7854F, 0.0F);
		bone5.setTextureOffset(37, 46).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone6);
		setRotationAngle(bone6, 0.7854F, 0.0F, 0.0F);
		bone6.setTextureOffset(37, 46).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, -1.0F, 5.0F);
		bone6.addChild(bone7);
		setRotationAngle(bone7, -0.7854F, 0.0F, 0.0F);
		bone7.setTextureOffset(37, 46).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone8);
		setRotationAngle(bone8, 0.0F, 0.7854F, 0.0F);
		bone8.setTextureOffset(37, 46).addBox(0.0F, -1.0F, 0.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, 0.0F, 5.0F);
		bone8.addChild(bone9);
		setRotationAngle(bone9, 0.0F, -0.7854F, 0.0F);
		bone9.setTextureOffset(37, 46).addBox(-0.2929F, -1.0F, -1.7071F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone.addChild(bone10);
		setRotationAngle(bone10, -0.7854F, 0.0F, 0.0F);
		bone10.setTextureOffset(37, 46).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(0.0F, 1.0F, 5.0F);
		bone10.addChild(bone11);
		setRotationAngle(bone11, -0.7854F, 0.0F, 0.0F);
		bone11.setTextureOffset(37, 46).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

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
	public void setRotationAngles(SnowPeaEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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
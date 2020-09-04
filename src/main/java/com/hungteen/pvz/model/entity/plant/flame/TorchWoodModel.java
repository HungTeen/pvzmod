package com.hungteen.pvz.model.entity.plant.flame;

import com.hungteen.pvz.entity.plant.flame.TorchWoodEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class TorchWoodModel extends EntityModel<TorchWoodEntity> {
	private final ModelRenderer total;
	private final ModelRenderer bone6;
	private final ModelRenderer bone5;
	private final ModelRenderer bone4;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone;
	private final ModelRenderer body;
	private final ModelRenderer head1;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ModelRenderer head4;

	public TorchWoodModel() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 2.3562F, 0.0F);
		bone6.setTextureOffset(96, 2).addBox(-5.0F, -1.0F, -19.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		bone6.setTextureOffset(96, 2).addBox(-5.0F, -3.0F, -17.0F, 10.0F, 3.0F, 25.0F, 0.0F, false);
		bone6.setTextureOffset(96, 2).addBox(-5.0F, -2.0F, -18.0F, 10.0F, 2.0F, 1.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(bone5);
		setRotationAngle(bone5, 0.0F, -2.3562F, 0.0F);
		bone5.setTextureOffset(94, 42).addBox(-5.0F, -1.0F, -19.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		bone5.setTextureOffset(94, 42).addBox(-5.0F, -3.0F, -17.0F, 10.0F, 3.0F, 25.0F, 0.0F, false);
		bone5.setTextureOffset(94, 42).addBox(-5.0F, -2.0F, -18.0F, 10.0F, 2.0F, 1.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(bone4);
		setRotationAngle(bone4, 0.0F, -1.1345F, 0.0F);
		bone4.setTextureOffset(130, 114).addBox(-5.0F, -1.0F, -19.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		bone4.setTextureOffset(130, 114).addBox(-5.0F, -3.0F, -17.0F, 10.0F, 3.0F, 25.0F, 0.0F, false);
		bone4.setTextureOffset(130, 114).addBox(-5.0F, -2.0F, -18.0F, 10.0F, 2.0F, 1.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 1.1345F, 0.0F);
		bone2.setTextureOffset(174, 11).addBox(-5.0F, -1.0F, -19.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		bone2.setTextureOffset(174, 11).addBox(-5.0F, -3.0F, -17.0F, 10.0F, 3.0F, 25.0F, 0.0F, false);
		bone2.setTextureOffset(174, 11).addBox(-5.0F, -2.0F, -18.0F, 10.0F, 2.0F, 1.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone2.addChild(bone3);
		bone3.setTextureOffset(174, 11).addBox(-4.0F, -1.0F, -19.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		bone3.setTextureOffset(174, 11).addBox(-4.0F, -3.0F, -17.0F, 8.0F, 3.0F, 25.0F, 0.0F, false);
		bone3.setTextureOffset(174, 11).addBox(-4.0F, -2.0F, -18.0F, 8.0F, 2.0F, 1.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(bone);
		bone.setTextureOffset(180, 68).addBox(-5.0F, -1.0F, -19.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(180, 68).addBox(-5.0F, -3.0F, -17.0F, 10.0F, 3.0F, 25.0F, 0.0F, false);
		bone.setTextureOffset(180, 68).addBox(-5.0F, -2.0F, -18.0F, 10.0F, 2.0F, 1.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		body.setTextureOffset(149, 199).addBox(-13.0F, -30.0F, -13.0F, 26.0F, 27.0F, 26.0F, 0.0F, false);
		body.setTextureOffset(91, 222).addBox(-12.0F, -32.0F, -14.0F, 24.0F, 29.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(33, 220).addBox(-12.0F, -32.0F, 13.0F, 24.0F, 29.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(22, 156).addBox(-14.0F, -32.0F, -12.0F, 1.0F, 29.0F, 24.0F, 0.0F, false);
		body.setTextureOffset(82, 164).addBox(13.0F, -32.0F, -12.0F, 1.0F, 29.0F, 24.0F, 0.0F, false);

		head1 = new ModelRenderer(this);
		head1.setRotationPoint(0.0F, -32.0F, -14.0F);
		body.addChild(head1);
		setRotationAngle(head1, -0.7854F, 0.0F, 0.0F);
		head1.setTextureOffset(200, 187).addBox(-12.0F, -1.0F, -2.0F, 24.0F, 1.0F, 3.0F, 0.0F, false);

		head2 = new ModelRenderer(this);
		head2.setRotationPoint(0.0F, -32.0F, 14.0F);
		body.addChild(head2);
		setRotationAngle(head2, 0.7854F, 0.0F, 0.0F);
		head2.setTextureOffset(196, 174).addBox(-12.0F, -1.0F, -1.0F, 24.0F, 1.0F, 3.0F, 0.0F, false);

		head3 = new ModelRenderer(this);
		head3.setRotationPoint(-13.0F, -32.0F, 0.0F);
		body.addChild(head3);
		setRotationAngle(head3, 0.0F, 0.0F, 0.7854F);
		head3.setTextureOffset(200, 140).addBox(-3.0F, 0.0F, -12.0F, 3.0F, 1.0F, 24.0F, 0.0F, false);

		head4 = new ModelRenderer(this);
		head4.setRotationPoint(13.0F, -32.0F, 0.0F);
		body.addChild(head4);
		setRotationAngle(head4, 0.0F, 0.0F, -0.7854F);
		head4.setTextureOffset(201, 108).addBox(0.0F, 0.0F, -12.0F, 3.0F, 1.0F, 24.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(TorchWoodEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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
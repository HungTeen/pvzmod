package com.hungteen.pvz.client.model.entity.misc;

import com.hungteen.pvz.common.entity.misc.BobsleCarEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class BobsleCarModel extends EntityModel<BobsleCarEntity> {
	private final ModelRenderer total;
	private final ModelRenderer mid;
	private final ModelRenderer left;
	private final ModelRenderer right;
	private final ModelRenderer Head;
	private final ModelRenderer wheal;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer wheal2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer wheal3;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer wheal4;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer tail;

	public BobsleCarModel() {
		texWidth = 512;
		texHeight = 512;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		setRotationAngle(total, 0.0F, 0.0F, 0.0F);
		

		mid = new ModelRenderer(this);
		mid.setPos(0.0F, -14.0F, 3.0F);
		total.addChild(mid);
		mid.texOffs(284, 421).addBox(-14.0F, 4.0F, -17.0F, 28.0F, 1.0F, 84.0F, 0.0F, false);
		mid.texOffs(459, 402).addBox(-8.0F, -4.0F, -3.0F, 16.0F, 8.0F, 6.0F, 0.0F, false);
		mid.texOffs(402, 399).addBox(-8.0F, -4.0F, 17.0F, 16.0F, 8.0F, 6.0F, 0.0F, false);
		mid.texOffs(463, 372).addBox(-8.0F, -4.0F, 37.0F, 16.0F, 8.0F, 6.0F, 0.0F, false);
		mid.texOffs(402, 368).addBox(-8.0F, -4.0F, 57.0F, 16.0F, 8.0F, 6.0F, 0.0F, false);
		mid.texOffs(440, 350).addBox(-14.0F, -2.0F, 67.0F, 28.0F, 7.0F, 1.0F, 0.0F, false);

		left = new ModelRenderer(this);
		left.setPos(13.0F, 4.0F, 24.0F);
		mid.addChild(left);
		left.texOffs(9, 399).addBox(1.0F, -24.0F, -41.0F, 1.0F, 25.0F, 84.0F, 0.0F, false);

		right = new ModelRenderer(this);
		right.setPos(-13.0F, 4.0F, 25.0F);
		mid.addChild(right);
		right.texOffs(336, 7).addBox(-2.0F, -24.0F, -42.0F, 1.0F, 25.0F, 84.0F, 0.0F, false);

		Head = new ModelRenderer(this);
		Head.setPos(0.0F, -9.0F, -22.0F);
		total.addChild(Head);
		Head.texOffs(188, 449).addBox(-15.0F, -25.0F, -12.0F, 30.0F, 25.0F, 20.0F, 0.0F, false);
		Head.texOffs(129, 436).addBox(-13.0F, -15.0F, -17.0F, 26.0F, 15.0F, 5.0F, 0.0F, false);
		Head.texOffs(13, 378).addBox(-10.0F, -7.0F, -22.0F, 20.0F, 7.0F, 5.0F, 0.0F, false);
		Head.texOffs(11, 340).addBox(-15.0F, -27.0F, -2.0F, 30.0F, 2.0F, 21.0F, 0.0F, false);

		wheal = new ModelRenderer(this);
		wheal.setPos(13.0F, -6.0F, -20.0F);
		total.addChild(wheal);
		wheal.texOffs(0, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 2.0F, 16.0F, 0.0F, false);
		wheal.texOffs(0, 0).addBox(-2.0F, -3.0F, -8.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal.texOffs(0, 0).addBox(-2.0F, -3.0F, 8.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal.texOffs(0, 0).addBox(-2.0F, 1.0F, 9.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal.texOffs(0, 0).addBox(-2.0F, 1.0F, 0.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal.texOffs(0, 0).addBox(-2.0F, 1.0F, -9.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal.texOffs(0, 0).addBox(-1.0F, 3.0F, -11.0F, 2.0F, 3.0F, 26.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 3.0F, -11.0F);
		wheal.addChild(bone);
		setRotationAngle(bone, -0.6981F, 0.0F, 0.0F);
		bone.texOffs(0, 0).addBox(-1.0F, 0.0F, -3.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, 3.0F, 14.0F);
		wheal.addChild(bone2);
		setRotationAngle(bone2, 0.6981F, 0.0F, 0.0F);
		bone2.texOffs(0, 0).addBox(-1.0F, 0.6428F, -1.234F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		wheal2 = new ModelRenderer(this);
		wheal2.setPos(13.0F, -6.0F, -20.0F);
		total.addChild(wheal2);
		wheal2.texOffs(0, 0).addBox(-27.0F, -1.0F, -6.0F, 2.0F, 2.0F, 16.0F, 0.0F, false);
		wheal2.texOffs(0, 0).addBox(-28.0F, -3.0F, -8.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal2.texOffs(0, 0).addBox(-28.0F, -3.0F, 8.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal2.texOffs(0, 0).addBox(-28.0F, 1.0F, 9.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal2.texOffs(0, 0).addBox(-28.0F, 1.0F, 0.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal2.texOffs(0, 0).addBox(-28.0F, 1.0F, -9.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal2.texOffs(0, 0).addBox(-27.0F, 3.0F, -11.0F, 2.0F, 3.0F, 26.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, 3.0F, -11.0F);
		wheal2.addChild(bone3);
		setRotationAngle(bone3, -0.6981F, 0.0F, 0.0F);
		bone3.texOffs(0, 0).addBox(-27.0F, 0.0F, -3.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(0.0F, 3.0F, 14.0F);
		wheal2.addChild(bone4);
		setRotationAngle(bone4, 0.6981F, 0.0F, 0.0F);
		bone4.texOffs(0, 0).addBox(-27.0F, 0.6428F, -1.234F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		wheal3 = new ModelRenderer(this);
		wheal3.setPos(13.0F, -6.0F, -20.0F);
		total.addChild(wheal3);
		wheal3.texOffs(0, 0).addBox(-1.0F, -1.0F, 70.0F, 2.0F, 2.0F, 16.0F, 0.0F, false);
		wheal3.texOffs(0, 0).addBox(-2.0F, -3.0F, 68.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal3.texOffs(0, 0).addBox(-2.0F, -3.0F, 84.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal3.texOffs(0, 0).addBox(-2.0F, 1.0F, 85.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal3.texOffs(0, 0).addBox(-2.0F, 1.0F, 76.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal3.texOffs(0, 0).addBox(-2.0F, 1.0F, 67.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal3.texOffs(0, 0).addBox(-1.0F, 3.0F, 65.0F, 2.0F, 3.0F, 26.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(0.0F, 3.0F, -11.0F);
		wheal3.addChild(bone5);
		setRotationAngle(bone5, -0.6981F, 0.0F, 0.0F);
		bone5.texOffs(0, 0).addBox(-1.0F, -48.8519F, 55.2194F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(0.0F, 3.0F, 14.0F);
		wheal3.addChild(bone6);
		setRotationAngle(bone6, 0.6981F, 0.0F, 0.0F);
		bone6.texOffs(0, 0).addBox(-1.0F, 49.4946F, 56.9854F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		wheal4 = new ModelRenderer(this);
		wheal4.setPos(13.0F, -6.0F, 55.0F);
		total.addChild(wheal4);
		wheal4.texOffs(0, 0).addBox(-27.0F, -1.0F, -6.0F, 2.0F, 2.0F, 16.0F, 0.0F, false);
		wheal4.texOffs(0, 0).addBox(-28.0F, -3.0F, -8.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal4.texOffs(0, 0).addBox(-28.0F, -3.0F, 8.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal4.texOffs(0, 0).addBox(-28.0F, 1.0F, 9.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal4.texOffs(0, 0).addBox(-28.0F, 1.0F, 0.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal4.texOffs(0, 0).addBox(-28.0F, 1.0F, -9.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
		wheal4.texOffs(0, 0).addBox(-27.0F, 3.0F, -11.0F, 2.0F, 3.0F, 26.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setPos(0.0F, 3.0F, -11.0F);
		wheal4.addChild(bone7);
		setRotationAngle(bone7, -0.6981F, 0.0F, 0.0F);
		bone7.texOffs(0, 0).addBox(-27.0F, 0.0F, -3.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setPos(0.0F, 3.0F, 14.0F);
		wheal4.addChild(bone8);
		setRotationAngle(bone8, 0.6981F, 0.0F, 0.0F);
		bone8.texOffs(0, 0).addBox(-27.0F, 0.6428F, -1.234F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setPos(0.0F, -22.0F, 70.0F);
		total.addChild(tail);
		setRotationAngle(tail, 0.4363F, 0.0F, 0.0F);
		tail.texOffs(261, 382).addBox(14.0F, -11.0F, 0.0F, 1.0F, 11.0F, 18.0F, 0.0F, false);
		tail.texOffs(184, 378).addBox(-15.0F, -11.0F, 0.0F, 1.0F, 11.0F, 18.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(BobsleCarEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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
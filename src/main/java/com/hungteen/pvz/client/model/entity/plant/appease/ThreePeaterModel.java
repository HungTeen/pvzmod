package com.hungteen.pvz.client.model.entity.plant.appease;

import com.hungteen.pvz.common.entity.plant.appease.ThreePeaterEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class ThreePeaterModel extends EntityModel<ThreePeaterEntity> {
	private final ModelRenderer total;
	private final ModelRenderer down;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer up2;
	private final ModelRenderer body2;
	private final ModelRenderer head2;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer up3;
	private final ModelRenderer body3;
	private final ModelRenderer head3;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer bone13;
	private final ModelRenderer bone14;
	private final ModelRenderer bone15;
	private final ModelRenderer bone16;
	private final ModelRenderer bone17;
	private final ModelRenderer bone18;

	public ThreePeaterModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		down = new ModelRenderer(this);
		down.setPos(1.0F, 0.0F, 0.0F);
		total.addChild(down);
		down.texOffs(14, 30).addBox(0.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.texOffs(13, 36).addBox(-2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.texOffs(1, 30).addBox(-2.0F, -1.0F, -3.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.texOffs(1, 35).addBox(-4.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.texOffs(2, 45).addBox(2.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		down.texOffs(0, 55).addBox(-3.0F, -1.0F, 3.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		down.texOffs(41, 1).addBox(-7.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		down.texOffs(42, 30).addBox(-3.0F, -1.0F, -6.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(up);
		setRotationAngle(up, 0.0F, 0.0F, -0.7854F);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, -1.0F, 0.0F);
		up.addChild(body);
		body.texOffs(45, 47).addBox(-1.0F, -15.0F, -1.0F, 2.0F, 15.0F, 2.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.7071F, -15.7071F, 0.0F);
		up.addChild(head);
		setRotationAngle(head, 0.0F, 0.0F, 0.7854F);
		head.texOffs(38, 15).addBox(-2.0F, -4.0F, -7.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
		head.texOffs(1, 19).addBox(-1.0F, -3.0F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		head.texOffs(0, 1).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		head.texOffs(46, 38).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, -7.0F, 4.0F);
		head.addChild(bone);
		setRotationAngle(bone, 0.6109F, 0.0F, 0.0F);
		bone.texOffs(3, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, 0.8192F, 0.4264F);
		bone.addChild(bone2);
		setRotationAngle(bone2, -0.48F, 0.0F, 0.0F);
		bone2.texOffs(54, 10).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, 0.0F, 2.0F);
		bone2.addChild(bone3);
		setRotationAngle(bone3, 0.3927F, 0.0F, 0.0F);
		bone3.texOffs(33, 57).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 6.0F, 1.0F, 0.0F, false);

		up2 = new ModelRenderer(this);
		up2.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(up2);
		

		body2 = new ModelRenderer(this);
		body2.setPos(-1.0F, -16.0F, 0.0F);
		up2.addChild(body2);
		body2.texOffs(55, 45).addBox(0.0F, -1.0F, -1.0F, 2.0F, 17.0F, 2.0F, 0.0F, false);

		head2 = new ModelRenderer(this);
		head2.setPos(0.0F, -18.0F, 0.0F);
		up2.addChild(head2);
		head2.texOffs(38, 15).addBox(-2.0F, -4.0F, -7.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
		head2.texOffs(1, 19).addBox(-1.0F, -3.0F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		head2.texOffs(0, 1).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		head2.texOffs(46, 38).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(0.0F, -7.0F, 4.0F);
		head2.addChild(bone4);
		setRotationAngle(bone4, 0.6109F, 0.6981F, 0.0F);
		bone4.texOffs(3, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(0.0F, 0.8192F, 0.4264F);
		bone4.addChild(bone5);
		setRotationAngle(bone5, -0.48F, 0.0F, 0.0F);
		bone5.texOffs(54, 10).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(0.0F, 0.0F, 2.0F);
		bone5.addChild(bone6);
		setRotationAngle(bone6, 0.3927F, 0.0F, 0.0F);
		bone6.texOffs(33, 57).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 6.0F, 1.0F, 0.0F, false);

		bone10 = new ModelRenderer(this);
		bone10.setPos(0.0F, -7.0F, 4.0F);
		head2.addChild(bone10);
		setRotationAngle(bone10, 0.6109F, -0.6981F, 0.0F);
		bone10.texOffs(3, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone11 = new ModelRenderer(this);
		bone11.setPos(0.0F, 0.8192F, 0.4264F);
		bone10.addChild(bone11);
		setRotationAngle(bone11, -0.48F, 0.0F, 0.0F);
		bone11.texOffs(54, 10).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone12 = new ModelRenderer(this);
		bone12.setPos(0.0F, 0.0F, 2.0F);
		bone11.addChild(bone12);
		setRotationAngle(bone12, 0.3927F, 0.0F, 0.0F);
		bone12.texOffs(33, 57).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 6.0F, 1.0F, 0.0F, false);

		up3 = new ModelRenderer(this);
		up3.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(up3);
		setRotationAngle(up3, 0.0F, 0.0F, 0.7854F);
		

		body3 = new ModelRenderer(this);
		body3.setPos(-1.0F, -13.0F, 0.0F);
		up3.addChild(body3);
		body3.texOffs(34, 38).addBox(0.0F, -3.0F, -1.0F, 2.0F, 15.0F, 2.0F, 0.0F, false);

		head3 = new ModelRenderer(this);
		head3.setPos(-0.7071F, -15.7071F, 0.0F);
		up3.addChild(head3);
		setRotationAngle(head3, 0.0F, 0.0F, -0.7854F);
		head3.texOffs(38, 15).addBox(-2.0F, -4.0F, -7.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
		head3.texOffs(1, 19).addBox(-1.0F, -3.0F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		head3.texOffs(0, 1).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		head3.texOffs(46, 38).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setPos(0.0F, -7.0F, 4.0F);
		head3.addChild(bone7);
		setRotationAngle(bone7, 0.6109F, 0.7418F, 0.0F);
		bone7.texOffs(3, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setPos(0.0F, 0.8192F, 0.4264F);
		bone7.addChild(bone8);
		setRotationAngle(bone8, -0.48F, 0.0F, 0.0F);
		bone8.texOffs(54, 10).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setPos(0.0F, 0.0F, 2.0F);
		bone8.addChild(bone9);
		setRotationAngle(bone9, 0.3927F, 0.0F, 0.0F);
		bone9.texOffs(33, 57).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 6.0F, 1.0F, 0.0F, false);

		bone13 = new ModelRenderer(this);
		bone13.setPos(0.0F, -7.0F, 4.0F);
		head3.addChild(bone13);
		setRotationAngle(bone13, 0.6109F, -0.6981F, 0.0F);
		bone13.texOffs(3, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone14 = new ModelRenderer(this);
		bone14.setPos(0.0F, 0.8192F, 0.4264F);
		bone13.addChild(bone14);
		setRotationAngle(bone14, -0.48F, 0.0F, 0.0F);
		bone14.texOffs(54, 10).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone15 = new ModelRenderer(this);
		bone15.setPos(0.0F, 0.0F, 2.0F);
		bone14.addChild(bone15);
		setRotationAngle(bone15, 0.3927F, 0.0F, 0.0F);
		bone15.texOffs(33, 57).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 6.0F, 1.0F, 0.0F, false);

		bone16 = new ModelRenderer(this);
		bone16.setPos(0.0F, -7.0F, 4.0F);
		head3.addChild(bone16);
		setRotationAngle(bone16, -0.2182F, 0.0F, 0.0F);
		bone16.texOffs(3, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone17 = new ModelRenderer(this);
		bone17.setPos(0.0F, 0.8192F, 0.4264F);
		bone16.addChild(bone17);
		setRotationAngle(bone17, -0.7854F, 0.0F, 0.0F);
		bone17.texOffs(54, 10).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone18 = new ModelRenderer(this);
		bone18.setPos(0.0F, 0.0F, 2.0F);
		bone17.addChild(bone18);
		setRotationAngle(bone18, 1.309F, 0.0F, 0.0F);
		bone18.texOffs(33, 57).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 6.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(ThreePeaterEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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
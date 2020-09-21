package com.hungteen.pvz.model.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.poolday.DolphinRiderEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class DolphinRiderModel extends EntityModel<DolphinRiderEntity> {
	private final ModelRenderer total;
	private final ModelRenderer zombie;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer dolphin;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;

	public DolphinRiderModel() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		zombie = new ModelRenderer(this);
		zombie.setRotationPoint(0.0F, 12.0F, 9.0F);
		total.addChild(zombie);
		

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, -24.0F, 0.0F);
		zombie.addChild(left_leg);
		setRotationAngle(left_leg, -0.7854F, 0.0F, -0.5236F);
		left_leg.setTextureOffset(112, 100).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 24.0F, 4.0F, 0.0F, false);
		left_leg.setTextureOffset(0, 0).addBox(-2.0F, 22.0F, -6.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, -24.0F, 0.0F);
		zombie.addChild(right_leg);
		setRotationAngle(right_leg, -0.7854F, 0.0F, 0.5236F);
		right_leg.setTextureOffset(93, 100).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 24.0F, 4.0F, 0.0F, false);
		right_leg.setTextureOffset(0, 0).addBox(-2.0F, 22.0F, -6.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -31.0F, 0.0F);
		zombie.addChild(body);
		body.setTextureOffset(0, 0).addBox(-7.0F, -17.0F, -3.0F, 14.0F, 24.0F, 6.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(7.0F, -46.0F, 0.0F);
		zombie.addChild(left_hand);
		left_hand.setTextureOffset(112, 66).addBox(0.0F, -2.0F, -2.0F, 4.0F, 24.0F, 4.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-7.0F, -46.0F, 0.0F);
		zombie.addChild(right_hand);
		right_hand.setTextureOffset(112, 66).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 24.0F, 4.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -48.0F, 0.0F);
		zombie.addChild(head);
		head.setTextureOffset(0, 104).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);

		dolphin = new ModelRenderer(this);
		dolphin.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(dolphin);
		dolphin.setTextureOffset(43, 1).addBox(-7.0F, -12.0F, -8.0F, 14.0F, 12.0F, 28.0F, 0.0F, false);
		dolphin.setTextureOffset(1, 32).addBox(-3.0F, -5.0F, -20.0F, 6.0F, 4.0F, 12.0F, 0.0F, false);
		dolphin.setTextureOffset(1, 50).addBox(-4.0F, -6.0F, 20.0F, 8.0F, 6.0F, 8.0F, 0.0F, false);
		dolphin.setTextureOffset(2, 67).addBox(-3.0F, -5.0F, 28.0F, 6.0F, 4.0F, 4.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 0.0F);
		dolphin.addChild(bone);
		setRotationAngle(bone, 0.0F, -0.2618F, -0.1745F);
		bone.setTextureOffset(3, 77).addBox(8.0F, -1.0F, 0.0F, 16.0F, 1.0F, 12.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		dolphin.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.2618F, 0.1745F);
		bone2.setTextureOffset(41, 43).addBox(-24.0F, -1.0F, 0.0F, 17.0F, 1.0F, 12.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, -4.0F, 32.0F);
		dolphin.addChild(bone3);
		setRotationAngle(bone3, 0.4363F, 0.0F, 0.0F);
		bone3.setTextureOffset(2, 94).addBox(-6.0F, -1.0F, -1.0F, 12.0F, 1.0F, 4.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, -12.0F, 10.0F);
		dolphin.addChild(bone4);
		setRotationAngle(bone4, -0.5236F, 0.0F, 0.0F);
		bone4.setTextureOffset(44, 92).addBox(-1.0F, -7.0F, 0.0F, 2.0F, 7.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(DolphinRiderEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
		this.head.rotateAngleX = headPitch / (180F / (float) Math.PI);
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
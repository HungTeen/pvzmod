package com.hungteen.pvz.model.entity.zombie.other;

import com.hungteen.pvz.entity.zombie.other.RaZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class RaZombieModel extends EntityModel<RaZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer collector;
	private final ModelRenderer cube_r1;
	private final ModelRenderer group2;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer group3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer head;
	private final ModelRenderer bone;

	public RaZombieModel() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, -24.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.setTextureOffset(0, 96).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, -24.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.setTextureOffset(32, 96).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, -24.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -7.0F, 0.0F);
		up.addChild(body);
		body.setTextureOffset(80, 96).addBox(-8.0F, -17.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(12.0F, -20.0F, 0.0F);
		up.addChild(left_hand);
		left_hand.setTextureOffset(96, 64).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-12.0F, -20.0F, 0.0F);
		up.addChild(right_hand);
		setRotationAngle(right_hand, -0.5236F, 0.0F, 0.0F);
		right_hand.setTextureOffset(64, 64).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		collector = new ModelRenderer(this);
		collector.setRotationPoint(1.0F, -1.5F, -24.0F);
		right_hand.addChild(collector);
		setRotationAngle(collector, -1.5708F, -1.5708F, 0.0F);
		collector.setTextureOffset(88, 54).addBox(-5.0F, -0.49F, -5.0F, 10.0F, 0.0F, 10.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.5F, 0.0F);
		collector.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.7854F, 0.0F);
		cube_r1.setTextureOffset(0, 26).addBox(-1.5F, -2.02F, -2.0F, 3.0F, 1.0F, 37.0F, 0.0F, true);

		group2 = new ModelRenderer(this);
		group2.setRotationPoint(0.0F, -7.5F, 0.0F);
		collector.addChild(group2);
		setRotationAngle(group2, 0.0F, -0.1745F, 0.0F);
		

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0F, 8.0F, 0.0F);
		group2.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, -0.3927F, 0.0F);
		cube_r2.setTextureOffset(56, 98).addBox(-1.5F, -1.01F, -10.0F, 3.0F, 1.0F, 5.0F, 0.0F, false);
		cube_r2.setTextureOffset(64, 104).addBox(5.0F, -1.01F, -1.5F, 5.0F, 1.0F, 3.0F, 0.0F, false);
		cube_r2.setTextureOffset(64, 108).addBox(-1.5F, -1.01F, 5.0F, 3.0F, 1.0F, 5.0F, 0.0F, false);
		cube_r2.setTextureOffset(64, 114).addBox(-10.0F, -1.01F, -1.5F, 5.0F, 1.0F, 3.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.0F, 8.0F, 0.0F);
		group2.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.3927F, 0.0F);
		cube_r3.setTextureOffset(64, 118).addBox(5.0F, -1.01F, -1.5F, 5.0F, 1.0F, 3.0F, 0.0F, false);
		cube_r3.setTextureOffset(64, 122).addBox(-1.5F, -1.01F, 5.0F, 3.0F, 1.0F, 5.0F, 0.0F, false);
		cube_r3.setTextureOffset(72, 96).addBox(-10.0F, -1.01F, -1.5F, 5.0F, 1.0F, 3.0F, 0.0F, false);
		cube_r3.setTextureOffset(81, 58).addBox(-1.5F, -1.01F, -10.0F, 3.0F, 1.0F, 5.0F, 0.0F, false);

		group3 = new ModelRenderer(this);
		group3.setRotationPoint(0.0F, -7.5F, 0.0F);
		collector.addChild(group3);
		setRotationAngle(group3, 0.0F, -0.1745F, 0.0F);
		group3.setTextureOffset(0, 76).addBox(-10.0F, 6.98F, -1.5F, 5.0F, 1.0F, 3.0F, 0.0F, false);
		group3.setTextureOffset(0, 70).addBox(-1.5F, 6.98F, 5.0F, 3.0F, 1.0F, 5.0F, 0.0F, false);
		group3.setTextureOffset(0, 66).addBox(5.0F, 6.98F, -1.5F, 5.0F, 1.0F, 3.0F, 0.0F, false);
		group3.setTextureOffset(48, 69).addBox(-1.5F, 6.98F, -10.0F, 3.0F, 1.0F, 5.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setRotationPoint(0.0F, 8.0F, 0.0F);
		group3.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.7854F, 0.0F);
		cube_r4.setTextureOffset(54, 59).addBox(5.0F, -1.02F, -1.5F, 5.0F, 1.0F, 3.0F, 0.0F, false);
		cube_r4.setTextureOffset(88, 66).addBox(-1.5F, -1.02F, 5.0F, 3.0F, 1.0F, 5.0F, 0.0F, true);
		cube_r4.setTextureOffset(48, 75).addBox(-10.0F, -1.02F, -1.5F, 5.0F, 1.0F, 3.0F, 0.0F, false);
		cube_r4.setTextureOffset(24, 97).addBox(-1.5F, -1.02F, -10.0F, 3.0F, 1.0F, 5.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -24.0F, 0.0F);
		up.addChild(head);
		head.setTextureOffset(0, 64).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);
		head.setTextureOffset(74, 1).addBox(-9.0F, -20.0F, -9.0F, 18.0F, 7.0F, 9.0F, 0.0F, false);
		head.setTextureOffset(0, 43).addBox(8.0F, -19.0F, -8.0F, 4.0F, 14.0F, 5.0F, 0.0F, false);
		head.setTextureOffset(0, 23).addBox(-12.0F, -19.0F, -8.0F, 4.0F, 14.0F, 5.0F, 0.0F, false);
		head.setTextureOffset(45, 49).addBox(-9.0F, -19.0F, 0.0F, 18.0F, 5.0F, 3.0F, 0.0F, false);
		head.setTextureOffset(87, 48).addBox(-9.0F, -18.0F, 3.0F, 18.0F, 4.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(43, 43).addBox(-9.0F, -17.0F, 5.0F, 18.0F, 3.0F, 4.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -14.0F, -9.0F);
		head.addChild(bone);
		setRotationAngle(bone, 0.0F, -0.7854F, 0.0F);
		bone.setTextureOffset(20, 57).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, -0.05F, false);
	}

	@Override
	public void setRotationAngles(RaZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.left_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        if(entity.checkCanWorkNow()) {
        	this.right_hand.rotateAngleX = - 1F;
        } else {
        	this.right_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        }
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
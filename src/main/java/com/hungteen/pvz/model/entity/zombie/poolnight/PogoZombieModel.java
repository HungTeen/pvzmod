package com.hungteen.pvz.model.entity.zombie.poolnight;

import com.hungteen.pvz.entity.zombie.poolnight.PogoZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class PogoZombieModel extends EntityModel<PogoZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer zombie;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer bone3;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer pogo;

	public PogoZombieModel() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 2.0F);
		setRotationAngle(total, 0.0873F, 0.0F, 0.0F);
		

		zombie = new ModelRenderer(this);
		zombie.setRotationPoint(0.0F, -6.0F, 5.0F);
		total.addChild(zombie);
		

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, -24.0F, 0.0F);
		zombie.addChild(up);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -7.0F, 0.0F);
		up.addChild(body);
		body.setTextureOffset(80, 96).addBox(-8.0F, -17.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(12.0F, -20.0F, 0.0F);
		up.addChild(left_hand);
		setRotationAngle(left_hand, -0.4363F, 0.0873F, 0.0F);
		left_hand.setTextureOffset(104, 62).addBox(-4.0F, -4.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-12.0F, -20.0F, 0.0F);
		up.addChild(right_hand);
		setRotationAngle(right_hand, -0.4363F, -0.0873F, 0.0F);
		right_hand.setTextureOffset(103, 26).addBox(-2.0F, -4.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -24.0F, 0.0F);
		up.addChild(head);
		head.setTextureOffset(20, 100).addBox(-7.0F, -14.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, -3.091F, -8.0834F);
		head.addChild(bone3);
		bone3.setTextureOffset(76, 12).addBox(-8.0F, -5.2921F, -0.2749F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		bone3.setTextureOffset(105, 12).addBox(7.0F, -5.2921F, -0.2749F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		bone3.setTextureOffset(56, 18).addBox(-7.0F, -5.2921F, -0.2749F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bone3.setTextureOffset(36, 14).addBox(5.0F, -5.2921F, -0.2749F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bone3.setTextureOffset(16, 21).addBox(-1.0F, -5.2921F, -0.2749F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bone3.setTextureOffset(20, 4).addBox(-5.0F, -6.2921F, -0.2749F, 4.0F, 3.0F, 1.0F, 0.0F, false);
		bone3.setTextureOffset(2, 10).addBox(1.0F, -6.2921F, -0.2749F, 4.0F, 3.0F, 1.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, -24.0F, 0.0F);
		zombie.addChild(left_leg);
		setRotationAngle(left_leg, -0.4363F, -0.2618F, 0.0F);
		left_leg.setTextureOffset(58, 62).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, -24.0F, 0.0F);
		zombie.addChild(right_leg);
		setRotationAngle(right_leg, -0.4363F, 0.2618F, 0.0F);
		right_leg.setTextureOffset(67, 27).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		pogo = new ModelRenderer(this);
		pogo.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(pogo);
		pogo.setTextureOffset(24, 85).addBox(-1.0F, -10.0F, -6.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
		pogo.setTextureOffset(36, 66).addBox(-2.0F, -22.0F, -7.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		pogo.setTextureOffset(32, 32).addBox(-3.0F, -38.0F, -8.0F, 6.0F, 16.0F, 6.0F, 0.0F, false);
		pogo.setTextureOffset(0, 87).addBox(3.0F, -34.0F, -6.0F, 9.0F, 2.0F, 2.0F, 0.0F, false);
		pogo.setTextureOffset(6, 51).addBox(-8.0F, -8.0F, -6.0F, 7.0F, 2.0F, 2.0F, 0.0F, false);
		pogo.setTextureOffset(2, 67).addBox(1.0F, -8.0F, -6.0F, 7.0F, 2.0F, 2.0F, 0.0F, false);
		pogo.setTextureOffset(4, 37).addBox(-12.0F, -34.0F, -6.0F, 9.0F, 2.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(PogoZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if(entity.hasPogo()) {
			this.left_hand.rotateAngleX = -0.4363F;
			this.left_hand.rotateAngleY = 0.0873F;
			this.right_hand.rotateAngleX = -0.4363F;
			this.right_hand.rotateAngleY = -0.0873F;
			this.left_leg.rotateAngleX = -0.4363F;
			this.left_leg.rotateAngleY = -0.2618F;
			this.right_leg.rotateAngleX = -0.4363F;
			this.right_leg.rotateAngleY = 0.2618F;
			this.total.rotateAngleX = 0.0873F;
		} else {
	        this.left_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	        this.left_hand.rotateAngleY = 0;
	        this.right_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	        this.right_hand.rotateAngleY = 0;
	        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
			this.left_leg.rotateAngleY = 0;
	        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	        this.right_leg.rotateAngleY = 0;
	        this.total.rotateAngleX = 0;
		}
		this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
		this.pogo.showModel = entity.hasMetal();
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
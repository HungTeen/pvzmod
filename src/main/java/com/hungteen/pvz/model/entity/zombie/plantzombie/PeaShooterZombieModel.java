package com.hungteen.pvz.model.entity.zombie.plantzombie;

import com.hungteen.pvz.entity.zombie.plantzombie.PeaShooterZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class PeaShooterZombieModel extends EntityModel<PeaShooterZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer up;
	private final ModelRenderer head;
	private final ModelRenderer right_hand;
	private final ModelRenderer left_hand;
	private final ModelRenderer body;

	public PeaShooterZombieModel() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, -23.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.setTextureOffset(0, 0).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, -23.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.setTextureOffset(44, 0).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(up);
		

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -48.0F, 0.0F);
		up.addChild(head);
		head.setTextureOffset(228, 243).addBox(-5.0F, -12.0F, 7.0F, 10.0F, 10.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(198, 202).addBox(-7.0F, -14.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(230, 168).addBox(7.0F, -12.0F, -5.0F, 1.0F, 10.0F, 10.0F, 0.0F, false);
		head.setTextureOffset(234, 137).addBox(-8.0F, -12.0F, -5.0F, 1.0F, 10.0F, 10.0F, 0.0F, false);
		head.setTextureOffset(208, 251).addBox(-1.0F, -11.0F, 8.0F, 2.0F, 1.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(158, 245).addBox(-5.0F, -15.0F, -5.0F, 10.0F, 1.0F, 10.0F, 0.0F, false);
		head.setTextureOffset(137, 240).addBox(-2.0F, -12.0F, 12.0F, 4.0F, 5.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(80, 227).addBox(-4.0F, -11.0F, -21.0F, 8.0F, 8.0F, 14.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-8.0F, -48.0F, 0.0F);
		up.addChild(right_hand);
		right_hand.setTextureOffset(96, 0).addBox(-8.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(9.0F, -48.0F, 0.0F);
		up.addChild(left_hand);
		left_hand.setTextureOffset(96, 60).addBox(-1.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -31.0F, 0.0F);
		up.addChild(body);
		body.setTextureOffset(0, 41).addBox(-8.0F, -17.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(PeaShooterZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.right_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
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
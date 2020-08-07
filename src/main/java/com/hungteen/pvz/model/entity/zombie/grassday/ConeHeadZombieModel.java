package com.hungteen.pvz.model.entity.zombie.grassday;

import com.hungteen.pvz.entity.zombie.grassday.ConeHeadZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class ConeHeadZombieModel extends EntityModel<ConeHeadZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer head;
	private final ModelRenderer body2;
	private final ModelRenderer right_hand;
	private final ModelRenderer left_hand;
	private final ModelRenderer body;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;

	public ConeHeadZombieModel() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -48.0F, 0.0F);
		total.addChild(head);
		head.setTextureOffset(16, 96).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		body2 = new ModelRenderer(this);
		body2.setRotationPoint(0.0F, 1.0F, 0.0F);
		head.addChild(body2);
		body2.setTextureOffset(152, 2).addBox(-8.0F, -22.0F, -8.0F, 16.0F, 5.0F, 16.0F, 0.0F, false);
		body2.setTextureOffset(180, 37).addBox(-9.0F, -17.0F, -9.0F, 18.0F, 1.0F, 1.0F, 0.0F, false);
		body2.setTextureOffset(187, 52).addBox(-9.0F, -17.0F, 8.0F, 18.0F, 1.0F, 1.0F, 0.0F, false);
		body2.setTextureOffset(194, 70).addBox(-9.0F, -17.0F, -8.0F, 1.0F, 1.0F, 16.0F, 0.0F, false);
		body2.setTextureOffset(186, 107).addBox(8.0F, -17.0F, -8.0F, 1.0F, 1.0F, 16.0F, 0.0F, false);
		body2.setTextureOffset(164, 140).addBox(-6.0F, -28.0F, -6.0F, 12.0F, 6.0F, 12.0F, 0.0F, false);
		body2.setTextureOffset(172, 183).addBox(-4.0F, -34.0F, -4.0F, 8.0F, 6.0F, 8.0F, 0.0F, false);
		body2.setTextureOffset(207, 212).addBox(-2.0F, -39.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);
		body2.setTextureOffset(176, 236).addBox(-1.0F, -42.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-8.0F, -48.0F, 0.0F);
		total.addChild(right_hand);
		right_hand.setTextureOffset(96, 0).addBox(-8.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(8.0F, -48.0F, 0.0F);
		total.addChild(left_hand);
		left_hand.setTextureOffset(96, 60).addBox(0.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -35.0F, 0.0F);
		total.addChild(body);
		body.setTextureOffset(0, 41).addBox(-8.0F, -13.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-5.0F, -23.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.setTextureOffset(44, 0).addBox(-3.0F, -1.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(3.0F, -23.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.setTextureOffset(0, 0).addBox(-3.0F, -1.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(ConeHeadZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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
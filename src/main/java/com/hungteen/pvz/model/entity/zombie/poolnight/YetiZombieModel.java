package com.hungteen.pvz.model.entity.zombie.poolnight;

import com.hungteen.pvz.entity.zombie.poolnight.YetiZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class YetiZombieModel extends EntityModel<YetiZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;

	public YetiZombieModel() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 2.0F);
		

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-7.0F, -36.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.setTextureOffset(5, 152).addBox(-5.0F, 0.0F, -6.0F, 12.0F, 36.0F, 12.0F, 0.0F, false);
		right_leg.setTextureOffset(156, 164).addBox(-5.0F, 34.0F, -12.0F, 12.0F, 2.0F, 6.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(6.0F, -36.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.setTextureOffset(8, 206).addBox(-6.0F, 0.0F, -6.0F, 12.0F, 36.0F, 12.0F, 0.0F, false);
		left_leg.setTextureOffset(155, 178).addBox(-6.0F, 34.0F, -12.0F, 12.0F, 2.0F, 6.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, -36.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.setTextureOffset(174, 202).addBox(-12.0F, -36.0F, -8.0F, 24.0F, 36.0F, 16.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(18.0F, -30.0F, 0.0F);
		up.addChild(left_hand);
		left_hand.setTextureOffset(204, 144).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 40.0F, 12.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-19.0F, -30.0F, 0.0F);
		up.addChild(right_hand);
		right_hand.setTextureOffset(203, 86).addBox(-5.0F, -6.0F, -6.0F, 12.0F, 40.0F, 12.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -36.0F, 0.0F);
		up.addChild(head);
		head.setTextureOffset(68, 205).addBox(-12.0F, -24.0F, -12.0F, 24.0F, 24.0F, 24.0F, 0.0F, false);
		head.setTextureOffset(156, 195).addBox(-4.0F, -1.0F, -13.0F, 8.0F, 5.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(YetiZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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
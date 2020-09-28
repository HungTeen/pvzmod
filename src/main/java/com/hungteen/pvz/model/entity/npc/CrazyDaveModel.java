package com.hungteen.pvz.model.entity.npc;

import com.hungteen.pvz.entity.npc.CrazyDaveEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class CrazyDaveModel extends EntityModel<CrazyDaveEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer hat;

	public CrazyDaveModel() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, -25.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.setTextureOffset(1, 96).addBox(-3.0F, 0.0F, -3.0F, 7.0F, 25.0F, 6.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(3.0F, -25.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.setTextureOffset(30, 96).addBox(-3.0F, 0.0F, -3.0F, 7.0F, 25.0F, 6.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, -25.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(-1.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.setTextureOffset(59, 94).addBox(-6.0F, -24.0F, -4.0F, 14.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(10.0F, -21.0F, 0.0F);
		up.addChild(left_hand);
		left_hand.setTextureOffset(102, 3).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 25.0F, 6.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-9.0F, -21.0F, 0.0F);
		up.addChild(right_hand);
		right_hand.setTextureOffset(102, 36).addBox(-4.0F, -3.0F, -3.0F, 6.0F, 25.0F, 6.0F, 0.0F, true);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -25.0F, 0.0F);
		up.addChild(head);
		head.setTextureOffset(1, 1).addBox(-7.0F, -13.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(1, 33).addBox(-7.0F, 1.0F, -7.0F, 14.0F, 2.0F, 1.0F, 0.0F, false);

		hat = new ModelRenderer(this);
		hat.setRotationPoint(0.0F, -16.0F, 0.0F);
		head.addChild(hat);
		hat.setTextureOffset(1, 40).addBox(-2.0F, -3.0F, -18.0F, 4.0F, 3.0F, 10.0F, 0.0F, false);
		hat.setTextureOffset(3, 61).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 11.0F, 16.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(CrazyDaveEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.right_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.hat.rotateAngleY= ageInTicks*1.0f;
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
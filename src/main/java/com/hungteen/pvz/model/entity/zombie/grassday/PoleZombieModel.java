package com.hungteen.pvz.model.entity.zombie.grassday;

import com.hungteen.pvz.entity.zombie.grassday.PoleZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class PoleZombieModel extends EntityModel<PoleZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer bone;
	private final ModelRenderer pole;
	private final ModelRenderer head;

	public PoleZombieModel() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, -23.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.setTextureOffset(161, 220).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 24.0F, 4.0F, 0.0F, false);
		right_leg.setTextureOffset(124, 239).addBox(-3.0F, 21.0F, -7.0F, 6.0F, 2.0F, 10.0F, 0.0F, false);
		right_leg.setTextureOffset(158, 202).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 4.0F, 6.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, -23.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.setTextureOffset(228, 219).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 24.0F, 4.0F, 0.0F, false);
		left_leg.setTextureOffset(190, 238).addBox(-3.0F, 21.0F, -7.0F, 6.0F, 2.0F, 10.0F, 0.0F, false);
		left_leg.setTextureOffset(190, 198).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 4.0F, 6.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, -26.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -6.0F, 0.0F);
		up.addChild(body);
		body.setTextureOffset(2, 222).addBox(-8.0F, -20.0F, -3.0F, 16.0F, 26.0F, 6.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(9.0F, -22.0F, 0.0F);
		up.addChild(left_hand);
		left_hand.setTextureOffset(49, 221).addBox(-1.0F, -4.0F, -3.0F, 6.0F, 26.0F, 6.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-8.0F, -22.0F, 4.0F);
		up.addChild(right_hand);
		setRotationAngle(right_hand, -1.5708F, 0.0F, 0.0F);
		right_hand.setTextureOffset(78, 228).addBox(-6.0F, 0.0F, -4.0F, 6.0F, 20.0F, 6.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-5.0F, 18.0F, -1.0F);
		right_hand.addChild(bone);
		setRotationAngle(bone, -1.5708F, 0.0F, 0.0F);
		bone.setTextureOffset(228, 195).addBox(-1.0F, 3.0F, -4.0F, 6.0F, 10.0F, 6.0F, 0.0F, false);

		pole = new ModelRenderer(this);
		pole.setRotationPoint(0.0F, 9.0F, 0.0F);
		bone.addChild(pole);
		pole.setTextureOffset(76, 4).addBox(1.0F, 2.1472F, -45.3617F, 2.0F, 2.0F, 85.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -24.0F, 0.0F);
		up.addChild(head);
		head.setTextureOffset(196, 104).addBox(-7.0F, -16.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(PoleZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;
        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.2F * limbSwingAmount;
//        this.right_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
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
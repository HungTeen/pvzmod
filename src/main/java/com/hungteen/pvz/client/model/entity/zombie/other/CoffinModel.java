package com.hungteen.pvz.client.model.entity.zombie.other;

import com.hungteen.pvz.common.entity.zombie.other.CoffinEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public class CoffinModel extends EntityModel<CoffinEntity> {

	private final ModelRenderer total;
	private final ModelRenderer coffin;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone;
	private final ModelRenderer zombie1;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer righthand_r1;
	private final ModelRenderer head;
	private final ModelRenderer bone4;
	private final ModelRenderer zombie2;
	private final ModelRenderer right_leg2;
	private final ModelRenderer left_leg2;
	private final ModelRenderer up2;
	private final ModelRenderer body2;
	private final ModelRenderer left_hand2;
	private final ModelRenderer lefthand_r1;
	private final ModelRenderer right_hand2;
	private final ModelRenderer head2;
	private final ModelRenderer bone5;
	private final ModelRenderer zombie3;
	private final ModelRenderer right_leg3;
	private final ModelRenderer left_leg3;
	private final ModelRenderer up3;
	private final ModelRenderer body3;
	private final ModelRenderer left_hand3;
	private final ModelRenderer right_hand3;
	private final ModelRenderer righthand_r2;
	private final ModelRenderer head3;
	private final ModelRenderer bone6;
	private final ModelRenderer zombie4;
	private final ModelRenderer right_leg4;
	private final ModelRenderer left_leg4;
	private final ModelRenderer up4;
	private final ModelRenderer body4;
	private final ModelRenderer left_hand4;
	private final ModelRenderer right_hand4;
	private final ModelRenderer head4;
	private final ModelRenderer bone7;

	public CoffinModel() {
		texWidth = 512;
		texHeight = 512;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);

		coffin = new ModelRenderer(this);
		coffin.setPos(0.0F, -48.0F, 0.0F);
		total.addChild(coffin);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, 0.0F, 0.0F);
		coffin.addChild(bone2);
		bone2.texOffs(213, 64).addBox(-16.0F, -24.0171F, -47.2149F, 32.0F, 11.0F, 96.0F, 0.0F, false);
		bone2.texOffs(219, 197).addBox(-15.0F, -13.0171F, -46.2149F, 30.0F, 11.0F, 94.0F, 0.0F, false);
		bone2.texOffs(206, 334).addBox(-18.0F, -2.0171F, -49.2149F, 36.0F, 2.0F, 100.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, 0.0F, 0.0F);
		bone2.addChild(bone3);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 0.0F, 0.0F);
		coffin.addChild(bone);
		bone.texOffs(18, 259).addBox(-19.0F, -28.0171F, -50.2149F, 38.0F, 3.0F, 102.0F, 0.0F, false);
		bone.texOffs(38, 389).addBox(-15.0F, -25.0171F, -46.2149F, 30.0F, 1.0F, 94.0F, 0.0F, false);
		bone.texOffs(14, 128).addBox(-17.0F, -29.0171F, -48.2149F, 34.0F, 1.0F, 98.0F, 0.0F, false);
		bone.texOffs(24, 13).addBox(-15.0F, -31.0171F, -44.2149F, 30.0F, 2.0F, 90.0F, 0.0F, false);

		zombie1 = new ModelRenderer(this);
		zombie1.setPos(29.0F, 0.0F, -16.0F);
		total.addChild(zombie1);

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-4.0F, -24.0F, 0.0F);
		zombie1.addChild(right_leg);
		right_leg.texOffs(44, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(4.0F, -24.0F, 0.0F);
		zombie1.addChild(left_leg);
		left_leg.texOffs(0, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setPos(0.0F, -24.0F, 0.0F);
		zombie1.addChild(up);

		body = new ModelRenderer(this);
		body.setPos(0.0F, -7.0F, 0.0F);
		up.addChild(body);
		body.texOffs(0, 41).addBox(-8.0F, -17.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(12.0F, -20.0F, 0.0F);
		up.addChild(left_hand);
		left_hand.texOffs(196, 6).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-12.0F, -20.0F, 0.0F);
		up.addChild(right_hand);

		righthand_r1 = new ModelRenderer(this);
		righthand_r1.setPos(-1.0F, 0.0F, 0.0F);
		right_hand.addChild(righthand_r1);
		setRotationAngle(righthand_r1, -1.5708F, 0.0F, 0.0F);
		righthand_r1.texOffs(455, 41).addBox(-3.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -24.0F, 0.0F);
		up.addChild(head);
		head.texOffs(16, 132).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(0.0F, -17.0F, 0.0F);
		head.addChild(bone4);
		bone4.texOffs(415, 18).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 7.0F, 12.0F, 0.0F, false);

		zombie2 = new ModelRenderer(this);
		zombie2.setPos(-28.0F, 0.0F, -16.0F);
		total.addChild(zombie2);

		right_leg2 = new ModelRenderer(this);
		right_leg2.setPos(-4.0F, -24.0F, 0.0F);
		zombie2.addChild(right_leg2);
		right_leg2.texOffs(44, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_leg2 = new ModelRenderer(this);
		left_leg2.setPos(4.0F, -24.0F, 0.0F);
		zombie2.addChild(left_leg2);
		left_leg2.texOffs(0, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		up2 = new ModelRenderer(this);
		up2.setPos(0.0F, -24.0F, 0.0F);
		zombie2.addChild(up2);

		body2 = new ModelRenderer(this);
		body2.setPos(0.0F, 0.0F, 0.0F);
		up2.addChild(body2);
		body2.texOffs(0, 41).addBox(-8.0F, -24.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand2 = new ModelRenderer(this);
		left_hand2.setPos(12.0F, -20.0F, 0.0F);
		up2.addChild(left_hand2);

		lefthand_r1 = new ModelRenderer(this);
		lefthand_r1.setPos(0.0F, 0.0F, 0.0F);
		left_hand2.addChild(lefthand_r1);
		setRotationAngle(lefthand_r1, -1.5708F, 0.0F, 0.0F);
		lefthand_r1.texOffs(196, 6).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		right_hand2 = new ModelRenderer(this);
		right_hand2.setPos(-12.0F, -20.0F, 0.0F);
		up2.addChild(right_hand2);
		right_hand2.texOffs(455, 41).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		head2 = new ModelRenderer(this);
		head2.setPos(0.0F, -24.0F, 0.0F);
		up2.addChild(head2);
		head2.texOffs(16, 132).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(0.0F, 48.0F, 0.0F);
		head2.addChild(bone5);
		bone5.texOffs(415, 18).addBox(-6.0F, -71.0F, -6.0F, 12.0F, 7.0F, 12.0F, 0.0F, false);

		zombie3 = new ModelRenderer(this);
		zombie3.setPos(29.0F, 0.0F, 32.0F);
		total.addChild(zombie3);

		right_leg3 = new ModelRenderer(this);
		right_leg3.setPos(-4.0F, -24.0F, 1.0F);
		zombie3.addChild(right_leg3);
		right_leg3.texOffs(44, 0).addBox(-4.0F, 0.0F, -5.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_leg3 = new ModelRenderer(this);
		left_leg3.setPos(4.0F, -24.0F, 1.0F);
		zombie3.addChild(left_leg3);
		left_leg3.texOffs(0, 0).addBox(-4.0F, 0.0F, -5.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		up3 = new ModelRenderer(this);
		up3.setPos(0.0F, -24.0F, -45.0F);
		zombie3.addChild(up3);

		body3 = new ModelRenderer(this);
		body3.setPos(0.0F, -7.0F, 45.0F);
		up3.addChild(body3);
		body3.texOffs(0, 41).addBox(-8.0F, -17.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand3 = new ModelRenderer(this);
		left_hand3.setPos(12.0F, -20.0F, 45.0F);
		up3.addChild(left_hand3);
		left_hand3.texOffs(196, 6).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		right_hand3 = new ModelRenderer(this);
		right_hand3.setPos(-12.0F, -20.0F, 45.0F);
		up3.addChild(right_hand3);

		righthand_r2 = new ModelRenderer(this);
		righthand_r2.setPos(0.0F, 0.0F, 0.0F);
		right_hand3.addChild(righthand_r2);
		setRotationAngle(righthand_r2, -1.5708F, 0.0F, 0.0F);
		righthand_r2.texOffs(455, 41).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		head3 = new ModelRenderer(this);
		head3.setPos(0.0F, -24.0F, 45.0F);
		up3.addChild(head3);
		head3.texOffs(16, 132).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(0.0F, -17.0F, 0.0F);
		head3.addChild(bone6);
		bone6.texOffs(415, 18).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 7.0F, 12.0F, 0.0F, false);

		zombie4 = new ModelRenderer(this);
		zombie4.setPos(-28.0F, 0.0F, 32.0F);
		total.addChild(zombie4);

		right_leg4 = new ModelRenderer(this);
		right_leg4.setPos(-4.0F, -24.0F, 0.0F);
		zombie4.addChild(right_leg4);
		right_leg4.texOffs(44, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_leg4 = new ModelRenderer(this);
		left_leg4.setPos(4.0F, -24.0F, 0.0F);
		zombie4.addChild(left_leg4);
		left_leg4.texOffs(0, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		up4 = new ModelRenderer(this);
		up4.setPos(0.0F, -24.0F, -1.0F);
		zombie4.addChild(up4);

		body4 = new ModelRenderer(this);
		body4.setPos(0.0F, 0.0F, 1.0F);
		up4.addChild(body4);
		body4.texOffs(0, 41).addBox(-8.0F, -24.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand4 = new ModelRenderer(this);
		left_hand4.setPos(12.0F, -20.0F, 1.0F);
		up4.addChild(left_hand4);
		setRotationAngle(left_hand4, -1.5708F, 0.0F, 0.0F);
		left_hand4.texOffs(196, 6).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		right_hand4 = new ModelRenderer(this);
		right_hand4.setPos(-12.0F, -20.0F, 1.0F);
		up4.addChild(right_hand4);
		right_hand4.texOffs(455, 41).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		head4 = new ModelRenderer(this);
		head4.setPos(0.0F, -24.0F, 1.0F);
		up4.addChild(head4);
		head4.texOffs(16, 132).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setPos(0.0F, -17.0F, 0.0F);
		head4.addChild(bone7);
		bone7.texOffs(415, 18).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 7.0F, 12.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(CoffinEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		this.updateParts(entity);
		// front left
		this.left_leg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.right_leg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.left_hand.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		// front right
		this.left_leg2.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.right_leg2.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.right_hand2.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		// back left
		this.left_leg3.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.right_leg3.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.left_hand3.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		// back right
		this.left_leg4.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.right_leg4.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.right_hand4.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		// coffin angry
		float percent = entity.getHealth() / entity.getMaxHealth();
		if (percent <= 1f / 6) {
			this.coffin.zRot = MathHelper.sin(ageInTicks * 0.8f) * 0.2f;
		} else {
			this.coffin.zRot = 0;
		}
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		total.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	public void updateParts(CoffinEntity entity) {
		float percent = entity.getHealth() / entity.getMaxHealth();
		this.zombie1.visible = percent > 5f / 6;
		this.zombie2.visible = percent > 4f / 6;
		this.zombie3.visible = percent > 3f / 6;
		this.zombie4.visible = percent > 2f / 6;
	}
}
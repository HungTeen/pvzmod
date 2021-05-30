package com.hungteen.pvz.client.model.entity.creature;

import com.hungteen.pvz.common.entity.creature.FoodieZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public class FoodieZombieModel extends EntityModel<FoodieZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer head;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer right_hand;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer left_hand;
	private final ModelRenderer armor;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer body;
	private final ModelRenderer right_leg;
	private final ModelRenderer right_foot;
	private final ModelRenderer left_leg;
	private final ModelRenderer left_foot;

	public FoodieZombieModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		setRotationAngle(total, 0.0F, 0.0F, 0.0F);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -20.0F, 0.0F);
		total.addChild(head);
		head.texOffs(80, 104).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
		head.texOffs(102, 89).addBox(6.0F, -8.0F, -6.0F, 1.0F, 2.0F, 12.0F, 0.0F, false);
		head.texOffs(102, 73).addBox(-7.0F, -8.0F, -6.0F, 1.0F, 2.0F, 12.0F, 0.0F, false);
		head.texOffs(102, 68).addBox(-6.0F, -8.0F, 6.0F, 12.0F, 2.0F, 1.0F, 0.0F, false);
		head.texOffs(102, 60).addBox(-6.0F, -9.0F, -7.0F, 12.0F, 4.0F, 1.0F, 0.0F, false);
		head.texOffs(110, 56).addBox(-4.0F, -5.0F, -7.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		head.texOffs(110, 52).addBox(-4.0F, -10.0F, -7.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(0.0F, 0.0F, -7.0F);
		head.addChild(bone6);
		bone6.texOffs(118, 45).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		bone6.texOffs(110, 39).addBox(-8.0F, -2.0F, -2.0F, 7.0F, 2.0F, 2.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setPos(-7.0F, -1.0F, 0.0F);
		bone6.addChild(bone7);
		setRotationAngle(bone7, 1.309F, -0.6981F, 0.0F);
		bone7.texOffs(40, 109).addBox(-1.6428F, -1.6943F, -0.3237F, 2.0F, 2.0F, 17.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-8.0F, -20.0F, 0.0F);
		total.addChild(right_hand);
		right_hand.texOffs(112, 1).addBox(-4.0F, 0.0F, -2.0F, 4.0F, 25.0F, 4.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, 0.0F, 0.0F);
		right_hand.addChild(bone3);
		bone3.texOffs(80, 89).addBox(-5.0F, 3.0F, -3.0F, 1.0F, 5.0F, 6.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(-4.0F, 5.0F, -2.0F);
		bone3.addChild(bone4);
		setRotationAngle(bone4, 0.0F, -1.1345F, 0.0F);
		bone4.texOffs(76, 103).addBox(-1.0F, -2.0F, -4.0F, 1.0F, 5.0F, 4.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(-4.0F, 5.0F, 2.0F);
		right_hand.addChild(bone5);
		setRotationAngle(bone5, 0.0F, 1.1345F, 0.0F);
		bone5.texOffs(96, 47).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 5.0F, 4.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(9.0F, -20.0F, 0.0F);
		total.addChild(left_hand);
		left_hand.texOffs(94, 0).addBox(-1.0F, 0.0F, -2.0F, 4.0F, 25.0F, 4.0F, 0.0F, false);

		armor = new ModelRenderer(this);
		armor.setPos(3.0F, 5.0F, 0.0F);
		left_hand.addChild(armor);
		armor.texOffs(76, 3).addBox(0.0F, -2.0F, -3.0F, 1.0F, 5.0F, 6.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 0.0F, 3.0F);
		armor.addChild(bone);
		setRotationAngle(bone, 0.0F, -1.1345F, 0.0F);
		bone.texOffs(79, 18).addBox(-0.866F, -2.0F, -0.5F, 1.0F, 5.0F, 5.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, 1.0F, -2.0F);
		armor.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 1.1345F, 0.0F);
		bone2.texOffs(80, 35).addBox(0.0F, -3.0F, -4.0F, 1.0F, 5.0F, 4.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, -3.0F, 0.0F);
		total.addChild(body);
		body.texOffs(1, 93).addBox(-8.0F, -17.0F, -3.0F, 16.0F, 25.0F, 6.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-4.0F, 5.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.texOffs(1, 64).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 23.0F, 4.0F, 0.0F, false);

		right_foot = new ModelRenderer(this);
		right_foot.setPos(0.0F, 24.0F, 0.0F);
		right_leg.addChild(right_foot);
		right_foot.texOffs(22, 73).addBox(-2.0F, -1.0F, -11.0F, 4.0F, 2.0F, 14.0F, 0.0F, false);
		right_foot.texOffs(56, 95).addBox(2.0F, -1.0F, -10.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
		right_foot.texOffs(65, 81).addBox(-3.0F, -1.0F, -10.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(4.0F, 5.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.texOffs(57, 2).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 23.0F, 4.0F, 0.0F, false);

		left_foot = new ModelRenderer(this);
		left_foot.setPos(0.0F, 24.0F, 0.0F);
		left_leg.addChild(left_foot);
		left_foot.texOffs(62, 62).addBox(-2.0F, -1.0F, -11.0F, 4.0F, 2.0F, 14.0F, 0.0F, false);
		left_foot.texOffs(49, 64).addBox(2.0F, -1.0F, -10.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
		left_foot.texOffs(60, 50).addBox(-3.0F, -1.0F, -10.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(FoodieZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		this.total.xRot = 3.14159f / 2;
		this.head.xRot = -3.14159f / 2;
		this.head.yRot = 0;
		this.right_hand.xRot = 0;
		this.left_hand.xRot = 0;
		this.right_leg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.left_leg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.right_foot.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.left_foot.xRot = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
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
}
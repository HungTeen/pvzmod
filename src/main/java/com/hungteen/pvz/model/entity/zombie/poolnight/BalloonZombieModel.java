package com.hungteen.pvz.model.entity.zombie.poolnight;

import com.hungteen.pvz.entity.zombie.poolnight.BalloonZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class BalloonZombieModel extends EntityModel<BalloonZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer balloon;
	private final ModelRenderer other;
	private final ModelRenderer tile;
	private final ModelRenderer zombie;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer up;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer bone;

	public BalloonZombieModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, -6.0F, 0.0F);
		

		balloon = new ModelRenderer(this);
		balloon.setPos(0.0F, -4.0F, 0.75F);
		total.addChild(balloon);
		

		other = new ModelRenderer(this);
		other.setPos(0.0F, 1.0F, 0.0F);
		balloon.addChild(other);
		other.texOffs(78, 91).addBox(-1.0F, -20.0F, -1.0F, 2.0F, 21.0F, 2.0F, 0.0F, false);
		other.texOffs(84, 86).addBox(-3.0F, -21.0F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);
		other.texOffs(87, 75).addBox(-5.0F, -22.0F, -5.0F, 10.0F, 1.0F, 10.0F, 0.0F, false);
		other.texOffs(73, 78).addBox(-3.0F, -44.0F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);
		other.texOffs(54, 56).addBox(-5.0F, -43.0F, -5.0F, 10.0F, 1.0F, 10.0F, 0.0F, false);
		other.texOffs(0, 28).addBox(-8.0F, -42.0F, -8.0F, 16.0F, 20.0F, 16.0F, 0.0F, false);
		other.texOffs(98, 0).addBox(-7.0F, -41.0F, -9.0F, 14.0F, 18.0F, 1.0F, 0.0F, false);
		other.texOffs(106, 19).addBox(-5.0F, -39.0F, 9.0F, 10.0F, 14.0F, 1.0F, 0.0F, false);
		other.texOffs(68, 0).addBox(-7.0F, -41.0F, 8.0F, 14.0F, 18.0F, 1.0F, 0.0F, false);
		other.texOffs(84, 19).addBox(-5.0F, -39.0F, -10.0F, 10.0F, 14.0F, 1.0F, 0.0F, false);
		other.texOffs(98, 43).addBox(8.0F, -41.0F, -7.0F, 1.0F, 18.0F, 14.0F, 0.0F, false);
		other.texOffs(46, 0).addBox(-10.0F, -39.0F, -5.0F, 1.0F, 14.0F, 10.0F, 0.0F, false);
		other.texOffs(64, 24).addBox(-9.0F, -41.0F, -7.0F, 1.0F, 18.0F, 14.0F, 0.0F, false);
		other.texOffs(24, 0).addBox(9.0F, -39.0F, -5.0F, 1.0F, 14.0F, 10.0F, 0.0F, false);
		other.texOffs(0, 38).addBox(-2.0F, -15.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);

		tile = new ModelRenderer(this);
		tile.setPos(0.0F, -0.5F, 0.0F);
		balloon.addChild(tile);
		setRotationAngle(tile, -0.3927F, 0.0F, 0.0F);
		tile.texOffs(34, 24).addBox(-8.0F, 1.0F, 0.0F, 16.0F, 1.0F, 1.0F, 0.0F, false);
		tile.texOffs(0, 0).addBox(-8.0F, 10.0F, 0.0F, 16.0F, 1.0F, 1.0F, 0.0F, false);
		tile.texOffs(1, 25).addBox(8.0F, 1.0F, 0.0F, 1.0F, 10.0F, 1.0F, 0.0F, false);
		tile.texOffs(1, 10).addBox(-9.0F, 1.0F, 0.0F, 1.0F, 10.0F, 1.0F, 0.0F, false);

		zombie = new ModelRenderer(this);
		zombie.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(zombie);
		setRotationAngle(zombie, 1.0472F, 0.0F, 0.0F);
		

		left_leg = new ModelRenderer(this);
		left_leg.setPos(4.0F, 7.0F, 0.0F);
		zombie.addChild(left_leg);
		setRotationAngle(left_leg, -0.7854F, 0.0F, 0.0F);
		left_leg.texOffs(0, 97).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 25.0F, 6.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-4.0F, 7.0F, 0.0F);
		zombie.addChild(right_leg);
		setRotationAngle(right_leg, -0.7854F, 0.0F, 0.0F);
		right_leg.texOffs(24, 97).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 25.0F, 6.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setPos(0.0F, 6.0F, 0.0F);
		zombie.addChild(up);
		setRotationAngle(up, 0.2618F, 0.0F, 0.0F);
		up.texOffs(0, 65).addBox(-8.0F, -24.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(9.0F, -21.0F, 0.0F);
		up.addChild(left_hand);
		setRotationAngle(left_hand, -1.7453F, 0.0F, 0.0F);
		left_hand.texOffs(48, 98).addBox(-1.0F, -3.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-8.0F, -21.0F, -1.0F);
		up.addChild(right_hand);
		setRotationAngle(right_hand, -1.7453F, 0.0F, 0.0F);
		right_hand.texOffs(49, 68).addBox(-6.0F, -4.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -24.0F, 0.0F);
		up.addChild(head);
		setRotationAngle(head, -1.0472F, 0.0F, 0.0F);
		head.texOffs(72, 100).addBox(-7.0F, -14.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
		head.texOffs(87, 93).addBox(-3.0F, -15.0F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);
		head.texOffs(18, 99).addBox(-1.0F, -17.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, -17.0F, 0.0F);
		head.addChild(bone);
		bone.texOffs(115, 112).addBox(-5.0F, -1.0F, 0.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		bone.texOffs(116, 106).addBox(-1.0F, -1.0F, -5.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
		bone.texOffs(115, 104).addBox(0.0F, -1.0F, -1.0F, 5.0F, 1.0F, 1.0F, 0.0F, false);
		bone.texOffs(114, 98).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 5.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(BalloonZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if(entity.hasBalloon()) {
			this.zombie.xRot = 1.0472F;
			this.left_leg.xRot = - 0.7854F;
			this.right_leg.xRot = - 0.7854F;
			this.up.xRot = 0.2618F;
			this.left_hand.xRot = -1.7453F;
			this.right_hand.xRot = -1.7453F;
			this.head.xRot = -1.0472F;
			this.head.yRot = 0;
			this.bone.yRot = ageInTicks;
		} else {
			this.up.xRot = 0;
			this.zombie.xRot = 0;
			this.bone.yRot = ageInTicks / 4;
			this.head.yRot = netHeadYaw / (180F / (float)Math.PI);
	        this.head.xRot = headPitch / (180F / (float)Math.PI);
	        this.left_leg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	        this.right_leg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	        this.right_hand.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	        this.left_hand.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		}
		this.balloon.visible = entity.hasBalloon();
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		total.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
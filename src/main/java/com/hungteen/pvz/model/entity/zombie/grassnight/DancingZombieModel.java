package com.hungteen.pvz.model.entity.zombie.grassnight;

import com.hungteen.pvz.entity.zombie.grassnight.DancingZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class DancingZombieModel extends EntityModel<DancingZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer body;

	public DancingZombieModel() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(5.0F, -30.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.setTextureOffset(218, 239).addBox(-4.0F, 24.0F, -6.0F, 8.0F, 6.0F, 11.0F, 0.0F, false);
		right_leg.setTextureOffset(0, 226).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(-5.0F, -30.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.setTextureOffset(36, 239).addBox(-4.0F, 24.0F, -6.0F, 8.0F, 6.0F, 11.0F, 0.0F, false);
		left_leg.setTextureOffset(96, 226).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, -30.0F, 0.0F);
		total.addChild(up);
		

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(11.0F, -21.0F, 1.0F);
		up.addChild(left_hand);
		left_hand.setTextureOffset(60, 188).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-11.0F, -22.0F, 0.0F);
		up.addChild(right_hand);
		right_hand.setTextureOffset(6, 179).addBox(-3.0F, -2.0F, -2.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -24.0F, 0.0F);
		up.addChild(head);
		head.setTextureOffset(0, 0).addBox(-6.0F, -12.0F, -5.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
		head.setTextureOffset(200, 204).addBox(-7.0F, -18.0F, -5.0F, 14.0F, 6.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(232, 177).addBox(-8.0F, -12.0F, -1.0F, 2.0F, 5.0F, 10.0F, 0.0F, false);
		head.setTextureOffset(132, 204).addBox(6.0F, -12.0F, -1.0F, 2.0F, 5.0F, 10.0F, 0.0F, false);
		head.setTextureOffset(162, 212).addBox(-6.0F, -12.0F, 7.0F, 12.0F, 5.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(70, 228).addBox(-7.0F, -9.0F, -7.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		head.setTextureOffset(31, 228).addBox(6.0F, -9.0F, -7.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		head.setTextureOffset(110, 216).addBox(-1.0F, -9.0F, -7.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(198, 197).addBox(-6.0F, -11.0F, -7.0F, 5.0F, 5.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(205, 176).addBox(1.0F, -11.0F, -7.0F, 5.0F, 5.0F, 1.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.setTextureOffset(141, 224).addBox(-8.0F, -24.0F, -3.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(DancingZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if(entity.getSummonTime() > 0) {
			this.total.rotateAngleY = 0;
			this.left_hand.rotateAngleX = 0;
			this.right_hand.rotateAngleX = - 2.5f;
			this.left_leg.rotateAngleX = 0;
			this.right_leg.rotateAngleX = 0;
			return ;
		}
		if(entity.getAttackTime() > 0) {
			int tick = entity.getAttackTime();
			int max = DancingZombieEntity.DANCE_CD;
			this.total.rotateAngleY = - MathHelper.sin(3.14159f * 2 * tick / max);
			this.right_hand.rotateAngleX = - 3 * MathHelper.abs(MathHelper.sin(3.14159f * 4 * tick / max));
			this.left_hand.rotateAngleX = - 3 * MathHelper.abs(MathHelper.sin(3.14159f * 4 * tick / max));
			this.left_leg.rotateAngleX = 0;
			this.right_leg.rotateAngleX = 0;
			return ;
		}
		this.total.rotateAngleY = 0;
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
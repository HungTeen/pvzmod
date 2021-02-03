package com.hungteen.pvz.model.entity.zombie.roof;

import com.hungteen.pvz.entity.zombie.roof.LadderZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class LadderZombieModel extends EntityModel<LadderZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer hammer;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer sting;
	private final ModelRenderer cube_r1;
	private final ModelRenderer beard;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer ladder;
	private final ModelRenderer floor;

	public LadderZombieModel() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, -23.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.setTextureOffset(44, 0).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, -23.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.setTextureOffset(0, 0).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -23.0F, 0.0F);
		up.addChild(body);
		body.setTextureOffset(0, 41).addBox(-8.0F, -25.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		hammer = new ModelRenderer(this);
		hammer.setRotationPoint(9.0F, -1.0F, 0.5F);
		body.addChild(hammer);
		setRotationAngle(hammer, 0.4363F, 0.0F, 0.0F);
		hammer.setTextureOffset(0, 163).addBox(-1.0F, -6.0F, -0.5F, 1.0F, 9.0F, 1.0F, 0.0F, false);
		hammer.setTextureOffset(0, 175).addBox(-1.0F, 3.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.2F, false);
		hammer.setTextureOffset(0, 179).addBox(-1.0F, -5.75F, -3.5F, 1.0F, 1.0F, 1.0F, 0.4F, false);
		hammer.setTextureOffset(6, 163).addBox(-1.0F, -5.75F, -3.5F, 1.0F, 1.0F, 5.0F, 0.1F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(8.0F, -48.0F, 0.0F);
		up.addChild(left_hand);
		setRotationAngle(left_hand, -0.8727F, 0.0F, 0.0F);
		left_hand.setTextureOffset(96, 60).addBox(0.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-8.0F, -48.0F, 0.0F);
		up.addChild(right_hand);
		setRotationAngle(right_hand, -0.8727F, 0.0F, 0.0F);
		right_hand.setTextureOffset(96, 0).addBox(-8.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -48.0F, 0.0F);
		up.addChild(head);
		head.setTextureOffset(16, 96).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		sting = new ModelRenderer(this);
		sting.setRotationPoint(9.0F, -17.0F, -1.0F);
		head.addChild(sting);
		setRotationAngle(sting, -0.6109F, 0.0F, 0.0F);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		sting.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, -0.2182F, 0.0F);
		cube_r1.setTextureOffset(0, 146).addBox(-0.8227F, -1.5736F, 0.7997F, 2.0F, 2.0F, 13.0F, -0.75F, false);

		beard = new ModelRenderer(this);
		beard.setRotationPoint(0.0F, 0.5F, 0.0F);
		head.addChild(beard);
		

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(-0.75F, -6.0F, -8.5F);
		beard.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, 0.2182F);
		cube_r2.setTextureOffset(0, 140).addBox(0.0F, -1.0F, 0.0F, 7.0F, 3.0F, 1.0F, -0.4F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setRotationPoint(0.25F, -6.0F, -8.5F);
		beard.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, -0.2182F);
		cube_r3.setTextureOffset(0, 133).addBox(-7.0F, -1.0F, 0.0F, 7.0F, 3.0F, 1.0F, -0.4F, false);

		ladder = new ModelRenderer(this);
		ladder.setRotationPoint(0.0F, -38.0F, -20.0F);
		up.addChild(ladder);
		setRotationAngle(ladder, 0.2182F, 0.0F, 0.0F);
		ladder.setTextureOffset(15, 202).addBox(-12.0F, -22.0F, -2.0F, 3.0F, 50.0F, 4.0F, 0.1F, false);
		ladder.setTextureOffset(0, 202).addBox(9.0F, -22.0F, -2.0F, 3.0F, 50.0F, 4.0F, 0.1F, false);
		ladder.setTextureOffset(0, 194).addBox(8.0F, 28.0F, -3.0F, 5.0F, 1.0F, 6.0F, 0.0F, false);
		ladder.setTextureOffset(0, 185).addBox(-13.0F, 28.0F, -3.0F, 5.0F, 1.0F, 6.0F, 0.0F, false);

		floor = new ModelRenderer(this);
		floor.setRotationPoint(0.0F, 0.0F, 0.0F);
		ladder.addChild(floor);
		floor.setTextureOffset(31, 250).addBox(-9.0F, 20.0F, -2.0F, 18.0F, 2.0F, 4.0F, 0.0F, false);
		floor.setTextureOffset(31, 242).addBox(-9.0F, 12.0F, -2.0F, 18.0F, 2.0F, 4.0F, 0.0F, false);
		floor.setTextureOffset(30, 234).addBox(-9.0F, 4.0F, -2.0F, 18.0F, 2.0F, 4.0F, 0.0F, false);
		floor.setTextureOffset(32, 225).addBox(-9.0F, -4.0F, -2.0F, 18.0F, 2.0F, 4.0F, 0.0F, false);
		floor.setTextureOffset(30, 217).addBox(-9.0F, -12.0F, -2.0F, 18.0F, 2.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(LadderZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        if(entity.canPartsBeRemoved()) {
	        this.right_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	        this.left_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		} else {
			this.right_hand.rotateAngleX = -0.8727F;
			this.left_hand.rotateAngleX = -0.8727F;
		}
        this.ladder.showModel = ! entity.canPartsBeRemoved();
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
package com.hungteen.pvz.model.entity.zombie.plantzombie;

import com.hungteen.pvz.entity.zombie.plantzombie.WallNutZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class WallNutZombieModel extends EntityModel<WallNutZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer face;
	private final ModelRenderer stage1;
	private final ModelRenderer stage2;
	private final ModelRenderer stage3;

	public WallNutZombieModel() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, -24.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.setTextureOffset(44, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, -24.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.setTextureOffset(0, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, -24.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.setTextureOffset(0, 41).addBox(-8.0F, -24.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(12.0F, -20.0F, 0.0F);
		up.addChild(left_hand);
		left_hand.setTextureOffset(96, 60).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-12.0F, -20.0F, 0.0F);
		up.addChild(right_hand);
		right_hand.setTextureOffset(96, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -24.0F, 0.0F);
		up.addChild(head);
		head.setTextureOffset(92, 134).addBox(-8.0F, -1.0F, -8.0F, 16.0F, 1.0F, 16.0F, 0.0F, false);
		head.setTextureOffset(0, 135).addBox(-8.0F, -26.0F, -8.0F, 16.0F, 2.0F, 16.0F, 0.0F, false);
		head.setTextureOffset(0, 180).addBox(-10.0F, -25.0F, -10.0F, 20.0F, 24.0F, 20.0F, 0.0F, false);
		head.setTextureOffset(86, 221).addBox(-11.0F, -23.0F, -8.0F, 1.0F, 20.0F, 16.0F, 0.0F, false);
		head.setTextureOffset(88, 178).addBox(10.0F, -23.0F, -8.0F, 1.0F, 20.0F, 16.0F, 0.0F, false);

		face = new ModelRenderer(this);
		face.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(face);
		face.setTextureOffset(0, 248).addBox(-1.0F, -11.0F, -10.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		stage1 = new ModelRenderer(this);
		stage1.setRotationPoint(0.0F, -10.0F, -10.25F);
		face.addChild(stage1);
		stage1.setTextureOffset(1, 252).addBox(-2.0F, -2.0F, 0.15F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		stage1.setTextureOffset(0, 243).addBox(1.0F, -2.0F, 0.15F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		stage2 = new ModelRenderer(this);
		stage2.setRotationPoint(0.0F, -10.0F, -10.25F);
		face.addChild(stage2);
		stage2.setTextureOffset(1, 252).addBox(-2.0F, -1.0F, 0.15F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		stage2.setTextureOffset(0, 243).addBox(1.0F, -1.0F, 0.15F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		stage3 = new ModelRenderer(this);
		stage3.setRotationPoint(0.0F, -10.0F, -10.25F);
		face.addChild(stage3);
		stage3.setTextureOffset(1, 252).addBox(-2.0F, 0.0F, 0.15F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		stage3.setTextureOffset(0, 243).addBox(1.0F, 0.0F, 0.15F, 1.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(WallNutZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.right_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.stage1.showModel = (entity.getHealth() / entity.getMaxHealth() > 2F / 3); 
        this.stage2.showModel = (entity.getHealth() / entity.getMaxHealth() > 1F / 3); 
        this.stage3.showModel = (entity.getHealth() / entity.getMaxHealth() <= 1F / 3); 
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
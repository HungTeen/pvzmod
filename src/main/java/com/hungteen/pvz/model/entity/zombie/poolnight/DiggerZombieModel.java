package com.hungteen.pvz.model.entity.zombie.poolnight;

import com.hungteen.pvz.entity.zombie.poolnight.DiggerZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class DiggerZombieModel extends EntityModel<DiggerZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer pickaxe;
	private final ModelRenderer bone;
	private final ModelRenderer head;
	private final ModelRenderer hat;

	public DiggerZombieModel() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, -24.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.setTextureOffset(221, 221).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, -24.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.setTextureOffset(182, 220).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, -24.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -7.0F, 0.0F);
		up.addChild(body);
		body.setTextureOffset(122, 217).addBox(-8.0F, -17.0F, -5.0F, 16.0F, 24.0F, 11.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(12.0F, -20.0F, 0.0F);
		up.addChild(left_hand);
		left_hand.setTextureOffset(83, 220).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-12.0F, -20.0F, 0.0F);
		up.addChild(right_hand);
		right_hand.setTextureOffset(43, 219).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		pickaxe = new ModelRenderer(this);
		pickaxe.setRotationPoint(0.0F, 18.5858F, -6.2426F);
		right_hand.addChild(pickaxe);
		setRotationAngle(pickaxe, -0.1745F, 0.0F, 0.0F);
		pickaxe.setTextureOffset(8, 231).addBox(-1.0F, -11.0434F, -18.7538F, 2.0F, 16.0F, 2.0F, 0.1F, false);
		pickaxe.setTextureOffset(193, 177).addBox(-1.0F, -1.0F, -19.0F, 2.0F, 2.0F, 28.0F, -0.1F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 6.0F, -18.0F);
		pickaxe.addChild(bone);
		setRotationAngle(bone, 0.5236F, 0.0F, 0.0F);
		bone.setTextureOffset(27, 243).addBox(-1.0F, -1.1439F, -0.1677F, 2.0F, 7.0F, 2.0F, 0.1F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -24.0F, 0.0F);
		up.addChild(head);
		head.setTextureOffset(118, 177).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		hat = new ModelRenderer(this);
		hat.setRotationPoint(0.0F, -16.0F, 0.0F);
		head.addChild(hat);
		hat.setTextureOffset(44, 192).addBox(-9.0F, -1.0F, -8.0F, 18.0F, 1.0F, 17.0F, 0.0F, false);
		hat.setTextureOffset(52, 173).addBox(-8.0F, -3.0F, -6.0F, 16.0F, 2.0F, 12.0F, 0.0F, false);
		hat.setTextureOffset(57, 159).addBox(-8.0F, -5.0F, -3.0F, 16.0F, 2.0F, 7.0F, 0.0F, false);
		hat.setTextureOffset(120, 168).addBox(-8.0F, -6.0F, 0.0F, 16.0F, 1.0F, 3.0F, 0.0F, false);
		hat.setTextureOffset(170, 161).addBox(-1.0F, -4.0F, -9.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
		hat.setTextureOffset(200, 161).addBox(-2.0F, -5.0F, -11.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
		hat.setTextureOffset(213, 143).addBox(-9.0F, 0.0F, 8.0F, 18.0F, 2.0F, 1.0F, 0.0F, false);
		hat.setTextureOffset(166, 143).addBox(-9.0F, 0.0F, -9.0F, 18.0F, 1.0F, 1.0F, 0.0F, false);
		hat.setTextureOffset(123, 131).addBox(8.0F, 0.0F, -8.0F, 1.0F, 1.0F, 16.0F, 0.0F, false);
		hat.setTextureOffset(79, 132).addBox(-9.0F, 0.0F, -8.0F, 1.0F, 1.0F, 16.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(DiggerZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if(entity.getAnimTime() > 0 && entity.getAnimTime() < DiggerZombieEntity.MAX_ANIM_TIME) {
			total.rotateAngleY = ageInTicks / 1f;
		} else {
			total.rotateAngleY = 0;
		}
		if(entity.hasPickaxe()) {
	        this.right_hand.rotateAngleX = - 1.57F + MathHelper.sin(ageInTicks) * 0.5F;
		} else {
			this.right_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		}
		this.pickaxe.showModel = entity.hasPickaxe();
		this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
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
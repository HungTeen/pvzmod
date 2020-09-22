package com.hungteen.pvz.model.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.poolday.LavaZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class LavaZombieModel extends EntityModel<LavaZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer head;
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

	public LavaZombieModel() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 22.0F, 0.0F);
		

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -48.0F, 0.0F);
		total.addChild(head);
		head.setTextureOffset(200, 228).addBox(-7.0F, -14.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(163, 236).addBox(7.0F, -9.0F, -8.0F, 1.0F, 4.0F, 15.0F, 0.0F, false);
		head.setTextureOffset(224, 206).addBox(-8.0F, -9.0F, -8.0F, 1.0F, 4.0F, 15.0F, 0.0F, false);
		head.setTextureOffset(180, 218).addBox(-7.0F, -9.0F, 7.0F, 14.0F, 4.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(226, 193).addBox(-7.0F, -10.0F, -8.0F, 14.0F, 6.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(191, 204).addBox(-6.0F, -4.0F, -8.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(230, 184).addBox(-6.0F, -11.0F, -8.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(238, 176).addBox(-4.0F, -3.0F, -8.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(238, 167).addBox(-4.0F, -12.0F, -8.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-8.0F, -48.0F, 0.0F);
		total.addChild(right_hand);
		right_hand.setTextureOffset(232, 0).addBox(-6.0F, 0.0F, -3.0F, 6.0F, 25.0F, 6.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-2.0F, 0.0F, 0.0F);
		right_hand.addChild(bone3);
		bone3.setTextureOffset(238, 54).addBox(-5.0F, 3.0F, -4.0F, 1.0F, 6.0F, 8.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(-4.0F, 5.0F, -2.0F);
		bone3.addChild(bone4);
		setRotationAngle(bone4, 0.0F, -1.1345F, 0.0F);
		bone4.setTextureOffset(242, 36).addBox(-1.9063F, -2.0F, -6.4226F, 1.0F, 6.0F, 6.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(-6.0F, 5.0F, 3.0F);
		right_hand.addChild(bone5);
		setRotationAngle(bone5, 0.0F, 1.1345F, 0.0F);
		bone5.setTextureOffset(242, 74).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 6.0F, 6.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(9.0F, -48.0F, 0.0F);
		total.addChild(left_hand);
		left_hand.setTextureOffset(202, 0).addBox(-1.0F, 0.0F, -3.0F, 6.0F, 25.0F, 6.0F, 0.0F, false);

		armor = new ModelRenderer(this);
		armor.setRotationPoint(5.0F, 5.0F, 0.0F);
		left_hand.addChild(armor);
		armor.setTextureOffset(208, 36).addBox(0.0F, -2.0F, -4.0F, 1.0F, 6.0F, 8.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 3.0F);
		armor.addChild(bone);
		setRotationAngle(bone, 0.0F, -1.1345F, 0.0F);
		bone.setTextureOffset(216, 56).addBox(0.0403F, -2.0F, -0.0774F, 1.0F, 6.0F, 6.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 1.0F, -2.0F);
		armor.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 1.1345F, 0.0F);
		bone2.setTextureOffset(219, 76).addBox(0.9063F, -3.0F, -6.4226F, 1.0F, 6.0F, 6.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -31.0F, 0.0F);
		total.addChild(body);
		body.setTextureOffset(0, 223).addBox(-8.0F, -17.0F, -4.0F, 16.0F, 25.0F, 8.0F, 0.0F, false);
		body.setTextureOffset(54, 226).addBox(-8.0F, -14.0F, 4.0F, 7.0F, 21.0F, 7.0F, 0.0F, false);
		body.setTextureOffset(90, 224).addBox(1.0F, -14.0F, 4.0F, 7.0F, 21.0F, 7.0F, 0.0F, false);
		body.setTextureOffset(125, 251).addBox(-5.0F, -15.0F, 7.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(127, 242).addBox(-5.0F, 7.0F, 7.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(147, 233).addBox(-5.0F, -5.0F, 11.0F, 10.0F, 2.0F, 1.0F, 0.0F, false);
		body.setTextureOffset(5, 193).addBox(-1.0F, -13.0F, 4.0F, 2.0F, 19.0F, 5.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, -23.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.setTextureOffset(29, 186).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 23.0F, 6.0F, 0.0F, false);

		right_foot = new ModelRenderer(this);
		right_foot.setRotationPoint(0.0F, 24.0F, 0.0F);
		right_leg.addChild(right_foot);
		right_foot.setTextureOffset(65, 202).addBox(-3.0F, -1.0F, -12.0F, 6.0F, 2.0F, 17.0F, 0.0F, false);
		right_foot.setTextureOffset(124, 218).addBox(3.0F, -1.0F, -11.0F, 1.0F, 2.0F, 7.0F, 0.0F, false);
		right_foot.setTextureOffset(146, 214).addBox(-4.0F, -1.0F, -11.0F, 1.0F, 2.0F, 7.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, -23.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.setTextureOffset(6, 154).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 23.0F, 6.0F, 0.0F, false);

		left_foot = new ModelRenderer(this);
		left_foot.setRotationPoint(0.0F, 24.0F, 0.0F);
		left_leg.addChild(left_foot);
		left_foot.setTextureOffset(77, 170).addBox(-3.0F, -1.0F, -12.0F, 6.0F, 2.0F, 17.0F, 0.0F, false);
		left_foot.setTextureOffset(55, 171).addBox(3.0F, -1.0F, -11.0F, 1.0F, 2.0F, 7.0F, 0.0F, false);
		left_foot.setTextureOffset(134, 196).addBox(-4.0F, -1.0F, -11.0F, 1.0F, 2.0F, 7.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(LavaZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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
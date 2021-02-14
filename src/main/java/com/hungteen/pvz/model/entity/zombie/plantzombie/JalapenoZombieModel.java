package com.hungteen.pvz.model.entity.zombie.plantzombie;

import com.hungteen.pvz.entity.zombie.plantzombie.JalapenoZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class JalapenoZombieModel extends EntityModel<JalapenoZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer bone13;
	private final ModelRenderer bone14;

	public JalapenoZombieModel() {
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
		head.setRotationPoint(0.0F, -24.0F, -2.0F);
		up.addChild(head);
		head.setTextureOffset(191, 221).addBox(-8.0F, -21.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);
		head.setTextureOffset(194, 196).addBox(-7.0F, -5.0F, -7.0F, 14.0F, 2.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(202, 168).addBox(-6.0F, -3.0F, -6.0F, 12.0F, 3.0F, 12.0F, 0.0F, false);
		head.setTextureOffset(210, 141).addBox(-5.0F, 0.0F, -5.0F, 10.0F, 4.0F, 10.0F, 0.0F, false);
		head.setTextureOffset(212, 114).addBox(-4.0F, 4.0F, -5.0F, 8.0F, 5.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(225, 95).addBox(-3.0F, 9.0F, -6.0F, 5.0F, 4.0F, 7.0F, 0.0F, false);
		head.setTextureOffset(224, 70).addBox(-3.0F, 13.0F, -8.0F, 6.0F, 3.0F, 7.0F, 0.0F, false);
		head.setTextureOffset(227, 54).addBox(-3.0F, 16.0F, -12.0F, 6.0F, 3.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(72, 237).addBox(-7.0F, -22.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(10, 235).addBox(-6.0F, -23.0F, -6.0F, 12.0F, 1.0F, 12.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, 16.0F, -12.0F);
		head.addChild(bone8);
		setRotationAngle(bone8, 1.0472F, 0.0F, 0.0F);
		bone8.setTextureOffset(226, 32).addBox(-2.0F, -3.0F, -1.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, -3.0F, 0.0F);
		bone8.addChild(bone9);
		setRotationAngle(bone9, 0.6981F, 0.0F, 0.0F);
		bone9.setTextureOffset(164, 224).addBox(-2.0F, -0.6428F, -0.766F, 4.0F, 1.0F, 4.0F, 0.0F, false);

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(0.0F, -0.0152F, 2.8264F);
		bone9.addChild(bone10);
		setRotationAngle(bone10, 0.9599F, 0.0F, 0.0F);
		bone10.setTextureOffset(150, 236).addBox(-1.0F, -0.0261F, -0.2521F, 2.0F, 4.0F, 1.0F, 0.0F, false);

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(0.0F, -23.0F, 0.0F);
		head.addChild(bone11);
		setRotationAngle(bone11, 0.0F, 0.0F, 0.2618F);
		bone11.setTextureOffset(127, 207).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(1.2247F, -5.2929F, 0.0F);
		bone11.addChild(bone12);
		setRotationAngle(bone12, 0.0F, 0.0F, -0.4363F);
		bone12.setTextureOffset(87, 213).addBox(-2.0F, -2.0F, -1.0F, 6.0F, 2.0F, 2.0F, 0.0F, false);

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(4.6375F, 2.1433F, 0.0F);
		bone12.addChild(bone13);
		setRotationAngle(bone13, 0.0F, 0.0F, -0.6109F);
		bone13.setTextureOffset(58, 204).addBox(0.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(0.7071F, -0.1213F, 0.0F);
		bone13.addChild(bone14);
		setRotationAngle(bone14, 0.0F, 0.0F, -1.9199F);
		bone14.setTextureOffset(101, 183).addBox(-1.0F, -3.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(JalapenoZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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
package com.hungteen.pvzmod.model.entities.zombies;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelGigaFootballZombie extends ModelBase {
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer left_arm;
	private final ModelRenderer bone;
	private final ModelRenderer hand;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer right_arm;
	private final ModelRenderer bone2;
	private final ModelRenderer hand2;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;

	public ModelGigaFootballZombie() {
		textureWidth = 256;
		textureHeight = 256;

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(5.0F, -7.0F, 0.0F);
		left_leg.cubeList.add(new ModelBox(left_leg, 236, 237, -2.0F, 16.0F, -2.0F, 4, 12, 4, 0.0F, false));
		left_leg.cubeList.add(new ModelBox(left_leg, 202, 241, -3.0F, 28.0F, -6.0F, 6, 3, 9, 0.0F, false));
		left_leg.cubeList.add(new ModelBox(left_leg, 237, 229, -3.0F, 27.0F, -6.0F, 6, 1, 1, 0.0F, false));
		left_leg.cubeList.add(new ModelBox(left_leg, 2, 232, -3.0F, -2.0F, -3.0F, 6, 18, 6, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-6.0F, -8.0F, 0.0F);
		right_leg.cubeList.add(new ModelBox(right_leg, 32, 236, -1.0F, 17.0F, -2.0F, 4, 12, 4, 0.0F, false));
		right_leg.cubeList.add(new ModelBox(right_leg, 54, 242, -2.0F, 29.0F, -6.0F, 6, 3, 9, 0.0F, false));
		right_leg.cubeList.add(new ModelBox(right_leg, 88, 251, -2.0F, 28.0F, -6.0F, 6, 1, 1, 0.0F, false));
		right_leg.cubeList.add(new ModelBox(right_leg, 108, 232, -2.0F, -1.0F, -3.0F, 6, 18, 6, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 197, 4, -9.0F, -63.0F, -5.0F, 18, 32, 10, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -39.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 189, 49, -8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 178, 86, -9.0F, -18.0F, -10.0F, 18, 2, 19, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 194, 111, -7.0F, -19.0F, -7.0F, 14, 1, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 214, 132, 9.0F, -16.0F, -9.0F, 1, 16, 18, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 215, 172, -9.0F, -16.0F, 9.0F, 18, 16, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 208, 195, -9.0F, 0.0F, 5.0F, 18, 1, 4, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 247, 207, -9.0F, -4.0F, -10.0F, 1, 4, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 246, 217, 8.0F, -4.0F, -10.0F, 1, 4, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 152, 220, -10.0F, -16.0F, -9.0F, 1, 16, 18, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 197, 231, -8.0F, -1.0F, -10.0F, 16, 1, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 192, 219, -8.0F, -4.0F, -10.0F, 16, 1, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 182, 212, -7.0F, -16.0F, -10.0F, 1, 15, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 140, 225, 6.0F, -16.0F, -10.0F, 1, 15, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 86, 239, 2.0F, -3.0F, -10.0F, 1, 2, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 56, 240, -3.0F, -3.0F, -10.0F, 1, 2, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 57, 233, -3.0F, -16.0F, -10.0F, 1, 1, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 30, 227, 2.0F, -16.0F, -10.0F, 1, 1, 1, 0.0F, false));

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(9.0F, -39.0F, 0.0F);
		left_arm.cubeList.add(new ModelBox(left_arm, 3, 214, 0.0F, 0.0F, -5.0F, 21, 1, 10, 0.0F, false));
		left_arm.cubeList.add(new ModelBox(left_arm, 60, 216, 0.0F, 1.0F, -6.0F, 16, 6, 1, 0.0F, false));
		left_arm.cubeList.add(new ModelBox(left_arm, 106, 213, 0.0F, 1.0F, 5.0F, 16, 6, 1, 0.0F, false));
		left_arm.cubeList.add(new ModelBox(left_arm, 144, 201, 0.0F, 1.0F, -5.0F, 1, 6, 10, 0.0F, false));
		left_arm.cubeList.add(new ModelBox(left_arm, 4, 193, 0.0F, 7.0F, -5.0F, 15, 3, 10, 0.0F, false));
		left_arm.cubeList.add(new ModelBox(left_arm, 100, 199, 16.0F, 1.0F, -6.0F, 2, 4, 1, 0.0F, false));
		left_arm.cubeList.add(new ModelBox(left_arm, 115, 203, 18.0F, 1.0F, -6.0F, 2, 2, 1, 0.0F, false));
		left_arm.cubeList.add(new ModelBox(left_arm, 128, 200, 16.0F, 1.0F, 5.0F, 2, 4, 1, 0.0F, false));
		left_arm.cubeList.add(new ModelBox(left_arm, 171, 204, 18.0F, 1.0F, 5.0F, 2, 2, 1, 0.0F, false));
		left_arm.cubeList.add(new ModelBox(left_arm, 126, 174, 0.0F, 10.0F, -5.0F, 10, 6, 10, 0.0F, false));
		left_arm.cubeList.add(new ModelBox(left_arm, 70, 76, 5.0F, -2.0F, -1.0F, 2, 2, 2, 0.0F, false));
		left_arm.cubeList.add(new ModelBox(left_arm, 120, 64, 10.0F, -2.0F, -1.0F, 2, 2, 2, 0.0F, false));
		left_arm.cubeList.add(new ModelBox(left_arm, 99, 52, 15.0F, -2.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(14.0F, 9.0F, 0.0F);
		setRotationAngle(bone, 0.0F, 0.0F, -0.8727F);
		left_arm.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 60, 197, 2.0F, -1.0F, -5.0F, 9, 1, 10, 0.0F, false));

		hand = new ModelRenderer(this);
		hand.setRotationPoint(7.0F, 16.0F, -1.0F);
		left_arm.addChild(hand);
		hand.cubeList.add(new ModelBox(hand, 4, 160, -4.0F, -5.0F, -20.0F, 4, 4, 24, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-4.0F, -3.0F, -20.0F);
		setRotationAngle(bone3, 0.0F, 0.0F, -1.1345F);
		hand.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 66, 183, -1.0F, -2.0F, 0.0F, 1, 3, 1, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(-3.0F, -5.0F, -20.0F);
		setRotationAngle(bone4, 0.0F, 0.0F, -0.4363F);
		hand.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 76, 184, -1.0F, -3.0F, 0.0F, 1, 4, 1, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(-2.0F, -5.0F, -20.0F);
		hand.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 88, 184, -1.0F, -4.0F, 0.0F, 1, 5, 1, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(-1.0F, -5.0F, -20.0F);
		setRotationAngle(bone6, 0.0F, 0.0F, 0.4363F);
		hand.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 101, 185, -1.0F, -3.0F, 0.0F, 1, 4, 1, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(-1.0F, -4.0F, -20.0F);
		setRotationAngle(bone7, 0.0F, 0.0F, 0.5236F);
		hand.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 114, 184, 0.0F, -3.0F, 0.0F, 1, 3, 1, 0.0F, false));

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(-9.0F, -39.0F, 0.0F);
		setRotationAngle(right_arm, 0.0F, 3.1416F, 0.0F);
		right_arm.cubeList.add(new ModelBox(right_arm, 4, 143, 0.0F, 0.0F, -5.0F, 21, 1, 10, 0.0F, false));
		right_arm.cubeList.add(new ModelBox(right_arm, 53, 160, 0.0F, 1.0F, -6.0F, 16, 6, 1, 0.0F, false));
		right_arm.cubeList.add(new ModelBox(right_arm, 62, 146, 0.0F, 1.0F, 5.0F, 16, 6, 1, 0.0F, false));
		right_arm.cubeList.add(new ModelBox(right_arm, 100, 157, 0.0F, 1.0F, -5.0F, 1, 6, 10, 0.0F, false));
		right_arm.cubeList.add(new ModelBox(right_arm, 4, 124, 0.0F, 7.0F, -5.0F, 15, 3, 10, 0.0F, false));
		right_arm.cubeList.add(new ModelBox(right_arm, 107, 142, 16.0F, 1.0F, -6.0F, 2, 4, 1, 0.0F, false));
		right_arm.cubeList.add(new ModelBox(right_arm, 124, 151, 18.0F, 1.0F, -6.0F, 2, 2, 1, 0.0F, false));
		right_arm.cubeList.add(new ModelBox(right_arm, 135, 160, 16.0F, 1.0F, 5.0F, 2, 4, 1, 0.0F, false));
		right_arm.cubeList.add(new ModelBox(right_arm, 172, 172, 18.0F, 1.0F, 5.0F, 2, 2, 1, 0.0F, false));
		right_arm.cubeList.add(new ModelBox(right_arm, 7, 97, 0.0F, 10.0F, -5.0F, 10, 6, 10, 0.0F, false));
		right_arm.cubeList.add(new ModelBox(right_arm, 72, 46, 5.0F, -2.0F, -1.0F, 2, 2, 2, 0.0F, false));
		right_arm.cubeList.add(new ModelBox(right_arm, 49, 58, 10.0F, -2.0F, -1.0F, 2, 2, 2, 0.0F, false));
		right_arm.cubeList.add(new ModelBox(right_arm, 36, 80, 15.0F, -2.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(14.0F, 9.0F, 0.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, -0.8727F);
		right_arm.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 63, 127, 2.0F, -1.0F, -5.0F, 9, 1, 10, 0.0F, false));

		hand2 = new ModelRenderer(this);
		hand2.setRotationPoint(7.0F, 12.0F, 1.0F);
		setRotationAngle(hand2, 0.0F, 3.1416F, 0.0F);
		right_arm.addChild(hand2);
		hand2.cubeList.add(new ModelBox(hand2, 61, 92, 0.0609F, -1.0F, -20.6055F, 4, 4, 24, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(5.0609F, 2.0F, -20.6055F);
		setRotationAngle(bone8, 0.0F, 0.0F, 1.1345F);
		hand2.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 131, 129, -1.4226F, -1.0937F, 0.0F, 1, 3, 1, 0.0F, false));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(1.0609F, 0.0F, -20.6055F);
		setRotationAngle(bone9, 0.0F, 0.0F, -0.6981F);
		hand2.addChild(bone9);
		bone9.cubeList.add(new ModelBox(bone9, 156, 136, -0.9128F, -2.9962F, 0.0F, 1, 3, 1, 0.0F, false));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(2.0609F, 0.0F, -20.6055F);
		setRotationAngle(bone10, 0.0F, 0.0F, -0.3491F);
		hand2.addChild(bone10);
		bone10.cubeList.add(new ModelBox(bone10, 180, 152, -0.9128F, -3.9962F, 0.0F, 1, 4, 1, 0.0F, false));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(4.0609F, -1.0F, -20.6055F);
		hand2.addChild(bone11);
		bone11.cubeList.add(new ModelBox(bone11, 153, 92, -1.9962F, -3.9128F, 0.0F, 1, 5, 1, 0.0F, false));

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(3.0F, 1.0F, -20.6055F);
		setRotationAngle(bone12, 0.0F, 0.0F, 0.5236F);
		hand2.addChild(bone12);
		bone12.cubeList.add(new ModelBox(bone12, 169, 120, -0.8051F, -4.5F, 0.0F, 1, 4, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		left_leg.render(f5);
		right_leg.render(f5);
		body.render(f5);
		head.render(f5);
		left_arm.render(f5);
		right_arm.render(f5);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
        this.head.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.head.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.right_leg.rotateAngleX = 2*MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
        this.left_leg.rotateAngleX = 2*MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
        this.right_arm.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_/3;
        this.left_arm.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_/3;
       
    }
}
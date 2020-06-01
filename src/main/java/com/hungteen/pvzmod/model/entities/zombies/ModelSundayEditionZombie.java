package com.hungteen.pvzmod.model.entities.zombies;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelSundayEditionZombie extends ModelBase {
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer bone2;
	private final ModelRenderer right_hand;
	private final ModelRenderer bone;
	private final ModelRenderer head;
	private final ModelRenderer bone5;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;

	public ModelSundayEditionZombie() {
		textureWidth = 256;
		textureHeight = 256;

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, 1.0F, 0.0F);
		left_leg.cubeList.add(new ModelBox(left_leg, 230, 225, -3.0F, -1.0F, -3.0F, 6, 22, 6, 0.0F, false));
		left_leg.cubeList.add(new ModelBox(left_leg, 196, 242, -3.0F, 21.0F, -5.0F, 6, 2, 9, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, 1.0F, 0.0F);
		right_leg.cubeList.add(new ModelBox(right_leg, 230, 192, -3.0F, -1.0F, -3.0F, 6, 22, 6, 0.0F, false));
		right_leg.cubeList.add(new ModelBox(right_leg, 191, 220, -3.0F, 21.0F, -5.0F, 6, 2, 9, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -7.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 3, 221, -8.0F, -17.0F, -4.0F, 16, 24, 8, 0.0F, false));

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(9.0F, -21.0F, 3.0F);
		setRotationAngle(left_hand, -1.309F, -0.0873F, 0.0F);
		left_hand.cubeList.add(new ModelBox(left_hand, 59, 221, -1.0F, 0.0F, -3.0F, 6, 24, 6, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(1.0F, 25.0F, 0.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.8727F);
		left_hand.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 160, 75, 0.0F, -4.0F, -28.0F, 1, 22, 45, 0.0F, false));

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-8.0F, -21.0F, 3.0F);
		setRotationAngle(right_hand, -1.309F, 0.0873F, 0.0F);
		right_hand.cubeList.add(new ModelBox(right_hand, 148, 222, -6.0F, 0.0F, -3.0F, 6, 24, 6, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-2.0F, 25.0F, 0.0F);
		setRotationAngle(bone, 0.0F, 0.0F, -0.8727F);
		right_hand.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 160, 4, -1.0F, -4.0F, -28.0F, 1, 22, 45, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -24.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 66, 182, -7.0F, -14.0F, -7.0F, 14, 14, 14, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, -14.0F, 0.0F);
		setRotationAngle(bone5, 0.0F, 0.0F, -0.6981F);
		head.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 130, 207, -1.0F, -1.0F, -1.0F, 10, 1, 1, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, -10.0F, 0.0F);
		setRotationAngle(bone3, 0.3491F, 0.0F, 0.0F);
		head.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 200, 188, -8.0F, -1.0F, -10.0F, 1, 1, 11, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 140, 180, 7.0F, -1.0F, -10.0F, 1, 1, 11, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 183, 187, -7.0F, -1.0F, -10.0F, 2, 1, 1, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 206, 172, 5.0F, -1.0F, -10.0F, 2, 1, 1, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 227, 175, -1.0F, -1.0F, -10.0F, 2, 1, 1, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 241, 169, -5.0F, -2.0F, -10.0F, 4, 3, 1, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 174, 175, 1.0F, -2.0F, -10.0F, 4, 3, 1, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, -14.0F, 0.0F);
		setRotationAngle(bone4, 0.0F, -0.8727F, -0.6981F);
		head.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 0, 0, -1.0F, -1.0F, 0.0F, 9, 1, 1, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(-2.0F, -13.0F, 1.0F);
		setRotationAngle(bone6, 0.0F, 0.0F, 0.5236F);
		head.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 169, 208, -9.0F, -2.0F, -2.0F, 10, 1, 1, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, -14.0F, -2.0F);
		setRotationAngle(bone7, -0.3491F, 1.0472F, -0.6981F);
		head.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 0, 0, -1.0F, -1.0F, 0.0F, 9, 1, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		left_leg.render(f5);
		right_leg.render(f5);
		body.render(f5);
		left_hand.render(f5);
		right_hand.render(f5);
		head.render(f5);
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
        this.right_leg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
        this.left_leg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
       
    }
}
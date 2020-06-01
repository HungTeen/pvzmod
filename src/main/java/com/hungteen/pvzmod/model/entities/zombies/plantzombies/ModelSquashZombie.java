package com.hungteen.pvzmod.model.entities.zombies.plantzombies;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


public class ModelSquashZombie extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer bone;

	public ModelSquashZombie() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, -24.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 44, 0, -4.0F, 0.0F, -4.0F, 8, 24, 8, 0.0F, false));

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, -24.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 0, 0, -4.0F, 0.0F, -4.0F, 8, 24, 8, 0.0F, false));

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, -24.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -7.0F, 0.0F);
		up.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 41, -8.0F, -17.0F, -4.0F, 16, 24, 8, 0.0F, false));

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(12.0F, -20.0F, 0.0F);
		up.addChild(left_hand);
		left_hand.cubeList.add(new ModelBox(left_hand, 96, 60, -4.0F, -4.0F, -4.0F, 8, 24, 8, 0.0F, false));

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-12.0F, -20.0F, 0.0F);
		up.addChild(right_hand);
		right_hand.cubeList.add(new ModelBox(right_hand, 96, 0, -4.0F, -4.0F, -4.0F, 8, 24, 8, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -24.0F, 0.0F);
		up.addChild(head);
		head.cubeList.add(new ModelBox(head, 146, 4, -12.0F, -12.0F, -12.0F, 24, 12, 24, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 140, 211, -10.0F, -22.0F, -10.0F, 20, 10, 20, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 156, 52, -8.0F, -32.0F, -8.0F, 16, 10, 16, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 173, 95, -6.0F, -38.0F, -6.0F, 12, 6, 12, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-1.0F, -39.0F, 0.0F);
		head.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, 0.6109F);
		bone.cubeList.add(new ModelBox(bone, 235, 130, -0.1808F, -5.5736F, -2.0F, 3, 7, 3, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		total.render(f5);
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
        this.right_hand.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
        this.left_hand.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
       
    }
}
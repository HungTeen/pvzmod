package com.hungteen.pvzmod.model.entities.zombies.grassday;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelBucketHeadZombie extends ModelBase {
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer bar;

	public ModelBucketHeadZombie() {
		textureWidth = 256;
		textureHeight = 256;
		this.isChild=false;
		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(-4.0F, 0.0F, 0.0F);
		left_leg.cubeList.add(new ModelBox(left_leg, 0, 0, 4.0F, 0.0F, -4.0F, 8, 24, 8, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(4.0F, 0.0F, 0.0F);
		right_leg.cubeList.add(new ModelBox(right_leg, 44, 0, -12.0F, 0.0F, -4.0F, 8, 24, 8, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -1.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 41, -8.0F, -23.0F, -4.0F, 16, 24, 8, 0.0F, false));

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(-8.0F, -24.0F, 0.0F);
		left_hand.cubeList.add(new ModelBox(left_hand, 96, 60, 16.0F, 0.0F, -4.0F, 8, 24, 8, 0.0F, false));

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(8.0F, -24.0F, 0.0F);
		right_hand.cubeList.add(new ModelBox(right_hand, 96, 0, -24.0F, 0.0F, -4.0F, 8, 24, 8, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -25.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 16, 96, -8.0F, -15.0F, -8.0F, 16, 16, 16, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 208, 244, -9.0F, -15.0F, -9.0F, 18, 2, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 208, 224, -9.0F, -15.0F, 8.0F, 18, 2, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 217, 185, -9.0F, -15.0F, -8.0F, 1, 2, 16, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 214, 156, 8.0F, -15.0F, -8.0F, 1, 2, 16, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 174, 105, -9.0F, -31.0F, -9.0F, 18, 16, 18, 0.0F, false));

		bar = new ModelRenderer(this);
		bar.setRotationPoint(0.0F, -14.0F, 0.0F);
		setRotationAngle(bar, 0.5236F, 0.0F, 0.0F);
		head.addChild(bar);
		bar.cubeList.add(new ModelBox(bar, 10, 174, -10.0F, -1.0F, -15.0F, 20, 1, 1, 0.0F, false));
		bar.cubeList.add(new ModelBox(bar, 28, 203, -10.0F, -1.0F, -14.0F, 1, 1, 14, 0.0F, false));
		bar.cubeList.add(new ModelBox(bar, 116, 131, 9.0F, -1.0F, -14.0F, 1, 1, 14, 0.0F, false));
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
        this.right_hand.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
        this.left_hand.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
       
    }
	
	public ModelRenderer getArm()
	{
		return this.right_hand;
	}
}
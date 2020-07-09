package com.hungteen.pvzmod.model.entities.zombies.grassday;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelConeHeadZombie extends ModelBase {
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer body2;

	public ModelConeHeadZombie() {
		textureWidth = 256;
		textureHeight = 256;

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(3.0F, 1.0F, 0.0F);
		left_leg.cubeList.add(new ModelBox(left_leg, 0, 0, -3.0F, -1.0F, -4.0F, 8, 24, 8, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-5.0F, 1.0F, 0.0F);
		right_leg.cubeList.add(new ModelBox(right_leg, 44, 0, -3.0F, -1.0F, -4.0F, 8, 24, 8, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -11.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 41, -8.0F, -13.0F, -4.0F, 16, 24, 8, 0.0F, false));

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(8.0F, -24.0F, 0.0F);
		left_hand.cubeList.add(new ModelBox(left_hand, 96, 60, 0.0F, 0.0F, -4.0F, 8, 24, 8, 0.0F, false));

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-8.0F, -24.0F, 0.0F);
		right_hand.cubeList.add(new ModelBox(right_hand, 96, 0, -8.0F, 0.0F, -4.0F, 8, 24, 8, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -24.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 16, 96, -8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F, false));

		body2 = new ModelRenderer(this);
		body2.setRotationPoint(0.0F, 1.0F, 0.0F);
		head.addChild(body2);
		body2.cubeList.add(new ModelBox(body2, 152, 2, -8.0F, -22.0F, -8.0F, 16, 5, 16, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 180, 37, -9.0F, -17.0F, -9.0F, 18, 1, 1, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 187, 52, -9.0F, -17.0F, 8.0F, 18, 1, 1, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 194, 70, -9.0F, -17.0F, -8.0F, 1, 1, 16, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 186, 107, 8.0F, -17.0F, -8.0F, 1, 1, 16, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 164, 140, -6.0F, -28.0F, -6.0F, 12, 6, 12, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 172, 183, -4.0F, -34.0F, -4.0F, 8, 6, 8, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 207, 212, -2.0F, -39.0F, -2.0F, 4, 5, 4, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 176, 236, -1.0F, -42.0F, -1.0F, 2, 3, 2, 0.0F, false));
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
}
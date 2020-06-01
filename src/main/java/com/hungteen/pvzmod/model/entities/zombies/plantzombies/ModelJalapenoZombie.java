package com.hungteen.pvzmod.model.entities.zombies.plantzombies;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelJalapenoZombie extends ModelBase {
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
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

	public ModelJalapenoZombie() {
		textureWidth = 256;
		textureHeight = 256;

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, 1.0F, 0.0F);
		left_leg.cubeList.add(new ModelBox(left_leg, 0, 0, -4.0F, -1.0F, -4.0F, 8, 24, 8, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, 1.0F, 0.0F);
		right_leg.cubeList.add(new ModelBox(right_leg, 44, 0, -4.0F, -1.0F, -4.0F, 8, 24, 8, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -3.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 41, -8.0F, -21.0F, -4.0F, 16, 24, 8, 0.0F, false));

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(8.0F, -24.0F, 0.0F);
		left_hand.cubeList.add(new ModelBox(left_hand, 96, 60, 0.0F, 0.0F, -4.0F, 8, 24, 8, 0.0F, false));

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-8.0F, -24.0F, 0.0F);
		right_hand.cubeList.add(new ModelBox(right_hand, 96, 0, -8.0F, 0.0F, -4.0F, 8, 24, 8, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -24.0F, -2.0F);
		head.cubeList.add(new ModelBox(head, 191, 221, -8.0F, -21.0F, -8.0F, 16, 16, 16, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 194, 196, -7.0F, -5.0F, -7.0F, 14, 2, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 202, 168, -6.0F, -3.0F, -6.0F, 12, 3, 12, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 210, 141, -5.0F, 0.0F, -5.0F, 10, 4, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 212, 114, -4.0F, 4.0F, -5.0F, 8, 5, 8, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 225, 95, -3.0F, 9.0F, -6.0F, 5, 4, 7, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 224, 70, -3.0F, 13.0F, -8.0F, 6, 3, 7, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 227, 54, -3.0F, 16.0F, -12.0F, 6, 3, 8, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 72, 237, -7.0F, -22.0F, -7.0F, 14, 1, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 10, 235, -6.0F, -23.0F, -6.0F, 12, 1, 12, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, 16.0F, -12.0F);
		setRotationAngle(bone8, 1.0472F, 0.0F, 0.0F);
		head.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 226, 32, -2.0F, -3.0F, -1.0F, 4, 4, 1, 0.0F, false));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, -3.0F, 0.0F);
		setRotationAngle(bone9, 0.6981F, 0.0F, 0.0F);
		bone8.addChild(bone9);
		bone9.cubeList.add(new ModelBox(bone9, 164, 224, -2.0F, -1.0F, -1.0F, 4, 1, 4, 0.0F, false));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(0.0F, -0.0152F, 2.8264F);
		setRotationAngle(bone10, 0.9599F, 0.0F, 0.0F);
		bone9.addChild(bone10);
		bone10.cubeList.add(new ModelBox(bone10, 150, 236, -1.0F, -1.0038F, -0.0872F, 2, 4, 1, 0.0F, false));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(0.0F, -23.0F, 0.0F);
		setRotationAngle(bone11, 0.0F, 0.0F, 0.2618F);
		head.addChild(bone11);
		bone11.cubeList.add(new ModelBox(bone11, 127, 207, -1.0F, -6.0F, -1.0F, 2, 7, 2, 0.0F, false));

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(1.2247F, -5.2929F, 0.0F);
		setRotationAngle(bone12, 0.0F, 0.0F, -0.4363F);
		bone11.addChild(bone12);
		bone12.cubeList.add(new ModelBox(bone12, 87, 213, -2.0F, -2.0F, -1.0F, 6, 2, 2, 0.0F, false));

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(4.6375F, 2.1433F, 0.0F);
		setRotationAngle(bone13, 0.0F, 0.0F, -0.6109F);
		bone12.addChild(bone13);
		bone13.cubeList.add(new ModelBox(bone13, 58, 204, 0.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F, false));

		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(0.7071F, -0.1213F, 0.0F);
		setRotationAngle(bone14, 0.0F, 0.0F, -1.9199F);
		bone13.addChild(bone14);
		bone14.cubeList.add(new ModelBox(bone14, 101, 183, -1.0F, -3.0F, -1.0F, 1, 4, 2, 0.0F, false));
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
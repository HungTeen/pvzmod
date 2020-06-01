package com.hungteen.pvzmod.model.entities.zombies.plantzombies;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelNutWallZombie extends ModelBase {
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer body2;//head

	public ModelNutWallZombie() {
		textureWidth = 256;
		textureHeight = 256;

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, 1.0F, 0.0F);
		left_leg.cubeList.add(new ModelBox(left_leg, 0, 0, -4.0F, -1.0F, -4.0F, 8, 24, 8, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, 1.0F, 0.0F);
		right_leg.cubeList.add(new ModelBox(right_leg, 44, 0, -4.0F, -1.0F, -4.0F, 8, 24, 8, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -7.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 41, -8.0F, -17.0F, -4.0F, 16, 24, 8, 0.0F, false));

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(9.0F, -24.0F, 0.0F);
		left_hand.cubeList.add(new ModelBox(left_hand, 96, 60, -1.0F, 0.0F, -4.0F, 8, 24, 8, 0.0F, false));

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-8.0F, -24.0F, 0.0F);
		right_hand.cubeList.add(new ModelBox(right_hand, 96, 0, -8.0F, 0.0F, -4.0F, 8, 24, 8, 0.0F, false));

		body2 = new ModelRenderer(this);
		body2.setRotationPoint(0.0F, -25.0F, 0.0F);
		body2.cubeList.add(new ModelBox(body2, 92, 134, -11.0F, 0.0F, -11.0F, 22, 1, 22, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 0, 135, -14.0F, -2.0F, -14.0F, 28, 2, 28, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 0, 180, -16.0F, -46.0F, -16.0F, 32, 44, 32, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 196, 188, 16.0F, -44.0F, -14.0F, 2, 40, 28, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 132, 188, -18.0F, -44.0F, -14.0F, 2, 40, 28, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 196, 0, -14.0F, -44.0F, -18.0F, 28, 40, 2, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 196, 45, -14.0F, -44.0F, 16.0F, 28, 40, 2, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 90, 97, -14.0F, -48.0F, -14.0F, 28, 2, 28, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 95, 159, -11.0F, -50.0F, -11.0F, 22, 2, 22, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 214, 89, -19.0F, -40.0F, -10.0F, 1, 32, 20, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 150, 1, 18.0F, -40.0F, -10.0F, 1, 32, 20, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 145, 56, -11.0F, -41.0F, -19.0F, 22, 34, 1, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 209, 144, -11.0F, -41.0F, 18.0F, 22, 34, 1, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 29, 75, -7.0F, -52.0F, -7.0F, 14, 2, 14, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		left_leg.render(f5);
		right_leg.render(f5);
		body.render(f5);
		left_hand.render(f5);
		right_hand.render(f5);
		body2.render(f5);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
        this.body2.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.body2.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.right_leg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
        this.left_leg.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
        this.right_hand.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_;
        this.left_hand.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_;
       
    }
}
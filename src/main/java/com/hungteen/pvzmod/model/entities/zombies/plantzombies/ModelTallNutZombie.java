package com.hungteen.pvzmod.model.entities.zombies.plantzombies;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelTallNutZombie extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer head;
	private final ModelRenderer right_hand;
	private final ModelRenderer left_hand;
	private final ModelRenderer body;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;

	public ModelTallNutZombie() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -48.0F, 0.0F);
		total.addChild(head);
		head.cubeList.add(new ModelBox(head, 172, 194, -10.0F, -42.0F, -10.0F, 20, 40, 20, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 114, 239, -7.0F, -1.0F, -7.0F, 14, 1, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 39, 236, -9.0F, -2.0F, -9.0F, 18, 1, 18, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 201, 133, -7.0F, -44.0F, -7.0F, 14, 1, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 182, 156, -9.0F, -43.0F, -9.0F, 18, 1, 18, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 219, 4, -11.0F, -41.0F, -9.0F, 1, 38, 18, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 2, 208, 11.0F, -39.0F, -7.0F, 1, 34, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 42, 170, 10.0F, -41.0F, -9.0F, 1, 38, 18, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 93, 178, -12.0F, -39.0F, -7.0F, 1, 34, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 219, 68, -9.0F, -41.0F, -11.0F, 18, 38, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 176, 3, -7.0F, -39.0F, -12.0F, 14, 34, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 159, 52, -9.0F, -41.0F, 10.0F, 18, 38, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 124, 112, -7.0F, -39.0F, 11.0F, 14, 34, 1, 0.0F, false));

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-8.0F, -44.0F, 0.0F);
		total.addChild(right_hand);
		right_hand.cubeList.add(new ModelBox(right_hand, 96, 0, -8.0F, -4.0F, -4.0F, 8, 24, 8, 0.0F, false));

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(9.0F, -44.0F, 0.0F);
		total.addChild(left_hand);
		left_hand.cubeList.add(new ModelBox(left_hand, 96, 60, -1.0F, -4.0F, -4.0F, 8, 24, 8, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -31.0F, 0.0F);
		total.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 41, -8.0F, -17.0F, -4.0F, 16, 24, 8, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, -24.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 44, 0, -4.0F, 0.0F, -4.0F, 8, 24, 8, 0.0F, false));

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, -24.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 0, 0, -4.0F, 0.0F, -4.0F, 8, 24, 8, 0.0F, false));
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
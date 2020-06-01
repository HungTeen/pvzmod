package com.hungteen.pvzmod.model.entities.zombies.plantzombies;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


public class ModelGatlingPeaZombie extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer gar;
	private final ModelRenderer helmet;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;

	public ModelGatlingPeaZombie() {
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
		head.setRotationPoint(0.0F, -26.0F, -1.0F);
		up.addChild(head);
		head.cubeList.add(new ModelBox(head, 4, 243, -5.0F, -10.0F, 7.0F, 10, 10, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 200, 224, -7.0F, -12.0F, -7.0F, 14, 14, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 183, 236, 7.0F, -10.0F, -5.0F, 1, 10, 6, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 164, 234, -8.0F, -10.0F, -5.0F, 1, 10, 6, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 122, 241, -5.0F, -13.0F, -5.0F, 10, 1, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 224, 190, -3.0F, -8.0F, -17.0F, 6, 6, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 234, 169, -4.0F, -8.0F, -17.0F, 1, 6, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 179, 203, -3.0F, -9.0F, -17.0F, 6, 1, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 198, 177, 3.0F, -8.0F, -17.0F, 1, 6, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 144, 187, -3.0F, -2.0F, -17.0F, 6, 1, 10, 0.0F, false));

		gar = new ModelRenderer(this);
		gar.setRotationPoint(0.0F, -5.0F, -17.0F);
		head.addChild(gar);
		gar.cubeList.add(new ModelBox(gar, 102, 244, -3.0F, -1.0F, -5.0F, 2, 2, 5, 0.0F, false));
		gar.cubeList.add(new ModelBox(gar, 83, 246, -1.0F, -3.0F, -5.0F, 2, 2, 5, 0.0F, false));
		gar.cubeList.add(new ModelBox(gar, 64, 246, 1.0F, -1.0F, -5.0F, 2, 2, 5, 0.0F, false));
		gar.cubeList.add(new ModelBox(gar, 44, 245, -1.0F, 1.0F, -5.0F, 2, 2, 5, 0.0F, false));

		helmet = new ModelRenderer(this);
		helmet.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(helmet);
		helmet.cubeList.add(new ModelBox(helmet, 4, 222, -9.0F, -12.0F, 1.0F, 2, 15, 2, 0.0F, false));
		helmet.cubeList.add(new ModelBox(helmet, 21, 221, 7.0F, -13.0F, 1.0F, 2, 16, 2, 0.0F, false));
		helmet.cubeList.add(new ModelBox(helmet, 32, 219, -9.0F, -15.0F, -8.0F, 18, 3, 18, 0.0F, false));
		helmet.cubeList.add(new ModelBox(helmet, 112, 210, -8.0F, -12.0F, 3.0F, 16, 13, 8, 0.0F, false));
		helmet.cubeList.add(new ModelBox(helmet, 2, 194, -8.0F, -17.0F, -7.0F, 16, 2, 16, 0.0F, false));
		helmet.cubeList.add(new ModelBox(helmet, 2, 176, -6.0F, -19.0F, -5.0F, 12, 2, 12, 0.0F, false));
		helmet.cubeList.add(new ModelBox(helmet, 230, 210, -4.0F, 2.0F, -7.0F, 8, 4, 2, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-7.0F, 2.0F, 2.0F);
		helmet.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.7854F, 0.4363F);
		bone.cubeList.add(new ModelBox(bone, 77, 211, -0.5255F, -0.4837F, -3.3539F, 9, 1, 2, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(7.0F, 3.0F, 2.0F);
		helmet.addChild(bone2);
		setRotationAngle(bone2, 0.0F, -0.7854F, -0.4363F);
		bone2.cubeList.add(new ModelBox(bone2, 176, 224, -7.8337F, -1.0611F, -3.9948F, 9, 1, 2, 0.0F, false));
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
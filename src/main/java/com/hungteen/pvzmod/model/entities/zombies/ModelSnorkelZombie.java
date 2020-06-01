package com.hungteen.pvzmod.model.entities.zombies;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelSnorkelZombie extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer head;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer right_hand;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer left_hand;
	private final ModelRenderer armor;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer body;
	private final ModelRenderer right_leg;
	private final ModelRenderer right_foot;
	private final ModelRenderer left_leg;
	private final ModelRenderer left_foot;

	public ModelSnorkelZombie() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 22.0F, 0.0F);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -48.0F, 0.0F);
		total.addChild(head);
		head.cubeList.add(new ModelBox(head, 80, 104, -6.0F, -12.0F, -6.0F, 12, 12, 12, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 102, 89, 6.0F, -8.0F, -6.0F, 1, 2, 12, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 102, 73, -7.0F, -8.0F, -6.0F, 1, 2, 12, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 102, 68, -6.0F, -8.0F, 6.0F, 12, 2, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 102, 60, -6.0F, -9.0F, -7.0F, 12, 4, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 110, 56, -4.0F, -5.0F, -7.0F, 8, 1, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 110, 52, -4.0F, -10.0F, -7.0F, 8, 1, 1, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 0.0F, -7.0F);
		head.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 118, 45, -1.0F, -2.0F, -2.0F, 2, 2, 3, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 110, 39, -8.0F, -2.0F, -2.0F, 7, 2, 2, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(-7.0F, -1.0F, 0.0F);
		setRotationAngle(bone7, 1.309F, -0.6981F, 0.0F);
		bone6.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 40, 109, -1.6428F, -1.6943F, -0.3237F, 2, 2, 17, 0.0F, false));

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-8.0F, -48.0F, 0.0F);
		total.addChild(right_hand);
		right_hand.cubeList.add(new ModelBox(right_hand, 112, 1, -4.0F, 0.0F, -2.0F, 4, 25, 4, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
		right_hand.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 80, 89, -5.0F, 3.0F, -3.0F, 1, 5, 6, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(-4.0F, 5.0F, -2.0F);
		setRotationAngle(bone4, 0.0F, -1.1345F, 0.0F);
		bone3.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 76, 103, -1.0F, -2.0F, -4.0F, 1, 5, 4, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(-4.0F, 5.0F, 2.0F);
		setRotationAngle(bone5, 0.0F, 1.1345F, 0.0F);
		right_hand.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 96, 47, -1.0F, -2.0F, 0.0F, 1, 5, 4, 0.0F, false));

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(9.0F, -48.0F, 0.0F);
		total.addChild(left_hand);
		left_hand.cubeList.add(new ModelBox(left_hand, 94, 0, -1.0F, 0.0F, -2.0F, 4, 25, 4, 0.0F, false));

		armor = new ModelRenderer(this);
		armor.setRotationPoint(3.0F, 5.0F, 0.0F);
		left_hand.addChild(armor);
		armor.cubeList.add(new ModelBox(armor, 76, 3, 0.0F, -2.0F, -3.0F, 1, 5, 6, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 3.0F);
		setRotationAngle(bone, 0.0F, -1.1345F, 0.0F);
		armor.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 79, 18, -0.866F, -2.0F, -0.5F, 1, 5, 5, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 1.0F, -2.0F);
		setRotationAngle(bone2, 0.0F, 1.1345F, 0.0F);
		armor.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 80, 35, 0.0F, -3.0F, -4.0F, 1, 5, 4, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -31.0F, 0.0F);
		total.addChild(body);
		body.cubeList.add(new ModelBox(body, 1, 93, -8.0F, -17.0F, -3.0F, 16, 25, 6, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, -23.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 1, 64, -2.0F, 0.0F, -2.0F, 4, 23, 4, 0.0F, false));

		right_foot = new ModelRenderer(this);
		right_foot.setRotationPoint(0.0F, 24.0F, 0.0F);
		right_leg.addChild(right_foot);
		right_foot.cubeList.add(new ModelBox(right_foot, 22, 73, -2.0F, -1.0F, -11.0F, 4, 2, 14, 0.0F, false));
		right_foot.cubeList.add(new ModelBox(right_foot, 56, 95, 2.0F, -1.0F, -10.0F, 1, 2, 6, 0.0F, false));
		right_foot.cubeList.add(new ModelBox(right_foot, 65, 81, -3.0F, -1.0F, -10.0F, 1, 2, 6, 0.0F, false));

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, -23.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 57, 2, -2.0F, 0.0F, -2.0F, 4, 23, 4, 0.0F, false));

		left_foot = new ModelRenderer(this);
		left_foot.setRotationPoint(0.0F, 24.0F, 0.0F);
		left_leg.addChild(left_foot);
		left_foot.cubeList.add(new ModelBox(left_foot, 62, 62, -2.0F, -1.0F, -11.0F, 4, 2, 14, 0.0F, false));
		left_foot.cubeList.add(new ModelBox(left_foot, 49, 64, 2.0F, -1.0F, -10.0F, 1, 2, 6, 0.0F, false));
		left_foot.cubeList.add(new ModelBox(left_foot, 60, 50, -3.0F, -1.0F, -10.0F, 1, 2, 6, 0.0F, false));
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
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        if(entityIn.isInWater()) {
        	this.left_foot.rotateAngleX=MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        	this.right_foot.rotateAngleX=MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        }else {
            this.right_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.left_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        }
	}
}
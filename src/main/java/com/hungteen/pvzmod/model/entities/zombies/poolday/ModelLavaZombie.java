package com.hungteen.pvzmod.model.entities.zombies.poolday;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelLavaZombie extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer head;
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

	public ModelLavaZombie() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 22.0F, 0.0F);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -48.0F, 0.0F);
		total.addChild(head);
		head.cubeList.add(new ModelBox(head, 200, 228, -7.0F, -14.0F, -7.0F, 14, 14, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 163, 236, 7.0F, -9.0F, -8.0F, 1, 4, 15, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 224, 206, -8.0F, -9.0F, -8.0F, 1, 4, 15, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 180, 218, -7.0F, -9.0F, 7.0F, 14, 4, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 226, 193, -7.0F, -10.0F, -8.0F, 14, 6, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 191, 204, -6.0F, -4.0F, -8.0F, 12, 1, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 230, 184, -6.0F, -11.0F, -8.0F, 12, 1, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 238, 176, -4.0F, -3.0F, -8.0F, 8, 1, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 238, 167, -4.0F, -12.0F, -8.0F, 8, 1, 1, 0.0F, false));

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-8.0F, -48.0F, 0.0F);
		total.addChild(right_hand);
		right_hand.cubeList.add(new ModelBox(right_hand, 232, 0, -6.0F, 0.0F, -3.0F, 6, 25, 6, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-2.0F, 0.0F, 0.0F);
		right_hand.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 238, 54, -5.0F, 3.0F, -4.0F, 1, 6, 8, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(-4.0F, 5.0F, -2.0F);
		setRotationAngle(bone4, 0.0F, -1.1345F, 0.0F);
		bone3.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 242, 36, -1.9063F, -2.0F, -6.4226F, 1, 6, 6, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(-6.0F, 5.0F, 3.0F);
		setRotationAngle(bone5, 0.0F, 1.1345F, 0.0F);
		right_hand.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 242, 74, -1.0F, -2.0F, 0.0F, 1, 6, 6, 0.0F, false));

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(9.0F, -48.0F, 0.0F);
		total.addChild(left_hand);
		left_hand.cubeList.add(new ModelBox(left_hand, 202, 0, -1.0F, 0.0F, -3.0F, 6, 25, 6, 0.0F, false));

		armor = new ModelRenderer(this);
		armor.setRotationPoint(5.0F, 5.0F, 0.0F);
		left_hand.addChild(armor);
		armor.cubeList.add(new ModelBox(armor, 208, 36, 0.0F, -2.0F, -4.0F, 1, 6, 8, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 3.0F);
		setRotationAngle(bone, 0.0F, -1.1345F, 0.0F);
		armor.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 216, 56, 0.0403F, -2.0F, -0.0774F, 1, 6, 6, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 1.0F, -2.0F);
		setRotationAngle(bone2, 0.0F, 1.1345F, 0.0F);
		armor.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 219, 76, 0.9063F, -3.0F, -6.4226F, 1, 6, 6, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -31.0F, 0.0F);
		total.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 223, -8.0F, -17.0F, -4.0F, 16, 25, 8, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 54, 226, -8.0F, -14.0F, 4.0F, 7, 21, 7, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 90, 224, 1.0F, -14.0F, 4.0F, 7, 21, 7, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 125, 251, -5.0F, -15.0F, 7.0F, 10, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 127, 242, -5.0F, 7.0F, 7.0F, 10, 1, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 147, 233, -5.0F, -5.0F, 11.0F, 10, 2, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 5, 193, -1.0F, -13.0F, 4.0F, 2, 19, 5, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, -23.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 29, 186, -3.0F, 0.0F, -3.0F, 6, 23, 6, 0.0F, false));

		right_foot = new ModelRenderer(this);
		right_foot.setRotationPoint(0.0F, 24.0F, 0.0F);
		right_leg.addChild(right_foot);
		right_foot.cubeList.add(new ModelBox(right_foot, 65, 202, -3.0F, -1.0F, -12.0F, 6, 2, 17, 0.0F, false));
		right_foot.cubeList.add(new ModelBox(right_foot, 124, 218, 3.0F, -1.0F, -11.0F, 1, 2, 7, 0.0F, false));
		right_foot.cubeList.add(new ModelBox(right_foot, 146, 214, -4.0F, -1.0F, -11.0F, 1, 2, 7, 0.0F, false));

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, -23.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 6, 154, -3.0F, 0.0F, -3.0F, 6, 23, 6, 0.0F, false));

		left_foot = new ModelRenderer(this);
		left_foot.setRotationPoint(0.0F, 24.0F, 0.0F);
		left_leg.addChild(left_foot);
		left_foot.cubeList.add(new ModelBox(left_foot, 77, 170, -3.0F, -1.0F, -12.0F, 6, 2, 17, 0.0F, false));
		left_foot.cubeList.add(new ModelBox(left_foot, 55, 171, 3.0F, -1.0F, -11.0F, 1, 2, 7, 0.0F, false));
		left_foot.cubeList.add(new ModelBox(left_foot, 134, 196, -4.0F, -1.0F, -11.0F, 1, 2, 7, 0.0F, false));
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
        if(entityIn.isInLava()) {
        	this.left_foot.rotateAngleX=MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        	this.right_foot.rotateAngleX=MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        }else {
            this.right_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.left_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        }
	}
}
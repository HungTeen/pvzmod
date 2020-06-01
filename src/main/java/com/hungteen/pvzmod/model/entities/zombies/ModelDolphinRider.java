package com.hungteen.pvzmod.model.entities.zombies;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelDolphinRider extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;

	public ModelDolphinRider() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, -24.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 112, 100, -2.0F, 0.0F, -2.0F, 4, 24, 4, 0.0F, false));
		left_leg.cubeList.add(new ModelBox(left_leg, 0, 0, -2.0F, 22.0F, -6.0F, 4, 2, 4, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, -24.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 93, 100, -2.0F, 0.0F, -2.0F, 4, 24, 4, 0.0F, false));
		right_leg.cubeList.add(new ModelBox(right_leg, 0, 0, -2.0F, 22.0F, -6.0F, 4, 2, 4, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -31.0F, 0.0F);
		total.addChild(body);
		body.cubeList.add(new ModelBox(body, 0, 0, -7.0F, -17.0F, -3.0F, 14, 24, 6, 0.0F, false));

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(7.0F, -46.0F, 0.0F);
		total.addChild(left_hand);
		left_hand.cubeList.add(new ModelBox(left_hand, 112, 66, 0.0F, -2.0F, -2.0F, 4, 24, 4, 0.0F, false));

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-7.0F, -46.0F, 0.0F);
		total.addChild(right_hand);
		right_hand.cubeList.add(new ModelBox(right_hand, 112, 32, -4.0F, -2.0F, -2.0F, 4, 24, 4, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -48.0F, 0.0F);
		total.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 104, -6.0F, -12.0F, -6.0F, 12, 12, 12, 0.0F, false));
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
		if(entityIn.isRiding()) {
			this.left_leg.rotateAngleX=-1.8f;
			this.right_leg.rotateAngleX=-1.8f;
			this.left_leg.rotateAngleY=-0.5f;
			this.right_leg.rotateAngleY=0.5f;
		}
		else {
			this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
	        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
	        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	        this.right_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	        this.left_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		}
	}
}
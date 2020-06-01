package com.hungteen.pvzmod.model.entities.zombies;

import com.hungteen.pvzmod.entities.zombies.EntityBalloonZombie;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelBalloonZombie extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer up;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer bone;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;

	public ModelBalloonZombie() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, -6.0F, 0.0F);

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, 6.0F, 0.0F);
		setRotationAngle(up, 0.2618F, 0.0F, 0.0F);
		total.addChild(up);
		up.cubeList.add(new ModelBox(up, 0, 41, -8.0F, -24.0F, -4.0F, 16, 24, 8, 0.0F, false));

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(9.0F, -21.0F, 0.0F);
		setRotationAngle(left_hand, -1.7453F, 0.0F, 0.0F);
		up.addChild(left_hand);
		left_hand.cubeList.add(new ModelBox(left_hand, 96, 60, -1.0F, -3.0F, -3.0F, 6, 24, 6, 0.0F, false));

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-8.0F, -21.0F, -1.0F);
		setRotationAngle(right_hand, -1.7453F, 0.0F, 0.0F);
		up.addChild(right_hand);
		right_hand.cubeList.add(new ModelBox(right_hand, 96, 0, -6.0F, -4.0F, -3.0F, 6, 24, 6, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -24.0F, 0.0F);
		setRotationAngle(head, -0.2618F, 0.0F, 0.0F);
		up.addChild(head);
		head.cubeList.add(new ModelBox(head, 16, 96, -7.0F, -14.0F, -7.0F, 14, 14, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 99, 110, -3.0F, -15.0F, -3.0F, 6, 1, 6, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 108, 95, -1.0F, -17.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -17.0F, 0.0F);
		head.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 77, 102, -5.0F, -1.0F, 0.0F, 5, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 75, 82, -1.0F, -1.0F, -5.0F, 1, 1, 5, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 78, 93, 0.0F, -1.0F, -1.0F, 5, 1, 1, 0.0F, false));
		bone.cubeList.add(new ModelBox(bone, 78, 118, 0.0F, -1.0F, 0.0F, 1, 1, 5, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, 7.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 44, 0, -3.0F, -2.0F, -3.0F, 6, 25, 6, 0.0F, false));

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, 7.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 0, 0, -3.0F, -2.0F, -3.0F, 6, 25, 6, 0.0F, false));
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
		if(entityIn instanceof EntityBalloonZombie) {
			this.bone.rotateAngleY=ageInTicks;
//			System.out.println(ageInTicks);
			if(entityIn.isRiding()) {
				this.total.rotateAngleX=1.1f;
				this.left_leg.rotateAngleX=-1.1f;
				this.right_leg.rotateAngleX=-1.1f;
				this.head.rotateAngleX=-1.4f;
			}
			else {
				this.total.rotateAngleX=0f;
				this.left_leg.rotateAngleX=0f;
				this.right_leg.rotateAngleX=0f;
				this.head.rotateAngleX=-0.2618F;
			}
		}
	}
}
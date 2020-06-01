package com.hungteen.pvzmod.model.entities.zombies;

import com.hungteen.pvzmod.entities.zombies.night.EntityDancingZombie;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelDancingZombie extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer left_hand;
	private final ModelRenderer left_hand2;

	public ModelDancingZombie() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(5.0F, -30.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 218, 239, -4.0F, 24.0F, -6.0F, 8, 6, 11, 0.0F, false));
		right_leg.cubeList.add(new ModelBox(right_leg, 0, 226, -3.0F, 0.0F, -2.0F, 6, 24, 6, 0.0F, false));

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(-5.0F, -30.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 36, 239, -4.0F, 24.0F, -6.0F, 8, 6, 11, 0.0F, false));
		left_leg.cubeList.add(new ModelBox(left_leg, 96, 226, -3.0F, 0.0F, -2.0F, 6, 24, 6, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -30.0F, 0.0F);
		total.addChild(body);
		body.cubeList.add(new ModelBox(body, 141, 224, -8.0F, -24.0F, -3.0F, 16, 24, 8, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -54.0F, 0.0F);
		total.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 0, -6.0F, -12.0F, -5.0F, 12, 12, 12, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 200, 204, -7.0F, -18.0F, -5.0F, 14, 6, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 232, 177, -8.0F, -12.0F, -1.0F, 2, 5, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 132, 204, 6.0F, -12.0F, -1.0F, 2, 5, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 162, 212, -6.0F, -12.0F, 7.0F, 12, 5, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 70, 228, -7.0F, -9.0F, -7.0F, 1, 1, 6, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 31, 228, 6.0F, -9.0F, -7.0F, 1, 1, 6, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 110, 216, -1.0F, -9.0F, -7.0F, 2, 1, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 198, 197, -6.0F, -11.0F, -7.0F, 5, 5, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 205, 176, 1.0F, -11.0F, -7.0F, 5, 5, 1, 0.0F, false));

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(-9.0F, -52.0F, 0.0F);
		total.addChild(left_hand);
		left_hand.cubeList.add(new ModelBox(left_hand, 6, 179, -5.0F, -2.0F, -2.0F, 6, 24, 6, 0.0F, false));

		left_hand2 = new ModelRenderer(this);
		left_hand2.setRotationPoint(8.0F, -51.0F, 1.0F);
		total.addChild(left_hand2);
		left_hand2.cubeList.add(new ModelBox(left_hand2, 60, 188, 0.0F, -3.0F, -3.0F, 6, 24, 6, 0.0F, false));
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
		if(!(entityIn instanceof EntityDancingZombie)) return ;
		EntityDancingZombie dancer =(EntityDancingZombie) entityIn;
		if(dancer.getDanceTime()>0) {
			this.total.rotateAngleY=-MathHelper.sin(3.14159f*dancer.getDanceTime()/50);
			this.left_hand.rotateAngleX=-3*MathHelper.abs(MathHelper.sin(3.14159f*dancer.getDanceTime()/25));
			this.left_hand2.rotateAngleX=-3*MathHelper.abs(MathHelper.sin(3.14159f*dancer.getDanceTime()/25));
			return;
		}
		if(dancer.getSummonTime()>0) {
			this.left_hand.rotateAngleX=-0.4f;
			this.left_hand.rotateAngleY=-0.7f;
			this.left_hand.rotateAngleZ=0.1f;
			this.left_hand2.rotateAngleX=-1.45f;
			this.left_hand2.rotateAngleY=0.4f;
			this.left_hand2.rotateAngleZ=0.2f;
			return ;
		}
		if(dancer.getWalkTime()>0) {
			this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
	        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
	        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing* 0.6662F) * 1.4F *limbSwingAmount;
	        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing* 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	        this.left_hand2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	        this.left_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		}
	}
}
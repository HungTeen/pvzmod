package com.hungteen.pvzmod.model.entities.zombies.poolnight;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


public class ModelDiggerZombie extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer pickaxe;
	private final ModelRenderer bone;
	private final ModelRenderer head;
	private final ModelRenderer hat;

	public ModelDiggerZombie() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, -24.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 221, 221, -4.0F, 0.0F, -4.0F, 8, 24, 8, 0.0F, false));

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, -24.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 182, 220, -4.0F, 0.0F, -4.0F, 8, 24, 8, 0.0F, false));

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, -24.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -7.0F, 0.0F);
		up.addChild(body);
		body.cubeList.add(new ModelBox(body, 122, 217, -8.0F, -17.0F, -5.0F, 16, 24, 11, 0.0F, false));

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(12.0F, -20.0F, 0.0F);
		up.addChild(left_hand);
		left_hand.cubeList.add(new ModelBox(left_hand, 83, 220, -4.0F, -4.0F, -4.0F, 8, 24, 8, 0.0F, false));

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-12.0F, -20.0F, 0.0F);
		up.addChild(right_hand);
		right_hand.cubeList.add(new ModelBox(right_hand, 43, 219, -4.0F, -4.0F, -4.0F, 8, 24, 8, 0.0F, false));

		pickaxe = new ModelRenderer(this);
		pickaxe.setRotationPoint(0.0F, 20.0F, -2.0F);
		right_hand.addChild(pickaxe);
		setRotationAngle(pickaxe, -0.1745F, 0.0F, 0.0F);
		pickaxe.cubeList.add(new ModelBox(pickaxe, 8, 231, -1.0F, -8.0F, -19.0F, 2, 13, 2, 0.0F, false));
		pickaxe.cubeList.add(new ModelBox(pickaxe, 193, 179, -1.0F, -1.0F, -17.0F, 2, 2, 26, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 6.0F, -18.0F);
		pickaxe.addChild(bone);
		setRotationAngle(bone, 0.5236F, 0.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 27, 243, -1.0F, -1.2929F, -0.2929F, 2, 5, 2, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -24.0F, 0.0F);
		up.addChild(head);
		head.cubeList.add(new ModelBox(head, 118, 177, -8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F, false));

		hat = new ModelRenderer(this);
		hat.setRotationPoint(0.0F, -16.0F, 0.0F);
		head.addChild(hat);
		hat.cubeList.add(new ModelBox(hat, 44, 192, -9.0F, -1.0F, -8.0F, 18, 1, 17, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 52, 173, -8.0F, -3.0F, -6.0F, 16, 2, 12, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 57, 159, -8.0F, -5.0F, -3.0F, 16, 2, 7, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 120, 168, -8.0F, -6.0F, 0.0F, 16, 1, 3, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 170, 161, -1.0F, -4.0F, -9.0F, 2, 2, 6, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 200, 161, -2.0F, -5.0F, -11.0F, 4, 4, 2, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 213, 143, -9.0F, 0.0F, 8.0F, 18, 2, 1, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 166, 143, -9.0F, 0.0F, -9.0F, 18, 1, 1, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 123, 131, 8.0F, 0.0F, -8.0F, 1, 1, 16, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 79, 132, -9.0F, 0.0F, -8.0F, 1, 1, 16, 0.0F, false));
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
        this.right_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}
}
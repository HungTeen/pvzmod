package com.hungteen.pvzmod.model.entities;

//Made with Blockbench
//Paste this code into your mod.

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCrazyDave extends ModelBase {
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer hat;

	public ModelCrazyDave() {
		textureWidth = 160;
		textureHeight = 160;

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(5.0F, -15.0F, 0.0F);
		left_leg.cubeList.add(new ModelBox(left_leg, 115, 49, -5.0F, -4.0F, -4.0F, 13, 43, 8, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-7.0F, -15.0F, 0.0F);
		right_leg.cubeList.add(new ModelBox(right_leg, 0, 68, -5.0F, -4.0F, -4.0F, 13, 43, 8, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(-3.0F, -38.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 43, 116, -10.0F, -17.0F, -4.0F, 26, 36, 8, 0.0F, false));

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(13.0F, -54.0F, 0.0F);
		left_hand.cubeList.add(new ModelBox(left_hand, 122, 114, 0.0F, -1.0F, -4.0F, 11, 36, 8, 0.0F, false));

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-13.0F, -54.0F, 0.0F);
		right_hand.cubeList.add(new ModelBox(right_hand, 45, 66, -11.0F, -1.0F, -4.0F, 11, 36, 8, 0.0F, true));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -56.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 27, -10.0F, -17.0F, -9.0F, 20, 18, 18, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 118, 40, -10.0F, 1.0F, -9.0F, 20, 4, 1, 0.0F, false));

		hat = new ModelRenderer(this);
		hat.setRotationPoint(0.0F, -20.0F, 0.0F);
		hat.cubeList.add(new ModelBox(hat, 80, 34, -2.0F, -3.0F, -24.0F, 4, 3, 14, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 76, 0, -11.0F, -8.0F, -10.0F, 22, 11, 20, 0.0F, false));
		head.addChild(hat);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		left_leg.render(f5);
		right_leg.render(f5);
		body.render(f5);
		left_hand.render(f5);
		right_hand.render(f5);
		head.render(f5);
		//hat.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.right_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
       
        this.hat.rotateAngleY= ageInTicks*1.0f;
    }
	
}
package com.hungteen.pvzmod.model.entities.plants.common;

import com.hungteen.pvzmod.entities.plants.common.EntityMelonPult;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


public class ModelMelonPult extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer head;
	private final ModelRenderer leg;
	private final ModelRenderer leaf1;
	private final ModelRenderer leaf2;
	private final ModelRenderer leaf3;
	private final ModelRenderer leaf4;
	private final ModelRenderer leaf5;
	private final ModelRenderer leaf6;
	private final ModelRenderer pult1;
	private final ModelRenderer pult2;
	private final ModelRenderer basket;
	private final ModelRenderer bone;

	public ModelMelonPult() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -2.0F, 0.0F);
		total.addChild(head);
		head.cubeList.add(new ModelBox(head, 147, 205, -14.0F, -25.0F, -13.0F, 28, 24, 26, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 154, 176, -13.0F, -26.0F, -12.0F, 26, 1, 24, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 169, 152, -11.0F, -27.0F, -10.0F, 22, 1, 20, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 198, 126, -13.0F, -24.0F, -14.0F, 26, 22, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 207, 104, -11.0F, -22.0F, -15.0F, 22, 18, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 208, 82, -11.0F, -22.0F, 14.0F, 22, 18, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 200, 56, -13.0F, -24.0F, 13.0F, 26, 22, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 204, 5, -15.0F, -24.0F, -12.0F, 1, 22, 24, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 146, 4, 14.0F, -24.0F, -12.0F, 1, 22, 24, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 153, 54, -16.0F, -22.0F, -10.0F, 1, 18, 20, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 98, 3, 15.0F, -22.0F, -10.0F, 1, 18, 20, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 40, 228, -13.0F, -1.0F, -12.0F, 26, 1, 24, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 48, 203, -11.0F, 0.0F, -10.0F, 22, 1, 20, 0.0F, false));

		leg = new ModelRenderer(this);
		leg.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(leg);
		

		leaf1 = new ModelRenderer(this);
		leaf1.setRotationPoint(0.0F, 0.0F, 0.0F);
		leg.addChild(leaf1);
		leaf1.cubeList.add(new ModelBox(leaf1, 88, 54, -5.0F, 1.0F, -20.0F, 10, 1, 20, 0.0F, false));

		leaf2 = new ModelRenderer(this);
		leaf2.setRotationPoint(0.0F, 0.0F, 0.0F);
		leg.addChild(leaf2);
		setRotationAngle(leaf2, 0.0F, -0.4363F, 0.0F);
		leaf2.cubeList.add(new ModelBox(leaf2, 94, 80, -20.0F, 1.0F, -5.0F, 14, 1, 10, 0.0F, false));

		leaf3 = new ModelRenderer(this);
		leaf3.setRotationPoint(0.0F, 0.0F, 0.0F);
		leg.addChild(leaf3);
		setRotationAngle(leaf3, 0.0F, 0.4363F, 0.0F);
		leaf3.cubeList.add(new ModelBox(leaf3, 93, 96, 6.0F, 1.0F, -5.0F, 14, 1, 10, 0.0F, false));

		leaf4 = new ModelRenderer(this);
		leaf4.setRotationPoint(0.0F, 0.0F, 0.0F);
		leg.addChild(leaf4);
		leaf4.cubeList.add(new ModelBox(leaf4, 79, 111, -5.0F, 1.0F, 0.0F, 10, 1, 20, 0.0F, false));

		leaf5 = new ModelRenderer(this);
		leaf5.setRotationPoint(0.0F, 0.0F, 0.0F);
		leg.addChild(leaf5);
		setRotationAngle(leaf5, 0.0F, 0.4363F, 0.0F);
		leaf5.cubeList.add(new ModelBox(leaf5, 87, 138, -20.0F, 1.0F, -5.0F, 14, 1, 10, 0.0F, false));

		leaf6 = new ModelRenderer(this);
		leaf6.setRotationPoint(0.0F, 0.0F, 0.0F);
		leg.addChild(leaf6);
		setRotationAngle(leaf6, 0.0F, -0.4363F, 0.0F);
		leaf6.cubeList.add(new ModelBox(leaf6, 90, 154, 6.0F, 1.0F, -5.0F, 14, 1, 10, 0.0F, false));

		pult1 = new ModelRenderer(this);
		pult1.setRotationPoint(0.0F, -30.0F, 12.0F);
		total.addChild(pult1);
		setRotationAngle(pult1, -0.9599F, 0.0F, 0.0F);
		pult1.cubeList.add(new ModelBox(pult1, 137, 185, -1.0F, -19.0F, -1.0F, 2, 23, 2, 0.0F, false));
		pult1.cubeList.add(new ModelBox(pult1, 56, 144, -2.0F, -7.0F, -2.0F, 4, 1, 4, 0.0F, false));
		pult1.cubeList.add(new ModelBox(pult1, 30, 145, -2.0F, -14.0F, -2.0F, 4, 1, 4, 0.0F, false));

		pult2 = new ModelRenderer(this);
		pult2.setRotationPoint(0.0F, -19.0F, 0.0F);
		pult1.addChild(pult2);
		setRotationAngle(pult2, 0.5236F, 0.0F, 0.0F);
		pult2.cubeList.add(new ModelBox(pult2, 37, 6, -1.0F, -1.0F, -1.0F, 2, 2, 24, 0.0F, false));
		pult2.cubeList.add(new ModelBox(pult2, 72, 41, -2.0F, -2.0F, 5.0F, 4, 4, 1, 0.0F, false));
		pult2.cubeList.add(new ModelBox(pult2, 69, 55, -2.0F, -2.0F, 12.0F, 4, 4, 1, 0.0F, false));

		basket = new ModelRenderer(this);
		basket.setRotationPoint(0.0F, -32.0514F, 23.4938F);
		pult1.addChild(basket);
		setRotationAngle(basket, -0.5236F, 0.0F, 0.0F);
		basket.cubeList.add(new ModelBox(basket, 18, 38, -10.0F, -15.0F, 0.0F, 20, 18, 2, 0.0F, false));
		basket.cubeList.add(new ModelBox(basket, 16, 5, -10.0F, -15.0F, -6.0F, 1, 18, 6, 0.0F, false));
		basket.cubeList.add(new ModelBox(basket, 58, 67, 9.0F, -15.0F, -6.0F, 1, 18, 6, 0.0F, false));
		basket.cubeList.add(new ModelBox(basket, 2, 64, -9.0F, 3.0F, -6.0F, 18, 1, 6, 0.0F, false));
		basket.cubeList.add(new ModelBox(basket, 2, 77, -9.0F, -16.0F, -6.0F, 18, 1, 6, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -5.0038F, 1.9128F);
		basket.addChild(bone);
		setRotationAngle(bone, 0.5236F, 0.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 9, 98, -9.0F, -23.0F, -16.0F, 18, 25, 14, 0.0F, false));
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
		EntityMelonPult entity=(EntityMelonPult) entityIn;
		int time=entity.getAttackTime();
		if(entity.getCanAttackNow()) {
			pult1.rotateAngleX=-1+1.2f*MathHelper.sin( (float) (time*Math.PI/20f));
		}
		else{
			
			pult1.rotateAngleX=-1+0.12f*MathHelper.sin( (float) (time*Math.PI/20f));
		}
	}
}
package com.hungteen.pvzmod.model.entities.plants.common;

import com.hungteen.pvzmod.entities.plants.common.EntityCabbagePult;
import com.hungteen.pvzmod.entities.plants.common.EntityKernelPult;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelKernelPult extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer head;
	private final ModelRenderer bone;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone2;
	private final ModelRenderer pult;
	private final ModelRenderer pult2;
	private final ModelRenderer basket;

	public ModelKernelPult() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(head);
		head.cubeList.add(new ModelBox(head, 158, 224, -12.0F, -7.0F, -12.0F, 24, 6, 24, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 165, 157, -11.0F, -13.0F, -11.0F, 22, 6, 22, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 173, 193, -10.0F, -19.0F, -10.0F, 20, 6, 20, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 180, 126, -9.0F, -25.0F, -9.0F, 18, 6, 18, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 189, 101, -8.0F, -28.0F, -8.0F, 16, 3, 16, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 206, 80, -5.0F, -1.0F, -20.0F, 10, 1, 14, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone, 0.0F, -0.5236F, 0.0F);
		head.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 101, 238, -21.0F, -1.0F, -5.0F, 15, 1, 10, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone3, 0.0F, -0.6109F, 0.0F);
		head.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 198, 59, -7.0F, -1.0F, 6.0F, 12, 1, 14, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone4, 0.0F, 0.6109F, 0.0F);
		head.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 197, 37, -5.0F, -1.0F, 6.0F, 12, 1, 14, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone2, 0.0F, 0.5236F, 0.0F);
		head.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 200, 18, 6.0F, -1.0F, -5.0F, 15, 1, 10, 0.0F, false));

		pult = new ModelRenderer(this);
		pult.setRotationPoint(0.0F, -27.0F, 0.0F);
		setRotationAngle(pult, -0.9599F, 0.0F, 0.0F);
		head.addChild(pult);
		pult.cubeList.add(new ModelBox(pult, 151, 212, -1.0F, -18.0F, -1.0F, 2, 20, 2, 0.0F, false));

		pult2 = new ModelRenderer(this);
		pult2.setRotationPoint(0.0F, -17.6073F, 0.2456F);
		setRotationAngle(pult2, 0.5236F, 0.0F, 0.0F);
		pult.addChild(pult2);
		pult2.cubeList.add(new ModelBox(pult2, 131, 5, -1.0F, -1.0F, -1.0F, 2, 2, 28, 0.0F, false));

		basket = new ModelRenderer(this);
		basket.setRotationPoint(0.0F, -33.3617F, 26.8528F);
		setRotationAngle(basket, -0.5236F, 0.0F, 0.0F);
		pult.addChild(basket);
		basket.cubeList.add(new ModelBox(basket, 93, 3, -6.0F, -10.0F, 0.0F, 12, 12, 2, 0.0F, false));
		basket.cubeList.add(new ModelBox(basket, 97, 30, -8.0F, -10.0F, -3.0F, 2, 12, 3, 0.0F, false));
		basket.cubeList.add(new ModelBox(basket, 120, 46, 6.0F, -10.0F, -3.0F, 2, 12, 3, 0.0F, false));
		basket.cubeList.add(new ModelBox(basket, 143, 49, -6.0F, 2.0F, -3.0F, 12, 2, 3, 0.0F, false));
		basket.cubeList.add(new ModelBox(basket, 142, 63, -6.0F, -12.019F, -3.4358F, 12, 2, 4, 0.0F, false));
		basket.cubeList.add(new ModelBox(basket, 135, 76, -6.0F, -10.0F, -9.0F, 12, 12, 9, 0.0F, false));
		basket.cubeList.add(new ModelBox(basket, 127, 112, -3.0F, -7.0F, -9.0F, 6, 6, 9, 0.0F, false));
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
		EntityKernelPult entity=(EntityKernelPult) entityIn;
		int time=entity.getAttackTime();
		if(entity.getCanAttackNow()) {
			pult.rotateAngleX=-1+1.2f*MathHelper.sin( (float) (time*Math.PI/20f));
		}
		else{
			
			pult.rotateAngleX=-1+0.12f*MathHelper.sin( (float) (time*Math.PI/20f));
		}
	}
}
package com.hungteen.pvzmod.model.entities.plants.common;

//Made with Blockbench
//Paste this code into your mod.

import org.lwjgl.opengl.GL11;

import com.hungteen.pvzmod.entities.plants.common.EntityCabbagePult;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class ModelCabbagePult extends ModelBase {
	private final ModelRenderer head;
	private final ModelRenderer pult;
	private final ModelRenderer pult2;
	private final ModelRenderer basket;
	private final ModelRenderer leave;

	private float attackTime;
	private boolean isAttack;
	private boolean AttackNow;
	private float restTime;
	private boolean isOut;
	
	public ModelCabbagePult() {
		textureWidth = 256;
		textureHeight = 256;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 24.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 4, 6, -13.0F, -25.0F, -13.0F, 26, 24, 26, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 53, 226, -18.0F, -1.0F, -18.0F, 10, 1, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 101, 238, -20.0F, -1.0F, -5.0F, 14, 1, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 192, 236, 8.0F, -1.0F, -18.0F, 10, 1, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 203, 11, -18.0F, -1.0F, 8.0F, 10, 1, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 171, 220, 8.0F, -1.0F, 8.0F, 10, 1, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 112, 219, -5.0F, -1.0F, -20.0F, 10, 1, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 193, 32, -5.0F, -1.0F, 6.0F, 10, 1, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 150, 191, 6.0F, -1.0F, -5.0F, 14, 1, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 100, 191, -18.0F, -1.0F, -18.0F, 10, 1, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 176, 162, -10.0F, -26.0F, -10.0F, 20, 1, 20, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 203, 140, -6.0F, -27.0F, -6.0F, 12, 1, 12, 0.0F, false));

		pult = new ModelRenderer(this);
		pult.setRotationPoint(0.0F, -28.0F, 0.0F);
		setRotationAngle(pult, -1.0F, 0.0F, 0.0F);
		head.addChild(pult);
		pult.cubeList.add(new ModelBox(pult, 240, 108, -1.0F, -19.0F, -1.0F, 2, 21, 2, 0.0F, false));

		pult2 = new ModelRenderer(this);
		pult2.setRotationPoint(0.0F, -19.0F, 0.0F);
		setRotationAngle(pult2, 0.5236F, 0.0F, 0.0F);
		pult.addChild(pult2);
		pult2.cubeList.add(new ModelBox(pult2, 110, 151, -1.0F, -1.0F, -1.0F, 2, 2, 29, 0.0F, false));

		basket = new ModelRenderer(this);
		basket.setRotationPoint(0.0F, -35.0F, 28.0F);
		setRotationAngle(basket, -0.5236F, 0.0F, 0.0F);
		pult.addChild(basket);
		basket.cubeList.add(new ModelBox(basket, 56, 182, -9.0F, -15.0F, 0.0F, 17, 17, 2, 0.0F, false));
		basket.cubeList.add(new ModelBox(basket, 31, 180, -11.0F, -15.0F, -5.0F, 2, 17, 5, 0.0F, false));
		basket.cubeList.add(new ModelBox(basket, 6, 203, 8.0F, -15.0F, -5.0F, 2, 17, 5, 0.0F, false));
		basket.cubeList.add(new ModelBox(basket, 3, 166, -10.0F, 2.0F, -5.0F, 19, 2, 5, 0.0F, false));
		basket.cubeList.add(new ModelBox(basket, 63, 164, -10.0F, -17.0F, -5.0F, 19, 2, 5, 0.0F, false));
		basket.cubeList.add(new ModelBox(basket, 144, 115, -9.0F, -15.0F, -14.0F, 17, 17, 13, 0.0F, false));

		leave = new ModelRenderer(this);
		leave.setRotationPoint(0.0F, -4.0F, 0.0F);
		setRotationAngle(leave, 0.3491F, 0.0F, 0.0F);
		leave.cubeList.add(new ModelBox(leave, 88, 136, -15.0F, 11.0F, -8.0F, 2, 1, 18, 0.0F, false));
		leave.cubeList.add(new ModelBox(leave, 12, 152, -15.0F, 11.0F, 10.0F, 30, 1, 2, 0.0F, false));
		leave.cubeList.add(new ModelBox(leave, 51, 125, 13.0F, 11.0F, -8.0F, 2, 1, 18, 0.0F, false));
		leave.cubeList.add(new ModelBox(leave, 104, 124, -5.0F, -4.0F, -12.0F, 10, 3, 1, 0.0F, false));
		
		this.AttackNow=false;
		this.isOut=false;
		this.attackTime=-1;
		this.restTime=-1;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		head.render(f5);
		leave.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {	
		EntityCabbagePult entity=(EntityCabbagePult) entityIn;
		int time=entity.getAttackTime();
		if(entity.getCanAttackNow()) {
			pult.rotateAngleX=-1+1.2f*MathHelper.sin( (float) (time*Math.PI/20f));
		}
		else{
			
			pult.rotateAngleX=-1+0.12f*MathHelper.sin( (float) (time*Math.PI/20f));
		}
	}
}
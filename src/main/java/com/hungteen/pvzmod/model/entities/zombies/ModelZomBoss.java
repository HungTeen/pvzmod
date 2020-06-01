package com.hungteen.pvzmod.model.entities.zombies;

import com.hungteen.pvzmod.entities.zombies.EntityZomBoss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelZomBoss extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer left_leg;
	private final ModelRenderer left_shoo;
	private final ModelRenderer right_leg;
	private final ModelRenderer right_shoo;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer pipe1;
	private final ModelRenderer pipe2;
	private final ModelRenderer head;
	private final ModelRenderer mouse;
	private final ModelRenderer lace;
	private final ModelRenderer left_arm;
	private final ModelRenderer right_arm;

	public ModelZomBoss() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(6.0F, -41.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 155, 211, -5.0F, 0.0F, -3.0F, 10, 33, 10, 0.0F, false));
		left_leg.cubeList.add(new ModelBox(left_leg, 202, 220, -5.0F, 12.0F, -4.0F, 10, 4, 1, 0.0F, false));

		left_shoo = new ModelRenderer(this);
		left_shoo.setRotationPoint(-6.0F, 41.0F, 0.0F);
		left_leg.addChild(left_shoo);
		left_shoo.cubeList.add(new ModelBox(left_shoo, 198, 230, 0.0F, -8.0F, -8.0F, 12, 8, 16, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-7.0F, -41.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 154, 164, -4.0F, 0.0F, -3.0F, 10, 33, 10, 0.0F, false));
		right_leg.cubeList.add(new ModelBox(right_leg, 230, 220, -4.0F, 12.0F, -4.0F, 10, 4, 1, 0.0F, false));

		right_shoo = new ModelRenderer(this);
		right_shoo.setRotationPoint(7.0F, 41.0F, 0.0F);
		right_leg.addChild(right_shoo);
		right_shoo.cubeList.add(new ModelBox(right_shoo, 198, 187, -12.0F, -8.0F, -8.0F, 12, 8, 16, 0.0F, false));

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, -41.0F, 0.0F);
		total.addChild(up);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.cubeList.add(new ModelBox(body, 186, 2, -11.0F, -40.0F, -4.0F, 22, 40, 12, 0.0F, false));

		pipe1 = new ModelRenderer(this);
		pipe1.setRotationPoint(9.0F, -39.0F, 6.0F);
		setRotationAngle(pipe1, -0.6109F, 0.0F, 0.0F);
		body.addChild(pipe1);
		pipe1.cubeList.add(new ModelBox(pipe1, 200, 170, -2.0F, -2.0F, 0.0F, 2, 2, 10, 0.0F, false));
		pipe1.cubeList.add(new ModelBox(pipe1, 245, 166, -2.0F, -2.0F, 10.0F, 2, 16, 2, 0.0F, false));

		pipe2 = new ModelRenderer(this);
		pipe2.setRotationPoint(9.0F, -39.0F, 6.0F);
		setRotationAngle(pipe2, -0.6109F, 0.0F, 0.0F);
		body.addChild(pipe2);
		pipe2.cubeList.add(new ModelBox(pipe2, 226, 150, -18.0F, -2.0F, 0.0F, 2, 2, 10, 0.0F, false));
		pipe2.cubeList.add(new ModelBox(pipe2, 210, 146, -18.0F, -2.0F, 10.0F, 2, 16, 2, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -40.0F, -3.0F);
		up.addChild(head);
		head.cubeList.add(new ModelBox(head, 188, 58, -8.0F, -9.0F, -16.0F, 16, 13, 16, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 195, 118, 2.0F, -13.0F, -13.0F, 2, 4, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 195, 134, -4.0F, -13.0F, -13.0F, 2, 4, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 172, 97, 8.0F, -4.0F, -9.0F, 1, 1, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 171, 77, 9.0F, -5.0F, -10.0F, 2, 3, 3, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 176, 103, -9.0F, -4.0F, -9.0F, 1, 1, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 154, 98, -11.0F, -5.0F, -10.0F, 2, 3, 3, 0.0F, false));

		mouse = new ModelRenderer(this);
		mouse.setRotationPoint(0.0F, 6.0F, -1.0F);
		head.addChild(mouse);
		mouse.cubeList.add(new ModelBox(mouse, 186, 92, -8.0F, 0.0F, -15.0F, 16, 3, 16, 0.0F, false));
		mouse.cubeList.add(new ModelBox(mouse, 134, 6, -8.0F, -2.0F, -3.0F, 16, 2, 4, 0.0F, false));
		mouse.cubeList.add(new ModelBox(mouse, 0, 0, -1.0F, -1.0F, -15.0F, 1, 1, 1, 0.0F, false));
		mouse.cubeList.add(new ModelBox(mouse, 0, 0, 1.0F, -2.0F, -15.0F, 1, 1, 1, 0.0F, false));
		mouse.cubeList.add(new ModelBox(mouse, 0, 0, 4.0F, -2.0F, -15.0F, 1, 1, 1, 0.0F, false));
		mouse.cubeList.add(new ModelBox(mouse, 0, 0, -2.0F, -2.0F, -15.0F, 1, 1, 1, 0.0F, false));
		mouse.cubeList.add(new ModelBox(mouse, 0, 0, -5.0F, -2.0F, -15.0F, 1, 1, 1, 0.0F, false));
		mouse.cubeList.add(new ModelBox(mouse, 0, 0, -7.0F, -2.0F, -15.0F, 1, 1, 1, 0.0F, false));
		mouse.cubeList.add(new ModelBox(mouse, 0, 0, -8.0F, -2.0F, -13.0F, 1, 1, 1, 0.0F, false));
		mouse.cubeList.add(new ModelBox(mouse, 0, 0, -8.0F, -2.0F, -9.0F, 1, 1, 1, 0.0F, false));
		mouse.cubeList.add(new ModelBox(mouse, 0, 0, -8.0F, -2.0F, -6.0F, 1, 1, 1, 0.0F, false));
		mouse.cubeList.add(new ModelBox(mouse, 0, 0, 7.0F, -2.0F, -7.0F, 1, 1, 1, 0.0F, false));
		mouse.cubeList.add(new ModelBox(mouse, 0, 0, 7.0F, -2.0F, -12.0F, 1, 1, 1, 0.0F, false));
		mouse.cubeList.add(new ModelBox(mouse, 0, 0, -1.0F, -1.0F, -15.0F, 1, 1, 1, 0.0F, false));
		mouse.cubeList.add(new ModelBox(mouse, 0, 0, 2.0F, -1.0F, -15.0F, 1, 1, 1, 0.0F, false));
		mouse.cubeList.add(new ModelBox(mouse, 0, 0, 6.0F, -1.0F, -15.0F, 1, 1, 1, 0.0F, false));
		mouse.cubeList.add(new ModelBox(mouse, 0, 0, -5.0F, -1.0F, -15.0F, 1, 1, 1, 0.0F, false));
		mouse.cubeList.add(new ModelBox(mouse, 0, 0, -8.0F, -1.0F, -15.0F, 1, 1, 1, 0.0F, false));
		mouse.cubeList.add(new ModelBox(mouse, 0, 0, -8.0F, -1.0F, -11.0F, 1, 1, 1, 0.0F, false));
		mouse.cubeList.add(new ModelBox(mouse, 0, 0, -8.0F, -1.0F, -8.0F, 1, 1, 1, 0.0F, false));
		mouse.cubeList.add(new ModelBox(mouse, 0, 0, 7.0F, -1.0F, -12.0F, 1, 1, 1, 0.0F, false));
		mouse.cubeList.add(new ModelBox(mouse, 0, 0, 7.0F, -1.0F, -8.0F, 1, 1, 1, 0.0F, false));
		mouse.cubeList.add(new ModelBox(mouse, 0, 0, 7.0F, -1.0F, -5.0F, 1, 1, 1, 0.0F, false));

		lace = new ModelRenderer(this);
		lace.setRotationPoint(0.0F, 0.0F, 2.0F);
		setRotationAngle(lace, 1.1345F, 0.0F, 0.0F);
		head.addChild(lace);
		lace.cubeList.add(new ModelBox(lace, 151, 151, -11.0F, -3.0F, 0.0F, 22, 3, 3, 0.0F, false));
		lace.cubeList.add(new ModelBox(lace, 155, 126, -11.0F, -3.0F, -11.0F, 3, 3, 11, 0.0F, false));
		lace.cubeList.add(new ModelBox(lace, 152, 108, 8.0F, -3.0F, -11.0F, 3, 3, 11, 0.0F, false));
		lace.cubeList.add(new ModelBox(lace, 102, 247, -11.0F, -3.0F, -14.0F, 22, 3, 3, 0.0F, false));

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(15.0F, -35.0F, 2.0F);
		up.addChild(left_arm);
		left_arm.cubeList.add(new ModelBox(left_arm, 108, 180, -4.0F, -5.0F, -5.0F, 10, 48, 10, 0.0F, false));

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(-16.0F, -35.0F, 2.0F);
		up.addChild(right_arm);
		right_arm.cubeList.add(new ModelBox(right_arm, 106, 112, -5.0F, -5.0F, -5.0F, 10, 48, 10, 0.0F, false));
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
		if(!(entityIn instanceof EntityZomBoss)) return ;
		EntityZomBoss boss=(EntityZomBoss) entityIn;
		if(boss.getAttackTime()>0) {//·¢Éä»ðÇò
			int t=boss.getAttackTick();
			int time=boss.getAttackTime();
			this.mouse.rotateAngleX=0.5f*time/t;
		}
		else {
			this.mouse.rotateAngleX=0;
		}
		if(boss.getAttackTarget()==null) {
			this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
	        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
	        this.right_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	        this.left_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		}
	}
}
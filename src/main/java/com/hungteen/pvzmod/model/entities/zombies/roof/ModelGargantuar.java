package com.hungteen.pvzmod.model.entities.zombies.roof;

import com.hungteen.pvzmod.entities.zombies.roof.EntityGargantuar;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelGargantuar extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer up;
	private final ModelRenderer back;
	private final ModelRenderer head;
	private final ModelRenderer bone3;
	private final ModelRenderer right_arm;
	private final ModelRenderer left_arm;
	private final ModelRenderer body;

	public ModelGargantuar() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(7.0F, -39.0F, 1.0F);
		total.addChild(left_leg);
		left_leg.cubeList.add(new ModelBox(left_leg, 199, 235, -6.0F, 36.0F, -9.0F, 12, 3, 16, 0.0F, false));
		left_leg.cubeList.add(new ModelBox(left_leg, 3, 205, -5.0F, -4.0F, -5.0F, 10, 40, 10, 0.0F, false));

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-8.0F, -39.0F, 1.0F);
		total.addChild(right_leg);
		right_leg.cubeList.add(new ModelBox(right_leg, 198, 211, -5.0F, 36.0F, -9.0F, 12, 3, 16, 0.0F, false));
		right_leg.cubeList.add(new ModelBox(right_leg, 47, 204, -4.0F, -4.0F, -5.0F, 10, 40, 10, 0.0F, false));

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, -39.0F, 0.0F);
		total.addChild(up);

		back = new ModelRenderer(this);
		back.setRotationPoint(0.0F, -25.0F, 23.0F);
		up.addChild(back);
		back.cubeList.add(new ModelBox(back, 93, 236, -8.0F, -15.0F, -10.0F, 16, 17, 1, 0.0F, false));
		back.cubeList.add(new ModelBox(back, 186, 186, -8.0F, 2.0F, -10.0F, 16, 1, 17, 0.0F, false));
		back.cubeList.add(new ModelBox(back, 5, 167, 8.0F, -15.0F, -10.0F, 1, 17, 18, 0.0F, false));
		back.cubeList.add(new ModelBox(back, 50, 184, -8.0F, -15.0F, 7.0F, 16, 17, 1, 0.0F, false));
		back.cubeList.add(new ModelBox(back, 94, 192, -9.0F, -15.0F, -10.0F, 1, 17, 18, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -40.0F, -11.0F);
		up.addChild(head);
		head.cubeList.add(new ModelBox(head, 192, 148, -8.0F, -8.0F, -14.0F, 16, 17, 14, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 1.0F);
		setRotationAngle(bone3, 1.2217F, 0.0F, 0.0F);
		head.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 4, 160, -8.0F, -1.0F, 0.0F, 16, 1, 1, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 4, 152, -9.0F, -1.0F, -12.0F, 18, 1, 1, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 49, 158, -9.0F, -1.0F, -11.0F, 1, 1, 12, 0.0F, false));
		bone3.cubeList.add(new ModelBox(bone3, 47, 140, 8.0F, -1.0F, -11.0F, 1, 1, 12, 0.0F, false));

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(-19.0F, -34.0F, 1.0F);
		up.addChild(right_arm);
		right_arm.cubeList.add(new ModelBox(right_arm, 100, 25, -6.0F, -6.0F, -6.0F, 12, 52, 12, 0.0F, false));

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(19.0F, -34.0F, 1.0F);
		up.addChild(left_arm);
		left_arm.cubeList.add(new ModelBox(left_arm, 207, 79, -6.0F, -6.0F, -6.0F, 12, 52, 12, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 39.0F, 0.0F);
		up.addChild(body);
		body.cubeList.add(new ModelBox(body, 153, 4, -13.0F, -79.0F, -11.0F, 26, 40, 24, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 83, 122, -6.0F, -80.0F, -10.0F, 1, 1, 24, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 146, 123, 5.0F, -80.0F, -10.0F, 1, 1, 24, 0.0F, false));
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
		
        if(entityIn instanceof EntityGargantuar) {
        	EntityGargantuar entity=(EntityGargantuar) entityIn;
        	if(entity.getAttackTime()==0&&entity.getThrowTime()==0) {//没攻击就原装
        		this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount/2;
                this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount/2;
                this.right_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
                this.left_arm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        	}else {
        		this.right_leg.rotateAngleX=0f;
        		this.left_leg.rotateAngleX=0f;
        		
        		if(entity.getThrowTime()>0) {
        			float sita=3.14159f/entity.getThrowTick();
        			this.left_arm.rotateAngleX=0;
        			this.right_arm.rotateAngleX=-MathHelper.sin(sita*entity.getThrowTime())*4.5f;
        		}
        		else {
                    float sita=3.14159f/entity.getAttackTick();
//                if(entity.getAttackTime()<=entity.getAttackTick()/2) {//上升
//                	this.up.rotateAngleX=-MathHelper.sin(sita*entity.getAttackTime()*3f/2f)*0.3f;
//                }
//                else {
//                	int time=entity.getAttackTime()-entity.getAttackTick()/2;
//                	this.up.rotateAngleX=MathHelper.sin(sita*time*3f/2f)*0.7f;
//                }
//                System.out.println(this.up.rotateAngleX+" "+entity.getAttackTime());
                    this.right_arm.rotateAngleX = 0f;
                    this.left_arm.rotateAngleX=-MathHelper.sin(sita*entity.getAttackTime())*1.5f;
        		}
        	}
        }
	}
	
	public ModelRenderer getArm()
	{
		return this.left_arm;
	}
}
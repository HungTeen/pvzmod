package com.hungteen.pvzmod.model.entities.plants.ice;

import com.hungteen.pvzmod.entities.plants.common.EntityMelonPult;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelWinterMelon extends ModelBase {
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
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer bone13;
	private final ModelRenderer bone14;
	private final ModelRenderer bone15;
	private final ModelRenderer bone16;
	private final ModelRenderer bone17;
	private final ModelRenderer bone18;
	private final ModelRenderer bone19;
	private final ModelRenderer bone20;
	private final ModelRenderer bone21;
	private final ModelRenderer bone22;
	private final ModelRenderer bone23;
	private final ModelRenderer bone24;
	private final ModelRenderer bone25;
	private final ModelRenderer bone26;
	private final ModelRenderer bone27;

	public ModelWinterMelon() {
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
		head.cubeList.add(new ModelBox(head, 40, 230, -13.0F, -1.0F, -12.0F, 26, 1, 24, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 56, 206, -11.0F, 0.0F, -10.0F, 22, 1, 20, 0.0F, false));

		leg = new ModelRenderer(this);
		leg.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(leg);

		leaf1 = new ModelRenderer(this);
		leaf1.setRotationPoint(0.0F, 0.0F, 0.0F);
		leg.addChild(leaf1);
		leaf1.cubeList.add(new ModelBox(leaf1, 88, 54, -5.0F, 1.0F, -20.0F, 10, 1, 20, 0.0F, false));

		leaf2 = new ModelRenderer(this);
		leaf2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(leaf2, 0.0F, -0.4363F, 0.0F);
		leg.addChild(leaf2);
		leaf2.cubeList.add(new ModelBox(leaf2, 94, 80, -20.0F, 1.0F, -5.0F, 14, 1, 10, 0.0F, false));

		leaf3 = new ModelRenderer(this);
		leaf3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(leaf3, 0.0F, 0.4363F, 0.0F);
		leg.addChild(leaf3);
		leaf3.cubeList.add(new ModelBox(leaf3, 93, 96, 6.0F, 1.0F, -5.0F, 14, 1, 10, 0.0F, false));

		leaf4 = new ModelRenderer(this);
		leaf4.setRotationPoint(0.0F, 0.0F, 0.0F);
		leg.addChild(leaf4);
		leaf4.cubeList.add(new ModelBox(leaf4, 79, 111, -5.0F, 1.0F, 0.0F, 10, 1, 20, 0.0F, false));

		leaf5 = new ModelRenderer(this);
		leaf5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(leaf5, 0.0F, 0.4363F, 0.0F);
		leg.addChild(leaf5);
		leaf5.cubeList.add(new ModelBox(leaf5, 87, 138, -20.0F, 1.0F, -5.0F, 14, 1, 10, 0.0F, false));

		leaf6 = new ModelRenderer(this);
		leaf6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(leaf6, 0.0F, -0.4363F, 0.0F);
		leg.addChild(leaf6);
		leaf6.cubeList.add(new ModelBox(leaf6, 90, 154, 6.0F, 1.0F, -5.0F, 14, 1, 10, 0.0F, false));

		pult1 = new ModelRenderer(this);
		pult1.setRotationPoint(0.0F, -30.0F, 12.0F);
		setRotationAngle(pult1, -1.1345F, 0.0F, 0.0F);
		total.addChild(pult1);
		pult1.cubeList.add(new ModelBox(pult1, 137, 185, -1.0F, -19.0F, -1.0F, 2, 23, 2, 0.0F, false));
		pult1.cubeList.add(new ModelBox(pult1, 56, 144, -2.0F, -7.0F, -2.0F, 4, 1, 4, 0.0F, false));
		pult1.cubeList.add(new ModelBox(pult1, 30, 145, -2.0F, -14.0F, -2.0F, 4, 1, 4, 0.0F, false));

		pult2 = new ModelRenderer(this);
		pult2.setRotationPoint(0.0F, -19.0F, 0.0F);
		setRotationAngle(pult2, 0.4363F, 0.0F, 0.0F);
		pult1.addChild(pult2);
		pult2.cubeList.add(new ModelBox(pult2, 37, 6, -1.0F, -1.0F, -1.0F, 2, 2, 24, 0.0F, false));
		pult2.cubeList.add(new ModelBox(pult2, 72, 41, -2.0F, -2.0F, 5.0F, 4, 4, 1, 0.0F, false));
		pult2.cubeList.add(new ModelBox(pult2, 69, 55, -2.0F, -2.0F, 12.0F, 4, 4, 1, 0.0F, false));

		basket = new ModelRenderer(this);
		basket.setRotationPoint(0.0F, -32.0F, 23.0F);
		setRotationAngle(basket, -0.3491F, 0.0F, 0.0F);
		pult1.addChild(basket);
		basket.cubeList.add(new ModelBox(basket, 18, 38, -9.0F, -16.0514F, 0.4938F, 18, 18, 2, 0.0F, false));
		basket.cubeList.add(new ModelBox(basket, 16, 5, -10.0F, -15.0514F, -5.5062F, 1, 17, 6, 0.0F, false));
		basket.cubeList.add(new ModelBox(basket, 58, 67, 9.0F, -15.0514F, -5.5062F, 1, 17, 6, 0.0F, false));
		basket.cubeList.add(new ModelBox(basket, 2, 64, -9.0F, 1.9486F, -4.5062F, 18, 2, 5, 0.0F, false));
		basket.cubeList.add(new ModelBox(basket, 2, 77, -9.0F, -17.0514F, -6.5062F, 18, 2, 7, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -6.0F, 2.0F);
		setRotationAngle(bone, 0.6109F, 0.0F, 0.0F);
		basket.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 9, 98, -8.0F, -23.0514F, -14.5062F, 16, 25, 13, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 2.0F);
		basket.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, -1.0F, -1.0F, 0.0F, 2, 2, 6, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 6.0F);
		setRotationAngle(bone3, 0.0F, -0.7854F, 0.0F);
		bone2.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 0, 0, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(-7.0F, 0.0F, 2.0F);
		basket.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 0, 0, -1.0F, -1.0F, 0.0F, 2, 2, 6, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 0.0F, 6.0F);
		setRotationAngle(bone5, 0.0F, -0.7854F, 0.0F);
		bone4.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 0, 0, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 0.0F, 2.0F);
		basket.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 0, 0, 6.0F, -1.0F, 0.0F, 2, 2, 6, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, 0.0F, 6.0F);
		setRotationAngle(bone7, 0.0F, -0.7854F, 0.0F);
		bone6.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 0, 0, 3.9497F, -1.0F, -5.9497F, 2, 2, 2, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, 0.0F, 2.0F);
		basket.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 0, 0, -1.0F, -15.0F, 0.0F, 2, 2, 6, 0.0F, false));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, 0.0F, 6.0F);
		setRotationAngle(bone9, 0.0F, -0.7854F, 0.0F);
		bone8.addChild(bone9);
		bone9.cubeList.add(new ModelBox(bone9, 0, 0, -1.0F, -15.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(1.0F, 0.0F, 2.0F);
		basket.addChild(bone10);
		bone10.cubeList.add(new ModelBox(bone10, 0, 0, -9.0F, -15.0F, 0.0F, 2, 2, 6, 0.0F, false));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(0.0F, 0.0F, 6.0F);
		setRotationAngle(bone11, 0.0F, -0.7854F, 0.0F);
		bone10.addChild(bone11);
		bone11.cubeList.add(new ModelBox(bone11, 0, 0, -6.6569F, -15.0F, 4.6569F, 2, 2, 2, 0.0F, false));

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(0.0F, 0.0F, 2.0F);
		basket.addChild(bone12);
		bone12.cubeList.add(new ModelBox(bone12, 0, 0, 6.0F, -15.0F, 0.0F, 2, 2, 6, 0.0F, false));

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(0.0F, 0.0F, 6.0F);
		setRotationAngle(bone13, 0.0F, -0.7854F, 0.0F);
		bone12.addChild(bone13);
		bone13.cubeList.add(new ModelBox(bone13, 0, 0, 3.9497F, -15.0F, -5.9497F, 2, 2, 2, 0.0F, false));

		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(0.0F, 0.0F, 2.0F);
		basket.addChild(bone14);
		bone14.cubeList.add(new ModelBox(bone14, 0, 0, 6.0F, -8.0F, 0.0F, 2, 2, 6, 0.0F, false));

		bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(0.0F, 0.0F, 6.0F);
		setRotationAngle(bone15, 0.0F, -0.7854F, 0.0F);
		bone14.addChild(bone15);
		bone15.cubeList.add(new ModelBox(bone15, 0, 0, 3.9497F, -8.0F, -5.9497F, 2, 2, 2, 0.0F, false));

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(-7.0F, -7.0F, 2.0F);
		basket.addChild(bone16);
		bone16.cubeList.add(new ModelBox(bone16, 0, 0, -1.0F, -1.0F, 0.0F, 2, 2, 6, 0.0F, false));

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(0.0F, 0.0F, 6.0F);
		setRotationAngle(bone17, 0.0F, -0.7854F, 0.0F);
		bone16.addChild(bone17);
		bone17.cubeList.add(new ModelBox(bone17, 0, 0, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(0.0F, 0.0F, 2.0F);
		basket.addChild(bone18);
		bone18.cubeList.add(new ModelBox(bone18, 0, 0, -1.0F, -8.0F, 0.0F, 2, 2, 6, 0.0F, false));

		bone19 = new ModelRenderer(this);
		bone19.setRotationPoint(0.0F, 0.0F, 6.0F);
		setRotationAngle(bone19, 0.0F, -0.7854F, 0.0F);
		bone18.addChild(bone19);
		bone19.cubeList.add(new ModelBox(bone19, 0, 0, -1.0F, -8.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone20 = new ModelRenderer(this);
		bone20.setRotationPoint(0.0F, 0.0F, 2.0F);
		basket.addChild(bone20);
		bone20.cubeList.add(new ModelBox(bone20, 0, 0, -5.0F, -4.0F, 0.0F, 2, 2, 6, 0.0F, false));

		bone21 = new ModelRenderer(this);
		bone21.setRotationPoint(0.0F, 0.0F, 6.0F);
		setRotationAngle(bone21, 0.0F, -0.7854F, 0.0F);
		bone20.addChild(bone21);
		bone21.cubeList.add(new ModelBox(bone21, 0, 0, -3.8284F, -4.0F, 1.8284F, 2, 2, 2, 0.0F, false));

		bone22 = new ModelRenderer(this);
		bone22.setRotationPoint(0.0F, 0.0F, 2.0F);
		basket.addChild(bone22);
		bone22.cubeList.add(new ModelBox(bone22, 0, 0, 3.0F, -4.0F, 0.0F, 2, 2, 6, 0.0F, false));

		bone23 = new ModelRenderer(this);
		bone23.setRotationPoint(0.0F, 0.0F, 6.0F);
		setRotationAngle(bone23, 0.0F, -0.7854F, 0.0F);
		bone22.addChild(bone23);
		bone23.cubeList.add(new ModelBox(bone23, 0, 0, 1.8284F, -4.0F, -3.8284F, 2, 2, 2, 0.0F, false));

		bone24 = new ModelRenderer(this);
		bone24.setRotationPoint(0.0F, 0.0F, 2.0F);
		basket.addChild(bone24);
		bone24.cubeList.add(new ModelBox(bone24, 0, 0, -5.0F, -12.0F, 0.0F, 2, 2, 6, 0.0F, false));

		bone25 = new ModelRenderer(this);
		bone25.setRotationPoint(0.0F, 0.0F, 6.0F);
		setRotationAngle(bone25, 0.0F, -0.7854F, 0.0F);
		bone24.addChild(bone25);
		bone25.cubeList.add(new ModelBox(bone25, 0, 0, -3.8284F, -12.0F, 1.8284F, 2, 2, 2, 0.0F, false));

		bone26 = new ModelRenderer(this);
		bone26.setRotationPoint(0.0F, 0.0F, 2.0F);
		basket.addChild(bone26);
		bone26.cubeList.add(new ModelBox(bone26, 0, 0, 3.0F, -12.0F, 0.0F, 2, 2, 6, 0.0F, false));

		bone27 = new ModelRenderer(this);
		bone27.setRotationPoint(0.0F, 0.0F, 6.0F);
		setRotationAngle(bone27, 0.0F, -0.7854F, 0.0F);
		bone26.addChild(bone27);
		bone27.cubeList.add(new ModelBox(bone27, 0, 0, 1.8284F, -12.0F, -3.8284F, 2, 2, 2, 0.0F, false));
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
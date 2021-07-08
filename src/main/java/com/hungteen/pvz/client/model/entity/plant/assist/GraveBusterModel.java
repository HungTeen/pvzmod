package com.hungteen.pvz.client.model.entity.plant.assist;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.assist.GraveBusterEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class GraveBusterModel extends PVZPlantModel<GraveBusterEntity> {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer mouse;
	private final ModelRenderer front;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer m1;
	private final ModelRenderer m2;
	private final ModelRenderer back;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer m5;
	private final ModelRenderer m6;
	private final ModelRenderer left;
	private final ModelRenderer bone9;
	private final ModelRenderer m4;
	private final ModelRenderer m3;
	private final ModelRenderer bone12;
	private final ModelRenderer right;
	private final ModelRenderer bone13;
	private final ModelRenderer m7;
	private final ModelRenderer m8;
	private final ModelRenderer bone16;
	private final ModelRenderer head;
	private final ModelRenderer right_wine;
	private final ModelRenderer bone17;
	private final ModelRenderer bone18;
	private final ModelRenderer bone19;
	private final ModelRenderer bone20;
	private final ModelRenderer left_wine;
	private final ModelRenderer bone25;
	private final ModelRenderer bone26;
	private final ModelRenderer bone31;
	private final ModelRenderer bone32;
	private final ModelRenderer back_wine;
	private final ModelRenderer bone21;
	private final ModelRenderer bone22;
	private final ModelRenderer bone29;
	private final ModelRenderer bone30;
	private final ModelRenderer front_wine;
	private final ModelRenderer bone24;
	private final ModelRenderer bone23;
	private final ModelRenderer bone28;
	private final ModelRenderer bone27;
	private final ModelRenderer bone33;
	private final ModelRenderer bone34;
	private final ModelRenderer bone35;
	private final ModelRenderer bone36;

	public GraveBusterModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 8.0F, 0.0F);
		total.addChild(body);
		body.texOffs(1, 99).addBox(-8.0F, -32.0F, -6.0F, 16.0F, 16.0F, 12.0F, 0.0F, false);
		body.texOffs(59, 110).addBox(-7.0F, -32.0F, -7.0F, 14.0F, 16.0F, 1.0F, 0.0F, false);
		body.texOffs(92, 110).addBox(-6.0F, -32.0F, 7.0F, 12.0F, 16.0F, 1.0F, 0.0F, false);
		body.texOffs(97, 91).addBox(-7.0F, -32.0F, 6.0F, 14.0F, 16.0F, 1.0F, 0.0F, false);
		body.texOffs(69, 91).addBox(-6.0F, -32.0F, -8.0F, 12.0F, 16.0F, 1.0F, 0.0F, false);

		mouse = new ModelRenderer(this);
		mouse.setPos(0.0F, 8.0F, 0.0F);
		total.addChild(mouse);
		

		front = new ModelRenderer(this);
		front.setPos(0.0F, -16.0F, -7.0F);
		mouse.addChild(front);
		

		bone = new ModelRenderer(this);
		bone.setPos(-5.0F, 0.0F, 0.0F);
		front.addChild(bone);
		bone.texOffs(58, 97).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(6.0F, 0.0F, 0.0F);
		front.addChild(bone2);
		bone2.texOffs(48, 99).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		m1 = new ModelRenderer(this);
		m1.setPos(-2.0F, 0.0F, 0.0F);
		front.addChild(m1);
		setRotationAngle(m1, -0.0873F, 0.0F, 0.0F);
		m1.texOffs(54, 84).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 8.0F, 2.0F, 0.0F, false);

		m2 = new ModelRenderer(this);
		m2.setPos(2.0F, 0.0F, 0.0F);
		front.addChild(m2);
		setRotationAngle(m2, 0.0873F, 0.0F, 0.0F);
		m2.texOffs(40, 84).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 8.0F, 2.0F, 0.0F, false);

		back = new ModelRenderer(this);
		back.setPos(0.0F, -16.0F, 7.0F);
		mouse.addChild(back);
		

		bone5 = new ModelRenderer(this);
		bone5.setPos(-5.0F, 0.0F, 0.0F);
		back.addChild(bone5);
		bone5.texOffs(29, 85).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(6.0F, 0.0F, 0.0F);
		back.addChild(bone6);
		bone6.texOffs(18, 84).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		m5 = new ModelRenderer(this);
		m5.setPos(-2.0F, 0.0F, 0.0F);
		back.addChild(m5);
		setRotationAngle(m5, 0.0873F, 0.0F, 0.0F);
		m5.texOffs(3, 83).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 8.0F, 2.0F, 0.0F, false);

		m6 = new ModelRenderer(this);
		m6.setPos(2.0F, 0.0F, 0.0F);
		back.addChild(m6);
		setRotationAngle(m6, -0.0873F, 0.0F, 0.0F);
		m6.texOffs(115, 77).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 8.0F, 2.0F, 0.0F, false);

		left = new ModelRenderer(this);
		left.setPos(-7.0F, -16.0F, 0.0F);
		mouse.addChild(left);
		

		bone9 = new ModelRenderer(this);
		bone9.setPos(0.0F, 0.0F, 5.0F);
		left.addChild(bone9);
		bone9.texOffs(104, 77).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		m4 = new ModelRenderer(this);
		m4.setPos(0.0F, 0.0F, 2.0F);
		left.addChild(m4);
		setRotationAngle(m4, 0.0F, 0.0F, -0.0873F);
		m4.texOffs(89, 76).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 8.0F, 4.0F, 0.0F, false);

		m3 = new ModelRenderer(this);
		m3.setPos(0.0F, 0.0F, -2.0F);
		left.addChild(m3);
		setRotationAngle(m3, 0.0F, 0.0F, 0.0873F);
		m3.texOffs(74, 76).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 8.0F, 4.0F, 0.0F, false);

		bone12 = new ModelRenderer(this);
		bone12.setPos(0.0F, 0.0F, -5.0F);
		left.addChild(bone12);
		bone12.texOffs(118, 65).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		right = new ModelRenderer(this);
		right.setPos(7.0F, -16.0F, 0.0F);
		mouse.addChild(right);
		

		bone13 = new ModelRenderer(this);
		bone13.setPos(0.0F, 0.0F, 5.0F);
		right.addChild(bone13);
		bone13.texOffs(107, 64).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		m7 = new ModelRenderer(this);
		m7.setPos(0.0F, 0.0F, 2.0F);
		right.addChild(m7);
		setRotationAngle(m7, 0.0F, 0.0F, -0.0873F);
		m7.texOffs(92, 62).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 8.0F, 4.0F, 0.0F, false);

		m8 = new ModelRenderer(this);
		m8.setPos(0.0F, 0.0F, -2.0F);
		right.addChild(m8);
		setRotationAngle(m8, 0.0F, 0.0F, 0.0873F);
		m8.texOffs(77, 62).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 8.0F, 4.0F, 0.0F, false);

		bone16 = new ModelRenderer(this);
		bone16.setPos(0.0F, 0.0F, -5.0F);
		right.addChild(bone16);
		bone16.texOffs(64, 68).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -24.0F, 0.0F);
		total.addChild(head);
		head.texOffs(73, 49).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		head.texOffs(84, 43).addBox(-6.0F, -1.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.texOffs(63, 50).addBox(-6.0F, -1.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.texOffs(56, 48).addBox(5.0F, -1.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.texOffs(48, 48).addBox(5.0F, -1.0F, 5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		right_wine = new ModelRenderer(this);
		right_wine.setPos(-8.0F, 0.0F, 0.0F);
		head.addChild(right_wine);
		right_wine.texOffs(30, 74).addBox(-2.0F, -3.0F, -3.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);

		bone17 = new ModelRenderer(this);
		bone17.setPos(1.0F, 0.0F, 5.0F);
		right_wine.addChild(bone17);
		setRotationAngle(bone17, -0.5236F, 1.0472F, 0.0F);
		bone17.texOffs(46, 73).addBox(0.0F, -1.0F, -4.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);

		bone18 = new ModelRenderer(this);
		bone18.setPos(1.0F, 0.0F, -5.0F);
		right_wine.addChild(bone18);
		setRotationAngle(bone18, 0.5236F, -1.0472F, 0.0F);
		bone18.texOffs(99, 38).addBox(0.0F, -1.0F, -2.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);

		bone19 = new ModelRenderer(this);
		bone19.setPos(-1.0F, -1.0F, -2.0F);
		right_wine.addChild(bone19);
		setRotationAngle(bone19, -0.5236F, -0.2618F, 0.0F);
		bone19.texOffs(15, 76).addBox(-3.0F, -1.0F, -3.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);

		bone20 = new ModelRenderer(this);
		bone20.setPos(-1.0F, -1.0F, 2.0F);
		right_wine.addChild(bone20);
		setRotationAngle(bone20, 0.5236F, 0.2618F, 0.0F);
		bone20.texOffs(3, 74).addBox(-3.0F, -1.0F, 0.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);

		left_wine = new ModelRenderer(this);
		left_wine.setPos(8.0F, 0.0F, 0.0F);
		head.addChild(left_wine);
		left_wine.texOffs(26, 64).addBox(1.0F, -3.0F, -3.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);

		bone25 = new ModelRenderer(this);
		bone25.setPos(-1.0F, 0.0F, -5.0F);
		left_wine.addChild(bone25);
		setRotationAngle(bone25, 0.5236F, 1.0472F, 0.0F);
		bone25.texOffs(61, 57).addBox(-1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);

		bone26 = new ModelRenderer(this);
		bone26.setPos(-1.0F, 0.0F, 5.0F);
		left_wine.addChild(bone26);
		setRotationAngle(bone26, -0.5236F, -1.0472F, 0.0F);
		bone26.texOffs(43, 64).addBox(-1.0F, -1.0F, -4.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);

		bone31 = new ModelRenderer(this);
		bone31.setPos(1.0F, -1.0F, -2.0F);
		left_wine.addChild(bone31);
		setRotationAngle(bone31, -0.5236F, 0.2618F, 0.0F);
		bone31.texOffs(12, 68).addBox(0.0F, -1.0F, -3.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);

		bone32 = new ModelRenderer(this);
		bone32.setPos(1.0F, -1.0F, 2.0F);
		left_wine.addChild(bone32);
		setRotationAngle(bone32, 0.5236F, -0.2618F, 0.0F);
		bone32.texOffs(3, 62).addBox(0.0F, -1.0F, 0.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);

		back_wine = new ModelRenderer(this);
		back_wine.setPos(0.0F, 0.0F, 6.0F);
		head.addChild(back_wine);
		setRotationAngle(back_wine, -0.2618F, 0.0F, 0.0F);
		

		bone21 = new ModelRenderer(this);
		bone21.setPos(-5.0F, 1.0F, -1.0F);
		back_wine.addChild(bone21);
		setRotationAngle(bone21, -0.2618F, 0.0F, 1.0472F);
		bone21.texOffs(123, 53).addBox(-1.0F, -7.0F, 0.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);

		bone22 = new ModelRenderer(this);
		bone22.setPos(5.0F, 1.0F, -1.0F);
		back_wine.addChild(bone22);
		setRotationAngle(bone22, -0.2618F, 0.0F, -1.0472F);
		bone22.texOffs(116, 54).addBox(0.0F, -7.0F, 0.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);

		bone29 = new ModelRenderer(this);
		bone29.setPos(0.0F, -2.0F, 1.0F);
		back_wine.addChild(bone29);
		setRotationAngle(bone29, 0.5236F, 0.0F, 0.0F);
		

		bone30 = new ModelRenderer(this);
		bone30.setPos(0.0F, -1.0F, 1.0F);
		bone29.addChild(bone30);
		setRotationAngle(bone30, 0.0F, 0.7854F, 0.0F);
		bone30.texOffs(105, 58).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		front_wine = new ModelRenderer(this);
		front_wine.setPos(0.0F, 0.0F, -6.0F);
		head.addChild(front_wine);
		setRotationAngle(front_wine, 0.2618F, 0.0F, 0.0F);
		

		bone24 = new ModelRenderer(this);
		bone24.setPos(5.0F, 1.0F, 1.0F);
		front_wine.addChild(bone24);
		setRotationAngle(bone24, 0.2618F, 0.0F, -1.0472F);
		bone24.texOffs(123, 43).addBox(0.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);

		bone23 = new ModelRenderer(this);
		bone23.setPos(-5.0F, 1.0F, 1.0F);
		front_wine.addChild(bone23);
		setRotationAngle(bone23, 0.2618F, 0.0F, 1.0472F);
		bone23.texOffs(116, 43).addBox(-1.0F, -7.0F, -1.0F, 1.0F, 7.0F, 1.0F, 0.0F, false);

		bone28 = new ModelRenderer(this);
		bone28.setPos(0.0F, -2.0F, -1.0F);
		front_wine.addChild(bone28);
		setRotationAngle(bone28, -0.5236F, 0.0F, 0.0F);
		

		bone27 = new ModelRenderer(this);
		bone27.setPos(0.0F, -1.0F, -1.0F);
		bone28.addChild(bone27);
		setRotationAngle(bone27, 0.0F, 0.7854F, 0.0F);
		bone27.texOffs(104, 50).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone33 = new ModelRenderer(this);
		bone33.setPos(-5.0F, 0.0F, 4.0F);
		head.addChild(bone33);
		setRotationAngle(bone33, -0.2618F, -0.7854F, 0.0F);
		bone33.texOffs(83, 50).addBox(0.0F, -1.0F, -7.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);

		bone34 = new ModelRenderer(this);
		bone34.setPos(5.0F, 0.0F, 4.0F);
		head.addChild(bone34);
		setRotationAngle(bone34, -0.2618F, 0.7854F, 0.0F);
		bone34.texOffs(40, 52).addBox(-1.0F, -1.0F, -7.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);

		bone35 = new ModelRenderer(this);
		bone35.setPos(-5.0F, 0.0F, -4.0F);
		head.addChild(bone35);
		setRotationAngle(bone35, 0.2618F, 0.7854F, 0.0F);
		bone35.texOffs(19, 51).addBox(0.0F, -1.0F, -1.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);

		bone36 = new ModelRenderer(this);
		bone36.setPos(5.0F, 0.0F, -4.0F);
		head.addChild(bone36);
		setRotationAngle(bone36, 0.2618F, -0.7854F, 0.0F);
		bone36.texOffs(3, 45).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(GraveBusterEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		final float angle = 0.15f;
		if(entity.isEatingTomb()) {
			int x = entity.getAttackTime();
			final int times = 7;
			float T = entity.getEatTombCD() * 1.0f / times;
			double w = 2 * Math.PI / T;
			this.m1.xRot = (float) - Math.cos(w * x) * angle;
			this.m2.xRot = (float) Math.cos(w * x) * angle;
			this.m3.zRot = (float) Math.cos(w * x) * angle;
			this.m4.zRot = (float) - Math.cos(w * x) * angle;
			this.m5.xRot = (float) Math.cos(w * x) * angle;
			this.m6.xRot = (float) - Math.cos(w * x) * angle;
			this.m7.zRot = (float) - Math.cos(w * x) * angle;
			this.m8.zRot = (float) Math.cos(w * x) * angle;
		}else {
			this.m1.xRot = -angle;
			this.m2.xRot = angle;
			this.m3.zRot = angle;
			this.m4.zRot = -angle;
			this.m5.xRot = angle;
			this.m6.xRot = -angle;
			this.m7.zRot = -angle;
			this.m8.zRot = angle;
		}
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<GraveBusterEntity> getPlantModel() {
		return this;
	}
}
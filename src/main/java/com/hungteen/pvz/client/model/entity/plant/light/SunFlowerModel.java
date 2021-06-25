package com.hungteen.pvz.client.model.entity.plant.light;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.light.SunFlowerEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class SunFlowerModel extends PVZPlantModel<SunFlowerEntity> {
	private final ModelRenderer total;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer HUABAN;
	private final ModelRenderer HUABAN2;
	private final ModelRenderer HUABAN3;
	private final ModelRenderer HUABAN4;
	private final ModelRenderer HUABAN5;
	private final ModelRenderer HUABAN6;
	private final ModelRenderer body;
	private final ModelRenderer cube_r1;
	private final ModelRenderer YEZI_4;
	private final ModelRenderer YEZI_3;
	private final ModelRenderer YEZI_1;
	private final ModelRenderer YEZI_2;

	public SunFlowerModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		left_hand = new ModelRenderer(this);
		left_hand.setPos(3.0F, -15.0F, 0.0F);
		total.addChild(left_hand);
		setRotationAngle(left_hand, 0.0F, 0.0F, -0.1745F);
		left_hand.texOffs(0, 0).addBox(1.0F, -1.0F, -1.0F, 6.0F, 3.0F, 2.0F, 0.0F, false);
		left_hand.texOffs(0, 0).addBox(-3.0912F, 0.0419F, 0.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-3.0F, -15.0F, 0.0F);
		total.addChild(right_hand);
		setRotationAngle(right_hand, 0.0F, 0.0F, 0.1745F);
		right_hand.texOffs(0, 0).addBox(-7.0F, -1.0F, -1.0F, 6.0F, 3.0F, 2.0F, 0.0F, false);
		right_hand.texOffs(0, 0).addBox(-1.0F, 0.0F, 0.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -32.0F, 0.0F);
		total.addChild(head);
		head.texOffs(73, 100).addBox(-11.0F, -15.0F, -5.99F, 22.0F, 21.0F, 4.0F, -0.02F, false);
		head.texOffs(0, 123).addBox(-5.0F, 5.9F, -6.0F, 10.0F, 1.0F, 4.0F, -0.02F, false);

		HUABAN = new ModelRenderer(this);
		HUABAN.setPos(0.0F, -3.0F, 0.5F);
		head.addChild(HUABAN);
		setRotationAngle(HUABAN, 0.0F, 0.0F, -0.2618F);
		HUABAN.texOffs(0, 62).addBox(-3.0F, -18.0F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN.texOffs(29, 63).addBox(-3.0F, 9.8564F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN.texOffs(42, 36).addBox(10.9282F, -4.0718F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN.texOffs(73, 73).addBox(-16.9282F, -4.0718F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);

		HUABAN2 = new ModelRenderer(this);
		HUABAN2.setPos(0.0F, 0.0F, 0.0F);
		HUABAN.addChild(HUABAN2);
		setRotationAngle(HUABAN2, 0.0F, 0.0F, -0.5236F);
		HUABAN2.texOffs(0, 62).addBox(-2.4641F, -17.8564F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN2.texOffs(29, 63).addBox(-2.4641F, 10.0F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN2.texOffs(42, 36).addBox(11.4641F, -3.9282F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN2.texOffs(73, 73).addBox(-16.3923F, -3.9282F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);

		HUABAN3 = new ModelRenderer(this);
		HUABAN3.setPos(0.0F, 0.0F, 0.0F);
		HUABAN2.addChild(HUABAN3);
		setRotationAngle(HUABAN3, 0.0F, 0.0F, -0.5236F);
		HUABAN3.texOffs(0, 62).addBox(-2.0718F, -17.4641F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN3.texOffs(29, 63).addBox(-2.0718F, 10.3923F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN3.texOffs(42, 36).addBox(11.8564F, -3.5359F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN3.texOffs(73, 73).addBox(-16.0F, -3.5359F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);

		HUABAN4 = new ModelRenderer(this);
		HUABAN4.setPos(0.0F, -3.0F, 0.0F);
		head.addChild(HUABAN4);
		HUABAN4.texOffs(0, 62).addBox(-4.0F, -16.0F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN4.texOffs(29, 63).addBox(-4.0F, 9.8564F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN4.texOffs(42, 36).addBox(10.9282F, -5.0718F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
		HUABAN4.texOffs(73, 73).addBox(-14.9282F, -5.0718F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		HUABAN5 = new ModelRenderer(this);
		HUABAN5.setPos(0.0F, 0.0F, 0.0F);
		HUABAN4.addChild(HUABAN5);
		setRotationAngle(HUABAN5, 0.0F, 0.0F, -0.5236F);
		HUABAN5.texOffs(0, 62).addBox(-3.4641F, -15.8564F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN5.texOffs(29, 63).addBox(-3.4641F, 10.0F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN5.texOffs(42, 36).addBox(11.4641F, -4.9282F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
		HUABAN5.texOffs(73, 73).addBox(-14.3923F, -4.9282F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		HUABAN6 = new ModelRenderer(this);
		HUABAN6.setPos(0.0F, 0.0F, 0.0F);
		HUABAN5.addChild(HUABAN6);
		setRotationAngle(HUABAN6, 0.0F, 0.0F, -0.5236F);
		HUABAN6.texOffs(0, 62).addBox(-3.0718F, -15.4641F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN6.texOffs(29, 63).addBox(-3.0718F, 10.3923F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN6.texOffs(42, 36).addBox(11.8564F, -4.5359F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
		HUABAN6.texOffs(73, 73).addBox(-14.0F, -4.5359F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, -1.0F, 0.0F);
		total.addChild(body);
		body.texOffs(112, 43).addBox(-2.0F, -34.0F, -2.0F, 4.0F, 35.0F, 4.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(0.0F, -34.7071F, -0.1213F);
		body.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.7854F, 0.0F, 0.0F);
		cube_r1.texOffs(23, 0).addBox(-2.0F, -4.0F, -3.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		YEZI_4 = new ModelRenderer(this);
		YEZI_4.setPos(-2.0F, -1.0F, 0.0F);
		total.addChild(YEZI_4);
		YEZI_4.texOffs(78, 26).addBox(-11.0F, -1.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
		YEZI_4.texOffs(114, 28).addBox(-3.0F, 0.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);

		YEZI_3 = new ModelRenderer(this);
		YEZI_3.setPos(0.0F, 0.0F, -2.0F);
		total.addChild(YEZI_3);
		YEZI_3.texOffs(114, 19).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		YEZI_3.texOffs(0, 36).addBox(-4.0F, -2.0F, -11.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);

		YEZI_1 = new ModelRenderer(this);
		YEZI_1.setPos(2.0F, 0.0F, 0.0F);
		total.addChild(YEZI_1);
		YEZI_1.texOffs(114, 0).addBox(0.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		YEZI_1.texOffs(79, 0).addBox(3.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);

		YEZI_2 = new ModelRenderer(this);
		YEZI_2.setPos(0.0F, 0.0F, 2.0F);
		total.addChild(YEZI_2);
		YEZI_2.texOffs(78, 12).addBox(-4.0F, -2.0F, 3.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
		YEZI_2.texOffs(114, 9).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(SunFlowerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<SunFlowerEntity> getPlantModel() {
		return this;
	}
}
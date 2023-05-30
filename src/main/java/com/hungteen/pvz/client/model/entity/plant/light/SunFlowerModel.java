package com.hungteen.pvz.client.model.entity.plant.light;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.light.SunFlowerEntity;
import com.hungteen.pvz.utils.AnimationUtil;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 4.1.3
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class SunFlowerModel extends PVZPlantModel<SunFlowerEntity> {
	private final ModelRenderer total;
	private final ModelRenderer leaves;
	private final ModelRenderer w_r1;
	private final ModelRenderer w_r2;
	private final ModelRenderer e_r1;
	private final ModelRenderer e_r2;
	private final ModelRenderer body;
	private final ModelRenderer stickd_r1;
	private final ModelRenderer head;
	private final ModelRenderer right_hand;
	private final ModelRenderer leafw_r1;
	private final ModelRenderer left_hand;
	private final ModelRenderer leafe_r1;

	public SunFlowerModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(8.0F, 24.0F, -8.0F);


		leaves = new ModelRenderer(this);
		leaves.setPos(-8.0F, 1.0F, 8.0F);
		total.addChild(leaves);


		w_r1 = new ModelRenderer(this);
		w_r1.setPos(0.0F, -0.5F, 0.0F);
		leaves.addChild(w_r1);
		setRotationAngle(w_r1, 0.1745F, 0.0F, 0.0F);
		w_r1.texOffs(0, 24).addBox(-2.0F, -0.5F, 0.0F, 4.0F, 1.0F, 6.0F, 0.0F, false);

		w_r2 = new ModelRenderer(this);
		w_r2.setPos(0.0F, -0.5F, 0.0F);
		leaves.addChild(w_r2);
		setRotationAngle(w_r2, -0.1745F, 0.0F, 0.0F);
		w_r2.texOffs(24, 7).addBox(-2.0F, -0.5F, -7.0F, 4.0F, 1.0F, 6.0F, 0.0F, false);

		e_r1 = new ModelRenderer(this);
		e_r1.setPos(0.0F, -0.5F, -1.0F);
		leaves.addChild(e_r1);
		setRotationAngle(e_r1, 0.0F, 0.0F, -0.1745F);
		e_r1.texOffs(26, 14).addBox(1.0F, -0.5F, -2.0F, 6.0F, 1.0F, 4.0F, 0.0F, false);

		e_r2 = new ModelRenderer(this);
		e_r2.setPos(0.0F, -0.5F, -1.0F);
		leaves.addChild(e_r2);
		setRotationAngle(e_r2, 0.0F, 0.0F, 0.1745F);
		e_r2.texOffs(30, 0).addBox(-7.0F, -0.5F, -2.0F, 6.0F, 1.0F, 4.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(-8.0F, 0.0F, 7.0F);
		total.addChild(body);
		body.texOffs(26, 30).addBox(-1.0F, -14.0F, 1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

		stickd_r1 = new ModelRenderer(this);
		stickd_r1.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(stickd_r1);
		setRotationAngle(stickd_r1, -0.2618F, 0.0F, 0.0F);
		stickd_r1.texOffs(18, 30).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -13.0F, 1.0F);
		body.addChild(head);
		head.texOffs(22, 20).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 4.0F, 0.0F, false);
		head.texOffs(0, 13).addBox(-5.0F, -5.0F, -3.0F, 10.0F, 8.0F, 3.0F, 0.0F, false);
		head.texOffs(0, 0).addBox(-7.0F, -7.0F, -2.0F, 14.0F, 12.0F, 1.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-0.5F, -3.5F, 1.0F);
		body.addChild(right_hand);


		leafw_r1 = new ModelRenderer(this);
		leafw_r1.setPos(0.0F, 0.0F, 0.0F);
		right_hand.addChild(leafw_r1);
		setRotationAngle(leafw_r1, 0.1745F, 0.0F, 0.1745F);
		leafw_r1.texOffs(8, 32).addBox(-3.5F, -0.5F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(0.5F, -4.5F, 1.0F);
		body.addChild(left_hand);


		leafe_r1 = new ModelRenderer(this);
		leafe_r1.setPos(0.0F, 0.0F, 0.0F);
		left_hand.addChild(leafe_r1);
		setRotationAngle(leafe_r1, 0.1745F, 0.0F, -0.1745F);
		leafe_r1.texOffs(0, 31).addBox(0.5F, -0.5F, -1.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(SunFlowerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		final int tick = entity.getAttackTime();
		if(tick > 0 && tick < entity.getAnimGenCD() || entity.isPlantInSuperMode()) {
			final int T = 10;
			this.left_hand.zRot = AnimationUtil.getUpDownUpDown(ageInTicks % T, T, - 30);
			this.right_hand.zRot = AnimationUtil.getUpDownUpDown(ageInTicks % T, T, - 30);
		} else {
			this.left_hand.zRot = 0;
			this.right_hand.zRot = 0;
		}
		final int T = 60;
		final int time = entity.getExistTick() % 60;
		final float degree = 7.5F;
		this.body.zRot = AnimationUtil.getUpDownUpDown(time, T, degree);
		this.head.zRot = AnimationUtil.getUpDownUpDown(time, T, - degree);
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
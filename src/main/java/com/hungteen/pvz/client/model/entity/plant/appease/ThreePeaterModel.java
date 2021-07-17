package com.hungteen.pvz.client.model.entity.plant.appease;

import com.hungteen.pvz.client.model.entity.plant.PlantShooterModel;
import com.hungteen.pvz.common.entity.plant.appease.ThreePeaterEntity;
import com.hungteen.pvz.common.entity.plant.base.PlantShooterEntity;
import com.hungteen.pvz.utils.AnimationUtil;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class ThreePeaterModel extends PlantShooterModel<ThreePeaterEntity> {
	private final ModelRenderer total;
	private final ModelRenderer leaves;
	private final ModelRenderer body;
	private final ModelRenderer left_up;
	private final ModelRenderer left_body;
	private final ModelRenderer left_head;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer bone13;
	private final ModelRenderer bone14;
	private final ModelRenderer bone15;
	private final ModelRenderer bone16;
	private final ModelRenderer bone17;
	private final ModelRenderer bone18;
	private final ModelRenderer mid_up;
	private final ModelRenderer mid_body;
	private final ModelRenderer mid_head;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer right_up;
	private final ModelRenderer right_body;
	private final ModelRenderer right_head;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;

	public ThreePeaterModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		leaves = new ModelRenderer(this);
		leaves.setPos(1.0F, 0.0F, 0.0F);
		total.addChild(leaves);
		leaves.texOffs(14, 30).addBox(0.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		leaves.texOffs(13, 36).addBox(-2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		leaves.texOffs(1, 30).addBox(-2.0F, -1.0F, -3.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		leaves.texOffs(1, 35).addBox(-4.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		leaves.texOffs(2, 45).addBox(2.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		leaves.texOffs(0, 55).addBox(-3.0F, -1.0F, 3.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		leaves.texOffs(41, 1).addBox(-7.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		leaves.texOffs(42, 30).addBox(-3.0F, -1.0F, -6.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		

		left_up = new ModelRenderer(this);
		left_up.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(left_up);
		setRotationAngle(left_up, 0.0F, 0.0F, 0.7854F);
		

		left_body = new ModelRenderer(this);
		left_body.setPos(-1.0F, -13.0F, 0.0F);
		left_up.addChild(left_body);
		left_body.texOffs(34, 38).addBox(0.0F, -3.0F, -1.0F, 2.0F, 15.0F, 2.0F, -0.1F, false);

		left_head = new ModelRenderer(this);
		left_head.setPos(-0.7071F, -15.7071F, 0.0F);
		left_up.addChild(left_head);
		setRotationAngle(left_head, 0.0F, -0.1309F, -0.7854F);
		left_head.texOffs(38, 15).addBox(-2.0F, -4.0F, -7.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
		left_head.texOffs(1, 19).addBox(-1.0F, -3.0F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		left_head.texOffs(0, 1).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		left_head.texOffs(46, 38).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setPos(0.0F, -7.0F, 4.0F);
		left_head.addChild(bone7);
		setRotationAngle(bone7, 0.6109F, 0.7418F, 0.0F);
		bone7.texOffs(3, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setPos(0.0F, 0.8192F, 0.4264F);
		bone7.addChild(bone8);
		setRotationAngle(bone8, -0.48F, 0.0F, 0.0F);
		bone8.texOffs(54, 10).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setPos(0.0F, 0.0F, 2.0F);
		bone8.addChild(bone9);
		setRotationAngle(bone9, 0.3927F, 0.0F, 0.0F);
		bone9.texOffs(33, 57).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 6.0F, 1.0F, 0.0F, false);

		bone13 = new ModelRenderer(this);
		bone13.setPos(0.0F, -7.0F, 4.0F);
		left_head.addChild(bone13);
		setRotationAngle(bone13, 0.6109F, -0.6981F, 0.0F);
		bone13.texOffs(3, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone14 = new ModelRenderer(this);
		bone14.setPos(0.0F, 0.8192F, 0.4264F);
		bone13.addChild(bone14);
		setRotationAngle(bone14, -0.48F, 0.0F, 0.0F);
		bone14.texOffs(54, 10).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone15 = new ModelRenderer(this);
		bone15.setPos(0.0F, 0.0F, 2.0F);
		bone14.addChild(bone15);
		setRotationAngle(bone15, 0.3927F, 0.0F, 0.0F);
		bone15.texOffs(33, 57).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 6.0F, 1.0F, 0.0F, false);

		bone16 = new ModelRenderer(this);
		bone16.setPos(0.0F, -7.0F, 4.0F);
		left_head.addChild(bone16);
		setRotationAngle(bone16, -0.2182F, 0.0F, 0.0F);
		bone16.texOffs(3, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone17 = new ModelRenderer(this);
		bone17.setPos(0.0F, 0.8192F, 0.4264F);
		bone16.addChild(bone17);
		setRotationAngle(bone17, -0.7854F, 0.0F, 0.0F);
		bone17.texOffs(54, 10).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone18 = new ModelRenderer(this);
		bone18.setPos(0.0F, 0.0F, 2.0F);
		bone17.addChild(bone18);
		setRotationAngle(bone18, 1.309F, 0.0F, 0.0F);
		bone18.texOffs(33, 57).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 6.0F, 1.0F, 0.0F, false);

		mid_up = new ModelRenderer(this);
		mid_up.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(mid_up);
		

		mid_body = new ModelRenderer(this);
		mid_body.setPos(-1.0F, -16.0F, 0.0F);
		mid_up.addChild(mid_body);
		mid_body.texOffs(55, 44).addBox(0.0F, -2.0F, -1.0F, 2.0F, 18.0F, 2.0F, -0.1F, false);

		mid_head = new ModelRenderer(this);
		mid_head.setPos(0.0F, -18.0F, 0.0F);
		mid_up.addChild(mid_head);
		mid_head.texOffs(38, 15).addBox(-2.0F, -4.0F, -7.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
		mid_head.texOffs(1, 19).addBox(-1.0F, -3.0F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		mid_head.texOffs(0, 1).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		mid_head.texOffs(46, 38).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(0.0F, -7.0F, 4.0F);
		mid_head.addChild(bone4);
		setRotationAngle(bone4, 0.6109F, 0.6981F, 0.0F);
		bone4.texOffs(3, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(0.0F, 0.8192F, 0.4264F);
		bone4.addChild(bone5);
		setRotationAngle(bone5, -0.48F, 0.0F, 0.0F);
		bone5.texOffs(54, 10).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(0.0F, 0.0F, 2.0F);
		bone5.addChild(bone6);
		setRotationAngle(bone6, 0.3927F, 0.0F, 0.0F);
		bone6.texOffs(33, 57).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 6.0F, 1.0F, 0.0F, false);

		bone10 = new ModelRenderer(this);
		bone10.setPos(0.0F, -7.0F, 4.0F);
		mid_head.addChild(bone10);
		setRotationAngle(bone10, 0.6109F, -0.6981F, 0.0F);
		bone10.texOffs(3, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone11 = new ModelRenderer(this);
		bone11.setPos(0.0F, 0.8192F, 0.4264F);
		bone10.addChild(bone11);
		setRotationAngle(bone11, -0.48F, 0.0F, 0.0F);
		bone11.texOffs(54, 10).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone12 = new ModelRenderer(this);
		bone12.setPos(0.0F, 0.0F, 2.0F);
		bone11.addChild(bone12);
		setRotationAngle(bone12, 0.3927F, 0.0F, 0.0F);
		bone12.texOffs(33, 57).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 6.0F, 1.0F, 0.0F, false);

		right_up = new ModelRenderer(this);
		right_up.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(right_up);
		setRotationAngle(right_up, 0.0F, 0.0F, -0.7854F);
		

		right_body = new ModelRenderer(this);
		right_body.setPos(0.0F, -1.0F, 0.0F);
		right_up.addChild(right_body);
		right_body.texOffs(45, 46).addBox(-1.0F, -15.0F, -1.0F, 2.0F, 15.0F, 2.0F, -0.1F, false);

		right_head = new ModelRenderer(this);
		right_head.setPos(0.7071F, -15.7071F, 0.0F);
		right_up.addChild(right_head);
		setRotationAngle(right_head, 0.0F, 0.1309F, 0.7854F);
		right_head.texOffs(38, 15).addBox(-2.0F, -4.0F, -7.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
		right_head.texOffs(1, 19).addBox(-1.0F, -3.0F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		right_head.texOffs(0, 1).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		right_head.texOffs(46, 38).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, -7.0F, 4.0F);
		right_head.addChild(bone);
		setRotationAngle(bone, 0.6109F, 0.0F, 0.0F);
		bone.texOffs(3, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, 0.8192F, 0.4264F);
		bone.addChild(bone2);
		setRotationAngle(bone2, -0.48F, 0.0F, 0.0F);
		bone2.texOffs(54, 10).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, 0.0F, 2.0F);
		bone2.addChild(bone3);
		setRotationAngle(bone3, 0.3927F, 0.0F, 0.0F);
		bone3.texOffs(33, 57).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 6.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(ThreePeaterEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		final float OFFSET = 0.78F;
		this.left_head.xRot = 0;
		this.mid_head.xRot = 0;
		this.right_head.xRot = 0;
		this.body.xRot = 0;
		this.right_up.zRot = - OFFSET;
		this.right_head.zRot = OFFSET;
		this.left_up.zRot = OFFSET;
		this.left_head.zRot = - OFFSET;
		if(entity.isPlantInSuperMode()) {
			final int T = PlantShooterEntity.SHOOT_ANIM_CD;
			final int tick = entity.getSuperTime() % T;
			if(tick >= 0) {
				this.left_head.xRot = AnimationUtil.getUpDownUpDown(tick, T, getMaxRotAngle() / 5);
				this.mid_head.xRot = AnimationUtil.getUpDownUpDown(tick, T, getMaxRotAngle() / 5);
				this.right_head.xRot = AnimationUtil.getUpDownUpDown(tick, T, getMaxRotAngle() / 5);
				this.body.xRot = AnimationUtil.getUpDownUpDown(tick, T, - getMaxRotAngle() / 5);
			} 
		} else {
			final int T = PlantShooterEntity.SHOOT_ANIM_CD;
			final int tick = entity.getShootTick() + T - entity.getShootCD();
			if(tick >= 0) {
				this.left_head.xRot = AnimationUtil.getUpDownUpDown(tick, T, getMaxRotAngle());
				this.mid_head.xRot = AnimationUtil.getUpDownUpDown(tick, T, getMaxRotAngle());
				this.right_head.xRot = AnimationUtil.getUpDownUpDown(tick, T, getMaxRotAngle());
				this.body.xRot = AnimationUtil.getUpDownUpDown(tick, T, - getMaxRotAngle());
			} else {//idle
				final int TT = 40;
				final int tt = entity.getExistTick() % TT;
				final float CHANGE = 3;
				this.mid_up.zRot = AnimationUtil.getUpDownUpDown(tt, TT, CHANGE / 4);
				this.left_up.zRot = AnimationUtil.getUpDownUpDown(tt, TT, CHANGE) + OFFSET;
				this.left_head.zRot = - AnimationUtil.getUpDownUpDown(tt, TT, CHANGE) - OFFSET;
				this.right_up.zRot = - AnimationUtil.getUpDownUpDown(tt, TT, CHANGE) - OFFSET;
				this.right_head.zRot = AnimationUtil.getUpDownUpDown(tt, TT, CHANGE) + OFFSET;
			}
		}
	}
	
	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

}
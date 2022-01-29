package com.hungteen.pvz.client.model.entity.zombie.roof;

import com.hungteen.pvz.api.interfaces.IBodyEntity;
import com.hungteen.pvz.api.paz.IZombieModel;
import com.hungteen.pvz.client.model.entity.PVZEntityModel;
import com.hungteen.pvz.common.entity.zombie.roof.GargantuarEntity;
import com.hungteen.pvz.common.entity.zombie.roof.GargantuarEntity.GargantuarType;
import com.hungteen.pvz.utils.AnimationUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class GargantuarModel<T extends GargantuarEntity> extends PVZEntityModel<T> implements IZombieModel<T>{
	private final ModelRenderer total;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer up;
	private final ModelRenderer back;
	private final ModelRenderer imp;
	private final ModelRenderer left_leg3;
	private final ModelRenderer right_leg3;
	private final ModelRenderer up3;
	private final ModelRenderer body3;
	private final ModelRenderer right_arm2;
	private final ModelRenderer cube_r1;
	private final ModelRenderer left_arm2;
	private final ModelRenderer cube_r2;
	private final ModelRenderer head3;
	private final ModelRenderer head;
	private final ModelRenderer angry;
	private final ModelRenderer bone3;
	private final ModelRenderer hurt2;
	private final ModelRenderer hurt21;
	private final ModelRenderer hurt22;
	private final ModelRenderer hurt23;
	private final ModelRenderer right_arm;
	private final ModelRenderer left_arm;
	private final ModelRenderer hurt1;
	private final ModelRenderer hurt11;
	private final ModelRenderer pole;
	private final ModelRenderer sign;
	private final ModelRenderer bone;
	private final ModelRenderer doll;
	private final ModelRenderer right_leg2;
	private final ModelRenderer rightleg_r1;
	private final ModelRenderer left_leg2;
	private final ModelRenderer leftleg_r1;
	private final ModelRenderer up2;
	private final ModelRenderer body2;
	private final ModelRenderer body_r1;
	private final ModelRenderer left_hand;
	private final ModelRenderer lefthand_r1;
	private final ModelRenderer right_hand;
	private final ModelRenderer righthand_r1;
	private final ModelRenderer head2;
	private final ModelRenderer head_r1;
	private final ModelRenderer body;

	public GargantuarModel() {
		texWidth = 512;
		texHeight = 512;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		left_leg = new ModelRenderer(this);
		left_leg.setPos(7.0F, -39.0F, 1.0F);
		total.addChild(left_leg);
		left_leg.texOffs(199, 235).addBox(-6.0F, 36.0F, -9.0F, 12.0F, 3.0F, 16.0F, 0.0F, false);
		left_leg.texOffs(3, 205).addBox(-5.0F, -4.0F, -5.0F, 10.0F, 40.0F, 10.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-8.0F, -39.0F, 1.0F);
		total.addChild(right_leg);
		right_leg.texOffs(198, 211).addBox(-5.0F, 36.0F, -9.0F, 12.0F, 3.0F, 16.0F, 0.0F, false);
		right_leg.texOffs(47, 204).addBox(-4.0F, -4.0F, -5.0F, 10.0F, 40.0F, 10.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setPos(0.0F, -39.0F, 0.0F);
		total.addChild(up);
		

		back = new ModelRenderer(this);
		back.setPos(0.0F, -25.0F, 23.0F);
		up.addChild(back);
		back.texOffs(93, 236).addBox(-8.0F, -15.0F, -10.0F, 16.0F, 17.0F, 1.0F, 0.0F, false);
		back.texOffs(186, 186).addBox(-8.0F, 2.0F, -10.0F, 16.0F, 1.0F, 17.0F, 0.0F, false);
		back.texOffs(5, 167).addBox(8.0F, -15.0F, -10.0F, 1.0F, 17.0F, 18.0F, 0.0F, false);
		back.texOffs(50, 184).addBox(-8.0F, -15.0F, 7.0F, 16.0F, 17.0F, 1.0F, 0.0F, false);
		back.texOffs(94, 192).addBox(-9.0F, -15.0F, -10.0F, 1.0F, 17.0F, 18.0F, 0.0F, false);

		imp = new ModelRenderer(this);
		imp.setPos(0.0F, 3.0F, -1.0F);
		back.addChild(imp);
		

		left_leg3 = new ModelRenderer(this);
		left_leg3.setPos(2.0F, -12.0F, 0.0F);
		imp.addChild(left_leg3);
		left_leg3.texOffs(408, 42).addBox(-1.0F, -1.0F, -2.0F, 4.0F, 13.0F, 4.0F, 0.0F, false);

		right_leg3 = new ModelRenderer(this);
		right_leg3.setPos(-3.0F, -12.0F, 0.0F);
		imp.addChild(right_leg3);
		right_leg3.texOffs(408, 24).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 13.0F, 4.0F, 0.0F, false);

		up3 = new ModelRenderer(this);
		up3.setPos(0.0F, -12.0F, 0.0F);
		imp.addChild(up3);
		

		body3 = new ModelRenderer(this);
		body3.setPos(0.0F, 0.0F, 0.0F);
		up3.addChild(body3);
		body3.texOffs(408, 4).addBox(-5.0F, -16.0F, -2.0F, 10.0F, 15.0F, 4.0F, 0.0F, false);

		right_arm2 = new ModelRenderer(this);
		right_arm2.setPos(-7.0F, -14.0F, 0.0F);
		up3.addChild(right_arm2);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(0.25F, 0.0F, 0.0F);
		right_arm2.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.7854F, 0.0F, 0.0F);
		cube_r1.texOffs(455, 29).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 15.0F, 4.0F, -0.1F, false);

		left_arm2 = new ModelRenderer(this);
		left_arm2.setPos(7.0F, -14.0F, 0.0F);
		up3.addChild(left_arm2);
		

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(-0.25F, 0.0F, 0.0F);
		left_arm2.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.7854F, 0.0F, 0.0F);
		cube_r2.texOffs(437, 19).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 15.0F, 4.0F, -0.1F, false);

		head3 = new ModelRenderer(this);
		head3.setPos(0.0F, -16.0F, 0.0F);
		up3.addChild(head3);
		head3.texOffs(424, 39).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -40.0F, -11.0F);
		up.addChild(head);
		head.texOffs(192, 148).addBox(-8.0F, -8.0F, -14.0F, 16.0F, 17.0F, 14.0F, 0.0F, false);

		angry = new ModelRenderer(this);
		angry.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(angry);
		angry.texOffs(3, 140).addBox(2.0F, -6.0F, -14.05F, 5.0F, 5.0F, 1.0F, 0.02F, false);
		angry.texOffs(28, 141).addBox(-6.0F, -5.0F, -14.05F, 3.0F, 3.0F, 1.0F, 0.02F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, 0.0F, 1.0F);
		head.addChild(bone3);
		setRotationAngle(bone3, 1.2217F, 0.0F, 0.0F);
		bone3.texOffs(4, 160).addBox(-8.0F, -1.0F, 0.0F, 16.0F, 1.0F, 1.0F, 0.0F, false);
		bone3.texOffs(4, 152).addBox(-9.0F, -1.0F, -12.0F, 18.0F, 1.0F, 1.0F, 0.0F, false);
		bone3.texOffs(49, 158).addBox(-9.0F, -1.0F, -11.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
		bone3.texOffs(47, 140).addBox(8.0F, -1.0F, -11.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);

		hurt2 = new ModelRenderer(this);
		hurt2.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(hurt2);
		

		hurt21 = new ModelRenderer(this);
		hurt21.setPos(8.5F, 2.0F, -8.75F);
		hurt2.addChild(hurt21);
		setRotationAngle(hurt21, -0.7854F, 0.0F, 0.0F);
		hurt21.texOffs(240, 239).addBox(-1.0F, -3.0F, -1.0F, 1.0F, 6.0F, 2.0F, -0.4F, false);
		hurt21.texOffs(242, 242).addBox(-1.0F, -1.0F, -3.0F, 1.0F, 2.0F, 6.0F, -0.4F, false);

		hurt22 = new ModelRenderer(this);
		hurt22.setPos(-7.5F, -5.0F, -3.25F);
		hurt2.addChild(hurt22);
		setRotationAngle(hurt22, -0.7854F, 0.0F, 0.0F);
		hurt22.texOffs(207, 239).addBox(-1.0F, -3.0F, -1.0F, 1.0F, 6.0F, 2.0F, -0.4F, false);
		hurt22.texOffs(242, 231).addBox(-1.0F, -1.0F, -3.0F, 1.0F, 2.0F, 6.0F, -0.4F, false);

		hurt23 = new ModelRenderer(this);
		hurt23.setPos(1.0F, -8.5F, -10.75F);
		hurt2.addChild(hurt23);
		setRotationAngle(hurt23, -0.3927F, 0.0F, -1.5708F);
		hurt23.texOffs(208, 231).addBox(-1.0F, -3.0F, -1.0F, 1.0F, 6.0F, 2.0F, -0.4F, false);
		hurt23.texOffs(189, 240).addBox(-1.0F, -1.0F, -3.0F, 1.0F, 2.0F, 6.0F, -0.4F, false);

		right_arm = new ModelRenderer(this);
		right_arm.setPos(-19.0F, -34.0F, 1.0F);
		up.addChild(right_arm);
		right_arm.texOffs(100, 25).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 52.0F, 12.0F, 0.0F, false);

		left_arm = new ModelRenderer(this);
		left_arm.setPos(19.0F, -34.0F, 1.0F);
		up.addChild(left_arm);
		left_arm.texOffs(207, 79).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 52.0F, 12.0F, 0.0F, false);

		hurt1 = new ModelRenderer(this);
		hurt1.setPos(0.0F, 0.0F, 0.0F);
		left_arm.addChild(hurt1);
		

		hurt11 = new ModelRenderer(this);
		hurt11.setPos(6.5F, 14.0F, 0.0F);
		hurt1.addChild(hurt11);
		setRotationAngle(hurt11, -0.7854F, 0.0F, 0.0F);
		hurt11.texOffs(0, 203).addBox(-1.0F, -5.0F, -1.0F, 1.0F, 10.0F, 2.0F, -0.4F, false);
		hurt11.texOffs(193, 239).addBox(-1.0F, -1.0F, -5.0F, 1.0F, 2.0F, 10.0F, -0.4F, false);

		pole = new ModelRenderer(this);
		pole.setPos(0.0F, 43.0F, -11.0F);
		left_arm.addChild(pole);
		pole.texOffs(0, 257).addBox(-2.0F, -3.0F, -50.0F, 4.0F, 4.0F, 72.0F, 0.0F, false);
		pole.texOffs(0, 300).addBox(-2.0F, -13.0F, -36.0F, 4.0F, 24.0F, 3.0F, 0.2F, false);
		pole.texOffs(16, 307).addBox(-2.0F, -9.0F, -46.0F, 4.0F, 16.0F, 3.0F, 0.2F, false);
		pole.texOffs(48, 300).addBox(-1.0F, -7.0F, -49.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
		pole.texOffs(29, 297).addBox(-1.0F, 3.0F, -49.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
		pole.texOffs(49, 320).addBox(-1.0F, -10.0F, -39.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);
		pole.texOffs(34, 317).addBox(-1.0F, 7.0F, -39.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		sign = new ModelRenderer(this);
		sign.setPos(0.0F, 42.0F, 0.0F);
		left_arm.addChild(sign);
		sign.texOffs(0, 337).addBox(-2.0F, -2.0F, -37.0F, 4.0F, 4.0F, 52.0F, -1.2F, false);
		sign.texOffs(0, 378).addBox(-2.0F, -2.0F, 13.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		sign.texOffs(0, 257).addBox(-1.0F, -1.0F, -67.0F, 2.0F, 2.0F, 2.0F, 0.2F, false);
		sign.texOffs(3, 337).addBox(-2.0F, -4.0F, 12.0F, 4.0F, 4.0F, 1.0F, -0.45F, false);
		sign.texOffs(5, 345).addBox(0.0F, -2.0F, 12.25F, 4.0F, 4.0F, 1.0F, -0.45F, false);
		sign.texOffs(37, 381).addBox(-4.0F, -2.0F, 12.25F, 4.0F, 4.0F, 1.0F, -0.45F, false);
		sign.texOffs(21, 381).addBox(-2.0F, 0.0F, 12.0F, 4.0F, 4.0F, 1.0F, -0.45F, false);
		sign.texOffs(0, 257).addBox(-2.0F, -2.0F, -67.0F, 4.0F, 4.0F, 7.0F, -1.2F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 0.0F, -53.0F);
		sign.addChild(bone);
		setRotationAngle(bone, -0.7854F, 0.0F, 0.0F);
		bone.texOffs(0, 335).addBox(-1.0F, -12.8284F, -7.1716F, 2.0F, 20.0F, 20.0F, 0.0F, false);

		doll = new ModelRenderer(this);
		doll.setPos(-19.0F, 69.0F, 4.0F);
		left_arm.addChild(doll);
		

		right_leg2 = new ModelRenderer(this);
		right_leg2.setPos(-4.0F, -24.0F, 0.0F);
		doll.addChild(right_leg2);
		

		rightleg_r1 = new ModelRenderer(this);
		rightleg_r1.setPos(23.0F, -3.0F, 0.0F);
		right_leg2.addChild(rightleg_r1);
		setRotationAngle(rightleg_r1, 1.5708F, 0.0F, -1.5708F);
		rightleg_r1.texOffs(306, 1).addBox(-8.0F, -24.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_leg2 = new ModelRenderer(this);
		left_leg2.setPos(4.0F, -24.0F, 0.0F);
		doll.addChild(left_leg2);
		

		leftleg_r1 = new ModelRenderer(this);
		leftleg_r1.setPos(15.0F, -3.0F, 0.0F);
		left_leg2.addChild(leftleg_r1);
		setRotationAngle(leftleg_r1, 1.5708F, 0.0F, -1.5708F);
		leftleg_r1.texOffs(261, 1).addBox(0.0F, -24.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		up2 = new ModelRenderer(this);
		up2.setPos(0.0F, -24.0F, 0.0F);
		doll.addChild(up2);
		

		body2 = new ModelRenderer(this);
		body2.setPos(0.0F, -7.0F, 0.0F);
		up2.addChild(body2);
		

		body_r1 = new ModelRenderer(this);
		body_r1.setPos(19.0F, 4.0F, 0.0F);
		body2.addChild(body_r1);
		setRotationAngle(body_r1, 1.5708F, 0.0F, -1.5708F);
		body_r1.texOffs(262, 42).addBox(-8.0F, -48.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(12.0F, -20.0F, 0.0F);
		up2.addChild(left_hand);
		

		lefthand_r1 = new ModelRenderer(this);
		lefthand_r1.setPos(7.0F, 17.0F, 0.0F);
		left_hand.addChild(lefthand_r1);
		setRotationAngle(lefthand_r1, 1.5708F, 0.0F, -1.5708F);
		lefthand_r1.texOffs(358, 61).addBox(8.0F, -48.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-12.0F, -20.0F, 0.0F);
		up2.addChild(right_hand);
		

		righthand_r1 = new ModelRenderer(this);
		righthand_r1.setPos(31.0F, 17.0F, 0.0F);
		right_hand.addChild(righthand_r1);
		setRotationAngle(righthand_r1, 1.5708F, 0.0F, -1.5708F);
		righthand_r1.texOffs(358, 1).addBox(-16.0F, -48.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		head2 = new ModelRenderer(this);
		head2.setPos(0.0F, -24.0F, 0.0F);
		up2.addChild(head2);
		

		head_r1 = new ModelRenderer(this);
		head_r1.setPos(19.0F, 21.0F, 0.0F);
		head2.addChild(head_r1);
		setRotationAngle(head_r1, 1.5708F, 0.0F, -1.5708F);
		head_r1.texOffs(278, 97).addBox(-8.0F, -64.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 39.0F, 0.0F);
		up.addChild(body);
		body.texOffs(153, 4).addBox(-13.0F, -79.0F, -11.0F, 26.0F, 40.0F, 24.0F, 0.0F, false);
		body.texOffs(83, 122).addBox(-6.0F, -80.0F, -10.0F, 1.0F, 1.0F, 24.0F, 0.0F, false);
		body.texOffs(146, 123).addBox(5.0F, -80.0F, -10.0F, 1.0F, 1.0F, 24.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.yRot = netHeadYaw / (180F / (float)Math.PI);
        this.head.xRot = headPitch / (180F / (float)Math.PI);
        if(entity.getAttackTime() == 0) {
            this.left_leg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.right_leg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
            this.right_arm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
            this.left_arm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
            this.up.xRot = 0;
		} else if(entity.getAttackTime() > 0) {// normal attack
			int total = entity.getCrushCD();
			int now = total - entity.getAttackTime();
			int frontT = total * 2 / 3;
			if(now < frontT) {
				this.left_arm.xRot = AnimationUtil.getUpDown(now, frontT, - 120);
				this.left_arm.yRot = 0;
			} else {
				this.left_arm.xRot = AnimationUtil.getUpDown(now - frontT , total - frontT, 30);
				this.left_arm.yRot = AnimationUtil.getUpDown(now - frontT , total - frontT, 30);
			}
			this.up.xRot = AnimationUtil.getUpDownUpDown(now, total, - 15);
			this.right_leg.xRot = 0;
			this.left_leg.xRot = 0;
			this.right_arm.xRot = 0;
		} else {
			int total = entity.getThrowCD();
			int now = total + entity.getAttackTime();
			int frontT = total * 3 / 4;
			if(now < frontT) {
				this.right_arm.xRot = AnimationUtil.getUp(now, frontT, - 225);
			} else {
				this.right_arm.xRot = AnimationUtil.getDown(now - frontT, total - frontT, - 225);
			}
			this.left_arm.xRot = 0;
			this.right_leg.xRot = 0;
			this.left_leg.xRot = 0;
			this.up.xRot = 0;
		}
        this.pole.visible = (entity.getToolType() == GargantuarType.POLE);
        this.sign.visible = (entity.getToolType() == GargantuarType.SIGN);
        this.doll.visible = (entity.getToolType() == GargantuarType.DOLL);
        this.hurt1.visible = (entity.getHealth() / entity.getMaxHealth() < 2F / 3);
        this.hurt2.visible = (entity.getHealth() / entity.getMaxHealth() < 1F / 3);
        this.angry.visible = entity.isSad;
        this.imp.visible = entity.hasImp();
	}

	@Override
	public void tickPartAnim(IBodyEntity entity, float limbSwing, float limbSwingAmount,
			float ageInTicks, float netHeadYaw, float headPitch) {
		final int cd = GargantuarEntity.DEATH_ANIM_CD;
		final int time = entity.getAnimTime();
		final int roundT = 40;
		if(time < roundT) {
			this.up.xRot = AnimationUtil.getUpDownUpDown(time, roundT, 15);
			this.total.xRot = AnimationUtil.getUpDownUpDown(time - roundT, roundT, 5);
		} else if(time < roundT * 2) {
			this.up.xRot = AnimationUtil.getUpDownUpDown(time - roundT, roundT, 30);
			this.total.xRot = AnimationUtil.getUpDownUpDown(time - roundT, roundT, 10);
		} else {
			this.total.xRot = AnimationUtil.getUp(time - 2 * roundT, cd - 2 * roundT, 80);
		}
	}

	@Override
	public void renderBody(IBodyEntity entity, MatrixStack stack, IVertexBuilder buffer, int packedLight,
			int packedOverlay) {
		this.pole.visible = false;
		this.sign.visible = false;
		this.doll.visible = false;
		this.angry.visible = entity.hasHandDefence();
		this.imp.visible = false;
		this.total.render(stack, buffer, packedLight, packedOverlay);
	}

	@Override
	public EntityModel<T> getZombieModel() {
		return this;
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		this.total.render(matrixStack, buffer, packedLight, packedOverlay);
	}
	
}
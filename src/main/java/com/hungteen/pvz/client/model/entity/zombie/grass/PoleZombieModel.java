package com.hungteen.pvz.client.model.entity.zombie.grass;

import java.util.Optional;

import com.hungteen.pvz.api.interfaces.IBodyEntity;
import com.hungteen.pvz.api.enums.BodyType;
import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.hungteen.pvz.common.entity.zombie.grass.PoleZombieEntity;
import com.hungteen.pvz.utils.AnimationUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class PoleZombieModel extends PVZZombieModel<PoleZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer left_hand2;
	private final ModelRenderer right_hand;
	private final ModelRenderer right_hand2;
	private final ModelRenderer pole;
	private final ModelRenderer head;

	public PoleZombieModel() {
		texWidth = 256;
		texHeight = 256;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-4.0F, -26.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.texOffs(161, 220).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 24.0F, 4.0F, 0.0F, false);
		right_leg.texOffs(124, 239).addBox(-3.0F, 24.0F, -7.0F, 6.0F, 2.0F, 10.0F, 0.0F, false);
		right_leg.texOffs(158, 202).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 4.0F, 6.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(4.0F, -26.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.texOffs(228, 219).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 24.0F, 4.0F, 0.0F, false);
		left_leg.texOffs(190, 238).addBox(-3.0F, 24.0F, -7.0F, 6.0F, 2.0F, 10.0F, 0.0F, false);
		left_leg.texOffs(190, 198).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 4.0F, 6.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setPos(0.0F, -26.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.texOffs(2, 222).addBox(-8.0F, -26.0F, -3.0F, 16.0F, 26.0F, 6.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(11.0F, -23.0F, 0.0F);
		up.addChild(left_hand);
		left_hand.texOffs(49, 221).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 26.0F, 6.0F, -0.05F, false);

		left_hand2 = new ModelRenderer(this);
		left_hand2.setPos(-2.0F, 12.0F, 0.0F);
		left_hand.addChild(left_hand2);
		left_hand2.texOffs(228, 195).addBox(-1.0F, 3.0F, -3.0F, 6.0F, 10.0F, 6.0F, 0.05F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-11.0F, -23.0F, 0.0F);
		up.addChild(right_hand);
		setRotationAngle(right_hand, -1.5708F, 0.0F, 0.0F);
		right_hand.texOffs(49, 221).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 18.0F, 6.0F, -0.05F, false);

		right_hand2 = new ModelRenderer(this);
		right_hand2.setPos(-2.0F, 12.0F, 0.0F);
		right_hand.addChild(right_hand2);
		setRotationAngle(right_hand2, -1.5708F, 0.0F, 0.0F);
		right_hand2.texOffs(228, 195).addBox(-1.0F, 3.0F, -3.0F, 6.0F, 10.0F, 6.0F, 0.05F, false);

		pole = new ModelRenderer(this);
		pole.setPos(0.0F, 9.0F, 1.0F);
		right_hand2.addChild(pole);
		pole.texOffs(76, 4).addBox(1.0F, 2.1472F, -45.3617F, 2.0F, 2.0F, 85.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -26.0F, 0.0F);
		up.addChild(head);
		head.texOffs(196, 104).addBox(-7.0F, -14.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
		
		this.rightHandOriginAngel = - 90;
	}

	@Override
	public void setupAnim(PoleZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		if(entity.getAttackTime() > 0) {
			int time = entity.getPoleJumpCD() - entity.getAttackTime();
			final int T = entity.getPoleJumpCD() / 4;
			if(time < T) {
				this.right_hand.xRot = - AnimationUtil.byDegree(90) + AnimationUtil.getUp(time, T, 90);
			} else if(time < T * 3) {
				this.right_hand.xRot = AnimationUtil.getUp(time - T, T * 2, 90);
				this.up.xRot = - AnimationUtil.getUp(time - T, T * 2, 90);
				this.left_leg.xRot = - AnimationUtil.getUp(time - T, T * 2, 120);
				this.right_leg.xRot = - AnimationUtil.getUp(time - T, T * 2, 120);
			} else {
				this.right_hand.xRot = AnimationUtil.byDegree(90) - AnimationUtil.getUp(time - 3 * T, T, 90);
				this.up.xRot = - AnimationUtil.byDegree(90) + AnimationUtil.getUp(time - 3 * T, T, 90);
				this.left_leg.xRot = - AnimationUtil.byDegree(120) + AnimationUtil.getUp(time - 3 * T, T, 120);
				this.right_leg.xRot = - AnimationUtil.byDegree(120) + AnimationUtil.getUp(time - 3 * T, T, 120);
			}
		}
	}
	
	@Override
	public void renderBody(IBodyEntity entity, MatrixStack stack, IVertexBuilder buffer, int packedLight,
			int packedOverlay) {
		super.renderBody(entity, stack, buffer, packedLight, packedOverlay);
		if(entity.getBodyType() == BodyType.BODY) {
			this.right_hand2.xRot = ! entity.hasHandDefence() ? 0 : -1.5708F;
		}
	}
	
	@Override
	public void refreshAnim() {
		this.right_hand.xRot = -1.5708F;
		this.up.xRot = 0;
	}
	
	@Override
	public void updateFreeParts(PoleZombieEntity entity) {
		super.updateFreeParts(entity);
		final boolean hasPole = entity.hasPole();
		this.pole.visible = hasPole;
		this.isRightHandFree = ! hasPole;
		this.right_hand2.xRot = this.isRightHandFree ? 0 : -1.5708F;
	}

	@Override
	public Optional<ModelRenderer> getHandDefence() {
		return Optional.ofNullable(this.pole);
	}
	
	@Override
	public ModelRenderer getZombieLeftHand() {
		return this.left_hand;
	}

	@Override
	public ModelRenderer getZombieRightHand() {
		return this.right_hand;
	}

	@Override
	public ModelRenderer getZombieLeftLeg() {
		return this.left_leg;
	}

	@Override
	public ModelRenderer getZombieRightLeg() {
		return this.right_leg;
	}

	@Override
	public ModelRenderer getZombieHead() {
		return this.head;
	}
	
	@Override
	public ModelRenderer getZombieUpBody() {
		return this.up;
	}

	@Override
	public ModelRenderer getZombieWholeBody() {
		return this.total;
	}
}
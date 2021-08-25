package com.hungteen.pvz.client.model.entity.zombie.grass;

import java.util.Optional;

import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.hungteen.pvz.common.entity.zombie.grass.FootballZombieEntity;
import com.hungteen.pvz.utils.AnimationUtil;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class FootballZombieModel extends PVZZombieModel<FootballZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer helmet;
	private final ModelRenderer left_hand;
	private final ModelRenderer bone;
	private final ModelRenderer hand;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer right_hand;
	private final ModelRenderer bone2;
	private final ModelRenderer hand2;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;

	public FootballZombieModel() {
		texWidth = 256;
		texHeight = 256;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		left_leg = new ModelRenderer(this);
		left_leg.setPos(5.0F, -31.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.texOffs(236, 237).addBox(-2.0F, 16.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		left_leg.texOffs(202, 241).addBox(-3.0F, 28.0F, -6.0F, 6.0F, 3.0F, 9.0F, 0.0F, false);
		left_leg.texOffs(237, 229).addBox(-3.0F, 27.0F, -6.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		left_leg.texOffs(2, 232).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 18.0F, 6.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-6.0F, -32.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.texOffs(32, 236).addBox(-1.0F, 17.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		right_leg.texOffs(54, 242).addBox(-2.0F, 29.0F, -6.0F, 6.0F, 3.0F, 9.0F, 0.0F, false);
		right_leg.texOffs(88, 251).addBox(-2.0F, 28.0F, -6.0F, 6.0F, 1.0F, 1.0F, 0.0F, false);
		right_leg.texOffs(108, 232).addBox(-2.0F, -1.0F, -3.0F, 6.0F, 18.0F, 6.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setPos(0.0F, -31.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.texOffs(197, 4).addBox(-9.0F, -32.0F, -5.0F, 18.0F, 32.0F, 10.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -32.0F, 0.0F);
		up.addChild(head);
		head.texOffs(189, 49).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		helmet = new ModelRenderer(this);
		helmet.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(helmet);
		helmet.texOffs(178, 86).addBox(-9.0F, -18.0F, -10.0F, 18.0F, 2.0F, 19.0F, 0.0F, false);
		helmet.texOffs(194, 111).addBox(-7.0F, -19.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);
		helmet.texOffs(214, 132).addBox(9.0F, -16.0F, -9.0F, 1.0F, 16.0F, 18.0F, 0.0F, false);
		helmet.texOffs(215, 172).addBox(-9.0F, -16.0F, 9.0F, 18.0F, 16.0F, 1.0F, 0.0F, false);
		helmet.texOffs(208, 195).addBox(-9.0F, 0.0F, 5.0F, 18.0F, 1.0F, 4.0F, 0.0F, false);
		helmet.texOffs(247, 207).addBox(-9.0F, -4.0F, -10.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		helmet.texOffs(246, 217).addBox(8.0F, -4.0F, -10.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		helmet.texOffs(152, 220).addBox(-10.0F, -16.0F, -9.0F, 1.0F, 16.0F, 18.0F, 0.0F, false);
		helmet.texOffs(197, 231).addBox(-8.0F, -1.0F, -10.0F, 16.0F, 1.0F, 1.0F, 0.0F, false);
		helmet.texOffs(192, 219).addBox(-8.0F, -4.0F, -10.0F, 16.0F, 1.0F, 1.0F, 0.0F, false);
		helmet.texOffs(182, 212).addBox(-7.0F, -16.0F, -10.0F, 1.0F, 15.0F, 1.0F, 0.0F, false);
		helmet.texOffs(140, 225).addBox(6.0F, -16.0F, -10.0F, 1.0F, 15.0F, 1.0F, 0.0F, false);
		helmet.texOffs(86, 239).addBox(2.0F, -3.0F, -10.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		helmet.texOffs(56, 240).addBox(-3.0F, -3.0F, -10.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		helmet.texOffs(57, 233).addBox(-3.0F, -16.0F, -10.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		helmet.texOffs(30, 227).addBox(2.0F, -16.0F, -10.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(14.0F, -27.0F, 0.0F);
		up.addChild(left_hand);
		left_hand.texOffs(3, 214).addBox(-5.0F, -5.0F, -5.0F, 20.0F, 1.0F, 10.0F, 0.0F, false);
		left_hand.texOffs(60, 216).addBox(-5.0F, -4.0F, -6.0F, 16.0F, 6.0F, 1.0F, 0.0F, false);
		left_hand.texOffs(106, 213).addBox(-5.0F, -4.0F, 5.0F, 16.0F, 6.0F, 1.0F, 0.0F, false);
		left_hand.texOffs(144, 201).addBox(-5.0F, -4.0F, -5.0F, 1.0F, 6.0F, 10.0F, 0.0F, false);
		left_hand.texOffs(4, 193).addBox(-5.0F, 2.0F, -5.0F, 15.0F, 3.0F, 10.0F, 0.0F, false);
		left_hand.texOffs(100, 199).addBox(11.0F, -4.0F, -6.0F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		left_hand.texOffs(115, 203).addBox(13.0F, -4.0F, -6.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		left_hand.texOffs(128, 200).addBox(11.0F, -4.0F, 5.0F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		left_hand.texOffs(171, 204).addBox(13.0F, -4.0F, 5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		left_hand.texOffs(126, 174).addBox(-5.0F, 5.0F, -5.0F, 10.0F, 6.0F, 10.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(9.0F, 4.0F, 0.0F);
		left_hand.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, -0.8727F);
		bone.texOffs(60, 197).addBox(2.0F, -1.0F, -5.0F, 8.0F, 1.0F, 10.0F, 0.0F, false);

		hand = new ModelRenderer(this);
		hand.setPos(2.0F, 11.0F, -1.0F);
		left_hand.addChild(hand);
		hand.texOffs(4, 160).addBox(-4.0F, -5.0F, -20.0F, 4.0F, 4.0F, 24.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(-4.0F, -3.0F, -20.0F);
		hand.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.0F, -1.1345F);
		bone3.texOffs(66, 183).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(-3.0F, -5.0F, -20.0F);
		hand.addChild(bone4);
		setRotationAngle(bone4, 0.0F, 0.0F, -0.4363F);
		bone4.texOffs(76, 184).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(-2.0F, -5.0F, -20.0F);
		hand.addChild(bone5);
		bone5.texOffs(88, 184).addBox(-1.0F, -4.0F, 0.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(-1.0F, -5.0F, -20.0F);
		hand.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 0.0F, 0.4363F);
		bone6.texOffs(101, 185).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setPos(-1.0F, -4.0F, -20.0F);
		hand.addChild(bone7);
		setRotationAngle(bone7, 0.0F, 0.0F, 0.5236F);
		bone7.texOffs(114, 184).addBox(0.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-14.0F, -27.0F, 0.0F);
		up.addChild(right_hand);
		setRotationAngle(right_hand, 0.0F, 3.1416F, 0.0F);
		right_hand.texOffs(4, 143).addBox(-5.0F, -5.0F, -5.0F, 20.0F, 1.0F, 10.0F, 0.0F, false);
		right_hand.texOffs(53, 160).addBox(-5.0F, -4.0F, -6.0F, 16.0F, 6.0F, 1.0F, 0.0F, false);
		right_hand.texOffs(62, 146).addBox(-5.0F, -4.0F, 5.0F, 16.0F, 6.0F, 1.0F, 0.0F, false);
		right_hand.texOffs(100, 157).addBox(-5.0F, -4.0F, -5.0F, 1.0F, 6.0F, 10.0F, 0.0F, false);
		right_hand.texOffs(4, 124).addBox(-5.0F, 2.0F, -5.0F, 15.0F, 3.0F, 10.0F, 0.0F, false);
		right_hand.texOffs(107, 142).addBox(11.0F, -4.0F, -6.0F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		right_hand.texOffs(124, 151).addBox(13.0F, -4.0F, -6.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		right_hand.texOffs(135, 160).addBox(11.0F, -4.0F, 5.0F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		right_hand.texOffs(172, 172).addBox(13.0F, -4.0F, 5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		right_hand.texOffs(7, 97).addBox(-5.0F, 5.0F, -5.0F, 10.0F, 6.0F, 10.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(9.0F, 4.0F, 0.0F);
		right_hand.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, -0.8727F);
		bone2.texOffs(63, 127).addBox(2.0F, -1.0F, -5.0F, 8.0F, 1.0F, 10.0F, 0.0F, false);

		hand2 = new ModelRenderer(this);
		hand2.setPos(2.0F, 7.0F, 1.0F);
		right_hand.addChild(hand2);
		setRotationAngle(hand2, 0.0F, 3.1416F, 0.0F);
		hand2.texOffs(61, 92).addBox(0.0609F, -1.0F, -20.6055F, 4.0F, 4.0F, 24.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setPos(5.0609F, 2.0F, -20.6055F);
		hand2.addChild(bone8);
		setRotationAngle(bone8, 0.0F, 0.0F, 1.1345F);
		bone8.texOffs(131, 129).addBox(-1.4226F, -1.0937F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setPos(1.0609F, 0.0F, -20.6055F);
		hand2.addChild(bone9);
		setRotationAngle(bone9, 0.0F, 0.0F, -0.6981F);
		bone9.texOffs(156, 136).addBox(-0.9128F, -2.9962F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		bone10 = new ModelRenderer(this);
		bone10.setPos(2.0609F, 0.0F, -20.6055F);
		hand2.addChild(bone10);
		setRotationAngle(bone10, 0.0F, 0.0F, -0.3491F);
		bone10.texOffs(180, 152).addBox(-0.9128F, -3.9962F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		bone11 = new ModelRenderer(this);
		bone11.setPos(4.0609F, -1.0F, -20.6055F);
		hand2.addChild(bone11);
		bone11.texOffs(153, 92).addBox(-1.9962F, -3.9128F, 0.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);

		bone12 = new ModelRenderer(this);
		bone12.setPos(3.0F, 1.0F, -20.6055F);
		hand2.addChild(bone12);
		setRotationAngle(bone12, 0.0F, 0.0F, 0.5236F);
		bone12.texOffs(169, 120).addBox(-0.8051F, -4.5F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void updateFreeParts(FootballZombieEntity entity) {
		super.updateFreeParts(entity);
		final boolean hasMetal = entity.hasMetal();
		this.helmet.visible = hasMetal;
	}
	
	@Override
	protected void doWalkAnimation(float limbSwing, float limbSwingAmount, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw / (180F / (float)Math.PI);
        this.head.xRot = headPitch / (180F / (float)Math.PI);
        this.right_leg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 1.5f;
        this.left_leg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount * 1.5f;
        this.right_hand.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / 3;
        this.left_hand.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / 3;
	}
	
	@Override
	protected void doPreAttackPose() {
		//can hand attack
		if(this.isLeftHandFree || this.isRightHandFree) {
			if(this.isLeftHandFree) {
				this.getZombieLeftHand().xRot = HAND_MAX_ANGLE / 3;
			}
			if(this.isRightHandFree) {
				this.getZombieRightHand().xRot = - HAND_MAX_ANGLE / 3;
			}
		} 
	}
	
	@Override
	protected void doHandEat(FootballZombieEntity entity) {
		if(this.isLeftHandFree) {
			this.getZombieLeftHand().xRot = HAND_MAX_ANGLE / 3 - AnimationUtil.getUpDown(MAX_ANIM_CD - entity.getAnimTime(), MAX_ANIM_CD, - 70);
		}
		if(this.isRightHandFree) {
			this.getZombieRightHand().xRot = - HAND_MAX_ANGLE / 3 + AnimationUtil.getUpDown(MAX_ANIM_CD - entity.getAnimTime(), MAX_ANIM_CD, - 70);
		}
	}
	
	@Override
	public Optional<ModelRenderer> getHelmet() {
		return Optional.ofNullable(this.helmet);
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
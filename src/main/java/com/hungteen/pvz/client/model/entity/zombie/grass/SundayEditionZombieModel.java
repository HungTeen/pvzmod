package com.hungteen.pvz.client.model.entity.zombie.grass;

import java.util.Optional;

import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.hungteen.pvz.common.entity.zombie.grass.SundayEditionZombieEntity;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class SundayEditionZombieModel extends PVZZombieModel<SundayEditionZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer head;
	private final ModelRenderer hair;
	private final ModelRenderer bone7;
	private final ModelRenderer bone6;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer glass;
	private final ModelRenderer red_eyes;
	private final ModelRenderer normal_eyes;
	private final ModelRenderer right_hand;
	private final ModelRenderer left_hand;
	private final ModelRenderer body;
	private final ModelRenderer paper;
	private final ModelRenderer bone2;
	private final ModelRenderer bone;

	public SundayEditionZombieModel() {
		texHeight = 256;
		texWidth = 256;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-4.0F, -24.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.texOffs(230, 192).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 22.0F, 6.0F, 0.0F, false);
		right_leg.texOffs(191, 220).addBox(-3.0F, 22.0F, -5.0F, 6.0F, 2.0F, 9.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(4.0F, -24.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.texOffs(230, 225).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 22.0F, 6.0F, 0.0F, false);
		left_leg.texOffs(196, 242).addBox(-3.0F, 22.0F, -5.0F, 6.0F, 2.0F, 9.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(up);
		

		head = new ModelRenderer(this);
		head.setPos(0.0F, -48.0F, 0.0F);
		up.addChild(head);
		head.texOffs(66, 182).addBox(-7.0F, -14.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);

		hair = new ModelRenderer(this);
		hair.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(hair);
		

		bone7 = new ModelRenderer(this);
		bone7.setPos(0.0F, -14.0F, -2.0F);
		hair.addChild(bone7);
		setRotationAngle(bone7, -0.3491F, 1.0472F, -0.6981F);
		bone7.texOffs(0, 0).addBox(-1.0F, -1.0F, 0.0F, 9.0F, 1.0F, 1.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(-2.0F, -13.0F, 1.0F);
		hair.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 0.0F, 0.5236F);
		bone6.texOffs(169, 208).addBox(-9.0F, -2.0F, -2.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(0.0F, -14.0F, 0.0F);
		hair.addChild(bone4);
		setRotationAngle(bone4, 0.0F, -0.8727F, -0.6981F);
		bone4.texOffs(0, 0).addBox(-1.0F, -1.0F, 0.0F, 9.0F, 1.0F, 1.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(0.0F, -14.0F, 0.0F);
		hair.addChild(bone5);
		setRotationAngle(bone5, 0.0F, 0.0F, -0.6981F);
		bone5.texOffs(130, 207).addBox(-1.0F, -1.0F, -1.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);

		glass = new ModelRenderer(this);
		glass.setPos(0.0F, -10.0F, 0.0F);
		head.addChild(glass);
		setRotationAngle(glass, 0.3491F, 0.0F, 0.0F);
		glass.texOffs(200, 188).addBox(-8.0F, -1.0F, -10.0F, 1.0F, 1.0F, 11.0F, 0.0F, false);
		glass.texOffs(140, 180).addBox(7.0F, -1.0F, -10.0F, 1.0F, 1.0F, 11.0F, 0.0F, false);
		glass.texOffs(183, 187).addBox(-7.0F, -1.0F, -10.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		glass.texOffs(206, 172).addBox(5.0F, -1.0F, -10.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		glass.texOffs(227, 175).addBox(-1.0F, -1.0F, -10.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		glass.texOffs(241, 169).addBox(-5.0F, -2.0F, -10.0F, 4.0F, 3.0F, 1.0F, 0.0F, false);
		glass.texOffs(174, 175).addBox(1.0F, -2.0F, -10.0F, 4.0F, 3.0F, 1.0F, 0.0F, false);

		red_eyes = new ModelRenderer(this);
		red_eyes.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(red_eyes);
		red_eyes.texOffs(5, 187).addBox(-6.0F, -13.0F, -7.1F, 5.0F, 5.0F, 1.0F, 0.0F, false);
		red_eyes.texOffs(4, 176).addBox(1.0F, -13.0F, -7.1F, 5.0F, 5.0F, 1.0F, 0.0F, false);

		normal_eyes = new ModelRenderer(this);
		normal_eyes.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(normal_eyes);
		normal_eyes.texOffs(30, 188).addBox(-6.0F, -13.0F, -7.1F, 5.0F, 5.0F, 1.0F, 0.0F, false);
		normal_eyes.texOffs(26, 178).addBox(1.0F, -13.0F, -7.1F, 5.0F, 5.0F, 1.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-11.0F, -45.0F, 0.0F);
		up.addChild(right_hand);
		setRotationAngle(right_hand, -1.1781F, 0.0F, 0.0F);
		right_hand.texOffs(148, 222).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		paper = new ModelRenderer(this);
		paper.setPos(12.0F, 20.0F, 0.5F);
		right_hand.addChild(paper);
		setRotationAngle(paper, -0.1745F, 0.0F, 0.0F);
		

		bone2 = new ModelRenderer(this);
		bone2.setPos(10.75F, 1.7244F, 2.8059F);
		paper.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.7854F);
		bone2.texOffs(160, 75).addBox(1.7677F, -4.1066F, -28.0F, 1.0F, 22.0F, 45.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(-13.75F, 1.7244F, 2.8059F);
		paper.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, -0.7854F);
		bone.texOffs(160, 4).addBox(-0.8393F, -1.8085F, -28.0F, 1.0F, 22.0F, 45.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(11.0F, -45.0F, 0.0F);
		up.addChild(left_hand);
		setRotationAngle(left_hand, -1.1781F, -0.0873F, 0.0F);
		left_hand.texOffs(59, 221).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, -24.0F, 0.0F);
		up.addChild(body);
		body.texOffs(3, 221).addBox(-8.0F, -24.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);
		
		this.rightHandOriginAngel = - 67.5F;
	}


	@Override
	public void updateFreeParts(SundayEditionZombieEntity entity) {
		super.updateFreeParts(entity);
		final boolean isPaperDestroyed = entity.isAngry();
		this.isLeftHandFree = isPaperDestroyed;
		this.isRightHandFree = isPaperDestroyed;
		this.red_eyes.visible = isPaperDestroyed;
		this.normal_eyes.visible = ! isPaperDestroyed;
		this.paper.visible = ! isPaperDestroyed;
	}
	
	@Override
	public void refreshAnim() {
		this.getZombieLeftHand().xRot = -1.0472F;
		this.getZombieRightHand().xRot = -1.0472F;
	}
	
	@Override
	protected boolean isZombieAngry(SundayEditionZombieEntity entity) {
		return entity.isAngry() || super.isZombieAngry(entity);
	}
	
	@Override
	public Optional<ModelRenderer> getHandDefence() {
		return Optional.ofNullable(this.paper);
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
package com.hungteen.pvz.client.model.entity.zombie.zombotany;

import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.hungteen.pvz.common.entity.zombie.zombotany.JalapenoZombieEntity;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class JalapenoZombieModel extends PVZZombieModel<JalapenoZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer bone13;
	private final ModelRenderer bone14;

	public JalapenoZombieModel() {
		texWidth = 256;
		texHeight = 256;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-4.0F, -24.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.texOffs(44, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(4.0F, -24.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.texOffs(0, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setPos(0.0F, -24.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.texOffs(0, 41).addBox(-8.0F, -24.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(12.0F, -20.0F, 0.0F);
		up.addChild(left_hand);
		left_hand.texOffs(96, 60).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-12.0F, -20.0F, 0.0F);
		up.addChild(right_hand);
		right_hand.texOffs(96, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -24.0F, -2.0F);
		up.addChild(head);
		head.texOffs(191, 221).addBox(-8.0F, -21.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);
		head.texOffs(194, 196).addBox(-7.0F, -5.0F, -7.0F, 14.0F, 2.0F, 14.0F, 0.0F, false);
		head.texOffs(202, 168).addBox(-6.0F, -3.0F, -6.0F, 12.0F, 3.0F, 12.0F, 0.0F, false);
		head.texOffs(210, 141).addBox(-5.0F, 0.0F, -5.0F, 10.0F, 4.0F, 10.0F, 0.0F, false);
		head.texOffs(212, 114).addBox(-4.0F, 4.0F, -5.0F, 8.0F, 5.0F, 8.0F, 0.0F, false);
		head.texOffs(225, 95).addBox(-3.0F, 9.0F, -6.0F, 5.0F, 4.0F, 7.0F, 0.0F, false);
		head.texOffs(224, 70).addBox(-3.0F, 13.0F, -8.0F, 6.0F, 3.0F, 7.0F, 0.0F, false);
		head.texOffs(227, 54).addBox(-3.0F, 16.0F, -12.0F, 6.0F, 3.0F, 8.0F, 0.0F, false);
		head.texOffs(72, 237).addBox(-7.0F, -22.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);
		head.texOffs(10, 235).addBox(-6.0F, -23.0F, -6.0F, 12.0F, 1.0F, 12.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setPos(0.0F, 16.0F, -12.0F);
		head.addChild(bone8);
		setRotationAngle(bone8, 1.0472F, 0.0F, 0.0F);
		bone8.texOffs(226, 32).addBox(-2.0F, -3.0F, -1.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setPos(0.0F, -3.0F, 0.0F);
		bone8.addChild(bone9);
		setRotationAngle(bone9, 0.6981F, 0.0F, 0.0F);
		bone9.texOffs(164, 224).addBox(-2.0F, -0.6428F, -0.766F, 4.0F, 1.0F, 4.0F, 0.0F, false);

		bone10 = new ModelRenderer(this);
		bone10.setPos(0.0F, -0.0152F, 2.8264F);
		bone9.addChild(bone10);
		setRotationAngle(bone10, 0.9599F, 0.0F, 0.0F);
		bone10.texOffs(150, 236).addBox(-1.0F, -0.0261F, -0.2521F, 2.0F, 4.0F, 1.0F, 0.0F, false);

		bone11 = new ModelRenderer(this);
		bone11.setPos(0.0F, -23.0F, 0.0F);
		head.addChild(bone11);
		setRotationAngle(bone11, 0.0F, 0.0F, 0.2618F);
		bone11.texOffs(127, 207).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

		bone12 = new ModelRenderer(this);
		bone12.setPos(1.2247F, -5.2929F, 0.0F);
		bone11.addChild(bone12);
		setRotationAngle(bone12, 0.0F, 0.0F, -0.4363F);
		bone12.texOffs(87, 213).addBox(-2.0F, -2.0F, -1.0F, 6.0F, 2.0F, 2.0F, 0.0F, false);

		bone13 = new ModelRenderer(this);
		bone13.setPos(4.6375F, 2.1433F, 0.0F);
		bone12.addChild(bone13);
		setRotationAngle(bone13, 0.0F, 0.0F, -0.6109F);
		bone13.texOffs(58, 204).addBox(0.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone14 = new ModelRenderer(this);
		bone14.setPos(0.7071F, -0.1213F, 0.0F);
		bone13.addChild(bone14);
		setRotationAngle(bone14, 0.0F, 0.0F, -1.9199F);
		bone14.texOffs(101, 183).addBox(-1.0F, -3.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);
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
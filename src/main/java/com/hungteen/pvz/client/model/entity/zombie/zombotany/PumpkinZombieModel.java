package com.hungteen.pvz.client.model.entity.zombie.zombotany;

import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.hungteen.pvz.common.entity.zombie.zombotany.PumpkinZombieEntity;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.1
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class PumpkinZombieModel extends PVZZombieModel<PumpkinZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer helmet;
	private final ModelRenderer bone;

	public PumpkinZombieModel() {
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
		head.setPos(0.0F, -24.0F, 0.0F);
		up.addChild(head);
		head.texOffs(16, 96).addBox(-8.0F, -17.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		helmet = new ModelRenderer(this);
		helmet.setPos(0.0F, -24.0F, 0.0F);
		up.addChild(helmet);
		helmet.texOffs(142, 4).addBox(-12.0F, -1.0F, -12.0F, 24.0F, 1.0F, 24.0F, 0.0F, false);
		helmet.texOffs(140, 38).addBox(-12.0F, -17.0F, -12.0F, 24.0F, 16.0F, 1.0F, 0.0F, false);
		helmet.texOffs(198, 39).addBox(-12.0F, -17.0F, 11.0F, 24.0F, 16.0F, 1.0F, 0.0F, false);
		helmet.texOffs(140, 63).addBox(-12.0F, -17.0F, -11.0F, 1.0F, 16.0F, 22.0F, 0.0F, false);
		helmet.texOffs(193, 65).addBox(11.0F, -17.0F, -11.0F, 1.0F, 16.0F, 22.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, -17.0F, 0.0F);
		helmet.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, 0.3927F);
		bone.texOffs(244, 111).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);
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
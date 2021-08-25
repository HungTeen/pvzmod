package com.hungteen.pvz.client.model.entity.zombie.pool;

import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.hungteen.pvz.common.entity.zombie.pool.BobsleZombieEntity;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class BobsleZombieModel extends PVZZombieModel<BobsleZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;

	public BobsleZombieModel() {
		texWidth = 256;
		texHeight = 256;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-4.0F, -25.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.texOffs(184, 229).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 21.0F, 4.0F, 0.0F, false);
		right_leg.texOffs(149, 238).addBox(-3.0F, 21.0F, -6.0F, 6.0F, 4.0F, 9.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(4.0F, -25.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.texOffs(238, 228).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 21.0F, 4.0F, 0.0F, false);
		left_leg.texOffs(203, 240).addBox(-3.0F, 21.0F, -6.0F, 6.0F, 4.0F, 9.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setPos(0.0F, -25.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.texOffs(5, 224).addBox(-8.0F, -23.0F, -3.0F, 16.0F, 23.0F, 6.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(10.0F, -21.0F, 0.0F);
		up.addChild(left_hand);
		left_hand.texOffs(54, 227).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 23.0F, 4.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-10.0F, -21.0F, 0.0F);
		up.addChild(right_hand);
		right_hand.texOffs(77, 224).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 23.0F, 4.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -23.0F, 0.0F);
		up.addChild(head);
		head.texOffs(3, 192).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
		head.texOffs(109, 234).addBox(-7.0F, -6.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head.texOffs(59, 199).addBox(-7.0F, -10.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head.texOffs(100, 201).addBox(6.0F, -6.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head.texOffs(142, 200).addBox(6.0F, -10.0F, -7.0F, 1.0F, 1.0F, 14.0F, 0.0F, false);
		head.texOffs(187, 214).addBox(-6.0F, -6.0F, 6.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		head.texOffs(222, 217).addBox(-6.0F, -10.0F, 6.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		head.texOffs(181, 192).addBox(-6.0F, -10.0F, -7.0F, 12.0F, 5.0F, 1.0F, 0.0F, false);
		head.texOffs(222, 198).addBox(-5.0F, -11.0F, -7.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		head.texOffs(134, 187).addBox(-5.0F, -5.0F, -7.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
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
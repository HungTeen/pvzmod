package com.hungteen.pvz.client.model.entity.zombie.pool;

import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.hungteen.pvz.common.entity.zombie.pool.YetiZombieEntity;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class YetiZombieModel extends PVZZombieModel<YetiZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;

	public YetiZombieModel() {
		texWidth = 256;
		texHeight = 256;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 2.0F);
		

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-7.0F, -36.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.texOffs(5, 152).addBox(-5.0F, 0.0F, -6.0F, 12.0F, 36.0F, 12.0F, 0.0F, false);
		right_leg.texOffs(156, 164).addBox(-5.0F, 34.0F, -12.0F, 12.0F, 2.0F, 6.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(6.0F, -36.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.texOffs(8, 206).addBox(-6.0F, 0.0F, -6.0F, 12.0F, 36.0F, 12.0F, 0.0F, false);
		left_leg.texOffs(155, 178).addBox(-6.0F, 34.0F, -12.0F, 12.0F, 2.0F, 6.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setPos(0.0F, -36.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.texOffs(174, 202).addBox(-12.0F, -36.0F, -8.0F, 24.0F, 36.0F, 16.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(18.0F, -30.0F, 0.0F);
		up.addChild(left_hand);
		left_hand.texOffs(204, 144).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 40.0F, 12.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-19.0F, -30.0F, 0.0F);
		up.addChild(right_hand);
		right_hand.texOffs(203, 86).addBox(-5.0F, -6.0F, -6.0F, 12.0F, 40.0F, 12.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -36.0F, 0.0F);
		up.addChild(head);
		head.texOffs(68, 205).addBox(-12.0F, -24.0F, -12.0F, 24.0F, 24.0F, 24.0F, 0.0F, false);
		head.texOffs(156, 195).addBox(-4.0F, -1.0F, -13.0F, 8.0F, 5.0F, 1.0F, 0.0F, false);
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
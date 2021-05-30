package com.hungteen.pvz.client.model.entity.zombie.roof;

import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.hungteen.pvz.common.entity.zombie.roof.ImpEntity;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class ImpModel extends PVZZombieModel<ImpEntity> {
	private final ModelRenderer total;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer right_arm;
	private final ModelRenderer left_arm;
	private final ModelRenderer head;

	public ImpModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		left_leg = new ModelRenderer(this);
		left_leg.setPos(3.0F, -13.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.texOffs(0, 47).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 13.0F, 4.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-3.0F, -12.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.texOffs(0, 29).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 13.0F, 4.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setPos(0.0F, -12.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.texOffs(0, 9).addBox(-5.0F, -16.0F, -2.0F, 10.0F, 15.0F, 4.0F, 0.0F, false);

		right_arm = new ModelRenderer(this);
		right_arm.setPos(-7.0F, -14.0F, 0.0F);
		up.addChild(right_arm);
		right_arm.texOffs(47, 34).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 15.0F, 4.0F, 0.0F, false);

		left_arm = new ModelRenderer(this);
		left_arm.setPos(7.0F, -14.0F, 0.0F);
		up.addChild(left_arm);
		left_arm.texOffs(29, 24).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 15.0F, 4.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -16.0F, 0.0F);
		up.addChild(head);
		head.texOffs(16, 44).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);
	}

	@Override
	public ModelRenderer getZombieLeftHand() {
		return this.left_arm;
	}

	@Override
	public ModelRenderer getZombieRightHand() {
		return this.right_arm;
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
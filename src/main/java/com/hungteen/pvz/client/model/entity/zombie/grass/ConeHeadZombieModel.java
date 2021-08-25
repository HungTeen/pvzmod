package com.hungteen.pvz.client.model.entity.zombie.grass;

import java.util.Optional;

import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.hungteen.pvz.common.entity.zombie.grass.ConeHeadZombieEntity;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.1
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class ConeHeadZombieModel extends PVZZombieModel<ConeHeadZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer defence;
	private final ModelRenderer defence1;
	private final ModelRenderer defence2;
	private final ModelRenderer defence3;

	public ConeHeadZombieModel() {
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
		up.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, -24.0F, 0.0F);
		up.addChild(body);
		body.texOffs(0, 41).addBox(-8.0F, -24.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(12.0F, -44.0F, 0.0F);
		up.addChild(left_hand);
		left_hand.texOffs(96, 60).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-12.0F, -44.0F, 0.0F);
		up.addChild(right_hand);
		right_hand.texOffs(96, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -48.0F, 0.0F);
		up.addChild(head);
		head.texOffs(16, 96).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		defence = new ModelRenderer(this);
		defence.setPos(0.0F, -16.0F, 0.0F);
		head.addChild(defence);
		

		defence3 = new ModelRenderer(this);
		defence3.setPos(0.0F, 17.0F, 0.0F);
		defence.addChild(defence3);
		defence3.texOffs(205, 59).addBox(-2.0F, -37.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
		defence3.texOffs(234, 60).addBox(-1.0F, -39.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		defence2 = new ModelRenderer(this);
		defence2.setPos(0.0F, -6.0F, 0.0F);
		defence.addChild(defence2);
		defence2.texOffs(142, 81).addBox(-3.0F, -11.0F, -3.0F, 6.0F, 4.0F, 6.0F, 0.0F, false);
		defence2.texOffs(217, 26).addBox(-5.0F, -7.0F, -5.0F, 10.0F, 5.0F, 10.0F, 0.0F, false);

		defence1 = new ModelRenderer(this);
		defence1.setPos(0.0F, 1.0F, 0.0F);
		defence.addChild(defence1);
		defence1.texOffs(139, 2).addBox(-8.0F, -3.0F, -8.0F, 16.0F, 2.0F, 16.0F, 0.0F, false);
		defence1.texOffs(216, 4).addBox(-9.0F, -3.0F, -9.0F, 18.0F, 3.0F, 1.0F, 0.0F, false);
		defence1.texOffs(215, 15).addBox(-9.0F, -3.0F, 8.0F, 18.0F, 3.0F, 1.0F, 0.0F, false);
		defence1.texOffs(139, 30).addBox(-9.0F, -3.0F, -8.0F, 1.0F, 3.0F, 16.0F, 0.0F, false);
		defence1.texOffs(181, 28).addBox(8.0F, -3.0F, -8.0F, 1.0F, 3.0F, 16.0F, 0.0F, false);
		defence1.texOffs(139, 56).addBox(-7.0F, -9.0F, -7.0F, 14.0F, 6.0F, 14.0F, 0.0F, false);
	}
	
	@Override
	public void updateFreeParts(ConeHeadZombieEntity entity) {
		super.updateFreeParts(entity);
		this.defence3.visible = entity.hasConeHead(3);
		this.defence2.visible = entity.hasConeHead(2);
		this.defence1.visible = entity.hasConeHead(1);
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
	
	@Override
	public Optional<ModelRenderer> getHelmet() {
		return Optional.ofNullable(this.defence);
	}

}
package com.hungteen.pvz.client.model.entity.zombie.zombotany;

import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.hungteen.pvz.common.entity.zombie.zombotany.WallNutZombieEntity;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class WallNutZombieModel extends PVZZombieModel<WallNutZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer face;
	private final ModelRenderer stage1;
	private final ModelRenderer stage2;
	private final ModelRenderer stage3;

	public WallNutZombieModel() {
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
		head.texOffs(92, 134).addBox(-8.0F, -1.0F, -8.0F, 16.0F, 1.0F, 16.0F, 0.0F, false);
		head.texOffs(0, 135).addBox(-8.0F, -26.0F, -8.0F, 16.0F, 2.0F, 16.0F, 0.0F, false);
		head.texOffs(0, 180).addBox(-10.0F, -25.0F, -10.0F, 20.0F, 24.0F, 20.0F, 0.0F, false);
		head.texOffs(86, 219).addBox(-11.0F, -23.0F, -8.0F, 1.0F, 20.0F, 16.0F, 0.0F, false);
		head.texOffs(88, 178).addBox(10.0F, -23.0F, -8.0F, 1.0F, 20.0F, 16.0F, 0.0F, false);

		face = new ModelRenderer(this);
		face.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(face);
		face.texOffs(0, 248).addBox(-1.0F, -11.0F, -10.1F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		stage1 = new ModelRenderer(this);
		stage1.setPos(0.0F, -10.0F, -10.25F);
		face.addChild(stage1);
		stage1.texOffs(1, 252).addBox(-2.0F, -2.0F, 0.15F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		stage1.texOffs(0, 243).addBox(1.0F, -2.0F, 0.15F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		stage2 = new ModelRenderer(this);
		stage2.setPos(0.0F, -10.0F, -10.25F);
		face.addChild(stage2);
		stage2.texOffs(1, 252).addBox(-2.0F, -1.0F, 0.15F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		stage2.texOffs(0, 243).addBox(1.0F, -1.0F, 0.15F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		stage3 = new ModelRenderer(this);
		stage3.setPos(0.0F, -10.0F, -10.25F);
		face.addChild(stage3);
		stage3.texOffs(1, 252).addBox(-2.0F, 0.0F, 0.15F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		stage3.texOffs(0, 243).addBox(1.0F, 0.0F, 0.15F, 1.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void updateFreeParts(WallNutZombieEntity entity) {
		super.updateFreeParts(entity);
		final float percent = entity.getHealth() / entity.getMaxHealth();
		this.stage1.visible = (percent > 2F / 3); 
        this.stage2.visible = (percent > 1F / 3); 
        this.stage3.visible = (percent <= 1F / 3); 
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
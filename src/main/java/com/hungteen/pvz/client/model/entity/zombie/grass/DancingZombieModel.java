package com.hungteen.pvz.client.model.entity.zombie.grass;

import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.hungteen.pvz.common.entity.zombie.grass.DancingZombieEntity;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class DancingZombieModel extends PVZZombieModel<DancingZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer body;

	public DancingZombieModel() {
		texWidth = 256;
		texHeight = 256;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		right_leg = new ModelRenderer(this);
		right_leg.setPos(5.0F, -30.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.texOffs(218, 239).addBox(-4.0F, 24.0F, -6.0F, 8.0F, 6.0F, 11.0F, 0.0F, false);
		right_leg.texOffs(0, 226).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(-5.0F, -30.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.texOffs(36, 239).addBox(-4.0F, 24.0F, -6.0F, 8.0F, 6.0F, 11.0F, 0.0F, false);
		left_leg.texOffs(96, 226).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setPos(0.0F, -30.0F, 0.0F);
		total.addChild(up);
		

		left_hand = new ModelRenderer(this);
		left_hand.setPos(11.0F, -21.0F, 1.0F);
		up.addChild(left_hand);
		left_hand.texOffs(60, 188).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-11.0F, -22.0F, 0.0F);
		up.addChild(right_hand);
		right_hand.texOffs(6, 179).addBox(-3.0F, -2.0F, -2.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -24.0F, 0.0F);
		up.addChild(head);
		head.texOffs(0, 0).addBox(-6.0F, -12.0F, -5.0F, 12.0F, 12.0F, 12.0F, 0.0F, false);
		head.texOffs(200, 204).addBox(-7.0F, -18.0F, -5.0F, 14.0F, 6.0F, 14.0F, 0.0F, false);
		head.texOffs(232, 177).addBox(-8.0F, -12.0F, -1.0F, 2.0F, 5.0F, 10.0F, 0.0F, false);
		head.texOffs(132, 204).addBox(6.0F, -12.0F, -1.0F, 2.0F, 5.0F, 10.0F, 0.0F, false);
		head.texOffs(162, 212).addBox(-6.0F, -12.0F, 7.0F, 12.0F, 5.0F, 2.0F, 0.0F, false);
		head.texOffs(70, 228).addBox(-7.0F, -9.0F, -7.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		head.texOffs(31, 228).addBox(6.0F, -9.0F, -7.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		head.texOffs(110, 216).addBox(-1.0F, -9.0F, -7.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		head.texOffs(198, 197).addBox(-6.0F, -11.0F, -7.0F, 5.0F, 5.0F, 1.0F, 0.0F, false);
		head.texOffs(205, 176).addBox(1.0F, -11.0F, -7.0F, 5.0F, 5.0F, 1.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.texOffs(141, 224).addBox(-8.0F, -24.0F, -3.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(DancingZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if(entity.getSummonTime() > 0) {
			this.total.yRot = 0;
			this.left_hand.xRot = 0;
			this.right_hand.xRot = - 2.5f;
			this.left_leg.xRot = 0;
			this.right_leg.xRot = 0;
			return ;
		}
		if(entity.getAttackTime() > 0) {
			int tick = entity.getAttackTime();
			int max = DancingZombieEntity.DANCE_CD;
			this.total.yRot = - MathHelper.sin(3.14159f * 2 * tick / max);
			this.right_hand.xRot = - 3 * MathHelper.abs(MathHelper.sin(3.14159f * 4 * tick / max));
			this.left_hand.xRot = - 3 * MathHelper.abs(MathHelper.sin(3.14159f * 4 * tick / max));
			this.left_leg.xRot = 0;
			this.right_leg.xRot = 0;
			return ;
		}
		
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}
	
	@Override
	public void refreshAnim() {
		this.total.yRot = 0;
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
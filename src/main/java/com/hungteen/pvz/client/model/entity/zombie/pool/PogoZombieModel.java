package com.hungteen.pvz.client.model.entity.zombie.pool;

import com.hungteen.pvz.api.interfaces.IBodyEntity;
import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.hungteen.pvz.common.entity.zombie.pool.PogoZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class PogoZombieModel extends PVZZombieModel<PogoZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer zombie;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer bone3;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer pogo;

	public PogoZombieModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 2.0F);
		setRotationAngle(total, 0.0873F, 0.0F, 0.0F);
		

		zombie = new ModelRenderer(this);
		zombie.setPos(0.0F, -6.0F, 5.0F);
		total.addChild(zombie);
		

		up = new ModelRenderer(this);
		up.setPos(0.0F, -24.0F, 0.0F);
		zombie.addChild(up);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, -7.0F, 0.0F);
		up.addChild(body);
		body.texOffs(80, 96).addBox(-8.0F, -17.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(12.0F, -20.0F, 0.0F);
		up.addChild(left_hand);
		setRotationAngle(left_hand, -0.4363F, 0.0873F, 0.0F);
		left_hand.texOffs(104, 62).addBox(-4.0F, -4.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-12.0F, -20.0F, 0.0F);
		up.addChild(right_hand);
		setRotationAngle(right_hand, -0.4363F, -0.0873F, 0.0F);
		right_hand.texOffs(103, 26).addBox(-2.0F, -4.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -24.0F, 0.0F);
		up.addChild(head);
		head.texOffs(20, 100).addBox(-7.0F, -14.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, -3.091F, -8.0834F);
		head.addChild(bone3);
		bone3.texOffs(76, 12).addBox(-8.0F, -5.2921F, -0.2749F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		bone3.texOffs(105, 12).addBox(7.0F, -5.2921F, -0.2749F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		bone3.texOffs(56, 18).addBox(-7.0F, -5.2921F, -0.2749F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bone3.texOffs(36, 14).addBox(5.0F, -5.2921F, -0.2749F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bone3.texOffs(16, 21).addBox(-1.0F, -5.2921F, -0.2749F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bone3.texOffs(20, 4).addBox(-5.0F, -6.2921F, -0.2749F, 4.0F, 3.0F, 1.0F, 0.0F, false);
		bone3.texOffs(2, 10).addBox(1.0F, -6.2921F, -0.2749F, 4.0F, 3.0F, 1.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(4.0F, -24.0F, 0.0F);
		zombie.addChild(left_leg);
		setRotationAngle(left_leg, -0.4363F, -0.2618F, 0.0F);
		left_leg.texOffs(58, 62).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-4.0F, -24.0F, 0.0F);
		zombie.addChild(right_leg);
		setRotationAngle(right_leg, -0.4363F, 0.2618F, 0.0F);
		right_leg.texOffs(67, 27).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		pogo = new ModelRenderer(this);
		pogo.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(pogo);
		pogo.texOffs(24, 85).addBox(-1.0F, -10.0F, -6.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
		pogo.texOffs(36, 66).addBox(-2.0F, -22.0F, -7.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		pogo.texOffs(32, 32).addBox(-3.0F, -38.0F, -8.0F, 6.0F, 16.0F, 6.0F, 0.0F, false);
		pogo.texOffs(0, 87).addBox(3.0F, -34.0F, -6.0F, 9.0F, 2.0F, 2.0F, 0.0F, false);
		pogo.texOffs(6, 51).addBox(-8.0F, -8.0F, -6.0F, 7.0F, 2.0F, 2.0F, 0.0F, false);
		pogo.texOffs(2, 67).addBox(1.0F, -8.0F, -6.0F, 7.0F, 2.0F, 2.0F, 0.0F, false);
		pogo.texOffs(4, 37).addBox(-12.0F, -34.0F, -6.0F, 9.0F, 2.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(PogoZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if(entity.hasPogo()) {
			this.left_hand.xRot = -0.4363F;
			this.left_hand.yRot = 0.0873F;
			this.right_hand.xRot = -0.4363F;
			this.right_hand.yRot = -0.0873F;
			this.left_leg.xRot = -0.4363F;
			this.left_leg.yRot = -0.2618F;
			this.right_leg.xRot = -0.4363F;
			this.right_leg.yRot = 0.2618F;
			this.total.xRot = 0.0873F;
			this.head.yRot = netHeadYaw / (180F / (float)Math.PI);
            this.head.xRot = headPitch / (180F / (float)Math.PI);
		} else {
	        this.left_hand.yRot = 0;
	        this.right_hand.yRot = 0;
			this.left_leg.yRot = 0;
	        this.right_leg.yRot = 0;
	        this.total.xRot = 0;
	        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
		this.updateFreeParts(entity);
		this.pogo.visible = entity.hasMetal();
	}
	
	@Override
	public void tickPartAnim(IBodyEntity entity, float limbSwing, float limbSwingAmount,
			float ageInTicks, float netHeadYaw, float headPitch) {
		this.left_hand.yRot = 0;
        this.right_hand.yRot = 0;
		this.left_leg.yRot = 0;
        this.right_leg.yRot = 0;
        this.left_leg.xRot = 0;
        this.right_leg.xRot = 0;
        this.total.xRot = 0;
		super.tickPartAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}
	
	@Override
	public void renderBody(IBodyEntity entity, MatrixStack stack, IVertexBuilder buffer, int packedLight,
			int packedOverlay) {
		this.pogo.visible = false;
		super.renderBody(entity, stack, buffer, packedLight, packedOverlay);
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
package com.hungteen.pvz.client.model.entity.zombie.roof;

import com.hungteen.pvz.common.entity.zombie.roof.BungeeZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class BungeeZombieModel extends EntityModel<BungeeZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;

	public BungeeZombieModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, -12.0F, 0.0F);
		setRotationAngle(total, 0.7854F, 0.0F, 0.0F);
		

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-4.0F, 12.0F, 0.0F);
		total.addChild(right_leg);
		setRotationAngle(right_leg, -0.7854F, 0.0F, 0.5236F);
		right_leg.texOffs(44, 0).addBox(-4.25F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(4.0F, 12.0F, 0.0F);
		total.addChild(left_leg);
		setRotationAngle(left_leg, -0.7854F, 0.0F, -0.5236F);
		left_leg.texOffs(0, 0).addBox(-3.75F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setPos(0.0F, 12.0F, 0.0F);
		total.addChild(up);
		setRotationAngle(up, 0.5236F, 0.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, -7.0F, 0.0F);
		up.addChild(body);
		body.texOffs(0, 41).addBox(-8.0F, -17.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(12.0F, -20.0F, 0.0F);
		up.addChild(left_hand);
		setRotationAngle(left_hand, -1.5708F, -0.2182F, 0.0F);
		left_hand.texOffs(96, 60).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-12.0F, -20.0F, 0.0F);
		up.addChild(right_hand);
		setRotationAngle(right_hand, -1.6144F, 0.2182F, 0.0F);
		right_hand.texOffs(96, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -24.0F, 0.0F);
		up.addChild(head);
		setRotationAngle(head, -1.0908F, 0.0F, 0.0F);
		head.texOffs(16, 96).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(BungeeZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if(! entity.getPassengers().isEmpty()) {
			int time = Math.max(0, entity.getAttackTime());
			this.left_hand.yRot = - 0.2182F + (1 - time * 1F / entity.getStayTime()) * 0.5F;
		    this.right_hand.yRot = 0.2182F - (1 - time * 1F / entity.getStayTime()) * 0.5F;
		} else {
			this.left_hand.yRot = - 0.2182F;
			this.right_hand.yRot = 0.2182F;
		}
		
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		total.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
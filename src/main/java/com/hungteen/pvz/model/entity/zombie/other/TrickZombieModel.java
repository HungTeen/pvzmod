package com.hungteen.pvz.model.entity.zombie.other;

import com.hungteen.pvz.entity.zombie.other.TrickZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.1
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class TrickZombieModel extends EntityModel<TrickZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer right_arm;
	private final ModelRenderer left_arm;
	private final ModelRenderer head;

	public TrickZombieModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		left_leg = new ModelRenderer(this);
		left_leg.setPos(2.0F, -12.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.texOffs(47, 24).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-3.0F, -12.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.texOffs(47, 1).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setPos(0.0F, -12.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.texOffs(1, 1).addBox(-5.0F, -16.0F, -2.0F, 10.0F, 14.0F, 4.0F, 0.0F, false);

		right_arm = new ModelRenderer(this);
		right_arm.setPos(-7.0F, -14.0F, 0.0F);
		up.addChild(right_arm);
		right_arm.texOffs(30, 2).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, false);

		left_arm = new ModelRenderer(this);
		left_arm.setPos(7.0F, -14.0F, 0.0F);
		up.addChild(left_arm);
		left_arm.texOffs(29, 22).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -16.0F, 0.0F);
		up.addChild(head);
		head.texOffs(23, 43).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(TrickZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.yRot = netHeadYaw / (180F / (float)Math.PI);
        this.head.xRot = headPitch / (180F / (float)Math.PI);
        this.right_leg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.right_arm.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_arm.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
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
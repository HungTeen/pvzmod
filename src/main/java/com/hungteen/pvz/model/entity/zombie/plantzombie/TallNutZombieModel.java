package com.hungteen.pvz.model.entity.zombie.plantzombie;

import com.hungteen.pvz.entity.zombie.zombotany.TallNutZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class TallNutZombieModel extends EntityModel<TallNutZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_hand;
	private final ModelRenderer left_hand;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer head;

	public TallNutZombieModel() {
		texWidth = 256;
		texHeight = 256;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-12.0F, -44.0F, 0.0F);
		total.addChild(right_hand);
		right_hand.texOffs(96, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(12.0F, -44.0F, 0.0F);
		total.addChild(left_hand);
		left_hand.texOffs(96, 60).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setPos(0.0F, -24.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.texOffs(0, 41).addBox(-8.0F, -24.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-4.0F, 0.0F, 0.0F);
		up.addChild(right_leg);
		right_leg.texOffs(44, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(4.0F, 0.0F, 0.0F);
		up.addChild(left_leg);
		left_leg.texOffs(0, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -24.0F, 0.0F);
		up.addChild(head);
		head.texOffs(172, 194).addBox(-10.0F, -40.0F, -10.0F, 20.0F, 40.0F, 20.0F, 0.0F, false);
		head.texOffs(182, 156).addBox(-9.0F, -41.0F, -9.0F, 18.0F, 1.0F, 18.0F, 0.0F, false);
		head.texOffs(219, 4).addBox(-11.0F, -39.0F, -9.0F, 1.0F, 38.0F, 18.0F, 0.0F, false);
		head.texOffs(2, 208).addBox(11.0F, -37.0F, -7.0F, 1.0F, 34.0F, 14.0F, 0.0F, false);
		head.texOffs(42, 170).addBox(10.0F, -39.0F, -9.0F, 1.0F, 38.0F, 18.0F, 0.0F, false);
		head.texOffs(93, 178).addBox(-12.0F, -37.0F, -7.0F, 1.0F, 34.0F, 14.0F, 0.0F, false);
		head.texOffs(219, 68).addBox(-9.0F, -39.0F, -11.0F, 18.0F, 38.0F, 1.0F, 0.0F, false);
		head.texOffs(176, 3).addBox(-7.0F, -37.0F, -12.0F, 14.0F, 34.0F, 1.0F, 0.0F, false);
		head.texOffs(159, 52).addBox(-9.0F, -39.0F, 10.0F, 18.0F, 38.0F, 1.0F, 0.0F, false);
		head.texOffs(124, 112).addBox(-7.0F, -37.0F, 11.0F, 14.0F, 34.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(TallNutZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.yRot = netHeadYaw / (180F / (float)Math.PI);
        this.head.xRot = headPitch / (180F / (float)Math.PI);
        this.right_leg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.right_hand.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_hand.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
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
package com.hungteen.pvz.model.entity.zombie.grassday;

import com.hungteen.pvz.entity.zombie.grassday.ConeHeadZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.1
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class ConeHeadZombieModel extends EntityModel<ConeHeadZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer head;
	private final ModelRenderer defence1;
	private final ModelRenderer defence2;
	private final ModelRenderer defence3;
	private final ModelRenderer right_hand;
	private final ModelRenderer left_hand;
	private final ModelRenderer body;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;

	public ConeHeadZombieModel() {
		texWidth = 256;
		texHeight = 256;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		head = new ModelRenderer(this);
		head.setPos(0.0F, -48.0F, 0.0F);
		total.addChild(head);
		head.texOffs(16, 96).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		defence1 = new ModelRenderer(this);
		defence1.setPos(0.0F, -15.0F, 0.0F);
		head.addChild(defence1);
		defence1.texOffs(139, 2).addBox(-8.0F, -3.0F, -8.0F, 16.0F, 2.0F, 16.0F, 0.0F, false);
		defence1.texOffs(216, 4).addBox(-9.0F, -3.0F, -9.0F, 18.0F, 3.0F, 1.0F, 0.0F, false);
		defence1.texOffs(215, 15).addBox(-9.0F, -3.0F, 8.0F, 18.0F, 3.0F, 1.0F, 0.0F, false);
		defence1.texOffs(139, 30).addBox(-9.0F, -3.0F, -8.0F, 1.0F, 3.0F, 16.0F, 0.0F, false);
		defence1.texOffs(181, 28).addBox(8.0F, -3.0F, -8.0F, 1.0F, 3.0F, 16.0F, 0.0F, false);
		defence1.texOffs(139, 56).addBox(-7.0F, -9.0F, -7.0F, 14.0F, 6.0F, 14.0F, 0.0F, false);

		defence2 = new ModelRenderer(this);
		defence2.setPos(0.0F, -22.0F, 0.0F);
		head.addChild(defence2);
		defence2.texOffs(142, 81).addBox(-3.0F, -11.0F, -3.0F, 6.0F, 4.0F, 6.0F, 0.0F, false);
		defence2.texOffs(217, 26).addBox(-5.0F, -7.0F, -5.0F, 10.0F, 5.0F, 10.0F, 0.0F, false);

		defence3 = new ModelRenderer(this);
		defence3.setPos(0.0F, 1.0F, 0.0F);
		head.addChild(defence3);
		defence3.texOffs(205, 59).addBox(-2.0F, -37.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
		defence3.texOffs(234, 60).addBox(-1.0F, -39.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-8.0F, -48.0F, 0.0F);
		total.addChild(right_hand);
		right_hand.texOffs(96, 0).addBox(-8.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(8.0F, -48.0F, 0.0F);
		total.addChild(left_hand);
		left_hand.texOffs(96, 60).addBox(0.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, -35.0F, 0.0F);
		total.addChild(body);
		body.texOffs(0, 41).addBox(-8.0F, -13.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-5.0F, -23.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.texOffs(44, 0).addBox(-3.0F, -1.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(3.0F, -23.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.texOffs(0, 0).addBox(-3.0F, -1.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(ConeHeadZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.yRot = netHeadYaw / (180F / (float)Math.PI);
        this.head.xRot = headPitch / (180F / (float)Math.PI);
        this.right_leg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.right_hand.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_hand.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        float percent = entity.getDefenceLife() / ConeHeadZombieEntity.CONE_HEALTH;
        this.defence3.visible = percent > 2.0f / 3;
		this.defence2.visible = percent > 1.0f / 3;
		this.defence1.visible = percent > 0;
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
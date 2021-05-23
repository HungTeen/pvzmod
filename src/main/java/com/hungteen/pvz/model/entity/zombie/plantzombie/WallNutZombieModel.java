package com.hungteen.pvz.model.entity.zombie.plantzombie;

import com.hungteen.pvz.entity.zombie.zombotany.WallNutZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class WallNutZombieModel extends EntityModel<WallNutZombieEntity> {
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
		head.texOffs(86, 221).addBox(-11.0F, -23.0F, -8.0F, 1.0F, 20.0F, 16.0F, 0.0F, false);
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
	public void setupAnim(WallNutZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.yRot = netHeadYaw / (180F / (float)Math.PI);
        this.head.xRot = headPitch / (180F / (float)Math.PI);
        this.left_leg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.right_leg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.right_hand.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_hand.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.stage1.visible = (entity.getHealth() / entity.getMaxHealth() > 2F / 3); 
        this.stage2.visible = (entity.getHealth() / entity.getMaxHealth() > 1F / 3); 
        this.stage3.visible = (entity.getHealth() / entity.getMaxHealth() <= 1F / 3); 
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
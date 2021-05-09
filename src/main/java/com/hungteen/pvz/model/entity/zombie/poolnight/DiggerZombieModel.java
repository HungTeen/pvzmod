package com.hungteen.pvz.model.entity.zombie.poolnight;

import com.hungteen.pvz.entity.zombie.poolnight.DiggerZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class DiggerZombieModel extends EntityModel<DiggerZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer pickaxe;
	private final ModelRenderer bone;
	private final ModelRenderer head;
	private final ModelRenderer hat;

	public DiggerZombieModel() {
		texWidth = 256;
		texHeight = 256;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-4.0F, -24.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.texOffs(221, 221).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(4.0F, -24.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.texOffs(182, 220).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setPos(0.0F, -24.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, -7.0F, 0.0F);
		up.addChild(body);
		body.texOffs(122, 217).addBox(-8.0F, -17.0F, -5.0F, 16.0F, 24.0F, 11.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(12.0F, -20.0F, 0.0F);
		up.addChild(left_hand);
		left_hand.texOffs(83, 220).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-12.0F, -20.0F, 0.0F);
		up.addChild(right_hand);
		right_hand.texOffs(43, 219).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		pickaxe = new ModelRenderer(this);
		pickaxe.setPos(0.0F, 18.5858F, -6.2426F);
		right_hand.addChild(pickaxe);
		setRotationAngle(pickaxe, -0.1745F, 0.0F, 0.0F);
		pickaxe.texOffs(8, 231).addBox(-1.0F, -11.0434F, -18.7538F, 2.0F, 16.0F, 2.0F, 0.1F, false);
		pickaxe.texOffs(193, 177).addBox(-1.0F, -1.0F, -19.0F, 2.0F, 2.0F, 28.0F, -0.1F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 6.0F, -18.0F);
		pickaxe.addChild(bone);
		setRotationAngle(bone, 0.5236F, 0.0F, 0.0F);
		bone.texOffs(27, 243).addBox(-1.0F, -1.1439F, -0.1677F, 2.0F, 7.0F, 2.0F, 0.1F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -24.0F, 0.0F);
		up.addChild(head);
		head.texOffs(118, 177).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		hat = new ModelRenderer(this);
		hat.setPos(0.0F, -16.0F, 0.0F);
		head.addChild(hat);
		hat.texOffs(44, 192).addBox(-9.0F, -1.0F, -8.0F, 18.0F, 1.0F, 17.0F, 0.0F, false);
		hat.texOffs(52, 173).addBox(-8.0F, -3.0F, -6.0F, 16.0F, 2.0F, 12.0F, 0.0F, false);
		hat.texOffs(57, 159).addBox(-8.0F, -5.0F, -3.0F, 16.0F, 2.0F, 7.0F, 0.0F, false);
		hat.texOffs(120, 168).addBox(-8.0F, -6.0F, 0.0F, 16.0F, 1.0F, 3.0F, 0.0F, false);
		hat.texOffs(170, 161).addBox(-1.0F, -4.0F, -9.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
		hat.texOffs(200, 161).addBox(-2.0F, -5.0F, -11.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
		hat.texOffs(213, 143).addBox(-9.0F, 0.0F, 8.0F, 18.0F, 2.0F, 1.0F, 0.0F, false);
		hat.texOffs(166, 143).addBox(-9.0F, 0.0F, -9.0F, 18.0F, 1.0F, 1.0F, 0.0F, false);
		hat.texOffs(123, 131).addBox(8.0F, 0.0F, -8.0F, 1.0F, 1.0F, 16.0F, 0.0F, false);
		hat.texOffs(79, 132).addBox(-9.0F, 0.0F, -8.0F, 1.0F, 1.0F, 16.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(DiggerZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if(entity.getAnimTime() > 0 && entity.getAnimTime() < DiggerZombieEntity.MAX_ANIM_TIME) {
			total.yRot = ageInTicks / 1f;
		} else {
			total.yRot = 0;
		}
		if(entity.hasPickaxe()) {
	        this.right_hand.xRot = - 1.57F + MathHelper.sin(ageInTicks) * 0.5F;
		} else {
			this.right_hand.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		}
		this.pickaxe.visible = entity.hasPickaxe();
		this.head.yRot = netHeadYaw / (180F / (float)Math.PI);
        this.head.xRot = headPitch / (180F / (float)Math.PI);
        this.right_leg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.left_hand.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.pickaxe.visible = entity.hasMetal();
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
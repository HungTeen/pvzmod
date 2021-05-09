package com.hungteen.pvz.model.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.poolday.LavaZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class LavaZombieModel extends EntityModel<LavaZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer head;
	private final ModelRenderer right_hand;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer left_hand;
	private final ModelRenderer armor;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer body;
	private final ModelRenderer right_leg;
	private final ModelRenderer right_foot;
	private final ModelRenderer left_leg;
	private final ModelRenderer left_foot;

	public LavaZombieModel() {
		texWidth = 256;
		texHeight = 256;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 22.0F, 0.0F);
		

		head = new ModelRenderer(this);
		head.setPos(0.0F, -48.0F, 0.0F);
		total.addChild(head);
		head.texOffs(200, 228).addBox(-7.0F, -14.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
		head.texOffs(163, 236).addBox(7.0F, -9.0F, -8.0F, 1.0F, 4.0F, 15.0F, 0.0F, false);
		head.texOffs(224, 206).addBox(-8.0F, -9.0F, -8.0F, 1.0F, 4.0F, 15.0F, 0.0F, false);
		head.texOffs(180, 218).addBox(-7.0F, -9.0F, 7.0F, 14.0F, 4.0F, 1.0F, 0.0F, false);
		head.texOffs(226, 193).addBox(-7.0F, -10.0F, -8.0F, 14.0F, 6.0F, 1.0F, 0.0F, false);
		head.texOffs(191, 204).addBox(-6.0F, -4.0F, -8.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		head.texOffs(230, 184).addBox(-6.0F, -11.0F, -8.0F, 12.0F, 1.0F, 1.0F, 0.0F, false);
		head.texOffs(238, 176).addBox(-4.0F, -3.0F, -8.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		head.texOffs(238, 167).addBox(-4.0F, -12.0F, -8.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-8.0F, -48.0F, 0.0F);
		total.addChild(right_hand);
		right_hand.texOffs(232, 0).addBox(-6.0F, 0.0F, -3.0F, 6.0F, 25.0F, 6.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(-2.0F, 0.0F, 0.0F);
		right_hand.addChild(bone3);
		bone3.texOffs(238, 54).addBox(-5.0F, 3.0F, -4.0F, 1.0F, 6.0F, 8.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(-4.0F, 5.0F, -2.0F);
		bone3.addChild(bone4);
		setRotationAngle(bone4, 0.0F, -1.1345F, 0.0F);
		bone4.texOffs(242, 36).addBox(-1.9063F, -2.0F, -6.4226F, 1.0F, 6.0F, 6.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(-6.0F, 5.0F, 3.0F);
		right_hand.addChild(bone5);
		setRotationAngle(bone5, 0.0F, 1.1345F, 0.0F);
		bone5.texOffs(242, 74).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 6.0F, 6.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(9.0F, -48.0F, 0.0F);
		total.addChild(left_hand);
		left_hand.texOffs(202, 0).addBox(-1.0F, 0.0F, -3.0F, 6.0F, 25.0F, 6.0F, 0.0F, false);

		armor = new ModelRenderer(this);
		armor.setPos(5.0F, 5.0F, 0.0F);
		left_hand.addChild(armor);
		armor.texOffs(208, 36).addBox(0.0F, -2.0F, -4.0F, 1.0F, 6.0F, 8.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 0.0F, 3.0F);
		armor.addChild(bone);
		setRotationAngle(bone, 0.0F, -1.1345F, 0.0F);
		bone.texOffs(216, 56).addBox(0.0403F, -2.0F, -0.0774F, 1.0F, 6.0F, 6.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, 1.0F, -2.0F);
		armor.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 1.1345F, 0.0F);
		bone2.texOffs(219, 76).addBox(0.9063F, -3.0F, -6.4226F, 1.0F, 6.0F, 6.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, -31.0F, 0.0F);
		total.addChild(body);
		body.texOffs(0, 223).addBox(-8.0F, -17.0F, -4.0F, 16.0F, 25.0F, 8.0F, 0.0F, false);
		body.texOffs(54, 226).addBox(-8.0F, -14.0F, 4.0F, 7.0F, 21.0F, 7.0F, 0.0F, false);
		body.texOffs(90, 224).addBox(1.0F, -14.0F, 4.0F, 7.0F, 21.0F, 7.0F, 0.0F, false);
		body.texOffs(125, 251).addBox(-5.0F, -15.0F, 7.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(127, 242).addBox(-5.0F, 7.0F, 7.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(147, 233).addBox(-5.0F, -5.0F, 11.0F, 10.0F, 2.0F, 1.0F, 0.0F, false);
		body.texOffs(5, 193).addBox(-1.0F, -13.0F, 4.0F, 2.0F, 19.0F, 5.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-4.0F, -23.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.texOffs(29, 186).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 23.0F, 6.0F, 0.0F, false);

		right_foot = new ModelRenderer(this);
		right_foot.setPos(0.0F, 24.0F, 0.0F);
		right_leg.addChild(right_foot);
		right_foot.texOffs(65, 202).addBox(-3.0F, -1.0F, -12.0F, 6.0F, 2.0F, 17.0F, 0.0F, false);
		right_foot.texOffs(124, 218).addBox(3.0F, -1.0F, -11.0F, 1.0F, 2.0F, 7.0F, 0.0F, false);
		right_foot.texOffs(146, 214).addBox(-4.0F, -1.0F, -11.0F, 1.0F, 2.0F, 7.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(4.0F, -23.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.texOffs(6, 154).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 23.0F, 6.0F, 0.0F, false);

		left_foot = new ModelRenderer(this);
		left_foot.setPos(0.0F, 24.0F, 0.0F);
		left_leg.addChild(left_foot);
		left_foot.texOffs(77, 170).addBox(-3.0F, -1.0F, -12.0F, 6.0F, 2.0F, 17.0F, 0.0F, false);
		left_foot.texOffs(55, 171).addBox(3.0F, -1.0F, -11.0F, 1.0F, 2.0F, 7.0F, 0.0F, false);
		left_foot.texOffs(134, 196).addBox(-4.0F, -1.0F, -11.0F, 1.0F, 2.0F, 7.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(LavaZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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
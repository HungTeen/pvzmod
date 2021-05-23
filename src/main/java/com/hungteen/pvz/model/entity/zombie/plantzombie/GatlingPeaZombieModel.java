package com.hungteen.pvz.model.entity.zombie.plantzombie;

import com.hungteen.pvz.entity.zombie.zombotany.GatlingPeaZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class GatlingPeaZombieModel extends EntityModel<GatlingPeaZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer gar;
	private final ModelRenderer helmet;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;

	public GatlingPeaZombieModel() {
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
		body.setPos(0.0F, -7.0F, 0.0F);
		up.addChild(body);
		body.texOffs(0, 41).addBox(-8.0F, -17.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(12.0F, -20.0F, 0.0F);
		up.addChild(left_hand);
		left_hand.texOffs(96, 60).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-12.0F, -20.0F, 0.0F);
		up.addChild(right_hand);
		right_hand.texOffs(96, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -26.0F, -1.0F);
		up.addChild(head);
		head.texOffs(4, 243).addBox(-5.0F, -10.0F, 7.0F, 10.0F, 10.0F, 1.0F, 0.0F, false);
		head.texOffs(200, 224).addBox(-7.0F, -12.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
		head.texOffs(183, 236).addBox(7.0F, -10.0F, -5.0F, 1.0F, 10.0F, 6.0F, 0.0F, false);
		head.texOffs(164, 234).addBox(-8.0F, -10.0F, -5.0F, 1.0F, 10.0F, 6.0F, 0.0F, false);
		head.texOffs(122, 241).addBox(-5.0F, -13.0F, -5.0F, 10.0F, 1.0F, 10.0F, 0.0F, false);
		head.texOffs(224, 190).addBox(-3.0F, -6.0F, -17.0F, 6.0F, 6.0F, 10.0F, 0.0F, false);
		head.texOffs(234, 169).addBox(-4.0F, -6.0F, -17.0F, 1.0F, 6.0F, 10.0F, 0.0F, false);
		head.texOffs(179, 203).addBox(-3.0F, -7.0F, -17.0F, 6.0F, 1.0F, 10.0F, 0.0F, false);
		head.texOffs(198, 177).addBox(3.0F, -6.0F, -17.0F, 1.0F, 6.0F, 10.0F, 0.0F, false);
		head.texOffs(144, 187).addBox(-3.0F, 0.0F, -17.0F, 6.0F, 1.0F, 10.0F, 0.0F, false);

		gar = new ModelRenderer(this);
		gar.setPos(0.0F, -3.0F, -17.0F);
		head.addChild(gar);
		gar.texOffs(102, 244).addBox(-3.0F, -1.0F, -5.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
		gar.texOffs(83, 246).addBox(-1.0F, -3.0F, -5.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
		gar.texOffs(64, 246).addBox(1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
		gar.texOffs(44, 245).addBox(-1.0F, 1.0F, -5.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		helmet = new ModelRenderer(this);
		helmet.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(helmet);
		helmet.texOffs(4, 222).addBox(-9.0F, -12.0F, 1.0F, 2.0F, 15.0F, 2.0F, 0.0F, false);
		helmet.texOffs(21, 221).addBox(7.0F, -13.0F, 1.0F, 2.0F, 16.0F, 2.0F, 0.0F, false);
		helmet.texOffs(32, 219).addBox(-9.0F, -15.0F, -8.0F, 18.0F, 3.0F, 18.0F, 0.0F, false);
		helmet.texOffs(112, 210).addBox(-8.0F, -12.0F, 3.0F, 16.0F, 13.0F, 8.0F, 0.0F, false);
		helmet.texOffs(2, 194).addBox(-8.0F, -17.0F, -7.0F, 16.0F, 2.0F, 16.0F, 0.0F, false);
		helmet.texOffs(2, 176).addBox(-6.0F, -19.0F, -5.0F, 12.0F, 2.0F, 12.0F, 0.0F, false);
		helmet.texOffs(230, 210).addBox(-4.0F, 2.0F, -7.0F, 8.0F, 4.0F, 2.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(-7.0F, 2.0F, 2.0F);
		helmet.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.7854F, 0.4363F);
		bone.texOffs(77, 211).addBox(-0.5255F, -0.4837F, -3.3539F, 9.0F, 1.0F, 2.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(7.0F, 3.0F, 2.0F);
		helmet.addChild(bone2);
		setRotationAngle(bone2, 0.0F, -0.7854F, -0.4363F);
		bone2.texOffs(176, 224).addBox(-7.8337F, -1.0611F, -3.9948F, 9.0F, 1.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(GatlingPeaZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.yRot = netHeadYaw / (180F / (float)Math.PI);
        this.head.xRot = headPitch / (180F / (float)Math.PI);
        this.left_leg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.right_leg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.right_hand.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_hand.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.gar.zRot = ageInTicks / 5;
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
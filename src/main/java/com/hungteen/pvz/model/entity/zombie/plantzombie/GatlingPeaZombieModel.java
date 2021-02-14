package com.hungteen.pvz.model.entity.zombie.plantzombie;

import com.hungteen.pvz.entity.zombie.plantzombie.GatlingPeaZombieEntity;
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
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, -24.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.setTextureOffset(44, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, -24.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.setTextureOffset(0, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, -24.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -7.0F, 0.0F);
		up.addChild(body);
		body.setTextureOffset(0, 41).addBox(-8.0F, -17.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(12.0F, -20.0F, 0.0F);
		up.addChild(left_hand);
		left_hand.setTextureOffset(96, 60).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-12.0F, -20.0F, 0.0F);
		up.addChild(right_hand);
		right_hand.setTextureOffset(96, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -26.0F, -1.0F);
		up.addChild(head);
		head.setTextureOffset(4, 243).addBox(-5.0F, -10.0F, 7.0F, 10.0F, 10.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(200, 224).addBox(-7.0F, -12.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(183, 236).addBox(7.0F, -10.0F, -5.0F, 1.0F, 10.0F, 6.0F, 0.0F, false);
		head.setTextureOffset(164, 234).addBox(-8.0F, -10.0F, -5.0F, 1.0F, 10.0F, 6.0F, 0.0F, false);
		head.setTextureOffset(122, 241).addBox(-5.0F, -13.0F, -5.0F, 10.0F, 1.0F, 10.0F, 0.0F, false);
		head.setTextureOffset(224, 190).addBox(-3.0F, -6.0F, -17.0F, 6.0F, 6.0F, 10.0F, 0.0F, false);
		head.setTextureOffset(234, 169).addBox(-4.0F, -6.0F, -17.0F, 1.0F, 6.0F, 10.0F, 0.0F, false);
		head.setTextureOffset(179, 203).addBox(-3.0F, -7.0F, -17.0F, 6.0F, 1.0F, 10.0F, 0.0F, false);
		head.setTextureOffset(198, 177).addBox(3.0F, -6.0F, -17.0F, 1.0F, 6.0F, 10.0F, 0.0F, false);
		head.setTextureOffset(144, 187).addBox(-3.0F, 0.0F, -17.0F, 6.0F, 1.0F, 10.0F, 0.0F, false);

		gar = new ModelRenderer(this);
		gar.setRotationPoint(0.0F, -3.0F, -17.0F);
		head.addChild(gar);
		gar.setTextureOffset(102, 244).addBox(-3.0F, -1.0F, -5.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
		gar.setTextureOffset(83, 246).addBox(-1.0F, -3.0F, -5.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
		gar.setTextureOffset(64, 246).addBox(1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
		gar.setTextureOffset(44, 245).addBox(-1.0F, 1.0F, -5.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		helmet = new ModelRenderer(this);
		helmet.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(helmet);
		helmet.setTextureOffset(4, 222).addBox(-9.0F, -12.0F, 1.0F, 2.0F, 15.0F, 2.0F, 0.0F, false);
		helmet.setTextureOffset(21, 221).addBox(7.0F, -13.0F, 1.0F, 2.0F, 16.0F, 2.0F, 0.0F, false);
		helmet.setTextureOffset(32, 219).addBox(-9.0F, -15.0F, -8.0F, 18.0F, 3.0F, 18.0F, 0.0F, false);
		helmet.setTextureOffset(112, 210).addBox(-8.0F, -12.0F, 3.0F, 16.0F, 13.0F, 8.0F, 0.0F, false);
		helmet.setTextureOffset(2, 194).addBox(-8.0F, -17.0F, -7.0F, 16.0F, 2.0F, 16.0F, 0.0F, false);
		helmet.setTextureOffset(2, 176).addBox(-6.0F, -19.0F, -5.0F, 12.0F, 2.0F, 12.0F, 0.0F, false);
		helmet.setTextureOffset(230, 210).addBox(-4.0F, 2.0F, -7.0F, 8.0F, 4.0F, 2.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-7.0F, 2.0F, 2.0F);
		helmet.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.7854F, 0.4363F);
		bone.setTextureOffset(77, 211).addBox(-0.5255F, -0.4837F, -3.3539F, 9.0F, 1.0F, 2.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(7.0F, 3.0F, 2.0F);
		helmet.addChild(bone2);
		setRotationAngle(bone2, 0.0F, -0.7854F, -0.4363F);
		bone2.setTextureOffset(176, 224).addBox(-7.8337F, -1.0611F, -3.9948F, 9.0F, 1.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(GatlingPeaZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.right_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.gar.rotateAngleZ = ageInTicks / 5;
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		total.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
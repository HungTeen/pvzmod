package com.hungteen.pvz.model.entity.zombie.grassday;

import com.hungteen.pvz.entity.zombie.grassday.ConeHeadZombieEntity;
import com.hungteen.pvz.model.entity.IHasDefence;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.1
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class ConeHeadZombieModel extends EntityModel<ConeHeadZombieEntity> implements IHasDefence{
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
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -48.0F, 0.0F);
		total.addChild(head);
		head.setTextureOffset(16, 96).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		defence1 = new ModelRenderer(this);
		defence1.setRotationPoint(0.0F, -15.0F, 0.0F);
		head.addChild(defence1);
		defence1.setTextureOffset(139, 2).addBox(-8.0F, -3.0F, -8.0F, 16.0F, 2.0F, 16.0F, 0.0F, false);
		defence1.setTextureOffset(216, 4).addBox(-9.0F, -3.0F, -9.0F, 18.0F, 3.0F, 1.0F, 0.0F, false);
		defence1.setTextureOffset(215, 15).addBox(-9.0F, -3.0F, 8.0F, 18.0F, 3.0F, 1.0F, 0.0F, false);
		defence1.setTextureOffset(139, 30).addBox(-9.0F, -3.0F, -8.0F, 1.0F, 3.0F, 16.0F, 0.0F, false);
		defence1.setTextureOffset(181, 28).addBox(8.0F, -3.0F, -8.0F, 1.0F, 3.0F, 16.0F, 0.0F, false);
		defence1.setTextureOffset(139, 56).addBox(-7.0F, -9.0F, -7.0F, 14.0F, 6.0F, 14.0F, 0.0F, false);

		defence2 = new ModelRenderer(this);
		defence2.setRotationPoint(0.0F, -22.0F, 0.0F);
		head.addChild(defence2);
		defence2.setTextureOffset(142, 81).addBox(-3.0F, -11.0F, -3.0F, 6.0F, 4.0F, 6.0F, 0.0F, false);
		defence2.setTextureOffset(217, 26).addBox(-5.0F, -7.0F, -5.0F, 10.0F, 5.0F, 10.0F, 0.0F, false);

		defence3 = new ModelRenderer(this);
		defence3.setRotationPoint(0.0F, 1.0F, 0.0F);
		head.addChild(defence3);
		defence3.setTextureOffset(205, 59).addBox(-2.0F, -37.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
		defence3.setTextureOffset(234, 60).addBox(-1.0F, -39.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-8.0F, -48.0F, 0.0F);
		total.addChild(right_hand);
		right_hand.setTextureOffset(96, 0).addBox(-8.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(8.0F, -48.0F, 0.0F);
		total.addChild(left_hand);
		left_hand.setTextureOffset(96, 60).addBox(0.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -35.0F, 0.0F);
		total.addChild(body);
		body.setTextureOffset(0, 41).addBox(-8.0F, -13.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-5.0F, -23.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.setTextureOffset(44, 0).addBox(-3.0F, -1.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(3.0F, -23.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.setTextureOffset(0, 0).addBox(-3.0F, -1.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(ConeHeadZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        this.right_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
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

	@Override
	public void setDestroyed(float percent) {
		this.defence3.showModel = percent > 3.0f/4;
		this.defence2.showModel = percent > 2.0f/4;
		this.defence1.showModel = percent > 1.0f/4;
	}
}
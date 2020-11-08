package com.hungteen.pvz.model.entity.plant.magic;

import com.hungteen.pvz.entity.plant.magic.CoffeeBeanEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class CoffeeBeanModel extends EntityModel<CoffeeBeanEntity> {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer bone11;
	private final ModelRenderer left_wing;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer right_wing;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;

	public CoffeeBeanModel() {
		textureWidth = 64;
		textureHeight = 64;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 16.0F, 4.0F);
		setRotationAngle(total, 1.2217F, 0.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 1.0F);
		total.addChild(body);
		body.setTextureOffset(1, 39).addBox(-6.0F, -8.0F, -9.0F, 12.0F, 8.0F, 16.0F, 0.0F, false);

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(0.0F, -4.0F, 7.0F);
		body.addChild(bone11);
		setRotationAngle(bone11, 0.2618F, 0.0F, 0.0F);
		bone11.setTextureOffset(51, 48).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		left_wing = new ModelRenderer(this);
		left_wing.setRotationPoint(6.0F, -4.0F, 3.0F);
		total.addChild(left_wing);
		setRotationAngle(left_wing, 0.0F, -0.4363F, 0.0F);
		left_wing.setTextureOffset(45, 42).addBox(-2.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(5.0F, 0.0F, 0.0F);
		left_wing.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.1745F, 0.0F);
		bone.setTextureOffset(47, 33).addBox(-2.0F, -1.0F, 0.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(5.0F, 0.0F, 0.0F);
		left_wing.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.6981F, 0.0F);
		bone2.setTextureOffset(33, 31).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(5.0F, 0.0F, 0.0F);
		left_wing.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 1.6581F, 0.0F);
		bone3.setTextureOffset(16, 31).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(5.0F, 0.0F, 0.0F);
		left_wing.addChild(bone4);
		setRotationAngle(bone4, 0.0F, 2.7053F, 0.0F);
		bone4.setTextureOffset(0, 46).addBox(-0.3572F, -1.0F, -0.766F, 2.0F, 1.0F, 6.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(4.0F, 0.0F, 0.0F);
		left_wing.addChild(bone5);
		setRotationAngle(bone5, 0.0F, -2.7925F, 0.0F);
		bone5.setTextureOffset(1, 28).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);

		right_wing = new ModelRenderer(this);
		right_wing.setRotationPoint(-6.0F, -4.0F, 3.0F);
		total.addChild(right_wing);
		setRotationAngle(right_wing, 0.0F, 0.4363F, 0.0F);
		right_wing.setTextureOffset(28, 26).addBox(-5.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(-5.0F, 0.0F, 0.0F);
		right_wing.addChild(bone6);
		setRotationAngle(bone6, 0.0F, -0.1745F, 0.0F);
		bone6.setTextureOffset(47, 23).addBox(0.0F, -1.0F, 0.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(-5.0F, 0.0F, 0.0F);
		right_wing.addChild(bone7);
		setRotationAngle(bone7, 0.0F, -0.6981F, 0.0F);
		bone7.setTextureOffset(12, 19).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(-5.0F, 0.0F, 0.0F);
		right_wing.addChild(bone8);
		setRotationAngle(bone8, 0.0F, -1.6581F, 0.0F);
		bone8.setTextureOffset(47, 14).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(-5.0F, 0.0F, 0.0F);
		right_wing.addChild(bone9);
		setRotationAngle(bone9, 0.0F, -2.7053F, 0.0F);
		bone9.setTextureOffset(29, 15).addBox(-1.6428F, -1.0F, -0.766F, 2.0F, 1.0F, 6.0F, 0.0F, false);

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(-4.0F, 0.0F, 0.0F);
		right_wing.addChild(bone10);
		setRotationAngle(bone10, 0.0F, 2.7925F, 0.0F);
		bone10.setTextureOffset(1, 11).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(CoffeeBeanEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.left_wing.rotateAngleZ = MathHelper.sin(ageInTicks / 10) * 3.14159f / 4;
		this.right_wing.rotateAngleZ = - MathHelper.sin(ageInTicks / 10) * 3.14159f / 4;
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
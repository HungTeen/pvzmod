package com.hungteen.pvz.model.entity.plant.spear;

import com.hungteen.pvz.entity.plant.spear.CatTailEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class CatTailModel extends EntityModel<CatTailEntity> {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer legs;
	private final ModelRenderer left_ear;
	private final ModelRenderer bone3;
	private final ModelRenderer bone2;
	private final ModelRenderer right_ear;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer tail;
	private final ModelRenderer tail1;
	private final ModelRenderer tail2;
	private final ModelRenderer tail3;
	private final ModelRenderer tail4;
	private final ModelRenderer tail5;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer tail6;

	public CatTailModel() {
		textureWidth = 512;
		textureHeight = 512;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		body.setTextureOffset(256, 384).addBox(-33.3301F, -68.0F, -32.0F, 64.0F, 64.0F, 64.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 4.0F, 0.0F);
		body.addChild(bone4);
		

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(35.0F, -68.0F, -32.0F);
		bone4.addChild(bone5);
		setRotationAngle(bone5, -0.2618F, 0.0F, 0.0F);
		bone5.setTextureOffset(367, 0).addBox(-71.3301F, 0.0F, 0.0F, 3.0F, 10.0F, 70.0F, 0.0F, false);
		bone5.setTextureOffset(214, 0).addBox(-4.3301F, 0.0F, 0.0F, 3.0F, 10.0F, 70.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone4.addChild(bone6);
		bone6.setTextureOffset(288, 11).addBox(-36.3301F, -49.8827F, 31.6148F, 70.0F, 10.0F, 4.0F, 0.0F, false);
		bone6.setTextureOffset(294, 40).addBox(-36.3301F, -68.0F, -35.0F, 70.0F, 10.0F, 3.0F, 0.0F, false);

		legs = new ModelRenderer(this);
		legs.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(legs);
		legs.setTextureOffset(368, 338).addBox(-43.3301F, -4.0F, 6.0F, 36.0F, 4.0F, 36.0F, 0.0F, false);
		legs.setTextureOffset(368, 298).addBox(4.6699F, -4.0F, 6.0F, 36.0F, 4.0F, 36.0F, 0.0F, false);
		legs.setTextureOffset(368, 258).addBox(-43.3301F, -4.0F, -42.0F, 36.0F, 4.0F, 36.0F, 0.0F, false);
		legs.setTextureOffset(367, 217).addBox(4.6699F, -4.0F, -42.0F, 36.0F, 4.0F, 36.0F, 0.0F, false);

		left_ear = new ModelRenderer(this);
		left_ear.setRotationPoint(32.0F, -66.0F, -6.0F);
		total.addChild(left_ear);
		setRotationAngle(left_ear, 0.0F, 0.0F, 0.8727F);
		

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
		left_ear.addChild(bone3);
		bone3.setTextureOffset(479, 302).addBox(-15.0486F, -5.4591F, -3.999F, 9.0F, 20.0F, 8.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		left_ear.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.8727F);
		bone2.setTextureOffset(459, 0).addBox(-13.855F, 1.0189F, -4.0F, 19.0F, 7.0F, 8.0F, 0.0F, false);

		right_ear = new ModelRenderer(this);
		right_ear.setRotationPoint(-35.0F, -66.0F, -6.0F);
		total.addChild(right_ear);
		setRotationAngle(right_ear, 0.0F, 0.0F, -0.8727F);
		

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, 0.0F, 0.0F);
		right_ear.addChild(bone7);
		bone7.setTextureOffset(479, 262).addBox(6.0486F, -5.4591F, -3.99F, 9.0F, 20.0F, 8.0F, 0.0F, true);

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, 0.0F, 0.0F);
		right_ear.addChild(bone8);
		setRotationAngle(bone8, 0.0F, 0.0F, -0.8727F);
		bone8.setTextureOffset(459, 32).addBox(-5.145F, 1.0189F, -4.0F, 19.0F, 7.0F, 8.0F, 0.0F, true);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, -24.8669F, 39.9447F);
		total.addChild(tail);
		

		tail1 = new ModelRenderer(this);
		tail1.setRotationPoint(0.0F, 9.8669F, -7.9447F);
		tail.addChild(tail1);
		tail1.setTextureOffset(6, 448).addBox(-5.3301F, -5.0F, -5.0F, 8.0F, 8.0F, 37.0F, 0.0F, false);

		tail2 = new ModelRenderer(this);
		tail2.setRotationPoint(0.0F, 6.7538F, 22.5237F);
		tail.addChild(tail2);
		setRotationAngle(tail2, -0.8727F, 0.0F, 0.0F);
		tail2.setTextureOffset(304, 323).addBox(-5.2301F, -29.2438F, -2.3326F, 8.0F, 32.0F, 8.0F, 0.0F, false);

		tail3 = new ModelRenderer(this);
		tail3.setRotationPoint(0.0F, -23.3801F, 21.2917F);
		tail.addChild(tail3);
		setRotationAngle(tail3, -1.6581F, 0.0F, 0.0F);
		tail3.setTextureOffset(222, 331).addBox(-5.3301F, -28.5396F, -11.7591F, 8.0F, 8.0F, 25.0F, 0.0F, false);

		tail4 = new ModelRenderer(this);
		tail4.setRotationPoint(0.0F, -21.0F, 1.0F);
		tail.addChild(tail4);
		setRotationAngle(tail4, 0.2618F, 0.0F, 0.0F);
		tail4.setTextureOffset(168, 372).addBox(-5.3301F, -2.336F, 43.0566F, 8.0F, 4.0F, 8.0F, 0.0F, false);

		tail5 = new ModelRenderer(this);
		tail5.setRotationPoint(2.6699F, -19.5361F, -3.1281F);
		tail.addChild(tail5);
		setRotationAngle(tail5, 0.2618F, 0.0F, 0.0F);
		

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(0.0F, 0.0F, 0.0F);
		tail5.addChild(bone10);
		bone10.setTextureOffset(0, 0).addBox(-7.2426F, -28.9745F, 43.9555F, 6.0F, 27.0F, 7.0F, 0.0F, false);
		bone10.setTextureOffset(0, 0).addBox(-7.2426F, -28.9745F, 51.4408F, 6.0F, 27.0F, 7.0F, 0.0F, false);
		bone10.setTextureOffset(0, 0).addBox(-11.4853F, -28.9745F, 48.1982F, 7.0F, 27.0F, 6.0F, 0.0F, false);
		bone10.setTextureOffset(0, 0).addBox(-4.0F, -28.9745F, 48.1982F, 7.0F, 27.0F, 6.0F, 0.0F, false);

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(0.0F, 0.0F, 0.0F);
		tail5.addChild(bone11);
		setRotationAngle(bone11, 0.0F, 0.7854F, 0.0F);
		bone11.setTextureOffset(0, 0).addBox(-42.2026F, -28.9745F, 25.9599F, 6.0F, 27.0F, 7.0F, 0.0F, false);
		bone11.setTextureOffset(0, 0).addBox(-42.2026F, -28.9745F, 33.4452F, 6.0F, 27.0F, 7.0F, 0.0F, false);
		bone11.setTextureOffset(0, 0).addBox(-46.4452F, -28.9745F, 30.2026F, 7.0F, 27.0F, 6.0F, 0.0F, false);
		bone11.setTextureOffset(0, 0).addBox(-38.9599F, -28.9745F, 30.2026F, 7.0F, 27.0F, 6.0F, 0.0F, false);

		tail6 = new ModelRenderer(this);
		tail6.setRotationPoint(-2.0F, -61.0F, 39.0F);
		tail.addChild(tail6);
		setRotationAngle(tail6, -0.2618F, -0.1745F, -0.6981F);
		tail6.setTextureOffset(377, 272).addBox(4.6394F, -8.0881F, -8.206F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		tail6.setTextureOffset(368, 225).addBox(7.6394F, -10.0881F, -10.206F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		tail6.setTextureOffset(483, 228).addBox(-1.3606F, -6.0881F, -6.206F, 8.0F, 8.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(CatTailEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		int tick = entity.getAttackTime();
		float v = 3.14159F / entity.getAnimCD();
		this.tail.rotateAngleX = 0.8F - 0.8F * Math.abs(MathHelper.cos(v * tick));
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
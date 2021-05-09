package com.hungteen.pvz.model.entity.plant.arma;

import com.hungteen.pvz.entity.plant.arma.MelonPultEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class MelonPultModel extends EntityModel<MelonPultEntity> {
	private final ModelRenderer total;
	private final ModelRenderer leaves;
	private final ModelRenderer leave1;
	private final ModelRenderer leave2;
	private final ModelRenderer leave3;
	private final ModelRenderer leave4;
	private final ModelRenderer body;
	private final ModelRenderer pult;
	private final ModelRenderer basket;
	private final ModelRenderer melon;
	private final ModelRenderer cube_r1;
	private final ModelRenderer face;

	public MelonPultModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 23.75F, 0.0F);
		

		leaves = new ModelRenderer(this);
		leaves.setPos(0.0F, 0.25F, 0.0F);
		total.addChild(leaves);
		

		leave1 = new ModelRenderer(this);
		leave1.setPos(-9.0F, 0.0F, -9.0F);
		leaves.addChild(leave1);
		leave1.texOffs(48, 97).addBox(0.0F, -1.0F, 2.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		leave1.texOffs(60, 98).addBox(-1.0F, -1.0F, 1.0F, 2.0F, 1.0F, 2.0F, -0.1F, false);

		leave2 = new ModelRenderer(this);
		leave2.setPos(6.0F, 0.0F, -5.0F);
		leaves.addChild(leave2);
		leave2.texOffs(68, 97).addBox(0.0F, -1.0F, -2.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		leave2.texOffs(78, 97).addBox(2.0F, -1.0F, -3.0F, 2.0F, 1.0F, 2.0F, -0.1F, false);

		leave3 = new ModelRenderer(this);
		leave3.setPos(-2.0F, 0.0F, 2.0F);
		leaves.addChild(leave3);
		leave3.texOffs(0, 85).addBox(-7.0F, -1.0F, 2.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		leave3.texOffs(0, 82).addBox(-8.0F, -1.0F, 4.0F, 2.0F, 1.0F, 2.0F, -0.1F, false);

		leave4 = new ModelRenderer(this);
		leave4.setPos(2.0F, 0.0F, 2.0F);
		leaves.addChild(leave4);
		leave4.texOffs(8, 80).addBox(4.0F, -1.0F, 2.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		leave4.texOffs(0, 79).addBox(6.0F, -1.0F, 4.0F, 2.0F, 1.0F, 2.0F, -0.1F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 1.0F, 0.0F);
		total.addChild(body);
		body.texOffs(0, 106).addBox(-8.0F, -11.0F, -6.0F, 16.0F, 10.0F, 12.0F, -0.2F, false);
		body.texOffs(100, 112).addBox(-6.5F, -9.5F, -6.25F, 13.0F, 7.0F, 1.0F, -0.2F, false);
		body.texOffs(56, 112).addBox(-8.25F, -9.5F, -4.5F, 1.0F, 7.0F, 9.0F, -0.2F, false);
		body.texOffs(76, 112).addBox(7.25F, -9.5F, -4.5F, 1.0F, 7.0F, 9.0F, -0.2F, false);
		body.texOffs(100, 120).addBox(-6.5F, -9.5F, 5.25F, 13.0F, 7.0F, 1.0F, -0.2F, false);
		body.texOffs(84, 102).addBox(-6.5F, -11.25F, -4.5F, 13.0F, 1.0F, 9.0F, -0.2F, false);
		body.texOffs(49, 101).addBox(-6.5F, -1.75F, -4.5F, 13.0F, 1.0F, 9.0F, -0.2F, false);

		pult = new ModelRenderer(this);
		pult.setPos(0.0F, -6.5F, 6.0F);
		total.addChild(pult);
		pult.texOffs(102, 80).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 2.0F, 11.0F, -0.2F, false);
		pult.texOffs(75, 89).addBox(-1.0F, -1.0F, 8.5F, 2.0F, 2.0F, 5.0F, -0.4F, false);

		basket = new ModelRenderer(this);
		basket.setPos(0.0F, 0.75F, 1.75F);
		pult.addChild(basket);
		basket.texOffs(96, 93).addBox(-4.0F, 0.0F, 12.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
		basket.texOffs(0, 103).addBox(-4.0F, -2.0F, 11.0F, 8.0F, 2.0F, 1.0F, 0.0F, false);
		basket.texOffs(0, 100).addBox(-4.0F, -2.0F, 20.0F, 8.0F, 2.0F, 1.0F, 0.0F, false);
		basket.texOffs(34, 79).addBox(-5.0F, -2.0F, 11.0F, 1.0F, 2.0F, 10.0F, 0.0F, false);
		basket.texOffs(51, 83).addBox(4.0F, -2.0F, 11.0F, 1.0F, 2.0F, 10.0F, 0.0F, false);

		melon = new ModelRenderer(this);
		melon.setPos(-0.5F, 0.0F, 12.5F);
		basket.addChild(melon);
		setRotationAngle(melon, 0.3054F, 0.0F, 0.0F);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(0.0F, 0.0F, 0.0F);
		melon.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.0873F, 0.0F, 0.0F);
		cube_r1.texOffs(10, 82).addBox(-3.0F, -6.0541F, -0.2441F, 7.0F, 6.0F, 10.0F, -0.3F, false);

		face = new ModelRenderer(this);
		face.setPos(0.0F, 0.75F, -2.25F);
		total.addChild(face);
		face.texOffs(0, 114).addBox(-4.0F, -7.5F, -4.3F, 2.0F, 3.0F, 1.0F, -0.45F, false);
		face.texOffs(0, 110).addBox(2.0F, -7.5F, -4.3F, 2.0F, 3.0F, 1.0F, -0.45F, false);
	}

	@Override
	public void setupAnim(MelonPultEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if(entity.getAttackTime() > 0) {
			float percent = 1 - entity.getAttackTime() * 1.0F / entity.getPultAnimTime();
			pult.xRot = (1F - MathHelper.abs(MathHelper.cos(percent * 3.14159F))) * 1.5F;
			this.melon.visible = (percent < 0.5);
		} else {
			pult.xRot = MathHelper.sin(ageInTicks / 10) / 8;
			this.melon.visible = true;
		}
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
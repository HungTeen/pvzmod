package com.hungteen.pvz.model.entity.plant.ice;

import com.hungteen.pvz.entity.plant.ice.SnowPeaEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class SnowPeaModel extends EntityModel<SnowPeaEntity> {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer gg1;
	private final ModelRenderer g1;
	private final ModelRenderer gg2;
	private final ModelRenderer g2;
	private final ModelRenderer gg3;
	private final ModelRenderer g3;
	private final ModelRenderer gg4;
	private final ModelRenderer g4;
	private final ModelRenderer gg5;
	private final ModelRenderer g5;
	private final ModelRenderer YEZI_4;
	private final ModelRenderer YEZI_3;
	private final ModelRenderer YEZI_2;
	private final ModelRenderer YEZI_1;

	public SnowPeaModel() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -31.0F, 0.0F);
		total.addChild(body);
		body.setTextureOffset(238, 218).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 32.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(210, 246).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -35.0F, 0.0F);
		total.addChild(head);
		head.setTextureOffset(212, 232).addBox(-5.0F, -10.0F, 7.0F, 10.0F, 10.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(150, 226).addBox(-7.0F, -12.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(124, 234).addBox(7.0F, -10.0F, -5.0F, 1.0F, 10.0F, 10.0F, 0.0F, false);
		head.setTextureOffset(125, 209).addBox(-8.0F, -10.0F, -5.0F, 1.0F, 10.0F, 10.0F, 0.0F, false);
		head.setTextureOffset(156, 211).addBox(-5.0F, -13.0F, -5.0F, 10.0F, 1.0F, 10.0F, 0.0F, false);
		head.setTextureOffset(196, 198).addBox(-3.0F, -7.0F, -21.0F, 6.0F, 6.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(124, 183).addBox(-4.0F, -7.0F, -21.0F, 1.0F, 6.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(161, 185).addBox(3.0F, -7.0F, -21.0F, 1.0F, 6.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(124, 164).addBox(-3.0F, -8.0F, -21.0F, 6.0F, 1.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(170, 164).addBox(-3.0F, -1.0F, -21.0F, 6.0F, 1.0F, 14.0F, 0.0F, false);

		gg1 = new ModelRenderer(this);
		gg1.setRotationPoint(2.0F, -7.0F, 8.0F);
		head.addChild(gg1);
		setRotationAngle(gg1, 0.2618F, 0.6981F, 0.0F);
		gg1.setTextureOffset(212, 171).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 7.0F, 0.0F, false);

		g1 = new ModelRenderer(this);
		g1.setRotationPoint(0.0F, 0.0F, 7.0F);
		gg1.addChild(g1);
		setRotationAngle(g1, 0.0F, -0.8727F, 0.0F);
		g1.setTextureOffset(242, 186).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		gg2 = new ModelRenderer(this);
		gg2.setRotationPoint(1.0F, -4.0F, 8.0F);
		head.addChild(gg2);
		setRotationAngle(gg2, -0.4363F, 0.6981F, 0.0F);
		gg2.setTextureOffset(231, 157).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 7.0F, 0.0F, false);

		g2 = new ModelRenderer(this);
		g2.setRotationPoint(0.0F, 0.0F, 7.0F);
		gg2.addChild(g2);
		setRotationAngle(g2, 0.0F, -0.8727F, 0.0F);
		g2.setTextureOffset(211, 160).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		gg3 = new ModelRenderer(this);
		gg3.setRotationPoint(0.0F, -7.0F, 8.0F);
		head.addChild(gg3);
		setRotationAngle(gg3, 0.6109F, 0.0F, 0.0F);
		gg3.setTextureOffset(180, 148).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 7.0F, 0.0F, false);

		g3 = new ModelRenderer(this);
		g3.setRotationPoint(0.0F, 0.0F, 7.0F);
		gg3.addChild(g3);
		setRotationAngle(g3, 0.0F, -0.8727F, 0.0F);
		g3.setTextureOffset(160, 152).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		gg4 = new ModelRenderer(this);
		gg4.setRotationPoint(-2.0F, -7.0F, 8.0F);
		head.addChild(gg4);
		setRotationAngle(gg4, 0.2618F, -0.6981F, 0.0F);
		gg4.setTextureOffset(127, 149).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 7.0F, 0.0F, false);

		g4 = new ModelRenderer(this);
		g4.setRotationPoint(0.0F, 0.0F, 7.0F);
		gg4.addChild(g4);
		setRotationAngle(g4, 0.0F, -0.8727F, 0.0F);
		g4.setTextureOffset(227, 152).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		gg5 = new ModelRenderer(this);
		gg5.setRotationPoint(-1.0F, -4.0F, 8.0F);
		head.addChild(gg5);
		setRotationAngle(gg5, -0.4363F, -0.6981F, 0.0F);
		gg5.setTextureOffset(146, 133).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 7.0F, 0.0F, false);

		g5 = new ModelRenderer(this);
		g5.setRotationPoint(0.0F, 0.0F, 7.0F);
		gg5.addChild(g5);
		setRotationAngle(g5, 0.0F, -0.8727F, 0.0F);
		g5.setTextureOffset(187, 136).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		YEZI_4 = new ModelRenderer(this);
		YEZI_4.setRotationPoint(-2.0F, -1.0F, 0.0F);
		total.addChild(YEZI_4);
		YEZI_4.setTextureOffset(206, 132).addBox(-11.0F, -1.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
		YEZI_4.setTextureOffset(194, 118).addBox(-3.0F, 0.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);

		YEZI_3 = new ModelRenderer(this);
		YEZI_3.setRotationPoint(0.0F, 0.0F, -2.0F);
		total.addChild(YEZI_3);
		YEZI_3.setTextureOffset(170, 121).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		YEZI_3.setTextureOffset(218, 116).addBox(-4.0F, -2.0F, -11.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);

		YEZI_2 = new ModelRenderer(this);
		YEZI_2.setRotationPoint(0.0F, 0.0F, 2.0F);
		total.addChild(YEZI_2);
		YEZI_2.setTextureOffset(217, 99).addBox(-4.0F, -2.0F, 3.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
		YEZI_2.setTextureOffset(194, 103).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);

		YEZI_1 = new ModelRenderer(this);
		YEZI_1.setRotationPoint(2.0F, 0.0F, 0.0F);
		total.addChild(YEZI_1);
		YEZI_1.setTextureOffset(170, 102).addBox(0.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		YEZI_1.setTextureOffset(128, 116).addBox(3.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(SnowPeaEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
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
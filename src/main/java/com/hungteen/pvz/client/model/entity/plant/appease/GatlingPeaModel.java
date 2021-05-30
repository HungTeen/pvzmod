package com.hungteen.pvz.client.model.entity.plant.appease;

import com.hungteen.pvz.common.entity.plant.appease.GatlingPeaEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class GatlingPeaModel extends EntityModel<GatlingPeaEntity> {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer helmet;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer mouse;
	private final ModelRenderer gar;
	private final ModelRenderer leaves;
	private final ModelRenderer YEZI_1;
	private final ModelRenderer YEZI_2;
	private final ModelRenderer YEZI_3;
	private final ModelRenderer YEZI_4;

	public GatlingPeaModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, -31.0F, 0.0F);
		total.addChild(body);
		body.texOffs(111, 91).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 32.0F, 4.0F, 0.0F, false);
		body.texOffs(86, 120).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -35.0F, 0.0F);
		total.addChild(head);
		head.texOffs(87, 106).addBox(-5.0F, -10.0F, 7.0F, 10.0F, 10.0F, 1.0F, 0.0F, false);
		head.texOffs(1, 99).addBox(-7.0F, -12.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
		head.texOffs(60, 111).addBox(7.0F, -10.0F, -5.0F, 1.0F, 10.0F, 6.0F, 0.0F, false);
		head.texOffs(113, 73).addBox(-8.0F, -10.0F, -5.0F, 1.0F, 10.0F, 6.0F, 0.0F, false);
		head.texOffs(87, 1).addBox(-5.0F, -13.0F, -5.0F, 10.0F, 1.0F, 10.0F, 0.0F, false);

		helmet = new ModelRenderer(this);
		helmet.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(helmet);
		helmet.texOffs(119, 50).addBox(-9.0F, -12.0F, 1.0F, 2.0F, 15.0F, 2.0F, 0.0F, false);
		helmet.texOffs(109, 51).addBox(7.0F, -13.0F, 1.0F, 2.0F, 16.0F, 2.0F, 0.0F, false);
		helmet.texOffs(1, 1).addBox(-9.0F, -15.0F, -8.0F, 18.0F, 3.0F, 18.0F, 0.0F, false);
		helmet.texOffs(1, 25).addBox(-8.0F, -12.0F, 3.0F, 16.0F, 13.0F, 8.0F, 0.0F, false);
		helmet.texOffs(2, 49).addBox(-8.0F, -17.0F, -7.0F, 16.0F, 2.0F, 16.0F, 0.0F, false);
		helmet.texOffs(53, 26).addBox(-6.0F, -19.0F, -5.0F, 12.0F, 2.0F, 12.0F, 0.0F, false);
		helmet.texOffs(71, 61).addBox(-4.0F, 2.0F, -5.0F, 8.0F, 4.0F, 2.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(-7.0F, 2.0F, 2.0F);
		helmet.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.7854F, 0.4363F);
		bone.texOffs(88, 92).addBox(-1.0F, 0.0F, -1.0F, 9.0F, 1.0F, 2.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(7.0F, 3.0F, 2.0F);
		helmet.addChild(bone2);
		setRotationAngle(bone2, 0.0F, -0.7854F, -0.4363F);
		bone2.texOffs(64, 104).addBox(-8.0F, -1.0F, -1.0F, 9.0F, 1.0F, 2.0F, 0.0F, false);

		mouse = new ModelRenderer(this);
		mouse.setPos(0.0F, -3.0F, -7.0F);
		head.addChild(mouse);
		mouse.texOffs(74, 44).addBox(-3.0F, -3.0F, -7.0F, 6.0F, 6.0F, 7.0F, 0.0F, false);
		mouse.texOffs(84, 62).addBox(-4.0F, -3.0F, -7.0F, 1.0F, 6.0F, 7.0F, 0.0F, false);
		mouse.texOffs(78, 80).addBox(-3.0F, -4.0F, -7.0F, 6.0F, 1.0F, 7.0F, 0.0F, false);
		mouse.texOffs(36, 72).addBox(3.0F, -3.0F, -7.0F, 1.0F, 6.0F, 7.0F, 0.0F, false);
		mouse.texOffs(64, 92).addBox(-3.0F, 3.0F, -7.0F, 6.0F, 1.0F, 7.0F, 0.0F, false);
		mouse.texOffs(78, 80).addBox(-3.0F, -4.0F, -7.0F, 6.0F, 1.0F, 7.0F, 0.0F, false);

		gar = new ModelRenderer(this);
		gar.setPos(0.0F, 0.0F, -7.0F);
		mouse.addChild(gar);
		gar.texOffs(113, 14).addBox(-3.0F, -1.0F, -5.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
		gar.texOffs(113, 23).addBox(-1.0F, -3.0F, -5.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
		gar.texOffs(113, 32).addBox(1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
		gar.texOffs(113, 41).addBox(-1.0F, 1.0F, -5.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);

		leaves = new ModelRenderer(this);
		leaves.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(leaves);
		

		YEZI_1 = new ModelRenderer(this);
		YEZI_1.setPos(2.0F, 0.0F, 0.0F);
		leaves.addChild(YEZI_1);
		YEZI_1.texOffs(72, 1).addBox(0.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		YEZI_1.texOffs(80, 14).addBox(3.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);

		YEZI_2 = new ModelRenderer(this);
		YEZI_2.setPos(0.0F, 0.0F, 2.0F);
		leaves.addChild(YEZI_2);
		YEZI_2.texOffs(1, 70).addBox(-4.0F, -2.0F, 3.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
		YEZI_2.texOffs(69, 9).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);

		YEZI_3 = new ModelRenderer(this);
		YEZI_3.setPos(0.0F, 0.0F, -2.0F);
		leaves.addChild(YEZI_3);
		YEZI_3.texOffs(58, 43).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		YEZI_3.texOffs(51, 68).addBox(-4.0F, -2.0F, -11.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);

		YEZI_4 = new ModelRenderer(this);
		YEZI_4.setPos(-2.0F, -1.0F, 0.0F);
		leaves.addChild(YEZI_4);
		YEZI_4.texOffs(1, 82).addBox(-11.0F, -1.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
		YEZI_4.texOffs(47, 92).addBox(-3.0F, 0.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(GatlingPeaEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if(entity.animTime > 0) {
			this.gar.zRot = ageInTicks / 5.0f % 100;
		} else {
			this.gar.zRot = ageInTicks / 20.0f % 100;
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
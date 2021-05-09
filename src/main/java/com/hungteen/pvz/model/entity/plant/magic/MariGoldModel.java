package com.hungteen.pvz.model.entity.plant.magic;

import com.hungteen.pvz.entity.plant.magic.MariGoldEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class MariGoldModel<T extends MariGoldEntity> extends EntityModel<T> {
	private final ModelRenderer total;
	private final ModelRenderer head;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer HUABAN;
	private final ModelRenderer HUABAN2;
	private final ModelRenderer HUABAN3;
	private final ModelRenderer HUABAN4;
	private final ModelRenderer HUABAN5;
	private final ModelRenderer HUABAN6;
	private final ModelRenderer body;
	private final ModelRenderer cube_r3;
	private final ModelRenderer YEZI_4;
	private final ModelRenderer YEZI_3;
	private final ModelRenderer YEZI_1;
	private final ModelRenderer YEZI_2;

	public MariGoldModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		head = new ModelRenderer(this);
		head.setPos(0.0F, -32.0F, 0.0F);
		total.addChild(head);
		head.texOffs(73, 100).addBox(-11.0F, -15.0F, -5.99F, 22.0F, 21.0F, 4.0F, -0.02F, false);
		head.texOffs(0, 123).addBox(-5.0F, 5.95F, -5.99F, 10.0F, 1.0F, 4.0F, -0.02F, false);
		head.texOffs(85, 100).addBox(-1.5F, -0.25F, -6.25F, 3.0F, 2.0F, 1.0F, -0.25F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(-4.2776F, -11.4466F, -5.75F);
		head.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, -0.3054F);
		cube_r1.texOffs(107, 88).addBox(-3.5F, -1.0F, -0.5F, 7.0F, 2.0F, 1.0F, -0.25F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(5.5F, -12.25F, -6.25F);
		head.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, 0.3054F);
		cube_r2.texOffs(106, 94).addBox(-4.0F, 0.0F, 0.0F, 7.0F, 2.0F, 1.0F, -0.25F, false);

		HUABAN = new ModelRenderer(this);
		HUABAN.setPos(0.0F, -3.0F, 1.0F);
		head.addChild(HUABAN);
		setRotationAngle(HUABAN, 0.0F, 0.0F, -0.2618F);
		HUABAN.texOffs(0, 62).addBox(-3.0F, -18.0F, -7.1F, 6.0F, 6.0F, 4.0F, -0.5F, false);
		HUABAN.texOffs(29, 63).addBox(-3.0F, 9.8564F, -7.1F, 6.0F, 6.0F, 4.0F, -0.5F, false);
		HUABAN.texOffs(42, 36).addBox(10.9282F, -4.0718F, -7.1F, 6.0F, 6.0F, 4.0F, -0.5F, false);
		HUABAN.texOffs(73, 73).addBox(-16.9282F, -4.0718F, -7.1F, 6.0F, 6.0F, 4.0F, -0.5F, false);

		HUABAN2 = new ModelRenderer(this);
		HUABAN2.setPos(0.0F, 0.0F, 0.0F);
		HUABAN.addChild(HUABAN2);
		setRotationAngle(HUABAN2, 0.0F, 0.0F, -0.5236F);
		HUABAN2.texOffs(0, 62).addBox(-2.4641F, -17.8564F, -7.1F, 6.0F, 6.0F, 4.0F, -0.5F, false);
		HUABAN2.texOffs(29, 63).addBox(-2.4641F, 10.0F, -7.1F, 6.0F, 6.0F, 4.0F, -0.5F, false);
		HUABAN2.texOffs(42, 36).addBox(11.4641F, -3.9282F, -7.1F, 6.0F, 6.0F, 4.0F, -0.5F, false);
		HUABAN2.texOffs(73, 73).addBox(-16.3923F, -3.9282F, -7.1F, 6.0F, 6.0F, 4.0F, -0.5F, false);

		HUABAN3 = new ModelRenderer(this);
		HUABAN3.setPos(0.0F, 0.0F, 0.0F);
		HUABAN2.addChild(HUABAN3);
		setRotationAngle(HUABAN3, 0.0F, 0.0F, -0.5236F);
		HUABAN3.texOffs(0, 62).addBox(-2.0718F, -17.4641F, -7.1F, 6.0F, 6.0F, 4.0F, -0.5F, false);
		HUABAN3.texOffs(29, 63).addBox(-2.0718F, 10.3923F, -7.1F, 6.0F, 6.0F, 4.0F, -0.5F, false);
		HUABAN3.texOffs(42, 36).addBox(11.8564F, -3.5359F, -7.1F, 6.0F, 6.0F, 4.0F, -0.5F, false);
		HUABAN3.texOffs(73, 73).addBox(-16.0F, -3.5359F, -7.1F, 6.0F, 6.0F, 4.0F, -0.5F, false);

		HUABAN4 = new ModelRenderer(this);
		HUABAN4.setPos(0.0F, -3.0F, 0.0F);
		head.addChild(HUABAN4);
		HUABAN4.texOffs(0, 62).addBox(-4.0F, -16.0F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN4.texOffs(29, 63).addBox(-4.0F, 9.8564F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN4.texOffs(42, 36).addBox(10.9282F, -5.0718F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
		HUABAN4.texOffs(73, 73).addBox(-14.9282F, -5.0718F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		HUABAN5 = new ModelRenderer(this);
		HUABAN5.setPos(0.0F, 0.0F, 0.0F);
		HUABAN4.addChild(HUABAN5);
		setRotationAngle(HUABAN5, 0.0F, 0.0F, -0.5236F);
		HUABAN5.texOffs(0, 62).addBox(-3.4641F, -15.8564F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN5.texOffs(29, 63).addBox(-3.4641F, 10.0F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN5.texOffs(42, 36).addBox(11.4641F, -4.9282F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
		HUABAN5.texOffs(73, 73).addBox(-14.3923F, -4.9282F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		HUABAN6 = new ModelRenderer(this);
		HUABAN6.setPos(0.0F, 0.0F, 0.0F);
		HUABAN5.addChild(HUABAN6);
		setRotationAngle(HUABAN6, 0.0F, 0.0F, -0.5236F);
		HUABAN6.texOffs(0, 62).addBox(-3.0718F, -15.4641F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN6.texOffs(29, 63).addBox(-3.0718F, 10.3923F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN6.texOffs(42, 36).addBox(11.8564F, -4.5359F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
		HUABAN6.texOffs(73, 73).addBox(-14.0F, -4.5359F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, -1.0F, 0.0F);
		total.addChild(body);
		body.texOffs(112, 43).addBox(-2.0F, -34.0F, -2.0F, 4.0F, 35.0F, 4.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(0.0F, -34.7071F, -0.1213F);
		body.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.7854F, 0.0F, 0.0F);
		cube_r3.texOffs(23, 0).addBox(-2.0F, -4.0F, -3.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		YEZI_4 = new ModelRenderer(this);
		YEZI_4.setPos(-2.0F, -1.0F, 0.0F);
		total.addChild(YEZI_4);
		YEZI_4.texOffs(78, 26).addBox(-11.0F, -1.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
		YEZI_4.texOffs(114, 28).addBox(-3.0F, 0.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);

		YEZI_3 = new ModelRenderer(this);
		YEZI_3.setPos(0.0F, 0.0F, -2.0F);
		total.addChild(YEZI_3);
		YEZI_3.texOffs(114, 19).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		YEZI_3.texOffs(0, 36).addBox(-4.0F, -2.0F, -11.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);

		YEZI_1 = new ModelRenderer(this);
		YEZI_1.setPos(2.0F, 0.0F, 0.0F);
		total.addChild(YEZI_1);
		YEZI_1.texOffs(114, 0).addBox(0.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		YEZI_1.texOffs(79, 0).addBox(3.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);

		YEZI_2 = new ModelRenderer(this);
		YEZI_2.setPos(0.0F, 0.0F, 2.0F);
		total.addChild(YEZI_2);
		YEZI_2.texOffs(78, 12).addBox(-4.0F, -2.0F, 3.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
		YEZI_2.texOffs(114, 9).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(MariGoldEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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
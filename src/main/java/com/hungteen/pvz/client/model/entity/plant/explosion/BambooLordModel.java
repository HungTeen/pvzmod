package com.hungteen.pvz.client.model.entity.plant.explosion;

import com.hungteen.pvz.common.entity.plant.explosion.BambooLordEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class BambooLordModel extends EntityModel<BambooLordEntity> {
	private final ModelRenderer total;
	private final ModelRenderer worm;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer body;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer down;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer head;
	private final ModelRenderer cube_r8;
	private final ModelRenderer face;
	private final ModelRenderer leaves;
	private final ModelRenderer leaf1;
	private final ModelRenderer cube_r9;
	private final ModelRenderer bone2;
	private final ModelRenderer cube_r10;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer leaf2;
	private final ModelRenderer cube_r11;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer leaf3;
	private final ModelRenderer cube_r12;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;

	public BambooLordModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		worm = new ModelRenderer(this);
		worm.setPos(1.0F, -10.25F, 0.0F);
		total.addChild(worm);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(-10.2695F, -6.5331F, 0.0F);
		worm.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, -1.9199F);
		cube_r1.texOffs(0, 18).addBox(-0.5F, 5.4F, 0.0F, 1.0F, 2.0F, 1.0F, -0.2F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(-7.3079F, -12.6447F, 0.0F);
		worm.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, -0.8727F);
		cube_r2.texOffs(18, 0).addBox(-1.0F, 4.8F, 0.0F, 1.0F, 2.0F, 1.0F, -0.2F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(-2.25F, -8.5F, 0.0F);
		worm.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, -0.3054F);
		cube_r3.texOffs(14, 30).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 9.0F, 1.0F, -0.2F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		body.texOffs(24, 0).addBox(-2.25F, -9.0F, -4.0F, 6.0F, 2.0F, 6.0F, -0.1F, false);
		body.texOffs(0, 14).addBox(-3.5F, -1.5F, -4.5F, 7.0F, 1.0F, 7.0F, -0.1F, false);
		body.texOffs(21, 21).addBox(-3.5F, -1.0F, -4.5F, 7.0F, 1.0F, 7.0F, 0.0F, false);
		body.texOffs(0, 45).addBox(-2.0F, -13.25F, -3.0F, 5.0F, 1.0F, 4.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(0.6351F, -9.1122F, 1.001F);
		body.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, -0.1309F);
		cube_r4.texOffs(21, 8).addBox(-3.0F, -0.7F, -5.0F, 6.0F, 1.0F, 6.0F, 0.25F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(1.0F, -9.0F, 5.0F);
		body.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, -0.3054F);
		cube_r5.texOffs(21, 15).addBox(-2.0F, -3.7F, -8.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);

		down = new ModelRenderer(this);
		down.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(down);
		

		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(0.0F, -0.25F, 1.0F);
		down.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, 0.0873F);
		cube_r6.texOffs(0, 23).addBox(-3.0F, -8.0F, -5.0F, 6.0F, 1.0F, 6.0F, 0.2F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setPos(0.0F, 0.0F, 1.0F);
		down.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, 0.0873F);
		cube_r7.texOffs(0, 0).addBox(-3.0F, -8.0F, -5.0F, 6.0F, 8.0F, 6.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(-0.25F, 0.0F, 0.0F);
		body.addChild(head);
		

		cube_r8 = new ModelRenderer(this);
		cube_r8.setPos(1.0F, -9.0F, 1.0F);
		head.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.0F, 0.0F, -0.0436F);
		cube_r8.texOffs(0, 30).addBox(2.0F, -7.7F, -5.0F, 1.0F, 8.0F, 6.0F, 0.0F, false);
		cube_r8.texOffs(18, 29).addBox(-3.0F, -7.7F, -5.0F, 1.0F, 8.0F, 6.0F, 0.0F, false);
		cube_r8.texOffs(32, 39).addBox(-2.0F, -7.7F, 0.0F, 4.0F, 8.0F, 1.0F, 0.0F, false);
		cube_r8.texOffs(42, 42).addBox(-2.0F, -7.7F, -5.0F, 4.0F, 8.0F, 1.0F, 0.0F, false);

		face = new ModelRenderer(this);
		face.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(face);
		face.texOffs(21, 15).addBox(1.5F, -12.5F, -3.8F, 1.0F, 1.0F, 1.0F, 0.3F, false);
		face.texOffs(21, 17).addBox(-1.5F, -12.5F, -3.8F, 1.0F, 1.0F, 1.0F, 0.3F, false);

		leaves = new ModelRenderer(this);
		leaves.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(leaves);
		

		leaf1 = new ModelRenderer(this);
		leaf1.setPos(2.0F, 0.0F, 0.0F);
		leaves.addChild(leaf1);
		setRotationAngle(leaf1, 0.0F, 0.1309F, 0.0F);
		

		cube_r9 = new ModelRenderer(this);
		cube_r9.setPos(-5.0F, 0.0F, 0.0F);
		leaf1.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, -0.0436F, -0.1309F);
		cube_r9.texOffs(18, 18).addBox(0.0F, -2.0F, -4.0F, 0.0F, 2.0F, 5.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, 0.0F, 0.0F);
		leaf1.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, -0.0873F);
		

		cube_r10 = new ModelRenderer(this);
		cube_r10.setPos(-5.0F, -2.0F, 0.0F);
		bone2.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0F, 0.0F, -0.1309F);
		cube_r10.texOffs(26, 26).addBox(0.0F, -2.0F, -3.5F, 0.0F, 2.0F, 4.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(1.162F, -1.906F, 0.1305F);
		bone2.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.0F, -0.4363F);
		bone3.texOffs(0, 13).addBox(-5.0F, -6.0F, -3.0F, 0.0F, 2.0F, 3.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(0.0F, 0.0F, 0.0F);
		bone3.addChild(bone4);
		bone4.texOffs(4, 4).addBox(-5.0F, -7.0F, -2.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		leaf2 = new ModelRenderer(this);
		leaf2.setPos(-2.0F, 2.0F, 1.0F);
		leaves.addChild(leaf2);
		setRotationAngle(leaf2, 0.0F, -1.5708F, 0.0436F);
		

		cube_r11 = new ModelRenderer(this);
		cube_r11.setPos(-5.0F, -2.0F, 0.0F);
		leaf2.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.0F, 0.0F, -0.1309F);
		cube_r11.texOffs(18, 21).addBox(0.0F, -2.0F, -3.5F, 0.0F, 2.0F, 4.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(1.162F, -1.906F, 0.1305F);
		leaf2.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 0.0F, -0.4363F);
		bone6.texOffs(0, 11).addBox(-5.0F, -6.0F, -3.0F, 0.0F, 2.0F, 3.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setPos(0.0F, 0.0F, 0.0F);
		bone6.addChild(bone7);
		bone7.texOffs(2, 4).addBox(-5.0F, -7.0F, -2.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		leaf3 = new ModelRenderer(this);
		leaf3.setPos(-2.0F, 2.0F, -2.0F);
		leaves.addChild(leaf3);
		setRotationAngle(leaf3, 3.1416F, 0.0F, -3.098F);
		

		cube_r12 = new ModelRenderer(this);
		cube_r12.setPos(-5.0F, -2.0F, 0.0F);
		leaf3.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.0F, 0.0F, -0.1309F);
		cube_r12.texOffs(18, 0).addBox(0.0F, -2.0F, -3.5F, 0.0F, 2.0F, 4.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setPos(1.162F, -1.906F, 0.1305F);
		leaf3.addChild(bone9);
		setRotationAngle(bone9, 0.0F, 0.0F, -0.4363F);
		bone9.texOffs(0, 0).addBox(-5.0F, -6.0F, -3.0F, 0.0F, 2.0F, 3.0F, 0.0F, false);

		bone10 = new ModelRenderer(this);
		bone10.setPos(0.0F, 0.0F, 0.0F);
		bone9.addChild(bone10);
		bone10.texOffs(0, 4).addBox(-5.0F, -7.0F, -2.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(BambooLordEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.worm.visible = (entity.getAttackTime() == 0);
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
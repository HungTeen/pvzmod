package com.hungteen.pvz.model.entity.plant.defence;

import com.hungteen.pvz.entity.plant.defence.GarlicEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class GarlicModel extends EntityModel<GarlicEntity> {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer hair;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer face;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;

	public GarlicModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(-4.0F, 18.0F, -5.0F);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		body.texOffs(0, 35).addBox(0.0F, 5.0F, 1.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
		body.texOffs(0, 24).addBox(-1.0F, 4.0F, 0.0F, 10.0F, 1.0F, 10.0F, 0.0F, false);
		body.texOffs(0, 44).addBox(-1.0F, -4.0F, -1.0F, 10.0F, 8.0F, 12.0F, 0.0F, false);
		body.texOffs(46, 57).addBox(0.0F, -3.0F, -1.75F, 8.0F, 6.0F, 1.0F, -0.1F, false);
		body.texOffs(46, 50).addBox(0.0F, -3.0F, 11.0F, 8.0F, 6.0F, 1.0F, -0.1F, false);
		body.texOffs(42, 32).addBox(-2.0F, -4.0F, 0.0F, 1.0F, 8.0F, 10.0F, 0.0F, false);
		body.texOffs(34, 28).addBox(-2.75F, -3.0F, 1.0F, 1.0F, 6.0F, 8.0F, -0.1F, false);
		body.texOffs(46, 18).addBox(9.75F, -3.0F, 1.0F, 1.0F, 6.0F, 8.0F, -0.1F, false);
		body.texOffs(42, 0).addBox(9.0F, -4.0F, 0.0F, 1.0F, 8.0F, 10.0F, 0.0F, false);
		body.texOffs(0, 13).addBox(0.0F, -5.0F, 0.0F, 8.0F, 1.0F, 10.0F, 0.0F, false);
		body.texOffs(32, 49).addBox(0.0F, -6.0F, 2.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		body.texOffs(24, 34).addBox(-1.0F, -5.0F, 1.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		body.texOffs(28, 14).addBox(8.0F, -5.0F, 1.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		body.texOffs(40, 19).addBox(7.0F, -6.0F, 2.0F, 1.0F, 1.0F, 6.0F, 0.0F, false);
		body.texOffs(0, 0).addBox(1.0F, -6.0F, 1.0F, 6.0F, 1.0F, 8.0F, 0.0F, false);
		body.texOffs(32, 0).addBox(2.0F, -7.0F, 2.0F, 4.0F, 1.0F, 6.0F, 0.0F, false);
		body.texOffs(58, 39).addBox(5.0F, -8.0F, 4.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		body.texOffs(58, 36).addBox(2.0F, -8.0F, 4.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		body.texOffs(0, 51).addBox(3.0F, -8.0F, 3.0F, 2.0F, 1.0F, 4.0F, 0.0F, false);
		body.texOffs(0, 46).addBox(1.0F, -7.0F, 3.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		body.texOffs(32, 44).addBox(6.0F, -7.0F, 3.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

		hair = new ModelRenderer(this);
		hair.setPos(0.0F, -4.0F, 0.0F);
		body.addChild(hair);
		hair.texOffs(56, 18).addBox(3.0F, -5.0F, 4.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(3.0F, -4.0F, 5.0F);
		hair.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.8727F, 0.0F, 0.3927F);
		cube_r1.texOffs(60, 20).addBox(0.0F, -5.0F, -1.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(3.25F, -4.25F, 6.0F);
		hair.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.5672F, 0.0F, -0.7854F);
		cube_r2.texOffs(54, 32).addBox(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(3.5F, -4.0F, 6.0F);
		hair.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.5672F, 0.0F, 0.3927F);
		cube_r3.texOffs(54, 37).addBox(0.0F, -5.0F, -1.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		face = new ModelRenderer(this);
		face.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(face);
		face.texOffs(0, 39).addBox(0.5F, -2.75F, -2.1F, 2.0F, 2.0F, 1.0F, -0.4F, false);
		face.texOffs(0, 25).addBox(5.5F, -2.75F, -2.1F, 2.0F, 2.0F, 1.0F, -0.4F, false);
		face.texOffs(0, 10).addBox(1.5F, 0.0F, -2.1F, 5.0F, 1.0F, 1.0F, -0.4F, false);
		face.texOffs(0, 18).addBox(1.25F, 0.25F, -2.0F, 1.0F, 1.0F, 1.0F, -0.3F, false);
		face.texOffs(0, 15).addBox(2.25F, 0.25F, -2.0F, 1.0F, 1.0F, 1.0F, -0.3F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(7.1871F, 0.4045F, -2.25F);
		face.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, -0.0873F);
		cube_r4.texOffs(0, 10).addBox(-1.5F, -0.5F, 0.15F, 2.0F, 1.0F, 1.0F, -0.4F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(1.8091F, 0.4917F, -2.25F);
		face.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, 0.0873F);
		cube_r5.texOffs(0, 10).addBox(-1.5F, -0.5F, 0.15F, 2.0F, 1.0F, 1.0F, -0.4F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(2.5F, -3.5F, -1.75F);
		face.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, -0.2182F);
		cube_r6.texOffs(0, 30).addBox(-2.75F, -0.7F, 0.4F, 3.0F, 1.0F, 1.0F, -0.3F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setPos(5.25F, -3.5F, -1.75F);
		face.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0F, 0.0F, 0.2182F);
		cube_r7.texOffs(0, 35).addBox(-0.25F, -0.75F, 0.4F, 3.0F, 1.0F, 1.0F, -0.3F, false);
	}

	@Override
	public void setupAnim(GarlicEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		
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
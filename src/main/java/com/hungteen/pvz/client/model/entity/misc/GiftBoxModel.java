package com.hungteen.pvz.client.model.entity.misc;// Made with Blockbench 4.1.3
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.hungteen.pvz.common.entity.misc.GiftBoxEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class GiftBoxModel extends EntityModel<GiftBoxEntity> {
	private final ModelRenderer total;
	private final ModelRenderer cover_r1;
	private final ModelRenderer tape1_r1;
	private final ModelRenderer tape2_r1;
	private final ModelRenderer tapehang1_r1;

	public GiftBoxModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(0, 16).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		total.texOffs(0, 0).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 7.0F, 8.0F, 0.0F, false);

		cover_r1 = new ModelRenderer(this);
		cover_r1.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(cover_r1);
		setRotationAngle(cover_r1, 0.0436F, -0.0873F, -0.0873F);
		cover_r1.texOffs(0, 16).addBox(-4.0F, -7.5F, -4.3F, 9.0F, 2.0F, 9.0F, 0.0F, false);

		tape1_r1 = new ModelRenderer(this);
		tape1_r1.setPos(0.0F, -9.0F, 0.0F);
		total.addChild(tape1_r1);
		setRotationAngle(tape1_r1, -0.0873F, 0.0F, 0.0F);
		tape1_r1.texOffs(0, 27).addBox(-1.0F, 0.4F, 0.0F, 2.0F, 1.0F, 4.0F, 0.0F, false);

		tape2_r1 = new ModelRenderer(this);
		tape2_r1.setPos(0.0F, -7.5F, 0.0F);
		total.addChild(tape2_r1);
		setRotationAngle(tape2_r1, 0.0F, 0.2618F, 0.0F);
		tape2_r1.texOffs(12, 27).addBox(-0.5F, -1.4F, -3.3F, 2.0F, 2.0F, 3.0F, 0.0F, false);

		tapehang1_r1 = new ModelRenderer(this);
		tapehang1_r1.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(tapehang1_r1);
		setRotationAngle(tapehang1_r1, 0.0F, -0.0873F, 0.0F);
		tapehang1_r1.texOffs(0, 0).addBox(-4.7F, -7.1F, -0.6F, 1.0F, 5.0F, 2.0F, 0.0F, false);
		tapehang1_r1.texOffs(0, 0).addBox(3.5F, -6.0F, -1.5F, 1.0F, 5.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(GiftBoxEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
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
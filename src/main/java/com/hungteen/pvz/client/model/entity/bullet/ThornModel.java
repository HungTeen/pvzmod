package com.hungteen.pvz.client.model.entity.bullet;

import com.hungteen.pvz.common.entity.bullet.ThornEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class ThornModel extends EntityModel<ThornEntity> {
	private final ModelRenderer total;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;

	public ThornModel() {
		texWidth = 16;
		texHeight = 16;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, -1.0F, -1.0F);
		total.addChild(bone);
		setRotationAngle(bone, -0.7854F, 0.0F, 0.0F);
		bone.texOffs(0, 5).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, -0.3F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, -1.0F, -1.0F);
		total.addChild(bone2);
		setRotationAngle(bone2, 0.0F, -0.7854F, 0.0F);
		bone2.texOffs(0, 9).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, -0.3F, false);
	}

	@Override
	public void setupAnim(ThornEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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
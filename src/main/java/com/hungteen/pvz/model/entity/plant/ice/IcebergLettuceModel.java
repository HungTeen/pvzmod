package com.hungteen.pvz.model.entity.plant.ice;

import com.hungteen.pvz.entity.plant.ice.IcebergLettuceEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class IcebergLettuceModel extends EntityModel<IcebergLettuceEntity> {
	private final ModelRenderer body;
	private final ModelRenderer hair;
	private final ModelRenderer jojo;
	private final ModelRenderer bone;
	private final ModelRenderer shell;
	private final ModelRenderer face;

	public IcebergLettuceModel() {
		texWidth = 128;
		texHeight = 128;

		body = new ModelRenderer(this);
		body.setPos(0.0F, 17.0F, 0.0F);
		body.texOffs(0, 96).addBox(-8.0F, -9.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		hair = new ModelRenderer(this);
		hair.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(hair);
		hair.texOffs(82, 88).addBox(-1.0F, -10.0F, -6.0F, 2.0F, 1.0F, 13.0F, 0.0F, false);

		jojo = new ModelRenderer(this);
		jojo.setPos(0.0F, -9.0F, -8.0F);
		hair.addChild(jojo);
		setRotationAngle(jojo, -0.6981F, 0.0F, 0.0F);
		jojo.texOffs(0, 105).addBox(-1.0F, -2.0F, -3.0F, 2.0F, 2.0F, 5.0F, -0.05F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, -1.0F, -3.0F);
		jojo.addChild(bone);
		setRotationAngle(bone, 0.7854F, 0.0F, 1.5708F);
		bone.texOffs(0, 101).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		shell = new ModelRenderer(this);
		shell.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(shell);
		shell.texOffs(0, 96).addBox(2.0F, 1.0F, -9.0F, 6.0F, 4.0F, 1.0F, 0.0F, false);
		shell.texOffs(48, 84).addBox(8.0F, 1.0F, -8.0F, 1.0F, 6.0F, 16.0F, 0.0F, false);
		shell.texOffs(98, 116).addBox(-9.0F, -1.0F, -2.0F, 1.0F, 2.0F, 10.0F, 0.0F, false);
		shell.texOffs(82, 110).addBox(8.0F, -1.0F, -2.0F, 1.0F, 2.0F, 10.0F, 0.0F, false);
		shell.texOffs(64, 115).addBox(-9.0F, -4.0F, 4.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
		shell.texOffs(110, 119).addBox(8.0F, -4.0F, 4.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
		shell.texOffs(94, 102).addBox(-8.0F, -6.0F, 8.0F, 16.0F, 13.0F, 1.0F, 0.0F, false);
		shell.texOffs(30, 93).addBox(-8.0F, 5.0F, -9.0F, 16.0F, 2.0F, 1.0F, 0.0F, false);
		shell.texOffs(82, 102).addBox(-2.0F, 2.0F, -9.0F, 4.0F, 3.0F, 1.0F, 0.0F, false);
		shell.texOffs(83, 111).addBox(-8.0F, 4.0F, -9.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);
		shell.texOffs(82, 107).addBox(-5.0F, 3.0F, -9.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);
		shell.texOffs(64, 106).addBox(-9.0F, 1.0F, -8.0F, 1.0F, 6.0F, 16.0F, 0.0F, false);

		face = new ModelRenderer(this);
		face.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(face);
		face.texOffs(0, 84).addBox(-6.0F, -6.0F, -8.5F, 4.0F, 6.0F, 1.0F, -0.45F, false);
		face.texOffs(12, 84).addBox(2.0F, -6.0F, -8.5F, 4.0F, 6.0F, 1.0F, -0.45F, false);
	}

	@Override
	public void setupAnim(IcebergLettuceEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
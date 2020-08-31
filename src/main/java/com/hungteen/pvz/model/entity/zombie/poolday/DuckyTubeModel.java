package com.hungteen.pvz.model.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.poolday.DuckyTubeEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class DuckyTubeModel extends EntityModel<DuckyTubeEntity> {
	private final ModelRenderer total;
	private final ModelRenderer bone;

	public DuckyTubeModel() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		total.setTextureOffset(80, 103).addBox(-11.0F, -5.0F, -10.0F, 4.0F, 5.0F, 20.0F, 0.0F, false);
		total.setTextureOffset(90, 90).addBox(-7.0F, -5.0F, 5.0F, 14.0F, 5.0F, 5.0F, 0.0F, false);
		total.setTextureOffset(88, 78).addBox(-7.0F, -5.0F, -10.0F, 14.0F, 5.0F, 6.0F, 0.0F, false);
		total.setTextureOffset(80, 51).addBox(7.0F, -5.0F, -10.0F, 4.0F, 5.0F, 20.0F, 0.0F, false);
		total.setTextureOffset(110, 37).addBox(-3.0F, -7.0F, -12.0F, 6.0F, 7.0F, 3.0F, 0.0F, false);
		total.setTextureOffset(102, 22).addBox(-3.0F, -14.0F, -15.0F, 6.0F, 7.0F, 7.0F, 0.0F, false);
		total.setTextureOffset(110, 14).addBox(-3.0F, -9.0F, -18.0F, 6.0F, 1.0F, 3.0F, 0.0F, false);
		total.setTextureOffset(62, 123).addBox(-2.0F, -14.0F, -8.0F, 4.0F, 1.0F, 1.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -5.0F, 10.0F);
		total.addChild(bone);
		setRotationAngle(bone, 0.5236F, 0.0F, 0.0F);
		bone.setTextureOffset(68, 107).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 5.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(DuckyTubeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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
package com.hungteen.pvz.model.entity.plant.explosion;

import com.hungteen.pvz.entity.plant.explosion.DoomShroomEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class DoomShroomModel extends EntityModel<DoomShroomEntity> {
	private final ModelRenderer total;
	private final ModelRenderer head;

	public DoomShroomModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(69, 89).addBox(-5.0F, -12.0F, -5.0F, 10.0F, 12.0F, 10.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -12.0F, 0.0F);
		total.addChild(head);
		head.texOffs(4, 70).addBox(-6.0F, -8.0F, -5.0F, 12.0F, 2.0F, 10.0F, 0.0F, false);
		head.texOffs(3, 88).addBox(-7.0F, -6.0F, -6.0F, 14.0F, 3.0F, 12.0F, 0.0F, false);
		head.texOffs(1, 108).addBox(-8.0F, -3.0F, -7.0F, 16.0F, 3.0F, 14.0F, 0.0F, false);
		head.texOffs(69, 118).addBox(-4.0F, -9.0F, -3.0F, 8.0F, 1.0F, 6.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(DoomShroomEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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
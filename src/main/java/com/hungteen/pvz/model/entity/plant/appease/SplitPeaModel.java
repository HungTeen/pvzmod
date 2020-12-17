package com.hungteen.pvz.model.entity.plant.appease;

import com.hungteen.pvz.entity.plant.appease.SplitPeaEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class SplitPeaModel extends EntityModel<SplitPeaEntity> {
	private final ModelRenderer total;
	private final ModelRenderer head;
	private final ModelRenderer down;
	private final ModelRenderer body;
	private final ModelRenderer head2;

	public SplitPeaModel() {
		textureWidth = 64;
		textureHeight = 64;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		total.setTextureOffset(35, 53).addBox(-3.0F, -22.0F, -1.0F, 6.0F, 7.0F, 2.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -15.0F, -5.0F);
		total.addChild(head);
		head.setTextureOffset(7, 15).addBox(-2.0F, -4.0F, -6.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(0, 16).addBox(-1.0F, -3.0F, -4.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 7.0F, 0.0F, false);

		down = new ModelRenderer(this);
		down.setRotationPoint(1.0F, 0.0F, 0.0F);
		total.addChild(down);
		down.setTextureOffset(0, 23).addBox(0.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.setTextureOffset(9, 23).addBox(-2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.setTextureOffset(20, 16).addBox(-2.0F, -1.0F, -3.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.setTextureOffset(20, 20).addBox(-4.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.setTextureOffset(1, 27).addBox(2.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		down.setTextureOffset(1, 33).addBox(-3.0F, -1.0F, 3.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		down.setTextureOffset(1, 38).addBox(-7.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		down.setTextureOffset(0, 44).addBox(-3.0F, -1.0F, -6.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(-1.0F, -13.0F, 0.0F);
		total.addChild(body);
		body.setTextureOffset(20, 24).addBox(0.0F, -2.0F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.setTextureOffset(16, 28).addBox(1.0F, -2.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.setTextureOffset(17, 32).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.setTextureOffset(17, 36).addBox(0.0F, -2.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.setTextureOffset(56, 48).addBox(0.0F, -1.0F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, false);

		head2 = new ModelRenderer(this);
		head2.setRotationPoint(0.0F, -15.0F, 4.0F);
		total.addChild(head2);
		head2.setTextureOffset(1, 49).addBox(-2.0F, -4.0F, 5.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
		head2.setTextureOffset(17, 40).addBox(-1.0F, -3.0F, 4.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		head2.setTextureOffset(29, 1).addBox(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 7.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(SplitPeaEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.total.rotateAngleY = entity.getRoundTick() * 3.1415926F / 10; 
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
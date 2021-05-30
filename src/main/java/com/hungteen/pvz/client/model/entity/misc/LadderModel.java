package com.hungteen.pvz.client.model.entity.misc;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class LadderModel<T extends PVZPlantEntity> extends EntityModel<T> {
	private final ModelRenderer total;
	private final ModelRenderer floor;

	public LadderModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, -5.0F, 0.0F);
		total.texOffs(14, 10).addBox(-12.0F, -22.0F, -2.0F, 3.0F, 50.0F, 4.0F, 0.1F, false);
		total.texOffs(0, 10).addBox(9.0F, -22.0F, -2.0F, 3.0F, 50.0F, 4.0F, 0.1F, false);
		total.texOffs(28, 50).addBox(8.0F, 28.0F, -3.0F, 5.0F, 1.0F, 6.0F, 0.0F, false);
		total.texOffs(28, 57).addBox(-13.0F, 28.0F, -3.0F, 5.0F, 1.0F, 6.0F, 0.0F, false);

		floor = new ModelRenderer(this);
		floor.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(floor);
		floor.texOffs(0, 4).addBox(-9.0F, 20.0F, -2.0F, 18.0F, 2.0F, 4.0F, 0.0F, false);
		floor.texOffs(0, 4).addBox(-9.0F, 12.0F, -2.0F, 18.0F, 2.0F, 4.0F, 0.0F, false);
		floor.texOffs(0, 4).addBox(-9.0F, 4.0F, -2.0F, 18.0F, 2.0F, 4.0F, 0.0F, false);
		floor.texOffs(0, 4).addBox(-9.0F, -4.0F, -2.0F, 18.0F, 2.0F, 4.0F, 0.0F, false);
		floor.texOffs(0, 4).addBox(-9.0F, -12.0F, -2.0F, 18.0F, 2.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		total.render(matrixStack, buffer, packedLight, packedOverlay);
	}
	
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay){
		total.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
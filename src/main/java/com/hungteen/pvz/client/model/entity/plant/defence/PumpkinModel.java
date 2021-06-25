package com.hungteen.pvz.client.model.entity.plant.defence;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.defence.PumpkinEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.1
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class PumpkinModel extends PVZPlantModel<PumpkinEntity> {
	private final ModelRenderer total;

	public PumpkinModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(0, 95).addBox(-16.0F, -1.0F, -16.0F, 32.0F, 1.0F, 32.0F, 0.0F, false);
		total.texOffs(0, 72).addBox(-16.0F, -17.0F, -16.0F, 32.0F, 16.0F, 1.0F, 0.0F, false);
		total.texOffs(62, 51).addBox(-16.0F, -17.0F, 15.0F, 32.0F, 16.0F, 1.0F, 0.0F, false);
		total.texOffs(0, 0).addBox(-16.0F, -17.0F, -15.0F, 1.0F, 16.0F, 30.0F, 0.0F, false);
		total.texOffs(66, 0).addBox(15.0F, -17.0F, -15.0F, 1.0F, 16.0F, 30.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(PumpkinEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		total.render(matrixStack, buffer, packedLight, packedOverlay);
	}
	
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay){
		total.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	@Override
	public EntityModel<PumpkinEntity> getPlantModel() {
		return this;
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

}
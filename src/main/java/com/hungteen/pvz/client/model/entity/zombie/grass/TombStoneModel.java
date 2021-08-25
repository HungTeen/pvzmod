package com.hungteen.pvz.client.model.entity.zombie.grass;

import com.hungteen.pvz.common.entity.zombie.custom.GigaTombStoneEntity;
import com.hungteen.pvz.common.entity.zombie.grass.AbstractTombStoneEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports


public class TombStoneModel<T extends AbstractTombStoneEntity> extends EntityModel<T> {
	private final ModelRenderer total;
	private final ModelRenderer logo;
	private final ModelRenderer red_logo;

	public TombStoneModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(56, 105).addBox(-9.0F, -4.0F, -9.0F, 18.0F, 4.0F, 18.0F, 0.0F, false);
		total.texOffs(56, 81).addBox(-9.0F, -38.0F, -9.0F, 18.0F, 4.0F, 18.0F, 0.0F, false);
		total.texOffs(0, 82).addBox(-8.0F, -34.0F, -8.0F, 2.0F, 30.0F, 16.0F, 0.0F, false);
		total.texOffs(92, 0).addBox(6.0F, -34.0F, -8.0F, 2.0F, 30.0F, 16.0F, 0.0F, false);
		total.texOffs(3, 5).addBox(-6.0F, -34.0F, -7.0F, 12.0F, 30.0F, 15.0F, 0.0F, false);

		logo = new ModelRenderer(this);
		logo.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(logo);
		logo.texOffs(0, 51).addBox(-3.0F, -30.0F, -7.1F, 6.0F, 7.0F, 1.0F, 0.0F, false);
		logo.texOffs(1, 60).addBox(-4.0F, -29.0F, -7.1F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		logo.texOffs(6, 60).addBox(3.0F, -29.0F, -7.1F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		logo.texOffs(1, 68).addBox(-2.0F, -31.0F, -7.1F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		logo.texOffs(1, 72).addBox(-2.0F, -23.0F, -7.1F, 4.0F, 1.0F, 1.0F, 0.0F, false);

		red_logo = new ModelRenderer(this);
		red_logo.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(red_logo);
		red_logo.texOffs(18, 51).addBox(-3.0F, -30.0F, -7.1F, 6.0F, 7.0F, 1.0F, 0.0F, false);
		red_logo.texOffs(16, 60).addBox(-4.0F, -29.0F, -7.1F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		red_logo.texOffs(22, 60).addBox(3.0F, -29.0F, -7.1F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		red_logo.texOffs(16, 68).addBox(-2.0F, -31.0F, -7.1F, 4.0F, 1.0F, 1.0F, 0.0F, false);
		red_logo.texOffs(14, 72).addBox(-2.0F, -23.0F, -7.1F, 4.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		final boolean red = entity instanceof GigaTombStoneEntity;
		this.red_logo.visible = red;
		this.logo.visible = ! red;
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
package com.hungteen.pvz.model.armor;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class FootballArmorModel extends BipedModel<LivingEntity> {
	private final ModelRenderer helmet;

	public FootballArmorModel(float scale) {
		super(scale, 0, 64, 64);

		helmet = new ModelRenderer(this);
		helmet.setRotationPoint(0.0F, 24.0F, 0.0F);
		helmet.setTextureOffset(0, 43).addBox(-9.0F, -18.0F, -10.0F, 18.0F, 2.0F, 19.0F, 0.0F, false);
		helmet.setTextureOffset(4, 48).addBox(-7.0F, -19.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);
		helmet.setTextureOffset(26, 30).addBox(9.0F, -16.0F, -9.0F, 1.0F, 16.0F, 18.0F, 0.0F, false);
		helmet.setTextureOffset(21, 45).addBox(-9.0F, -16.0F, 9.0F, 18.0F, 16.0F, 1.0F, 0.0F, false);
		helmet.setTextureOffset(17, 56).addBox(-9.0F, 0.0F, 5.0F, 18.0F, 1.0F, 4.0F, 0.0F, false);
		helmet.setTextureOffset(47, 53).addBox(-9.0F, -4.0F, -10.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		helmet.setTextureOffset(1, 55).addBox(8.0F, -4.0F, -10.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		helmet.setTextureOffset(26, 30).addBox(-10.0F, -16.0F, -9.0F, 1.0F, 16.0F, 18.0F, 0.0F, false);
		helmet.setTextureOffset(28, 57).addBox(-8.0F, -1.0F, -10.0F, 16.0F, 1.0F, 1.0F, 0.0F, false);
		helmet.setTextureOffset(23, 55).addBox(-8.0F, -4.0F, -10.0F, 16.0F, 1.0F, 1.0F, 0.0F, false);
		helmet.setTextureOffset(38, 47).addBox(-7.0F, -16.0F, -10.0F, 1.0F, 15.0F, 1.0F, 0.0F, false);
		helmet.setTextureOffset(48, 47).addBox(6.0F, -16.0F, -10.0F, 1.0F, 15.0F, 1.0F, 0.0F, false);
		helmet.setTextureOffset(47, 58).addBox(2.0F, -3.0F, -10.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		helmet.setTextureOffset(39, 58).addBox(-3.0F, -3.0F, -10.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		helmet.setTextureOffset(34, 53).addBox(-3.0F, -16.0F, -10.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		helmet.setTextureOffset(32, 54).addBox(2.0F, -16.0F, -10.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		
		this.bipedHead.addChild(helmet);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
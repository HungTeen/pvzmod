package com.hungteen.pvz.model.armor;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class BucketHeadModel extends BipedModel<LivingEntity> {
	private final ModelRenderer total;
	private final ModelRenderer pole;

	public BucketHeadModel(float scale) {
		super(scale, 0, 64, 64);

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, -8.0F, 0.0F);
		total.setTextureOffset(23, 43).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);
		total.setTextureOffset(0, 52).addBox(-5.0F, 0.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		total.setTextureOffset(0, 40).addBox(4.0F, 0.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		total.setTextureOffset(1, 35).addBox(-4.0F, 0.0F, -5.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		total.setTextureOffset(23, 35).addBox(-4.0F, 0.0F, 4.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);

		pole = new ModelRenderer(this);
		pole.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(pole);
		setRotationAngle(pole, 1.0472F, 0.0F, 0.0F);
		pole.setTextureOffset(0, 40).addBox(5.0F, -1.0F, -8.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		pole.setTextureOffset(0, 40).addBox(-6.0F, -1.0F, -8.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		pole.setTextureOffset(0, 40).addBox(-5.0F, -1.0F, -8.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		
		this.bipedHead.addChild(total);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
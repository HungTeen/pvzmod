package com.hungteen.pvz.client.model.armor;

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
		total.setPos(0.0F, -8.0F, 0.0F);
		total.texOffs(23, 43).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);
		total.texOffs(0, 52).addBox(-5.0F, 0.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		total.texOffs(0, 40).addBox(4.0F, 0.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		total.texOffs(1, 35).addBox(-4.0F, 0.0F, -5.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		total.texOffs(23, 35).addBox(-4.0F, 0.0F, 4.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);

		pole = new ModelRenderer(this);
		pole.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(pole);
		setRotationAngle(pole, 1.0472F, 0.0F, 0.0F);
		pole.texOffs(0, 40).addBox(5.0F, -1.0F, -8.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		pole.texOffs(0, 40).addBox(-6.0F, -1.0F, -8.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		pole.texOffs(0, 40).addBox(-5.0F, -1.0F, -8.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		
		this.head.addChild(total);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
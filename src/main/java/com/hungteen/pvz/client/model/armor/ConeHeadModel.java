package com.hungteen.pvz.client.model.armor;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class ConeHeadModel extends BipedModel<LivingEntity> {

	public ConeHeadModel(float scale) {
		super(scale, 0, 64, 64);

		ModelRenderer body = new ModelRenderer(this);
		body.setPos(0.0F, - 8F, 0.0F);
		body.texOffs(1, 33).addBox(-5.0F, -3.0F, -5.0F, 10.0F, 3.0F, 10.0F, 0.0F, false);
		body.texOffs(2, 47).addBox(-6.0F, 0.0F, -6.0F, 12.0F, 1.0F, 2.0F, 0.0F, false);
		body.texOffs(1, 51).addBox(-6.0F, 0.0F, 4.0F, 12.0F, 1.0F, 2.0F, 0.0F, false);
		body.texOffs(1, 54).addBox(4.0F, 0.0F, -4.0F, 2.0F, 1.0F, 8.0F, 0.0F, false);
		body.texOffs(42, 43).addBox(-6.0F, 0.0F, -4.0F, 2.0F, 1.0F, 8.0F, 0.0F, false);
		body.texOffs(31, 53).addBox(-4.0F, -6.0F, -4.0F, 8.0F, 3.0F, 8.0F, 0.0F, false);
		body.texOffs(40, 33).addBox(-3.0F, -9.0F, -3.0F, 6.0F, 3.0F, 6.0F, 0.0F, false);
		body.texOffs(15, 55).addBox(-2.0F, -12.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
		body.texOffs(1, 35).addBox(-1.0F, -15.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
		
		this.head.addChild(body);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
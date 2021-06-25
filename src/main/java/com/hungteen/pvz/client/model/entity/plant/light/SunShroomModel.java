package com.hungteen.pvz.client.model.entity.plant.light;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.light.SunShroomEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class SunShroomModel extends PVZPlantModel<SunShroomEntity> {
	private final ModelRenderer body;
	private final ModelRenderer hat;

	public SunShroomModel() {
		texWidth = 128;
		texHeight = 128;

		body = new ModelRenderer(this);
		body.setPos(0.0F, 24.0F, 0.0F);
		body.texOffs(1, 1).addBox(-7.0F, -14.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);

		hat = new ModelRenderer(this);
		hat.setPos(0.0F, -14.0F, 0.0F);
		body.addChild(hat);
		hat.texOffs(2, 97).addBox(-11.0F, -7.0F, -11.0F, 22.0F, 7.0F, 22.0F, 0.0F, false);
		hat.texOffs(1, 32).addBox(-10.0F, -12.0F, -10.0F, 20.0F, 5.0F, 20.0F, 0.0F, false);
		hat.texOffs(62, 4).addBox(-8.0F, -15.0F, -8.0F, 16.0F, 3.0F, 16.0F, 0.0F, false);
		hat.texOffs(77, 27).addBox(-6.0F, -16.0F, -6.0F, 12.0F, 1.0F, 12.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(SunShroomEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.body;
	}

	@Override
	public EntityModel<SunShroomEntity> getPlantModel() {
		return this;
	}
}
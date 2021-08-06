package com.hungteen.pvz.client.model.entity.plant.assist;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.assist.FlowerPotEntity;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class FlowerPotModel extends PVZPlantModel<FlowerPotEntity> {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;

	public FlowerPotModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(0, 53).addBox(-5.0F, -1.0F, -5.0F, 10.0F, 1.0F, 10.0F, 0.0F, false);
		total.texOffs(0, 50).addBox(-6.0F, -3.0F, -6.0F, 12.0F, 2.0F, 12.0F, 0.0F, false);
		total.texOffs(0, 47).addBox(-7.0F, -6.0F, -7.0F, 14.0F, 3.0F, 14.0F, 0.0F, false);
		total.texOffs(0, 0).addBox(-8.0F, -12.0F, -8.0F, 16.0F, 6.0F, 16.0F, 0.0F, false);
		total.texOffs(0, 48).addBox(-8.0F, -13.0F, -8.0F, 1.0F, 1.0F, 15.0F, 0.0F, false);
		total.texOffs(2, 31).addBox(7.0F, -13.0F, -8.0F, 1.0F, 1.0F, 15.0F, 0.0F, false);
		total.texOffs(1, 32).addBox(-7.0F, -13.0F, -8.0F, 14.0F, 1.0F, 1.0F, 0.0F, false);
		total.texOffs(2, 36).addBox(-8.0F, -13.0F, 7.0F, 16.0F, 1.0F, 1.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(1.0F, -11.5F, 5.5F);
		total.addChild(body);
		setRotationAngle(body, -0.1309F, 0.0F, -0.3054F);
		body.texOffs(55, 48).addBox(-1.0F, -5.0F, 0.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, -4.0F, 0.0F);
		body.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, -0.6109F);
		bone.texOffs(55, 55).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, -0.1F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(-0.25F, -3.75F, 0.0F);
		body.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.6545F);
		bone2.texOffs(55, 44).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, -0.1F, false);
	}

	@Override
	public void setupAnim(FlowerPotEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}
}
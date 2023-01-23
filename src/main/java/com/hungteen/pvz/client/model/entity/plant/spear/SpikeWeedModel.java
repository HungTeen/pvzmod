package com.hungteen.pvz.client.model.entity.plant.spear;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.spear.SpikeWeedEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class SpikeWeedModel extends PVZPlantModel<SpikeWeedEntity> {
	private final ModelRenderer total;
	private final ModelRenderer bone;

	public SpikeWeedModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(0, 19).addBox(-8.0F, -3.0F, -8.0F, 16.0F, 3.0F, 16.0F, 0.0F, false);
		total.texOffs(0, 0).addBox(-8.0F, -3.2F, -8.0F, 16.0F, 3.0F, 16.0F, 0.3F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(bone);
		bone.texOffs(32, 38).addBox(-8.0F, -6.0F, -4.0F, 16.0F, 4.0F, 0.0F, 0.0F, false);
		bone.texOffs(32, 42).addBox(-8.0F, -6.0F, 4.0F, 16.0F, 4.0F, 0.0F, 0.0F, false);
		bone.texOffs(0, 22).addBox(-4.0F, -6.0F, -8.0F, 0.0F, 4.0F, 16.0F, 0.0F, false);
		bone.texOffs(0, 26).addBox(4.0F, -6.0F, -8.0F, 0.0F, 4.0F, 16.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(SpikeWeedEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<SpikeWeedEntity> getPlantModel() {
		return this;
	}
}
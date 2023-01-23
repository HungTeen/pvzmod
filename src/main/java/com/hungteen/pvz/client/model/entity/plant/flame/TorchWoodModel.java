package com.hungteen.pvz.client.model.entity.plant.flame;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.flame.TorchWoodEntity;
import com.hungteen.pvz.common.entity.plant.flame.TorchWoodEntity.FlameTypes;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class TorchWoodModel extends PVZPlantModel<TorchWoodEntity> {
	private final ModelRenderer total;

	public TorchWoodModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(48, 11).addBox(-7.0F, -9.0F, -7.0F, 14.0F, 2.0F, 14.0F, 0.0F, false);
		total.texOffs(0, 25).addBox(-8.0F, -3.0F, -8.0F, 16.0F, 3.0F, 16.0F, 0.0F, false);
		total.texOffs(0, 0).addBox(-7.5F, -10.0F, -7.5F, 15.0F, 10.0F, 15.0F, 0.0F, false);
		total.texOffs(0, 44).addBox(-7.0F, -12.0F, -7.0F, 14.0F, 12.0F, 14.0F, 0.0F, false);
	}


	@Override
	public void setupAnim(TorchWoodEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
//		this.yellow.visible = entity.getFlameType() == FlameTypes.YELLOW;
//		this.blue.visible = entity.getFlameType() == FlameTypes.BLUE;
//		this.purple.visible = entity.getFlameType() == FlameTypes.PURPLE;
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<TorchWoodEntity> getPlantModel() {
		return this;
	}
}
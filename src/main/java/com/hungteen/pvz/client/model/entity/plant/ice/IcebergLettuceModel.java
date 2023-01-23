package com.hungteen.pvz.client.model.entity.plant.ice;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.ice.IcebergLettuceEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class IcebergLettuceModel extends PVZPlantModel<IcebergLettuceEntity> {
	private final ModelRenderer total;
	private final ModelRenderer berg;

	public IcebergLettuceModel() {
		texWidth = 32;
		texHeight = 32;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(0, 23).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);

		berg = new ModelRenderer(this);
		berg.setPos(0.0F, -1.0F, 0.0F);
		total.addChild(berg);
		berg.texOffs(0, 0).addBox(-3.0F, -5.5F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, true);
		berg.texOffs(0, 13).addBox(-2.5F, -5.0F, -2.5F, 5.0F, 5.0F, 5.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(IcebergLettuceEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<IcebergLettuceEntity> getPlantModel() {
		return this;
	}
}
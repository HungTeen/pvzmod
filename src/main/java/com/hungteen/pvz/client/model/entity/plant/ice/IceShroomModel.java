package com.hungteen.pvz.client.model.entity.plant.ice;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.ice.IceShroomEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class IceShroomModel extends PVZPlantModel<IceShroomEntity> {
	private final ModelRenderer total;
	private final ModelRenderer hat;

	public IceShroomModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(72, 64).addBox(-7.0F, -9.0F, -7.0F, 14.0F, 9.0F, 14.0F, 0.0F, false);
		total.texOffs(72, 87).addBox(-7.0F, -6.0F, -7.0F, 14.0F, 6.0F, 14.0F, 0.4F, false);
		total.texOffs(0, 105).addBox(-8.0F, -3.0F, -8.0F, 16.0F, 3.0F, 16.0F, 0.0F, false);

		hat = new ModelRenderer(this);
		hat.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(hat);
		hat.texOffs(0, 51).addBox(-9.0F, -15.0F, -9.0F, 18.0F, 6.0F, 18.0F, 0.0F, false);
		hat.texOffs(0, 25).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 1.0F, 16.0F, 0.0F, false);
		hat.texOffs(0, 0).addBox(-9.0F, -16.0F, -9.0F, 18.0F, 7.0F, 18.0F, 0.4F, false);
		hat.texOffs(60, 107).addBox(-8.5F, -9.0F, -8.5F, 17.0F, 4.0F, 17.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(IceShroomEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<IceShroomEntity> getPlantModel() {
		return this;
	}
}
package com.hungteen.pvz.client.model.entity.plant.magic;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.magic.HypnoShroomEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class HypnoShroomModel extends PVZPlantModel<HypnoShroomEntity> {
	private final ModelRenderer total;
	private final ModelRenderer reye;
	private final ModelRenderer leye;
	private final ModelRenderer hat;

	public HypnoShroomModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(0, 32).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 7.0F, 8.0F, 0.0F, false);

		reye = new ModelRenderer(this);
		reye.setPos(-3.0F, -4.0F, -4.25F);
		total.addChild(reye);
		reye.texOffs(0, 0).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 0.0F, 0.0F, false);

		leye = new ModelRenderer(this);
		leye.setPos(3.0F, -4.0F, -4.25F);
		total.addChild(leye);
		leye.texOffs(0, 4).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 0.0F, 0.0F, false);

		hat = new ModelRenderer(this);
		hat.setPos(0.0F, -10.0F, 0.0F);
		total.addChild(hat);
		hat.texOffs(0, 0).addBox(-6.0F, -1.0F, -6.0F, 12.0F, 5.0F, 12.0F, 0.0F, false);
		hat.texOffs(0, 17).addBox(-5.0F, -6.0F, -5.0F, 10.0F, 5.0F, 10.0F, 0.0F, false);
		hat.texOffs(30, 17).addBox(-3.0F, -9.0F, -3.0F, 6.0F, 3.0F, 6.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(HypnoShroomEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.hat.yRot = ageInTicks / 30;
//		this.leye.zRot = entity.getExistTick() / 10f;
//		this.reye.zRot = entity.getExistTick() / 10f;

	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<HypnoShroomEntity> getPlantModel() {
		return this;
	}
}
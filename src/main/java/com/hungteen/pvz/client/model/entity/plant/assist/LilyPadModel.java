package com.hungteen.pvz.client.model.entity.plant.assist;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.assist.LilyPadEntity;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class LilyPadModel extends PVZPlantModel<LilyPadEntity> {
	private final ModelRenderer total;

	public LilyPadModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(-8.0F, 16.0F, 8.0F);
		total.texOffs(23, 46).addBox(6.0F, 7.0F, -16.0F, 4.0F, 1.0F, 16.0F, 0.0F, false);
		total.texOffs(31, 30).addBox(10.0F, 7.0F, -15.0F, 2.0F, 1.0F, 14.0F, 0.0F, false);
		total.texOffs(31, 14).addBox(4.0F, 7.0F, -15.0F, 2.0F, 1.0F, 14.0F, 0.0F, false);
		total.texOffs(37, 1).addBox(12.0F, 7.0F, -14.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
		total.texOffs(1, 1).addBox(0.0F, 7.0F, -10.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		total.texOffs(13, 1).addBox(1.0F, 7.0F, -12.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		total.texOffs(11, 11).addBox(2.0F, 7.0F, -13.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		total.texOffs(2, 24).addBox(3.0F, 7.0F, -14.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
		total.texOffs(3, 38).addBox(13.0F, 7.0F, -13.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		total.texOffs(2, 50).addBox(14.0F, 7.0F, -12.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		total.texOffs(1, 12).addBox(15.0F, 7.0F, -10.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(LilyPadEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

}
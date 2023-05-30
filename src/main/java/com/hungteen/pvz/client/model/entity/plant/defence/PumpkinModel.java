package com.hungteen.pvz.client.model.entity.plant.defence;

import com.hungteen.pvz.client.model.entity.ComponentModel;
import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.defence.PumpkinEntity;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 4.1.3
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


public class PumpkinModel extends PVZPlantModel<PumpkinEntity> {

	private final ModelRenderer total;

	public PumpkinModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		setRotationAngle(total, 0.0F, 1.5708F, 0.0F);
		total.texOffs(0, 0).addBox(-8.5F, -10.0F, -8.5F, 17.0F, 10.0F, 17.0F, 0.0F, false);
		total.texOffs(2, 27).addBox(8.0F, -6.0F, -8.5F, 1.0F, 6.0F, 17.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(PumpkinEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {

	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	public static class PumpkinArmorModel<T extends PVZPlantEntity> extends ComponentModel<T> {

		private final ModelRenderer total;

		public PumpkinArmorModel() {
			texWidth = 128;
			texHeight = 128;

			total = new ModelRenderer(this);
			total.setPos(0.0F, 24.0F, 0.0F);
			setRotationAngle(total, 0.0F, 1.5708F, 0.0F);
			total.texOffs(0, 0).addBox(-8.5F, -10.0F, -8.5F, 17.0F, 10.0F, 17.0F, 0.0F, false);
			total.texOffs(2, 27).addBox(8.0F, -6.0F, -8.5F, 1.0F, 6.0F, 17.0F, 0.0F, false);
		}

		@Override
		public ModelRenderer getTotalModel() {
			return this.total;
		}
	}

}
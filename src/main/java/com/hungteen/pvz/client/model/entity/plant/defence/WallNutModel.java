package com.hungteen.pvz.client.model.entity.plant.defence;

import com.hungteen.pvz.client.model.entity.ComponentModel;
import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.defence.WallNutEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class WallNutModel<T extends WallNutEntity> extends PVZPlantModel<T> {
	private final ModelRenderer body;

	public WallNutModel() {
		texWidth = 128;
		texHeight = 128;

		body = new ModelRenderer(this);
		body.setPos(0.0F, 24.0F, 0.0F);
		body.texOffs(0, 42).addBox(-5.0F, -1.0F, -5.0F, 10.0F, 1.0F, 10.0F, 0.0F, false);
		body.texOffs(0, 0).addBox(-7.0F, -14.0F, -7.0F, 14.0F, 13.0F, 14.0F, 0.0F, false);
		body.texOffs(0, 27).addBox(-6.0F, -17.0F, -6.0F, 12.0F, 3.0F, 12.0F, 0.0F, false);
		body.texOffs(0, 57).addBox(-7.0F, -14.0F, -6.6F, 14.0F, 13.0F, 1.0F, 0.0F, false);
		body.texOffs(56, 0).addBox(-3.0F, -12.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.body;
	}

	@Override
	public EntityModel<T> getPlantModel() {
		return this;
	}

	public static class WallNutArmorModel extends ComponentModel<WallNutEntity> {

		private final ModelRenderer body;

		public WallNutArmorModel() {
			texWidth = 64;
			texHeight = 64;

			body = new ModelRenderer(this);
			body.setPos(0.0F, 24.0F, 0.0F);
			body.texOffs(0, 0).addBox(-8.0F, -15.0F, -8.0F, 16.0F, 13.0F, 16.0F, 0.0F, false);
			body.texOffs(0, 29).addBox(-7.0F, -18.0F, -7.0F, 14.0F, 3.0F, 14.0F, 0.0F, false);
		}

		@Override
		public ModelRenderer getTotalModel() {
			return this.body;
		}
	}
}
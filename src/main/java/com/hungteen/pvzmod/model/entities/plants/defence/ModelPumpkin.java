package com.hungteen.pvzmod.model.entities.plants.defence;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelPumpkin extends ModelBase {
	private final ModelRenderer total;

	public ModelPumpkin() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		total.cubeList.add(new ModelBox(total, 0, 95, -16.0F, -1.0F, -16.0F, 32, 1, 32, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 0, 72, -16.0F, -17.0F, -16.0F, 32, 16, 1, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 62, 51, -16.0F, -17.0F, 15.0F, 32, 16, 1, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 0, 0, -16.0F, -17.0F, -15.0F, 1, 16, 30, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 66, 0, 15.0F, -17.0F, -15.0F, 1, 16, 30, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		total.render(f5);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
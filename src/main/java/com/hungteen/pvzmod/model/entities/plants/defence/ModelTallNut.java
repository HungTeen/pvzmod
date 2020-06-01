package com.hungteen.pvzmod.model.entities.plants.defence;
// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTallNut extends ModelBase {
	private final ModelRenderer body;

	public ModelTallNut() {
		textureWidth = 512;
		textureHeight = 512;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 416, 486, -11.0F, -1.0F, -11.0F, 22, 1, 22, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 272, 476, -15.0F, -3.0F, -15.0F, 30, 2, 30, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 6, 408, -17.0F, -71.0F, -17.0F, 34, 68, 34, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 156, 420, 17.0F, -69.0F, -14.0F, 2, 64, 28, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 308, -19.0F, -69.0F, -14.0F, 2, 64, 28, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 84, 334, -14.0F, -69.0F, -19.0F, 28, 64, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 174, 348, -14.0F, -69.0F, 17.0F, 28, 64, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 246, 428, -14.0F, -73.0F, -14.0F, 28, 2, 28, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 380, 442, -11.0F, -75.0F, -11.0F, 22, 2, 22, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 466, 354, -20.0F, -66.0F, -10.0F, 1, 57, 20, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 410, 342, 19.0F, -64.0F, -10.0F, 1, 55, 20, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 346, 356, -11.0F, -65.0F, -20.0F, 22, 57, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 278, 344, -11.0F, -65.0F, 19.0F, 22, 57, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 458, 326, -7.0F, -77.0F, -7.0F, 14, 2, 14, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body.render(f5);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
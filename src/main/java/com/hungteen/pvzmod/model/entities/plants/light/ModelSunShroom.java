package com.hungteen.pvzmod.model.entities.plants.light;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelSunShroom extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer hat;

	public ModelSunShroom() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 63, 94, -8.0F, -16.0F, -8.0F, 16, 16, 16, 0.0F, false));

		hat = new ModelRenderer(this);
		hat.setRotationPoint(0.0F, -16.0F, 0.0F);
		body.addChild(hat);
		hat.cubeList.add(new ModelBox(hat, 30, 64, -12.0F, -4.0F, -12.0F, 24, 4, 24, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 47, 39, -10.0F, -8.0F, -10.0F, 20, 4, 20, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 68, 3, -7.0F, -11.0F, -7.0F, 14, 3, 14, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 92, 26, -4.0F, -12.0F, -4.0F, 8, 1, 8, 0.0F, false));
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
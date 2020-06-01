package com.hungteen.pvzmod.model.entities.zombies.special;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelTombStone extends ModelBase {
	private final ModelRenderer total;

	public ModelTombStone() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		total.cubeList.add(new ModelBox(total, 56, 105, -9.0F, -4.0F, -9.0F, 18, 4, 18, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 56, 81, -9.0F, -38.0F, -9.0F, 18, 4, 18, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 0, 82, -8.0F, -34.0F, -8.0F, 2, 30, 16, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 92, 0, 6.0F, -34.0F, -8.0F, 2, 30, 16, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 3, 5, -6.0F, -34.0F, -7.0F, 12, 30, 15, 0.0F, false));
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
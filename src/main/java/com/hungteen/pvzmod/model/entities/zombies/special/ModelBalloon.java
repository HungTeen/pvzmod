package com.hungteen.pvzmod.model.entities.zombies.special;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelBalloon extends ModelBase {
	private final ModelRenderer balloon;

	public ModelBalloon() {
		textureWidth = 128;
		textureHeight = 128;

		balloon = new ModelRenderer(this);
		balloon.setRotationPoint(0.0F, 23.0F, 0.0F);
		balloon.cubeList.add(new ModelBox(balloon, 119, 106, -1.0F, -18.0F, -1.0F, 2, 19, 2, 0.0F, false));
		balloon.cubeList.add(new ModelBox(balloon, 93, 120, -3.0F, -19.0F, -3.0F, 6, 1, 6, 0.0F, false));
		balloon.cubeList.add(new ModelBox(balloon, 51, 115, -5.0F, -20.0F, -5.0F, 10, 1, 10, 0.0F, false));
		balloon.cubeList.add(new ModelBox(balloon, 89, 108, -3.0F, -42.0F, -3.0F, 6, 1, 6, 0.0F, false));
		balloon.cubeList.add(new ModelBox(balloon, 2, 116, -5.0F, -41.0F, -5.0F, 10, 1, 10, 0.0F, false));
		balloon.cubeList.add(new ModelBox(balloon, 63, 2, -8.0F, -40.0F, -8.0F, 16, 20, 16, 0.0F, false));
		balloon.cubeList.add(new ModelBox(balloon, 95, 42, -7.0F, -39.0F, -9.0F, 14, 18, 1, 0.0F, false));
		balloon.cubeList.add(new ModelBox(balloon, 103, 64, -5.0F, -37.0F, 9.0F, 10, 14, 1, 0.0F, false));
		balloon.cubeList.add(new ModelBox(balloon, 97, 82, -7.0F, -39.0F, 8.0F, 14, 18, 1, 0.0F, false));
		balloon.cubeList.add(new ModelBox(balloon, 71, 91, -5.0F, -37.0F, -10.0F, 10, 14, 1, 0.0F, false));
		balloon.cubeList.add(new ModelBox(balloon, 5, 80, 8.0F, -39.0F, -7.0F, 1, 18, 14, 0.0F, false));
		balloon.cubeList.add(new ModelBox(balloon, 42, 84, -10.0F, -37.0F, -5.0F, 1, 14, 10, 0.0F, false));
		balloon.cubeList.add(new ModelBox(balloon, 60, 45, -9.0F, -39.0F, -7.0F, 1, 18, 14, 0.0F, false));
		balloon.cubeList.add(new ModelBox(balloon, 33, 53, 9.0F, -37.0F, -5.0F, 1, 14, 10, 0.0F, false));
		balloon.cubeList.add(new ModelBox(balloon, 10, 69, -2.0F, -13.0F, -2.0F, 4, 2, 4, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		balloon.render(f5);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
package com.hungteen.pvzmod.model.entities.plants.magic;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelHypnoShroom extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer hat;

	public ModelHypnoShroom() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 63, 86, -8.0F, -25.0F, -8.0F, 16, 25, 16, 0.0F, false));

		hat = new ModelRenderer(this);
		hat.setRotationPoint(0.0F, -26.0F, 0.0F);
		setRotationAngle(hat, -0.0873F, 0.0F, 0.0F);
		body.addChild(hat);
		hat.cubeList.add(new ModelBox(hat, 1, 1, -12.0F, -6.0F, -12.0F, 24, 8, 24, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 1, 35, -10.0F, -14.0F, -10.0F, 20, 8, 20, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 1, 66, -7.0F, -20.0F, -7.0F, 14, 6, 14, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 2, 90, -4.0F, -24.0F, -4.0F, 8, 4, 8, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 4, 107, -2.0F, -26.0F, -2.0F, 4, 2, 4, 0.0F, false));
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
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		this.hat.rotateAngleY=ageInTicks/30;
	}
}
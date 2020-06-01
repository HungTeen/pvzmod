package com.hungteen.pvzmod.model.entities.plants.fight;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


public class ModelSquash extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer bone;

	public ModelSquash() {
		textureWidth = 256;
		textureHeight = 256;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 125, 202, -16.0F, -16.0F, -16.0F, 32, 16, 32, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 138, 153, -14.0F, -32.0F, -14.0F, 28, 16, 28, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 40, 148, -11.0F, -44.0F, -11.0F, 22, 12, 22, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -50.0F, 0.0F);
		body.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, 0.5236F);
		bone.cubeList.add(new ModelBox(bone, 235, 130, 1.0F, -1.8038F, -2.0F, 4, 9, 4, 0.0F, false));
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
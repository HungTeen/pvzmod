package com.hungteen.pvzmod.model.entities.plants.explosion;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


public class ModelPotatoMine extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer string;

	public ModelPotatoMine() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 17.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		body.cubeList.add(new ModelBox(body, 1, 105, -10.0F, 5.0F, -10.0F, 20, 2, 20, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 4, 82, -9.0F, 2.0F, -9.0F, 18, 3, 18, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 4, 61, -8.0F, -1.0F, -8.0F, 16, 3, 16, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 1, 2, -6.0F, -2.0F, -6.0F, 12, 1, 12, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 21, 24, -3.0F, -3.0F, -3.0F, 6, 1, 6, 0.0F, false));

		string = new ModelRenderer(this);
		string.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(string);
		string.cubeList.add(new ModelBox(string, 8, 35, -1.0F, -15.0F, -1.0F, 2, 12, 2, 0.0F, false));
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
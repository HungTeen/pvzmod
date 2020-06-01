package com.hungteen.pvzmod.model.entities.plants.defence;

//Made with Blockbench
//Paste this code into your mod.

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelNutWall extends ModelBase {
	private final ModelRenderer body;

	public ModelNutWall() {
		textureWidth = 256;
		textureHeight = 256;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 168, 233, -11.0F, 23.0F, -11.0F, 22, 1, 22, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 144, 197, -14.0F, 21.0F, -14.0F, 28, 2, 28, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 180, -16.0F, -23.0F, -16.0F, 32, 44, 32, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 107, 16.0F, -21.0F, -14.0F, 2, 40, 28, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 65, 106, -18.0F, -21.0F, -14.0F, 2, 40, 28, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 196, 0, -14.0F, -21.0F, -18.0F, 28, 40, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 196, 47, -14.0F, -21.0F, 16.0F, 28, 40, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 144, 96, -14.0F, -25.0F, -14.0F, 28, 2, 28, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 176, 132, -11.0F, -27.0F, -11.0F, 22, 2, 22, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 144, 3, -19.0F, -17.0F, -10.0F, 1, 32, 20, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 88, 0, 18.0F, -17.0F, -10.0F, 1, 32, 20, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 30, 47, -11.0F, -18.0F, -19.0F, 22, 34, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 2, 4, -11.0F, -18.0F, 18.0F, 22, 34, 1, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 187, 166, -7.0F, -29.0F, -7.0F, 14, 2, 14, 0.0F, false));
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
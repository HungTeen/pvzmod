package com.hungteen.pvzmod.model.items;

//Made with Blockbench
//Paste this code into your mod.

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelBarrier extends ModelBiped {
	private final ModelRenderer body;

	public ModelBarrier() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 23.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 81, 114, -5.0F, -34.0F, -5.0F, 10, 3, 10, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 89, 108, -6.0F, -31.0F, -6.0F, 12, 1, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 91, 101, -6.0F, -31.0F, 4.0F, 12, 1, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 100, 88, 4.0F, -31.0F, -4.0F, 2, 1, 8, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 104, 75, -6.0F, -31.0F, -4.0F, 2, 1, 8, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 6, 116, -4.0F, -37.0F, -4.0F, 8, 3, 8, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 6, 102, -3.0F, -40.0F, -3.0F, 6, 3, 6, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 107, 5, -2.0F, -43.0F, -2.0F, 4, 3, 4, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 111, 41, -1.0F, -46.0F, -1.0F, 2, 3, 2, 0.0F, false));
		
		this.bipedHead.addChild(body);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
//		GlStateManager.scale(0.5f, 0.5f, 0.5f);
		super.render(entity, f, f1,  f2,  f3,  f4,  f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
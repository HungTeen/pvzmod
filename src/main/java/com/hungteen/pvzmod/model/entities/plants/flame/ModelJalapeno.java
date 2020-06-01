package com.hungteen.pvzmod.model.entities.plants.flame;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelJalapeno extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;

	public ModelJalapeno() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 2.0F);
		body.cubeList.add(new ModelBox(body, 63, 94, -8.0F, -40.0F, -8.0F, 16, 16, 16, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 69, 76, -7.0F, -24.0F, -7.0F, 14, 2, 14, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 78, 58, -6.0F, -22.0F, -6.0F, 12, 3, 12, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 88, 42, -5.0F, -19.0F, -5.0F, 10, 4, 10, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 95, 24, -4.0F, -15.0F, -5.0F, 8, 5, 8, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 103, 9, -3.0F, -10.0F, -6.0F, 5, 4, 7, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 75, 2, -3.0F, -6.0F, -8.0F, 6, 3, 7, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 67, 15, -3.0F, -3.0F, -12.0F, 6, 3, 8, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 0, -7.0F, -41.0F, -7.0F, 14, 1, 14, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 0, -6.0F, -42.0F, -6.0F, 12, 1, 12, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -3.0F, -12.0F);
		setRotationAngle(bone, 1.0472F, 0.0F, 0.0F);
		body.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 76, 30, -2.0F, -3.0F, -1.0F, 4, 4, 1, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, -3.0F, 0.0F);
		setRotationAngle(bone2, 0.6981F, 0.0F, 0.0F);
		bone.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 68, 42, -2.0F, -1.0F, -1.0F, 4, 1, 4, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, -0.0152F, 2.8264F);
		setRotationAngle(bone3, 0.9599F, 0.0F, 0.0F);
		bone2.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 75, 52, -1.0F, -1.0038F, -0.0872F, 2, 4, 1, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, -42.0F, 0.0F);
		setRotationAngle(bone4, 0.0F, 0.0F, 0.2618F);
		body.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 0, 0, -1.0F, -6.0F, -1.0F, 2, 7, 2, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(1.2247F, -5.2929F, 0.0F);
		setRotationAngle(bone5, 0.0F, 0.0F, -0.4363F);
		bone4.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 0, 0, -2.0F, -2.0F, -1.0F, 6, 2, 2, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(4.6375F, 2.1433F, 0.0F);
		setRotationAngle(bone6, 0.0F, 0.0F, -0.6109F);
		bone5.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 0, 0, 0.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.7071F, -0.1213F, 0.0F);
		setRotationAngle(bone7, 0.0F, 0.0F, -1.9199F);
		bone6.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 0, 0, -1.0F, -3.0F, -1.0F, 1, 4, 2, 0.0F, false));
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
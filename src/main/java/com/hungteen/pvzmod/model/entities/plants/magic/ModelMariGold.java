package com.hungteen.pvzmod.model.entities.plants.magic;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelMariGold extends ModelBase {
	private final ModelRenderer YEZI_1;
	private final ModelRenderer YEZI_2;
	private final ModelRenderer YEZI_3;
	private final ModelRenderer YEZI_4;
	private final ModelRenderer body;
	private final ModelRenderer TUO;
	private final ModelRenderer HUABAN;

	public ModelMariGold() {
		textureWidth = 128;
		textureHeight = 128;

		YEZI_1 = new ModelRenderer(this);
		YEZI_1.setRotationPoint(2.0F, 24.0F, 0.0F);
		YEZI_1.cubeList.add(new ModelBox(YEZI_1, 114, 0, 0.0F, -1.0F, -2.0F, 3, 1, 4, 0.0F, false));
		YEZI_1.cubeList.add(new ModelBox(YEZI_1, 79, 0, 3.0F, -2.0F, -4.0F, 8, 2, 8, 0.0F, false));

		YEZI_2 = new ModelRenderer(this);
		YEZI_2.setRotationPoint(0.0F, 24.0F, 2.0F);
		YEZI_2.cubeList.add(new ModelBox(YEZI_2, 78, 12, -4.0F, -2.0F, 3.0F, 8, 2, 8, 0.0F, false));
		YEZI_2.cubeList.add(new ModelBox(YEZI_2, 114, 9, -2.0F, -1.0F, 0.0F, 4, 1, 3, 0.0F, false));

		YEZI_3 = new ModelRenderer(this);
		YEZI_3.setRotationPoint(0.0F, 24.0F, -2.0F);
		YEZI_3.cubeList.add(new ModelBox(YEZI_3, 114, 19, -2.0F, -1.0F, -3.0F, 4, 1, 3, 0.0F, false));
		YEZI_3.cubeList.add(new ModelBox(YEZI_3, 0, 36, -4.0F, -2.0F, -11.0F, 8, 2, 8, 0.0F, false));

		YEZI_4 = new ModelRenderer(this);
		YEZI_4.setRotationPoint(-2.0F, 23.0F, 0.0F);
		YEZI_4.cubeList.add(new ModelBox(YEZI_4, 78, 26, -11.0F, -1.0F, -4.0F, 8, 2, 8, 0.0F, false));
		YEZI_4.cubeList.add(new ModelBox(YEZI_4, 114, 28, -3.0F, 0.0F, -2.0F, 3, 1, 4, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 23.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 112, 43, -2.0F, -31.0F, -2.0F, 4, 32, 4, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 85, 41, -2.0F, -39.0F, -4.0F, 4, 8, 6, 0.0F, false));

		TUO = new ModelRenderer(this);
		TUO.setRotationPoint(0.0F, -15.0F, 0.0F);
		TUO.cubeList.add(new ModelBox(TUO, 72, 100, -10.0F, -12.0F, -6.0F, 20, 20, 4, 0.0F, false));
		TUO.cubeList.add(new ModelBox(TUO, 0, 106, 10.0F, -10.0F, -6.0F, 3, 16, 4, 0.0F, false));
		TUO.cubeList.add(new ModelBox(TUO, 17, 106, -13.0F, -10.0F, -6.0F, 3, 16, 4, 0.0F, false));
		TUO.cubeList.add(new ModelBox(TUO, 0, 85, -8.0F, -15.0F, -6.0F, 16, 3, 4, 0.0F, false));
		TUO.cubeList.add(new ModelBox(TUO, 0, 74, -8.0F, 8.0F, -6.0F, 16, 3, 4, 0.0F, false));

		HUABAN = new ModelRenderer(this);
		HUABAN.setRotationPoint(0.0F, -3.0F, 0.0F);
		TUO.addChild(HUABAN);
		HUABAN.cubeList.add(new ModelBox(HUABAN, 0, 62, -4.0F, -14.0F, -6.0F, 8, 2, 4, 0.0F, false));
		HUABAN.cubeList.add(new ModelBox(HUABAN, 29, 63, -4.0F, 14.0F, -6.0F, 8, 2, 4, 0.0F, false));
		HUABAN.cubeList.add(new ModelBox(HUABAN, 42, 36, 13.0F, -3.0F, -6.0F, 2, 8, 4, 0.0F, false));
		HUABAN.cubeList.add(new ModelBox(HUABAN, 73, 73, -15.0F, -3.0F, -6.0F, 2, 8, 4, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		YEZI_1.render(f5);
		YEZI_2.render(f5);
		YEZI_3.render(f5);
		YEZI_4.render(f5);
		body.render(f5);
		TUO.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
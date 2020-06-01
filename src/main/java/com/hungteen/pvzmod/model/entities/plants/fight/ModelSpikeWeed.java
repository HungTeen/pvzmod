package com.hungteen.pvzmod.model.entities.plants.fight;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelSpikeWeed extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer bone13;

	public ModelSpikeWeed() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -12.0F, -2.0F, -12.0F, 24, 2, 24, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -2.0F, 0.0F);
		setRotationAngle(bone, 0.7854F, 0.0F, -0.6109F);
		body.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 104, 4, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-9.0F, -2.0F, 9.0F);
		setRotationAngle(bone2, -0.7854F, -0.7854F, 0.7854F);
		body.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 105, 14, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, -2.0F, 8.0F);
		setRotationAngle(bone3, -0.7854F, 0.0F, 0.6109F);
		body.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 106, 23, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(9.0F, -2.0F, 9.0F);
		setRotationAngle(bone4, -0.7854F, -0.7854F, 0.7854F);
		body.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 107, 32, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(-9.0F, -2.0F, -9.0F);
		setRotationAngle(bone5, 0.7854F, 0.7854F, -0.7854F);
		body.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 108, 42, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(9.0F, -2.0F, -9.0F);
		setRotationAngle(bone6, -0.7854F, -0.7854F, 0.7854F);
		body.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 110, 50, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(-8.0F, -2.0F, 0.0F);
		setRotationAngle(bone7, 0.0F, -0.7854F, 0.6109F);
		body.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 114, 58, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(7.0F, -2.0F, 0.0F);
		setRotationAngle(bone8, 0.0F, 0.7854F, -0.6109F);
		body.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 113, 70, -0.4208F, -0.4264F, -0.4208F, 2, 2, 2, 0.0F, false));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, -2.0F, -8.0F);
		setRotationAngle(bone9, -0.7854F, 0.0F, -0.6109F);
		body.addChild(bone9);
		bone9.cubeList.add(new ModelBox(bone9, 118, 82, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(-5.0F, -2.0F, -4.0F);
		setRotationAngle(bone10, 0.7854F, 0.0F, -0.6109F);
		body.addChild(bone10);
		bone10.cubeList.add(new ModelBox(bone10, 116, 92, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(-5.0F, -2.0F, 5.0F);
		setRotationAngle(bone11, -0.7854F, 0.0F, 0.6109F);
		body.addChild(bone11);
		bone11.cubeList.add(new ModelBox(bone11, 115, 102, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(4.0F, -2.0F, 5.0F);
		setRotationAngle(bone12, 0.7854F, 0.0F, -0.6109F);
		body.addChild(bone12);
		bone12.cubeList.add(new ModelBox(bone12, 114, 111, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(4.0F, -2.0F, -4.0F);
		setRotationAngle(bone13, -0.7854F, 0.0F, 0.6109F);
		body.addChild(bone13);
		bone13.cubeList.add(new ModelBox(bone13, 107, 119, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));
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
package com.hungteen.pvzmod.model.entities.plants.fight;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelSpikeRock extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer t1;
	private final ModelRenderer spike1;
	private final ModelRenderer t2;
	private final ModelRenderer spike2;
	private final ModelRenderer t3;
	private final ModelRenderer spike3;
	private final ModelRenderer spike4;
	private final ModelRenderer spike5;
	private final ModelRenderer spike6;
	private final ModelRenderer spike7;
	private final ModelRenderer spike8;
	private final ModelRenderer spike9;
	private final ModelRenderer spike10;
	private final ModelRenderer spike11;
	private final ModelRenderer spike12;
	private final ModelRenderer spike13;
	private final ModelRenderer spike14;
	private final ModelRenderer spike15;

	public ModelSpikeRock() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		total.cubeList.add(new ModelBox(total, 31, 102, -12.0F, -1.0F, -12.0F, 24, 1, 24, 0.0F, false));

		t1 = new ModelRenderer(this);
		t1.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(t1);
		t1.cubeList.add(new ModelBox(t1, 119, 89, -1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F, false));

		spike1 = new ModelRenderer(this);
		spike1.setRotationPoint(0.0F, -4.0F, 0.0F);
		setRotationAngle(spike1, 0.0F, 0.0F, 0.7854F);
		t1.addChild(spike1);
		spike1.cubeList.add(new ModelBox(spike1, 119, 96, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		t2 = new ModelRenderer(this);
		t2.setRotationPoint(10.0F, 0.0F, 0.0F);
		setRotationAngle(t2, 0.0F, 0.0F, 0.1745F);
		total.addChild(t2);
		t2.cubeList.add(new ModelBox(t2, 108, 87, -1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F, false));

		spike2 = new ModelRenderer(this);
		spike2.setRotationPoint(0.0F, -4.0F, 0.0F);
		setRotationAngle(spike2, 0.0F, 0.0F, 0.7854F);
		t2.addChild(spike2);
		spike2.cubeList.add(new ModelBox(spike2, 108, 95, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		t3 = new ModelRenderer(this);
		t3.setRotationPoint(-10.0F, 0.0F, 0.0F);
		setRotationAngle(t3, 0.0F, 0.0F, -0.1745F);
		total.addChild(t3);
		t3.cubeList.add(new ModelBox(t3, 98, 87, -1.0F, -4.0F, -1.0F, 2, 4, 2, 0.0F, false));

		spike3 = new ModelRenderer(this);
		spike3.setRotationPoint(0.0F, -4.0F, 0.0F);
		setRotationAngle(spike3, 0.0F, 0.0F, 0.7854F);
		t3.addChild(spike3);
		spike3.cubeList.add(new ModelBox(spike3, 96, 95, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		spike4 = new ModelRenderer(this);
		spike4.setRotationPoint(0.0F, -1.0F, -9.0F);
		setRotationAngle(spike4, 0.0F, 0.0F, 0.7854F);
		total.addChild(spike4);
		spike4.cubeList.add(new ModelBox(spike4, 86, 96, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		spike5 = new ModelRenderer(this);
		spike5.setRotationPoint(10.0F, -1.0F, -9.0F);
		setRotationAngle(spike5, -0.7854F, 0.7854F, 0.0F);
		total.addChild(spike5);
		spike5.cubeList.add(new ModelBox(spike5, 87, 89, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		spike6 = new ModelRenderer(this);
		spike6.setRotationPoint(-10.0F, -1.0F, -9.0F);
		setRotationAngle(spike6, -0.7854F, -0.7854F, 0.0F);
		total.addChild(spike6);
		spike6.cubeList.add(new ModelBox(spike6, 75, 96, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		spike7 = new ModelRenderer(this);
		spike7.setRotationPoint(-6.0F, -1.0F, -5.0F);
		setRotationAngle(spike7, -0.7854F, 0.7854F, 0.0F);
		total.addChild(spike7);
		spike7.cubeList.add(new ModelBox(spike7, 76, 88, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		spike8 = new ModelRenderer(this);
		spike8.setRotationPoint(6.0F, -1.0F, -5.0F);
		setRotationAngle(spike8, -0.7854F, -0.7854F, 0.0F);
		total.addChild(spike8);
		spike8.cubeList.add(new ModelBox(spike8, 117, 81, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		spike9 = new ModelRenderer(this);
		spike9.setRotationPoint(-10.0F, -1.0F, 9.0F);
		setRotationAngle(spike9, 0.7854F, 0.7854F, 0.0F);
		total.addChild(spike9);
		spike9.cubeList.add(new ModelBox(spike9, 106, 80, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		spike10 = new ModelRenderer(this);
		spike10.setRotationPoint(10.0F, -1.0F, 9.0F);
		setRotationAngle(spike10, -0.7854F, -0.7854F, 0.0F);
		total.addChild(spike10);
		spike10.cubeList.add(new ModelBox(spike10, 95, 80, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		spike11 = new ModelRenderer(this);
		spike11.setRotationPoint(0.0F, -1.0F, 9.0F);
		setRotationAngle(spike11, 0.0F, 0.0F, 0.7854F);
		total.addChild(spike11);
		spike11.cubeList.add(new ModelBox(spike11, 85, 81, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		spike12 = new ModelRenderer(this);
		spike12.setRotationPoint(-6.0F, -1.0F, 5.0F);
		setRotationAngle(spike12, -0.7854F, -0.7854F, 0.0F);
		total.addChild(spike12);
		spike12.cubeList.add(new ModelBox(spike12, 75, 81, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		spike13 = new ModelRenderer(this);
		spike13.setRotationPoint(6.0F, -1.0F, 5.0F);
		setRotationAngle(spike13, 0.7854F, 0.7854F, 0.0F);
		total.addChild(spike13);
		spike13.cubeList.add(new ModelBox(spike13, 118, 74, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		spike14 = new ModelRenderer(this);
		spike14.setRotationPoint(-5.0F, -1.0F, 0.0F);
		setRotationAngle(spike14, 0.7854F, 0.0F, 0.0F);
		total.addChild(spike14);
		spike14.cubeList.add(new ModelBox(spike14, 105, 72, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		spike15 = new ModelRenderer(this);
		spike15.setRotationPoint(5.0F, -1.0F, 0.0F);
		setRotationAngle(spike15, 0.7854F, 0.0F, 0.0F);
		total.addChild(spike15);
		spike15.cubeList.add(new ModelBox(spike15, 90, 72, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));
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
package com.hungteen.pvzmod.model.entities.plants.light;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelTwinSunFlower extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer YEZI_4;
	private final ModelRenderer YEZI_3;
	private final ModelRenderer YEZI_2;
	private final ModelRenderer YEZI_1;
	private final ModelRenderer left;
	private final ModelRenderer body;
	private final ModelRenderer bone;
	private final ModelRenderer TUO;
	private final ModelRenderer HUABAN;
	private final ModelRenderer la;
	private final ModelRenderer ra;
	private final ModelRenderer right;
	private final ModelRenderer body2;
	private final ModelRenderer bone2;
	private final ModelRenderer TUO2;
	private final ModelRenderer HUABAN2;
	private final ModelRenderer la2;
	private final ModelRenderer ra2;

	public ModelTwinSunFlower() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);

		YEZI_4 = new ModelRenderer(this);
		YEZI_4.setRotationPoint(-2.0F, -1.0F, 0.0F);
		total.addChild(YEZI_4);
		YEZI_4.cubeList.add(new ModelBox(YEZI_4, 95, 1, -11.0F, -1.0F, -4.0F, 8, 2, 8, 0.0F, false));
		YEZI_4.cubeList.add(new ModelBox(YEZI_4, 69, 1, -3.0F, 0.0F, -2.0F, 3, 1, 4, 0.0F, false));

		YEZI_3 = new ModelRenderer(this);
		YEZI_3.setRotationPoint(0.0F, 0.0F, -2.0F);
		total.addChild(YEZI_3);
		YEZI_3.cubeList.add(new ModelBox(YEZI_3, 68, 9, -2.0F, -1.0F, -3.0F, 4, 1, 3, 0.0F, false));
		YEZI_3.cubeList.add(new ModelBox(YEZI_3, 95, 13, -4.0F, -2.0F, -11.0F, 8, 2, 8, 0.0F, false));

		YEZI_2 = new ModelRenderer(this);
		YEZI_2.setRotationPoint(0.0F, 0.0F, 2.0F);
		total.addChild(YEZI_2);
		YEZI_2.cubeList.add(new ModelBox(YEZI_2, 95, 24, -4.0F, -2.0F, 3.0F, 8, 2, 8, 0.0F, false));
		YEZI_2.cubeList.add(new ModelBox(YEZI_2, 68, 16, -2.0F, -1.0F, 0.0F, 4, 1, 3, 0.0F, false));

		YEZI_1 = new ModelRenderer(this);
		YEZI_1.setRotationPoint(2.0F, 0.0F, 0.0F);
		total.addChild(YEZI_1);
		YEZI_1.cubeList.add(new ModelBox(YEZI_1, 69, 24, 0.0F, -1.0F, -2.0F, 3, 1, 4, 0.0F, false));
		YEZI_1.cubeList.add(new ModelBox(YEZI_1, 94, 36, 3.0F, -2.0F, -4.0F, 8, 2, 8, 0.0F, false));

		left = new ModelRenderer(this);
		left.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(left, 0.0F, 0.0F, 0.6981F);
		total.addChild(left);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -1.0F, 0.0F);
		left.addChild(body);
		body.cubeList.add(new ModelBox(body, 111, 73, -2.0F, -20.0F, -2.0F, 4, 21, 4, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -20.0F, 0.0F);
		setRotationAngle(bone, 0.0F, 0.0F, -0.5236F);
		body.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 93, 71, -2.0F, -22.0F, -2.0F, 4, 23, 4, 0.0F, false));

		TUO = new ModelRenderer(this);
		TUO.setRotationPoint(-0.9848F, -23.8264F, 0.0F);
		setRotationAngle(TUO, 0.0F, 0.0F, -0.1745F);
		bone.addChild(TUO);
		TUO.cubeList.add(new ModelBox(TUO, 83, 105, -9.0F, -8.0F, -6.0F, 18, 18, 4, 0.0F, false));
		TUO.cubeList.add(new ModelBox(TUO, 54, 2, 9.0F, -6.0F, -6.0F, 2, 14, 4, 0.0F, false));
		TUO.cubeList.add(new ModelBox(TUO, 40, 1, -11.0F, -6.0F, -6.0F, 2, 14, 4, 0.0F, false));
		TUO.cubeList.add(new ModelBox(TUO, 28, 23, -7.0F, -10.0F, -6.0F, 14, 2, 4, 0.0F, false));
		TUO.cubeList.add(new ModelBox(TUO, 34, 31, -7.0F, 10.0F, -6.0F, 14, 2, 4, 0.0F, false));

		HUABAN = new ModelRenderer(this);
		HUABAN.setRotationPoint(0.0F, -3.0F, 0.0F);
		TUO.addChild(HUABAN);
		HUABAN.cubeList.add(new ModelBox(HUABAN, 58, 40, -4.0F, -9.0F, -6.0F, 8, 2, 4, 0.0F, false));
		HUABAN.cubeList.add(new ModelBox(HUABAN, 59, 49, -4.0F, 15.0F, -6.0F, 8, 2, 4, 0.0F, false));
		HUABAN.cubeList.add(new ModelBox(HUABAN, 68, 57, 11.0F, 0.0F, -6.0F, 2, 8, 4, 0.0F, false));
		HUABAN.cubeList.add(new ModelBox(HUABAN, 76, 73, -13.0F, 0.0F, -6.0F, 2, 8, 4, 0.0F, false));

		la = new ModelRenderer(this);
		la.setRotationPoint(2.0F, 0.0F, 0.0F);
		setRotationAngle(la, 0.0F, 0.0F, -0.2618F);
		bone.addChild(la);
		la.cubeList.add(new ModelBox(la, 74, 88, -1.0F, -3.0F, 0.0F, 7, 3, 1, 0.0F, false));

		ra = new ModelRenderer(this);
		ra.setRotationPoint(-1.0F, 0.0F, 0.0F);
		setRotationAngle(ra, 0.0F, 0.0F, 0.2618F);
		bone.addChild(ra);
		ra.cubeList.add(new ModelBox(ra, 75, 95, -7.0F, -3.0F, 0.0F, 7, 3, 1, 0.0F, false));

		right = new ModelRenderer(this);
		right.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(right, 0.0F, 0.0F, -0.6981F);
		total.addChild(right);

		body2 = new ModelRenderer(this);
		body2.setRotationPoint(0.0F, -1.0F, 0.0F);
		right.addChild(body2);
		body2.cubeList.add(new ModelBox(body2, 21, 2, -2.0F, -13.0F, -2.0F, 4, 14, 4, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-0.0265F, -12.1898F, 0.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.6109F);
		body2.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 54, 102, -2.0F, -19.0F, -2.0F, 4, 20, 4, 0.0F, false));

		TUO2 = new ModelRenderer(this);
		TUO2.setRotationPoint(0.0F, -20.0F, 0.0F);
		setRotationAngle(TUO2, 0.0F, 0.0F, 0.0873F);
		bone2.addChild(TUO2);
		TUO2.cubeList.add(new ModelBox(TUO2, 7, 104, -9.0F, -8.0F, -6.0F, 18, 18, 4, 0.0F, false));
		TUO2.cubeList.add(new ModelBox(TUO2, 87, 51, 9.0F, -6.0F, -6.0F, 2, 14, 4, 0.0F, false));
		TUO2.cubeList.add(new ModelBox(TUO2, 57, 78, -11.0F, -6.0F, -6.0F, 2, 14, 4, 0.0F, false));
		TUO2.cubeList.add(new ModelBox(TUO2, 15, 94, -7.0F, -10.0F, -6.0F, 14, 2, 4, 0.0F, false));
		TUO2.cubeList.add(new ModelBox(TUO2, 2, 53, -7.0F, 10.0F, -6.0F, 14, 2, 4, 0.0F, false));

		HUABAN2 = new ModelRenderer(this);
		HUABAN2.setRotationPoint(0.0F, -3.0F, 0.0F);
		TUO2.addChild(HUABAN2);
		HUABAN2.cubeList.add(new ModelBox(HUABAN2, 1, 24, -4.0F, -9.0F, -6.0F, 8, 2, 4, 0.0F, false));
		HUABAN2.cubeList.add(new ModelBox(HUABAN2, 43, 70, -4.0F, 15.0F, -6.0F, 8, 2, 4, 0.0F, false));
		HUABAN2.cubeList.add(new ModelBox(HUABAN2, 41, 49, 11.0F, 0.0F, -6.0F, 2, 8, 4, 0.0F, false));
		HUABAN2.cubeList.add(new ModelBox(HUABAN2, 27, 77, -13.0F, 0.0F, -6.0F, 2, 8, 4, 0.0F, false));

		la2 = new ModelRenderer(this);
		la2.setRotationPoint(2.0F, 0.0F, 0.0F);
		setRotationAngle(la2, 0.0F, 0.0F, -0.2618F);
		bone2.addChild(la2);
		la2.cubeList.add(new ModelBox(la2, 2, 10, -1.0F, -3.0F, 0.0F, 7, 3, 1, 0.0F, false));

		ra2 = new ModelRenderer(this);
		ra2.setRotationPoint(-1.0F, 0.0F, 0.0F);
		setRotationAngle(ra2, 0.0F, 0.0F, 0.2618F);
		bone2.addChild(ra2);
		ra2.cubeList.add(new ModelBox(ra2, 5, 85, -7.0F, -3.0F, 0.0F, 7, 3, 1, 0.0F, false));
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
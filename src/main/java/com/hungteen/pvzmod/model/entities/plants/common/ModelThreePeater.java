package com.hungteen.pvzmod.model.entities.plants.common;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelThreePeater extends ModelBase {
	private final ModelRenderer YEZI_1;
	private final ModelRenderer YEZI_2;
	private final ModelRenderer YEZI_3;
	private final ModelRenderer YEZI_4;
	private final ModelRenderer branch1;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer branch2;
	private final ModelRenderer body2;
	private final ModelRenderer head2;
	private final ModelRenderer branch3;
	private final ModelRenderer body3;
	private final ModelRenderer head3;

	public ModelThreePeater() {
		textureWidth = 256;
		textureHeight = 256;

		YEZI_1 = new ModelRenderer(this);
		YEZI_1.setRotationPoint(2.0F, 24.0F, 0.0F);
		YEZI_1.cubeList.add(new ModelBox(YEZI_1, 2, 2, 0.0F, -1.0F, -2.0F, 3, 1, 4, 0.0F, false));
		YEZI_1.cubeList.add(new ModelBox(YEZI_1, 3, 10, 3.0F, -2.0F, -4.0F, 8, 2, 8, 0.0F, false));

		YEZI_2 = new ModelRenderer(this);
		YEZI_2.setRotationPoint(0.0F, 24.0F, 2.0F);
		YEZI_2.cubeList.add(new ModelBox(YEZI_2, 4, 27, -4.0F, -2.0F, 3.0F, 8, 2, 8, 0.0F, false));
		YEZI_2.cubeList.add(new ModelBox(YEZI_2, 4, 44, -2.0F, -1.0F, 0.0F, 4, 1, 3, 0.0F, false));

		YEZI_3 = new ModelRenderer(this);
		YEZI_3.setRotationPoint(0.0F, 24.0F, -2.0F);
		YEZI_3.cubeList.add(new ModelBox(YEZI_3, 3, 55, -2.0F, -1.0F, -3.0F, 4, 1, 3, 0.0F, false));
		YEZI_3.cubeList.add(new ModelBox(YEZI_3, 2, 64, -4.0F, -2.0F, -11.0F, 8, 2, 8, 0.0F, false));

		YEZI_4 = new ModelRenderer(this);
		YEZI_4.setRotationPoint(-2.0F, 23.0F, 0.0F);
		YEZI_4.cubeList.add(new ModelBox(YEZI_4, 4, 80, -11.0F, -1.0F, -4.0F, 8, 2, 8, 0.0F, false));
		YEZI_4.cubeList.add(new ModelBox(YEZI_4, 6, 96, -3.0F, 0.0F, -2.0F, 3, 1, 4, 0.0F, false));

		branch1 = new ModelRenderer(this);
		branch1.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(branch1, 0.0F, 0.0F, -0.6981F);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -31.0F, 0.0F);
		branch1.addChild(body);
		body.cubeList.add(new ModelBox(body, 4, 107, -2.0F, -3.0F, -2.0F, 4, 32, 4, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -1.0F, 0.0F);
		setRotationAngle(head, 0.0F, 0.0F, 0.6109F);
		body.addChild(head);
		head.cubeList.add(new ModelBox(head, 4, 148, -5.0F, -13.0F, 7.0F, 10, 10, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 35, 5, -7.0F, -15.0F, -7.0F, 14, 14, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 100, 7, 7.0F, -13.0F, -5.0F, 1, 10, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 132, 9, -8.0F, -13.0F, -5.0F, 1, 10, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 155, 7, -1.0F, -12.0F, 8.0F, 2, 1, 4, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 169, 4, -5.0F, -16.0F, -5.0F, 10, 1, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 160, 20, -2.0F, -13.0F, 12.0F, 4, 5, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 208, 4, -4.0F, -12.0F, -21.0F, 8, 8, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 180, 21, -3.0F, -1.0F, -3.0F, 6, 1, 6, 0.0F, false));

		branch2 = new ModelRenderer(this);
		branch2.setRotationPoint(0.0F, 23.0F, 0.0F);

		body2 = new ModelRenderer(this);
		body2.setRotationPoint(0.0F, -30.0F, 0.0F);
		branch2.addChild(body2);
		body2.cubeList.add(new ModelBox(body2, 237, 35, -2.0F, -1.0F, -2.0F, 4, 32, 4, 0.0F, false));
		body2.cubeList.add(new ModelBox(body2, 208, 32, -3.0F, -2.0F, -3.0F, 6, 1, 6, 0.0F, false));

		head2 = new ModelRenderer(this);
		head2.setRotationPoint(0.0F, -4.0F, 0.0F);
		body2.addChild(head2);
		head2.cubeList.add(new ModelBox(head2, 208, 44, -5.0F, -10.0F, 7.0F, 10, 10, 1, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 174, 63, -7.0F, -12.0F, -7.0F, 14, 14, 14, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 173, 42, 7.0F, -10.0F, -5.0F, 1, 10, 10, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 142, 36, -8.0F, -10.0F, -5.0F, 1, 10, 10, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 119, 39, -4.0F, -9.0F, 8.0F, 2, 1, 4, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 123, 61, -5.0F, -13.0F, -5.0F, 10, 1, 10, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 148, 80, 1.0F, -10.0F, 12.0F, 4, 5, 2, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 56, 41, -4.0F, -9.0F, -21.0F, 8, 8, 14, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 35, 43, -5.0F, -10.0F, 12.0F, 4, 5, 2, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 64, 69, 2.0F, -9.0F, 8.0F, 2, 1, 4, 0.0F, false));

		branch3 = new ModelRenderer(this);
		branch3.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(branch3, 0.0F, 0.0F, 0.6981F);

		body3 = new ModelRenderer(this);
		body3.setRotationPoint(0.0F, -31.0F, 0.0F);
		branch3.addChild(body3);
		body3.cubeList.add(new ModelBox(body3, 43, 85, -2.0F, -3.0F, -2.0F, 4, 32, 4, 0.0F, false));

		head3 = new ModelRenderer(this);
		head3.setRotationPoint(0.0F, -1.0F, 0.0F);
		setRotationAngle(head3, 0.0F, 0.0F, -0.6109F);
		body3.addChild(head3);
		head3.cubeList.add(new ModelBox(head3, 71, 90, -5.0F, -13.0F, 7.0F, 10, 10, 1, 0.0F, false));
		head3.cubeList.add(new ModelBox(head3, 107, 96, -7.0F, -15.0F, -7.0F, 14, 14, 14, 0.0F, false));
		head3.cubeList.add(new ModelBox(head3, 176, 100, 7.0F, -13.0F, -5.0F, 1, 10, 10, 0.0F, false));
		head3.cubeList.add(new ModelBox(head3, 205, 101, -8.0F, -13.0F, -5.0F, 1, 10, 10, 0.0F, false));
		head3.cubeList.add(new ModelBox(head3, 236, 101, -4.0F, -12.0F, 8.0F, 2, 1, 4, 0.0F, false));
		head3.cubeList.add(new ModelBox(head3, 47, 128, -5.0F, -16.0F, -5.0F, 10, 1, 10, 0.0F, false));
		head3.cubeList.add(new ModelBox(head3, 92, 130, 1.0F, -14.0F, 12.0F, 4, 5, 2, 0.0F, false));
		head3.cubeList.add(new ModelBox(head3, 116, 132, -4.0F, -12.0F, -21.0F, 8, 8, 14, 0.0F, false));
		head3.cubeList.add(new ModelBox(head3, 171, 132, -5.0F, -14.0F, 12.0F, 4, 5, 2, 0.0F, false));
		head3.cubeList.add(new ModelBox(head3, 206, 134, 2.0F, -12.0F, 8.0F, 2, 1, 4, 0.0F, false));
		head3.cubeList.add(new ModelBox(head3, 195, 148, -1.0F, -6.0F, 8.0F, 2, 1, 4, 0.0F, false));
		head3.cubeList.add(new ModelBox(head3, 237, 145, -2.0F, -7.0F, 12.0F, 4, 5, 2, 0.0F, false));
		head3.cubeList.add(new ModelBox(head3, 48, 157, -3.0F, -1.0F, -3.0F, 6, 1, 6, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		YEZI_1.render(f5);
		YEZI_2.render(f5);
		YEZI_3.render(f5);
		YEZI_4.render(f5);
		branch1.render(f5);
		branch2.render(f5);
		branch3.render(f5);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
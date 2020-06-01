package com.hungteen.pvzmod.model.entities.plants.fight;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelTangleKelp extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer hand;
	private final ModelRenderer hand2;
	private final ModelRenderer tangle1;
	private final ModelRenderer bone;
	private final ModelRenderer tangle2;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer tangle3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer tangle4;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer tangle5;
	private final ModelRenderer tangle6;
	private final ModelRenderer tangle7;

	public ModelTangleKelp() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		body.cubeList.add(new ModelBox(body, 71, 107, -8.0F, -8.0F, -6.0F, 16, 8, 12, 0.0F, false));

		hand = new ModelRenderer(this);
		hand.setRotationPoint(-8.0F, -2.0F, 0.0F);
		setRotationAngle(hand, 0.0F, 0.0F, 0.9599F);
		total.addChild(hand);
		hand.cubeList.add(new ModelBox(hand, 100, 101, -11.0F, -2.0F, -1.0F, 11, 2, 2, 0.0F, false));

		hand2 = new ModelRenderer(this);
		hand2.setRotationPoint(8.0F, -2.0F, 0.0F);
		setRotationAngle(hand2, 0.0F, 0.0F, -0.9599F);
		total.addChild(hand2);
		hand2.cubeList.add(new ModelBox(hand2, 101, 94, 0.0F, -2.0F, -1.0F, 11, 2, 2, 0.0F, false));

		tangle1 = new ModelRenderer(this);
		tangle1.setRotationPoint(-6.0F, -8.0F, -4.0F);
		setRotationAngle(tangle1, -0.2618F, 0.0F, 0.4363F);
		total.addChild(tangle1);
		tangle1.cubeList.add(new ModelBox(tangle1, 118, 80, -1.0F, -9.0F, -1.0F, 2, 10, 2, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.3054F, -8.1762F, 0.0F);
		setRotationAngle(bone, 0.0F, 0.0F, -0.4363F);
		tangle1.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 106, 79, -1.0F, -11.0F, -1.0F, 2, 11, 2, 0.0F, false));

		tangle2 = new ModelRenderer(this);
		tangle2.setRotationPoint(5.0F, -8.0F, -3.0F);
		setRotationAngle(tangle2, -0.1745F, 0.0F, -0.3491F);
		total.addChild(tangle2);
		tangle2.cubeList.add(new ModelBox(tangle2, 118, 66, -1.0F, -8.0F, -1.0F, 2, 9, 2, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.342F, -7.9397F, 0.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.9599F);
		tangle2.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 107, 68, -1.0F, -5.0F, -1.0F, 2, 6, 2, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-0.8192F, -4.4264F, 0.0F);
		setRotationAngle(bone3, 0.0F, 0.0F, -1.2217F);
		bone2.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 69, 105, -1.0F, -4.0F, -1.0F, 2, 6, 2, 0.0F, false));

		tangle3 = new ModelRenderer(this);
		tangle3.setRotationPoint(-1.0F, -8.0F, 3.0F);
		setRotationAngle(tangle3, 0.1745F, 0.0F, -0.3491F);
		total.addChild(tangle3);
		tangle3.cubeList.add(new ModelBox(tangle3, 86, 92, -0.0603F, -5.6632F, -1.0594F, 2, 7, 2, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, -7.0143F, -0.1632F);
		setRotationAngle(bone4, 0.0F, 0.0F, -0.2618F);
		tangle3.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 70, 91, -0.1795F, -4.4314F, -1.0594F, 2, 6, 2, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.3656F, -6.2049F, -0.6585F);
		setRotationAngle(bone5, 0.6109F, 0.0F, 0.7854F);
		bone4.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 90, 78, 0.9644F, -4.3581F, -1.0059F, 2, 6, 2, 0.0F, false));

		tangle4 = new ModelRenderer(this);
		tangle4.setRotationPoint(5.0F, -8.0F, 1.0F);
		setRotationAngle(tangle4, -0.5236F, 0.0F, -0.3491F);
		total.addChild(tangle4);
		tangle4.cubeList.add(new ModelBox(tangle4, 75, 74, -1.0F, -9.0F, -1.0F, 2, 10, 2, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.342F, -7.9397F, 0.0F);
		setRotationAngle(bone6, 0.0F, 0.0F, -0.6109F);
		tangle4.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 91, 65, -1.0F, -5.0F, -1.0F, 2, 5, 2, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(-1.419F, -5.208F, -0.171F);
		setRotationAngle(bone7, 0.0F, 0.0F, -0.8727F);
		bone6.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 75, 60, -1.0F, -4.0F, -1.0F, 2, 6, 2, 0.0F, false));

		tangle5 = new ModelRenderer(this);
		tangle5.setRotationPoint(0.0F, -8.0F, -3.0F);
		setRotationAngle(tangle5, -0.4363F, 0.0F, 0.0F);
		total.addChild(tangle5);
		tangle5.cubeList.add(new ModelBox(tangle5, 61, 69, -1.0F, -4.0F, -1.0F, 2, 5, 2, 0.0F, false));

		tangle6 = new ModelRenderer(this);
		tangle6.setRotationPoint(-6.0F, -8.0F, 3.0F);
		total.addChild(tangle6);
		tangle6.cubeList.add(new ModelBox(tangle6, 56, 82, -1.0F, -11.0F, -1.0F, 2, 12, 2, 0.0F, false));

		tangle7 = new ModelRenderer(this);
		tangle7.setRotationPoint(7.0F, -9.0F, 4.0F);
		setRotationAngle(tangle7, 0.4363F, 0.0F, 0.0F);
		total.addChild(tangle7);
		tangle7.cubeList.add(new ModelBox(tangle7, 52, 105, -1.0F, -8.0F, -1.0F, 2, 10, 2, 0.0F, false));
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
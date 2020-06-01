package com.hungteen.pvzmod.model.entities.plants.light;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelGoldLeaf extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer left;
	private final ModelRenderer right;
	private final ModelRenderer hair;
	private final ModelRenderer bone;

	public ModelGoldLeaf() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		total.cubeList.add(new ModelBox(total, 99, 112, -6.0F, -12.0F, -1.0F, 12, 12, 2, 0.0F, false));

		left = new ModelRenderer(this);
		left.setRotationPoint(6.0F, 0.0F, 0.0F);
		setRotationAngle(left, 0.0F, 0.0F, -0.7854F);
		total.addChild(left);
		left.cubeList.add(new ModelBox(left, 108, 103, -0.5858F, -2.0F, -1.0F, 7, 2, 2, 0.0F, false));

		right = new ModelRenderer(this);
		right.setRotationPoint(-6.0F, -1.0F, 0.0F);
		setRotationAngle(right, 0.0F, 0.0F, 0.7854F);
		total.addChild(right);
		right.cubeList.add(new ModelBox(right, 109, 94, -5.7071F, -1.2929F, -1.0F, 7, 2, 2, 0.0F, false));

		hair = new ModelRenderer(this);
		hair.setRotationPoint(-1.0F, -11.0F, 0.0F);
		setRotationAngle(hair, 0.0F, 0.0F, 0.4363F);
		total.addChild(hair);
		hair.cubeList.add(new ModelBox(hair, 87, 119, -1.0F, -5.0F, -1.0F, 2, 5, 2, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -5.0F, 0.0F);
		setRotationAngle(bone, 0.0F, 0.0F, 1.2217F);
		hair.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 87, 104, -1.0F, -3.0F, -1.0F, 2, 4, 2, 0.0F, false));
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
	
	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) {
		
	}
}
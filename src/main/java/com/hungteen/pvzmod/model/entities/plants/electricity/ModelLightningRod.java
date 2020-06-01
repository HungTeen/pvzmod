package com.hungteen.pvzmod.model.entities.plants.electricity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelLightningRod extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer leaf;
	private final ModelRenderer leaf2;
	private final ModelRenderer leaf3;
	private final ModelRenderer leaf4;
	private final ModelRenderer body;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;

	public ModelLightningRod() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);

		leaf = new ModelRenderer(this);
		leaf.setRotationPoint(-1.0F, 0.0F, 1.0F);
		setRotationAngle(leaf, 0.1745F, 0.0873F, 0.1745F);
		total.addChild(leaf);
		leaf.cubeList.add(new ModelBox(leaf, 96, 119, -8.0F, -1.0F, 0.0F, 8, 1, 8, 0.0F, false));

		leaf2 = new ModelRenderer(this);
		leaf2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(leaf2, -0.1745F, -0.0873F, 0.1745F);
		total.addChild(leaf2);
		leaf2.cubeList.add(new ModelBox(leaf2, 96, 107, -9.1435F, -0.6877F, -8.7713F, 8, 1, 8, 0.0F, false));

		leaf3 = new ModelRenderer(this);
		leaf3.setRotationPoint(1.0F, 0.0F, 1.0F);
		setRotationAngle(leaf3, 0.1745F, 0.0F, -0.1745F);
		total.addChild(leaf3);
		leaf3.cubeList.add(new ModelBox(leaf3, 96, 96, 0.0F, -1.0F, 0.0F, 8, 1, 8, 0.0F, false));

		leaf4 = new ModelRenderer(this);
		leaf4.setRotationPoint(1.0F, 0.0F, 0.0F);
		setRotationAngle(leaf4, -0.1745F, 0.0F, -0.1745F);
		total.addChild(leaf4);
		leaf4.cubeList.add(new ModelBox(leaf4, 96, 84, 0.0F, -1.0F, -9.0F, 8, 1, 8, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		body.cubeList.add(new ModelBox(body, 68, 106, -3.0F, -16.0F, -3.0F, 6, 16, 6, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 120, 56, -1.0F, -40.0F, -1.0F, 2, 24, 2, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -23.0F, 0.0F);
		setRotationAngle(bone, 0.0F, -1.5708F, 0.0F);
		body.addChild(bone);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(1.0F, 0.0F, -10.0F);
		setRotationAngle(bone2, 0.0F, 0.7854F, 0.0F);
		bone.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 79, 96, -5.7071F, -1.0F, -1.7071F, 5, 2, 1, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 78, 84, -1.0F, -1.0F, -1.0F, 1, 2, 5, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, -29.0F, 0.0F);
		setRotationAngle(bone3, 0.0F, 1.5708F, 0.0F);
		body.addChild(bone3);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(1.0F, 0.0F, -10.0F);
		setRotationAngle(bone4, 0.0F, 0.7854F, 0.0F);
		bone3.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 100, 75, -5.7071F, -1.0F, -1.7071F, 5, 2, 1, 0.0F, false));
		bone4.cubeList.add(new ModelBox(bone4, 82, 72, -1.0F, -1.0F, -1.0F, 1, 2, 5, 0.0F, false));
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
		this.bone.rotateAngleY=ageInTicks/100;
		this.bone3.rotateAngleY=ageInTicks/100+3.14f;
	}
}
package com.hungteen.pvzmod.model.entities.plants.explosion;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


public class ModelCherryBomb extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer bone;
	private final ModelRenderer bone3;
	private final ModelRenderer bone2;
	private final ModelRenderer head1;
	private final ModelRenderer head2;

	public ModelCherryBomb() {
		textureWidth = 256;
		textureHeight = 256;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-9.0F, -18.0F, 0.0F);
		body.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, 0.7854F);
		bone.cubeList.add(new ModelBox(bone, 52, 105, -2.0F, -17.0F, -1.0F, 2, 20, 2, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, -16.0F, 0.0F);
		bone.addChild(bone3);
		setRotationAngle(bone3, 0.0F, -0.3491F, 0.6981F);
		bone3.cubeList.add(new ModelBox(bone3, 36, 113, -1.0F, -8.0F, -3.0F, 1, 8, 6, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(9.0F, -18.0F, 0.0F);
		body.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, -0.7854F);
		bone2.cubeList.add(new ModelBox(bone2, 23, 108, 0.0F, -13.0F, -1.0F, 2, 16, 2, 0.0F, false));

		head1 = new ModelRenderer(this);
		head1.setRotationPoint(-11.0F, -18.0F, 0.0F);
		body.addChild(head1);
		setRotationAngle(head1, 0.0F, 0.2618F, 0.0F);
		head1.cubeList.add(new ModelBox(head1, 63, 61, -8.0F, 1.0F, -8.0F, 16, 16, 16, 0.0F, false));
		head1.cubeList.add(new ModelBox(head1, 97, 30, 8.0F, 2.0F, -7.0F, 1, 14, 14, 0.0F, false));
		head1.cubeList.add(new ModelBox(head1, 64, 31, -9.0F, 2.0F, -7.0F, 1, 14, 14, 0.0F, false));
		head1.cubeList.add(new ModelBox(head1, 97, 14, -7.0F, 2.0F, -9.0F, 14, 14, 1, 0.0F, false));
		head1.cubeList.add(new ModelBox(head1, 64, 13, -7.0F, 2.0F, 8.0F, 14, 14, 1, 0.0F, false));
		head1.cubeList.add(new ModelBox(head1, 0, 85, -7.0F, 0.0F, -7.0F, 14, 1, 14, 0.0F, false));
		head1.cubeList.add(new ModelBox(head1, 1, 68, -7.0F, 17.0F, -7.0F, 14, 1, 14, 0.0F, false));

		head2 = new ModelRenderer(this);
		head2.setRotationPoint(11.0F, -18.0F, 0.0F);
		body.addChild(head2);
		setRotationAngle(head2, 0.0F, -0.2618F, 0.0F);
		head2.cubeList.add(new ModelBox(head2, 63, 95, -8.0F, 1.0F, -8.0F, 16, 16, 16, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 29, 37, 8.0F, 2.0F, -7.0F, 1, 14, 14, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 3, 7, -9.0F, 2.0F, -7.0F, 1, 14, 14, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 4, 140, -7.0F, 2.0F, -9.0F, 14, 14, 1, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 44, 139, -7.0F, 2.0F, 8.0F, 14, 14, 1, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 84, 138, -7.0F, 0.0F, -7.0F, 14, 1, 14, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 8, 163, -7.0F, 17.0F, -7.0F, 14, 1, 14, 0.0F, false));
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
package com.hungteen.pvzmod.model.entities.zombies.special;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelDolphin extends ModelBase {
	private final ModelRenderer dolphin;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;

	public ModelDolphin() {
		textureWidth = 128;
		textureHeight = 128;

		dolphin = new ModelRenderer(this);
		dolphin.setRotationPoint(0.0F, 24.0F, 0.0F);
		dolphin.cubeList.add(new ModelBox(dolphin, 44, 88, -7.0F, -12.0F, -8.0F, 14, 12, 28, 0.0F, false));
		dolphin.cubeList.add(new ModelBox(dolphin, 4, 112, -3.0F, -5.0F, -20.0F, 6, 4, 12, 0.0F, false));
		dolphin.cubeList.add(new ModelBox(dolphin, 96, 42, -4.0F, -6.0F, 20.0F, 8, 6, 8, 0.0F, false));
		dolphin.cubeList.add(new ModelBox(dolphin, 108, 33, -3.0F, -5.0F, 28.0F, 6, 4, 4, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone, 0.0F, -0.2618F, -0.1745F);
		dolphin.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 72, 73, 8.0F, -1.0F, 0.0F, 16, 1, 12, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone2, 0.0F, 0.2618F, 0.1745F);
		dolphin.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 72, 58, -24.0F, -1.0F, 0.0F, 17, 1, 12, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, -4.0F, 32.0F);
		setRotationAngle(bone3, 0.4363F, 0.0F, 0.0F);
		dolphin.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 96, 26, -6.0F, -1.0F, -1.0F, 12, 1, 4, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, -12.0F, 10.0F);
		setRotationAngle(bone4, -0.5236F, 0.0F, 0.0F);
		dolphin.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 55, 70, -1.0F, -7.0F, 0.0F, 2, 7, 4, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		dolphin.render(f5);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
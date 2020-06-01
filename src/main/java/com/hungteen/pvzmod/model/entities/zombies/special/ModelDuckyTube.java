package com.hungteen.pvzmod.model.entities.zombies.special;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelDuckyTube extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer bone;

	public ModelDuckyTube() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		total.cubeList.add(new ModelBox(total, 80, 103, -11.0F, -5.0F, -10.0F, 4, 5, 20, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 90, 90, -7.0F, -5.0F, 5.0F, 14, 5, 5, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 88, 78, -7.0F, -5.0F, -10.0F, 14, 5, 6, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 80, 51, 7.0F, -5.0F, -10.0F, 4, 5, 20, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 110, 37, -3.0F, -7.0F, -12.0F, 6, 7, 3, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 102, 22, -3.0F, -14.0F, -15.0F, 6, 7, 7, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 110, 14, -3.0F, -9.0F, -18.0F, 6, 1, 3, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 62, 123, -2.0F, -14.0F, -8.0F, 4, 1, 1, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -5.0F, 10.0F);
		setRotationAngle(bone, 0.5236F, 0.0F, 0.0F);
		total.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 68, 107, -2.0F, 0.0F, -2.0F, 4, 1, 5, 0.0F, false));
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
		this.bone.rotateAngleX=MathHelper.sin(ageInTicks/20)/3;
	}
}
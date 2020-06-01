package com.hungteen.pvzmod.model.entities.special;

import com.hungteen.pvzmod.entities.special.EntityGardenRake;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelGardenRake extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;

	public ModelGardenRake() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, -3.0F);
		total.cubeList.add(new ModelBox(total, 75, 124, -12.0F, -1.0F, 1.0F, 24, 1, 2, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 0, 0, -12.0F, -4.0F, 0.0F, 1, 3, 1, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 0, 0, -10.0F, -4.0F, 0.0F, 1, 3, 1, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 0, 0, -8.0F, -4.0F, 0.0F, 1, 3, 1, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 0, 0, -6.0F, -4.0F, 0.0F, 1, 3, 1, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 0, 0, -4.0F, -4.0F, 0.0F, 1, 3, 1, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 0, 0, -2.0F, -4.0F, 0.0F, 1, 3, 1, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 0, 0, 11.0F, -4.0F, 0.0F, 1, 3, 1, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 0, 0, 9.0F, -4.0F, 0.0F, 1, 3, 1, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 0, 0, 7.0F, -4.0F, 0.0F, 1, 3, 1, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 0, 0, 5.0F, -4.0F, 0.0F, 1, 3, 1, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 0, 0, 3.0F, -4.0F, 0.0F, 1, 3, 1, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 0, 0, 1.0F, -4.0F, 0.0F, 1, 3, 1, 0.0F, false));
		total.cubeList.add(new ModelBox(total, 23, 5, -1.0F, -1.0F, 3.0F, 2, 1, 48, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(1.0F, 0.0F, 12.0F);
		setRotationAngle(bone, 0.0F, 0.7854F, 0.0F);
		total.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 40, 117, -1.0F, -1.0F, 0.0F, 15, 1, 1, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-1.0F, 0.0F, 12.0F);
		setRotationAngle(bone2, 0.0F, -0.7854F, 0.0F);
		total.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 28, 123, -14.0F, -1.0F, 0.0F, 15, 1, 1, 0.0F, false));
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
		if(entityIn instanceof EntityGardenRake) {
			int time=((EntityGardenRake) entityIn).getAttackTime();
			total.rotateAngleX=2f*time/10;
		}
	}
}
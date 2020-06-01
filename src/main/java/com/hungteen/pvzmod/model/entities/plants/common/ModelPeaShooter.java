package com.hungteen.pvzmod.model.entities.plants.common;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


public class ModelPeaShooter extends ModelBase {
	private final ModelRenderer YEZI_1;
	private final ModelRenderer YEZI_2;
	private final ModelRenderer YEZI_3;
	private final ModelRenderer YEZI_4;
	private final ModelRenderer head;
	private final ModelRenderer body;

	public ModelPeaShooter() {
		textureWidth = 128;
		textureHeight = 128;

		YEZI_1 = new ModelRenderer(this);
		YEZI_1.setRotationPoint(2.0F, 24.0F, 0.0F);
		YEZI_1.cubeList.add(new ModelBox(YEZI_1, 0, 65, 0.0F, -1.0F, -2.0F, 3, 1, 4, 0.0F, false));
		YEZI_1.cubeList.add(new ModelBox(YEZI_1, 0, 50, 3.0F, -2.0F, -4.0F, 8, 2, 8, 0.0F, false));

		YEZI_2 = new ModelRenderer(this);
		YEZI_2.setRotationPoint(0.0F, 24.0F, 2.0F);
		YEZI_2.cubeList.add(new ModelBox(YEZI_2, 0, 74, -4.0F, -2.0F, 3.0F, 8, 2, 8, 0.0F, false));
		YEZI_2.cubeList.add(new ModelBox(YEZI_2, 114, 118, -2.0F, -1.0F, 0.0F, 4, 1, 3, 0.0F, false));

		YEZI_3 = new ModelRenderer(this);
		YEZI_3.setRotationPoint(0.0F, 24.0F, -2.0F);
		YEZI_3.cubeList.add(new ModelBox(YEZI_3, 95, 124, -2.0F, -1.0F, -3.0F, 4, 1, 3, 0.0F, false));
		YEZI_3.cubeList.add(new ModelBox(YEZI_3, 0, 0, -4.0F, -2.0F, -11.0F, 8, 2, 8, 0.0F, false));

		YEZI_4 = new ModelRenderer(this);
		YEZI_4.setRotationPoint(-2.0F, 23.0F, 0.0F);
		YEZI_4.cubeList.add(new ModelBox(YEZI_4, 0, 88, -11.0F, -1.0F, -4.0F, 8, 2, 8, 0.0F, false));
		YEZI_4.cubeList.add(new ModelBox(YEZI_4, 49, 104, -3.0F, 0.0F, -2.0F, 3, 1, 4, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -11.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 66, 117, -5.0F, -10.0F, 7.0F, 10, 10, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 25, 23, -7.0F, -12.0F, -7.0F, 14, 14, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 106, 80, 7.0F, -10.0F, -5.0F, 1, 10, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 106, 57, -8.0F, -10.0F, -5.0F, 1, 10, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 116, 46, -1.0F, -9.0F, 8.0F, 2, 1, 4, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 88, 30, -5.0F, -13.0F, -5.0F, 10, 1, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 5, 39, -2.0F, -10.0F, 12.0F, 4, 5, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 64, 86, -3.0F, -7.0F, -21.0F, 6, 6, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 84, 6, -4.0F, -7.0F, -21.0F, 1, 6, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 40, 2, 3.0F, -7.0F, -21.0F, 1, 6, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 1, 112, -3.0F, -8.0F, -21.0F, 6, 1, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 63, 68, -3.0F, -1.0F, -21.0F, 6, 1, 14, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -7.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 38, 58, -2.0F, -1.0F, -2.0F, 4, 32, 4, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 104, 104, -3.0F, -2.0F, -3.0F, 6, 1, 6, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		YEZI_1.render(f5);
		YEZI_2.render(f5);
		YEZI_3.render(f5);
		YEZI_4.render(f5);
		head.render(f5);
		body.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
		float maxMove=0.3f;
        this.YEZI_1.rotateAngleZ = -MathHelper.abs(MathHelper.cos(ageInTicks*0.01f))*maxMove;
        this.YEZI_2.rotateAngleX = MathHelper.abs(MathHelper.cos(ageInTicks*0.01f))*maxMove;
        this.YEZI_3.rotateAngleX = -MathHelper.abs(MathHelper.cos(ageInTicks*0.01f))*maxMove;
        this.YEZI_4.rotateAngleZ = MathHelper.abs(MathHelper.cos(ageInTicks*0.01f))*maxMove;
    }
}
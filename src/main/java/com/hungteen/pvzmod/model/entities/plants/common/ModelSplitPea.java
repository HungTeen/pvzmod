package com.hungteen.pvzmod.model.entities.plants.common;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


public class ModelSplitPea extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer head2;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer YEZI_4;
	private final ModelRenderer YEZI_3;
	private final ModelRenderer YEZI_2;
	private final ModelRenderer YEZI_1;

	public ModelSplitPea() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		head2 = new ModelRenderer(this);
		head2.setRotationPoint(0.0F, -35.0F, 9.0F);
		total.addChild(head2);
		setRotationAngle(head2, 0.0F, 3.1416F, 0.0F);
		head2.cubeList.add(new ModelBox(head2, 4, 175, -7.0F, -12.0F, -7.0F, 14, 14, 14, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 66, 182, 7.0F, -10.0F, -5.0F, 1, 10, 10, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 94, 182, -8.0F, -10.0F, -5.0F, 1, 10, 10, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 121, 190, -6.0F, -13.0F, -6.0F, 12, 1, 12, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 165, 170, -3.0F, -7.0F, -21.0F, 6, 6, 14, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 204, 156, 3.0F, -7.0F, -21.0F, 1, 6, 14, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 106, 159, -3.0F, -8.0F, -21.0F, 6, 1, 14, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 60, 157, -3.0F, -1.0F, -21.0F, 6, 1, 14, 0.0F, false));
		head2.cubeList.add(new ModelBox(head2, 14, 149, -4.0F, -7.0F, -21.0F, 1, 6, 14, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -31.0F, 0.0F);
		total.addChild(body);
		body.cubeList.add(new ModelBox(body, 238, 186, -2.0F, -1.0F, -2.0F, 4, 32, 4, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 202, 190, -4.0F, -2.0F, -4.0F, 8, 1, 8, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -35.0F, -9.0F);
		total.addChild(head);
		head.cubeList.add(new ModelBox(head, 135, 237, -5.0F, -10.0F, 7.0F, 10, 12, 4, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 74, 225, -7.0F, -12.0F, -7.0F, 14, 14, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 48, 234, 7.0F, -10.0F, -5.0F, 1, 10, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 21, 234, -8.0F, -10.0F, -5.0F, 1, 10, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 128, 219, -5.0F, -13.0F, -5.0F, 10, 1, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 4, 209, -3.0F, -7.0F, -21.0F, 6, 6, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 50, 206, -3.0F, -8.0F, -21.0F, 6, 1, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 95, 207, -3.0F, -1.0F, -21.0F, 6, 1, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 170, 205, -4.0F, -7.0F, -21.0F, 1, 6, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 206, 204, 3.0F, -7.0F, -21.0F, 1, 6, 14, 0.0F, false));

		YEZI_4 = new ModelRenderer(this);
		YEZI_4.setRotationPoint(-2.0F, -1.0F, 0.0F);
		total.addChild(YEZI_4);
		YEZI_4.cubeList.add(new ModelBox(YEZI_4, 167, 230, -11.0F, -1.0F, -4.0F, 8, 2, 8, 0.0F, false));
		YEZI_4.cubeList.add(new ModelBox(YEZI_4, 240, 226, -3.0F, 0.0F, -2.0F, 3, 1, 4, 0.0F, false));

		YEZI_3 = new ModelRenderer(this);
		YEZI_3.setRotationPoint(0.0F, 0.0F, -2.0F);
		total.addChild(YEZI_3);
		YEZI_3.cubeList.add(new ModelBox(YEZI_3, 240, 234, -2.0F, -1.0F, -3.0F, 4, 1, 3, 0.0F, false));
		YEZI_3.cubeList.add(new ModelBox(YEZI_3, 202, 230, -4.0F, -2.0F, -11.0F, 8, 2, 8, 0.0F, false));

		YEZI_2 = new ModelRenderer(this);
		YEZI_2.setRotationPoint(0.0F, 0.0F, 2.0F);
		total.addChild(YEZI_2);
		YEZI_2.cubeList.add(new ModelBox(YEZI_2, 169, 244, -4.0F, -2.0F, 3.0F, 8, 2, 8, 0.0F, false));
		YEZI_2.cubeList.add(new ModelBox(YEZI_2, 239, 242, -2.0F, -1.0F, 0.0F, 4, 1, 3, 0.0F, false));

		YEZI_1 = new ModelRenderer(this);
		YEZI_1.setRotationPoint(2.0F, 0.0F, 0.0F);
		total.addChild(YEZI_1);
		YEZI_1.cubeList.add(new ModelBox(YEZI_1, 240, 249, 0.0F, -1.0F, -2.0F, 3, 1, 4, 0.0F, false));
		YEZI_1.cubeList.add(new ModelBox(YEZI_1, 204, 244, 3.0F, -2.0F, -4.0F, 8, 2, 8, 0.0F, false));
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
	
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
		float maxMove=0.3f;
        this.YEZI_1.rotateAngleZ = -MathHelper.abs(MathHelper.cos(ageInTicks*0.01f))*maxMove;
        this.YEZI_2.rotateAngleX = MathHelper.abs(MathHelper.cos(ageInTicks*0.01f))*maxMove;
        this.YEZI_3.rotateAngleX = -MathHelper.abs(MathHelper.cos(ageInTicks*0.01f))*maxMove;
        this.YEZI_4.rotateAngleZ = MathHelper.abs(MathHelper.cos(ageInTicks*0.01f))*maxMove;
    }
}
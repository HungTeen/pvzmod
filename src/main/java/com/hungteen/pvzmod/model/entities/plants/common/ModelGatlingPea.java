package com.hungteen.pvzmod.model.entities.plants.common;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


public class ModelGatlingPea extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer gar;
	private final ModelRenderer helmet;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer leaves;
	private final ModelRenderer YEZI_1;
	private final ModelRenderer YEZI_2;
	private final ModelRenderer YEZI_3;
	private final ModelRenderer YEZI_4;

	public ModelGatlingPea() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -31.0F, 0.0F);
		total.addChild(body);
		body.cubeList.add(new ModelBox(body, 111, 91, -2.0F, -1.0F, -2.0F, 4, 32, 4, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 86, 120, -3.0F, -2.0F, -3.0F, 6, 1, 6, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -35.0F, 0.0F);
		total.addChild(head);
		head.cubeList.add(new ModelBox(head, 87, 106, -5.0F, -10.0F, 7.0F, 10, 10, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 1, 99, -7.0F, -12.0F, -7.0F, 14, 14, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 60, 111, 7.0F, -10.0F, -5.0F, 1, 10, 6, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 113, 73, -8.0F, -10.0F, -5.0F, 1, 10, 6, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 87, 1, -5.0F, -13.0F, -5.0F, 10, 1, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 74, 44, -3.0F, -7.0F, -17.0F, 6, 6, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 84, 62, -4.0F, -7.0F, -17.0F, 1, 6, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 78, 80, -3.0F, -8.0F, -17.0F, 6, 1, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 36, 72, 3.0F, -7.0F, -17.0F, 1, 6, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 64, 92, -3.0F, -1.0F, -17.0F, 6, 1, 10, 0.0F, false));

		gar = new ModelRenderer(this);
		gar.setRotationPoint(0.0F, -4.0F, -17.0F);
		head.addChild(gar);
		gar.cubeList.add(new ModelBox(gar, 113, 14, -3.0F, -1.0F, -5.0F, 2, 2, 5, 0.0F, false));
		gar.cubeList.add(new ModelBox(gar, 113, 23, -1.0F, -3.0F, -5.0F, 2, 2, 5, 0.0F, false));
		gar.cubeList.add(new ModelBox(gar, 113, 32, 1.0F, -1.0F, -5.0F, 2, 2, 5, 0.0F, false));
		gar.cubeList.add(new ModelBox(gar, 113, 41, -1.0F, 1.0F, -5.0F, 2, 2, 5, 0.0F, false));

		helmet = new ModelRenderer(this);
		helmet.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(helmet);
		helmet.cubeList.add(new ModelBox(helmet, 119, 50, -9.0F, -12.0F, 1.0F, 2, 15, 2, 0.0F, false));
		helmet.cubeList.add(new ModelBox(helmet, 109, 51, 7.0F, -13.0F, 1.0F, 2, 16, 2, 0.0F, false));
		helmet.cubeList.add(new ModelBox(helmet, 1, 1, -9.0F, -15.0F, -8.0F, 18, 3, 18, 0.0F, false));
		helmet.cubeList.add(new ModelBox(helmet, 1, 25, -8.0F, -12.0F, 3.0F, 16, 13, 8, 0.0F, false));
		helmet.cubeList.add(new ModelBox(helmet, 2, 49, -8.0F, -17.0F, -7.0F, 16, 2, 16, 0.0F, false));
		helmet.cubeList.add(new ModelBox(helmet, 53, 26, -6.0F, -19.0F, -5.0F, 12, 2, 12, 0.0F, false));
		helmet.cubeList.add(new ModelBox(helmet, 71, 61, -4.0F, 2.0F, -5.0F, 8, 4, 2, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-7.0F, 2.0F, 2.0F);
		helmet.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.7854F, 0.4363F);
		bone.cubeList.add(new ModelBox(bone, 88, 92, -1.0F, 0.0F, -1.0F, 9, 1, 2, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(7.0F, 3.0F, 2.0F);
		helmet.addChild(bone2);
		setRotationAngle(bone2, 0.0F, -0.7854F, -0.4363F);
		bone2.cubeList.add(new ModelBox(bone2, 64, 104, -8.0F, -1.0F, -1.0F, 9, 1, 2, 0.0F, false));

		leaves = new ModelRenderer(this);
		leaves.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(leaves);
		

		YEZI_1 = new ModelRenderer(this);
		YEZI_1.setRotationPoint(2.0F, 0.0F, 0.0F);
		leaves.addChild(YEZI_1);
		YEZI_1.cubeList.add(new ModelBox(YEZI_1, 72, 1, 0.0F, -1.0F, -2.0F, 3, 1, 4, 0.0F, false));
		YEZI_1.cubeList.add(new ModelBox(YEZI_1, 80, 14, 3.0F, -2.0F, -4.0F, 8, 2, 8, 0.0F, false));

		YEZI_2 = new ModelRenderer(this);
		YEZI_2.setRotationPoint(0.0F, 0.0F, 2.0F);
		leaves.addChild(YEZI_2);
		YEZI_2.cubeList.add(new ModelBox(YEZI_2, 1, 70, -4.0F, -2.0F, 3.0F, 8, 2, 8, 0.0F, false));
		YEZI_2.cubeList.add(new ModelBox(YEZI_2, 69, 9, -2.0F, -1.0F, 0.0F, 4, 1, 3, 0.0F, false));

		YEZI_3 = new ModelRenderer(this);
		YEZI_3.setRotationPoint(0.0F, 0.0F, -2.0F);
		leaves.addChild(YEZI_3);
		YEZI_3.cubeList.add(new ModelBox(YEZI_3, 58, 43, -2.0F, -1.0F, -3.0F, 4, 1, 3, 0.0F, false));
		YEZI_3.cubeList.add(new ModelBox(YEZI_3, 51, 68, -4.0F, -2.0F, -11.0F, 8, 2, 8, 0.0F, false));

		YEZI_4 = new ModelRenderer(this);
		YEZI_4.setRotationPoint(-2.0F, -1.0F, 0.0F);
		leaves.addChild(YEZI_4);
		YEZI_4.cubeList.add(new ModelBox(YEZI_4, 1, 82, -11.0F, -1.0F, -4.0F, 8, 2, 8, 0.0F, false));
		YEZI_4.cubeList.add(new ModelBox(YEZI_4, 47, 92, -3.0F, 0.0F, -2.0F, 3, 1, 4, 0.0F, false));
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
		float maxMove=0.3f;
        this.YEZI_1.rotateAngleZ = -MathHelper.abs(MathHelper.cos(ageInTicks*0.01f))*maxMove;
        this.YEZI_2.rotateAngleX = MathHelper.abs(MathHelper.cos(ageInTicks*0.01f))*maxMove;
        this.YEZI_3.rotateAngleX = -MathHelper.abs(MathHelper.cos(ageInTicks*0.01f))*maxMove;
        this.YEZI_4.rotateAngleZ = MathHelper.abs(MathHelper.cos(ageInTicks*0.01f))*maxMove;
        this.gar.rotateAngleZ=ageInTicks/20;
	}
}
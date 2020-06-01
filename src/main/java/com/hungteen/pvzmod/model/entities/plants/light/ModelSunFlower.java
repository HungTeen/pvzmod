package com.hungteen.pvzmod.model.entities.plants.light;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


public class ModelSunFlower extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer leftarm2;
	private final ModelRenderer rightarm;
	private final ModelRenderer TUO;
	private final ModelRenderer HUABAN;
	private final ModelRenderer body;
	private final ModelRenderer YEZI_4;
	private final ModelRenderer YEZI_3;
	private final ModelRenderer YEZI_1;
	private final ModelRenderer YEZI_2;

	public ModelSunFlower() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		leftarm2 = new ModelRenderer(this);
		leftarm2.setRotationPoint(3.0F, -15.0F, 0.0F);
		total.addChild(leftarm2);
		setRotationAngle(leftarm2, 0.0F, 0.0F, -0.1745F);
		leftarm2.cubeList.add(new ModelBox(leftarm2, 0, 0, -2.0F, -1.0F, -1.0F, 7, 3, 2, 0.0F, false));

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(-3.0F, -15.0F, 0.0F);
		total.addChild(rightarm);
		setRotationAngle(rightarm, 0.0F, 0.0F, 0.1745F);
		rightarm.cubeList.add(new ModelBox(rightarm, 0, 0, -5.0F, -1.0F, -1.0F, 7, 3, 2, 0.0F, false));

		TUO = new ModelRenderer(this);
		TUO.setRotationPoint(0.0F, -36.0F, 0.0F);
		total.addChild(TUO);
		TUO.cubeList.add(new ModelBox(TUO, 72, 100, -12.0F, -14.0F, -6.0F, 24, 24, 4, 0.0F, false));
		TUO.cubeList.add(new ModelBox(TUO, 0, 106, 12.0F, -11.0F, -6.0F, 3, 18, 4, 0.0F, false));
		TUO.cubeList.add(new ModelBox(TUO, 17, 106, -15.0F, -11.0F, -6.0F, 3, 18, 4, 0.0F, false));
		TUO.cubeList.add(new ModelBox(TUO, 0, 85, -9.0F, -17.0F, -6.0F, 18, 3, 4, 0.0F, false));
		TUO.cubeList.add(new ModelBox(TUO, 0, 74, -9.0F, 10.0F, -6.0F, 18, 3, 4, 0.0F, false));

		HUABAN = new ModelRenderer(this);
		HUABAN.setRotationPoint(0.0F, -3.0F, 0.0F);
		TUO.addChild(HUABAN);
		HUABAN.cubeList.add(new ModelBox(HUABAN, 0, 62, -4.0F, -16.0F, -6.0F, 8, 2, 4, 0.0F, false));
		HUABAN.cubeList.add(new ModelBox(HUABAN, 29, 63, -4.0F, 16.0F, -6.0F, 8, 2, 4, 0.0F, false));
		HUABAN.cubeList.add(new ModelBox(HUABAN, 42, 36, 15.0F, -3.0F, -6.0F, 2, 8, 4, 0.0F, false));
		HUABAN.cubeList.add(new ModelBox(HUABAN, 73, 73, -17.0F, -3.0F, -6.0F, 2, 8, 4, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -1.0F, 0.0F);
		total.addChild(body);
		body.cubeList.add(new ModelBox(body, 112, 43, -2.0F, -36.0F, -2.0F, 4, 37, 4, 0.0F, false));

		YEZI_4 = new ModelRenderer(this);
		YEZI_4.setRotationPoint(-2.0F, -1.0F, 0.0F);
		total.addChild(YEZI_4);
		YEZI_4.cubeList.add(new ModelBox(YEZI_4, 78, 26, -11.0F, -1.0F, -4.0F, 8, 2, 8, 0.0F, false));
		YEZI_4.cubeList.add(new ModelBox(YEZI_4, 114, 28, -3.0F, 0.0F, -2.0F, 3, 1, 4, 0.0F, false));

		YEZI_3 = new ModelRenderer(this);
		YEZI_3.setRotationPoint(0.0F, 0.0F, -2.0F);
		total.addChild(YEZI_3);
		YEZI_3.cubeList.add(new ModelBox(YEZI_3, 114, 19, -2.0F, -1.0F, -3.0F, 4, 1, 3, 0.0F, false));
		YEZI_3.cubeList.add(new ModelBox(YEZI_3, 0, 36, -4.0F, -2.0F, -11.0F, 8, 2, 8, 0.0F, false));

		YEZI_1 = new ModelRenderer(this);
		YEZI_1.setRotationPoint(2.0F, 0.0F, 0.0F);
		total.addChild(YEZI_1);
		YEZI_1.cubeList.add(new ModelBox(YEZI_1, 114, 0, 0.0F, -1.0F, -2.0F, 3, 1, 4, 0.0F, false));
		YEZI_1.cubeList.add(new ModelBox(YEZI_1, 79, 0, 3.0F, -2.0F, -4.0F, 8, 2, 8, 0.0F, false));

		YEZI_2 = new ModelRenderer(this);
		YEZI_2.setRotationPoint(0.0F, 0.0F, 2.0F);
		total.addChild(YEZI_2);
		YEZI_2.cubeList.add(new ModelBox(YEZI_2, 78, 12, -4.0F, -2.0F, 3.0F, 8, 2, 8, 0.0F, false));
		YEZI_2.cubeList.add(new ModelBox(YEZI_2, 114, 9, -2.0F, -1.0F, 0.0F, 4, 1, 3, 0.0F, false));
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
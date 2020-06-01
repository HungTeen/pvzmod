package com.hungteen.pvzmod.model.entities.plants.ice;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


public class ModelSnowPea extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer gg1;
	private final ModelRenderer g1;
	private final ModelRenderer gg2;
	private final ModelRenderer g2;
	private final ModelRenderer gg3;
	private final ModelRenderer g3;
	private final ModelRenderer gg4;
	private final ModelRenderer g4;
	private final ModelRenderer gg5;
	private final ModelRenderer g5;
	private final ModelRenderer YEZI_4;
	private final ModelRenderer YEZI_3;
	private final ModelRenderer YEZI_2;
	private final ModelRenderer YEZI_1;

	public ModelSnowPea() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -31.0F, 0.0F);
		total.addChild(body);
		body.cubeList.add(new ModelBox(body, 238, 218, -2.0F, -1.0F, -2.0F, 4, 32, 4, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 210, 246, -3.0F, -2.0F, -3.0F, 6, 1, 6, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -35.0F, 0.0F);
		total.addChild(head);
		head.cubeList.add(new ModelBox(head, 212, 232, -5.0F, -10.0F, 7.0F, 10, 10, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 150, 226, -7.0F, -12.0F, -7.0F, 14, 14, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 124, 234, 7.0F, -10.0F, -5.0F, 1, 10, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 125, 209, -8.0F, -10.0F, -5.0F, 1, 10, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 156, 211, -5.0F, -13.0F, -5.0F, 10, 1, 10, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 196, 198, -3.0F, -7.0F, -21.0F, 6, 6, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 124, 183, -4.0F, -7.0F, -21.0F, 1, 6, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 161, 185, 3.0F, -7.0F, -21.0F, 1, 6, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 124, 164, -3.0F, -8.0F, -21.0F, 6, 1, 14, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 170, 164, -3.0F, -1.0F, -21.0F, 6, 1, 14, 0.0F, false));

		gg1 = new ModelRenderer(this);
		gg1.setRotationPoint(2.0F, -7.0F, 8.0F);
		head.addChild(gg1);
		setRotationAngle(gg1, 0.2618F, 0.6981F, 0.0F);
		gg1.cubeList.add(new ModelBox(gg1, 212, 171, -2.0F, -2.0F, 0.0F, 4, 4, 7, 0.0F, false));

		g1 = new ModelRenderer(this);
		g1.setRotationPoint(0.0F, 0.0F, 7.0F);
		gg1.addChild(g1);
		setRotationAngle(g1, 0.0F, -0.8727F, 0.0F);
		g1.cubeList.add(new ModelBox(g1, 242, 186, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		gg2 = new ModelRenderer(this);
		gg2.setRotationPoint(1.0F, -4.0F, 8.0F);
		head.addChild(gg2);
		setRotationAngle(gg2, -0.4363F, 0.6981F, 0.0F);
		gg2.cubeList.add(new ModelBox(gg2, 231, 157, -2.0F, -2.0F, 0.0F, 4, 4, 7, 0.0F, false));

		g2 = new ModelRenderer(this);
		g2.setRotationPoint(0.0F, 0.0F, 7.0F);
		gg2.addChild(g2);
		setRotationAngle(g2, 0.0F, -0.8727F, 0.0F);
		g2.cubeList.add(new ModelBox(g2, 211, 160, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		gg3 = new ModelRenderer(this);
		gg3.setRotationPoint(0.0F, -7.0F, 8.0F);
		head.addChild(gg3);
		setRotationAngle(gg3, 0.6109F, 0.0F, 0.0F);
		gg3.cubeList.add(new ModelBox(gg3, 180, 148, -2.0F, -2.0F, 0.0F, 4, 4, 7, 0.0F, false));

		g3 = new ModelRenderer(this);
		g3.setRotationPoint(0.0F, 0.0F, 7.0F);
		gg3.addChild(g3);
		setRotationAngle(g3, 0.0F, -0.8727F, 0.0F);
		g3.cubeList.add(new ModelBox(g3, 160, 152, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		gg4 = new ModelRenderer(this);
		gg4.setRotationPoint(-2.0F, -7.0F, 8.0F);
		head.addChild(gg4);
		setRotationAngle(gg4, 0.2618F, -0.6981F, 0.0F);
		gg4.cubeList.add(new ModelBox(gg4, 127, 149, -2.0F, -2.0F, 0.0F, 4, 4, 7, 0.0F, false));

		g4 = new ModelRenderer(this);
		g4.setRotationPoint(0.0F, 0.0F, 7.0F);
		gg4.addChild(g4);
		setRotationAngle(g4, 0.0F, -0.8727F, 0.0F);
		g4.cubeList.add(new ModelBox(g4, 227, 152, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		gg5 = new ModelRenderer(this);
		gg5.setRotationPoint(-1.0F, -4.0F, 8.0F);
		head.addChild(gg5);
		setRotationAngle(gg5, -0.4363F, -0.6981F, 0.0F);
		gg5.cubeList.add(new ModelBox(gg5, 146, 133, -2.0F, -2.0F, 0.0F, 4, 4, 7, 0.0F, false));

		g5 = new ModelRenderer(this);
		g5.setRotationPoint(0.0F, 0.0F, 7.0F);
		gg5.addChild(g5);
		setRotationAngle(g5, 0.0F, -0.8727F, 0.0F);
		g5.cubeList.add(new ModelBox(g5, 187, 136, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		YEZI_4 = new ModelRenderer(this);
		YEZI_4.setRotationPoint(-2.0F, -1.0F, 0.0F);
		total.addChild(YEZI_4);
		YEZI_4.cubeList.add(new ModelBox(YEZI_4, 206, 132, -11.0F, -1.0F, -4.0F, 8, 2, 8, 0.0F, false));
		YEZI_4.cubeList.add(new ModelBox(YEZI_4, 194, 118, -3.0F, 0.0F, -2.0F, 3, 1, 4, 0.0F, false));

		YEZI_3 = new ModelRenderer(this);
		YEZI_3.setRotationPoint(0.0F, 0.0F, -2.0F);
		total.addChild(YEZI_3);
		YEZI_3.cubeList.add(new ModelBox(YEZI_3, 170, 121, -2.0F, -1.0F, -3.0F, 4, 1, 3, 0.0F, false));
		YEZI_3.cubeList.add(new ModelBox(YEZI_3, 218, 116, -4.0F, -2.0F, -11.0F, 8, 2, 8, 0.0F, false));

		YEZI_2 = new ModelRenderer(this);
		YEZI_2.setRotationPoint(0.0F, 0.0F, 2.0F);
		total.addChild(YEZI_2);
		YEZI_2.cubeList.add(new ModelBox(YEZI_2, 217, 99, -4.0F, -2.0F, 3.0F, 8, 2, 8, 0.0F, false));
		YEZI_2.cubeList.add(new ModelBox(YEZI_2, 194, 103, -2.0F, -1.0F, 0.0F, 4, 1, 3, 0.0F, false));

		YEZI_1 = new ModelRenderer(this);
		YEZI_1.setRotationPoint(2.0F, 0.0F, 0.0F);
		total.addChild(YEZI_1);
		YEZI_1.cubeList.add(new ModelBox(YEZI_1, 170, 102, 0.0F, -1.0F, -2.0F, 3, 1, 4, 0.0F, false));
		YEZI_1.cubeList.add(new ModelBox(YEZI_1, 128, 116, 3.0F, -2.0F, -4.0F, 8, 2, 8, 0.0F, false));
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
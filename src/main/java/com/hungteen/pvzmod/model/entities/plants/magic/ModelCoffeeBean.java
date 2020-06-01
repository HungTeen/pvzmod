package com.hungteen.pvzmod.model.entities.plants.magic;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.5.2
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


public class ModelCoffeeBean extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer bone11;
	private final ModelRenderer left_wing;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer right_wing;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;

	public ModelCoffeeBean() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 16.0F, 4.0F);
		setRotationAngle(total, 1.2217F, 0.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 1.0F);
		total.addChild(body);
		body.cubeList.add(new ModelBox(body, 71, 103, -6.0F, -8.0F, -9.0F, 12, 8, 16, 0.0F, false));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(0.0F, -4.0F, 7.0F);
		body.addChild(bone11);
		setRotationAngle(bone11, 0.2618F, 0.0F, 0.0F);
		bone11.cubeList.add(new ModelBox(bone11, 115, 96, -1.0F, -1.0F, -1.0F, 2, 2, 4, 0.0F, false));

		left_wing = new ModelRenderer(this);
		left_wing.setRotationPoint(6.0F, -4.0F, 3.0F);
		total.addChild(left_wing);
		setRotationAngle(left_wing, 0.0F, -0.4363F, 0.0F);
		left_wing.cubeList.add(new ModelBox(left_wing, 94, 97, -2.0F, -1.0F, -1.0F, 7, 2, 2, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(5.0F, 0.0F, 0.0F);
		left_wing.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.1745F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 109, 82, -2.0F, -1.0F, 0.0F, 2, 1, 6, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(5.0F, 0.0F, 0.0F);
		left_wing.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.6981F, 0.0F);
		bone2.cubeList.add(new ModelBox(bone2, 109, 66, -1.0F, -1.0F, 0.0F, 2, 1, 6, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(5.0F, 0.0F, 0.0F);
		left_wing.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 1.6581F, 0.0F);
		bone3.cubeList.add(new ModelBox(bone3, 110, 55, -1.0F, -1.0F, 0.0F, 2, 1, 6, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(5.0F, 0.0F, 0.0F);
		left_wing.addChild(bone4);
		setRotationAngle(bone4, 0.0F, 2.7053F, 0.0F);
		bone4.cubeList.add(new ModelBox(bone4, 108, 44, -0.3572F, -1.0F, -0.766F, 2, 1, 6, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(4.0F, 0.0F, 0.0F);
		left_wing.addChild(bone5);
		setRotationAngle(bone5, 0.0F, -2.7925F, 0.0F);
		bone5.cubeList.add(new ModelBox(bone5, 108, 32, -1.0F, -1.0F, 0.0F, 2, 1, 6, 0.0F, false));

		right_wing = new ModelRenderer(this);
		right_wing.setRotationPoint(-6.0F, -4.0F, 3.0F);
		total.addChild(right_wing);
		setRotationAngle(right_wing, 0.0F, 0.4363F, 0.0F);
		right_wing.cubeList.add(new ModelBox(right_wing, 107, 13, -5.0F, -1.0F, -1.0F, 7, 2, 2, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(-5.0F, 0.0F, 0.0F);
		right_wing.addChild(bone6);
		setRotationAngle(bone6, 0.0F, -0.1745F, 0.0F);
		bone6.cubeList.add(new ModelBox(bone6, 108, 22, 0.0F, -1.0F, 0.0F, 2, 1, 6, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(-5.0F, 0.0F, 0.0F);
		right_wing.addChild(bone7);
		setRotationAngle(bone7, 0.0F, -0.6981F, 0.0F);
		bone7.cubeList.add(new ModelBox(bone7, 109, 1, -1.0F, -1.0F, 0.0F, 2, 1, 6, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(-5.0F, 0.0F, 0.0F);
		right_wing.addChild(bone8);
		setRotationAngle(bone8, 0.0F, -1.6581F, 0.0F);
		bone8.cubeList.add(new ModelBox(bone8, 86, 2, -1.0F, -1.0F, 0.0F, 2, 1, 6, 0.0F, false));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(-5.0F, 0.0F, 0.0F);
		right_wing.addChild(bone9);
		setRotationAngle(bone9, 0.0F, -2.7053F, 0.0F);
		bone9.cubeList.add(new ModelBox(bone9, 84, 12, -1.6428F, -1.0F, -0.766F, 2, 1, 6, 0.0F, false));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(-4.0F, 0.0F, 0.0F);
		right_wing.addChild(bone10);
		setRotationAngle(bone10, 0.0F, 2.7925F, 0.0F);
		bone10.cubeList.add(new ModelBox(bone10, 85, 25, -1.0F, -1.0F, 0.0F, 2, 1, 6, 0.0F, false));
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
		this.left_wing.rotateAngleZ=MathHelper.sin(ageInTicks/10)*3.14159f/4;
		this.right_wing.rotateAngleZ=-MathHelper.sin(ageInTicks/10)*3.14159f/4;
	}
}
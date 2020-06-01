package com.hungteen.pvzmod.model.entities.plants.magic;

import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.entities.plants.magic.EntityCatTail;
import com.hungteen.pvzmod.entities.plants.magic.EntityStrangeCat;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelCatTail extends ModelBase {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer legs;
	private final ModelRenderer left_ear;
	private final ModelRenderer bone3;
	private final ModelRenderer bone2;
	private final ModelRenderer right_ear;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer tail;
	private final ModelRenderer tail1;
	private final ModelRenderer tail2;
	private final ModelRenderer tail3;
	private final ModelRenderer tail4;
	private final ModelRenderer tail5;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer tail6;

	public ModelCatTail() {
		textureWidth = 512;
		textureHeight = 512;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		body.cubeList.add(new ModelBox(body, 256, 384, -33.3301F, -68.0F, -32.0F, 64, 64, 64, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 4.0F, 0.0F);
		body.addChild(bone4);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(35.0F, -68.0F, -32.0F);
		setRotationAngle(bone5, -0.2618F, 0.0F, 0.0F);
		bone4.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 367, 0, -71.3301F, 0.0F, 0.0F, 3, 10, 70, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 214, 0, -4.3301F, 0.0F, 0.0F, 3, 10, 70, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone4.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 288, 11, -36.3301F, -49.8827F, 31.6148F, 70, 10, 4, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 294, 40, -36.3301F, -68.0F, -35.0F, 70, 10, 3, 0.0F, false));

		legs = new ModelRenderer(this);
		legs.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(legs);
		legs.cubeList.add(new ModelBox(legs, 368, 338, -43.3301F, -4.0F, 6.0F, 36, 4, 36, 0.0F, false));
		legs.cubeList.add(new ModelBox(legs, 368, 298, 4.6699F, -4.0F, 6.0F, 36, 4, 36, 0.0F, false));
		legs.cubeList.add(new ModelBox(legs, 368, 258, -43.3301F, -4.0F, -42.0F, 36, 4, 36, 0.0F, false));
		legs.cubeList.add(new ModelBox(legs, 367, 217, 4.6699F, -4.0F, -42.0F, 36, 4, 36, 0.0F, false));

		left_ear = new ModelRenderer(this);
		left_ear.setRotationPoint(32.0F, -66.0F, -6.0F);
		setRotationAngle(left_ear, 0.0F, 0.0F, 0.8727F);
		total.addChild(left_ear);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
		left_ear.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 479, 302, -15.0486F, -5.4591F, -3.999F, 9, 20, 8, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.8727F);
		left_ear.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 459, 0, -13.855F, 1.0189F, -4.0F, 19, 7, 8, 0.0F, false));

		right_ear = new ModelRenderer(this);
		right_ear.setRotationPoint(-35.0F, -66.0F, -6.0F);
		setRotationAngle(right_ear, 0.0F, 0.0F, -0.8727F);
		total.addChild(right_ear);

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, 0.0F, 0.0F);
		right_ear.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 479, 262, 6.0486F, -5.4591F, -3.99F, 9, 20, 8, 0.0F, true));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone8, 0.0F, 0.0F, -0.8727F);
		right_ear.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 459, 32, -5.145F, 1.0189F, -4.0F, 19, 7, 8, 0.0F, true));

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, -24.8669F, 39.9447F);
		setRotationAngle(tail, -0.1745F, 0.0F, 0.0F);
		total.addChild(tail);

		tail1 = new ModelRenderer(this);
		tail1.setRotationPoint(0.0F, 9.8669F, -7.9447F);
		tail.addChild(tail1);
		tail1.cubeList.add(new ModelBox(tail1, 6, 448, -5.3301F, -5.0F, -5.0F, 8, 8, 37, 0.0F, false));

		tail2 = new ModelRenderer(this);
		tail2.setRotationPoint(0.0F, 6.7538F, 22.5237F);
		setRotationAngle(tail2, -0.8727F, 0.0F, 0.0F);
		tail.addChild(tail2);
		tail2.cubeList.add(new ModelBox(tail2, 304, 323, -5.2301F, -29.2438F, -2.3326F, 8, 32, 8, 0.0F, false));

		tail3 = new ModelRenderer(this);
		tail3.setRotationPoint(0.0F, -23.3801F, 21.2917F);
		setRotationAngle(tail3, -1.6581F, 0.0F, 0.0F);
		tail.addChild(tail3);
		tail3.cubeList.add(new ModelBox(tail3, 222, 331, -5.3301F, -28.5396F, -11.7591F, 8, 8, 25, 0.0F, false));

		tail4 = new ModelRenderer(this);
		tail4.setRotationPoint(0.0F, -21.0F, 1.0F);
		setRotationAngle(tail4, 0.2618F, 0.0F, 0.0F);
		tail.addChild(tail4);
		tail4.cubeList.add(new ModelBox(tail4, 168, 372, -5.3301F, -2.336F, 43.0566F, 8, 4, 8, 0.0F, false));

		tail5 = new ModelRenderer(this);
		tail5.setRotationPoint(2.6699F, -19.5361F, -3.1281F);
		setRotationAngle(tail5, 0.2618F, 0.0F, 0.0F);
		tail.addChild(tail5);

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(0.0F, 0.0F, 0.0F);
		tail5.addChild(bone10);
		bone10.cubeList.add(new ModelBox(bone10, 0, 0, -7.2426F, -28.9745F, 43.9555F, 6, 27, 7, 0.0F, false));
		bone10.cubeList.add(new ModelBox(bone10, 0, 0, -7.2426F, -28.9745F, 51.4408F, 6, 27, 7, 0.0F, false));
		bone10.cubeList.add(new ModelBox(bone10, 0, 0, -11.4853F, -28.9745F, 48.1982F, 7, 27, 6, 0.0F, false));
		bone10.cubeList.add(new ModelBox(bone10, 0, 0, -4.0F, -28.9745F, 48.1982F, 7, 27, 6, 0.0F, false));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bone11, 0.0F, 0.7854F, 0.0F);
		tail5.addChild(bone11);
		bone11.cubeList.add(new ModelBox(bone11, 0, 0, -42.2026F, -28.9745F, 25.9599F, 6, 27, 7, 0.0F, false));
		bone11.cubeList.add(new ModelBox(bone11, 0, 0, -42.2026F, -28.9745F, 33.4452F, 6, 27, 7, 0.0F, false));
		bone11.cubeList.add(new ModelBox(bone11, 0, 0, -46.4452F, -28.9745F, 30.2026F, 7, 27, 6, 0.0F, false));
		bone11.cubeList.add(new ModelBox(bone11, 0, 0, -38.9599F, -28.9745F, 30.2026F, 7, 27, 6, 0.0F, false));

		tail6 = new ModelRenderer(this);
		tail6.setRotationPoint(-2.0F, -61.0F, 39.0F);
		setRotationAngle(tail6, -0.2618F, -0.1745F, -0.6981F);
		tail.addChild(tail6);
		tail6.cubeList.add(new ModelBox(tail6, 377, 272, 4.6394F, -8.0881F, -8.206F, 4, 4, 4, 0.0F, false));
		tail6.cubeList.add(new ModelBox(tail6, 368, 225, 7.6394F, -10.0881F, -10.206F, 3, 3, 3, 0.0F, false));
		tail6.cubeList.add(new ModelBox(tail6, 483, 228, -1.3606F, -6.0881F, -6.206F, 8, 8, 8, 0.0F, false));
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
		if(entityIn instanceof EntityCatTail) {
			if(((EntityCatTail) entityIn).getAttackTime()%2==0&&((EntityCatTail) entityIn).getAttackTime()!=0) {
				this.tail.rotateAngleX=0.6745f;
			}
			else {
				this.tail.rotateAngleX=0.1745f;
			}
		}
		else if(entityIn instanceof EntityStrangeCat) {
			EntityStrangeCat entity=((EntityStrangeCat)entityIn);
			if(entity.getAttackTime()<entity.getAttackCD()) {
				this.tail.rotateAngleX=0.6745f;
			}
			else {
				this.tail.rotateAngleX=0.1745f;
			}
		}
	}
}
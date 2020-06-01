package com.hungteen.pvzmod.model.entities.plants.ice;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelIceShroom extends ModelBase {
	private final ModelRenderer body;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer bone13;
	private final ModelRenderer bone14;
	private final ModelRenderer bone15;
	private final ModelRenderer bone16;
	private final ModelRenderer bone17;
	private final ModelRenderer bone18;
	private final ModelRenderer bone19;
	private final ModelRenderer bone20;
	private final ModelRenderer bone21;
	private final ModelRenderer bone22;
	private final ModelRenderer bone23;
	private final ModelRenderer bone24;
	private final ModelRenderer bone25;
	private final ModelRenderer bone26;
	private final ModelRenderer bone27;
	private final ModelRenderer bone28;
	private final ModelRenderer bone29;
	private final ModelRenderer bone30;
	private final ModelRenderer bone31;
	private final ModelRenderer bone32;
	private final ModelRenderer bone33;
	private final ModelRenderer bone34;
	private final ModelRenderer bone35;
	private final ModelRenderer bone36;
	private final ModelRenderer bone39;
	private final ModelRenderer bone40;
	private final ModelRenderer bone41;
	private final ModelRenderer bone42;
	private final ModelRenderer bone43;
	private final ModelRenderer bone44;

	public ModelIceShroom() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 63, 96, -8.0F, -16.0F, -8.0F, 16, 15, 16, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 55, 74, -9.0F, -1.0F, -9.0F, 18, 1, 18, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 65, 54, -8.0F, -19.0F, -7.0F, 16, 3, 15, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-7.0F, -19.0F, -6.0F);
		setRotationAngle(bone, 0.2618F, 0.7854F, 0.0F);
		body.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 118, 45, -1.0F, -3.0F, -1.0F, 2, 4, 2, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, -3.0F, 0.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.7854F);
		bone.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 118, 45, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(7.0F, -19.0F, -6.0F);
		setRotationAngle(bone3, 0.2618F, -0.7854F, 0.0F);
		body.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 103, 45, -1.0F, -3.0F, -1.0F, 2, 4, 2, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, -3.0F, 0.0F);
		setRotationAngle(bone4, 0.0F, 0.0F, 0.7854F);
		bone3.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 103, 45, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(-4.0F, -19.0F, -7.0F);
		setRotationAngle(bone5, 0.3491F, 0.0F, 0.0F);
		body.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 118, 35, -1.0F, -3.0F, 0.0F, 2, 4, 2, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, -3.0F, 1.0F);
		setRotationAngle(bone6, 0.0F, 0.0F, 0.7854F);
		bone5.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 118, 35, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(4.0F, -19.0F, -7.0F);
		setRotationAngle(bone7, 0.3491F, 0.0F, 0.0F);
		body.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 103, 34, -1.0F, -3.0F, 0.0F, 2, 4, 2, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, -3.0F, 1.0F);
		setRotationAngle(bone8, 0.0F, 0.0F, 0.7854F);
		bone7.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 103, 34, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(-2.0F, -19.0F, -4.0F);
		setRotationAngle(bone9, 0.0F, 0.0F, -0.3491F);
		body.addChild(bone9);
		bone9.cubeList.add(new ModelBox(bone9, 118, 22, -1.0F, -5.0F, 0.0F, 2, 6, 2, 0.0F, false));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(0.0F, -5.0F, 1.0F);
		setRotationAngle(bone10, 0.0F, 0.0F, 0.7854F);
		bone9.addChild(bone10);
		bone10.cubeList.add(new ModelBox(bone10, 118, 22, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(2.0F, -19.0F, -4.0F);
		setRotationAngle(bone11, 0.0F, 0.0F, 0.3491F);
		body.addChild(bone11);
		bone11.cubeList.add(new ModelBox(bone11, 103, 18, -1.0F, -5.0F, 0.0F, 2, 6, 2, 0.0F, false));

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(0.0F, -5.0F, 1.0F);
		setRotationAngle(bone12, 0.0F, 0.0F, 0.7854F);
		bone11.addChild(bone12);
		bone12.cubeList.add(new ModelBox(bone12, 103, 18, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(0.0F, -19.0F, -1.0F);
		body.addChild(bone13);
		bone13.cubeList.add(new ModelBox(bone13, 44, 116, -2.0F, -6.0F, -1.0F, 4, 6, 4, 0.0F, false));

		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(0.0F, -6.0F, 1.0F);
		setRotationAngle(bone14, 0.0F, 0.0F, 0.7854F);
		bone13.addChild(bone14);
		bone14.cubeList.add(new ModelBox(bone14, 44, 116, -2.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F, false));

		bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(-2.0F, -19.0F, 2.0F);
		setRotationAngle(bone15, 0.0F, 0.0F, -0.3491F);
		body.addChild(bone15);
		bone15.cubeList.add(new ModelBox(bone15, 33, 118, -1.0F, -5.0F, 0.0F, 2, 6, 2, 0.0F, false));

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(0.0F, -5.0F, 1.0F);
		setRotationAngle(bone16, 0.0F, 0.0F, 0.7854F);
		bone15.addChild(bone16);
		bone16.cubeList.add(new ModelBox(bone16, 33, 118, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(-7.0F, -19.0F, -4.0F);
		body.addChild(bone17);
		bone17.cubeList.add(new ModelBox(bone17, 41, 100, -1.0F, -3.0F, 0.0F, 2, 3, 2, 0.0F, false));

		bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(0.0F, -3.0F, 1.0F);
		setRotationAngle(bone18, 0.0F, 0.0F, 0.7854F);
		bone17.addChild(bone18);
		bone18.cubeList.add(new ModelBox(bone18, 41, 100, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone19 = new ModelRenderer(this);
		bone19.setRotationPoint(-7.0F, -19.0F, 3.0F);
		body.addChild(bone19);
		bone19.cubeList.add(new ModelBox(bone19, 30, 104, -1.0F, -3.0F, 0.0F, 2, 3, 2, 0.0F, false));

		bone20 = new ModelRenderer(this);
		bone20.setRotationPoint(0.0F, -3.0F, 1.0F);
		setRotationAngle(bone20, 0.0F, 0.0F, 0.7854F);
		bone19.addChild(bone20);
		bone20.cubeList.add(new ModelBox(bone20, 30, 104, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone21 = new ModelRenderer(this);
		bone21.setRotationPoint(-7.0F, -19.0F, -1.0F);
		body.addChild(bone21);
		bone21.cubeList.add(new ModelBox(bone21, 38, 88, -1.0F, -3.0F, 0.0F, 2, 3, 2, 0.0F, false));

		bone22 = new ModelRenderer(this);
		bone22.setRotationPoint(0.0F, -3.0F, 1.0F);
		setRotationAngle(bone22, 0.0F, 0.0F, 0.7854F);
		bone21.addChild(bone22);
		bone22.cubeList.add(new ModelBox(bone22, 38, 88, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone23 = new ModelRenderer(this);
		bone23.setRotationPoint(-4.0F, -19.0F, 6.0F);
		body.addChild(bone23);
		bone23.cubeList.add(new ModelBox(bone23, 50, 76, -1.0F, -3.0F, 0.0F, 2, 3, 2, 0.0F, false));

		bone24 = new ModelRenderer(this);
		bone24.setRotationPoint(0.0F, -3.0F, 1.0F);
		setRotationAngle(bone24, 0.0F, 0.0F, 0.7854F);
		bone23.addChild(bone24);
		bone24.cubeList.add(new ModelBox(bone24, 50, 76, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone25 = new ModelRenderer(this);
		bone25.setRotationPoint(-7.0F, -19.0F, 7.0F);
		setRotationAngle(bone25, -0.2618F, -0.7854F, 0.0F);
		body.addChild(bone25);
		bone25.cubeList.add(new ModelBox(bone25, 53, 65, -1.0F, -3.0F, -1.0F, 2, 4, 2, 0.0F, false));

		bone26 = new ModelRenderer(this);
		bone26.setRotationPoint(0.0F, -3.0F, -13.0F);
		setRotationAngle(bone26, 0.0F, 0.0F, 0.7854F);
		bone25.addChild(bone26);
		bone26.cubeList.add(new ModelBox(bone26, 53, 65, -1.0F, -1.0F, 12.0F, 2, 2, 2, 0.0F, false));

		bone27 = new ModelRenderer(this);
		bone27.setRotationPoint(0.0F, -19.0F, 6.0F);
		setRotationAngle(bone27, -0.4363F, 0.0F, 0.0F);
		body.addChild(bone27);
		bone27.cubeList.add(new ModelBox(bone27, 62, 55, -1.0F, -4.0F, -1.0F, 2, 5, 2, 0.0F, false));

		bone28 = new ModelRenderer(this);
		bone28.setRotationPoint(0.0F, -4.0F, 0.0F);
		setRotationAngle(bone28, 0.0F, 0.0F, 0.7854F);
		bone27.addChild(bone28);
		bone28.cubeList.add(new ModelBox(bone28, 62, 55, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone29 = new ModelRenderer(this);
		bone29.setRotationPoint(7.0F, -19.0F, 7.0F);
		setRotationAngle(bone29, -0.2618F, 0.7854F, 0.0F);
		body.addChild(bone29);
		bone29.cubeList.add(new ModelBox(bone29, 70, 41, -1.0F, -3.0F, -1.0F, 2, 4, 2, 0.0F, false));

		bone30 = new ModelRenderer(this);
		bone30.setRotationPoint(0.0F, -3.0F, 0.0F);
		setRotationAngle(bone30, 0.0F, 0.0F, 0.7854F);
		bone29.addChild(bone30);
		bone30.cubeList.add(new ModelBox(bone30, 70, 41, -1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F, false));

		bone31 = new ModelRenderer(this);
		bone31.setRotationPoint(4.0F, -19.0F, -7.0F);
		body.addChild(bone31);
		bone31.cubeList.add(new ModelBox(bone31, 82, 38, -1.0F, -3.0F, 13.0F, 2, 3, 2, 0.0F, false));

		bone32 = new ModelRenderer(this);
		bone32.setRotationPoint(0.0F, -3.0F, 1.0F);
		setRotationAngle(bone32, 0.0F, 0.0F, 0.7854F);
		bone31.addChild(bone32);
		bone32.cubeList.add(new ModelBox(bone32, 82, 38, -1.0F, -1.0F, 12.0F, 2, 2, 2, 0.0F, false));

		bone33 = new ModelRenderer(this);
		bone33.setRotationPoint(0.0F, -19.0F, -20.0F);
		body.addChild(bone33);
		bone33.cubeList.add(new ModelBox(bone33, 82, 28, -1.0F, -3.0F, 13.0F, 2, 3, 2, 0.0F, false));

		bone34 = new ModelRenderer(this);
		bone34.setRotationPoint(0.0F, -3.0F, 1.0F);
		setRotationAngle(bone34, 0.0F, 0.0F, 0.7854F);
		bone33.addChild(bone34);
		bone34.cubeList.add(new ModelBox(bone34, 82, 28, -1.0F, -1.0F, 12.0F, 2, 2, 2, 0.0F, false));

		bone35 = new ModelRenderer(this);
		bone35.setRotationPoint(7.0F, -19.0F, -10.0F);
		body.addChild(bone35);
		bone35.cubeList.add(new ModelBox(bone35, 88, 15, -1.0F, -3.0F, 13.0F, 2, 3, 2, 0.0F, false));

		bone36 = new ModelRenderer(this);
		bone36.setRotationPoint(0.0F, -3.0F, 1.0F);
		setRotationAngle(bone36, 0.0F, 0.0F, 0.7854F);
		bone35.addChild(bone36);
		bone36.cubeList.add(new ModelBox(bone36, 88, 15, -1.0F, -1.0F, 12.0F, 2, 2, 2, 0.0F, false));

		bone39 = new ModelRenderer(this);
		bone39.setRotationPoint(-7.0F, -19.0F, -4.0F);
		body.addChild(bone39);
		bone39.cubeList.add(new ModelBox(bone39, 96, 6, 13.0F, -3.0F, 3.0F, 2, 3, 2, 0.0F, false));

		bone40 = new ModelRenderer(this);
		bone40.setRotationPoint(0.0F, -3.0F, 1.0F);
		setRotationAngle(bone40, 0.0F, 0.0F, 0.7854F);
		bone39.addChild(bone40);
		bone40.cubeList.add(new ModelBox(bone40, 96, 6, 8.8995F, -10.8995F, 2.0F, 2, 2, 2, 0.0F, false));

		bone41 = new ModelRenderer(this);
		bone41.setRotationPoint(-7.0F, -19.0F, -7.0F);
		body.addChild(bone41);
		bone41.cubeList.add(new ModelBox(bone41, 77, 1, 13.0F, -3.0F, 3.0F, 2, 3, 2, 0.0F, false));

		bone42 = new ModelRenderer(this);
		bone42.setRotationPoint(0.0F, -3.0F, 1.0F);
		setRotationAngle(bone42, 0.0F, 0.0F, 0.7854F);
		bone41.addChild(bone42);
		bone42.cubeList.add(new ModelBox(bone42, 77, 1, 8.8995F, -10.8995F, 2.0F, 2, 2, 2, 0.0F, false));

		bone43 = new ModelRenderer(this);
		bone43.setRotationPoint(2.0F, -19.0F, 3.0F);
		setRotationAngle(bone43, 0.0F, 0.0F, 0.3491F);
		body.addChild(bone43);
		bone43.cubeList.add(new ModelBox(bone43, 70, 11, -1.0F, -5.0F, -1.0F, 2, 6, 2, 0.0F, false));

		bone44 = new ModelRenderer(this);
		bone44.setRotationPoint(0.0F, -4.0F, 0.0F);
		setRotationAngle(bone44, 0.0F, 0.0F, 0.7854F);
		bone43.addChild(bone44);
		bone44.cubeList.add(new ModelBox(bone44, 70, 11, -1.7071F, -1.7071F, -1.0F, 2, 2, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		body.render(f5);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
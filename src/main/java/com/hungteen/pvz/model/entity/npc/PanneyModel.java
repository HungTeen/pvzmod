package com.hungteen.pvz.model.entity.npc;

import com.hungteen.pvz.entity.npc.PennyEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class PanneyModel extends EntityModel<PennyEntity> {
	private final ModelRenderer car;
	private final ModelRenderer head;
	private final ModelRenderer bone10;
	private final ModelRenderer bone;
	private final ModelRenderer light;
	private final ModelRenderer bone9;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone6;
	private final ModelRenderer bone5;
	private final ModelRenderer body;
	private final ModelRenderer top;
	private final ModelRenderer tail;
	private final ModelRenderer tyres;
	private final ModelRenderer tyre_1;
	private final ModelRenderer bone14;
	private final ModelRenderer bone12;
	private final ModelRenderer bone13;
	private final ModelRenderer bone15;
	private final ModelRenderer bone16;
	private final ModelRenderer bone17;
	private final ModelRenderer tyre_2;
	private final ModelRenderer bone8;
	private final ModelRenderer bone11;
	private final ModelRenderer bone18;
	private final ModelRenderer bone19;
	private final ModelRenderer bone20;
	private final ModelRenderer bone21;
	private final ModelRenderer tyre_3;
	private final ModelRenderer bone22;
	private final ModelRenderer bone23;
	private final ModelRenderer bone24;
	private final ModelRenderer bone25;
	private final ModelRenderer bone26;
	private final ModelRenderer bone27;
	private final ModelRenderer tyre_4;
	private final ModelRenderer bone28;
	private final ModelRenderer bone29;
	private final ModelRenderer bone30;
	private final ModelRenderer bone31;
	private final ModelRenderer bone32;
	private final ModelRenderer bone33;

	public PanneyModel() {
		textureWidth = 128;
		textureHeight = 128;

		car = new ModelRenderer(this);
		car.setRotationPoint(0.0F, 24.0F, 0.0F);
		car.setTextureOffset(0, 0).addBox(-7.5F, -21.0F, -10.0F, 15.0F, 18.0F, 32.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(9.0F, -7.0F, -6.0F);
		car.addChild(head);
		head.setTextureOffset(0, 50).addBox(-16.5F, 2.0F, -17.0F, 15.0F, 2.0F, 13.0F, 0.0F, false);

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(0.0F, 13.0F, 5.0F);
		head.addChild(bone10);
		bone10.setTextureOffset(45, 50).addBox(-16.5F, -27.0F, -14.0F, 15.0F, 5.0F, 5.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-1.0F, -32.0F, -16.0F);
		bone10.addChild(bone);
		setRotationAngle(bone, 0.9275F, 0.0F, 0.0F);
		bone.setTextureOffset(60, 60).addBox(-15.5F, 4.6002F, -6.8003F, 15.0F, 3.0F, 4.0F, 0.0F, false);

		light = new ModelRenderer(this);
		light.setRotationPoint(0.0F, 0.0F, -12.0F);
		bone10.addChild(light);
		light.setTextureOffset(34, 92).addBox(-11.0F, -27.5F, -1.5F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		light.setTextureOffset(45, 60).addBox(-10.5F, -29.0F, -1.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, 1.0F, 0.0F);
		head.addChild(bone9);
		bone9.setTextureOffset(36, 67).addBox(-13.5F, -10.0F, -8.0F, 9.0F, 11.0F, 0.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-7.0F, -15.0F, -12.0F);
		bone9.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.6458F, 0.0F);
		bone2.setTextureOffset(54, 62).addBox(-0.422F, 5.0F, 4.6991F, 0.0F, 11.0F, 5.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(-7.0F, -15.0F, -12.0F);
		bone9.addChild(bone3);
		setRotationAngle(bone3, 0.0F, -0.6458F, 0.0F);
		bone3.setTextureOffset(0, 45).addBox(-2.7725F, 5.0F, 7.1063F, 0.0F, 11.0F, 5.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(bone4);
		bone4.setTextureOffset(0, 67).addBox(-15.0F, -4.5F, -15.0F, 2.0F, 6.0F, 9.0F, 0.0F, false);
		bone4.setTextureOffset(62, 16).addBox(-5.5F, -4.5F, -15.0F, 2.0F, 6.0F, 9.0F, 0.0F, false);
		bone4.setTextureOffset(0, 0).addBox(-12.5F, -1.75F, -17.0F, 7.0F, 3.0F, 9.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone4.addChild(bone6);
		

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(-10.0F, -15.0F, -12.0F);
		bone4.addChild(bone5);
		setRotationAngle(bone5, 0.3944F, 0.0F, 0.0F);
		bone5.setTextureOffset(62, 0).addBox(-2.5F, 10.3111F, -9.708F, 7.0F, 1.0F, 10.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(9.0F, -7.0F, -6.0F);
		car.addChild(body);
		

		top = new ModelRenderer(this);
		top.setRotationPoint(0.0F, 17.0F, -29.0F);
		body.addChild(top);
		top.setTextureOffset(62, 11).addBox(-15.5F, -35.0F, 50.0F, 13.0F, 1.0F, 4.0F, 0.0F, false);
		top.setTextureOffset(88, 77).addBox(-3.5F, -34.0F, 50.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
		top.setTextureOffset(90, 23).addBox(-15.5F, -34.0F, 50.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(10.0F, -5.0F, -38.0F);
		car.addChild(tail);
		tail.setTextureOffset(0, 12).addBox(-16.5F, -12.0F, 60.0F, 12.0F, 12.0F, 1.0F, 0.0F, false);

		tyres = new ModelRenderer(this);
		tyres.setRotationPoint(9.0F, 0.0F, 11.0F);
		car.addChild(tyres);
		

		tyre_1 = new ModelRenderer(this);
		tyre_1.setRotationPoint(0.0F, 0.0F, -29.0F);
		tyres.addChild(tyre_1);
		

		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(0.0F, -10.0F, -68.0F);
		tyre_1.addChild(bone14);
		

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone14.addChild(bone12);
		bone12.setTextureOffset(24, 90).addBox(-2.5F, 6.0F, 68.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone12.setTextureOffset(10, 90).addBox(-2.5F, 2.7574F, 68.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(0.0F, -10.0F, -2.0F);
		bone14.addChild(bone13);
		setRotationAngle(bone13, -1.5708F, 0.0F, 0.0F);
		bone13.setTextureOffset(50, 89).addBox(-2.5F, -71.8787F, 14.8787F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone13.setTextureOffset(60, 89).addBox(-2.5F, -75.1213F, 14.8787F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(0.0F, -12.0F, -70.0F);
		tyre_1.addChild(bone15);
		setRotationAngle(bone15, -0.7854F, 0.0F, 0.0F);
		

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone15.addChild(bone16);
		bone16.setTextureOffset(0, 89).addBox(-2.5F, -45.0122F, 54.9828F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone16.setTextureOffset(88, 67).addBox(-2.5F, -48.2548F, 54.9828F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(0.0F, -10.0F, -2.0F);
		bone15.addChild(bone17);
		setRotationAngle(bone17, -1.5708F, 0.0F, 0.0F);
		bone17.setTextureOffset(83, 88).addBox(-2.5F, -58.8614F, -36.1335F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone17.setTextureOffset(86, 0).addBox(-2.5F, -62.1041F, -36.1335F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		tyre_2 = new ModelRenderer(this);
		tyre_2.setRotationPoint(-15.0F, 0.0F, -29.0F);
		tyres.addChild(tyre_2);
		

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, -10.0F, -68.0F);
		tyre_2.addChild(bone8);
		

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone8.addChild(bone11);
		bone11.setTextureOffset(17, 86).addBox(-2.5F, 6.0F, 68.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone11.setTextureOffset(85, 50).addBox(-2.5F, 2.7574F, 68.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(0.0F, -10.0F, -2.0F);
		bone8.addChild(bone18);
		setRotationAngle(bone18, -1.5708F, 0.0F, 0.0F);
		bone18.setTextureOffset(73, 85).addBox(-2.5F, -71.8787F, 14.8787F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone18.setTextureOffset(43, 85).addBox(-2.5F, -75.1213F, 14.8787F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone19 = new ModelRenderer(this);
		bone19.setRotationPoint(0.0F, -12.0F, -70.0F);
		tyre_2.addChild(bone19);
		setRotationAngle(bone19, -0.7854F, 0.0F, 0.0F);
		

		bone20 = new ModelRenderer(this);
		bone20.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone19.addChild(bone20);
		bone20.setTextureOffset(33, 85).addBox(-2.5F, -45.0122F, 54.9828F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone20.setTextureOffset(84, 20).addBox(-2.5F, -48.2548F, 54.9828F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone21 = new ModelRenderer(this);
		bone21.setRotationPoint(0.0F, -10.0F, -2.0F);
		bone19.addChild(bone21);
		setRotationAngle(bone21, -1.5708F, 0.0F, 0.0F);
		bone21.setTextureOffset(63, 82).addBox(-2.5F, -58.8614F, -36.1335F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone21.setTextureOffset(53, 82).addBox(-2.5F, -62.1041F, -36.1335F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		tyre_3 = new ModelRenderer(this);
		tyre_3.setRotationPoint(-15.0F, 0.0F, 0.0F);
		tyres.addChild(tyre_3);
		

		bone22 = new ModelRenderer(this);
		bone22.setRotationPoint(0.0F, -10.0F, -68.0F);
		tyre_3.addChild(bone22);
		

		bone23 = new ModelRenderer(this);
		bone23.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone22.addChild(bone23);
		bone23.setTextureOffset(10, 82).addBox(-2.5F, 6.0F, 68.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone23.setTextureOffset(0, 82).addBox(-2.5F, 2.7574F, 68.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone24 = new ModelRenderer(this);
		bone24.setRotationPoint(0.0F, -10.0F, -2.0F);
		bone22.addChild(bone24);
		setRotationAngle(bone24, -1.5708F, 0.0F, 0.0F);
		bone24.setTextureOffset(81, 74).addBox(-2.5F, -71.8787F, 14.8787F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone24.setTextureOffset(81, 81).addBox(-2.5F, -75.1213F, 14.8787F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone25 = new ModelRenderer(this);
		bone25.setRotationPoint(0.0F, -12.0F, -70.0F);
		tyre_3.addChild(bone25);
		setRotationAngle(bone25, -0.7854F, 0.0F, 0.0F);
		

		bone26 = new ModelRenderer(this);
		bone26.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone25.addChild(bone26);
		bone26.setTextureOffset(78, 67).addBox(-2.5F, -45.0122F, 54.9828F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone26.setTextureOffset(71, 78).addBox(-2.5F, -48.2548F, 54.9828F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone27 = new ModelRenderer(this);
		bone27.setRotationPoint(0.0F, -10.0F, -2.0F);
		bone25.addChild(bone27);
		setRotationAngle(bone27, -1.5708F, 0.0F, 0.0F);
		bone27.setTextureOffset(46, 78).addBox(-2.5F, -58.8614F, -36.1335F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone27.setTextureOffset(36, 78).addBox(-2.5F, -62.1041F, -36.1335F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		tyre_4 = new ModelRenderer(this);
		tyre_4.setRotationPoint(0.0F, 0.0F, 0.0F);
		tyres.addChild(tyre_4);
		

		bone28 = new ModelRenderer(this);
		bone28.setRotationPoint(0.0F, -10.0F, -68.0F);
		tyre_4.addChild(bone28);
		

		bone29 = new ModelRenderer(this);
		bone29.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone28.addChild(bone29);
		bone29.setTextureOffset(75, 16).addBox(-2.5F, 6.0F, 68.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone29.setTextureOffset(61, 75).addBox(-2.5F, 2.7574F, 68.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone30 = new ModelRenderer(this);
		bone30.setRotationPoint(0.0F, -10.0F, -2.0F);
		bone28.addChild(bone30);
		setRotationAngle(bone30, -1.5708F, 0.0F, 0.0F);
		bone30.setTextureOffset(71, 71).addBox(-2.5F, -71.8787F, 14.8787F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone30.setTextureOffset(64, 67).addBox(-2.5F, -75.1213F, 14.8787F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone31 = new ModelRenderer(this);
		bone31.setRotationPoint(0.0F, -12.0F, -70.0F);
		tyre_4.addChild(bone31);
		setRotationAngle(bone31, -0.7854F, 0.0F, 0.0F);
		

		bone32 = new ModelRenderer(this);
		bone32.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone31.addChild(bone32);
		bone32.setTextureOffset(62, 0).addBox(-2.5F, -45.0122F, 54.9828F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone32.setTextureOffset(20, 25).addBox(-2.5F, -48.2548F, 54.9828F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone33 = new ModelRenderer(this);
		bone33.setRotationPoint(0.0F, -10.0F, -2.0F);
		bone31.addChild(bone33);
		setRotationAngle(bone33, -1.5708F, 0.0F, 0.0F);
		bone33.setTextureOffset(10, 25).addBox(-2.5F, -58.8614F, -36.1335F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone33.setTextureOffset(0, 25).addBox(-2.5F, -62.1041F, -36.1335F, 2.0F, 4.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(PennyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		car.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
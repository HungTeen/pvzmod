package com.hungteen.pvz.client.model.entity.npc;

import com.hungteen.pvz.common.entity.npc.PennyEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class PennyModel extends EntityModel<PennyEntity> {
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

	public PennyModel() {
		texWidth = 128;
		texHeight = 128;

		car = new ModelRenderer(this);
		car.setPos(0.0F, 24.0F, 0.0F);
		car.texOffs(0, 0).addBox(-7.5F, -21.0F, -10.0F, 15.0F, 18.0F, 32.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(9.0F, -7.0F, -6.0F);
		car.addChild(head);
		head.texOffs(0, 50).addBox(-16.5F, 2.0F, -17.0F, 15.0F, 2.0F, 13.0F, 0.0F, false);

		bone10 = new ModelRenderer(this);
		bone10.setPos(0.0F, 13.0F, 5.0F);
		head.addChild(bone10);
		bone10.texOffs(45, 50).addBox(-16.5F, -27.0F, -14.0F, 15.0F, 5.0F, 5.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(-1.0F, -32.0F, -16.0F);
		bone10.addChild(bone);
		setRotationAngle(bone, 0.9275F, 0.0F, 0.0F);
		bone.texOffs(60, 60).addBox(-15.5F, 4.6002F, -6.8003F, 15.0F, 3.0F, 4.0F, 0.0F, false);

		light = new ModelRenderer(this);
		light.setPos(0.0F, 0.0F, -12.0F);
		bone10.addChild(light);
		light.texOffs(34, 92).addBox(-11.0F, -27.5F, -1.5F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		light.texOffs(45, 60).addBox(-10.5F, -29.0F, -1.0F, 3.0F, 2.0F, 3.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setPos(0.0F, 1.0F, 0.0F);
		head.addChild(bone9);
		bone9.texOffs(36, 67).addBox(-13.5F, -10.0F, -8.0F, 9.0F, 11.0F, 0.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(-7.0F, -15.0F, -12.0F);
		bone9.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.6458F, 0.0F);
		bone2.texOffs(54, 62).addBox(-0.422F, 5.0F, 4.6991F, 0.0F, 11.0F, 5.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(-7.0F, -15.0F, -12.0F);
		bone9.addChild(bone3);
		setRotationAngle(bone3, 0.0F, -0.6458F, 0.0F);
		bone3.texOffs(0, 45).addBox(-2.7725F, 5.0F, 7.1063F, 0.0F, 11.0F, 5.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(bone4);
		bone4.texOffs(0, 67).addBox(-15.0F, -4.5F, -15.0F, 2.0F, 6.0F, 9.0F, 0.0F, false);
		bone4.texOffs(62, 16).addBox(-5.5F, -4.5F, -15.0F, 2.0F, 6.0F, 9.0F, 0.0F, false);
		bone4.texOffs(0, 0).addBox(-12.5F, -1.75F, -17.0F, 7.0F, 3.0F, 9.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(0.0F, 0.0F, 0.0F);
		bone4.addChild(bone6);
		

		bone5 = new ModelRenderer(this);
		bone5.setPos(-10.0F, -15.0F, -12.0F);
		bone4.addChild(bone5);
		setRotationAngle(bone5, 0.3944F, 0.0F, 0.0F);
		bone5.texOffs(62, 0).addBox(-2.5F, 10.3111F, -9.708F, 7.0F, 1.0F, 10.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(9.0F, -7.0F, -6.0F);
		car.addChild(body);
		

		top = new ModelRenderer(this);
		top.setPos(0.0F, 17.0F, -29.0F);
		body.addChild(top);
		top.texOffs(62, 11).addBox(-15.5F, -35.0F, 50.0F, 13.0F, 1.0F, 4.0F, 0.0F, false);
		top.texOffs(88, 77).addBox(-3.5F, -34.0F, 50.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);
		top.texOffs(90, 23).addBox(-15.5F, -34.0F, 50.0F, 1.0F, 3.0F, 4.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setPos(10.0F, -5.0F, -38.0F);
		car.addChild(tail);
		tail.texOffs(0, 12).addBox(-16.5F, -12.0F, 60.0F, 12.0F, 12.0F, 1.0F, 0.0F, false);

		tyres = new ModelRenderer(this);
		tyres.setPos(9.0F, 0.0F, 11.0F);
		car.addChild(tyres);
		

		tyre_1 = new ModelRenderer(this);
		tyre_1.setPos(0.0F, 0.0F, -29.0F);
		tyres.addChild(tyre_1);
		

		bone14 = new ModelRenderer(this);
		bone14.setPos(0.0F, -10.0F, -68.0F);
		tyre_1.addChild(bone14);
		

		bone12 = new ModelRenderer(this);
		bone12.setPos(0.0F, 0.0F, 0.0F);
		bone14.addChild(bone12);
		bone12.texOffs(24, 90).addBox(-2.5F, 6.0F, 68.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone12.texOffs(10, 90).addBox(-2.5F, 2.7574F, 68.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone13 = new ModelRenderer(this);
		bone13.setPos(0.0F, -10.0F, -2.0F);
		bone14.addChild(bone13);
		setRotationAngle(bone13, -1.5708F, 0.0F, 0.0F);
		bone13.texOffs(50, 89).addBox(-2.5F, -71.8787F, 14.8787F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone13.texOffs(60, 89).addBox(-2.5F, -75.1213F, 14.8787F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone15 = new ModelRenderer(this);
		bone15.setPos(0.0F, -12.0F, -70.0F);
		tyre_1.addChild(bone15);
		setRotationAngle(bone15, -0.7854F, 0.0F, 0.0F);
		

		bone16 = new ModelRenderer(this);
		bone16.setPos(0.0F, 0.0F, 0.0F);
		bone15.addChild(bone16);
		bone16.texOffs(0, 89).addBox(-2.5F, -45.0122F, 54.9828F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone16.texOffs(88, 67).addBox(-2.5F, -48.2548F, 54.9828F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone17 = new ModelRenderer(this);
		bone17.setPos(0.0F, -10.0F, -2.0F);
		bone15.addChild(bone17);
		setRotationAngle(bone17, -1.5708F, 0.0F, 0.0F);
		bone17.texOffs(83, 88).addBox(-2.5F, -58.8614F, -36.1335F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone17.texOffs(86, 0).addBox(-2.5F, -62.1041F, -36.1335F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		tyre_2 = new ModelRenderer(this);
		tyre_2.setPos(-15.0F, 0.0F, -29.0F);
		tyres.addChild(tyre_2);
		

		bone8 = new ModelRenderer(this);
		bone8.setPos(0.0F, -10.0F, -68.0F);
		tyre_2.addChild(bone8);
		

		bone11 = new ModelRenderer(this);
		bone11.setPos(0.0F, 0.0F, 0.0F);
		bone8.addChild(bone11);
		bone11.texOffs(17, 86).addBox(-2.5F, 6.0F, 68.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone11.texOffs(85, 50).addBox(-2.5F, 2.7574F, 68.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone18 = new ModelRenderer(this);
		bone18.setPos(0.0F, -10.0F, -2.0F);
		bone8.addChild(bone18);
		setRotationAngle(bone18, -1.5708F, 0.0F, 0.0F);
		bone18.texOffs(73, 85).addBox(-2.5F, -71.8787F, 14.8787F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone18.texOffs(43, 85).addBox(-2.5F, -75.1213F, 14.8787F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone19 = new ModelRenderer(this);
		bone19.setPos(0.0F, -12.0F, -70.0F);
		tyre_2.addChild(bone19);
		setRotationAngle(bone19, -0.7854F, 0.0F, 0.0F);
		

		bone20 = new ModelRenderer(this);
		bone20.setPos(0.0F, 0.0F, 0.0F);
		bone19.addChild(bone20);
		bone20.texOffs(33, 85).addBox(-2.5F, -45.0122F, 54.9828F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone20.texOffs(84, 20).addBox(-2.5F, -48.2548F, 54.9828F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone21 = new ModelRenderer(this);
		bone21.setPos(0.0F, -10.0F, -2.0F);
		bone19.addChild(bone21);
		setRotationAngle(bone21, -1.5708F, 0.0F, 0.0F);
		bone21.texOffs(63, 82).addBox(-2.5F, -58.8614F, -36.1335F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone21.texOffs(53, 82).addBox(-2.5F, -62.1041F, -36.1335F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		tyre_3 = new ModelRenderer(this);
		tyre_3.setPos(-15.0F, 0.0F, 0.0F);
		tyres.addChild(tyre_3);
		

		bone22 = new ModelRenderer(this);
		bone22.setPos(0.0F, -10.0F, -68.0F);
		tyre_3.addChild(bone22);
		

		bone23 = new ModelRenderer(this);
		bone23.setPos(0.0F, 0.0F, 0.0F);
		bone22.addChild(bone23);
		bone23.texOffs(10, 82).addBox(-2.5F, 6.0F, 68.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone23.texOffs(0, 82).addBox(-2.5F, 2.7574F, 68.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone24 = new ModelRenderer(this);
		bone24.setPos(0.0F, -10.0F, -2.0F);
		bone22.addChild(bone24);
		setRotationAngle(bone24, -1.5708F, 0.0F, 0.0F);
		bone24.texOffs(81, 74).addBox(-2.5F, -71.8787F, 14.8787F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone24.texOffs(81, 81).addBox(-2.5F, -75.1213F, 14.8787F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone25 = new ModelRenderer(this);
		bone25.setPos(0.0F, -12.0F, -70.0F);
		tyre_3.addChild(bone25);
		setRotationAngle(bone25, -0.7854F, 0.0F, 0.0F);
		

		bone26 = new ModelRenderer(this);
		bone26.setPos(0.0F, 0.0F, 0.0F);
		bone25.addChild(bone26);
		bone26.texOffs(78, 67).addBox(-2.5F, -45.0122F, 54.9828F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone26.texOffs(71, 78).addBox(-2.5F, -48.2548F, 54.9828F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone27 = new ModelRenderer(this);
		bone27.setPos(0.0F, -10.0F, -2.0F);
		bone25.addChild(bone27);
		setRotationAngle(bone27, -1.5708F, 0.0F, 0.0F);
		bone27.texOffs(46, 78).addBox(-2.5F, -58.8614F, -36.1335F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone27.texOffs(36, 78).addBox(-2.5F, -62.1041F, -36.1335F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		tyre_4 = new ModelRenderer(this);
		tyre_4.setPos(0.0F, 0.0F, 0.0F);
		tyres.addChild(tyre_4);
		

		bone28 = new ModelRenderer(this);
		bone28.setPos(0.0F, -10.0F, -68.0F);
		tyre_4.addChild(bone28);
		

		bone29 = new ModelRenderer(this);
		bone29.setPos(0.0F, 0.0F, 0.0F);
		bone28.addChild(bone29);
		bone29.texOffs(75, 16).addBox(-2.5F, 6.0F, 68.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone29.texOffs(61, 75).addBox(-2.5F, 2.7574F, 68.0F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone30 = new ModelRenderer(this);
		bone30.setPos(0.0F, -10.0F, -2.0F);
		bone28.addChild(bone30);
		setRotationAngle(bone30, -1.5708F, 0.0F, 0.0F);
		bone30.texOffs(71, 71).addBox(-2.5F, -71.8787F, 14.8787F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone30.texOffs(64, 67).addBox(-2.5F, -75.1213F, 14.8787F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone31 = new ModelRenderer(this);
		bone31.setPos(0.0F, -12.0F, -70.0F);
		tyre_4.addChild(bone31);
		setRotationAngle(bone31, -0.7854F, 0.0F, 0.0F);
		

		bone32 = new ModelRenderer(this);
		bone32.setPos(0.0F, 0.0F, 0.0F);
		bone31.addChild(bone32);
		bone32.texOffs(62, 0).addBox(-2.5F, -45.0122F, 54.9828F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone32.texOffs(20, 25).addBox(-2.5F, -48.2548F, 54.9828F, 2.0F, 4.0F, 3.0F, 0.0F, false);

		bone33 = new ModelRenderer(this);
		bone33.setPos(0.0F, -10.0F, -2.0F);
		bone31.addChild(bone33);
		setRotationAngle(bone33, -1.5708F, 0.0F, 0.0F);
		bone33.texOffs(10, 25).addBox(-2.5F, -58.8614F, -36.1335F, 2.0F, 4.0F, 3.0F, 0.0F, false);
		bone33.texOffs(0, 25).addBox(-2.5F, -62.1041F, -36.1335F, 2.0F, 4.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(PennyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		car.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
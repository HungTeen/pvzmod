package com.hungteen.pvz.client.model.entity.plant.ice;

import com.hungteen.pvz.common.entity.plant.ice.IceShroomEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class IceShroomModel extends EntityModel<IceShroomEntity> {
	private final ModelRenderer body;
	private final ModelRenderer ice;
	private final ModelRenderer bone43;
	private final ModelRenderer bone44;
	private final ModelRenderer bone41;
	private final ModelRenderer bone42;
	private final ModelRenderer bone39;
	private final ModelRenderer bone40;
	private final ModelRenderer bone35;
	private final ModelRenderer bone36;
	private final ModelRenderer bone33;
	private final ModelRenderer bone34;
	private final ModelRenderer bone31;
	private final ModelRenderer bone32;
	private final ModelRenderer bone27;
	private final ModelRenderer bone28;
	private final ModelRenderer bone29;
	private final ModelRenderer bone30;
	private final ModelRenderer bone25;
	private final ModelRenderer bone26;
	private final ModelRenderer bone23;
	private final ModelRenderer bone24;
	private final ModelRenderer bone21;
	private final ModelRenderer bone22;
	private final ModelRenderer bone19;
	private final ModelRenderer bone20;
	private final ModelRenderer bone17;
	private final ModelRenderer bone18;
	private final ModelRenderer bone15;
	private final ModelRenderer bone16;
	private final ModelRenderer bone13;
	private final ModelRenderer bone14;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;

	public IceShroomModel() {
		texWidth = 128;
		texHeight = 128;

		body = new ModelRenderer(this);
		body.setPos(0.0F, 24.0F, 0.0F);
		body.texOffs(1, 23).addBox(-8.0F, -19.0F, -7.0F, 16.0F, 18.0F, 15.0F, 0.0F, false);
		body.texOffs(1, 2).addBox(-9.0F, -1.0F, -9.0F, 18.0F, 1.0F, 18.0F, 0.0F, false);
		body.texOffs(92, 20).addBox(-8.0F, -19.0F, -8.0F, 16.0F, 1.0F, 1.0F, 0.0F, false);
		body.texOffs(92, 2).addBox(-8.0F, -15.0F, -8.0F, 16.0F, 14.0F, 1.0F, 0.0F, false);
		body.texOffs(77, 21).addBox(-8.0F, -18.0F, -8.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		body.texOffs(84, 21).addBox(7.0F, -18.0F, -8.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		ice = new ModelRenderer(this);
		ice.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(ice);
		

		bone43 = new ModelRenderer(this);
		bone43.setPos(3.0F, -19.0F, 3.0F);
		ice.addChild(bone43);
		setRotationAngle(bone43, 0.0F, 0.0F, 0.3491F);
		bone43.texOffs(1, 60).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		bone44 = new ModelRenderer(this);
		bone44.setPos(0.0F, -4.0F, 0.0F);
		bone43.addChild(bone44);
		setRotationAngle(bone44, 0.0F, 0.0F, 0.7854F);
		bone44.texOffs(1, 70).addBox(-1.7071F, -1.7071F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone41 = new ModelRenderer(this);
		bone41.setPos(8.0F, -19.0F, -3.0F);
		ice.addChild(bone41);
		setRotationAngle(bone41, 0.0F, 0.0F, 0.5236F);
		bone41.texOffs(1, 76).addBox(-2.0F, -3.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone42 = new ModelRenderer(this);
		bone42.setPos(-15.0F, -3.0F, -3.0F);
		bone41.addChild(bone42);
		setRotationAngle(bone42, 0.0F, 0.0F, 0.7854F);
		bone42.texOffs(1, 85).addBox(8.8995F, -10.8995F, 2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone39 = new ModelRenderer(this);
		bone39.setPos(-8.0F, -19.0F, -4.0F);
		ice.addChild(bone39);
		bone39.texOffs(2, 92).addBox(13.0F, -3.0F, 3.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		bone40 = new ModelRenderer(this);
		bone40.setPos(14.0F, -3.0F, 4.0F);
		bone39.addChild(bone40);
		setRotationAngle(bone40, -0.7854F, 0.0F, 0.0F);
		bone40.texOffs(1, 100).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone35 = new ModelRenderer(this);
		bone35.setPos(7.0F, -19.0F, 3.0F);
		ice.addChild(bone35);
		setRotationAngle(bone35, 0.0F, 0.0F, 0.5236F);
		bone35.texOffs(1, 107).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone36 = new ModelRenderer(this);
		bone36.setPos(0.0F, -3.0F, -13.0F);
		bone35.addChild(bone36);
		setRotationAngle(bone36, 0.0F, 0.0F, 0.7854F);
		bone36.texOffs(1, 116).addBox(-1.0F, -1.0F, 12.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone33 = new ModelRenderer(this);
		bone33.setPos(0.0F, -19.0F, -7.0F);
		ice.addChild(bone33);
		bone33.texOffs(14, 61).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone34 = new ModelRenderer(this);
		bone34.setPos(0.0F, -3.0F, -13.0F);
		bone33.addChild(bone34);
		setRotationAngle(bone34, 0.0F, 0.0F, 0.7854F);
		bone34.texOffs(13, 70).addBox(-1.7071F, -1.7071F, 12.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone31 = new ModelRenderer(this);
		bone31.setPos(4.0F, -19.0F, 7.0F);
		ice.addChild(bone31);
		setRotationAngle(bone31, -0.3927F, 0.0F, 0.0F);
		bone31.texOffs(12, 77).addBox(-1.0F, -3.0F, -2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone32 = new ModelRenderer(this);
		bone32.setPos(0.0F, -3.0F, -14.0F);
		bone31.addChild(bone32);
		setRotationAngle(bone32, 0.0F, 0.0F, 0.7854F);
		bone32.texOffs(13, 86).addBox(-1.0F, -1.0F, 12.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone27 = new ModelRenderer(this);
		bone27.setPos(0.0F, -19.0F, 7.0F);
		ice.addChild(bone27);
		bone27.texOffs(13, 92).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone28 = new ModelRenderer(this);
		bone28.setPos(0.0F, -4.0F, 0.0F);
		bone27.addChild(bone28);
		setRotationAngle(bone28, 0.0F, 0.0F, 0.7854F);
		bone28.texOffs(14, 100).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone29 = new ModelRenderer(this);
		bone29.setPos(7.0F, -19.0F, 7.0F);
		ice.addChild(bone29);
		setRotationAngle(bone29, -0.2618F, 0.7854F, 0.0F);
		bone29.texOffs(14, 107).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone30 = new ModelRenderer(this);
		bone30.setPos(0.0F, -3.0F, 0.0F);
		bone29.addChild(bone30);
		setRotationAngle(bone30, 0.0F, 0.0F, 0.7854F);
		bone30.texOffs(14, 116).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone25 = new ModelRenderer(this);
		bone25.setPos(-7.0F, -19.0F, 7.0F);
		ice.addChild(bone25);
		setRotationAngle(bone25, -0.2618F, -0.7854F, 0.0F);
		bone25.texOffs(27, 61).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone26 = new ModelRenderer(this);
		bone26.setPos(0.0F, -3.0F, -13.0F);
		bone25.addChild(bone26);
		setRotationAngle(bone26, 0.0F, 0.0F, 0.7854F);
		bone26.texOffs(27, 70).addBox(-1.0F, -1.0F, 12.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone23 = new ModelRenderer(this);
		bone23.setPos(-4.0F, -19.0F, 7.0F);
		ice.addChild(bone23);
		setRotationAngle(bone23, -0.3927F, 0.0F, 0.0F);
		bone23.texOffs(26, 77).addBox(-1.0F, -3.0F, -2.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone24 = new ModelRenderer(this);
		bone24.setPos(0.0F, -2.0F, -1.0F);
		bone23.addChild(bone24);
		setRotationAngle(bone24, 0.0F, 0.0F, 0.7854F);
		bone24.texOffs(26, 86).addBox(-1.7071F, -1.7071F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone21 = new ModelRenderer(this);
		bone21.setPos(-6.0F, -19.0F, -1.0F);
		ice.addChild(bone21);
		bone21.texOffs(26, 93).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		bone22 = new ModelRenderer(this);
		bone22.setPos(0.0F, -3.0F, 1.0F);
		bone21.addChild(bone22);
		setRotationAngle(bone22, -0.7854F, 0.0F, 0.0F);
		bone22.texOffs(26, 102).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone19 = new ModelRenderer(this);
		bone19.setPos(-7.0F, -19.0F, 3.0F);
		ice.addChild(bone19);
		setRotationAngle(bone19, 0.0F, 0.0F, -0.5236F);
		bone19.texOffs(26, 110).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone20 = new ModelRenderer(this);
		bone20.setPos(0.0F, -3.0F, 0.0F);
		bone19.addChild(bone20);
		setRotationAngle(bone20, 0.0F, 0.0F, 0.7854F);
		bone20.texOffs(24, 120).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone17 = new ModelRenderer(this);
		bone17.setPos(-7.0F, -19.0F, -3.0F);
		ice.addChild(bone17);
		setRotationAngle(bone17, 0.0F, 0.0F, -0.5236F);
		bone17.texOffs(40, 60).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone18 = new ModelRenderer(this);
		bone18.setPos(0.0F, -3.0F, 0.0F);
		bone17.addChild(bone18);
		setRotationAngle(bone18, 0.0F, 0.0F, 0.7854F);
		bone18.texOffs(40, 70).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone15 = new ModelRenderer(this);
		bone15.setPos(-3.0F, -19.0F, 2.0F);
		ice.addChild(bone15);
		setRotationAngle(bone15, 0.0F, 0.0F, -0.3491F);
		bone15.texOffs(40, 76).addBox(-1.0F, -5.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		bone16 = new ModelRenderer(this);
		bone16.setPos(0.0F, -5.0F, 1.0F);
		bone15.addChild(bone16);
		setRotationAngle(bone16, 0.0F, 0.0F, 0.7854F);
		bone16.texOffs(39, 88).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone13 = new ModelRenderer(this);
		bone13.setPos(0.0F, -19.0F, -1.0F);
		ice.addChild(bone13);
		bone13.texOffs(38, 96).addBox(-2.0F, -6.0F, -1.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		bone14 = new ModelRenderer(this);
		bone14.setPos(0.0F, -6.0F, 1.0F);
		bone13.addChild(bone14);
		setRotationAngle(bone14, 0.0F, 0.0F, 0.7854F);
		bone14.texOffs(37, 109).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		bone11 = new ModelRenderer(this);
		bone11.setPos(3.0F, -19.0F, -4.0F);
		ice.addChild(bone11);
		setRotationAngle(bone11, 0.0F, 0.0F, 0.3491F);
		bone11.texOffs(37, 119).addBox(-1.0F, -5.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		bone12 = new ModelRenderer(this);
		bone12.setPos(0.0F, -5.0F, 1.0F);
		bone11.addChild(bone12);
		setRotationAngle(bone12, 0.0F, 0.0F, 0.7854F);
		bone12.texOffs(55, 61).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setPos(-3.0F, -19.0F, -4.0F);
		ice.addChild(bone9);
		setRotationAngle(bone9, 0.0F, 0.0F, -0.3491F);
		bone9.texOffs(54, 68).addBox(-1.0F, -5.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		bone10 = new ModelRenderer(this);
		bone10.setPos(0.0F, -5.0F, 1.0F);
		bone9.addChild(bone10);
		setRotationAngle(bone10, 0.0F, 0.0F, 0.7854F);
		bone10.texOffs(52, 79).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setPos(4.0F, -19.0F, -7.0F);
		ice.addChild(bone7);
		setRotationAngle(bone7, 0.3927F, 0.0F, 0.0F);
		bone7.texOffs(54, 88).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setPos(0.0F, -3.0F, 1.0F);
		bone7.addChild(bone8);
		setRotationAngle(bone8, 0.0F, 0.0F, 0.7854F);
		bone8.texOffs(1, 123).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(-4.0F, -19.0F, -7.0F);
		ice.addChild(bone5);
		setRotationAngle(bone5, 0.3927F, 0.0F, 0.0F);
		bone5.texOffs(57, 99).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(0.0F, -3.0F, 1.0F);
		bone5.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 0.0F, 0.7854F);
		bone6.texOffs(57, 109).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(7.0F, -19.0F, -7.0F);
		ice.addChild(bone3);
		setRotationAngle(bone3, 0.2618F, -0.7854F, 0.0F);
		bone3.texOffs(51, 121).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(0.0F, -3.0F, 0.0F);
		bone3.addChild(bone4);
		setRotationAngle(bone4, 0.0F, 0.0F, 0.7854F);
		bone4.texOffs(57, 115).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(-7.0F, -19.0F, -7.0F);
		ice.addChild(bone);
		setRotationAngle(bone, 0.2618F, 0.7854F, 0.0F);
		bone.texOffs(80, 5).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, -3.0F, 0.0F);
		bone.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.7854F);
		bone2.texOffs(80, 14).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(IceShroomEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
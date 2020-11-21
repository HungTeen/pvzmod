package com.hungteen.pvz.model.entity.plant.light;

import com.hungteen.pvz.entity.plant.light.TwinSunFlowerEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class TwinSunFlowerModel extends EntityModel<TwinSunFlowerEntity> {
	private final ModelRenderer total;
	private final ModelRenderer YEZI_4;
	private final ModelRenderer YEZI_3;
	private final ModelRenderer YEZI_1;
	private final ModelRenderer YEZI_2;
	private final ModelRenderer left_guy;
	private final ModelRenderer head2;
	private final ModelRenderer HUABAN7;
	private final ModelRenderer HUABAN8;
	private final ModelRenderer HUABAN9;
	private final ModelRenderer HUABAN10;
	private final ModelRenderer HUABAN11;
	private final ModelRenderer HUABAN12;
	private final ModelRenderer body2;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer cube_r1;
	private final ModelRenderer left_hand2;
	private final ModelRenderer right_hand2;
	private final ModelRenderer left_guy2;
	private final ModelRenderer body3;
	private final ModelRenderer right_hand3;
	private final ModelRenderer left_hand3;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer cube_r2;
	private final ModelRenderer head3;
	private final ModelRenderer HUABAN2;
	private final ModelRenderer HUABAN3;
	private final ModelRenderer HUABAN4;
	private final ModelRenderer HUABAN5;
	private final ModelRenderer HUABAN6;
	private final ModelRenderer HUABAN13;

	public TwinSunFlowerModel() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		YEZI_4 = new ModelRenderer(this);
		YEZI_4.setRotationPoint(-2.0F, -1.0F, 0.0F);
		total.addChild(YEZI_4);
		YEZI_4.setTextureOffset(78, 26).addBox(-11.0F, -1.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
		YEZI_4.setTextureOffset(114, 28).addBox(-3.0F, 0.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);

		YEZI_3 = new ModelRenderer(this);
		YEZI_3.setRotationPoint(0.0F, 0.0F, -2.0F);
		total.addChild(YEZI_3);
		YEZI_3.setTextureOffset(114, 19).addBox(-2.0F, -1.0F, -3.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		YEZI_3.setTextureOffset(0, 36).addBox(-4.0F, -2.0F, -11.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);

		YEZI_1 = new ModelRenderer(this);
		YEZI_1.setRotationPoint(2.0F, 0.0F, 0.0F);
		total.addChild(YEZI_1);
		YEZI_1.setTextureOffset(114, 0).addBox(0.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		YEZI_1.setTextureOffset(79, 0).addBox(3.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);

		YEZI_2 = new ModelRenderer(this);
		YEZI_2.setRotationPoint(0.0F, 0.0F, 2.0F);
		total.addChild(YEZI_2);
		YEZI_2.setTextureOffset(78, 12).addBox(-4.0F, -2.0F, 3.0F, 8.0F, 2.0F, 8.0F, 0.0F, false);
		YEZI_2.setTextureOffset(114, 9).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);

		left_guy = new ModelRenderer(this);
		left_guy.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(left_guy);
		

		head2 = new ModelRenderer(this);
		head2.setRotationPoint(16.0F, -46.0F, 0.0F);
		left_guy.addChild(head2);
		head2.setTextureOffset(73, 100).addBox(-11.0F, -15.0F, -5.99F, 22.0F, 21.0F, 4.0F, -0.02F, false);
		head2.setTextureOffset(0, 123).addBox(-5.0F, 5.9F, -6.0F, 10.0F, 1.0F, 4.0F, -0.02F, false);

		HUABAN7 = new ModelRenderer(this);
		HUABAN7.setRotationPoint(0.0F, -3.0F, 0.5F);
		head2.addChild(HUABAN7);
		setRotationAngle(HUABAN7, 0.0F, 0.0F, -0.2618F);
		HUABAN7.setTextureOffset(0, 62).addBox(-3.0F, -18.0F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN7.setTextureOffset(29, 63).addBox(-3.0F, 9.8564F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN7.setTextureOffset(42, 36).addBox(10.9282F, -4.0718F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN7.setTextureOffset(73, 73).addBox(-16.9282F, -4.0718F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);

		HUABAN8 = new ModelRenderer(this);
		HUABAN8.setRotationPoint(0.0F, 0.0F, 0.0F);
		HUABAN7.addChild(HUABAN8);
		setRotationAngle(HUABAN8, 0.0F, 0.0F, -0.5236F);
		HUABAN8.setTextureOffset(0, 62).addBox(-2.4641F, -17.8564F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN8.setTextureOffset(29, 63).addBox(-2.4641F, 10.0F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN8.setTextureOffset(42, 36).addBox(11.4641F, -3.9282F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN8.setTextureOffset(73, 73).addBox(-16.3923F, -3.9282F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);

		HUABAN9 = new ModelRenderer(this);
		HUABAN9.setRotationPoint(0.0F, 0.0F, 0.0F);
		HUABAN8.addChild(HUABAN9);
		setRotationAngle(HUABAN9, 0.0F, 0.0F, -0.5236F);
		HUABAN9.setTextureOffset(0, 62).addBox(-2.0718F, -17.4641F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN9.setTextureOffset(29, 63).addBox(-2.0718F, 10.3923F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN9.setTextureOffset(42, 36).addBox(11.8564F, -3.5359F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN9.setTextureOffset(73, 73).addBox(-16.0F, -3.5359F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);

		HUABAN10 = new ModelRenderer(this);
		HUABAN10.setRotationPoint(0.0F, -3.0F, 0.0F);
		head2.addChild(HUABAN10);
		HUABAN10.setTextureOffset(0, 62).addBox(-4.0F, -16.0F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN10.setTextureOffset(29, 63).addBox(-4.0F, 9.8564F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN10.setTextureOffset(42, 36).addBox(10.9282F, -5.0718F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
		HUABAN10.setTextureOffset(73, 73).addBox(-14.9282F, -5.0718F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		HUABAN11 = new ModelRenderer(this);
		HUABAN11.setRotationPoint(0.0F, 0.0F, 0.0F);
		HUABAN10.addChild(HUABAN11);
		setRotationAngle(HUABAN11, 0.0F, 0.0F, -0.5236F);
		HUABAN11.setTextureOffset(0, 62).addBox(-3.4641F, -15.8564F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN11.setTextureOffset(29, 63).addBox(-3.4641F, 10.0F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN11.setTextureOffset(42, 36).addBox(11.4641F, -4.9282F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
		HUABAN11.setTextureOffset(73, 73).addBox(-14.3923F, -4.9282F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		HUABAN12 = new ModelRenderer(this);
		HUABAN12.setRotationPoint(0.0F, 0.0F, 0.0F);
		HUABAN11.addChild(HUABAN12);
		setRotationAngle(HUABAN12, 0.0F, 0.0F, -0.5236F);
		HUABAN12.setTextureOffset(0, 62).addBox(-3.0718F, -15.4641F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN12.setTextureOffset(29, 63).addBox(-3.0718F, 10.3923F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN12.setTextureOffset(42, 36).addBox(11.8564F, -4.5359F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
		HUABAN12.setTextureOffset(73, 73).addBox(-14.0F, -4.5359F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		body2 = new ModelRenderer(this);
		body2.setRotationPoint(0.0F, -1.0F, 0.0F);
		left_guy.addChild(body2);
		setRotationAngle(body2, 0.0F, 0.0F, 0.5236F);
		body2.setTextureOffset(112, 43).addBox(-2.0F, -31.0F, -2.0F, 4.0F, 32.0F, 4.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -31.0F, 0.0F);
		body2.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, -0.5236F);
		bone.setTextureOffset(0, 0).addBox(-2.2679F, -17.0F, -2.0F, 4.0F, 18.0F, 4.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, -18.0F, 0.0F);
		bone.addChild(bone2);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setRotationPoint(-0.2679F, 2.3512F, 0.5254F);
		bone2.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.7418F, 0.0F, 0.0F);
		cube_r1.setTextureOffset(0, 0).addBox(-2.0F, -5.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);

		left_hand2 = new ModelRenderer(this);
		left_hand2.setRotationPoint(2.134F, -28.5F, 0.0F);
		body2.addChild(left_hand2);
		setRotationAngle(left_hand2, 0.0F, 0.0F, -0.3491F);
		left_hand2.setTextureOffset(0, 0).addBox(2.0563F, -1.2443F, -1.0F, 6.0F, 3.0F, 2.0F, 0.0F, false);
		left_hand2.setTextureOffset(0, 0).addBox(-3.0197F, -0.3761F, 0.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);

		right_hand2 = new ModelRenderer(this);
		right_hand2.setRotationPoint(-1.634F, -28.634F, 0.0F);
		body2.addChild(right_hand2);
		setRotationAngle(right_hand2, 0.0F, 0.0F, -0.0873F);
		right_hand2.setTextureOffset(0, 0).addBox(-8.4088F, -1.1233F, -1.0F, 6.0F, 3.0F, 2.0F, 0.0F, false);
		right_hand2.setTextureOffset(0, 0).addBox(-2.4088F, -0.1233F, 0.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		left_guy2 = new ModelRenderer(this);
		left_guy2.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(left_guy2);
		

		body3 = new ModelRenderer(this);
		body3.setRotationPoint(0.0F, -1.0F, 0.0F);
		left_guy2.addChild(body3);
		setRotationAngle(body3, 0.0F, 0.0F, -0.5236F);
		body3.setTextureOffset(112, 43).addBox(-2.0F, -27.0F, -2.0F, 4.0F, 28.0F, 4.0F, 0.0F, false);

		right_hand3 = new ModelRenderer(this);
		right_hand3.setRotationPoint(-1.7679F, -19.4019F, 0.0F);
		body3.addChild(right_hand3);
		setRotationAngle(right_hand3, 0.0F, 0.0F, 0.3054F);
		right_hand3.setTextureOffset(0, 0).addBox(-7.9848F, -0.8264F, -1.0F, 6.0F, 3.0F, 2.0F, 0.0F, false);
		right_hand3.setTextureOffset(0, 0).addBox(-1.9848F, 0.1736F, 0.0F, 3.0F, 1.0F, 1.0F, 0.0F, false);

		left_hand3 = new ModelRenderer(this);
		left_hand3.setRotationPoint(2.2321F, -19.4019F, 0.0F);
		body3.addChild(left_hand3);
		setRotationAngle(left_hand3, 0.0F, 0.0F, -0.0436F);
		left_hand3.setTextureOffset(0, 0).addBox(1.9848F, -0.8264F, -1.0F, 6.0F, 3.0F, 2.0F, 0.0F, false);
		left_hand3.setTextureOffset(0, 0).addBox(-2.1063F, 0.2155F, 0.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, -27.0F, 0.0F);
		body3.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.0F, 0.2618F);
		bone3.setTextureOffset(0, 0).addBox(-1.9319F, -7.4824F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, 0.0F, 0.0F);
		bone3.addChild(bone4);
		

		cube_r2 = new ModelRenderer(this);
		cube_r2.setRotationPoint(0.0681F, -8.1895F, -0.1213F);
		bone4.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.7854F, 0.0F, 0.0F);
		cube_r2.setTextureOffset(23, 0).addBox(-2.0F, -4.0F, -3.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		head3 = new ModelRenderer(this);
		head3.setRotationPoint(-16.0F, -33.0F, 0.0F);
		left_guy2.addChild(head3);
		head3.setTextureOffset(73, 100).addBox(-11.0F, -15.0F, -5.99F, 22.0F, 21.0F, 4.0F, -0.02F, false);
		head3.setTextureOffset(0, 123).addBox(-5.0F, 5.9F, -6.0F, 10.0F, 1.0F, 4.0F, -0.02F, false);

		HUABAN2 = new ModelRenderer(this);
		HUABAN2.setRotationPoint(0.0F, -3.0F, 0.5F);
		head3.addChild(HUABAN2);
		setRotationAngle(HUABAN2, 0.0F, 0.0F, -0.2618F);
		HUABAN2.setTextureOffset(0, 62).addBox(-3.0F, -18.0F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN2.setTextureOffset(29, 63).addBox(-3.0F, 9.8564F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN2.setTextureOffset(42, 36).addBox(10.9282F, -4.0718F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN2.setTextureOffset(73, 73).addBox(-16.9282F, -4.0718F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);

		HUABAN3 = new ModelRenderer(this);
		HUABAN3.setRotationPoint(0.0F, 0.0F, 0.0F);
		HUABAN2.addChild(HUABAN3);
		setRotationAngle(HUABAN3, 0.0F, 0.0F, -0.5236F);
		HUABAN3.setTextureOffset(0, 62).addBox(-2.4641F, -17.8564F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN3.setTextureOffset(29, 63).addBox(-2.4641F, 10.0F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN3.setTextureOffset(42, 36).addBox(11.4641F, -3.9282F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN3.setTextureOffset(73, 73).addBox(-16.3923F, -3.9282F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);

		HUABAN4 = new ModelRenderer(this);
		HUABAN4.setRotationPoint(0.0F, 0.0F, 0.0F);
		HUABAN3.addChild(HUABAN4);
		setRotationAngle(HUABAN4, 0.0F, 0.0F, -0.5236F);
		HUABAN4.setTextureOffset(0, 62).addBox(-2.0718F, -17.4641F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN4.setTextureOffset(29, 63).addBox(-2.0718F, 10.3923F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN4.setTextureOffset(42, 36).addBox(11.8564F, -3.5359F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);
		HUABAN4.setTextureOffset(73, 73).addBox(-16.0F, -3.5359F, -6.0F, 6.0F, 6.0F, 3.0F, 0.0F, false);

		HUABAN5 = new ModelRenderer(this);
		HUABAN5.setRotationPoint(0.0F, -3.0F, 0.0F);
		head3.addChild(HUABAN5);
		HUABAN5.setTextureOffset(0, 62).addBox(-4.0F, -16.0F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN5.setTextureOffset(29, 63).addBox(-4.0F, 9.8564F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN5.setTextureOffset(42, 36).addBox(10.9282F, -5.0718F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
		HUABAN5.setTextureOffset(73, 73).addBox(-14.9282F, -5.0718F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		HUABAN6 = new ModelRenderer(this);
		HUABAN6.setRotationPoint(0.0F, 0.0F, 0.0F);
		HUABAN5.addChild(HUABAN6);
		setRotationAngle(HUABAN6, 0.0F, 0.0F, -0.5236F);
		HUABAN6.setTextureOffset(0, 62).addBox(-3.4641F, -15.8564F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN6.setTextureOffset(29, 63).addBox(-3.4641F, 10.0F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN6.setTextureOffset(42, 36).addBox(11.4641F, -4.9282F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
		HUABAN6.setTextureOffset(73, 73).addBox(-14.3923F, -4.9282F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		HUABAN13 = new ModelRenderer(this);
		HUABAN13.setRotationPoint(0.0F, 0.0F, 0.0F);
		HUABAN6.addChild(HUABAN13);
		setRotationAngle(HUABAN13, 0.0F, 0.0F, -0.5236F);
		HUABAN13.setTextureOffset(0, 62).addBox(-3.0718F, -15.4641F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN13.setTextureOffset(29, 63).addBox(-3.0718F, 10.3923F, -6.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		HUABAN13.setTextureOffset(42, 36).addBox(11.8564F, -4.5359F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
		HUABAN13.setTextureOffset(73, 73).addBox(-14.0F, -4.5359F, -6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(TwinSunFlowerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		total.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
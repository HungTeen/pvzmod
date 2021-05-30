package com.hungteen.pvz.client.model.entity.misc;

import com.hungteen.pvz.common.entity.misc.LawnMowerEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class LawnMowerModel extends EntityModel<LawnMowerEntity> {
	private final ModelRenderer total;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer bone3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer Thing;
	private final ModelRenderer Wheel;
	private final ModelRenderer wheel1;
	private final ModelRenderer wheel2;
	private final ModelRenderer wheel3;
	private final ModelRenderer wheel4;
	private final ModelRenderer Handle;
	private final ModelRenderer cube_r5;
	private final ModelRenderer Wire;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer bone2;
	private final ModelRenderer Cuter;
	private final ModelRenderer cube_r9;

	public LawnMowerModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(0, 23).addBox(-5.0F, -6.0F, -8.0F, 10.0F, 5.0F, 16.0F, 0.0F, false);
		total.texOffs(0, 0).addBox(-5.0F, -6.0F, -9.0F, 10.0F, 5.0F, 18.0F, -0.2F, false);
		total.texOffs(45, 45).addBox(-5.0F, -6.0F, -9.5F, 10.0F, 5.0F, 2.0F, -0.4F, false);
		total.texOffs(38, 0).addBox(-3.5F, -7.8F, -3.0F, 7.0F, 2.0F, 8.0F, 0.0F, false);
		total.texOffs(36, 23).addBox(-3.5F, -8.7F, -3.0F, 7.0F, 3.0F, 8.0F, -0.2F, false);
		total.texOffs(38, 10).addBox(-3.0F, -9.8F, -2.0F, 6.0F, 2.0F, 6.0F, -0.2F, false);
		total.texOffs(0, 44).addBox(-3.5F, -10.8F, -2.5F, 7.0F, 2.0F, 7.0F, -0.3F, false);
		total.texOffs(26, 52).addBox(-6.0F, -2.8F, -0.5F, 12.0F, 1.0F, 2.0F, -0.2F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(-2.0F, -11.0F, 8.0F);
		total.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.7418F, 0.0F, 0.0F);
		cube_r1.texOffs(10, 16).addBox(-1.5F, -1.5F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		cube_r1.texOffs(0, 8).addBox(-1.5F, -1.5F, 0.0F, 1.0F, 1.0F, 2.0F, -0.15F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(-5.0F, -2.0F, 2.0F);
		total.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.3491F, 0.0F, 0.0F);
		cube_r2.texOffs(36, 36).addBox(-1.0F, -0.5F, -1.0F, 12.0F, 1.0F, 2.0F, -0.2F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(-5.0F, -2.0F, -1.0F);
		total.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.3491F, 0.0F, 0.0F);
		cube_r3.texOffs(50, 42).addBox(-1.0F, -0.5F, -1.0F, 12.0F, 1.0F, 2.0F, -0.2F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(-5.0F, -6.0F, 1.0F);
		total.addChild(bone3);
		bone3.texOffs(0, 26).addBox(0.5F, -1.0F, -0.5F, 1.0F, 1.0F, 1.0F, 0.2F, false);
		bone3.texOffs(0, 11).addBox(0.5F, -0.8F, -2.0F, 2.0F, 1.0F, 1.0F, -0.2F, false);
		bone3.texOffs(8, 4).addBox(-0.5F, -0.8F, 1.3F, 2.0F, 1.0F, 1.0F, -0.28F, false);
		bone3.texOffs(8, 8).addBox(-1.0F, -0.8F, 1.3F, 2.0F, 1.0F, 1.0F, -0.18F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(-1.0F, 0.0F, -1.0F);
		bone3.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 1.5708F, 0.0F);
		cube_r4.texOffs(0, 16).addBox(-3.0F, -0.8F, 1.5F, 4.0F, 1.0F, 1.0F, -0.28F, false);

		Thing = new ModelRenderer(this);
		Thing.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(Thing);
		Thing.texOffs(52, 52).addBox(-6.5F, -2.5F, 6.0F, 13.0F, 1.0F, 1.0F, 0.0F, false);
		Thing.texOffs(36, 34).addBox(-6.5F, -2.5F, -7.0F, 13.0F, 1.0F, 1.0F, 0.0F, false);

		Wheel = new ModelRenderer(this);
		Wheel.setPos(0.0F, 0.0F, 0.0F);
		Thing.addChild(Wheel);
		

		wheel1 = new ModelRenderer(this);
		wheel1.setPos(-6.0F, -2.0F, -6.5F);
		Wheel.addChild(wheel1);
		wheel1.texOffs(31, 55).addBox(-0.3F, -2.5F, -1.5F, 1.0F, 5.0F, 3.0F, 0.0F, false);
		wheel1.texOffs(49, 54).addBox(-0.3F, -2.0F, -2.5F, 1.0F, 4.0F, 5.0F, 0.0F, false);

		wheel2 = new ModelRenderer(this);
		wheel2.setPos(6.0F, -2.0F, -6.5F);
		Wheel.addChild(wheel2);
		wheel2.texOffs(0, 23).addBox(-0.8F, -2.0F, -2.5F, 1.0F, 4.0F, 5.0F, 0.0F, false);
		wheel2.texOffs(38, 0).addBox(-0.8F, -2.5F, -1.5F, 1.0F, 5.0F, 3.0F, 0.0F, false);

		wheel3 = new ModelRenderer(this);
		wheel3.setPos(-6.0F, -2.0F, 6.5F);
		Wheel.addChild(wheel3);
		wheel3.texOffs(0, 55).addBox(-0.3F, -2.0F, -2.5F, 1.0F, 4.0F, 5.0F, 0.0F, false);
		wheel3.texOffs(39, 55).addBox(-0.3F, -2.5F, -1.5F, 1.0F, 5.0F, 3.0F, 0.0F, false);

		wheel4 = new ModelRenderer(this);
		wheel4.setPos(6.0F, -2.0F, 6.5F);
		Wheel.addChild(wheel4);
		wheel4.texOffs(15, 53).addBox(-0.8F, -2.0F, -2.5F, 1.0F, 4.0F, 5.0F, 0.0F, false);
		wheel4.texOffs(36, 23).addBox(-0.8F, -2.5F, -1.5F, 1.0F, 5.0F, 3.0F, 0.0F, false);

		Handle = new ModelRenderer(this);
		Handle.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(Handle);
		

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(5.0F, -6.0F, 7.0F);
		Handle.addChild(cube_r5);
		setRotationAngle(cube_r5, -0.1745F, 0.0F, 0.0F);
		cube_r5.texOffs(52, 39).addBox(-9.5F, -11.0F, 0.0F, 9.0F, 1.0F, 1.0F, -0.2F, false);
		cube_r5.texOffs(14, 0).addBox(-1.5F, -11.0F, 0.0F, 1.0F, 12.0F, 1.0F, -0.2F, false);
		cube_r5.texOffs(14, 0).addBox(-9.5F, -11.0F, 0.0F, 1.0F, 12.0F, 1.0F, -0.2F, false);
		cube_r5.texOffs(0, 53).addBox(-9.5F, -6.0F, 0.0F, 9.0F, 1.0F, 1.0F, -0.2F, false);

		Wire = new ModelRenderer(this);
		Wire.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(Wire);
		

		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(-1.0F, -9.0F, 7.0F);
		Wire.addChild(cube_r6);
		setRotationAngle(cube_r6, 2.0944F, 0.0F, 0.0F);
		cube_r6.texOffs(8, 0).addBox(-1.0F, -3.0F, -0.4F, 1.0F, 3.0F, 1.0F, -0.4F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setPos(-1.0F, -11.0F, 9.0F);
		Wire.addChild(cube_r7);
		setRotationAngle(cube_r7, 2.4435F, 0.0F, 0.0F);
		cube_r7.texOffs(12, 27).addBox(-1.0F, -3.7F, -0.01F, 1.0F, 2.0F, 1.0F, -0.4F, false);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setPos(-1.0F, -12.0F, 9.0F);
		Wire.addChild(cube_r8);
		setRotationAngle(cube_r8, 2.6616F, 0.0F, 0.0F);
		cube_r8.texOffs(14, 14).addBox(-1.0F, -3.3F, 0.0F, 1.0F, 3.0F, 1.0F, -0.4F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(bone2);
		bone2.texOffs(0, 35).addBox(-3.5F, -2.3F, -4.0F, 7.0F, 2.0F, 1.0F, 0.0F, false);
		bone2.texOffs(0, 32).addBox(-3.5F, -2.3F, 3.0F, 7.0F, 2.0F, 1.0F, 0.0F, false);
		bone2.texOffs(21, 44).addBox(-3.0F, -1.8F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);
		bone2.texOffs(0, 8).addBox(-4.0F, -2.3F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);
		bone2.texOffs(0, 0).addBox(3.0F, -2.3F, -3.0F, 1.0F, 2.0F, 6.0F, 0.0F, false);

		Cuter = new ModelRenderer(this);
		Cuter.setPos(0.0F, 0.0F, 0.0F);
		bone2.addChild(Cuter);
		Cuter.texOffs(0, 23).addBox(-0.5F, -2.3F, -0.5F, 1.0F, 2.0F, 1.0F, -0.2F, false);
		Cuter.texOffs(0, 3).addBox(-0.5F, -1.3F, -1.0F, 1.0F, 1.0F, 2.0F, -0.3F, false);
		Cuter.texOffs(56, 10).addBox(-1.0F, -2.0F, -2.2F, 2.0F, 2.0F, 2.0F, -0.5F, false);
		Cuter.texOffs(7, 55).addBox(-1.0F, -2.0F, 0.2F, 2.0F, 2.0F, 2.0F, -0.5F, false);
		Cuter.texOffs(7, 23).addBox(-2.2F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, -0.5F, false);
		Cuter.texOffs(8, 10).addBox(0.2F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, -0.5F, false);

		cube_r9 = new ModelRenderer(this);
		cube_r9.setPos(0.0F, 0.0F, 0.0F);
		Cuter.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.0F, -1.5708F, 0.0F);
		cube_r9.texOffs(0, 0).addBox(-0.5F, -1.3F, -1.0F, 1.0F, 1.0F, 2.0F, -0.3F, false);
	}

	@Override
	public void setupAnim(LawnMowerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if(entity.isStartRun()) {
			float speed = 1F;
			this.wheel1.xRot = ageInTicks * speed;
			this.wheel2.xRot = ageInTicks * speed;
			this.wheel3.xRot = ageInTicks * speed;
			this.wheel4.xRot = ageInTicks * speed;
		} else {
			this.wheel1.xRot = 0;
			this.wheel2.xRot = 0;
			this.wheel3.xRot = 0;
			this.wheel4.xRot = 0;
		}
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		total.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
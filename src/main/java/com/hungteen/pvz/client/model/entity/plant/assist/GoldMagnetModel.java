package com.hungteen.pvz.client.model.entity.plant.assist;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.assist.GoldMagnetEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class GoldMagnetModel extends PVZPlantModel<GoldMagnetEntity> {
	private final ModelRenderer total;
	private final ModelRenderer plate;
	private final ModelRenderer bone3;
	private final ModelRenderer cube_r1;
	private final ModelRenderer bone4;
	private final ModelRenderer bone15;
	private final ModelRenderer bone16;
	private final ModelRenderer bone17;
	private final ModelRenderer bone18;
	private final ModelRenderer bone19;
	private final ModelRenderer bone20;
	private final ModelRenderer magnet;
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
	private final ModelRenderer face;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer elect;
	private final ModelRenderer base;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer leave1;

	public GoldMagnetModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(0, 52).addBox(-0.9443F, -8.2791F, -1.0F, 2.0F, 3.0F, 2.0F, -0.28F, false);

		plate = new ModelRenderer(this);
		plate.setPos(0.0F, -7.0F, 0.0F);
		total.addChild(plate);
		plate.texOffs(8, 59).addBox(-2.0F, -1.5F, -2.0F, 4.0F, 1.0F, 4.0F, -0.3F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, 0.0F, 0.0F);
		plate.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 1.5708F, 0.0F);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(-1.75F, -0.1402F, 1.5902F);
		bone3.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.5236F, 0.0F, 0.0F);
		cube_r1.texOffs(9, 56).addBox(-0.25F, -1.2165F, 0.125F, 4.0F, 1.0F, 1.0F, -0.3F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(0.0F, 0.0F, 0.0F);
		plate.addChild(bone4);
		setRotationAngle(bone4, -0.5236F, 1.5708F, 0.0F);
		bone4.texOffs(9, 53).addBox(-2.0F, -0.5428F, -2.5722F, 4.0F, 1.0F, 1.0F, -0.3F, false);

		bone15 = new ModelRenderer(this);
		bone15.setPos(-1.75F, -0.75F, -1.0F);
		plate.addChild(bone15);
		setRotationAngle(bone15, 0.5236F, -0.5236F, -0.5236F);
		bone15.texOffs(53, 37).addBox(-2.75F, -0.75F, -1.5F, 3.0F, 1.0F, 2.0F, -0.35F, false);

		bone16 = new ModelRenderer(this);
		bone16.setPos(1.75F, -0.75F, -1.0F);
		plate.addChild(bone16);
		setRotationAngle(bone16, 0.5236F, 0.5236F, 0.5236F);
		bone16.texOffs(42, 37).addBox(-0.25F, -0.75F, -1.5F, 3.0F, 1.0F, 2.0F, -0.35F, false);

		bone17 = new ModelRenderer(this);
		bone17.setPos(-1.75F, -0.75F, 1.0F);
		plate.addChild(bone17);
		setRotationAngle(bone17, -0.5236F, 0.5236F, -0.5236F);
		bone17.texOffs(32, 37).addBox(-2.75F, -0.75F, -0.5F, 3.0F, 1.0F, 2.0F, -0.35F, false);

		bone18 = new ModelRenderer(this);
		bone18.setPos(1.75F, -0.75F, 1.0F);
		plate.addChild(bone18);
		setRotationAngle(bone18, -0.5236F, -0.5236F, 0.5236F);
		bone18.texOffs(24, 35).addBox(-0.25F, -0.75F, -0.5F, 3.0F, 1.0F, 2.0F, -0.35F, false);

		bone19 = new ModelRenderer(this);
		bone19.setPos(-1.75F, -0.75F, 0.5F);
		plate.addChild(bone19);
		setRotationAngle(bone19, 0.0F, 0.0F, 0.3927F);
		bone19.texOffs(53, 37).addBox(-2.75F, -0.75F, -1.5F, 3.0F, 1.0F, 2.0F, -0.35F, false);

		bone20 = new ModelRenderer(this);
		bone20.setPos(1.75F, -0.75F, 0.5F);
		plate.addChild(bone20);
		setRotationAngle(bone20, 0.0F, 0.0F, -0.3927F);
		bone20.texOffs(42, 37).addBox(-0.25F, -0.75F, -1.5F, 3.0F, 1.0F, 2.0F, -0.35F, false);

		magnet = new ModelRenderer(this);
		magnet.setPos(0.0F, -8.75F, 0.0F);
		total.addChild(magnet);
		magnet.texOffs(25, 57).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 3.0F, 3.0F, -0.25F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(0.0F, 0.0F, 0.0F);
		magnet.addChild(bone5);
		setRotationAngle(bone5, 0.0F, 0.0F, -0.5236F);
		bone5.texOffs(38, 58).addBox(0.4575F, -1.4755F, -1.5F, 2.0F, 3.0F, 3.0F, -0.25F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(0.0F, 0.0F, 0.0F);
		magnet.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 0.0F, 0.5236F);
		bone6.texOffs(20, 50).addBox(-2.4575F, -1.4755F, -1.5F, 2.0F, 3.0F, 3.0F, -0.25F, false);

		bone7 = new ModelRenderer(this);
		bone7.setPos(0.0F, -1.0F, 0.0F);
		magnet.addChild(bone7);
		setRotationAngle(bone7, 0.0F, 0.0F, -1.0472F);
		bone7.texOffs(51, 52).addBox(0.1585F, -0.0425F, -1.5F, 3.0F, 3.0F, 3.0F, -0.25F, false);

		bone8 = new ModelRenderer(this);
		bone8.setPos(0.0F, -1.0F, 0.0F);
		magnet.addChild(bone8);
		setRotationAngle(bone8, 0.0F, 0.0F, 1.0472F);
		bone8.texOffs(38, 51).addBox(-3.1585F, -0.0425F, -1.5F, 3.0F, 3.0F, 3.0F, -0.25F, false);

		bone9 = new ModelRenderer(this);
		bone9.setPos(0.0F, 0.0F, 0.0F);
		magnet.addChild(bone9);
		setRotationAngle(bone9, 0.0F, 0.0F, 1.309F);
		bone9.texOffs(31, 47).addBox(-4.8246F, 0.3592F, -1.5F, 2.0F, 3.0F, 3.0F, -0.25F, false);

		bone10 = new ModelRenderer(this);
		bone10.setPos(0.0F, 0.0F, 0.0F);
		magnet.addChild(bone10);
		bone10.texOffs(10, 45).addBox(-4.4373F, -7.364F, -1.5F, 3.0F, 4.0F, 3.0F, -0.25F, false);

		bone11 = new ModelRenderer(this);
		bone11.setPos(0.0F, 0.0F, 0.0F);
		magnet.addChild(bone11);
		bone11.texOffs(51, 44).addBox(1.4373F, -7.364F, -1.5F, 3.0F, 4.0F, 3.0F, -0.25F, false);

		bone12 = new ModelRenderer(this);
		bone12.setPos(0.0F, 0.0F, 0.0F);
		magnet.addChild(bone12);
		setRotationAngle(bone12, 0.0F, 0.0F, -1.309F);
		bone12.texOffs(1, 41).addBox(2.8246F, 0.3592F, -1.5F, 2.0F, 3.0F, 3.0F, -0.25F, false);

		bone13 = new ModelRenderer(this);
		bone13.setPos(0.0F, 0.0F, 0.0F);
		magnet.addChild(bone13);
		setRotationAngle(bone13, 0.0F, 0.0F, 0.1745F);
		bone13.texOffs(40, 41).addBox(-5.609F, -8.0288F, -1.5F, 3.0F, 2.0F, 3.0F, -0.25F, false);

		bone14 = new ModelRenderer(this);
		bone14.setPos(0.0F, 0.0F, 0.0F);
		magnet.addChild(bone14);
		setRotationAngle(bone14, 0.0F, 0.0F, -0.1745F);
		bone14.texOffs(21, 42).addBox(2.609F, -8.0288F, -1.5F, 3.0F, 2.0F, 3.0F, -0.25F, false);

		face = new ModelRenderer(this);
		face.setPos(0.0F, -11.0F, 0.0F);
		total.addChild(face);
		face.texOffs(11, 40).addBox(-2.5F, 0.0F, -1.75F, 2.0F, 2.0F, 1.0F, -0.4F, false);
		face.texOffs(33, 40).addBox(0.5F, 0.0F, -1.75F, 2.0F, 2.0F, 1.0F, -0.4F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(1.75F, -0.5F, -1.75F);
		face.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, 0.1745F);
		cube_r2.texOffs(57, 41).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, -0.4F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(-1.75F, -0.5F, -1.75F);
		face.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, -0.1745F);
		cube_r3.texOffs(19, 39).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 1.0F, -0.4F, false);

		elect = new ModelRenderer(this);
		elect.setPos(0.0F, -16.25F, 0.0F);
		total.addChild(elect);
		elect.texOffs(1, 38).addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 1.0F, -0.4F, false);

		base = new ModelRenderer(this);
		base.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(base);
		

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 0.0F, 0.0F);
		base.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, 0.2618F);
		bone.texOffs(56, 58).addBox(-1.171F, -3.2198F, -1.0F, 2.0F, 4.0F, 2.0F, -0.4F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.75F, -2.25F, 0.0F);
		base.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, -0.1309F);
		bone2.texOffs(0, 58).addBox(-1.2479F, -3.7826F, -1.0F, 2.0F, 4.0F, 2.0F, -0.3F, false);

		leave1 = new ModelRenderer(this);
		leave1.setPos(0.75F, -2.25F, 0.0F);
		base.addChild(leave1);
		setRotationAngle(leave1, -0.0873F, 0.0F, -0.1309F);
		leave1.texOffs(20, 56).addBox(-0.25F, -0.75F, -1.75F, 2.0F, 1.0F, 2.0F, -0.35F, false);
	}

	@Override
	public void setupAnim(GoldMagnetEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<GoldMagnetEntity> getPlantModel() {
		return this;
	}
}
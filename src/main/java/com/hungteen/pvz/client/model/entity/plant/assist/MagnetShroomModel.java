package com.hungteen.pvz.client.model.entity.plant.assist;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.assist.MagnetShroomEntity;
import com.hungteen.pvz.remove.MetalTypes;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class MagnetShroomModel extends PVZPlantModel<MagnetShroomEntity> {
	private final ModelRenderer total;
	private final ModelRenderer plate;
	private final ModelRenderer bone3;
	private final ModelRenderer cube_r1;
	private final ModelRenderer bone4;
	private final ModelRenderer face;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer elect;
	private final ModelRenderer magnets;
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
	private final ModelRenderer rest_magnet;
	private final ModelRenderer bone2;
	private final ModelRenderer bone15;
	private final ModelRenderer bone16;
	private final ModelRenderer bone17;
	private final ModelRenderer bone18;
	private final ModelRenderer bone19;
	private final ModelRenderer bone20;
	private final ModelRenderer bone21;
	private final ModelRenderer bone22;
	private final ModelRenderer bone23;
	private final ModelRenderer absorb_magnet;
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

	public MagnetShroomModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(0, 61).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		total.texOffs(0, 54).addBox(-1.0F, -5.5F, -1.0F, 2.0F, 5.0F, 2.0F, -0.3F, false);
		total.texOffs(0, 49).addBox(-1.0F, -7.5F, -1.0F, 2.0F, 3.0F, 2.0F, -0.4F, false);

		plate = new ModelRenderer(this);
		plate.setPos(0.0F, -7.0F, 0.0F);
		total.addChild(plate);
		plate.texOffs(9, 59).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.1F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, 0.0F, 0.0F);
		plate.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 1.5708F, 0.0F);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(-1.75F, -0.134F, 1.75F);
		bone3.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.5236F, 0.0F, 0.0F);
		cube_r1.texOffs(9, 56).addBox(0.25F, -1.0F, 0.0F, 3.0F, 1.0F, 2.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(0.0F, 0.0F, 0.0F);
		plate.addChild(bone4);
		setRotationAngle(bone4, -0.5236F, 1.5708F, 0.0F);
		bone4.texOffs(9, 53).addBox(-1.5F, -0.241F, -3.5825F, 3.0F, 1.0F, 2.0F, 0.0F, false);

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

		magnets = new ModelRenderer(this);
		magnets.setPos(0.0F, -8.0F, 0.0F);
		total.addChild(magnets);
		

		magnet = new ModelRenderer(this);
		magnet.setPos(0.0F, -0.75F, 0.0F);
		magnets.addChild(magnet);
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

		rest_magnet = new ModelRenderer(this);
		rest_magnet.setPos(0.0F, -0.75F, 0.0F);
		magnets.addChild(rest_magnet);
		rest_magnet.texOffs(1, 1).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 3.0F, 3.0F, -0.25F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, 0.0F, 0.0F);
		rest_magnet.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, -0.5236F);
		bone2.texOffs(1, 7).addBox(0.4575F, -1.4755F, -1.5F, 2.0F, 3.0F, 3.0F, -0.25F, false);

		bone15 = new ModelRenderer(this);
		bone15.setPos(0.0F, 0.0F, 0.0F);
		rest_magnet.addChild(bone15);
		setRotationAngle(bone15, 0.0F, 0.0F, 0.5236F);
		bone15.texOffs(1, 13).addBox(-2.4575F, -1.4755F, -1.5F, 2.0F, 3.0F, 3.0F, -0.25F, false);

		bone16 = new ModelRenderer(this);
		bone16.setPos(0.0F, -1.0F, 0.0F);
		rest_magnet.addChild(bone16);
		setRotationAngle(bone16, 0.0F, 0.0F, -1.0472F);
		bone16.texOffs(1, 1).addBox(0.1585F, -0.0425F, -1.5F, 3.0F, 3.0F, 3.0F, -0.25F, false);

		bone17 = new ModelRenderer(this);
		bone17.setPos(0.0F, -1.0F, 0.0F);
		rest_magnet.addChild(bone17);
		setRotationAngle(bone17, 0.0F, 0.0F, 1.0472F);
		bone17.texOffs(0, 7).addBox(-3.1585F, -0.0425F, -1.5F, 3.0F, 3.0F, 3.0F, -0.25F, false);

		bone18 = new ModelRenderer(this);
		bone18.setPos(0.0F, 0.0F, 0.0F);
		rest_magnet.addChild(bone18);
		setRotationAngle(bone18, 0.0F, 0.0F, 1.309F);
		bone18.texOffs(1, 13).addBox(-4.8246F, 0.3592F, -1.5F, 2.0F, 3.0F, 3.0F, -0.25F, false);

		bone19 = new ModelRenderer(this);
		bone19.setPos(0.0F, 0.0F, 0.0F);
		rest_magnet.addChild(bone19);
		bone19.texOffs(1, 1).addBox(-4.4373F, -7.364F, -1.5F, 3.0F, 4.0F, 3.0F, -0.25F, false);

		bone20 = new ModelRenderer(this);
		bone20.setPos(0.0F, 0.0F, 0.0F);
		rest_magnet.addChild(bone20);
		bone20.texOffs(1, 1).addBox(1.4373F, -7.364F, -1.5F, 3.0F, 4.0F, 3.0F, -0.25F, false);

		bone21 = new ModelRenderer(this);
		bone21.setPos(0.0F, 0.0F, 0.0F);
		rest_magnet.addChild(bone21);
		setRotationAngle(bone21, 0.0F, 0.0F, -1.309F);
		bone21.texOffs(2, 3).addBox(2.8246F, 0.3592F, -1.5F, 2.0F, 3.0F, 3.0F, -0.25F, false);

		bone22 = new ModelRenderer(this);
		bone22.setPos(0.0F, 0.0F, 0.0F);
		rest_magnet.addChild(bone22);
		setRotationAngle(bone22, 0.0F, 0.0F, 0.1745F);
		bone22.texOffs(1, 20).addBox(-5.609F, -8.0288F, -1.5F, 3.0F, 2.0F, 3.0F, -0.25F, false);

		bone23 = new ModelRenderer(this);
		bone23.setPos(0.0F, 0.0F, 0.0F);
		rest_magnet.addChild(bone23);
		setRotationAngle(bone23, 0.0F, 0.0F, -0.1745F);
		bone23.texOffs(1, 26).addBox(2.609F, -8.0288F, -1.5F, 3.0F, 2.0F, 3.0F, -0.25F, false);

		absorb_magnet = new ModelRenderer(this);
		absorb_magnet.setPos(0.0F, -0.75F, 0.0F);
		magnets.addChild(absorb_magnet);
		absorb_magnet.texOffs(16, 1).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 3.0F, 3.0F, -0.25F, false);

		bone24 = new ModelRenderer(this);
		bone24.setPos(0.0F, 0.0F, 0.0F);
		absorb_magnet.addChild(bone24);
		setRotationAngle(bone24, 0.0F, 0.0F, -0.5236F);
		bone24.texOffs(15, 1).addBox(0.4575F, -1.4755F, -1.5F, 2.0F, 3.0F, 3.0F, -0.25F, false);

		bone25 = new ModelRenderer(this);
		bone25.setPos(0.0F, 0.0F, 0.0F);
		absorb_magnet.addChild(bone25);
		setRotationAngle(bone25, 0.0F, 0.0F, 0.5236F);
		bone25.texOffs(16, 2).addBox(-2.4575F, -1.4755F, -1.5F, 2.0F, 3.0F, 3.0F, -0.25F, false);

		bone26 = new ModelRenderer(this);
		bone26.setPos(0.0F, -1.0F, 0.0F);
		absorb_magnet.addChild(bone26);
		setRotationAngle(bone26, 0.0F, 0.0F, -1.0472F);
		bone26.texOffs(15, 2).addBox(0.1585F, -0.0425F, -1.5F, 3.0F, 3.0F, 3.0F, -0.25F, false);

		bone27 = new ModelRenderer(this);
		bone27.setPos(0.0F, -1.0F, 0.0F);
		absorb_magnet.addChild(bone27);
		setRotationAngle(bone27, 0.0F, 0.0F, 1.0472F);
		bone27.texOffs(15, 1).addBox(-3.1585F, -0.0425F, -1.5F, 3.0F, 3.0F, 3.0F, -0.25F, false);

		bone28 = new ModelRenderer(this);
		bone28.setPos(0.0F, 0.0F, 0.0F);
		absorb_magnet.addChild(bone28);
		setRotationAngle(bone28, 0.0F, 0.0F, 1.309F);
		bone28.texOffs(16, 2).addBox(-4.8246F, 0.3592F, -1.5F, 2.0F, 3.0F, 3.0F, -0.25F, false);

		bone29 = new ModelRenderer(this);
		bone29.setPos(0.0F, 0.0F, 0.0F);
		absorb_magnet.addChild(bone29);
		bone29.texOffs(15, 2).addBox(-4.4373F, -7.364F, -1.5F, 3.0F, 4.0F, 3.0F, -0.25F, false);

		bone30 = new ModelRenderer(this);
		bone30.setPos(0.0F, 0.0F, 0.0F);
		absorb_magnet.addChild(bone30);
		bone30.texOffs(16, 3).addBox(1.4373F, -7.364F, -1.5F, 3.0F, 4.0F, 3.0F, -0.25F, false);

		bone31 = new ModelRenderer(this);
		bone31.setPos(0.0F, 0.0F, 0.0F);
		absorb_magnet.addChild(bone31);
		setRotationAngle(bone31, 0.0F, 0.0F, -1.309F);
		bone31.texOffs(16, 3).addBox(2.8246F, 0.3592F, -1.5F, 2.0F, 3.0F, 3.0F, -0.25F, false);

		bone32 = new ModelRenderer(this);
		bone32.setPos(0.0F, 0.0F, 0.0F);
		absorb_magnet.addChild(bone32);
		setRotationAngle(bone32, 0.0F, 0.0F, 0.1745F);
		bone32.texOffs(15, 11).addBox(-5.609F, -8.0288F, -1.5F, 3.0F, 2.0F, 3.0F, -0.25F, false);

		bone33 = new ModelRenderer(this);
		bone33.setPos(0.0F, 0.0F, 0.0F);
		absorb_magnet.addChild(bone33);
		setRotationAngle(bone33, 0.0F, 0.0F, -0.1745F);
		bone33.texOffs(15, 17).addBox(2.609F, -8.0288F, -1.5F, 3.0F, 2.0F, 3.0F, -0.25F, false);
	}

	@Override
	public void setupAnim(MagnetShroomEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		final boolean isRest = ! entity.isMagnetActive();
		final boolean hasReach = entity.getMetalType() != MetalTypes.EMPTY;
		this.magnet.visible = ! isRest;
		this.rest_magnet.visible = (isRest && hasReach);
		this.absorb_magnet.visible = (isRest && ! hasReach);
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<MagnetShroomEntity> getPlantModel() {
		return this;
	}
}
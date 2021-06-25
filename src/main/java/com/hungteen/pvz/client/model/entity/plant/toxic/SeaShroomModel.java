package com.hungteen.pvz.client.model.entity.plant.toxic;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.toxic.SeaShroomEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class SeaShroomModel extends PVZPlantModel<SeaShroomEntity> {
	private final ModelRenderer total;
	private final ModelRenderer tail;
	private final ModelRenderer bone;
	private final ModelRenderer cube_r1;
	private final ModelRenderer bone2;
	private final ModelRenderer cube_r2;
	private final ModelRenderer bone3;
	private final ModelRenderer cube_r3;
	private final ModelRenderer bone4;
	private final ModelRenderer cube_r4;
	private final ModelRenderer bone5;
	private final ModelRenderer cube_r5;
	private final ModelRenderer bone6;
	private final ModelRenderer cube_r6;
	private final ModelRenderer bone7;
	private final ModelRenderer cube_r7;
	private final ModelRenderer bone8;
	private final ModelRenderer cube_r8;
	private final ModelRenderer bone9;
	private final ModelRenderer cube_r9;
	private final ModelRenderer bone10;
	private final ModelRenderer cube_r10;
	private final ModelRenderer bone11;
	private final ModelRenderer cube_r11;
	private final ModelRenderer bone12;
	private final ModelRenderer cube_r12;
	private final ModelRenderer bone13;
	private final ModelRenderer cube_r13;
	private final ModelRenderer bone14;
	private final ModelRenderer cube_r14;
	private final ModelRenderer bone15;
	private final ModelRenderer cube_r15;

	public SeaShroomModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 14.0F, 0.0F);
		total.texOffs(31, 47).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		total.texOffs(14, 58).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		total.texOffs(0, 0).addBox(-6.0F, -12.0F, -6.0F, 12.0F, 4.0F, 12.0F, 0.0F, false);
		total.texOffs(0, 17).addBox(-5.0F, -15.0F, -5.0F, 10.0F, 3.0F, 10.0F, 0.0F, false);
		total.texOffs(0, 31).addBox(-4.0F, -16.0F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
		total.texOffs(1, 42).addBox(-1.0F, -4.0F, -6.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		total.texOffs(9, 41).addBox(-2.0F, -5.0F, -8.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(tail);
		

		bone = new ModelRenderer(this);
		bone.setPos(-1.0F, 0.0F, 1.0F);
		tail.addChild(bone);
		setRotationAngle(bone, 0.1309F, 0.0F, 0.1309F);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(0.0F, 1.0F, 0.0F);
		bone.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0873F, 0.0F, 0.0F);
		cube_r1.texOffs(58, 50).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.1F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, 3.9772F, 0.348F);
		bone.addChild(bone2);
		

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(0.0F, 0.0F, 0.0F);
		bone2.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.1745F, 0.0F, 0.0F);
		cube_r2.texOffs(59, 43).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 4.0F, 1.0F, 0.1F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, 0.0F, 0.0F);
		bone2.addChild(bone3);
		

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(0.0F, 3.8941F, 0.863F);
		bone3.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.3491F, 0.0F, 0.0F);
		cube_r3.texOffs(54, 40).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 5.0F, 1.0F, 0.1F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(1.0F, 0.0F, 1.0F);
		tail.addChild(bone4);
		setRotationAngle(bone4, 0.1309F, 0.0F, -0.1309F);
		

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(0.9914F, 0.8706F, 0.017F);
		bone4.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0873F, 0.0F, 0.0F);
		cube_r4.texOffs(9, 59).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.1F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(0.9914F, 3.8478F, 0.365F);
		bone4.addChild(bone5);
		

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(0.0F, 0.0F, 0.0F);
		bone5.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.1745F, 0.0F, 0.0F);
		cube_r5.texOffs(4, 58).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 4.0F, 1.0F, 0.1F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(0.0F, 0.0F, 0.0F);
		bone5.addChild(bone6);
		

		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(0.0F, 3.8941F, 0.863F);
		bone6.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.3491F, 0.0F, 0.0F);
		cube_r6.texOffs(0, 47).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 5.0F, 1.0F, 0.1F, false);

		bone7 = new ModelRenderer(this);
		bone7.setPos(-1.0F, 0.0F, -2.0F);
		tail.addChild(bone7);
		setRotationAngle(bone7, 0.1309F, -1.0472F, 0.1309F);
		

		cube_r7 = new ModelRenderer(this);
		cube_r7.setPos(0.0F, 1.0F, 0.0F);
		bone7.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0873F, 0.0F, 0.0F);
		cube_r7.texOffs(1, 54).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.1F, false);

		bone8 = new ModelRenderer(this);
		bone8.setPos(0.0F, 3.9772F, 0.348F);
		bone7.addChild(bone8);
		

		cube_r8 = new ModelRenderer(this);
		cube_r8.setPos(0.0F, 0.0F, 0.0F);
		bone8.addChild(cube_r8);
		setRotationAngle(cube_r8, 0.1745F, 0.0F, 0.0F);
		cube_r8.texOffs(6, 48).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 4.0F, 1.0F, 0.1F, false);

		bone9 = new ModelRenderer(this);
		bone9.setPos(0.0F, 0.0F, 0.0F);
		bone8.addChild(bone9);
		

		cube_r9 = new ModelRenderer(this);
		cube_r9.setPos(0.0F, 3.8941F, 0.863F);
		bone9.addChild(cube_r9);
		setRotationAngle(cube_r9, 0.3491F, 0.0F, 0.0F);
		cube_r9.texOffs(11, 49).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 5.0F, 1.0F, 0.1F, false);

		bone10 = new ModelRenderer(this);
		bone10.setPos(2.0F, 0.0F, -2.0F);
		tail.addChild(bone10);
		setRotationAngle(bone10, 0.1309F, 1.0472F, -0.1309F);
		

		cube_r10 = new ModelRenderer(this);
		cube_r10.setPos(0.4957F, 0.7585F, -0.8342F);
		bone10.addChild(cube_r10);
		setRotationAngle(cube_r10, 0.0873F, 0.0F, 0.0F);
		cube_r10.texOffs(17, 48).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.1F, false);

		bone11 = new ModelRenderer(this);
		bone11.setPos(0.4957F, 3.7357F, -0.4863F);
		bone10.addChild(bone11);
		

		cube_r11 = new ModelRenderer(this);
		cube_r11.setPos(0.0F, 0.0F, 0.0F);
		bone11.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.1745F, 0.0F, 0.0F);
		cube_r11.texOffs(22, 50).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 4.0F, 1.0F, 0.1F, false);

		bone12 = new ModelRenderer(this);
		bone12.setPos(0.0F, 0.0F, 0.0F);
		bone11.addChild(bone12);
		

		cube_r12 = new ModelRenderer(this);
		cube_r12.setPos(0.0F, 3.8941F, 0.863F);
		bone12.addChild(cube_r12);
		setRotationAngle(cube_r12, 0.3491F, 0.0F, 0.0F);
		cube_r12.texOffs(22, 41).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 5.0F, 1.0F, 0.1F, false);

		bone13 = new ModelRenderer(this);
		bone13.setPos(0.0F, 0.0F, -3.0F);
		tail.addChild(bone13);
		

		cube_r13 = new ModelRenderer(this);
		cube_r13.setPos(0.0F, 1.0F, 0.0F);
		bone13.addChild(cube_r13);
		setRotationAngle(cube_r13, -0.0873F, 0.0F, 0.0F);
		cube_r13.texOffs(28, 42).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.1F, false);

		bone14 = new ModelRenderer(this);
		bone14.setPos(0.0F, 3.9772F, 0.348F);
		bone13.addChild(bone14);
		setRotationAngle(bone14, -0.5236F, 0.0F, 0.0F);
		

		cube_r14 = new ModelRenderer(this);
		cube_r14.setPos(0.2046F, 0.0979F, -0.7978F);
		bone14.addChild(cube_r14);
		setRotationAngle(cube_r14, 0.1745F, 0.0F, -0.1745F);
		cube_r14.texOffs(34, 41).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 4.0F, 1.0F, 0.1F, false);

		bone15 = new ModelRenderer(this);
		bone15.setPos(0.0F, 0.0F, 0.0F);
		bone14.addChild(bone15);
		

		cube_r15 = new ModelRenderer(this);
		cube_r15.setPos(0.8752F, 3.9011F, -0.3679F);
		bone15.addChild(cube_r15);
		setRotationAngle(cube_r15, -0.0873F, 0.0F, -0.1745F);
		cube_r15.texOffs(59, 35).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 5.0F, 1.0F, 0.1F, false);
	}

	@Override
	public void setupAnim(SeaShroomEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<SeaShroomEntity> getPlantModel() {
		return this;
	}
}
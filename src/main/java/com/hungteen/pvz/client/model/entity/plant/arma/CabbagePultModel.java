package com.hungteen.pvz.client.model.entity.plant.arma;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.arma.CabbagePultEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class CabbagePultModel extends PVZPlantModel<CabbagePultEntity> {
	private final ModelRenderer total;
	private final ModelRenderer head;
	private final ModelRenderer face;
	private final ModelRenderer leaves;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer cube_r7;
	private final ModelRenderer cube_r8;
	private final ModelRenderer pult;
	private final ModelRenderer cube_r9;
	private final ModelRenderer cube_r10;
	private final ModelRenderer cube_r11;
	private final ModelRenderer cube_r12;
	private final ModelRenderer basket;
	private final ModelRenderer cabbage;

	public CabbagePultModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		head = new ModelRenderer(this);
		head.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(head);
		head.texOffs(0, 41).addBox(-6.0F, -11.0F, -6.0F, 12.0F, 11.0F, 12.0F, 0.0F, false);
		head.texOffs(0, 0).addBox(-5.0F, -11.75F, -5.0F, 10.0F, 1.0F, 10.0F, -0.2F, false);
		head.texOffs(36, 46).addBox(-3.0F, -12.25F, -3.0F, 6.0F, 1.0F, 6.0F, -0.3F, false);

		face = new ModelRenderer(this);
		face.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(face);
		face.texOffs(36, 48).addBox(-3.25F, -7.25F, -6.45F, 2.0F, 3.0F, 1.0F, -0.4F, false);
		face.texOffs(36, 44).addBox(1.25F, -7.25F, -6.45F, 2.0F, 3.0F, 1.0F, -0.4F, false);
		face.texOffs(36, 41).addBox(-4.5F, -9.25F, -6.45F, 4.0F, 2.0F, 1.0F, -0.4F, false);
		face.texOffs(46, 41).addBox(0.5F, -9.25F, -6.45F, 4.0F, 2.0F, 1.0F, -0.4F, false);

		leaves = new ModelRenderer(this);
		leaves.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(leaves);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(1.0F, 0.25F, 5.5F);
		leaves.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0873F, 0.0F, 0.0F);
		cube_r1.texOffs(10, 11).addBox(-4.0F, -1.0F, 0.0F, 6.0F, 1.0F, 3.0F, -0.4F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(1.0F, 0.25F, -5.5F);
		leaves.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.0873F, 0.0F, 0.0F);
		cube_r2.texOffs(10, 15).addBox(-4.0F, -1.0F, -3.0F, 6.0F, 1.0F, 3.0F, -0.4F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(5.5F, 0.25F, 0.0F);
		leaves.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, -0.0873F);
		cube_r3.texOffs(12, 24).addBox(0.0F, -1.0F, -3.0F, 3.0F, 1.0F, 6.0F, -0.4F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(-5.5F, 0.25F, 0.0F);
		leaves.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, 0.0873F);
		cube_r4.texOffs(38, 19).addBox(-3.0F, -1.0F, -3.0F, 3.0F, 1.0F, 6.0F, -0.4F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(5.25F, -0.25F, 5.25F);
		leaves.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0873F, 0.0F, -0.0873F);
		cube_r5.texOffs(0, 22).addBox(-0.25F, -0.5F, -0.25F, 4.0F, 1.0F, 4.0F, -0.3F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(5.25F, -0.25F, -5.25F);
		leaves.addChild(cube_r6);
		setRotationAngle(cube_r6, -0.0873F, 0.0F, -0.0873F);
		cube_r6.texOffs(0, 27).addBox(-0.25F, -0.5F, -3.75F, 4.0F, 1.0F, 4.0F, -0.3F, false);

		cube_r7 = new ModelRenderer(this);
		cube_r7.setPos(-5.25F, -0.25F, 5.25F);
		leaves.addChild(cube_r7);
		setRotationAngle(cube_r7, 0.0873F, 0.0F, 0.0873F);
		cube_r7.texOffs(48, 58).addBox(-3.75F, -0.5F, -0.25F, 4.0F, 1.0F, 4.0F, -0.3F, false);

		cube_r8 = new ModelRenderer(this);
		cube_r8.setPos(-5.25F, -0.25F, -5.25F);
		leaves.addChild(cube_r8);
		setRotationAngle(cube_r8, -0.0873F, 0.0F, 0.0873F);
		cube_r8.texOffs(48, 53).addBox(-3.75F, -0.5F, -3.75F, 4.0F, 1.0F, 4.0F, -0.3F, false);

		pult = new ModelRenderer(this);
		pult.setPos(0.0F, -12.0F, 0.0F);
		total.addChild(pult);
		

		cube_r9 = new ModelRenderer(this);
		cube_r9.setPos(0.0F, -4.707F, 4.4751F);
		pult.addChild(cube_r9);
		setRotationAngle(cube_r9, -1.9199F, 0.0F, 0.0F);
		cube_r9.texOffs(56, 17).addBox(-1.0F, -16.0F, -1.0F, 2.0F, 15.0F, 2.0F, -0.4F, false);

		cube_r10 = new ModelRenderer(this);
		cube_r10.setPos(0.0F, 0.5F, 0.0F);
		pult.addChild(cube_r10);
		setRotationAngle(cube_r10, -0.2182F, 0.0F, 0.0F);
		cube_r10.texOffs(4, 41).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, -0.4F, false);

		cube_r11 = new ModelRenderer(this);
		cube_r11.setPos(0.0F, -2.4723F, 6.0981F);
		pult.addChild(cube_r11);
		setRotationAngle(cube_r11, 0.5236F, 0.0F, 0.0F);
		cube_r11.texOffs(2, 46).addBox(-1.0F, -5.0F, -2.0F, 2.0F, 4.0F, 3.0F, -0.9F, false);

		cube_r12 = new ModelRenderer(this);
		cube_r12.setPos(0.0F, 0.0277F, -1.1519F);
		pult.addChild(cube_r12);
		setRotationAngle(cube_r12, -1.0472F, 0.0F, 0.0F);
		cube_r12.texOffs(56, 41).addBox(-1.0F, -9.0F, -1.0F, 2.0F, 8.0F, 2.0F, -0.4F, false);

		basket = new ModelRenderer(this);
		basket.setPos(0.0F, 2.0F, 20.0F);
		pult.addChild(basket);
		basket.texOffs(30, 0).addBox(-4.0F, -3.0F, -1.0F, 8.0F, 3.0F, 1.0F, 0.0F, false);
		basket.texOffs(30, 4).addBox(-4.0F, -3.0F, 8.0F, 8.0F, 3.0F, 1.0F, 0.0F, false);
		basket.texOffs(0, 32).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
		basket.texOffs(46, 0).addBox(-5.0F, -3.0F, 0.0F, 1.0F, 3.0F, 8.0F, 0.0F, false);
		basket.texOffs(0, 11).addBox(4.0F, -3.0F, 0.0F, 1.0F, 3.0F, 8.0F, 0.0F, false);

		cabbage = new ModelRenderer(this);
		cabbage.setPos(0.0F, 0.0F, 0.0F);
		basket.addChild(cabbage);
		cabbage.texOffs(32, 26).addBox(-4.0F, -7.0F, 0.0F, 8.0F, 7.0F, 8.0F, -0.4F, false);
	}

	@Override
	public void setupAnim(CabbagePultEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if(entity.getAttackTime() > 0) {
			float percent = 1 - entity.getAttackTime() * 1.0F / entity.getPultAnimTime();
			pult.xRot = (1F - MathHelper.abs(MathHelper.cos(percent * 3.14159F))) * 1.5F;
			this.cabbage.visible = (percent < 0.5);
		} else {
			pult.xRot = MathHelper.sin(ageInTicks / 10) / 8;
			this.cabbage.visible = true;
		}
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<CabbagePultEntity> getPlantModel() {
		return this;
	}
}
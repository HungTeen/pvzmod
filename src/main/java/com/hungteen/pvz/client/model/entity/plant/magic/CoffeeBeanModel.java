package com.hungteen.pvz.client.model.entity.plant.magic;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.magic.CoffeeBeanEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class CoffeeBeanModel extends PVZPlantModel<CoffeeBeanEntity> {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer right_wing;
	private final ModelRenderer left_wing;

	public CoffeeBeanModel() {
		texWidth = 32;
		texHeight = 32;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 20.0F, 0.0F);


		body = new ModelRenderer(this);
		body.setPos(0.0F, 10.0F, 0.0F);
		total.addChild(body);


		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(0.0F, -13.5F, 0.0F);
		body.addChild(cube_r1);
		setRotationAngle(cube_r1, -0.2618F, 0.0F, 0.0F);
		cube_r1.texOffs(0, 0).addBox(-2.0F, -2.5F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(0.0F, -16.0F, 0.0F);
		body.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.6981F, 0.0F, 0.0F);
		cube_r2.texOffs(0, 9).addBox(-3.0F, -7.0F, 0.5F, 6.0F, 7.0F, 1.0F, 0.0F, false);

		right_wing = new ModelRenderer(this);
		right_wing.setPos(0.0F, 10.0F, 0.0F);
		total.addChild(right_wing);


		left_wing = new ModelRenderer(this);
		left_wing.setPos(0.0F, 10.0F, 0.0F);
		total.addChild(left_wing);

	}

	@Override
	public void setupAnim(CoffeeBeanEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.left_wing.zRot = MathHelper.sin(ageInTicks / 10) * 3.14159f / 4;
		this.right_wing.zRot = - MathHelper.sin(ageInTicks / 10) * 3.14159f / 4;
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<CoffeeBeanEntity> getPlantModel() {
		return this;
	}
}
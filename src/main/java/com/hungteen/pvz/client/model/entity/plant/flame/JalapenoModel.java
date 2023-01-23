package com.hungteen.pvz.client.model.entity.plant.flame;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.flame.JalapenoEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class JalapenoModel extends PVZPlantModel<JalapenoEntity> {
	private final ModelRenderer total;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;

	public JalapenoModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(0, 0).addBox(-5.0F, -20.0F, -5.0F, 10.0F, 13.0F, 10.0F, 0.0F, false);
		total.texOffs(0, 38).addBox(-5.0F, -20.0F, -5.0F, 10.0F, 13.0F, 10.0F, -0.5F, false);
		total.texOffs(0, 30).addBox(0.0F, -29.0F, -7.0F, 0.0F, 9.0F, 8.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(0.0F, -20.5F, 0.0F);
		total.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.1309F, 0.0F, 0.0F);
		cube_r1.texOffs(32, 15).addBox(-4.0F, -1.3F, -4.25F, 8.0F, 1.0F, 8.0F, 0.6F, false);
		cube_r1.texOffs(24, 30).addBox(-4.0F, -1.5F, -4.25F, 8.0F, 3.0F, 8.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(0.0F, -4.5F, 0.0F);
		total.addChild(cube_r2);
		setRotationAngle(cube_r2, -0.2182F, 0.0F, 0.0F);
		cube_r2.texOffs(0, 23).addBox(-4.0F, -4.5F, -4.75F, 8.0F, 7.0F, 8.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(0.0F, -4.5F, 0.0F);
		total.addChild(cube_r3);
		setRotationAngle(cube_r3, -0.4363F, 0.0F, 0.0F);
		cube_r3.texOffs(30, 0).addBox(-3.0F, 1.5F, -3.0F, 6.0F, 5.0F, 5.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(JalapenoEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<JalapenoEntity> getPlantModel() {
		return this;
	}
}
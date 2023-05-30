package com.hungteen.pvz.client.model.entity.plant.light;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.light.PlanternEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class PlanternModel extends PVZPlantModel<PlanternEntity> {
	private final ModelRenderer total;
	private final ModelRenderer head;
	private final ModelRenderer bottom;
	private final ModelRenderer stick1;
	private final ModelRenderer stick2;
	private final ModelRenderer stick3;
	private final ModelRenderer stick4;
	private final ModelRenderer stick5;
	private final ModelRenderer stick6;

	public PlanternModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);


		head = new ModelRenderer(this);
		head.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(head);
		head.texOffs(0, 0).addBox(-2.0F, -33.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		head.texOffs(42, 47).addBox(-6.0F, -29.0F, 0.0F, 12.0F, 3.0F, 0.0F, 0.0F, false);
		head.texOffs(42, 32).addBox(0.0F, -29.0F, -6.0F, 0.0F, 3.0F, 12.0F, 0.0F, false);
		head.texOffs(0, 23).addBox(-9.0F, -26.0F, -9.0F, 18.0F, 3.0F, 18.0F, 0.0F, false);
		head.texOffs(0, 0).addBox(-9.5F, -23.0F, -9.5F, 19.0F, 4.0F, 19.0F, 0.0F, false);
		head.texOffs(45, 53).addBox(-5.5F, -25.0F, -5.5F, 11.0F, 11.0F, 11.0F, 0.0F, false);
		head.texOffs(0, 64).addBox(-5.0F, -25.0F, -5.0F, 10.0F, 11.0F, 10.0F, 0.0F, false);
		head.texOffs(0, 44).addBox(-7.0F, -14.0F, -7.0F, 14.0F, 6.0F, 14.0F, 0.0F, false);
		head.texOffs(89, 0).addBox(-2.0F, -14.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		bottom = new ModelRenderer(this);
		bottom.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(bottom);
		bottom.texOffs(42, 50).addBox(-5.0F, -3.0F, 0.0F, 10.0F, 3.0F, 0.0F, 0.0F, false);
		bottom.texOffs(0, 0).addBox(0.0F, -3.0F, -5.0F, 0.0F, 3.0F, 10.0F, 0.0F, false);
		bottom.texOffs(89, 12).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		stick1 = new ModelRenderer(this);
		stick1.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(stick1);
		stick1.texOffs(106, 0).addBox(-2.0F, -9.0F, -2.0F, 4.0F, 9.0F, 4.0F, -0.01F, false);

		stick2 = new ModelRenderer(this);
		stick2.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(stick2);
		stick2.texOffs(106, 0).addBox(-2.0F, -9.0F, -2.0F, 4.0F, 9.0F, 4.0F, -0.01F, false);

		stick3 = new ModelRenderer(this);
		stick3.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(stick3);
		stick3.texOffs(106, 0).addBox(-2.0F, -9.0F, -2.0F, 4.0F, 9.0F, 4.0F, -0.01F, false);

		stick4 = new ModelRenderer(this);
		stick4.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(stick4);
		stick4.texOffs(106, 0).addBox(-2.0F, -9.0F, -2.0F, 4.0F, 9.0F, 4.0F, -0.01F, false);

		stick5 = new ModelRenderer(this);
		stick5.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(stick5);
		stick5.texOffs(106, 0).addBox(-2.0F, -9.0F, -2.0F, 4.0F, 9.0F, 4.0F, -0.01F, false);

		stick6 = new ModelRenderer(this);
		stick6.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(stick6);
		stick6.texOffs(106, 0).addBox(-2.0F, -9.0F, -2.0F, 4.0F, 9.0F, 4.0F, -0.01F, false);
	}


	@Override
	public void setupAnim(PlanternEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<PlanternEntity> getPlantModel() {
		return this;
	}
}
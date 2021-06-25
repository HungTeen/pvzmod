package com.hungteen.pvz.client.model.entity.plant.toxic;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.toxic.ScaredyShroomEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class ScaredyShroomModel extends PVZPlantModel<ScaredyShroomEntity> {
	private final ModelRenderer total;
	private final ModelRenderer head;
	private final ModelRenderer hat;
	private final ModelRenderer body;

	public ScaredyShroomModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		head = new ModelRenderer(this);
		head.setPos(0.0F, -3.0F, -3.0F);
		total.addChild(head);
		head.texOffs(52, 2).addBox(-1.0F, -18.0F, -2.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		head.texOffs(1, 3).addBox(-2.0F, -19.0F, -4.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
		head.texOffs(31, 33).addBox(-4.0F, -23.0F, -1.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		hat = new ModelRenderer(this);
		hat.setPos(0.0F, -23.0F, 2.0F);
		head.addChild(hat);
		setRotationAngle(hat, -0.6109F, 0.0F, 0.0F);
		hat.texOffs(0, 0).addBox(-6.0F, -10.1161F, -3.4601F, 12.0F, 12.0F, 13.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(-1.0F, -13.0F, 0.0F);
		total.addChild(body);
		body.texOffs(0, 40).addBox(-1.0F, -4.0F, -2.0F, 4.0F, 15.0F, 4.0F, 0.0F, false);
		body.texOffs(17, 55).addBox(-2.0F, 11.0F, -3.0F, 6.0F, 2.0F, 6.0F, 0.0F, false);
		body.texOffs(17, 55).addBox(-2.0F, -5.0F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(ScaredyShroomEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<ScaredyShroomEntity> getPlantModel() {
		return this;
	}
}
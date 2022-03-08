package com.hungteen.pvz.client.model.entity.plant.appease;

import java.util.Optional;

import com.hungteen.pvz.client.model.entity.plant.PlantShooterModel;
import com.hungteen.pvz.common.entity.plant.appease.SplitPeaEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class SplitPeaModel extends PlantShooterModel<SplitPeaEntity> {
	private final ModelRenderer total;
	private final ModelRenderer leaves;
	private final ModelRenderer body;
	private final ModelRenderer heads;
	private final ModelRenderer back_head;
	private final ModelRenderer front_head;

	public SplitPeaModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		leaves = new ModelRenderer(this);
		leaves.setPos(1.0F, 0.0F, 0.0F);
		total.addChild(leaves);
		leaves.texOffs(0, 23).addBox(0.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		leaves.texOffs(9, 23).addBox(-2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		leaves.texOffs(20, 16).addBox(-2.0F, -1.0F, -3.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		leaves.texOffs(20, 20).addBox(-4.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		leaves.texOffs(1, 27).addBox(2.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		leaves.texOffs(1, 33).addBox(-3.0F, -1.0F, 3.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		leaves.texOffs(1, 38).addBox(-7.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		leaves.texOffs(0, 44).addBox(-3.0F, -1.0F, -6.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		body.texOffs(56, 47).addBox(-1.0F, -15.0F, -1.0F, 2.0F, 15.0F, 2.0F, 0.0F, false);

		heads = new ModelRenderer(this);
		heads.setPos(0.0F, -15.0F, 0.0F);
		body.addChild(heads);
		heads.texOffs(35, 53).addBox(-3.0F, -7.0F, -1.0F, 6.0F, 7.0F, 2.0F, 0.0F, false);
		heads.texOffs(17, 36).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		heads.texOffs(20, 24).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		heads.texOffs(16, 28).addBox(0.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		heads.texOffs(17, 32).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		back_head = new ModelRenderer(this);
		back_head.setPos(0.0F, 0.0F, 4.0F);
		heads.addChild(back_head);
		back_head.texOffs(1, 49).addBox(-2.0F, -4.0F, 5.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
		back_head.texOffs(17, 40).addBox(-1.0F, -3.0F, 4.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		back_head.texOffs(29, 1).addBox(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 7.0F, 0.0F, false);

		front_head = new ModelRenderer(this);
		front_head.setPos(0.0F, 0.0F, -5.0F);
		heads.addChild(front_head);
		front_head.texOffs(7, 15).addBox(-2.0F, -4.0F, -6.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
		front_head.texOffs(0, 16).addBox(-1.0F, -3.0F, -4.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		front_head.texOffs(0, 0).addBox(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 7.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(SplitPeaEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.total.yRot = entity.getRoundTick() * 3.1415926F / 10;
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}
	
	@Override
	public Optional<ModelRenderer> getHeadModel() {
		return Optional.ofNullable(this.heads);
	}
	
	@Override
	public Optional<ModelRenderer> getBodyModel() {
		return Optional.ofNullable(this.body);
	}

	@Override
	public EntityModel<SplitPeaEntity> getPlantModel() {
		return this;
	}
	
}
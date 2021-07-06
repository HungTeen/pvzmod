package com.hungteen.pvz.client.model.entity.plant.appease;

import java.util.Optional;

import com.hungteen.pvz.client.model.entity.plant.PlantShooterModel;
import com.hungteen.pvz.common.entity.plant.appease.RepeaterEntity;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class RepeaterModel extends PlantShooterModel<RepeaterEntity> {
	private final ModelRenderer total;
	private final ModelRenderer leaves;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;

	public RepeaterModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		leaves = new ModelRenderer(this);
		leaves.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(leaves);
		leaves.texOffs(14, 30).addBox(1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		leaves.texOffs(13, 36).addBox(-1.0F, -1.0F, 1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		leaves.texOffs(1, 30).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		leaves.texOffs(1, 35).addBox(-3.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		leaves.texOffs(2, 45).addBox(3.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		leaves.texOffs(0, 55).addBox(-2.0F, -1.0F, 3.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		leaves.texOffs(41, 1).addBox(-6.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		leaves.texOffs(42, 30).addBox(-2.0F, -1.0F, -6.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		body.texOffs(25, 36).addBox(-1.0F, -15.0F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.texOffs(24, 40).addBox(0.0F, -15.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.texOffs(23, 44).addBox(-2.0F, -15.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.texOffs(25, 31).addBox(-1.0F, -15.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.texOffs(55, 47).addBox(-1.0F, -14.0F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -15.0F, 0.0F);
		body.addChild(head);
		head.texOffs(38, 15).addBox(-2.0F, -4.0F, -7.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
		head.texOffs(1, 19).addBox(-1.0F, -3.0F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		head.texOffs(0, 1).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(1.0F, -7.0F, 4.0F);
		head.addChild(bone);
		setRotationAngle(bone, 0.5236F, 0.3491F, 0.0F);
		bone.texOffs(3, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, 0.8192F, 0.4264F);
		bone.addChild(bone2);
		setRotationAngle(bone2, -0.48F, 0.0F, 0.0F);
		bone2.texOffs(54, 10).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, 0.0F, 2.0F);
		bone2.addChild(bone3);
		setRotationAngle(bone3, 0.3927F, 0.0F, 0.0F);
		bone3.texOffs(33, 57).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 6.0F, 1.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(-1.0F, -7.0F, 4.0F);
		head.addChild(bone4);
		setRotationAngle(bone4, 0.5236F, -0.3491F, 0.0F);
		bone4.texOffs(3, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(0.0F, 0.8192F, 0.4264F);
		bone4.addChild(bone5);
		setRotationAngle(bone5, -0.48F, 0.0F, 0.0F);
		bone5.texOffs(54, 10).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(0.0F, 0.0F, 2.0F);
		bone5.addChild(bone6);
		setRotationAngle(bone6, 0.3927F, 0.0F, 0.0F);
		bone6.texOffs(33, 57).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 6.0F, 1.0F, 0.0F, false);
	}

	@Override
	public Optional<ModelRenderer> getHeadModel() {
		return Optional.ofNullable(this.head);
	}
	
	@Override
	public Optional<ModelRenderer> getBodyModel() {
		return Optional.ofNullable(this.body);
	}
	
	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}
	
}
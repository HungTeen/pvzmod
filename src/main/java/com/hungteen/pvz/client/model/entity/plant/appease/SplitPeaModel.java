package com.hungteen.pvz.client.model.entity.plant.appease;

import java.util.Optional;

import com.hungteen.pvz.client.model.entity.plant.PlantShooterModel;
import com.hungteen.pvz.common.entity.plant.appease.SplitPeaEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class SplitPeaModel extends PlantShooterModel<SplitPeaEntity> {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer back;
	private final ModelRenderer mouth_r1;
	private final ModelRenderer head_r1;
	private final ModelRenderer front;
	private final ModelRenderer down;
	private final ModelRenderer n_r1;
	private final ModelRenderer w_r1;
	private final ModelRenderer e_r1;
	private final ModelRenderer s_r1;

	public SplitPeaModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);


		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		setRotationAngle(body, -0.0873F, 0.0F, 0.0F);
		body.texOffs(0, 31).addBox(-1.0F, -12.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -12.0F, 0.0F);
		body.addChild(head);
		setRotationAngle(head, 0.0873F, 0.0F, 0.0F);
		head.texOffs(40, 8).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);

		back = new ModelRenderer(this);
		back.setPos(0.0F, -2.6F, 8.2F);
		head.addChild(back);
		back.texOffs(30, 0).addBox(-2.0F, -1.0F, -0.7F, 4.0F, 4.0F, 3.0F, 0.0F, false);
		back.texOffs(10, 32).addBox(-5.0F, -5.0F, 1.0F, 10.0F, 1.0F, 0.0F, 0.0F, false);

		mouth_r1 = new ModelRenderer(this);
		mouth_r1.setPos(0.0F, 1.0F, 3.2F);
		back.addChild(mouth_r1);
		setRotationAngle(mouth_r1, 0.0F, 3.1416F, 0.0F);
		mouth_r1.texOffs(44, 0).addBox(-3.0F, -3.0F, -1.0F, 6.0F, 6.0F, 2.0F, 0.0F, false);

		head_r1 = new ModelRenderer(this);
		head_r1.setPos(0.0F, -1.0F, -4.0F);
		back.addChild(head_r1);
		setRotationAngle(head_r1, 0.0F, 3.1416F, 0.0F);
		head_r1.texOffs(0, 0).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 10.0F, 10.0F, -0.5F, false);

		front = new ModelRenderer(this);
		front.setPos(0.0F, 12.0F, 0.0F);
		head.addChild(front);
		front.texOffs(0, 0).addBox(-5.0F, -22.0F, -9.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);
		front.texOffs(30, 0).addBox(-2.0F, -17.0F, -12.0F, 4.0F, 4.0F, 3.0F, 0.0F, false);
		front.texOffs(44, 0).addBox(-3.0F, -18.0F, -13.0F, 6.0F, 6.0F, 2.0F, 0.0F, false);

		down = new ModelRenderer(this);
		down.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(down);


		n_r1 = new ModelRenderer(this);
		n_r1.setPos(0.0F, 0.8F, 0.0F);
		down.addChild(n_r1);
		setRotationAngle(n_r1, -0.1745F, -0.7854F, 0.0F);
		n_r1.texOffs(4, 20).addBox(-2.0F, -1.0F, -7.0F, 4.0F, 1.0F, 6.0F, 0.0F, false);

		w_r1 = new ModelRenderer(this);
		w_r1.setPos(0.0F, 0.8F, 0.0F);
		down.addChild(w_r1);
		setRotationAngle(w_r1, 0.1745F, -0.7854F, -0.1745F);
		w_r1.texOffs(6, 27).addBox(1.0F, -1.0F, -2.0F, 6.0F, 1.0F, 4.0F, 0.0F, false);

		e_r1 = new ModelRenderer(this);
		e_r1.setPos(0.0F, 0.8F, 0.0F);
		down.addChild(e_r1);
		setRotationAngle(e_r1, -0.1745F, -0.7854F, 0.1745F);
		e_r1.texOffs(26, 27).addBox(-7.0F, -1.0F, -2.0F, 6.0F, 1.0F, 4.0F, 0.0F, false);

		s_r1 = new ModelRenderer(this);
		s_r1.setPos(0.0F, 0.8F, 0.0F);
		down.addChild(s_r1);
		setRotationAngle(s_r1, 0.1745F, -0.7854F, 0.0F);
		s_r1.texOffs(24, 20).addBox(-2.0F, -1.0F, 1.0F, 4.0F, 1.0F, 6.0F, 0.0F, false);
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
		return Optional.ofNullable(this.head);
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
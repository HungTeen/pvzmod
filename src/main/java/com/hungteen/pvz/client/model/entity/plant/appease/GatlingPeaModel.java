package com.hungteen.pvz.client.model.entity.plant.appease;

import java.util.Optional;

import com.hungteen.pvz.client.model.entity.plant.PlantShooterModel;
import com.hungteen.pvz.common.entity.plant.appease.GatlingPeaEntity;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class GatlingPeaModel extends PlantShooterModel<GatlingPeaEntity> {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer barrel;
	private final ModelRenderer eyebrow;
	private final ModelRenderer down;
	private final ModelRenderer n_r1;
	private final ModelRenderer w_r1;
	private final ModelRenderer e_r1;
	private final ModelRenderer s_r1;

	public GatlingPeaModel() {
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
		head.texOffs(30, 0).addBox(-2.0F, -5.0F, -7.0F, 4.0F, 4.0F, 3.0F, 0.0F, false);
		head.texOffs(44, 0).addBox(-3.0F, -6.0F, -8.0F, 6.0F, 6.0F, 2.0F, 0.0F, false);
		head.texOffs(0, 0).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);
		head.texOffs(40, 8).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
		head.texOffs(0, 46).addBox(-5.5F, -6.0F, -4.5F, 11.0F, 7.0F, 11.0F, 0.0F, false);
		head.texOffs(20, 30).addBox(-5.5F, -11.0F, -5.5F, 11.0F, 5.0F, 11.0F, 0.0F, false);

		barrel = new ModelRenderer(this);
		barrel.setPos(0.0F, -3.0F, -9.5F);
		head.addChild(barrel);
		barrel.texOffs(50, 20).addBox(-2.0F, -2.0F, -1.5F, 4.0F, 4.0F, 3.0F, 0.0F, false);

		eyebrow = new ModelRenderer(this);
		eyebrow.setPos(0.0F, 12.0F, -1.0F);
		head.addChild(eyebrow);
		eyebrow.texOffs(41, 19).addBox(-5.0F, -21.25F, -4.6F, 10.0F, 1.0F, 0.0F, 0.0F, false);

		down = new ModelRenderer(this);
		down.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(down);


		n_r1 = new ModelRenderer(this);
		n_r1.setPos(0.0F, 0.8F, 0.0F);
		down.addChild(n_r1);
		setRotationAngle(n_r1, -0.1745F, -0.7854F, 0.0F);
		n_r1.texOffs(4, 20).addBox(-2.0F, -1.0F, -7.0F, 4.0F, 0.0F, 6.0F, 0.0F, false);

		w_r1 = new ModelRenderer(this);
		w_r1.setPos(0.0F, 0.8F, 0.0F);
		down.addChild(w_r1);
		setRotationAngle(w_r1, 0.1745F, -0.7854F, -0.1745F);
		w_r1.texOffs(6, 26).addBox(1.0F, -1.0F, -2.0F, 6.0F, 0.0F, 4.0F, 0.0F, false);

		e_r1 = new ModelRenderer(this);
		e_r1.setPos(0.0F, 0.8F, 0.0F);
		down.addChild(e_r1);
		setRotationAngle(e_r1, -0.1745F, -0.7854F, 0.1745F);
		e_r1.texOffs(26, 26).addBox(-7.0F, -1.0F, -2.0F, 6.0F, 0.0F, 4.0F, 0.0F, false);

		s_r1 = new ModelRenderer(this);
		s_r1.setPos(0.0F, 0.8F, 0.0F);
		down.addChild(s_r1);
		setRotationAngle(s_r1, 0.1745F, -0.7854F, 0.0F);
		s_r1.texOffs(24, 20).addBox(-2.0F, -1.0F, 1.0F, 4.0F, 0.0F, 6.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(GatlingPeaEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if(entity.animTime > 0) {
			this.barrel.zRot = ageInTicks / 5.0f % 100;
		} else {
			this.barrel.zRot = ageInTicks / 20.0f % 100;
		}
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

}
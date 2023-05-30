package com.hungteen.pvz.client.model.entity.plant.enforce;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.enforce.TangleKelpEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class TangleKelpModel extends PVZPlantModel<TangleKelpEntity> {
	private final ModelRenderer total;
	private final ModelRenderer c1;
	private final ModelRenderer c2;
	private final ModelRenderer f1;
	private final ModelRenderer n_r1;
	private final ModelRenderer s_r1;
	private final ModelRenderer f2;
	private final ModelRenderer n_r2;
	private final ModelRenderer s_r2;
	private final ModelRenderer f3;
	private final ModelRenderer n_r3;
	private final ModelRenderer s_r3;
	private final ModelRenderer f4;
	private final ModelRenderer n_r4;
	private final ModelRenderer s_r4;

	public TangleKelpModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(94, 0).addBox(-4.5F, -8.0F, -4.0F, 9.0F, 8.0F, 8.0F, 0.0F, false);
		total.texOffs(104, 16).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 8.0F, 6.0F, 0.0F, false);

		c1 = new ModelRenderer(this);
		c1.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(c1);
		c1.texOffs(0, 0).addBox(-5.5F, -18.0F, -5.0F, 11.0F, 19.0F, 10.0F, 0.0F, false);

		c2 = new ModelRenderer(this);
		c2.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(c2);
		c2.texOffs(0, 29).addBox(-5.5F, -18.0F, -5.0F, 11.0F, 19.0F, 10.0F, 0.0F, false);

		f1 = new ModelRenderer(this);
		f1.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(f1);
		f1.texOffs(54, -10).addBox(0.0F, -22.0F, -5.0F, 0.0F, 16.0F, 10.0F, 0.0F, false);

		n_r1 = new ModelRenderer(this);
		n_r1.setPos(0.0F, -11.0F, 0.0F);
		f1.addChild(n_r1);
		setRotationAngle(n_r1, -0.4363F, 0.0F, 0.0F);
		n_r1.texOffs(48, 0).addBox(-8.0F, -11.0F, 0.0F, 16.0F, 22.0F, 0.0F, 0.0F, false);

		s_r1 = new ModelRenderer(this);
		s_r1.setPos(0.0F, -11.0F, 0.0F);
		f1.addChild(s_r1);
		setRotationAngle(s_r1, 0.4363F, 0.0F, 0.0F);
		s_r1.texOffs(48, 44).addBox(-8.0F, -11.0F, 0.0F, 16.0F, 22.0F, 0.0F, 0.0F, false);

		f2 = new ModelRenderer(this);
		f2.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(f2);
		f2.texOffs(54, 12).addBox(0.0F, -22.0F, -5.0F, 0.0F, 16.0F, 10.0F, 0.0F, false);

		n_r2 = new ModelRenderer(this);
		n_r2.setPos(0.0F, -11.0F, 0.0F);
		f2.addChild(n_r2);
		setRotationAngle(n_r2, -0.4363F, 0.0F, 0.0F);
		n_r2.texOffs(48, 22).addBox(-8.0F, -11.0F, 0.0F, 16.0F, 22.0F, 0.0F, 0.0F, false);

		s_r2 = new ModelRenderer(this);
		s_r2.setPos(0.0F, -11.0F, 0.0F);
		f2.addChild(s_r2);
		setRotationAngle(s_r2, 0.4363F, 0.0F, 0.0F);
		s_r2.texOffs(48, 66).addBox(-8.0F, -11.0F, 0.0F, 16.0F, 22.0F, 0.0F, 0.0F, false);

		f3 = new ModelRenderer(this);
		f3.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(f3);
		f3.texOffs(54, 34).addBox(0.0F, -22.0F, -5.0F, 0.0F, 16.0F, 10.0F, 0.0F, false);

		n_r3 = new ModelRenderer(this);
		n_r3.setPos(0.0F, -11.0F, 0.0F);
		f3.addChild(n_r3);
		setRotationAngle(n_r3, -0.4363F, 0.0F, 0.0F);
		n_r3.texOffs(48, 44).addBox(-8.0F, -11.0F, 0.0F, 16.0F, 22.0F, 0.0F, 0.0F, false);

		s_r3 = new ModelRenderer(this);
		s_r3.setPos(0.0F, -11.0F, 0.0F);
		f3.addChild(s_r3);
		setRotationAngle(s_r3, 0.4363F, 0.0F, 0.0F);
		s_r3.texOffs(48, 0).addBox(-8.0F, -11.0F, 0.0F, 16.0F, 22.0F, 0.0F, 0.0F, false);

		f4 = new ModelRenderer(this);
		f4.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(f4);
		f4.texOffs(54, 56).addBox(0.0F, -22.0F, -5.0F, 0.0F, 16.0F, 10.0F, 0.0F, false);

		n_r4 = new ModelRenderer(this);
		n_r4.setPos(0.0F, -11.0F, 0.0F);
		f4.addChild(n_r4);
		setRotationAngle(n_r4, -0.4363F, 0.0F, 0.0F);
		n_r4.texOffs(48, 66).addBox(-8.0F, -11.0F, 0.0F, 16.0F, 22.0F, 0.0F, 0.0F, false);

		s_r4 = new ModelRenderer(this);
		s_r4.setPos(0.0F, -11.0F, 0.0F);
		f4.addChild(s_r4);
		setRotationAngle(s_r4, 0.4363F, 0.0F, 0.0F);
		s_r4.texOffs(48, 22).addBox(-8.0F, -11.0F, 0.0F, 16.0F, 22.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(TangleKelpEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.c1.visible = entity.getExistTick() % 12 > 6;
		this.c2.visible = entity.getExistTick() % 12 <= 6;
		this.f1.visible = false;
		this.f2.visible = false;
		this.f3.visible = false;
		this.f4.visible = false;
		if (entity.getExistTick() % 12 < 3) {
			this.f1.visible = true;
		}
		else if (entity.getExistTick() % 12 < 6) {
			this.f2.visible = true;
		}
		else if (entity.getExistTick() % 12 < 9) {
			this.f3.visible = true;
		}
		else {
			this.f4.visible = true;
		}
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<TangleKelpEntity> getPlantModel() {
		return this;
	}
}
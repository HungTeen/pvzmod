package com.hungteen.pvz.client.model.entity.plant.ice;

import java.util.Optional;

import com.hungteen.pvz.client.model.entity.plant.PlantShooterModel;
import com.hungteen.pvz.common.entity.plant.ice.SnowPeaEntity;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class SnowPeaModel extends PlantShooterModel<SnowPeaEntity> {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer hair;
	private final ModelRenderer u_r1;
	private final ModelRenderer m_r1;
	private final ModelRenderer wu_r1;
	private final ModelRenderer wd_r1;
	private final ModelRenderer ed_r1;
	private final ModelRenderer eu_r1;
	private final ModelRenderer down;
	private final ModelRenderer n_r1;
	private final ModelRenderer w_r1;
	private final ModelRenderer e_r1;
	private final ModelRenderer s_r1;

	public SnowPeaModel() {
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
		head.texOffs(30, 0).addBox(-2.0F, -5.0F, -8.0F, 4.0F, 4.0F, 3.0F, 0.0F, false);
		head.texOffs(44, 0).addBox(-3.0F, -6.0F, -9.0F, 6.0F, 6.0F, 2.0F, 0.0F, false);
		head.texOffs(0, 0).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);
		head.texOffs(40, 8).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, false);

		hair = new ModelRenderer(this);
		hair.setPos(-1.0F, -4.5F, 5.0F);
		head.addChild(hair);
		hair.texOffs(0, 56).addBox(-2.0F, -3.5F, 0.0F, 6.0F, 6.0F, 2.0F, 0.0F, false);
		hair.texOffs(2, 49).addBox(-1.0F, 0.5F, -1.0F, 3.0F, 3.0F, 4.0F, 0.0F, false);

		u_r1 = new ModelRenderer(this);
		u_r1.setPos(0.5F, -1.0F, 0.0F);
		hair.addChild(u_r1);
		setRotationAngle(u_r1, 0.2618F, -0.1745F, 0.0873F);
		u_r1.texOffs(16, 55).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 6.0F, 0.0F, false);

		m_r1 = new ModelRenderer(this);
		m_r1.setPos(2.5F, -1.0F, 0.0F);
		hair.addChild(m_r1);
		setRotationAngle(m_r1, -0.1745F, 0.2618F, 0.0F);
		m_r1.texOffs(32, 49).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);

		wu_r1 = new ModelRenderer(this);
		wu_r1.setPos(3.5F, -3.0F, 0.0F);
		hair.addChild(wu_r1);
		setRotationAngle(wu_r1, 0.5236F, 0.2618F, 0.0F);
		wu_r1.texOffs(48, 56).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);

		wd_r1 = new ModelRenderer(this);
		wd_r1.setPos(3.5F, 2.0F, 0.0F);
		hair.addChild(wd_r1);
		setRotationAngle(wd_r1, -0.5236F, 0.5236F, 0.0F);
		wd_r1.texOffs(48, 48).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);

		ed_r1 = new ModelRenderer(this);
		ed_r1.setPos(-1.5F, 2.0F, 0.0F);
		hair.addChild(ed_r1);
		setRotationAngle(ed_r1, -0.4363F, -0.5236F, 0.0F);
		ed_r1.texOffs(16, 47).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 5.0F, 0.0F, false);

		eu_r1 = new ModelRenderer(this);
		eu_r1.setPos(-1.5F, -3.0F, 0.0F);
		hair.addChild(eu_r1);
		setRotationAngle(eu_r1, 0.5236F, -0.5236F, 0.0F);
		eu_r1.texOffs(34, 57).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 4.0F, 0.0F, false);

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
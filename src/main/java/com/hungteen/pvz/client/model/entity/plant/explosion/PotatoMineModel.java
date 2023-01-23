package com.hungteen.pvz.client.model.entity.plant.explosion;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.explosion.PotatoMineEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class PotatoMineModel extends PVZPlantModel<PotatoMineEntity> {
	private final ModelRenderer total;
	private final ModelRenderer dirt;
	private final ModelRenderer ne;
	private final ModelRenderer sw;
	private final ModelRenderer se;
	private final ModelRenderer nw;
	private final ModelRenderer body;
	private final ModelRenderer red;
	private final ModelRenderer white;

	public PotatoMineModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 25.0F, 0.0F);


		dirt = new ModelRenderer(this);
		dirt.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(dirt);


		ne = new ModelRenderer(this);
		ne.setPos(0.0F, 0.0F, 0.0F);
		dirt.addChild(ne);
		ne.texOffs(5, 56).addBox(-6.5F, -4.0F, -6.5F, 2.0F, 3.0F, 2.0F, 0.0F, true);
		ne.texOffs(4, 55).addBox(-4.5F, -2.0F, -6.5F, 1.0F, 1.0F, 2.0F, 0.0F, true);
		ne.texOffs(5, 57).addBox(-2.5F, -2.0F, -6.5F, 2.0F, 1.0F, 2.0F, 0.0F, true);
		ne.texOffs(0, 58).addBox(-6.5F, -3.0F, -4.5F, 2.0F, 2.0F, 3.0F, 0.0F, true);

		sw = new ModelRenderer(this);
		sw.setPos(0.0F, 0.0F, 0.0F);
		dirt.addChild(sw);
		sw.texOffs(2, 51).addBox(0.5F, -2.0F, 4.5F, 3.0F, 1.0F, 2.0F, 0.0F, true);
		sw.texOffs(2, 59).addBox(3.5F, -3.0F, 3.5F, 3.0F, 2.0F, 3.0F, 0.0F, true);
		sw.texOffs(5, 55).addBox(4.5F, -2.0F, 0.5F, 2.0F, 1.0F, 3.0F, 0.0F, true);

		se = new ModelRenderer(this);
		se.setPos(0.0F, 0.0F, 0.0F);
		dirt.addChild(se);
		se.texOffs(1, 55).addBox(-6.5F, -4.0F, 3.5F, 4.0F, 3.0F, 3.0F, 0.0F, true);
		se.texOffs(0, 58).addBox(-6.5F, -2.0F, -0.5F, 3.0F, 1.0F, 4.0F, 0.0F, true);
		se.texOffs(4, 52).addBox(-2.5F, -2.0F, 4.5F, 1.0F, 1.0F, 2.0F, 0.0F, true);

		nw = new ModelRenderer(this);
		nw.setPos(0.0F, 0.0F, 0.0F);
		dirt.addChild(nw);
		nw.texOffs(1, 55).addBox(4.5F, -3.0F, -6.5F, 2.0F, 2.0F, 3.0F, 0.0F, true);
		nw.texOffs(0, 53).addBox(1.5F, -2.0F, -6.5F, 3.0F, 1.0F, 2.0F, 0.0F, true);
		nw.texOffs(2, 58).addBox(5.5F, -2.0F, -3.5F, 1.0F, 1.0F, 2.0F, 0.0F, true);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		setRotationAngle(body, -0.0873F, 0.0F, 0.0F);
		body.texOffs(0, 0).addBox(-6.0F, -7.0038F, -5.9128F, 12.0F, 8.0F, 12.0F, 0.0F, false);
		body.texOffs(0, 25).addBox(0.0F, -10.0038F, -0.9128F, 0.0F, 3.0F, 2.0F, 0.0F, false);
		body.texOffs(40, 25).addBox(-3.0F, -7.5038F, -2.9128F, 6.0F, 1.0F, 6.0F, 0.0F, false);
		body.texOffs(14, 27).addBox(-1.0F, -10.0038F, 0.0872F, 2.0F, 3.0F, 0.0F, 0.0F, false);

		red = new ModelRenderer(this);
		red.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(red);
		red.texOffs(48, 8).addBox(-2.0F, -14.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		white = new ModelRenderer(this);
		white.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(white);
		white.texOffs(48, 0).addBox(-2.0F, -14.0038F, -1.9128F, 4.0F, 4.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(PotatoMineEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		final int T = (entity.getSignChangeCD() << 1);
		final int current = entity.getExistTick() % T;
		final boolean flag = (current < (T >> 1));
		this.red.visible = flag;
		this.white.visible = !flag;
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<PotatoMineEntity> getPlantModel() {
		return this;
	}
}
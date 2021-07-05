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
	private final ModelRenderer body;
	private final ModelRenderer bone;
	private final ModelRenderer string;
	private final ModelRenderer red;
	private final ModelRenderer white;

	public PotatoMineModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 17.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		body.texOffs(1, 105).addBox(-10.0F, 5.0F, -10.0F, 20.0F, 2.0F, 20.0F, 0.0F, false);
		body.texOffs(4, 82).addBox(-9.0F, 2.0F, -9.0F, 18.0F, 3.0F, 18.0F, 0.0F, false);
		body.texOffs(4, 61).addBox(-8.0F, -1.0F, -8.0F, 16.0F, 3.0F, 16.0F, 0.0F, false);
		body.texOffs(78, 2).addBox(-6.0F, -2.0F, -6.0F, 12.0F, 1.0F, 12.0F, 0.0F, false);
		body.texOffs(21, 24).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 1.0F, 6.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(bone);
		

		string = new ModelRenderer(this);
		string.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(string);
		string.texOffs(8, 34).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		red = new ModelRenderer(this);
		red.setPos(0.0F, 0.0F, 0.0F);
		string.addChild(red);
		red.texOffs(111, 22).addBox(-2.0F, -12.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		white = new ModelRenderer(this);
		white.setPos(0.0F, 0.0F, 0.0F);
		string.addChild(white);
		white.texOffs(110, 34).addBox(-2.0F, -12.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(PotatoMineEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		final int T = (entity.getSignChangeCD() << 1);
		final int current = entity.getExistTick() % T;
		final boolean flag = (current < (T >> 1));
		this.red.visible = flag;
		this.white.visible = ! flag;
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
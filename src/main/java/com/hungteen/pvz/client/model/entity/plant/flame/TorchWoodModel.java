package com.hungteen.pvz.client.model.entity.plant.flame;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.flame.TorchWoodEntity;
import com.hungteen.pvz.common.entity.plant.flame.TorchWoodEntity.FlameTypes;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class TorchWoodModel extends PVZPlantModel<TorchWoodEntity> {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer hair;
	private final ModelRenderer hair1;
	private final ModelRenderer hair2;
	private final ModelRenderer hair3;
	private final ModelRenderer hair4;
	private final ModelRenderer yellow;
	private final ModelRenderer yellow_core;
	private final ModelRenderer yellow_eyes;
	private final ModelRenderer blue;
	private final ModelRenderer blue_core;
	private final ModelRenderer blue_eyes;
	private final ModelRenderer purple;
	private final ModelRenderer purple_core;
	private final ModelRenderer purple_eyes;
	private final ModelRenderer legs;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer leg5;

	public TorchWoodModel() {
		texWidth = 256;
		texHeight = 256;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		body.texOffs(149, 199).addBox(-13.0F, -30.0F, -13.0F, 26.0F, 27.0F, 26.0F, 0.0F, false);
		body.texOffs(91, 222).addBox(-12.0F, -32.0F, -14.0F, 24.0F, 29.0F, 1.0F, 0.0F, false);
		body.texOffs(33, 220).addBox(-12.0F, -32.0F, 13.0F, 24.0F, 29.0F, 1.0F, 0.0F, false);
		body.texOffs(22, 156).addBox(-14.0F, -32.0F, -12.0F, 1.0F, 29.0F, 24.0F, 0.0F, false);
		body.texOffs(82, 164).addBox(13.0F, -32.0F, -12.0F, 1.0F, 29.0F, 24.0F, 0.0F, false);

		hair = new ModelRenderer(this);
		hair.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(hair);
		

		hair1 = new ModelRenderer(this);
		hair1.setPos(0.0F, -32.0F, -14.0F);
		hair.addChild(hair1);
		setRotationAngle(hair1, -0.7854F, 0.0F, 0.0F);
		hair1.texOffs(200, 187).addBox(-12.0F, -1.0F, -2.0F, 24.0F, 1.0F, 3.0F, 0.0F, false);

		hair2 = new ModelRenderer(this);
		hair2.setPos(0.0F, -32.0F, 14.0F);
		hair.addChild(hair2);
		setRotationAngle(hair2, 0.7854F, 0.0F, 0.0F);
		hair2.texOffs(196, 174).addBox(-12.0F, -1.0F, -1.0F, 24.0F, 1.0F, 3.0F, 0.0F, false);

		hair3 = new ModelRenderer(this);
		hair3.setPos(-13.0F, -32.0F, 0.0F);
		hair.addChild(hair3);
		setRotationAngle(hair3, 0.0F, 0.0F, 0.7854F);
		hair3.texOffs(200, 140).addBox(-3.0F, 0.0F, -12.0F, 3.0F, 1.0F, 24.0F, 0.0F, false);

		hair4 = new ModelRenderer(this);
		hair4.setPos(13.0F, -32.0F, 0.0F);
		hair.addChild(hair4);
		setRotationAngle(hair4, 0.0F, 0.0F, -0.7854F);
		hair4.texOffs(201, 108).addBox(0.0F, 0.0F, -12.0F, 3.0F, 1.0F, 24.0F, 0.0F, false);

		yellow = new ModelRenderer(this);
		yellow.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(yellow);
		

		yellow_core = new ModelRenderer(this);
		yellow_core.setPos(0.0F, 0.0F, 0.0F);
		yellow.addChild(yellow_core);
		yellow_core.texOffs(1, 1).addBox(-13.0F, -30.75F, -13.0F, 26.0F, 1.0F, 26.0F, -0.3F, false);

		yellow_eyes = new ModelRenderer(this);
		yellow_eyes.setPos(-4.0F, -20.0F, -4.5F);
		yellow.addChild(yellow_eyes);
		yellow_eyes.texOffs(2, 2).addBox(-7.0F, -4.0F, -10.0F, 7.0F, 4.0F, 1.0F, -0.4F, false);
		yellow_eyes.texOffs(3, 10).addBox(8.0F, -4.0F, -10.0F, 7.0F, 4.0F, 1.0F, -0.4F, false);

		blue = new ModelRenderer(this);
		blue.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(blue);
		

		blue_core = new ModelRenderer(this);
		blue_core.setPos(0.0F, 0.0F, 0.0F);
		blue.addChild(blue_core);
		blue_core.texOffs(2, 31).addBox(-13.0F, -30.75F, -13.0F, 26.0F, 1.0F, 26.0F, -0.3F, false);

		blue_eyes = new ModelRenderer(this);
		blue_eyes.setPos(-4.0F, -20.0F, -4.5F);
		blue.addChild(blue_eyes);
		blue_eyes.texOffs(3, 34).addBox(-7.0F, -4.0F, -10.0F, 7.0F, 4.0F, 1.0F, -0.4F, false);
		blue_eyes.texOffs(4, 43).addBox(8.0F, -4.0F, -10.0F, 7.0F, 4.0F, 1.0F, -0.4F, false);

		purple = new ModelRenderer(this);
		purple.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(purple);
		

		purple_core = new ModelRenderer(this);
		purple_core.setPos(0.0F, 0.0F, 0.0F);
		purple.addChild(purple_core);
		purple_core.texOffs(2, 61).addBox(-13.0F, -30.75F, -13.0F, 26.0F, 1.0F, 26.0F, -0.3F, false);

		purple_eyes = new ModelRenderer(this);
		purple_eyes.setPos(-4.0F, -20.0F, -4.5F);
		purple.addChild(purple_eyes);
		purple_eyes.texOffs(5, 65).addBox(-7.0F, -4.0F, -10.0F, 7.0F, 4.0F, 1.0F, -0.4F, false);
		purple_eyes.texOffs(5, 73).addBox(8.0F, -4.0F, -10.0F, 7.0F, 4.0F, 1.0F, -0.4F, false);

		legs = new ModelRenderer(this);
		legs.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(legs);
		

		leg1 = new ModelRenderer(this);
		leg1.setPos(0.0F, 0.0F, 0.0F);
		legs.addChild(leg1);
		leg1.texOffs(184, 9).addBox(-5.0F, -1.0F, -19.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		leg1.texOffs(185, 2).addBox(-5.0F, -3.0F, -17.0F, 10.0F, 3.0F, 25.0F, 0.0F, false);
		leg1.texOffs(184, 3).addBox(-5.0F, -2.0F, -18.0F, 10.0F, 2.0F, 1.0F, 0.0F, false);

		leg2 = new ModelRenderer(this);
		leg2.setPos(0.0F, 0.0F, 0.0F);
		legs.addChild(leg2);
		setRotationAngle(leg2, 0.0F, -1.1345F, 0.0F);
		leg2.texOffs(184, 9).addBox(-5.0F, -1.0F, -19.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		leg2.texOffs(185, 2).addBox(-5.0F, -3.0F, -17.0F, 10.0F, 3.0F, 25.0F, 0.0F, false);
		leg2.texOffs(184, 3).addBox(-5.0F, -2.0F, -18.0F, 10.0F, 2.0F, 1.0F, 0.0F, false);

		leg3 = new ModelRenderer(this);
		leg3.setPos(0.0F, 0.0F, 0.0F);
		legs.addChild(leg3);
		setRotationAngle(leg3, 0.0F, 1.1345F, 0.0F);
		leg3.texOffs(184, 9).addBox(-5.0F, -1.0F, -19.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		leg3.texOffs(185, 2).addBox(-5.0F, -3.0F, -17.0F, 10.0F, 3.0F, 25.0F, 0.0F, false);
		leg3.texOffs(184, 3).addBox(-5.0F, -2.0F, -18.0F, 10.0F, 2.0F, 1.0F, 0.0F, false);

		leg4 = new ModelRenderer(this);
		leg4.setPos(0.0F, 0.0F, 0.0F);
		legs.addChild(leg4);
		setRotationAngle(leg4, 0.0F, -2.3562F, 0.0F);
		leg4.texOffs(184, 9).addBox(-5.0F, -1.0F, -19.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		leg4.texOffs(185, 2).addBox(-5.0F, -3.0F, -17.0F, 10.0F, 3.0F, 25.0F, 0.0F, false);
		leg4.texOffs(184, 3).addBox(-5.0F, -2.0F, -18.0F, 10.0F, 2.0F, 1.0F, 0.0F, false);

		leg5 = new ModelRenderer(this);
		leg5.setPos(0.0F, 0.0F, 0.0F);
		legs.addChild(leg5);
		setRotationAngle(leg5, 0.0F, 2.3562F, 0.0F);
		leg5.texOffs(184, 9).addBox(-5.0F, -1.0F, -19.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		leg5.texOffs(185, 2).addBox(-5.0F, -3.0F, -17.0F, 10.0F, 3.0F, 25.0F, 0.0F, false);
		leg5.texOffs(184, 3).addBox(-5.0F, -2.0F, -18.0F, 10.0F, 2.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(TorchWoodEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.yellow.visible = entity.getFlameType() == FlameTypes.YELLOW;
		this.blue.visible = entity.getFlameType() == FlameTypes.BLUE;
		this.purple.visible = entity.getFlameType() == FlameTypes.PURPLE;
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<TorchWoodEntity> getPlantModel() {
		return this;
	}
}
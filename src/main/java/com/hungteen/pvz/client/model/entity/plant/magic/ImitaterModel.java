package com.hungteen.pvz.client.model.entity.plant.magic;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.magic.ImitaterEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class ImitaterModel extends PVZPlantModel<ImitaterEntity> {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer hands;
	private final ModelRenderer hand1;
	private final ModelRenderer hand2;
	private final ModelRenderer hand3;
	private final ModelRenderer hand4;
	private final ModelRenderer face;
	private final ModelRenderer cube_r3;

	public ImitaterModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		body.texOffs(0, 53).addBox(-4.5F, -17.0F, -3.0F, 9.0F, 5.0F, 6.0F, 0.0F, false);
		body.texOffs(30, 52).addBox(-4.5F, -6.25F, -3.0F, 9.0F, 6.0F, 6.0F, 0.4F, false);
		body.texOffs(0, 41).addBox(-4.5F, -12.25F, -3.0F, 9.0F, 6.0F, 6.0F, 0.2F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -17.0F, 0.0F);
		total.addChild(head);
		head.texOffs(0, 57).addBox(-1.0F, -2.25F, 0.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(-1.5F, -0.75F, -1.5F);
		head.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, -0.1309F);
		cube_r1.texOffs(48, 35).addBox(-4.0F, 0.0F, -2.0F, 1.0F, 1.0F, 7.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(-0.75F, -0.5F, -1.5F);
		head.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, -0.1309F);
		cube_r2.texOffs(32, 43).addBox(-4.0F, -1.0F, -2.0F, 9.0F, 2.0F, 7.0F, 0.1F, false);

		hands = new ModelRenderer(this);
		hands.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(hands);
		

		hand1 = new ModelRenderer(this);
		hand1.setPos(-4.0F, -10.0F, 0.0F);
		hands.addChild(hand1);
		setRotationAngle(hand1, 0.0F, 0.0F, 0.3054F);
		hand1.texOffs(56, 56).addBox(-3.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, -0.25F, false);

		hand2 = new ModelRenderer(this);
		hand2.setPos(-4.0F, -5.0F, 0.0F);
		hands.addChild(hand2);
		setRotationAngle(hand2, 0.0F, 0.48F, -0.1745F);
		hand2.texOffs(56, 54).addBox(-3.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, -0.25F, false);

		hand3 = new ModelRenderer(this);
		hand3.setPos(4.0F, -3.0F, 0.0F);
		hands.addChild(hand3);
		setRotationAngle(hand3, 0.0F, -2.6616F, 0.6981F);
		hand3.texOffs(56, 52).addBox(-3.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, -0.25F, false);

		hand4 = new ModelRenderer(this);
		hand4.setPos(3.0F, -14.0F, 2.0F);
		hands.addChild(hand4);
		setRotationAngle(hand4, 0.0F, 2.7053F, 0.6981F);
		hand4.texOffs(28, 56).addBox(-3.0F, -1.0F, 0.0F, 3.0F, 1.0F, 1.0F, -0.25F, false);

		face = new ModelRenderer(this);
		face.setPos(0.0F, -18.0F, 0.5F);
		total.addChild(face);
		face.texOffs(31, 46).addBox(-3.5F, 2.0F, -4.0F, 3.0F, 3.0F, 1.0F, -0.45F, false);
		face.texOffs(32, 53).addBox(-2.5F, 3.0F, -3.85F, 1.0F, 1.0F, 1.0F, -0.25F, false);
		face.texOffs(32, 53).addBox(1.5F, 3.0F, -3.85F, 1.0F, 1.0F, 1.0F, -0.25F, false);
		face.texOffs(24, 43).addBox(0.5F, 2.0F, -4.0F, 3.0F, 3.0F, 1.0F, -0.45F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(2.0F, 4.5F, -3.5F);
		face.addChild(cube_r3);
		setRotationAngle(cube_r3, -0.0436F, 0.0F, 0.0F);
		cube_r3.texOffs(0, 54).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 2.0F, 1.0F, -0.45F, false);
		cube_r3.texOffs(32, 53).addBox(-4.5F, -0.5F, -0.5F, 1.0F, 2.0F, 1.0F, -0.45F, false);
	}

	@Override
	public void setupAnim(ImitaterEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.total.yRot = ageInTicks / 3;
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<ImitaterEntity> getPlantModel() {
		return this;
	}
}
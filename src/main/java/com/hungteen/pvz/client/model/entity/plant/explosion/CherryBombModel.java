package com.hungteen.pvz.client.model.entity.plant.explosion;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.explosion.CherryBombEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class CherryBombModel extends PVZPlantModel<CherryBombEntity> {
	private final ModelRenderer body;
	private final ModelRenderer bone;
	private final ModelRenderer bone3;
	private final ModelRenderer bone2;
	private final ModelRenderer head1;
	private final ModelRenderer head2;

	public CherryBombModel() {
		texWidth = 256;
		texHeight = 256;

		body = new ModelRenderer(this);
		body.setPos(0.0F, 24.0F, 0.0F);
		

		bone = new ModelRenderer(this);
		bone.setPos(-9.0F, -18.0F, 0.0F);
		body.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, 0.7854F);
		bone.texOffs(52, 105).addBox(-2.0F, -17.0F, -1.0F, 2.0F, 20.0F, 2.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, -16.0F, 0.0F);
		bone.addChild(bone3);
		setRotationAngle(bone3, 0.0F, -0.3491F, 0.6981F);
		bone3.texOffs(36, 113).addBox(-1.0F, -8.0F, -3.0F, 1.0F, 8.0F, 6.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(9.0F, -18.0F, 0.0F);
		body.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, -0.7854F);
		bone2.texOffs(23, 108).addBox(0.0F, -13.0F, -1.0F, 2.0F, 16.0F, 2.0F, 0.0F, false);

		head1 = new ModelRenderer(this);
		head1.setPos(-11.0F, -18.0F, 0.0F);
		body.addChild(head1);
		setRotationAngle(head1, 0.0F, 0.2618F, 0.0F);
		head1.texOffs(63, 61).addBox(-8.0F, 1.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);
		head1.texOffs(97, 30).addBox(8.0F, 2.0F, -7.0F, 1.0F, 14.0F, 14.0F, 0.0F, false);
		head1.texOffs(64, 31).addBox(-9.0F, 2.0F, -7.0F, 1.0F, 14.0F, 14.0F, 0.0F, false);
		head1.texOffs(97, 14).addBox(-7.0F, 2.0F, -9.0F, 14.0F, 14.0F, 1.0F, 0.0F, false);
		head1.texOffs(64, 13).addBox(-7.0F, 2.0F, 8.0F, 14.0F, 14.0F, 1.0F, 0.0F, false);
		head1.texOffs(0, 85).addBox(-7.0F, 0.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);
		head1.texOffs(1, 68).addBox(-7.0F, 17.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);

		head2 = new ModelRenderer(this);
		head2.setPos(11.0F, -18.0F, 0.0F);
		body.addChild(head2);
		setRotationAngle(head2, 0.0F, -0.2618F, 0.0F);
		head2.texOffs(63, 95).addBox(-8.0F, 1.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);
		head2.texOffs(29, 37).addBox(8.0F, 2.0F, -7.0F, 1.0F, 14.0F, 14.0F, 0.0F, false);
		head2.texOffs(3, 7).addBox(-9.0F, 2.0F, -7.0F, 1.0F, 14.0F, 14.0F, 0.0F, false);
		head2.texOffs(4, 140).addBox(-7.0F, 2.0F, -9.0F, 14.0F, 14.0F, 1.0F, 0.0F, false);
		head2.texOffs(44, 139).addBox(-7.0F, 2.0F, 8.0F, 14.0F, 14.0F, 1.0F, 0.0F, false);
		head2.texOffs(84, 138).addBox(-7.0F, 0.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);
		head2.texOffs(8, 163).addBox(-7.0F, 17.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(CherryBombEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.body;
	}

	@Override
	public EntityModel<CherryBombEntity> getPlantModel() {
		return this;
	}
}
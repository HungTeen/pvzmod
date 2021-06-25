package com.hungteen.pvz.client.model.entity.plant.flame;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.flame.TorchWoodEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class TorchWoodModel extends PVZPlantModel<TorchWoodEntity> {
	private final ModelRenderer total;
	private final ModelRenderer bone6;
	private final ModelRenderer bone5;
	private final ModelRenderer bone4;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone;
	private final ModelRenderer body;
	private final ModelRenderer head1;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ModelRenderer head4;

	public TorchWoodModel() {
		texWidth = 256;
		texHeight = 256;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		bone6 = new ModelRenderer(this);
		bone6.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 2.3562F, 0.0F);
		bone6.texOffs(96, 2).addBox(-5.0F, -1.0F, -19.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		bone6.texOffs(96, 2).addBox(-5.0F, -3.0F, -17.0F, 10.0F, 3.0F, 25.0F, 0.0F, false);
		bone6.texOffs(96, 2).addBox(-5.0F, -2.0F, -18.0F, 10.0F, 2.0F, 1.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(bone5);
		setRotationAngle(bone5, 0.0F, -2.3562F, 0.0F);
		bone5.texOffs(94, 42).addBox(-5.0F, -1.0F, -19.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		bone5.texOffs(94, 42).addBox(-5.0F, -3.0F, -17.0F, 10.0F, 3.0F, 25.0F, 0.0F, false);
		bone5.texOffs(94, 42).addBox(-5.0F, -2.0F, -18.0F, 10.0F, 2.0F, 1.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(bone4);
		setRotationAngle(bone4, 0.0F, -1.1345F, 0.0F);
		bone4.texOffs(130, 114).addBox(-5.0F, -1.0F, -19.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		bone4.texOffs(130, 114).addBox(-5.0F, -3.0F, -17.0F, 10.0F, 3.0F, 25.0F, 0.0F, false);
		bone4.texOffs(130, 114).addBox(-5.0F, -2.0F, -18.0F, 10.0F, 2.0F, 1.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 1.1345F, 0.0F);
		bone2.texOffs(174, 11).addBox(-5.0F, -1.0F, -19.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		bone2.texOffs(174, 11).addBox(-5.0F, -3.0F, -17.0F, 10.0F, 3.0F, 25.0F, 0.0F, false);
		bone2.texOffs(174, 11).addBox(-5.0F, -2.0F, -18.0F, 10.0F, 2.0F, 1.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, 0.0F, 0.0F);
		bone2.addChild(bone3);
		bone3.texOffs(174, 11).addBox(-4.0F, -1.0F, -19.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		bone3.texOffs(174, 11).addBox(-4.0F, -3.0F, -17.0F, 8.0F, 3.0F, 25.0F, 0.0F, false);
		bone3.texOffs(174, 11).addBox(-4.0F, -2.0F, -18.0F, 8.0F, 2.0F, 1.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(bone);
		bone.texOffs(180, 68).addBox(-5.0F, -1.0F, -19.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);
		bone.texOffs(180, 68).addBox(-5.0F, -3.0F, -17.0F, 10.0F, 3.0F, 25.0F, 0.0F, false);
		bone.texOffs(180, 68).addBox(-5.0F, -2.0F, -18.0F, 10.0F, 2.0F, 1.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		body.texOffs(149, 199).addBox(-13.0F, -30.0F, -13.0F, 26.0F, 27.0F, 26.0F, 0.0F, false);
		body.texOffs(91, 222).addBox(-12.0F, -32.0F, -14.0F, 24.0F, 29.0F, 1.0F, 0.0F, false);
		body.texOffs(33, 220).addBox(-12.0F, -32.0F, 13.0F, 24.0F, 29.0F, 1.0F, 0.0F, false);
		body.texOffs(22, 156).addBox(-14.0F, -32.0F, -12.0F, 1.0F, 29.0F, 24.0F, 0.0F, false);
		body.texOffs(82, 164).addBox(13.0F, -32.0F, -12.0F, 1.0F, 29.0F, 24.0F, 0.0F, false);

		head1 = new ModelRenderer(this);
		head1.setPos(0.0F, -32.0F, -14.0F);
		body.addChild(head1);
		setRotationAngle(head1, -0.7854F, 0.0F, 0.0F);
		head1.texOffs(200, 187).addBox(-12.0F, -1.0F, -2.0F, 24.0F, 1.0F, 3.0F, 0.0F, false);

		head2 = new ModelRenderer(this);
		head2.setPos(0.0F, -32.0F, 14.0F);
		body.addChild(head2);
		setRotationAngle(head2, 0.7854F, 0.0F, 0.0F);
		head2.texOffs(196, 174).addBox(-12.0F, -1.0F, -1.0F, 24.0F, 1.0F, 3.0F, 0.0F, false);

		head3 = new ModelRenderer(this);
		head3.setPos(-13.0F, -32.0F, 0.0F);
		body.addChild(head3);
		setRotationAngle(head3, 0.0F, 0.0F, 0.7854F);
		head3.texOffs(200, 140).addBox(-3.0F, 0.0F, -12.0F, 3.0F, 1.0F, 24.0F, 0.0F, false);

		head4 = new ModelRenderer(this);
		head4.setPos(13.0F, -32.0F, 0.0F);
		body.addChild(head4);
		setRotationAngle(head4, 0.0F, 0.0F, -0.7854F);
		head4.texOffs(201, 108).addBox(0.0F, 0.0F, -12.0F, 3.0F, 1.0F, 24.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(TorchWoodEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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
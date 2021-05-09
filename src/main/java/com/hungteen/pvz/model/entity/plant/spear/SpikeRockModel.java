package com.hungteen.pvz.model.entity.plant.spear;

import com.hungteen.pvz.entity.plant.spear.SpikeRockEntity;
import com.hungteen.pvz.utils.AnimationUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class SpikeRockModel extends EntityModel<SpikeRockEntity> {
	private final ModelRenderer total;
	private final ModelRenderer t1;
	private final ModelRenderer spike1;
	private final ModelRenderer t2;
	private final ModelRenderer spike2;
	private final ModelRenderer t3;
	private final ModelRenderer spike3;
	private final ModelRenderer spikes;
	private final ModelRenderer spike15;
	private final ModelRenderer spike14;
	private final ModelRenderer spike13;
	private final ModelRenderer spike12;
	private final ModelRenderer spike11;
	private final ModelRenderer spike10;
	private final ModelRenderer spike9;
	private final ModelRenderer spike8;
	private final ModelRenderer spike7;
	private final ModelRenderer spike6;
	private final ModelRenderer spike5;
	private final ModelRenderer spike4;

	public SpikeRockModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(31, 102).addBox(-12.0F, -1.0F, -12.0F, 24.0F, 1.0F, 24.0F, 0.0F, false);

		t1 = new ModelRenderer(this);
		t1.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(t1);
		t1.texOffs(119, 89).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, -0.05F, false);

		spike1 = new ModelRenderer(this);
		spike1.setPos(0.0F, -4.0F, 0.0F);
		t1.addChild(spike1);
		setRotationAngle(spike1, 0.0F, 0.0F, 0.7854F);
		spike1.texOffs(119, 96).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		t2 = new ModelRenderer(this);
		t2.setPos(10.0F, 0.0F, 0.0F);
		total.addChild(t2);
		setRotationAngle(t2, 0.0F, 0.0F, 0.1745F);
		t2.texOffs(108, 87).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, -0.05F, false);

		spike2 = new ModelRenderer(this);
		spike2.setPos(0.0F, -4.0F, 0.0F);
		t2.addChild(spike2);
		setRotationAngle(spike2, 0.0F, 0.0F, 0.7854F);
		spike2.texOffs(108, 95).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		t3 = new ModelRenderer(this);
		t3.setPos(-10.0F, 0.0F, 0.0F);
		total.addChild(t3);
		setRotationAngle(t3, 0.0F, 0.0F, -0.1745F);
		t3.texOffs(98, 87).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, -0.05F, false);

		spike3 = new ModelRenderer(this);
		spike3.setPos(0.0F, -4.0F, 0.0F);
		t3.addChild(spike3);
		setRotationAngle(spike3, 0.0F, 0.0F, 0.7854F);
		spike3.texOffs(96, 95).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		spikes = new ModelRenderer(this);
		spikes.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(spikes);
		

		spike15 = new ModelRenderer(this);
		spike15.setPos(5.0F, -1.0F, 0.0F);
		spikes.addChild(spike15);
		setRotationAngle(spike15, 0.7854F, 0.0F, 0.0F);
		spike15.texOffs(90, 72).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		spike14 = new ModelRenderer(this);
		spike14.setPos(-5.0F, -1.0F, 0.0F);
		spikes.addChild(spike14);
		setRotationAngle(spike14, 0.7854F, 0.0F, 0.0F);
		spike14.texOffs(105, 72).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		spike13 = new ModelRenderer(this);
		spike13.setPos(6.0F, -1.0F, 5.0F);
		spikes.addChild(spike13);
		setRotationAngle(spike13, 0.7854F, 0.7854F, 0.0F);
		spike13.texOffs(118, 74).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		spike12 = new ModelRenderer(this);
		spike12.setPos(-6.0F, -1.0F, 5.0F);
		spikes.addChild(spike12);
		setRotationAngle(spike12, -0.7854F, -0.7854F, 0.0F);
		spike12.texOffs(75, 81).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		spike11 = new ModelRenderer(this);
		spike11.setPos(0.0F, -1.0F, 9.0F);
		spikes.addChild(spike11);
		setRotationAngle(spike11, 0.0F, 0.0F, 0.7854F);
		spike11.texOffs(85, 81).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		spike10 = new ModelRenderer(this);
		spike10.setPos(10.0F, -1.0F, 9.0F);
		spikes.addChild(spike10);
		setRotationAngle(spike10, -0.7854F, -0.7854F, 0.0F);
		spike10.texOffs(95, 80).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		spike9 = new ModelRenderer(this);
		spike9.setPos(-10.0F, -1.0F, 9.0F);
		spikes.addChild(spike9);
		setRotationAngle(spike9, 0.7854F, 0.7854F, 0.0F);
		spike9.texOffs(106, 80).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		spike8 = new ModelRenderer(this);
		spike8.setPos(6.0F, -1.0F, -5.0F);
		spikes.addChild(spike8);
		setRotationAngle(spike8, -0.7854F, -0.7854F, 0.0F);
		spike8.texOffs(117, 81).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		spike7 = new ModelRenderer(this);
		spike7.setPos(-6.0F, -1.0F, -5.0F);
		spikes.addChild(spike7);
		setRotationAngle(spike7, -0.7854F, 0.7854F, 0.0F);
		spike7.texOffs(76, 88).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		spike6 = new ModelRenderer(this);
		spike6.setPos(-10.0F, -1.0F, -9.0F);
		spikes.addChild(spike6);
		setRotationAngle(spike6, -0.7854F, -0.7854F, 0.0F);
		spike6.texOffs(75, 96).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		spike5 = new ModelRenderer(this);
		spike5.setPos(10.0F, -1.0F, -9.0F);
		spikes.addChild(spike5);
		setRotationAngle(spike5, -0.7854F, 0.7854F, 0.0F);
		spike5.texOffs(87, 89).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		spike4 = new ModelRenderer(this);
		spike4.setPos(0.0F, -1.0F, -9.0F);
		spikes.addChild(spike4);
		setRotationAngle(spike4, 0.0F, 0.0F, 0.7854F);
		spike4.texOffs(86, 96).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(SpikeRockEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.t2.visible = (entity.getSpikeNum() * 1F / entity.getSpikesCount() > 2F / 3);
		this.t3.visible = (entity.getSpikeNum() * 1F / entity.getSpikesCount() > 1F / 3);
		this.t1.visible = (entity.getSpikeNum() * 1F / entity.getSpikesCount() > 0);
		int tot = entity.getAttackCD();
		int now = tot - entity.getAttackTime();
		this.t3.zRot = AnimationUtil.getUpDown(now, tot, 25);
		this.t2.zRot = AnimationUtil.getUpDown(now, tot, - 25);
		this.t1.xRot = AnimationUtil.getUpDownUpDown(now, tot, 25);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		total.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
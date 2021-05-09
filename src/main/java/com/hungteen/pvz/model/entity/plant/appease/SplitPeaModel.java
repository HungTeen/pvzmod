package com.hungteen.pvz.model.entity.plant.appease;

import com.hungteen.pvz.entity.plant.appease.SplitPeaEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class SplitPeaModel extends EntityModel<SplitPeaEntity> {
	private final ModelRenderer total;
	private final ModelRenderer head;
	private final ModelRenderer down;
	private final ModelRenderer body;
	private final ModelRenderer head2;

	public SplitPeaModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(35, 53).addBox(-3.0F, -22.0F, -1.0F, 6.0F, 7.0F, 2.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -15.0F, -5.0F);
		total.addChild(head);
		head.texOffs(7, 15).addBox(-2.0F, -4.0F, -6.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
		head.texOffs(0, 16).addBox(-1.0F, -3.0F, -4.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		head.texOffs(0, 0).addBox(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 7.0F, 0.0F, false);

		down = new ModelRenderer(this);
		down.setPos(1.0F, 0.0F, 0.0F);
		total.addChild(down);
		down.texOffs(0, 23).addBox(0.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.texOffs(9, 23).addBox(-2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.texOffs(20, 16).addBox(-2.0F, -1.0F, -3.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.texOffs(20, 20).addBox(-4.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.texOffs(1, 27).addBox(2.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		down.texOffs(1, 33).addBox(-3.0F, -1.0F, 3.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		down.texOffs(1, 38).addBox(-7.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		down.texOffs(0, 44).addBox(-3.0F, -1.0F, -6.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(-1.0F, -13.0F, 0.0F);
		total.addChild(body);
		body.texOffs(20, 24).addBox(0.0F, -2.0F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.texOffs(16, 28).addBox(1.0F, -2.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.texOffs(17, 32).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.texOffs(17, 36).addBox(0.0F, -2.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.texOffs(56, 48).addBox(0.0F, -1.0F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, false);

		head2 = new ModelRenderer(this);
		head2.setPos(0.0F, -15.0F, 4.0F);
		total.addChild(head2);
		head2.texOffs(1, 49).addBox(-2.0F, -4.0F, 5.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
		head2.texOffs(17, 40).addBox(-1.0F, -3.0F, 4.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		head2.texOffs(29, 1).addBox(-4.0F, -8.0F, -3.0F, 8.0F, 8.0F, 7.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(SplitPeaEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.total.yRot = entity.getRoundTick() * 3.1415926F / 10; 
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
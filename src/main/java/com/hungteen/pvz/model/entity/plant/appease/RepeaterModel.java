package com.hungteen.pvz.model.entity.plant.appease;

import com.hungteen.pvz.entity.plant.appease.RepeaterEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class RepeaterModel extends EntityModel<RepeaterEntity> {
	private final ModelRenderer total;
	private final ModelRenderer head;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer down;
	private final ModelRenderer body;

	public RepeaterModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		head = new ModelRenderer(this);
		head.setPos(0.0F, -15.0F, 0.0F);
		total.addChild(head);
		head.texOffs(38, 15).addBox(-2.0F, -4.0F, -7.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
		head.texOffs(1, 19).addBox(-1.0F, -3.0F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		head.texOffs(0, 1).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(1.0F, -7.0F, 4.0F);
		head.addChild(bone);
		setRotationAngle(bone, 0.5236F, 0.3491F, 0.0F);
		bone.texOffs(3, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, 0.8192F, 0.4264F);
		bone.addChild(bone2);
		setRotationAngle(bone2, -0.48F, 0.0F, 0.0F);
		bone2.texOffs(54, 10).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, 0.0F, 2.0F);
		bone2.addChild(bone3);
		setRotationAngle(bone3, 0.3927F, 0.0F, 0.0F);
		bone3.texOffs(33, 57).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 6.0F, 1.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(-1.0F, -7.0F, 4.0F);
		head.addChild(bone4);
		setRotationAngle(bone4, 0.5236F, -0.3491F, 0.0F);
		bone4.texOffs(3, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(0.0F, 0.8192F, 0.4264F);
		bone4.addChild(bone5);
		setRotationAngle(bone5, -0.48F, 0.0F, 0.0F);
		bone5.texOffs(54, 10).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(0.0F, 0.0F, 2.0F);
		bone5.addChild(bone6);
		setRotationAngle(bone6, 0.3927F, 0.0F, 0.0F);
		bone6.texOffs(33, 57).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 6.0F, 1.0F, 0.0F, false);

		down = new ModelRenderer(this);
		down.setPos(1.0F, 0.0F, 0.0F);
		total.addChild(down);
		down.texOffs(14, 30).addBox(0.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.texOffs(13, 36).addBox(-2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.texOffs(1, 30).addBox(-2.0F, -1.0F, -3.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.texOffs(1, 35).addBox(-4.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.texOffs(2, 45).addBox(2.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		down.texOffs(0, 55).addBox(-3.0F, -1.0F, 3.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		down.texOffs(41, 1).addBox(-7.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		down.texOffs(42, 30).addBox(-3.0F, -1.0F, -6.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(-1.0F, -13.0F, 0.0F);
		total.addChild(body);
		body.texOffs(25, 36).addBox(0.0F, -2.0F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.texOffs(24, 40).addBox(1.0F, -2.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.texOffs(23, 44).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.texOffs(25, 31).addBox(0.0F, -2.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.texOffs(55, 47).addBox(0.0F, -1.0F, -1.0F, 2.0F, 14.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(RepeaterEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
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
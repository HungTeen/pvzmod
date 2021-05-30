package com.hungteen.pvz.client.model.entity.misc;

import com.hungteen.pvz.common.entity.misc.ZombieHandEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class ZombieHandModel extends EntityModel<ZombieHandEntity> {
	private final ModelRenderer total;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;

	public ZombieHandModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(1, 31).addBox(-4.0F, -24.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(-3.0F, -24.0F, 2.0F);
		total.addChild(bone);
		setRotationAngle(bone, -0.7854F, -0.2618F, 0.0F);
		bone.texOffs(35, 53).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, -24.0F, 3.0F);
		total.addChild(bone2);
		setRotationAngle(bone2, -0.5236F, 0.0F, 0.0F);
		bone2.texOffs(45, 53).addBox(-1.0F, -7.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(3.0F, -24.0F, 2.0F);
		total.addChild(bone3);
		setRotationAngle(bone3, -0.7854F, 0.2618F, 0.0F);
		bone3.texOffs(55, 53).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(-3.0F, -24.0F, -2.0F);
		total.addChild(bone4);
		setRotationAngle(bone4, -1.0472F, -1.0472F, 0.0F);
		bone4.texOffs(34, 42).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(3.0F, -24.0F, -2.0F);
		total.addChild(bone5);
		setRotationAngle(bone5, -1.0472F, 1.0472F, 0.0F);
		bone5.texOffs(45, 41).addBox(-1.0F, -5.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(ZombieHandEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		int tick = entity.getTick();
		this.bone.xRot = - 0.785f + tick * 0.08f / 4;
		this.bone2.xRot = - 0.523f + tick * 0.08f / 4;
		this.bone3.xRot = - 0.785f + tick * 0.08f / 4;
		this.bone4.xRot = - 1.047f + tick * 0.14f / 4;
		this.bone5.xRot = - 1.047f + tick * 0.14f / 4;
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
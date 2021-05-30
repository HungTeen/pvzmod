package com.hungteen.pvz.client.model.entity.misc;

import com.hungteen.pvz.common.entity.misc.GardenRakeEntity;
import com.hungteen.pvz.utils.AnimationUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class GardenRakeModel extends EntityModel<GardenRakeEntity> {
	private final ModelRenderer total;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;

	public GardenRakeModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, -3.0F);
		total.texOffs(75, 124).addBox(-12.0F, -1.0F, 1.0F, 24.0F, 1.0F, 2.0F, 0.0F, false);
		total.texOffs(0, 0).addBox(-12.0F, -4.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		total.texOffs(0, 0).addBox(-10.0F, -4.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		total.texOffs(0, 0).addBox(-8.0F, -4.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		total.texOffs(0, 0).addBox(-6.0F, -4.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		total.texOffs(0, 0).addBox(-4.0F, -4.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		total.texOffs(0, 0).addBox(-2.0F, -4.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		total.texOffs(0, 0).addBox(11.0F, -4.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		total.texOffs(0, 0).addBox(9.0F, -4.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		total.texOffs(0, 0).addBox(7.0F, -4.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		total.texOffs(0, 0).addBox(5.0F, -4.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		total.texOffs(0, 0).addBox(3.0F, -4.0F, 0.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		total.texOffs(0, 0).addBox(0.0F, -4.0F, 0.0F, 2.0F, 3.0F, 1.0F, 0.0F, false);
		total.texOffs(23, 5).addBox(-1.0F, -1.0F, 3.0F, 2.0F, 1.0F, 48.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(1.0F, 0.0F, 12.0F);
		total.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.7854F, 0.0F);
		bone.texOffs(40, 117).addBox(-1.0F, -1.0F, 0.0F, 15.0F, 1.0F, 1.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(-1.0F, 0.0F, 12.0F);
		total.addChild(bone2);
		setRotationAngle(bone2, 0.0F, -0.7854F, 0.0F);
		bone2.texOffs(28, 123).addBox(-14.0F, -1.0F, 0.0F, 15.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(GardenRakeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		int time = entity.getAttackTime();
		if(time > 0) {
			int max = entity.getAnimTime();
			this.total.xRot = AnimationUtil.getUp(time, max, 120);
		} else {
			this.total.xRot = 0;
		}
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
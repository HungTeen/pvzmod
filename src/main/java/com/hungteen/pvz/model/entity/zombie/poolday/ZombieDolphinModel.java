package com.hungteen.pvz.model.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.poolday.ZombieDolphinEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public class ZombieDolphinModel extends EntityModel<ZombieDolphinEntity> {
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer nose;
	private final ModelRenderer tail;
	private final ModelRenderer tail_fin;
	private final ModelRenderer back_fin;
	private final ModelRenderer left_fin;
	private final ModelRenderer right_fin;

	public ZombieDolphinModel() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, -3.0F);
		body.setTextureOffset(21, 43).addBox(-4.0F, -7.0F, 0.0F, 8.0F, 7.0F, 13.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(head);
		head.setTextureOffset(1, 1).addBox(-4.0F, -7.0F, -6.0F, 8.0F, 7.0F, 6.0F, 0.0F, false);

		nose = new ModelRenderer(this);
		nose.setRotationPoint(0.0F, 0.0F, -10.0F);
		head.addChild(nose);
		nose.setTextureOffset(30, 2).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 3.0F, 6.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, -2.5F, 14.0F);
		body.addChild(tail);
		setRotationAngle(tail, -0.0873F, 0.0F, 0.0F);
		tail.setTextureOffset(33, 12).addBox(-2.0F, -2.5F, -1.0F, 4.0F, 5.0F, 11.0F, 0.0F, false);

		tail_fin = new ModelRenderer(this);
		tail_fin.setRotationPoint(0.0F, 0.0F, 9.0F);
		tail.addChild(tail_fin);
		setRotationAngle(tail_fin, -0.1396F, 0.0F, 0.0F);
		tail_fin.setTextureOffset(29, 31).addBox(-5.0F, -0.5F, -1.0F, 10.0F, 1.0F, 6.0F, 0.0F, false);

		back_fin = new ModelRenderer(this);
		back_fin.setRotationPoint(0.0F, -7.0F, 5.0F);
		body.addChild(back_fin);
		setRotationAngle(back_fin, 1.0472F, 0.0F, 0.0F);
		back_fin.setTextureOffset(18, 16).addBox(-0.5F, -0.75F, -0.5F, 1.0F, 4.0F, 5.0F, 0.0F, false);

		left_fin = new ModelRenderer(this);
		left_fin.setRotationPoint(3.0F, -2.0F, 5.0F);
		body.addChild(left_fin);
		setRotationAngle(left_fin, 0.9599F, 0.0F, 1.8675F);
		left_fin.setTextureOffset(1, 15).addBox(0.0F, -4.0F, -1.5F, 1.0F, 4.0F, 7.0F, 0.0F, false);

		right_fin = new ModelRenderer(this);
		right_fin.setRotationPoint(-3.0F, -2.0F, 5.0F);
		body.addChild(right_fin);
		setRotationAngle(right_fin, 0.9599F, 0.0F, -1.8675F);
		right_fin.setTextureOffset(1, 27).addBox(-1.0F, -4.0F, -1.5F, 1.0F, 4.0F, 7.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(ZombieDolphinEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		this.body.rotateAngleX = headPitch * ((float) Math.PI / 180F);
		this.body.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
		if (Entity.horizontalMag(entity.getMotion()) > 1.0E-7D) {
			this.body.rotateAngleX += -0.05F + -0.05F * MathHelper.cos(ageInTicks * 0.3F);
			this.tail.rotateAngleX = -0.1F * MathHelper.cos(ageInTicks * 0.3F);
			this.tail_fin.rotateAngleX = -0.2F * MathHelper.cos(ageInTicks * 0.3F);
		}
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
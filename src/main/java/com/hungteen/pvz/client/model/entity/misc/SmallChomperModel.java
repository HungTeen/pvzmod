package com.hungteen.pvz.client.model.entity.misc;

import com.hungteen.pvz.common.entity.misc.SmallChomperEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class SmallChomperModel extends EntityModel<SmallChomperEntity> {
	private final ModelRenderer head;
	private final ModelRenderer bone14;
	private final ModelRenderer bone15;
	private final ModelRenderer up_mouse;
	private final ModelRenderer bone9;
	private final ModelRenderer bone8;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer bone13;
	private final ModelRenderer down_mouse;
	private final ModelRenderer leaf;
	private final ModelRenderer bone16;
	private final ModelRenderer bone17;
	private final ModelRenderer bone18;
	private final ModelRenderer bone19;
	private final ModelRenderer bone20;
	private final ModelRenderer bone21;
	private final ModelRenderer tongue;
	private final ModelRenderer tongue2;

	public SmallChomperModel() {
		texWidth = 128;
		texHeight = 128;

		head = new ModelRenderer(this);
		head.setPos(0.0F, 21.0F, -2.0F);
		setRotationAngle(head, -1.5708F, 0.0F, 0.0F);
		head.texOffs(72, 113).addBox(-6.0F, -7.0F, -4.0F, 12.0F, 10.0F, 4.0F, 0.0F, false);

		bone14 = new ModelRenderer(this);
		bone14.setPos(0.0F, -6.0F, 0.0F);
		head.addChild(bone14);
		setRotationAngle(bone14, -0.9599F, 0.0F, 0.0F);
		bone14.texOffs(105, 107).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone15 = new ModelRenderer(this);
		bone15.setPos(0.0F, -1.0F, 0.0F);
		bone14.addChild(bone15);
		setRotationAngle(bone15, 0.0F, 0.0F, -0.7854F);
		bone15.texOffs(35, 45).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		up_mouse = new ModelRenderer(this);
		up_mouse.setPos(0.0F, -4.0F, -2.0F);
		head.addChild(up_mouse);
		setRotationAngle(up_mouse, -1.0472F, 0.0F, 0.0F);
		up_mouse.texOffs(75, 94).addBox(6.0F, -2.0F, -11.0F, 1.0F, 4.0F, 11.0F, 0.0F, false);
		up_mouse.texOffs(22, 96).addBox(-6.0F, -3.0F, -12.0F, 12.0F, 5.0F, 12.0F, 0.0F, false);
		up_mouse.texOffs(103, 89).addBox(-5.0F, -2.0F, -13.0F, 10.0F, 4.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(100, 70).addBox(-7.0F, -2.0F, -11.0F, 1.0F, 4.0F, 11.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(-6.0F, 2.0F, -7.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(-6.0F, 2.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(-6.0F, 2.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(-6.0F, 2.0F, -9.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(-6.0F, 2.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(-5.0F, 2.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(-3.0F, 2.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(-1.0F, 2.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(1.0F, 2.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(3.0F, 2.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(5.0F, 2.0F, -12.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(5.0F, 2.0F, -10.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(5.0F, 2.0F, -8.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(5.0F, 2.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(5.0F, 2.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setPos(0.0F, -3.0F, -10.0F);
		up_mouse.addChild(bone9);
		setRotationAngle(bone9, -0.0873F, 0.0F, 0.0F);
		bone9.texOffs(9, 99).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setPos(0.0F, -4.0F, 0.0F);
		bone9.addChild(bone8);
		setRotationAngle(bone8, 0.0F, 0.0F, -0.7854F);
		bone8.texOffs(51, 43).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone10 = new ModelRenderer(this);
		bone10.setPos(0.0F, -3.0F, -6.0F);
		up_mouse.addChild(bone10);
		setRotationAngle(bone10, -0.1745F, 0.0F, 0.0F);
		bone10.texOffs(4, 90).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone11 = new ModelRenderer(this);
		bone11.setPos(0.0F, -3.0F, 0.0F);
		bone10.addChild(bone11);
		setRotationAngle(bone11, 0.0F, 0.0F, -0.7854F);
		bone11.texOffs(62, 44).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone12 = new ModelRenderer(this);
		bone12.setPos(0.0F, -3.0F, -2.0F);
		up_mouse.addChild(bone12);
		setRotationAngle(bone12, -0.2618F, 0.0F, 0.0F);
		bone12.texOffs(32, 89).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		bone13 = new ModelRenderer(this);
		bone13.setPos(0.0F, -2.0F, 0.0F);
		bone12.addChild(bone13);
		setRotationAngle(bone13, 0.0F, 0.0F, -0.7854F);
		bone13.texOffs(19, 91).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		down_mouse = new ModelRenderer(this);
		down_mouse.setPos(0.0F, 1.0F, -2.0F);
		head.addChild(down_mouse);
		setRotationAngle(down_mouse, 1.0472F, 0.0F, 0.0F);
		down_mouse.texOffs(46, 79).addBox(6.0F, -2.0F, -11.0F, 1.0F, 3.0F, 11.0F, 0.0F, false);
		down_mouse.texOffs(5, 63).addBox(-6.0F, -2.0F, -12.0F, 12.0F, 4.0F, 12.0F, 0.0F, false);
		down_mouse.texOffs(74, 88).addBox(-5.0F, -2.0F, -13.0F, 10.0F, 3.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(73, 71).addBox(-7.0F, -2.0F, -11.0F, 1.0F, 3.0F, 11.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(-6.0F, -3.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(5.0F, -3.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(-6.0F, -3.0F, -8.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(-6.0F, -3.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(-6.0F, -3.0F, -10.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(-6.0F, -3.0F, -12.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(-4.0F, -3.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(-2.0F, -3.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(0.0F, -3.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(2.0F, -3.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(4.0F, -3.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(5.0F, -3.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(5.0F, -3.0F, -9.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(5.0F, -3.0F, -7.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(5.0F, -3.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		leaf = new ModelRenderer(this);
		leaf.setPos(0.0F, -1.0F, 0.0F);
		head.addChild(leaf);
		leaf.texOffs(64, 73).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);

		bone16 = new ModelRenderer(this);
		bone16.setPos(0.0F, 0.0F, 1.0F);
		leaf.addChild(bone16);
		setRotationAngle(bone16, -0.5236F, 0.0F, 0.0F);
		bone16.texOffs(58, 65).addBox(-1.0F, -4.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

		bone17 = new ModelRenderer(this);
		bone17.setPos(0.0F, 0.0F, 1.0F);
		leaf.addChild(bone17);
		setRotationAngle(bone17, -0.5236F, 0.0F, 1.0472F);
		bone17.texOffs(70, 64).addBox(-1.0F, -4.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

		bone18 = new ModelRenderer(this);
		bone18.setPos(0.0F, 0.0F, 1.0F);
		leaf.addChild(bone18);
		setRotationAngle(bone18, -0.5236F, 0.0F, 2.0944F);
		bone18.texOffs(92, 64).addBox(-1.0F, -4.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

		bone19 = new ModelRenderer(this);
		bone19.setPos(0.0F, 0.0F, 1.0F);
		leaf.addChild(bone19);
		setRotationAngle(bone19, -0.2618F, 0.0F, -3.1416F);
		bone19.texOffs(108, 61).addBox(-1.0F, -4.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

		bone20 = new ModelRenderer(this);
		bone20.setPos(0.0F, 0.0F, 1.0F);
		leaf.addChild(bone20);
		setRotationAngle(bone20, -0.5236F, 0.0F, -1.0472F);
		bone20.texOffs(119, 61).addBox(-1.0F, -4.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

		bone21 = new ModelRenderer(this);
		bone21.setPos(0.0F, 0.0F, 1.0F);
		leaf.addChild(bone21);
		setRotationAngle(bone21, -0.5236F, 0.0F, -2.0944F);
		bone21.texOffs(81, 57).addBox(-1.0F, -4.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

		tongue = new ModelRenderer(this);
		tongue.setPos(0.0F, -1.0F, -5.0F);
		head.addChild(tongue);
		tongue.texOffs(7, 53).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 4.0F, 0.0F, false);

		tongue2 = new ModelRenderer(this);
		tongue2.setPos(0.0F, 0.0F, -4.0F);
		tongue.addChild(tongue2);
		tongue2.texOffs(23, 52).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(SmallChomperEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		int tick = entity.getTick();//1 - 20
		this.up_mouse.xRot = -1f + tick*0.05f;
		this.down_mouse.xRot = 1f - tick*0.05f;
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
package com.hungteen.pvz.client.model.entity.plant.enforce;

import com.hungteen.pvz.common.entity.plant.enforce.TangleKelpEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class TangleKelpModel extends EntityModel<TangleKelpEntity> {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer hand;
	private final ModelRenderer hand2;
	private final ModelRenderer tangle1;
	private final ModelRenderer bone;
	private final ModelRenderer tangle2;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer tangle3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone8;
	private final ModelRenderer tangle4;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer tangle5;
	private final ModelRenderer tangle6;
	private final ModelRenderer tangle7;

	public TangleKelpModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		body.texOffs(71, 107).addBox(-8.0F, -8.0F, -6.0F, 16.0F, 8.0F, 12.0F, 0.0F, false);

		hand = new ModelRenderer(this);
		hand.setPos(-8.0F, -2.0F, 0.0F);
		total.addChild(hand);
		setRotationAngle(hand, 0.0F, 0.0F, 0.9599F);
		hand.texOffs(100, 101).addBox(-11.0F, -2.0F, -1.0F, 11.0F, 2.0F, 2.0F, 0.0F, false);

		hand2 = new ModelRenderer(this);
		hand2.setPos(8.0F, -2.0F, 0.0F);
		total.addChild(hand2);
		setRotationAngle(hand2, 0.0F, 0.0F, -0.9599F);
		hand2.texOffs(101, 94).addBox(0.0F, -2.0F, -1.0F, 11.0F, 2.0F, 2.0F, 0.0F, false);

		tangle1 = new ModelRenderer(this);
		tangle1.setPos(-6.0F, -8.0F, -4.0F);
		total.addChild(tangle1);
		setRotationAngle(tangle1, -0.2618F, 0.0F, 0.4363F);
		tangle1.texOffs(118, 80).addBox(-1.0F, -9.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.3054F, -8.1762F, 0.0F);
		tangle1.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, -0.4363F);
		bone.texOffs(106, 79).addBox(-1.0223F, -11.4531F, -1.0F, 2.0F, 11.0F, 2.0F, 0.0F, false);

		tangle2 = new ModelRenderer(this);
		tangle2.setPos(5.0F, -8.0F, -3.0F);
		total.addChild(tangle2);
		setRotationAngle(tangle2, -0.1745F, 0.0F, -0.3491F);
		tangle2.texOffs(118, 66).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 9.0F, 2.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.342F, -7.9397F, 0.0F);
		tangle2.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.9599F);
		bone2.texOffs(107, 68).addBox(-0.8192F, -4.9353F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(-0.8192F, -4.4264F, 0.0F);
		bone2.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.0F, -1.2217F);
		bone3.texOffs(69, 105).addBox(-0.8378F, -4.2947F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		tangle3 = new ModelRenderer(this);
		tangle3.setPos(-1.0F, -8.0F, 3.0F);
		total.addChild(tangle3);
		setRotationAngle(tangle3, 0.2566F, 0.0298F, -0.3478F);
		tangle3.texOffs(86, 92).addBox(-0.0603F, -5.6632F, -1.0594F, 2.0F, 7.0F, 2.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(0.0F, -7.0143F, -0.1632F);
		tangle3.addChild(bone4);
		setRotationAngle(bone4, 0.0F, 0.0F, -0.2618F);
		bone4.texOffs(70, 91).addBox(-0.4761F, -4.1929F, -0.8962F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(-1.2754F, -7.342F, -0.5397F);
		bone4.addChild(bone5);
		setRotationAngle(bone5, 0.6109F, 0.0F, -0.0436F);
		bone5.texOffs(90, 78).addBox(0.6612F, -0.4517F, -2.4783F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setPos(0.0F, 0.0F, 0.0F);
		bone5.addChild(bone8);
		setRotationAngle(bone8, 0.0F, 0.0F, -0.829F);
		bone8.texOffs(0, 0).addBox(0.7797F, 0.1824F, -2.4783F, 3.0F, 2.0F, 2.0F, 0.0F, false);

		tangle4 = new ModelRenderer(this);
		tangle4.setPos(5.0F, -8.0F, 1.0F);
		total.addChild(tangle4);
		setRotationAngle(tangle4, -0.5236F, 0.0F, -0.3491F);
		tangle4.texOffs(75, 74).addBox(-1.0F, -9.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(0.342F, -7.9397F, 0.0F);
		tangle4.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 0.0F, -0.6109F);
		bone6.texOffs(91, 65).addBox(-0.8528F, -5.4912F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setPos(-1.419F, -5.208F, -0.171F);
		bone6.addChild(bone7);
		setRotationAngle(bone7, 0.0F, 0.0F, -0.8727F);
		bone7.texOffs(75, 60).addBox(-0.1336F, -4.2162F, -0.829F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		tangle5 = new ModelRenderer(this);
		tangle5.setPos(0.0F, -8.0F, -3.0F);
		total.addChild(tangle5);
		setRotationAngle(tangle5, -0.4363F, 0.0F, 0.0F);
		tangle5.texOffs(61, 69).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		tangle6 = new ModelRenderer(this);
		tangle6.setPos(-6.0F, -8.0F, 3.0F);
		total.addChild(tangle6);
		tangle6.texOffs(56, 82).addBox(-1.0F, -11.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);

		tangle7 = new ModelRenderer(this);
		tangle7.setPos(7.0F, -9.0F, 4.0F);
		total.addChild(tangle7);
		setRotationAngle(tangle7, 0.4363F, 0.0F, 0.0F);
		tangle7.texOffs(52, 105).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(TangleKelpEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
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
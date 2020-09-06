package com.hungteen.pvz.model.entity.plant.defence;

import com.hungteen.pvz.entity.plant.defence.WaterGuardEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class WaterGuardModel extends EntityModel<WaterGuardEntity> {
	private final ModelRenderer total;
	private final ModelRenderer side;
	private final ModelRenderer side2;
	private final ModelRenderer side3;
	private final ModelRenderer side4;
	private final ModelRenderer side5;
	private final ModelRenderer side6;

	public WaterGuardModel() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		total.setTextureOffset(188, 221).addBox(-8.0F, -17.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		side = new ModelRenderer(this);
		side.setRotationPoint(0.0F, -1.0F, 0.0F);
		total.addChild(side);
		setRotationAngle(side, 0.0F, 0.0F, -3.1416F);
		side.setTextureOffset(144, 234).addBox(-2.0F, -1.0F, -8.0F, 4.0F, 1.0F, 16.0F, 0.0F, true);
		side.setTextureOffset(165, 214).addBox(2.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, true);
		side.setTextureOffset(221, 202).addBox(-4.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, true);
		side.setTextureOffset(202, 199).addBox(4.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, true);
		side.setTextureOffset(189, 202).addBox(-8.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);
		side.setTextureOffset(136, 233).addBox(-7.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, true);
		side.setTextureOffset(147, 211).addBox(-6.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, true);
		side.setTextureOffset(154, 193).addBox(-5.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, true);
		side.setTextureOffset(228, 186).addBox(5.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, true);
		side.setTextureOffset(204, 186).addBox(6.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, true);
		side.setTextureOffset(186, 187).addBox(7.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, true);

		side2 = new ModelRenderer(this);
		side2.setRotationPoint(-8.0F, -9.0F, 0.0F);
		total.addChild(side2);
		setRotationAngle(side2, 0.0F, 0.0F, -1.5708F);
		side2.setTextureOffset(92, 235).addBox(-2.0F, -1.0F, -8.0F, 4.0F, 1.0F, 16.0F, 0.0F, false);
		side2.setTextureOffset(69, 229).addBox(2.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, false);
		side2.setTextureOffset(109, 216).addBox(-4.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, false);
		side2.setTextureOffset(89, 211).addBox(4.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
		side2.setTextureOffset(75, 248).addBox(-8.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		side2.setTextureOffset(48, 244).addBox(-7.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		side2.setTextureOffset(22, 241).addBox(-6.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		side2.setTextureOffset(39, 225).addBox(-5.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
		side2.setTextureOffset(3, 236).addBox(5.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		side2.setTextureOffset(21, 223).addBox(6.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		side2.setTextureOffset(5, 221).addBox(7.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

		side3 = new ModelRenderer(this);
		side3.setRotationPoint(8.0F, -9.0F, 0.0F);
		total.addChild(side3);
		setRotationAngle(side3, 0.0F, 0.0F, 1.5708F);
		side3.setTextureOffset(43, 205).addBox(-2.0F, -1.0F, -8.0F, 4.0F, 1.0F, 16.0F, 0.0F, false);
		side3.setTextureOffset(10, 198).addBox(2.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, false);
		side3.setTextureOffset(82, 192).addBox(-4.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, false);
		side3.setTextureOffset(121, 200).addBox(4.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
		side3.setTextureOffset(144, 195).addBox(-8.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		side3.setTextureOffset(66, 192).addBox(-7.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		side3.setTextureOffset(35, 189).addBox(-6.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		side3.setTextureOffset(6, 179).addBox(-5.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
		side3.setTextureOffset(112, 184).addBox(5.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		side3.setTextureOffset(160, 179).addBox(6.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		side3.setTextureOffset(140, 184).addBox(7.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

		side4 = new ModelRenderer(this);
		side4.setRotationPoint(0.0F, -17.0F, 0.0F);
		total.addChild(side4);
		side4.setTextureOffset(213, 163).addBox(-2.0F, -1.0F, -8.0F, 4.0F, 1.0F, 16.0F, 0.0F, false);
		side4.setTextureOffset(189, 157).addBox(2.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, false);
		side4.setTextureOffset(34, 170).addBox(-4.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, false);
		side4.setTextureOffset(71, 175).addBox(4.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
		side4.setTextureOffset(104, 183).addBox(-8.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		side4.setTextureOffset(6, 166).addBox(-7.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		side4.setTextureOffset(59, 163).addBox(-6.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		side4.setTextureOffset(22, 154).addBox(-5.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
		side4.setTextureOffset(131, 169).addBox(5.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		side4.setTextureOffset(115, 167).addBox(6.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		side4.setTextureOffset(160, 168).addBox(7.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

		side5 = new ModelRenderer(this);
		side5.setRotationPoint(0.0F, -9.0F, -8.0F);
		total.addChild(side5);
		setRotationAngle(side5, 1.5708F, 0.0F, 0.0F);
		side5.setTextureOffset(212, 140).addBox(-2.0F, -1.0F, -8.0F, 4.0F, 1.0F, 16.0F, 0.0F, false);
		side5.setTextureOffset(161, 146).addBox(2.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, false);
		side5.setTextureOffset(121, 147).addBox(-4.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, false);
		side5.setTextureOffset(86, 156).addBox(4.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
		side5.setTextureOffset(9, 150).addBox(-8.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		side5.setTextureOffset(50, 147).addBox(-7.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		side5.setTextureOffset(77, 140).addBox(-6.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		side5.setTextureOffset(104, 134).addBox(-5.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
		side5.setTextureOffset(148, 138).addBox(5.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		side5.setTextureOffset(192, 139).addBox(6.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		side5.setTextureOffset(30, 144).addBox(7.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);

		side6 = new ModelRenderer(this);
		side6.setRotationPoint(0.0F, -9.0F, 8.0F);
		total.addChild(side6);
		setRotationAngle(side6, -1.5708F, 0.0F, 0.0F);
		side6.setTextureOffset(7, 121).addBox(-2.0F, -1.0F, -8.0F, 4.0F, 1.0F, 16.0F, 0.0F, false);
		side6.setTextureOffset(53, 121).addBox(2.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, false);
		side6.setTextureOffset(91, 115).addBox(-4.0F, -1.0F, -7.0F, 2.0F, 1.0F, 14.0F, 0.0F, false);
		side6.setTextureOffset(133, 122).addBox(4.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
		side6.setTextureOffset(173, 131).addBox(-8.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
		side6.setTextureOffset(209, 126).addBox(-7.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		side6.setTextureOffset(230, 118).addBox(-6.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		side6.setTextureOffset(9, 102).addBox(-5.0F, -1.0F, -6.0F, 1.0F, 1.0F, 12.0F, 0.0F, false);
		side6.setTextureOffset(41, 105).addBox(5.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F, 0.0F, false);
		side6.setTextureOffset(76, 108).addBox(6.0F, -1.0F, -4.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);
		side6.setTextureOffset(121, 107).addBox(7.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(WaterGuardEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		total.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
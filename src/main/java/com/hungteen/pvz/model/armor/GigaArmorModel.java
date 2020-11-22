package com.hungteen.pvz.model.armor;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class GigaArmorModel extends BipedModel<LivingEntity> {
	private final ModelRenderer helmet;
	private final ModelRenderer chestplate;
	private final ModelRenderer right_hand_armor;
	private final ModelRenderer bone2;
	private final ModelRenderer left_hand_armor;
	private final ModelRenderer bone;
	private final EquipmentSlotType slot;
	
	public GigaArmorModel(EquipmentSlotType slot, float scale) {
		super(scale, 0, 64, 64);
		this.slot = slot;
		
		helmet = new ModelRenderer(this);
		helmet.setRotationPoint(0.0F, 0F, 0.0F);
		helmet.setTextureOffset(30, 54).addBox(-4.0F, -9.0F, -5.0F, 8.0F, 1.0F, 9.0F, 0.0F, false);
		helmet.setTextureOffset(46, 44).addBox(-4.0F, -8.0F, 4.0F, 8.0F, 8.0F, 1.0F, 0.0F, false);
		helmet.setTextureOffset(25, 36).addBox(-5.0F, -8.0F, -5.0F, 1.0F, 8.0F, 9.0F, 0.0F, false);
		helmet.setTextureOffset(0, 34).addBox(4.0F, -8.0F, -5.0F, 1.0F, 8.0F, 9.0F, 0.0F, false);
		helmet.setTextureOffset(45, 40).addBox(-4.0F, -1.0F, -5.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		helmet.setTextureOffset(45, 37).addBox(-4.0F, -3.0F, -5.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		helmet.setTextureOffset(40, 42).addBox(-3.0F, -2.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		helmet.setTextureOffset(40, 39).addBox(2.0F, -2.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		helmet.setTextureOffset(39, 36).addBox(-3.0F, -8.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		helmet.setTextureOffset(58, 58).addBox(2.0F, -8.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		
		this.bipedHead.addChild(helmet);
		
		chestplate = new ModelRenderer(this);
		chestplate.setRotationPoint(0.0F, 12.0F, 0.0F);
		chestplate.setTextureOffset(0, 51).addBox(-4.0F, -12.0F, -3.0F, 8.0F, 12.0F, 1.0F, 0.0F, false);
		chestplate.setTextureOffset(0, 38).addBox(-4.0F, -12.0F, 2.0F, 8.0F, 12.0F, 1.0F, 0.0F, false);
		chestplate.setTextureOffset(18, 38).addBox(-5.0F, -9.0F, -2.0F, 1.0F, 9.0F, 4.0F, 0.0F, false);
		chestplate.setTextureOffset(18, 51).addBox(4.0F, -9.0F, -2.0F, 1.0F, 9.0F, 4.0F, 0.0F, false);
		
		this.bipedBody.addChild(chestplate);

		right_hand_armor = new ModelRenderer(this);
		right_hand_armor.setRotationPoint(-1.0F, 0.0F, 0.0F);
		right_hand_armor.setTextureOffset(28, 43).addBox(-5.0F, -2.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		right_hand_armor.setTextureOffset(28, 38).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);
		right_hand_armor.setTextureOffset(22, 33).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		right_hand_armor.setTextureOffset(12, 33).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);
		right_hand_armor.setTextureOffset(8, 34).addBox(-3.0F, -2.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		right_hand_armor.setTextureOffset(4, 35).addBox(-4.0F, -2.0F, -3.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		right_hand_armor.setTextureOffset(0, 34).addBox(-5.0F, -2.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		right_hand_armor.setTextureOffset(18, 39).addBox(-4.0F, -2.0F, 2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		right_hand_armor.setTextureOffset(24, 38).addBox(-3.0F, -2.0F, 2.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		right_hand_armor.setTextureOffset(0, 36).addBox(-5.0F, -2.0F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		right_hand_armor.setTextureOffset(44, 51).addBox(-4.0F, -3.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		right_hand_armor.setTextureOffset(44, 52).addBox(-4.0F, -3.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		right_hand_armor.setTextureOffset(45, 46).addBox(-1.0F, -3.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		right_hand_armor.setTextureOffset(46, 53).addBox(-1.0F, -3.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-3.0F, 2.0F, 0.0F);
		right_hand_armor.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.7854F);
		bone2.setTextureOffset(28, 59).addBox(-3.5355F, -1.7071F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		
		this.bipedRightArm.addChild(right_hand_armor);

		left_hand_armor = new ModelRenderer(this);
		left_hand_armor.setRotationPoint(1.0F, 0.0F, 0.0F);
		left_hand_armor.setTextureOffset(50, 59).addBox(2.0F, -2.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		left_hand_armor.setTextureOffset(48, 53).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		left_hand_armor.setTextureOffset(54, 47).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);
		left_hand_armor.setTextureOffset(60, 42).addBox(2.0F, -2.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		left_hand_armor.setTextureOffset(53, 42).addBox(3.0F, -2.0F, -3.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		left_hand_armor.setTextureOffset(28, 48).addBox(4.0F, -2.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		left_hand_armor.setTextureOffset(24, 51).addBox(3.0F, -2.0F, 2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		left_hand_armor.setTextureOffset(38, 40).addBox(2.0F, -2.0F, 2.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		left_hand_armor.setTextureOffset(38, 38).addBox(4.0F, -2.0F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		left_hand_armor.setTextureOffset(38, 33).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);
		left_hand_armor.setTextureOffset(44, 51).addBox(0.0F, -3.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		left_hand_armor.setTextureOffset(44, 52).addBox(0.0F, -3.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		left_hand_armor.setTextureOffset(45, 46).addBox(3.0F, -3.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		left_hand_armor.setTextureOffset(46, 53).addBox(3.0F, -3.0F, -2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(3.0F, 2.0F, 0.0F);
		left_hand_armor.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, -0.7854F);
		bone.setTextureOffset(28, 53).addBox(-0.4645F, -1.7071F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		
		this.bipedLeftArm.addChild(left_hand_armor);
	}
	
	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		this.helmet.showModel = this.slot == EquipmentSlotType.HEAD;
		this.right_hand_armor.showModel = this.slot == EquipmentSlotType.CHEST;
		this.left_hand_armor.showModel = this.slot == EquipmentSlotType.CHEST;
		super.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
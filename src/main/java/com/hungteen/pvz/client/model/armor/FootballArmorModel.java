package com.hungteen.pvz.client.model.armor;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class FootballArmorModel extends BipedModel<LivingEntity> {
	private final ModelRenderer helmet;
	private final ModelRenderer chestplate;
	private final ModelRenderer right_hand_armor;
	private final ModelRenderer bone2;
	private final ModelRenderer left_hand_armor;
	private final ModelRenderer bone;
	private final EquipmentSlotType slot;
	
	public FootballArmorModel(EquipmentSlotType slot, float scale) {
		super(scale, 0, 64, 64);
		this.slot = slot;
		
		//helmet for head
		helmet = new ModelRenderer(this);
		helmet.setPos(0.0F, 0F, 0.0F);
		helmet.texOffs(30, 54).addBox(-4.0F, -9.0F, -5.0F, 8.0F, 1.0F, 9.0F, 0.0F, false);
		helmet.texOffs(46, 44).addBox(-4.0F, -8.0F, 4.0F, 8.0F, 8.0F, 1.0F, 0.0F, false);
		helmet.texOffs(25, 36).addBox(-5.0F, -8.0F, -5.0F, 1.0F, 8.0F, 9.0F, 0.0F, false);
		helmet.texOffs(0, 34).addBox(4.0F, -8.0F, -5.0F, 1.0F, 8.0F, 9.0F, 0.0F, false);
		helmet.texOffs(45, 40).addBox(-4.0F, -1.0F, -5.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		helmet.texOffs(45, 37).addBox(-4.0F, -3.0F, -5.0F, 8.0F, 1.0F, 1.0F, 0.0F, false);
		helmet.texOffs(40, 42).addBox(-3.0F, -2.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		helmet.texOffs(40, 39).addBox(2.0F, -2.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		helmet.texOffs(39, 36).addBox(-3.0F, -8.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		helmet.texOffs(58, 58).addBox(2.0F, -8.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		
		this.head.addChild(helmet);
		
		chestplate = new ModelRenderer(this);
		chestplate.setPos(0.0F, 12F, 0.0F);
		chestplate.texOffs(0, 51).addBox(-4.0F, -12.0F, -3.0F, 8.0F, 12.0F, 1.0F, 0.0F, false);
		chestplate.texOffs(0, 38).addBox(-4.0F, -12.0F, 2.0F, 8.0F, 12.0F, 1.0F, 0.0F, false);
		chestplate.texOffs(18, 38).addBox(-5.0F, -9.0F, -2.0F, 1.0F, 9.0F, 4.0F, 0.0F, false);
		chestplate.texOffs(18, 51).addBox(4.0F, -9.0F, -2.0F, 1.0F, 9.0F, 4.0F, 0.0F, false);
		
		this.body.addChild(chestplate);

		right_hand_armor = new ModelRenderer(this);
		right_hand_armor.setPos(- 1.0F, - 1F, 0.0F);
		right_hand_armor.texOffs(28, 43).addBox(-5.0F, -2.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		right_hand_armor.texOffs(28, 38).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);
		right_hand_armor.texOffs(22, 33).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		right_hand_armor.texOffs(12, 33).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);
		right_hand_armor.texOffs(8, 34).addBox(-3.0F, -2.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		right_hand_armor.texOffs(4, 35).addBox(-4.0F, -2.0F, -3.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		right_hand_armor.texOffs(0, 34).addBox(-5.0F, -2.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		right_hand_armor.texOffs(18, 39).addBox(-4.0F, -2.0F, 2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		right_hand_armor.texOffs(24, 38).addBox(-3.0F, -2.0F, 2.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		right_hand_armor.texOffs(0, 36).addBox(-5.0F, -2.0F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(-3.0F, 2.0F, 0.0F);
		right_hand_armor.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.7854F);
		bone2.texOffs(28, 59).addBox(-3.5355F, -1.7071F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		
		this.rightArm.addChild(right_hand_armor);

		left_hand_armor = new ModelRenderer(this);
		left_hand_armor.setPos(1.0F, - 1F, 0.0F);
		left_hand_armor.texOffs(50, 59).addBox(2.0F, -2.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		left_hand_armor.texOffs(48, 53).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		left_hand_armor.texOffs(54, 47).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);
		left_hand_armor.texOffs(60, 42).addBox(2.0F, -2.0F, -3.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		left_hand_armor.texOffs(53, 42).addBox(3.0F, -2.0F, -3.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		left_hand_armor.texOffs(28, 48).addBox(4.0F, -2.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		left_hand_armor.texOffs(24, 51).addBox(3.0F, -2.0F, 2.0F, 1.0F, 2.0F, 1.0F, 0.0F, false);
		left_hand_armor.texOffs(38, 40).addBox(2.0F, -2.0F, 2.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		left_hand_armor.texOffs(38, 38).addBox(4.0F, -2.0F, 2.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		left_hand_armor.texOffs(38, 33).addBox(-2.0F, -2.0F, 2.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(3.0F, 2.0F, 0.0F);
		left_hand_armor.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, -0.7854F);
		bone.texOffs(28, 53).addBox(-0.4645F, -1.7071F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
		
		this.leftArm.addChild(left_hand_armor);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
	
	@Override
	public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha) {
		this.helmet.visible = this.slot == EquipmentSlotType.HEAD;
		this.right_hand_armor.visible = this.slot == EquipmentSlotType.CHEST;
		this.left_hand_armor.visible = this.slot == EquipmentSlotType.CHEST;
		super.renderToBuffer(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}
	
}
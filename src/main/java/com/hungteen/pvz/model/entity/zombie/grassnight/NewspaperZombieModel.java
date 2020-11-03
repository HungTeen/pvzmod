package com.hungteen.pvz.model.entity.zombie.grassnight;

import com.hungteen.pvz.entity.zombie.grassnight.NewspaperZombieEntity;
import com.hungteen.pvz.model.entity.IHasDefence;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.1
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class NewspaperZombieModel extends EntityModel<NewspaperZombieEntity> implements IHasDefence{
	private final ModelRenderer total;
	private final ModelRenderer paper_r1;
	private final ModelRenderer right_hand_r1;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer bone3_r1;
	private final ModelRenderer bone3;
	private final ModelRenderer paper;
	private final ModelRenderer bone_r1;
	private final ModelRenderer bone2_r1;
	private final ModelRenderer bone2;
	private final ModelRenderer bone;

	public NewspaperZombieModel() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(4.0F, 1.0F, 0.0F);
		

		paper_r1 = new ModelRenderer(this);
		paper_r1.setRotationPoint(0.0F, 1.0F, -9.0F);
		total.addChild(paper_r1);
		setRotationAngle(paper_r1, 0.0873F, 0.0F, 0.0F);
		

		right_hand_r1 = new ModelRenderer(this);
		right_hand_r1.setRotationPoint(-12.0F, -22.0F, 3.0F);
		total.addChild(right_hand_r1);
		setRotationAngle(right_hand_r1, -1.309F, 0.0F, 0.0F);
		

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.setTextureOffset(230, 225).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 22.0F, 6.0F, 0.0F, false);
		left_leg.setTextureOffset(196, 242).addBox(-3.0F, 21.0F, -5.0F, 6.0F, 2.0F, 9.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-8.0F, 0.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.setTextureOffset(230, 192).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 22.0F, 6.0F, 0.0F, false);
		right_leg.setTextureOffset(191, 220).addBox(-3.0F, 21.0F, -5.0F, 6.0F, 2.0F, 9.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(-4.0F, -8.0F, 0.0F);
		total.addChild(body);
		body.setTextureOffset(3, 221).addBox(-8.0F, -17.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(5.0F, -22.0F, 3.0F);
		total.addChild(left_hand);
		setRotationAngle(left_hand, -1.309F, 0.0F, 0.0F);
		left_hand.setTextureOffset(59, 221).addBox(-1.0F, 0.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-12.0F, -22.0F, 3.0F);
		total.addChild(right_hand);
		setRotationAngle(right_hand, -1.309F, 0.0F, 0.0F);
		right_hand.setTextureOffset(148, 222).addBox(-6.0F, 0.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(-4.0F, -25.0F, 0.0F);
		total.addChild(head);
		head.setTextureOffset(66, 182).addBox(-7.0F, -14.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
		head.setTextureOffset(130, 207).addBox(-7.0F, -15.0F, 2.0F, 13.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(169, 208).addBox(-6.0F, -15.0F, -3.0F, 13.0F, 1.0F, 1.0F, 0.0F, false);

		bone3_r1 = new ModelRenderer(this);
		bone3_r1.setRotationPoint(0.0F, -10.0F, 0.0F);
		head.addChild(bone3_r1);
		setRotationAngle(bone3_r1, 0.3491F, 0.0F, 0.0F);
		

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, -10.0F, 0.0F);
		head.addChild(bone3);
		setRotationAngle(bone3, 0.3491F, 0.0F, 0.0F);
		bone3.setTextureOffset(200, 188).addBox(-8.0F, -1.0F, -10.0F, 1.0F, 1.0F, 11.0F, 0.0F, false);
		bone3.setTextureOffset(140, 180).addBox(7.0F, -1.0F, -10.0F, 1.0F, 1.0F, 11.0F, 0.0F, false);
		bone3.setTextureOffset(183, 187).addBox(-7.0F, -1.0F, -10.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bone3.setTextureOffset(206, 172).addBox(5.0F, -1.0F, -10.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bone3.setTextureOffset(227, 175).addBox(-1.0F, -1.0F, -10.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bone3.setTextureOffset(241, 169).addBox(-5.0F, -2.0F, -10.0F, 4.0F, 3.0F, 1.0F, 0.0F, false);
		bone3.setTextureOffset(174, 175).addBox(1.0F, -2.0F, -10.0F, 4.0F, 3.0F, 1.0F, 0.0F, false);

		paper = new ModelRenderer(this);
		paper.setRotationPoint(0.0F, 1.0F, -9.0F);
		total.addChild(paper);
		setRotationAngle(paper, 0.0873F, 0.0F, 0.0F);
		

		bone_r1 = new ModelRenderer(this);
		bone_r1.setRotationPoint(-14.0F, -22.0F, -8.0F);
		paper.addChild(bone_r1);
		setRotationAngle(bone_r1, -1.5708F, -0.6981F, 0.0F);
		

		bone2_r1 = new ModelRenderer(this);
		bone2_r1.setRotationPoint(6.0F, -22.0F, -8.0F);
		paper.addChild(bone2_r1);
		setRotationAngle(bone2_r1, -1.5708F, 0.6981F, 0.0F);
		

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(6.0F, -22.0F, -8.0F);
		paper.addChild(bone2);
		setRotationAngle(bone2, -1.5708F, 0.6981F, 0.0F);
		bone2.setTextureOffset(88, 212).addBox(0.0F, 0.0F, -13.0F, 1.0F, 16.0F, 25.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-14.0F, -22.0F, -8.0F);
		paper.addChild(bone);
		setRotationAngle(bone, -1.5708F, -0.6981F, 0.0F);
		bone.setTextureOffset(5, 174).addBox(-1.0F, 0.0F, -13.0F, 1.0F, 16.0F, 25.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(NewspaperZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
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

	@Override
	public void setDestroyed(LivingEntity entity) {
		float now = entity.getHealth();
		float max = entity.getMaxHealth();
		this.paper.showModel = now >= max / 2;
	}
	
}
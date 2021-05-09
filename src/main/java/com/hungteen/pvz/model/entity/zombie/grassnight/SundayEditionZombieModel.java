package com.hungteen.pvz.model.entity.zombie.grassnight;

import com.hungteen.pvz.entity.zombie.grassnight.SundayEditionZombieEntity;
import com.hungteen.pvz.model.entity.IHasDefence;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class SundayEditionZombieModel extends EntityModel<SundayEditionZombieEntity> implements IHasDefence {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer head;
	private final ModelRenderer bone5;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer right_hand;
	private final ModelRenderer left_hand;
	private final ModelRenderer body;
	private final ModelRenderer paper;
	private final ModelRenderer bone2;
	private final ModelRenderer bone;

	public SundayEditionZombieModel() {
		texWidth = 256;
		texHeight = 256;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-4.0F, -23.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.texOffs(230, 192).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 22.0F, 6.0F, 0.0F, false);
		right_leg.texOffs(191, 220).addBox(-3.0F, 21.0F, -5.0F, 6.0F, 2.0F, 9.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(4.0F, -23.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.texOffs(230, 225).addBox(-3.0F, -1.0F, -3.0F, 6.0F, 22.0F, 6.0F, 0.0F, false);
		left_leg.texOffs(196, 242).addBox(-3.0F, 21.0F, -5.0F, 6.0F, 2.0F, 9.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(up);
		

		head = new ModelRenderer(this);
		head.setPos(0.0F, -48.0F, 0.0F);
		up.addChild(head);
		head.texOffs(66, 182).addBox(-7.0F, -14.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(0.0F, -14.0F, 0.0F);
		head.addChild(bone5);
		setRotationAngle(bone5, 0.0F, 0.0F, -0.6981F);
		bone5.texOffs(130, 207).addBox(-1.0F, -1.0F, -1.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, -10.0F, 0.0F);
		head.addChild(bone3);
		setRotationAngle(bone3, 0.3491F, 0.0F, 0.0F);
		bone3.texOffs(200, 188).addBox(-8.0F, -1.0F, -10.0F, 1.0F, 1.0F, 11.0F, 0.0F, false);
		bone3.texOffs(140, 180).addBox(7.0F, -1.0F, -10.0F, 1.0F, 1.0F, 11.0F, 0.0F, false);
		bone3.texOffs(183, 187).addBox(-7.0F, -1.0F, -10.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bone3.texOffs(206, 172).addBox(5.0F, -1.0F, -10.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bone3.texOffs(227, 175).addBox(-1.0F, -1.0F, -10.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		bone3.texOffs(241, 169).addBox(-5.0F, -2.0F, -10.0F, 4.0F, 3.0F, 1.0F, 0.0F, false);
		bone3.texOffs(174, 175).addBox(1.0F, -2.0F, -10.0F, 4.0F, 3.0F, 1.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(0.0F, -14.0F, 0.0F);
		head.addChild(bone4);
		setRotationAngle(bone4, 0.0F, -0.8727F, -0.6981F);
		bone4.texOffs(0, 0).addBox(-1.0F, -1.0F, 0.0F, 9.0F, 1.0F, 1.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(-2.0F, -13.0F, 1.0F);
		head.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 0.0F, 0.5236F);
		bone6.texOffs(169, 208).addBox(-9.0F, -2.0F, -2.0F, 10.0F, 1.0F, 1.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setPos(0.0F, -14.0F, -2.0F);
		head.addChild(bone7);
		setRotationAngle(bone7, -0.3491F, 1.0472F, -0.6981F);
		bone7.texOffs(0, 0).addBox(-1.0F, -1.0F, 0.0F, 9.0F, 1.0F, 1.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-8.0F, -45.0F, 3.0F);
		up.addChild(right_hand);
		setRotationAngle(right_hand, -1.309F, 0.0873F, 0.0F);
		right_hand.texOffs(148, 222).addBox(-6.0F, 0.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(9.0F, -45.0F, 3.0F);
		up.addChild(left_hand);
		setRotationAngle(left_hand, -1.309F, -0.0873F, 0.0F);
		left_hand.texOffs(59, 221).addBox(-1.0F, 0.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, -31.0F, 0.0F);
		up.addChild(body);
		body.texOffs(3, 221).addBox(-8.0F, -17.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		paper = new ModelRenderer(this);
		paper.setPos(0.0F, -42.0F, -20.0F);
		up.addChild(paper);
		setRotationAngle(paper, -1.309F, 0.0F, 0.0F);
		

		bone2 = new ModelRenderer(this);
		bone2.setPos(10.75F, 1.0F, 3.0F);
		paper.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.8727F);
		bone2.texOffs(160, 75).addBox(1.7677F, -6.1066F, -28.0F, 1.0F, 22.0F, 45.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(-13.75F, 1.0F, 3.0F);
		paper.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, -0.8727F);
		bone.texOffs(160, 4).addBox(-0.8393F, -3.8085F, -28.0F, 1.0F, 22.0F, 45.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(SundayEditionZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.yRot = netHeadYaw / (180F / (float)Math.PI);
        this.head.xRot = headPitch / (180F / (float)Math.PI);
        this.right_leg.xRot = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.xRot = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
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
	
	@Override
	public void setDestroyed(LivingEntity entity) {
		if(entity instanceof SundayEditionZombieEntity) {
			this.paper.visible = !((SundayEditionZombieEntity) entity).canPartsBeRemoved();
		}
	}
	
}
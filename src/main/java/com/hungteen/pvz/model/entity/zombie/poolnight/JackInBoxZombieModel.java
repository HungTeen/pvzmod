package com.hungteen.pvz.model.entity.zombie.poolnight;

import com.hungteen.pvz.entity.zombie.poolnight.JackInBoxZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class JackInBoxZombieModel extends EntityModel<JackInBoxZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_arm;
	private final ModelRenderer bone;
	private final ModelRenderer right_arm;
	private final ModelRenderer head;
	private final ModelRenderer box;
	private final ModelRenderer jack;
	private final ModelRenderer jack_head;
	private final ModelRenderer h1;
	private final ModelRenderer h3;
	private final ModelRenderer h2;
	private final ModelRenderer top;

	public JackInBoxZombieModel() {
		textureWidth = 128;
		textureHeight = 128;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, -24.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.setTextureOffset(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);
		left_leg.setTextureOffset(0, 31).addBox(-3.0F, 23.0F, -6.0F, 6.0F, 1.0F, 3.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, -24.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.setTextureOffset(25, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);
		right_leg.setTextureOffset(0, 35).addBox(-3.0F, 23.0F, -6.0F, 6.0F, 1.0F, 3.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, -24.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.setTextureOffset(51, 0).addBox(-8.0F, -24.0F, -5.0F, 16.0F, 24.0F, 10.0F, 0.0F, false);

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(12.0F, -20.0F, 0.0F);
		up.addChild(left_arm);
		setRotationAngle(left_arm, -0.5236F, 0.0F, 0.0F);
		left_arm.setTextureOffset(21, 31).addBox(-4.0F, -4.0F, -3.0F, 6.0F, 26.0F, 6.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(2.0F, 21.0F, 0.0F);
		left_arm.addChild(bone);
		setRotationAngle(bone, -1.0472F, 0.0F, 0.0F);
		bone.setTextureOffset(1, 40).addBox(0.0F, 0.0F, -1.0F, 2.0F, 1.0F, 7.0F, 0.0F, false);

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(-12.0F, -20.0F, 0.0F);
		up.addChild(right_arm);
		setRotationAngle(right_arm, -1.5708F, 0.0F, 0.0F);
		right_arm.setTextureOffset(48, 35).addBox(-2.0F, -4.0F, -4.0F, 6.0F, 26.0F, 6.0F, 0.0F, false);
		right_arm.setTextureOffset(2, 49).addBox(0.0F, 22.0F, 1.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -24.0F, 0.0F);
		up.addChild(head);
		head.setTextureOffset(0, 68).addBox(-7.0F, -14.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);

		box = new ModelRenderer(this);
		box.setRotationPoint(0.0F, 0.0F, 0.0F);
		up.addChild(box);
		box.setTextureOffset(1, 97).addBox(-3.0F, -4.0F, -21.0F, 12.0F, 1.0F, 12.0F, 0.0F, false);
		box.setTextureOffset(75, 35).addBox(-3.0F, -14.0F, -21.0F, 1.0F, 10.0F, 12.0F, 0.0F, false);
		box.setTextureOffset(75, 59).addBox(8.0F, -14.0F, -21.0F, 1.0F, 10.0F, 12.0F, 0.0F, false);
		box.setTextureOffset(50, 69).addBox(-2.0F, -14.0F, -21.0F, 10.0F, 10.0F, 1.0F, 0.0F, false);
		box.setTextureOffset(58, 83).addBox(-2.0F, -14.0F, -10.0F, 10.0F, 10.0F, 1.0F, 0.0F, false);

		jack = new ModelRenderer(this);
		jack.setRotationPoint(3.0F, -4.0F, -15.0F);
		box.addChild(jack);
		setRotationAngle(jack, 0.0873F, 0.0F, 0.0F);
		jack.setTextureOffset(106, 1).addBox(-1.0F, -18.0F, -1.0F, 2.0F, 18.0F, 2.0F, 0.0F, false);

		jack_head = new ModelRenderer(this);
		jack_head.setRotationPoint(0.0F, -18.0F, 0.0F);
		jack.addChild(jack_head);
		setRotationAngle(jack_head, 0.2618F, 0.0F, 0.0F);
		jack_head.setTextureOffset(106, 22).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		h1 = new ModelRenderer(this);
		h1.setRotationPoint(0.0F, -3.0F, -2.0F);
		jack_head.addChild(h1);
		setRotationAngle(h1, -0.4363F, 0.0F, 0.0F);
		h1.setTextureOffset(106, 32).addBox(-1.0F, -1.0F, -3.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);

		h3 = new ModelRenderer(this);
		h3.setRotationPoint(0.0F, -3.0F, 2.0F);
		jack_head.addChild(h3);
		setRotationAngle(h3, 0.4363F, 0.0F, 0.0F);
		h3.setTextureOffset(103, 40).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);

		h2 = new ModelRenderer(this);
		h2.setRotationPoint(0.0F, -4.0F, 0.0F);
		jack_head.addChild(h2);
		setRotationAngle(h2, 0.0F, 0.0F, -0.2618F);
		h2.setTextureOffset(104, 50).addBox(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);

		top = new ModelRenderer(this);
		top.setRotationPoint(3.0F, -14.0F, -20.0F);
		box.addChild(top);
		top.setTextureOffset(2, 112).addBox(-5.0F, -1.0F, 0.0F, 10.0F, 1.0F, 11.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(JackInBoxZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if(entity.getAnimTick() <= JackInBoxZombieEntity.MIN_ANIM_TICK) {
			int tick = JackInBoxZombieEntity.MIN_ANIM_TICK - entity.getAnimTick();
			this.top.rotateAngleX = tick * 1.1f / 10f;
		} else {
			this.top.rotateAngleX = 0;
			this.right_arm.rotateAngleX = MathHelper.sin(ageInTicks) / 4f - 1f;
		}
		this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		this.jack.showModel = (entity.getAnimTick() < JackInBoxZombieEntity.MIN_ANIM_TICK / 4);
		this.box.showModel = entity.hasMetal();
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
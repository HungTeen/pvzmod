package com.hungteen.pvz.model.entity.zombie.grassnight;

import com.hungteen.pvz.entity.zombie.grassnight.ScreenDoorZombieEntity;
import com.hungteen.pvz.model.entity.IHasDefence;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class ScreenDoorZombieModel extends EntityModel<ScreenDoorZombieEntity> implements IHasDefence{
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer door;

	public ScreenDoorZombieModel() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-4.0F, -23.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.setTextureOffset(44, 0).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(4.0F, -23.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.setTextureOffset(0, 0).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, 0.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -23.0F, 0.0F);
		up.addChild(body);
		body.setTextureOffset(0, 41).addBox(-8.0F, -25.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(8.0F, -48.0F, 0.0F);
		up.addChild(left_hand);
		setRotationAngle(left_hand, -0.8727F, 0.0F, 0.0F);
		left_hand.setTextureOffset(96, 60).addBox(0.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-8.0F, -48.0F, 0.0F);
		up.addChild(right_hand);
		setRotationAngle(right_hand, -0.8727F, 0.0F, 0.0F);
		right_hand.setTextureOffset(96, 0).addBox(-8.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -48.0F, 0.0F);
		up.addChild(head);
		head.setTextureOffset(16, 96).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);

		door = new ModelRenderer(this);
		door.setRotationPoint(0.0F, -30.0F, -20.0F);
		up.addChild(door);
		door.setTextureOffset(192, 186).addBox(-10.0F, -22.0F, -1.0F, 20.0F, 42.0F, 2.0F, 0.0F, false);
		door.setTextureOffset(58, 196).addBox(-13.0F, -22.0F, -2.0F, 3.0F, 42.0F, 4.0F, 0.0F, false);
		door.setTextureOffset(103, 197).addBox(10.0F, -22.0F, -2.0F, 3.0F, 42.0F, 4.0F, 0.0F, false);
		door.setTextureOffset(137, 172).addBox(-13.0F, -25.0F, -2.0F, 26.0F, 3.0F, 4.0F, 0.0F, false);
		door.setTextureOffset(132, 128).addBox(-13.0F, 20.0F, -2.0F, 26.0F, 5.0F, 4.0F, 0.0F, false);
		door.setTextureOffset(16, 240).addBox(10.0F, -1.0F, 2.0F, 3.0F, 3.0F, 4.0F, 0.0F, false);
		door.setTextureOffset(21, 222).addBox(10.0F, -1.0F, -6.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
		door.setTextureOffset(27, 203).addBox(11.0F, 0.0F, -5.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		door.setTextureOffset(46, 179).addBox(-14.0F, 17.0F, -2.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		door.setTextureOffset(30, 179).addBox(-14.0F, -22.0F, -2.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		door.setTextureOffset(12, 182).addBox(-14.0F, -3.0F, -2.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(ScreenDoorZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.head.rotateAngleY = netHeadYaw / (180F / (float)Math.PI);
        this.head.rotateAngleX = headPitch / (180F / (float)Math.PI);
        this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        if(entity.canPartsBeRemoved()) {
	        this.right_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	        this.left_hand.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
		} else {
			this.right_hand.rotateAngleX = -0.8727F;
			this.left_hand.rotateAngleX = -0.8727F;
		}
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
		if(entity instanceof ScreenDoorZombieEntity) {
			this.door.showModel = !((ScreenDoorZombieEntity) entity).canPartsBeRemoved();
		}
	}
	
}
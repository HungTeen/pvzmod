package com.hungteen.pvz.client.model.entity.zombie.grass;

import java.util.Optional;

import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.hungteen.pvz.common.entity.zombie.grass.ScreenDoorZombieEntity;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class ScreenDoorZombieModel extends PVZZombieModel<ScreenDoorZombieEntity> {
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
		texWidth = 256;
		texHeight = 256;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-4.0F, -24.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.texOffs(44, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setPos(4.0F, -24.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.texOffs(0, 0).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setPos(0.0F, -24.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.texOffs(0, 41).addBox(-8.0F, -24.0F, -4.0F, 16.0F, 24.0F, 8.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(12.0F, -20.0F, 0.0F);
		up.addChild(left_hand);
		setRotationAngle(left_hand, -1.0472F, 0.0F, 0.0F);
		left_hand.texOffs(96, 60).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-12.0F, -20.0F, 0.0F);
		up.addChild(right_hand);
		setRotationAngle(right_hand, -1.0472F, 0.0F, 0.0F);
		right_hand.texOffs(96, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		door = new ModelRenderer(this);
		door.setPos(12.0F, 23.0F, 1.0F);
		right_hand.addChild(door);
		setRotationAngle(door, 1.0472F, 0.0F, 0.0F);
		door.texOffs(192, 186).addBox(-10.0F, -22.0F, -1.0F, 20.0F, 42.0F, 2.0F, 0.0F, false);
		door.texOffs(58, 196).addBox(-13.0F, -22.0F, -2.0F, 3.0F, 42.0F, 4.0F, 0.0F, false);
		door.texOffs(103, 197).addBox(10.0F, -22.0F, -2.0F, 3.0F, 42.0F, 4.0F, 0.0F, false);
		door.texOffs(137, 172).addBox(-13.0F, -25.0F, -2.0F, 26.0F, 3.0F, 4.0F, 0.0F, false);
		door.texOffs(132, 128).addBox(-13.0F, 20.0F, -2.0F, 26.0F, 5.0F, 4.0F, 0.0F, false);
		door.texOffs(16, 240).addBox(10.0F, -1.0F, 2.0F, 3.0F, 3.0F, 4.0F, 0.0F, false);
		door.texOffs(21, 222).addBox(10.0F, -1.0F, -6.0F, 3.0F, 3.0F, 1.0F, 0.0F, false);
		door.texOffs(27, 203).addBox(11.0F, 0.0F, -5.0F, 1.0F, 1.0F, 3.0F, 0.0F, false);
		door.texOffs(46, 179).addBox(-14.0F, 17.0F, -2.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		door.texOffs(30, 179).addBox(-14.0F, -22.0F, -2.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);
		door.texOffs(12, 182).addBox(-14.0F, -3.0F, -2.0F, 1.0F, 5.0F, 1.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -24.0F, 0.0F);
		up.addChild(head);
		head.texOffs(16, 96).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F, 0.0F, false);
		
		this.rightHandOriginAngel = - 60;
	}

	@Override
	public void updateFreeParts(ScreenDoorZombieEntity entity) {
		super.updateFreeParts(entity);
		final boolean isPartDestroyed = ! entity.hasMetal();
		this.door.visible = ! isPartDestroyed;
		this.isLeftHandFree = isPartDestroyed;
		this.isRightHandFree = isPartDestroyed;
	}
	
	@Override
	public void refreshAnim() {
		this.getZombieLeftHand().xRot = -1.0472F;
		this.getZombieRightHand().xRot = -1.0472F;
	}

	@Override
	public Optional<ModelRenderer> getHandDefence() {
		return Optional.ofNullable(this.door);
	}
	
	@Override
	public ModelRenderer getZombieLeftHand() {
		return this.left_hand;
	}

	@Override
	public ModelRenderer getZombieRightHand() {
		return this.right_hand;
	}

	@Override
	public ModelRenderer getZombieLeftLeg() {
		return this.left_leg;
	}

	@Override
	public ModelRenderer getZombieRightLeg() {
		return this.right_leg;
	}

	@Override
	public ModelRenderer getZombieHead() {
		return this.head;
	}
	
	@Override
	public ModelRenderer getZombieUpBody() {
		return this.up;
	}

	@Override
	public ModelRenderer getZombieWholeBody() {
		return this.total;
	}

}
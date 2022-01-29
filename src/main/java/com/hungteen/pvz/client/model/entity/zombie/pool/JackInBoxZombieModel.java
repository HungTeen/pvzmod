package com.hungteen.pvz.client.model.entity.zombie.pool;

import java.util.Optional;

import com.hungteen.pvz.api.interfaces.IBodyEntity;
import com.hungteen.pvz.api.enums.BodyType;
import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.hungteen.pvz.common.entity.zombie.pool.JackInBoxZombieEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class JackInBoxZombieModel extends PVZZombieModel<JackInBoxZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer bone;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer box;
	private final ModelRenderer jack;
	private final ModelRenderer jack_head;
	private final ModelRenderer h1;
	private final ModelRenderer h3;
	private final ModelRenderer h2;
	private final ModelRenderer top;
	private final ModelRenderer cube_r1;

	public JackInBoxZombieModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		left_leg = new ModelRenderer(this);
		left_leg.setPos(4.0F, -24.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.texOffs(0, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);
		left_leg.texOffs(0, 31).addBox(-3.0F, 23.0F, -6.0F, 6.0F, 1.0F, 3.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-4.0F, -24.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.texOffs(25, 0).addBox(-3.0F, 0.0F, -3.0F, 6.0F, 24.0F, 6.0F, 0.0F, false);
		right_leg.texOffs(0, 35).addBox(-3.0F, 23.0F, -6.0F, 6.0F, 1.0F, 3.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setPos(0.0F, -24.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.texOffs(51, 0).addBox(-8.0F, -24.0F, -5.0F, 16.0F, 24.0F, 10.0F, 0.0F, false);

		left_hand = new ModelRenderer(this);
		left_hand.setPos(12.0F, -20.0F, 0.0F);
		up.addChild(left_hand);
		setRotationAngle(left_hand, -0.5236F, 0.0F, 0.0F);
		left_hand.texOffs(21, 31).addBox(-4.0F, -4.0F, -3.0F, 6.0F, 26.0F, 6.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(2.0F, 21.0F, 0.0F);
		left_hand.addChild(bone);
		setRotationAngle(bone, -1.0472F, 0.0F, 0.0F);
		bone.texOffs(1, 40).addBox(0.0F, 0.0F, -1.0F, 2.0F, 1.0F, 7.0F, 0.0F, false);

		box = new ModelRenderer(this);
		box.setPos(-10.0F, 27.0F, -3.0F);
		left_hand.addChild(box);
		setRotationAngle(box, 0.5236F, 0.0F, 0.0F);
		box.texOffs(1, 97).addBox(-5.0F, -1.0F, -7.0F, 12.0F, 1.0F, 12.0F, 0.0F, false);
		box.texOffs(75, 35).addBox(-5.0F, -11.0F, -7.0F, 1.0F, 10.0F, 12.0F, 0.0F, false);
		box.texOffs(75, 59).addBox(6.0F, -11.0F, -7.0F, 1.0F, 10.0F, 12.0F, 0.0F, false);
		box.texOffs(50, 69).addBox(-4.0F, -11.0F, -7.0F, 10.0F, 10.0F, 1.0F, 0.0F, false);
		box.texOffs(58, 83).addBox(-4.0F, -11.0F, 4.0F, 10.0F, 10.0F, 1.0F, 0.0F, false);

		jack = new ModelRenderer(this);
		jack.setPos(1.0F, -1.0F, -1.0F);
		box.addChild(jack);
		setRotationAngle(jack, 0.0873F, 0.0F, 0.0F);
		jack.texOffs(106, 1).addBox(-1.0F, -18.0F, -1.0F, 2.0F, 18.0F, 2.0F, 0.0F, false);

		jack_head = new ModelRenderer(this);
		jack_head.setPos(0.0F, -18.0F, 0.0F);
		jack.addChild(jack_head);
		setRotationAngle(jack_head, 0.2618F, 0.0F, 0.0F);
		jack_head.texOffs(106, 22).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		h1 = new ModelRenderer(this);
		h1.setPos(0.0F, -3.0F, -2.0F);
		jack_head.addChild(h1);
		setRotationAngle(h1, -0.4363F, 0.0F, 0.0F);
		h1.texOffs(106, 32).addBox(-1.0F, -1.0F, -3.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);

		h3 = new ModelRenderer(this);
		h3.setPos(0.0F, -3.0F, 2.0F);
		jack_head.addChild(h3);
		setRotationAngle(h3, 0.4363F, 0.0F, 0.0F);
		h3.texOffs(103, 40).addBox(-1.0F, -1.0F, -1.0F, 1.0F, 2.0F, 4.0F, 0.0F, false);

		h2 = new ModelRenderer(this);
		h2.setPos(0.0F, -4.0F, 0.0F);
		jack_head.addChild(h2);
		setRotationAngle(h2, 0.0F, 0.0F, -0.2618F);
		h2.texOffs(104, 50).addBox(-1.0F, -4.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);

		top = new ModelRenderer(this);
		top.setPos(1.0F, -11.0F, -6.0F);
		box.addChild(top);
		setRotationAngle(top, 2.0944F, 0.0F, 0.0F);
		

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(-3.0F, 0.0F, 0.0F);
		top.addChild(cube_r1);
		setRotationAngle(cube_r1, -2.0944F, 0.0F, 0.0F);
		cube_r1.texOffs(2, 112).addBox(-2.0F, -1.0F, 0.0F, 10.0F, 1.0F, 11.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-12.0F, -20.0F, 0.0F);
		up.addChild(right_hand);
		setRotationAngle(right_hand, -0.6981F, 0.0F, 0.0F);
		right_hand.texOffs(48, 35).addBox(-2.0F, -4.0F, -4.0F, 6.0F, 26.0F, 6.0F, 0.0F, false);
		right_hand.texOffs(2, 49).addBox(0.0F, 22.0F, 1.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -24.0F, 0.0F);
		up.addChild(head);
		head.texOffs(0, 68).addBox(-7.0F, -14.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
		
		this.rightHandOriginAngel = - 30;
	}

	@Override
	public void setupAnim(JackInBoxZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		if(entity.getAttackTime() >= 0  && entity.getAttackTime() <= JackInBoxZombieEntity.JACK_EXPLODE_CD) {
			final int tick = JackInBoxZombieEntity.JACK_EXPLODE_CD - entity.getAttackTime();
			this.top.xRot = 2.0944F - tick / 10f;
		} else {
			this.top.xRot = 2.0944F;
			this.right_hand.xRot = MathHelper.sin(ageInTicks) / 4f - 1f;
		}
	}
	
	@Override
	public void renderBody(IBodyEntity entity, MatrixStack stack, IVertexBuilder buffer, int packedLight,
			int packedOverlay) {
		if(entity.getBodyType() == BodyType.HAND) {
			this.box.visible = false;
		}
		this.jack.visible = false;
		super.renderBody(entity, stack, buffer, packedLight, packedOverlay);
	}
	
	@Override
	public void updateFreeParts(JackInBoxZombieEntity entity) {
		super.updateFreeParts(entity);
		final boolean hasBox = entity.hasMetal();
		this.isLeftHandFree = ! hasBox;
		this.box.visible = hasBox;
		this.jack.visible = (entity.getAttackTime() >= 0 && entity.getAttackTime() < JackInBoxZombieEntity.JACK_EXPLODE_CD / 4);
	}
	
	@Override
	public Optional<ModelRenderer> getHandDefence() {
		return Optional.ofNullable(this.box);
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
package com.hungteen.pvz.client.model.entity.plant.enforce;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.enforce.ChomperEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class ChomperModel extends PVZPlantModel<ChomperEntity> {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone22;
	private final ModelRenderer head;
	private final ModelRenderer bone14;
	private final ModelRenderer bone15;
	private final ModelRenderer up_mouse;
	private final ModelRenderer bone9;
	private final ModelRenderer bone8;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer bone13;
	private final ModelRenderer down_mouse;
	private final ModelRenderer leaf;
	private final ModelRenderer bone16;
	private final ModelRenderer bone17;
	private final ModelRenderer bone18;
	private final ModelRenderer bone19;
	private final ModelRenderer bone20;
	private final ModelRenderer bone21;
	private final ModelRenderer tongue;
	private final ModelRenderer tongue2;
	private final ModelRenderer root;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;

	public ChomperModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		setRotationAngle(body, -0.1309F, 0.0F, 0.0F);
		body.texOffs(2, 118).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, -16.9572F, 1.3474F);
		body.addChild(bone);
		setRotationAngle(bone, -0.6545F, 0.0F, 0.0F);
		bone.texOffs(14, 118).addBox(-1.0F, 2.5352F, 3.5905F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, -2.8145F, -0.7628F);
		bone.addChild(bone2);
		setRotationAngle(bone2, -0.829F, 0.0F, 0.0F);
		bone2.texOffs(27, 116).addBox(-1.0F, -1.07F, 0.2365F, 2.0F, 2.0F, 8.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, 3.3053F, -5.9933F);
		bone2.addChild(bone3);
		setRotationAngle(bone3, -0.8727F, 0.0F, 0.0F);
		bone3.texOffs(49, 118).addBox(-1.0F, -7.5847F, 0.6528F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		bone22 = new ModelRenderer(this);
		bone22.setPos(0.0F, 4.7601F, -3.6526F);
		bone3.addChild(bone22);
		setRotationAngle(bone22, -2.4871F, 0.0F, 0.0F);
		bone22.texOffs(61, 120).addBox(-1.0F, -1.7939F, -9.8869F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -22.0F, -2.0F);
		body.addChild(head);
		setRotationAngle(head, 0.25F, 0.0F, 0.0F);
		head.texOffs(72, 113).addBox(-6.0F, -7.0F, -4.0F, 12.0F, 10.0F, 4.0F, 0.0F, false);

		bone14 = new ModelRenderer(this);
		bone14.setPos(0.0F, -6.0F, 0.0F);
		head.addChild(bone14);
		setRotationAngle(bone14, -0.9599F, 0.0F, 0.0F);
		bone14.texOffs(105, 107).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone15 = new ModelRenderer(this);
		bone15.setPos(0.0F, -1.0F, 0.0F);
		bone14.addChild(bone15);
		setRotationAngle(bone15, 0.0F, 0.0F, -0.7854F);
		bone15.texOffs(35, 45).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		up_mouse = new ModelRenderer(this);
		up_mouse.setPos(0.0F, -4.0F, -2.0F);
		head.addChild(up_mouse);
		setRotationAngle(up_mouse, -0.5236F, 0.0F, 0.0F);
		up_mouse.texOffs(75, 94).addBox(6.0F, -2.0F, -11.0F, 1.0F, 4.0F, 11.0F, 0.0F, false);
		up_mouse.texOffs(22, 96).addBox(-6.0F, -3.0F, -12.0F, 12.0F, 5.0F, 12.0F, 0.0F, false);
		up_mouse.texOffs(103, 89).addBox(-5.0F, -2.0F, -13.0F, 10.0F, 4.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(100, 70).addBox(-7.0F, -2.0F, -11.0F, 1.0F, 4.0F, 11.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(-6.0F, 2.0F, -7.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(-6.0F, 2.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(-6.0F, 2.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(-6.0F, 2.0F, -9.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(-6.0F, 2.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(-5.0F, 2.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(-3.0F, 2.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(-1.0F, 2.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(1.0F, 2.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(3.0F, 2.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(5.0F, 2.0F, -12.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(5.0F, 2.0F, -10.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(5.0F, 2.0F, -8.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(5.0F, 2.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		up_mouse.texOffs(13, 110).addBox(5.0F, 2.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setPos(0.0F, -3.0F, -10.0F);
		up_mouse.addChild(bone9);
		setRotationAngle(bone9, -0.0873F, 0.0F, 0.0F);
		bone9.texOffs(9, 99).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 5.0F, 2.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setPos(0.0F, -4.0F, 0.0F);
		bone9.addChild(bone8);
		setRotationAngle(bone8, 0.0F, 0.0F, -0.7854F);
		bone8.texOffs(51, 43).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone10 = new ModelRenderer(this);
		bone10.setPos(0.0F, -3.0F, -6.0F);
		up_mouse.addChild(bone10);
		setRotationAngle(bone10, -0.1745F, 0.0F, 0.0F);
		bone10.texOffs(4, 90).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone11 = new ModelRenderer(this);
		bone11.setPos(0.0F, -3.0F, 0.0F);
		bone10.addChild(bone11);
		setRotationAngle(bone11, 0.0F, 0.0F, -0.7854F);
		bone11.texOffs(62, 44).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		bone12 = new ModelRenderer(this);
		bone12.setPos(0.0F, -3.0F, -2.0F);
		up_mouse.addChild(bone12);
		setRotationAngle(bone12, -0.2618F, 0.0F, 0.0F);
		bone12.texOffs(32, 89).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		bone13 = new ModelRenderer(this);
		bone13.setPos(0.0F, -2.0F, 0.0F);
		bone12.addChild(bone13);
		setRotationAngle(bone13, 0.0F, 0.0F, -0.7854F);
		bone13.texOffs(19, 91).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

		down_mouse = new ModelRenderer(this);
		down_mouse.setPos(0.0F, 1.0F, -2.0F);
		head.addChild(down_mouse);
		setRotationAngle(down_mouse, 0.5236F, 0.0F, 0.0F);
		down_mouse.texOffs(46, 79).addBox(6.0F, -2.0F, -11.0F, 1.0F, 3.0F, 11.0F, 0.0F, false);
		down_mouse.texOffs(5, 63).addBox(-6.0F, -2.0F, -12.0F, 12.0F, 4.0F, 12.0F, 0.0F, false);
		down_mouse.texOffs(74, 88).addBox(-5.0F, -2.0F, -13.0F, 10.0F, 3.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(73, 71).addBox(-7.0F, -2.0F, -11.0F, 1.0F, 3.0F, 11.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(-6.0F, -3.0F, -4.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(5.0F, -3.0F, -3.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(-6.0F, -3.0F, -8.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(-6.0F, -3.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(-6.0F, -3.0F, -10.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(-6.0F, -3.0F, -12.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(-4.0F, -3.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(-2.0F, -3.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(0.0F, -3.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(2.0F, -3.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(4.0F, -3.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(5.0F, -3.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(5.0F, -3.0F, -9.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(5.0F, -3.0F, -7.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		down_mouse.texOffs(5, 110).addBox(5.0F, -3.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		leaf = new ModelRenderer(this);
		leaf.setPos(0.0F, -1.0F, 0.0F);
		head.addChild(leaf);
		leaf.texOffs(64, 73).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 1.0F, 0.0F, false);

		bone16 = new ModelRenderer(this);
		bone16.setPos(0.0F, 0.0F, 1.0F);
		leaf.addChild(bone16);
		setRotationAngle(bone16, -0.5236F, 0.0F, 0.0F);
		bone16.texOffs(58, 65).addBox(-1.0F, -4.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

		bone17 = new ModelRenderer(this);
		bone17.setPos(0.0F, 0.0F, 1.0F);
		leaf.addChild(bone17);
		setRotationAngle(bone17, -0.5236F, 0.0F, 1.0472F);
		bone17.texOffs(70, 64).addBox(-1.0F, -4.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

		bone18 = new ModelRenderer(this);
		bone18.setPos(0.0F, 0.0F, 1.0F);
		leaf.addChild(bone18);
		setRotationAngle(bone18, -0.5236F, 0.0F, 2.0944F);
		bone18.texOffs(92, 64).addBox(-1.0F, -4.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

		bone19 = new ModelRenderer(this);
		bone19.setPos(0.0F, 0.0F, 1.0F);
		leaf.addChild(bone19);
		setRotationAngle(bone19, -0.2618F, 0.0F, -3.1416F);
		bone19.texOffs(108, 61).addBox(-1.0F, -4.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

		bone20 = new ModelRenderer(this);
		bone20.setPos(0.0F, 0.0F, 1.0F);
		leaf.addChild(bone20);
		setRotationAngle(bone20, -0.5236F, 0.0F, -1.0472F);
		bone20.texOffs(119, 61).addBox(-1.0F, -4.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

		bone21 = new ModelRenderer(this);
		bone21.setPos(0.0F, 0.0F, 1.0F);
		leaf.addChild(bone21);
		setRotationAngle(bone21, -0.5236F, 0.0F, -2.0944F);
		bone21.texOffs(81, 57).addBox(-1.0F, -4.866F, -1.5F, 2.0F, 4.0F, 1.0F, 0.0F, false);

		tongue = new ModelRenderer(this);
		tongue.setPos(0.0F, -1.0F, -5.0F);
		head.addChild(tongue);
		tongue.texOffs(7, 53).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 4.0F, 0.0F, false);

		tongue2 = new ModelRenderer(this);
		tongue2.setPos(0.0F, 0.0F, -4.0F);
		tongue.addChild(tongue2);
		tongue2.texOffs(23, 52).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 1.0F, 4.0F, 0.0F, false);

		root = new ModelRenderer(this);
		root.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(root);
		root.texOffs(44, 52).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(0.0F, -2.0F, 0.0F);
		root.addChild(bone4);
		setRotationAngle(bone4, 0.0F, 0.0F, -0.3927F);
		bone4.texOffs(68, 50).addBox(-7.0F, -1.0F, -2.0F, 7.0F, 1.0F, 4.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(0.0F, -2.0F, 0.0F);
		root.addChild(bone5);
		setRotationAngle(bone5, 0.0F, 0.0F, 0.3927F);
		bone5.texOffs(97, 50).addBox(0.0F, -1.0F, -2.0F, 7.0F, 1.0F, 4.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(0.0F, -3.0F, 0.0F);
		root.addChild(bone6);
		setRotationAngle(bone6, -0.3927F, 0.0F, 0.0F);
		bone6.texOffs(102, 37).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 1.0F, 7.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setPos(0.0F, -3.0F, 0.0F);
		root.addChild(bone7);
		setRotationAngle(bone7, 0.3927F, 0.0F, 0.0F);
		bone7.texOffs(75, 38).addBox(-2.0F, 0.0F, -7.0F, 4.0F, 1.0F, 7.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(ChomperEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		final int tick = entity.getAttackTime();
		final int T = ChomperEntity.ATTACK_ANIM_CD;
		if(tick > 0 && tick <= T / 2) {//1 - 10
			this.body.xRot = 0.05f * tick;//0.5
			this.head.xRot = 0.25f - 0.075f * tick;//0.25 - -0.5
			if(tick <= T / 3) {//1 - 6
				this.up_mouse.xRot = - 0.52f - 0.05f*tick;
				this.down_mouse.xRot = 0.52f + 0.05f*tick;
			}else {// 7 - 10 ( 1 - 4)
				float tmp = tick - T * 2f / 3;
				this.up_mouse.xRot = - 0.82f + 0.2f * tmp;
				this.down_mouse.xRot = 0.82f - 0.2f * tmp;
			}
		} else if(tick > T / 2){// 11 - 20 (1 - 10)
			final int tmp = tick - T / 2;
			this.body.xRot = 0.5f - 0.05f * tmp;
			this.head.xRot = - 0.5f + 0.075f * tmp;
			this.up_mouse.xRot = -0.05f * tmp;
			this.down_mouse.xRot = 0.05f * tmp;
		} else {
			this.body.xRot = 0;
			if(entity.getRestTick() > 0) {
				this.tongue.xRot = entity.getRandom().nextFloat() - 0.5f;
				this.tongue2.xRot = entity.getRandom().nextFloat() - 0.5f;
				this.head.xRot = 1.04f;
				this.up_mouse.xRot = - 0.15f;
				this.down_mouse.xRot = 0.15f;
				this.leaf.zRot = 0.52f;
			}else {
				this.tongue.xRot = 0;
				this.tongue2.xRot = 0;
				this.head.xRot = 0.25f;
				this.up_mouse.xRot = -0.52f;
				this.down_mouse.xRot = 0.52f;
				this.leaf.zRot = 0;
			}
		}
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<ChomperEntity> getPlantModel() {
		return this;
	}
	
}
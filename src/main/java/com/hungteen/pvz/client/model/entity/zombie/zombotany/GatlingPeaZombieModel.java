package com.hungteen.pvz.client.model.entity.zombie.zombotany;

import com.hungteen.pvz.client.model.entity.zombie.PVZZombieModel;
import com.hungteen.pvz.common.entity.zombie.zombotany.GatlingPeaZombieEntity;

import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class GatlingPeaZombieModel extends PVZZombieModel<GatlingPeaZombieEntity> {
	private final ModelRenderer total;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer left_hand;
	private final ModelRenderer right_hand;
	private final ModelRenderer head;
	private final ModelRenderer gar;
	private final ModelRenderer helmet;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;

	public GatlingPeaZombieModel() {
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
		left_hand.texOffs(96, 60).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		right_hand = new ModelRenderer(this);
		right_hand.setPos(-12.0F, -20.0F, 0.0F);
		up.addChild(right_hand);
		right_hand.texOffs(96, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 24.0F, 8.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -26.0F, -1.0F);
		up.addChild(head);
		head.texOffs(4, 243).addBox(-5.0F, -10.0F, 7.0F, 10.0F, 10.0F, 1.0F, 0.0F, false);
		head.texOffs(200, 224).addBox(-7.0F, -12.0F, -7.0F, 14.0F, 14.0F, 14.0F, 0.0F, false);
		head.texOffs(183, 236).addBox(7.0F, -10.0F, -5.0F, 1.0F, 10.0F, 6.0F, 0.0F, false);
		head.texOffs(164, 234).addBox(-8.0F, -10.0F, -5.0F, 1.0F, 10.0F, 6.0F, 0.0F, false);
		head.texOffs(122, 241).addBox(-5.0F, -13.0F, -5.0F, 10.0F, 1.0F, 10.0F, 0.0F, false);
		head.texOffs(224, 190).addBox(-3.0F, -6.0F, -17.0F, 6.0F, 6.0F, 10.0F, 0.0F, false);
		head.texOffs(234, 169).addBox(-4.0F, -6.0F, -17.0F, 1.0F, 6.0F, 10.0F, 0.0F, false);
		head.texOffs(179, 203).addBox(-3.0F, -7.0F, -17.0F, 6.0F, 1.0F, 10.0F, 0.0F, false);
		head.texOffs(198, 177).addBox(3.0F, -6.0F, -17.0F, 1.0F, 6.0F, 10.0F, 0.0F, false);
		head.texOffs(144, 187).addBox(-3.0F, 0.0F, -17.0F, 6.0F, 1.0F, 10.0F, 0.0F, false);

		gar = new ModelRenderer(this);
		gar.setPos(0.0F, -3.0F, -17.0F);
		head.addChild(gar);
		gar.texOffs(102, 244).addBox(-3.0F, -1.0F, -5.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
		gar.texOffs(83, 246).addBox(-1.0F, -3.0F, -5.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
		gar.texOffs(64, 246).addBox(1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
		gar.texOffs(44, 245).addBox(-1.0F, 1.0F, -5.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);

		helmet = new ModelRenderer(this);
		helmet.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(helmet);
		helmet.texOffs(4, 222).addBox(-9.0F, -12.0F, 1.0F, 2.0F, 15.0F, 2.0F, 0.0F, false);
		helmet.texOffs(21, 221).addBox(7.0F, -13.0F, 1.0F, 2.0F, 16.0F, 2.0F, 0.0F, false);
		helmet.texOffs(32, 219).addBox(-9.0F, -15.0F, -8.0F, 18.0F, 3.0F, 18.0F, 0.0F, false);
		helmet.texOffs(112, 210).addBox(-8.0F, -12.0F, 3.0F, 16.0F, 13.0F, 8.0F, 0.0F, false);
		helmet.texOffs(2, 194).addBox(-8.0F, -17.0F, -7.0F, 16.0F, 2.0F, 16.0F, 0.0F, false);
		helmet.texOffs(2, 176).addBox(-6.0F, -19.0F, -5.0F, 12.0F, 2.0F, 12.0F, 0.0F, false);
		helmet.texOffs(230, 210).addBox(-4.0F, 2.0F, -7.0F, 8.0F, 4.0F, 2.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(-7.0F, 2.0F, 2.0F);
		helmet.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.7854F, 0.4363F);
		bone.texOffs(77, 211).addBox(-0.5255F, -0.4837F, -3.3539F, 9.0F, 1.0F, 2.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(7.0F, 3.0F, 2.0F);
		helmet.addChild(bone2);
		setRotationAngle(bone2, 0.0F, -0.7854F, -0.4363F);
		bone2.texOffs(176, 224).addBox(-7.8337F, -1.0611F, -3.9948F, 9.0F, 1.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(GatlingPeaZombieEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.gar.zRot = ageInTicks / 5;
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
package com.hungteen.pvz.client.model.entity.plant.magic;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.magic.StrangeCatEntity;
import com.hungteen.pvz.utils.AnimationUtil;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class StrangeCatModel extends PVZPlantModel<StrangeCatEntity> {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer legs;
	private final ModelRenderer left_ear;
	private final ModelRenderer bone3;
	private final ModelRenderer bone2;
	private final ModelRenderer right_ear;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer tail;
	private final ModelRenderer tail1;
	private final ModelRenderer tail2;
	private final ModelRenderer tail3;
	private final ModelRenderer tail4;
	private final ModelRenderer tail5;
	private final ModelRenderer bone10;
	private final ModelRenderer bone11;
	private final ModelRenderer tail6;

	public StrangeCatModel() {
		texWidth = 512;
		texHeight = 512;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(body);
		body.texOffs(256, 384).addBox(-33.3301F, -68.0F, -32.0F, 64.0F, 64.0F, 64.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(0.0F, 4.0F, 0.0F);
		body.addChild(bone4);
		

		bone5 = new ModelRenderer(this);
		bone5.setPos(35.0F, -68.0F, -32.0F);
		bone4.addChild(bone5);
		setRotationAngle(bone5, -0.2618F, 0.0F, 0.0F);
		bone5.texOffs(367, 0).addBox(-71.3301F, 0.0F, 0.0F, 3.0F, 10.0F, 70.0F, 0.0F, false);
		bone5.texOffs(214, 0).addBox(-4.3301F, 0.0F, 0.0F, 3.0F, 10.0F, 70.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(0.0F, 0.0F, 0.0F);
		bone4.addChild(bone6);
		bone6.texOffs(288, 11).addBox(-36.3301F, -49.8827F, 31.6148F, 70.0F, 10.0F, 4.0F, 0.0F, false);
		bone6.texOffs(294, 40).addBox(-36.3301F, -68.0F, -35.0F, 70.0F, 10.0F, 3.0F, 0.0F, false);

		legs = new ModelRenderer(this);
		legs.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(legs);
		legs.texOffs(368, 338).addBox(-43.3301F, -4.0F, 6.0F, 36.0F, 4.0F, 36.0F, 0.0F, false);
		legs.texOffs(368, 298).addBox(4.6699F, -4.0F, 6.0F, 36.0F, 4.0F, 36.0F, 0.0F, false);
		legs.texOffs(368, 258).addBox(-43.3301F, -4.0F, -42.0F, 36.0F, 4.0F, 36.0F, 0.0F, false);
		legs.texOffs(367, 217).addBox(4.6699F, -4.0F, -42.0F, 36.0F, 4.0F, 36.0F, 0.0F, false);

		left_ear = new ModelRenderer(this);
		left_ear.setPos(32.0F, -66.0F, -6.0F);
		total.addChild(left_ear);
		setRotationAngle(left_ear, 0.0F, 0.0F, 0.8727F);
		

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, 0.0F, 0.0F);
		left_ear.addChild(bone3);
		bone3.texOffs(479, 302).addBox(-15.0486F, -5.4591F, -3.999F, 9.0F, 20.0F, 8.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, 0.0F, 0.0F);
		left_ear.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.8727F);
		bone2.texOffs(459, 0).addBox(-13.855F, 1.0189F, -4.0F, 19.0F, 7.0F, 8.0F, 0.0F, false);

		right_ear = new ModelRenderer(this);
		right_ear.setPos(-35.0F, -66.0F, -6.0F);
		total.addChild(right_ear);
		setRotationAngle(right_ear, 0.0F, 0.0F, -0.8727F);
		

		bone7 = new ModelRenderer(this);
		bone7.setPos(0.0F, 0.0F, 0.0F);
		right_ear.addChild(bone7);
		bone7.texOffs(479, 262).addBox(6.0486F, -5.4591F, -3.99F, 9.0F, 20.0F, 8.0F, 0.0F, true);

		bone8 = new ModelRenderer(this);
		bone8.setPos(0.0F, 0.0F, 0.0F);
		right_ear.addChild(bone8);
		setRotationAngle(bone8, 0.0F, 0.0F, -0.8727F);
		bone8.texOffs(459, 32).addBox(-5.145F, 1.0189F, -4.0F, 19.0F, 7.0F, 8.0F, 0.0F, true);

		tail = new ModelRenderer(this);
		tail.setPos(0.0F, -24.8669F, 35.9447F);
		total.addChild(tail);
		

		tail1 = new ModelRenderer(this);
		tail1.setPos(0.0F, 9.8669F, -7.9447F);
		tail.addChild(tail1);
		tail1.texOffs(6, 448).addBox(-5.3301F, -5.0F, -5.0F, 8.0F, 8.0F, 37.0F, 0.0F, false);

		tail2 = new ModelRenderer(this);
		tail2.setPos(0.0F, 6.7538F, 22.5237F);
		tail.addChild(tail2);
		setRotationAngle(tail2, -0.8727F, 0.0F, 0.0F);
		tail2.texOffs(304, 323).addBox(-5.32F, -29.2438F, -2.3326F, 8.0F, 32.0F, 8.0F, 0.0F, false);

		tail3 = new ModelRenderer(this);
		tail3.setPos(0.0F, -23.3801F, 21.2917F);
		tail.addChild(tail3);
		setRotationAngle(tail3, -1.6581F, 0.0F, 0.0F);
		tail3.texOffs(222, 331).addBox(-5.3301F, -28.5396F, -11.7591F, 8.0F, 8.0F, 25.0F, 0.0F, false);

		tail4 = new ModelRenderer(this);
		tail4.setPos(0.0F, -21.0F, 1.0F);
		tail.addChild(tail4);
		setRotationAngle(tail4, 0.2618F, 0.0F, 0.0F);
		tail4.texOffs(168, 372).addBox(-5.3301F, -2.336F, 43.0566F, 8.0F, 4.0F, 8.0F, 0.0F, false);

		tail5 = new ModelRenderer(this);
		tail5.setPos(2.6699F, -19.5361F, -3.1281F);
		tail.addChild(tail5);
		setRotationAngle(tail5, 0.2618F, 0.0F, 0.0F);
		

		bone10 = new ModelRenderer(this);
		bone10.setPos(0.0F, 0.0F, 0.0F);
		tail5.addChild(bone10);
		bone10.texOffs(0, 0).addBox(-7.2426F, -28.9745F, 43.9555F, 6.0F, 27.0F, 7.0F, 0.0F, false);
		bone10.texOffs(0, 0).addBox(-7.2426F, -28.9745F, 51.4408F, 6.0F, 27.0F, 7.0F, 0.0F, false);
		bone10.texOffs(0, 0).addBox(-11.4853F, -28.9745F, 48.1982F, 7.0F, 27.0F, 6.0F, 0.0F, false);
		bone10.texOffs(0, 0).addBox(-4.0F, -28.9745F, 48.1982F, 7.0F, 27.0F, 6.0F, 0.0F, false);

		bone11 = new ModelRenderer(this);
		bone11.setPos(0.0F, 0.0F, 0.0F);
		tail5.addChild(bone11);
		setRotationAngle(bone11, 0.0F, 0.7854F, 0.0F);
		bone11.texOffs(0, 0).addBox(-42.2026F, -28.9745F, 25.9599F, 6.0F, 27.0F, 7.0F, 0.0F, false);
		bone11.texOffs(0, 0).addBox(-42.2026F, -28.9745F, 33.4452F, 6.0F, 27.0F, 7.0F, 0.0F, false);
		bone11.texOffs(0, 0).addBox(-46.4452F, -28.9745F, 30.2026F, 7.0F, 27.0F, 6.0F, 0.0F, false);
		bone11.texOffs(0, 0).addBox(-38.9599F, -28.9745F, 30.2026F, 7.0F, 27.0F, 6.0F, 0.0F, false);

		tail6 = new ModelRenderer(this);
		tail6.setPos(-2.0F, -61.0F, 39.0F);
		tail.addChild(tail6);
		setRotationAngle(tail6, -0.2618F, -0.1745F, -0.6981F);
		tail6.texOffs(377, 272).addBox(4.6394F, -8.0881F, -8.206F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		tail6.texOffs(368, 225).addBox(7.6394F, -10.0881F, -10.206F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		tail6.texOffs(475, 226).addBox(-1.3606F, -6.0881F, -6.206F, 8.0F, 8.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(StrangeCatEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		final int time = entity.getAttackTime();
		if(entity.isResting()) {
			this.tail.xRot = 0;
		} else if(time == 0) {
			final int T = 20;
			final int now = entity.getExistTick() % T;
			this.tail.xRot = AnimationUtil.getUpDownUpDown(now, T, 30);
		} else {
			final int tick = StrangeCatEntity.ANIM_CD - time;
		    final float v = 3.14159F / StrangeCatEntity.ANIM_CD / 2;
		    this.tail.xRot = 0.8F - 0.8F * Math.abs(MathHelper.cos(v * tick));
		}
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<StrangeCatEntity> getPlantModel() {
		return this;
	}
}
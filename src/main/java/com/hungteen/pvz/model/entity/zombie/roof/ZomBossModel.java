package com.hungteen.pvz.model.entity.zombie.roof;

import com.hungteen.pvz.entity.zombie.roof.ZomBossEntity;
import com.hungteen.pvz.entity.zombie.roof.ZomBossEntity.ZomBossStates;
import com.hungteen.pvz.utils.AnimationUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class ZomBossModel extends EntityModel<ZomBossEntity> {
	private final ModelRenderer total;
	private final ModelRenderer left_leg;
	private final ModelRenderer left_shoo;
	private final ModelRenderer right_leg;
	private final ModelRenderer right_shoo;
	private final ModelRenderer up;
	private final ModelRenderer body;
	private final ModelRenderer pipe1;
	private final ModelRenderer pipe2;
	private final ModelRenderer head;
	private final ModelRenderer mouse;
	private final ModelRenderer lace;
	private final ModelRenderer red_eyes;
	private final ModelRenderer yellow_eyes;
	private final ModelRenderer blue_eyes;
	private final ModelRenderer left_arm;
	private final ModelRenderer right_arm;

	public ZomBossModel() {
		textureWidth = 256;
		textureHeight = 256;

		total = new ModelRenderer(this);
		total.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(6.0F, -41.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.setTextureOffset(155, 211).addBox(-5.0F, 0.0F, -3.0F, 10.0F, 33.0F, 10.0F, 0.0F, false);
		left_leg.setTextureOffset(202, 220).addBox(-5.0F, 12.0F, -4.0F, 10.0F, 4.0F, 1.0F, 0.0F, false);

		left_shoo = new ModelRenderer(this);
		left_shoo.setRotationPoint(-6.0F, 41.0F, 0.0F);
		left_leg.addChild(left_shoo);
		left_shoo.setTextureOffset(198, 230).addBox(0.0F, -8.0F, -8.0F, 12.0F, 8.0F, 16.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-7.0F, -41.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.setTextureOffset(154, 164).addBox(-4.0F, 0.0F, -3.0F, 10.0F, 33.0F, 10.0F, 0.0F, false);
		right_leg.setTextureOffset(230, 220).addBox(-4.0F, 12.0F, -4.0F, 10.0F, 4.0F, 1.0F, 0.0F, false);

		right_shoo = new ModelRenderer(this);
		right_shoo.setRotationPoint(7.0F, 41.0F, 0.0F);
		right_leg.addChild(right_shoo);
		right_shoo.setTextureOffset(198, 187).addBox(-12.0F, -8.0F, -8.0F, 12.0F, 8.0F, 16.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setRotationPoint(0.0F, -41.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.setTextureOffset(186, 2).addBox(-11.0F, -40.0F, -4.0F, 22.0F, 40.0F, 12.0F, 0.0F, false);

		pipe1 = new ModelRenderer(this);
		pipe1.setRotationPoint(9.0F, -39.0F, 6.0F);
		body.addChild(pipe1);
		setRotationAngle(pipe1, -0.6109F, 0.0F, 0.0F);
		pipe1.setTextureOffset(200, 170).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 10.0F, 0.0F, false);
		pipe1.setTextureOffset(245, 166).addBox(-2.0F, -2.0F, 10.0F, 2.0F, 16.0F, 2.0F, 0.0F, false);

		pipe2 = new ModelRenderer(this);
		pipe2.setRotationPoint(9.0F, -39.0F, 6.0F);
		body.addChild(pipe2);
		setRotationAngle(pipe2, -0.6109F, 0.0F, 0.0F);
		pipe2.setTextureOffset(226, 150).addBox(-18.0F, -2.0F, 0.0F, 2.0F, 2.0F, 10.0F, 0.0F, false);
		pipe2.setTextureOffset(210, 146).addBox(-18.0F, -2.0F, 10.0F, 2.0F, 16.0F, 2.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -40.0F, -3.0F);
		up.addChild(head);
		head.setTextureOffset(188, 58).addBox(-8.0F, -9.0F, -16.0F, 16.0F, 13.0F, 16.0F, 0.0F, false);
		head.setTextureOffset(195, 118).addBox(2.0F, -13.0F, -13.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(195, 134).addBox(-4.0F, -13.0F, -13.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(172, 97).addBox(8.0F, -4.0F, -9.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(171, 77).addBox(9.0F, -5.0F, -10.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);
		head.setTextureOffset(176, 103).addBox(-9.0F, -4.0F, -9.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.setTextureOffset(154, 98).addBox(-11.0F, -5.0F, -10.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);

		mouse = new ModelRenderer(this);
		mouse.setRotationPoint(0.0F, 6.0F, -1.0F);
		head.addChild(mouse);
		mouse.setTextureOffset(186, 92).addBox(-8.0F, 0.0F, -15.0F, 16.0F, 3.0F, 16.0F, 0.0F, false);
		mouse.setTextureOffset(134, 6).addBox(-8.0F, -2.0F, -3.0F, 16.0F, 2.0F, 4.0F, 0.0F, false);
		mouse.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, -15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.setTextureOffset(0, 0).addBox(1.0F, -2.0F, -15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.setTextureOffset(0, 0).addBox(4.0F, -2.0F, -15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.setTextureOffset(0, 0).addBox(-2.0F, -2.0F, -15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.setTextureOffset(0, 0).addBox(-5.0F, -2.0F, -15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.setTextureOffset(0, 0).addBox(-7.0F, -2.0F, -15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.setTextureOffset(0, 0).addBox(-8.0F, -2.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.setTextureOffset(0, 0).addBox(-8.0F, -2.0F, -9.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.setTextureOffset(0, 0).addBox(-8.0F, -2.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.setTextureOffset(0, 0).addBox(7.0F, -2.0F, -7.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.setTextureOffset(0, 0).addBox(7.0F, -2.0F, -12.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.setTextureOffset(0, 0).addBox(-1.0F, -1.0F, -15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.setTextureOffset(0, 0).addBox(2.0F, -1.0F, -15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.setTextureOffset(0, 0).addBox(6.0F, -1.0F, -15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.setTextureOffset(0, 0).addBox(-5.0F, -1.0F, -15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.setTextureOffset(0, 0).addBox(-8.0F, -1.0F, -15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.setTextureOffset(0, 0).addBox(-8.0F, -1.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.setTextureOffset(0, 0).addBox(-8.0F, -1.0F, -8.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.setTextureOffset(0, 0).addBox(7.0F, -1.0F, -12.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.setTextureOffset(0, 0).addBox(7.0F, -1.0F, -8.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.setTextureOffset(0, 0).addBox(7.0F, -1.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		lace = new ModelRenderer(this);
		lace.setRotationPoint(0.0F, 0.0F, 2.0F);
		head.addChild(lace);
		setRotationAngle(lace, 1.1345F, 0.0F, 0.0F);
		lace.setTextureOffset(151, 151).addBox(-11.0F, -3.0F, 0.0F, 22.0F, 3.0F, 3.0F, 0.0F, false);
		lace.setTextureOffset(155, 126).addBox(-11.0F, -3.0F, -11.0F, 3.0F, 3.0F, 11.0F, 0.0F, false);
		lace.setTextureOffset(152, 108).addBox(8.0F, -3.0F, -11.0F, 3.0F, 3.0F, 11.0F, 0.0F, false);
		lace.setTextureOffset(102, 247).addBox(-11.0F, -3.0F, -14.0F, 22.0F, 3.0F, 3.0F, 0.0F, false);

		red_eyes = new ModelRenderer(this);
		red_eyes.setRotationPoint(-2.0F, -1.0F, -17.0F);
		head.addChild(red_eyes);
		red_eyes.setTextureOffset(86, 249).addBox(-5.0F, -5.0F, 0.9F, 5.0F, 5.0F, 1.0F, 0.0F, false);
		red_eyes.setTextureOffset(89, 241).addBox(4.0F, -5.0F, 0.9F, 5.0F, 5.0F, 1.0F, 0.0F, false);

		yellow_eyes = new ModelRenderer(this);
		yellow_eyes.setRotationPoint(-2.0F, -1.0F, -17.0F);
		head.addChild(yellow_eyes);
		yellow_eyes.setTextureOffset(92, 231).addBox(-5.0F, -5.0F, 0.9F, 5.0F, 5.0F, 1.0F, 0.0F, false);
		yellow_eyes.setTextureOffset(94, 222).addBox(4.0F, -5.0F, 0.9F, 5.0F, 5.0F, 1.0F, 0.0F, false);

		blue_eyes = new ModelRenderer(this);
		blue_eyes.setRotationPoint(-2.0F, -1.0F, -17.0F);
		head.addChild(blue_eyes);
		blue_eyes.setTextureOffset(92, 202).addBox(-5.0F, -5.0F, 0.9F, 5.0F, 5.0F, 1.0F, 0.0F, false);
		blue_eyes.setTextureOffset(91, 212).addBox(4.0F, -5.0F, 0.9F, 5.0F, 5.0F, 1.0F, 0.0F, false);

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(15.0F, -35.0F, 2.0F);
		up.addChild(left_arm);
		left_arm.setTextureOffset(108, 180).addBox(-4.0F, -5.0F, -5.0F, 10.0F, 48.0F, 10.0F, 0.0F, false);

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(-16.0F, -35.0F, 2.0F);
		up.addChild(right_arm);
		right_arm.setTextureOffset(106, 112).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 48.0F, 10.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(ZomBossEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.red_eyes.showModel = (entity.getZomBossState() == ZomBossStates.FLAME);
		this.blue_eyes.showModel = (entity.getZomBossState() == ZomBossStates.ICE);
		this.yellow_eyes.showModel = (! this.red_eyes.showModel && ! this.blue_eyes.showModel);
		if(entity.getZomBossState() == ZomBossStates.FLAME || entity.getZomBossState() == ZomBossStates.ICE) {
			this.mouse.rotateAngleX = AnimationUtil.getUp(entity.getAttackTime(), entity.getAnimShootCD(), 30);
			this.right_arm.rotateAngleX = 0;
			this.left_arm.rotateAngleX = 0;
		} else if(entity.getZomBossState() == ZomBossStates.CAR) {
			this.right_arm.rotateAngleX = AnimationUtil.getUpDown(entity.getAttackTime(), entity.getAnimThrowCD(), - 60);
			this.mouse.rotateAngleX = 0;
			this.left_arm.rotateAngleX = 0;
		} else if(entity.getZomBossState() == ZomBossStates.STEAL) {
			this.left_arm.rotateAngleX = AnimationUtil.getUpDown(entity.getAttackTime(), entity.getAnimThrowCD(), - 120);
			this.right_arm.rotateAngleX = AnimationUtil.getUpDown(entity.getAttackTime(), entity.getAnimThrowCD(), - 120);
			this.mouse.rotateAngleX = 0;
		} else {
			this.left_arm.rotateAngleX = 0;
			this.right_arm.rotateAngleX = 0;
			this.mouse.rotateAngleX = 0;
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
}
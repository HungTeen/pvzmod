package com.hungteen.pvz.client.model.entity.zombie.roof;

import com.hungteen.pvz.api.interfaces.IBodyEntity;
import com.hungteen.pvz.api.paz.IZombieModel;
import com.hungteen.pvz.common.entity.zombie.base.EdgarRobotEntity;
import com.hungteen.pvz.utils.AnimationUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class EdgarRobotModel<T extends EdgarRobotEntity> extends EntityModel<T> implements IZombieModel<T>{
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

	public EdgarRobotModel() {
		texWidth = 256;
		texHeight = 256;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		left_leg = new ModelRenderer(this);
		left_leg.setPos(6.0F, -41.0F, 0.0F);
		total.addChild(left_leg);
		left_leg.texOffs(155, 211).addBox(-5.0F, 0.0F, -3.0F, 10.0F, 33.0F, 10.0F, 0.0F, false);
		left_leg.texOffs(202, 220).addBox(-5.0F, 12.0F, -4.0F, 10.0F, 4.0F, 1.0F, 0.0F, false);

		left_shoo = new ModelRenderer(this);
		left_shoo.setPos(-6.0F, 41.0F, 0.0F);
		left_leg.addChild(left_shoo);
		left_shoo.texOffs(198, 230).addBox(0.0F, -8.0F, -8.0F, 12.0F, 8.0F, 16.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setPos(-7.0F, -41.0F, 0.0F);
		total.addChild(right_leg);
		right_leg.texOffs(154, 164).addBox(-4.0F, 0.0F, -3.0F, 10.0F, 33.0F, 10.0F, 0.0F, false);
		right_leg.texOffs(230, 220).addBox(-4.0F, 12.0F, -4.0F, 10.0F, 4.0F, 1.0F, 0.0F, false);

		right_shoo = new ModelRenderer(this);
		right_shoo.setPos(7.0F, 41.0F, 0.0F);
		right_leg.addChild(right_shoo);
		right_shoo.texOffs(198, 187).addBox(-12.0F, -8.0F, -8.0F, 12.0F, 8.0F, 16.0F, 0.0F, false);

		up = new ModelRenderer(this);
		up.setPos(0.0F, -41.0F, 0.0F);
		total.addChild(up);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 0.0F);
		up.addChild(body);
		body.texOffs(186, 2).addBox(-11.0F, -40.0F, -4.0F, 22.0F, 40.0F, 12.0F, 0.0F, false);

		pipe1 = new ModelRenderer(this);
		pipe1.setPos(9.0F, -39.0F, 6.0F);
		body.addChild(pipe1);
		setRotationAngle(pipe1, -0.6109F, 0.0F, 0.0F);
		pipe1.texOffs(200, 170).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 10.0F, 0.0F, false);
		pipe1.texOffs(245, 166).addBox(-2.0F, -2.0F, 10.0F, 2.0F, 16.0F, 2.0F, 0.0F, false);

		pipe2 = new ModelRenderer(this);
		pipe2.setPos(9.0F, -39.0F, 6.0F);
		body.addChild(pipe2);
		setRotationAngle(pipe2, -0.6109F, 0.0F, 0.0F);
		pipe2.texOffs(226, 150).addBox(-18.0F, -2.0F, 0.0F, 2.0F, 2.0F, 10.0F, 0.0F, false);
		pipe2.texOffs(210, 146).addBox(-18.0F, -2.0F, 10.0F, 2.0F, 16.0F, 2.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -40.0F, -3.0F);
		up.addChild(head);
		head.texOffs(188, 58).addBox(-8.0F, -9.0F, -16.0F, 16.0F, 13.0F, 16.0F, 0.0F, false);
		head.texOffs(195, 118).addBox(2.0F, -13.0F, -13.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		head.texOffs(195, 134).addBox(-4.0F, -13.0F, -13.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		head.texOffs(172, 97).addBox(8.0F, -4.0F, -9.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.texOffs(171, 77).addBox(9.0F, -5.0F, -10.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);
		head.texOffs(176, 103).addBox(-9.0F, -4.0F, -9.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		head.texOffs(154, 98).addBox(-11.0F, -5.0F, -10.0F, 2.0F, 3.0F, 3.0F, 0.0F, false);

		mouse = new ModelRenderer(this);
		mouse.setPos(0.0F, 6.0F, -1.0F);
		head.addChild(mouse);
		mouse.texOffs(186, 92).addBox(-8.0F, 0.0F, -15.0F, 16.0F, 3.0F, 16.0F, 0.0F, false);
		mouse.texOffs(134, 6).addBox(-8.0F, -2.0F, -3.0F, 16.0F, 2.0F, 4.0F, 0.0F, false);
		mouse.texOffs(0, 0).addBox(-1.0F, -1.0F, -15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.texOffs(0, 0).addBox(1.0F, -2.0F, -15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.texOffs(0, 0).addBox(4.0F, -2.0F, -15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.texOffs(0, 0).addBox(-2.0F, -2.0F, -15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.texOffs(0, 0).addBox(-5.0F, -2.0F, -15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.texOffs(0, 0).addBox(-7.0F, -2.0F, -15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.texOffs(0, 0).addBox(-8.0F, -2.0F, -13.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.texOffs(0, 0).addBox(-8.0F, -2.0F, -9.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.texOffs(0, 0).addBox(-8.0F, -2.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.texOffs(0, 0).addBox(7.0F, -2.0F, -7.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.texOffs(0, 0).addBox(7.0F, -2.0F, -12.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.texOffs(0, 0).addBox(-1.0F, -1.0F, -15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.texOffs(0, 0).addBox(2.0F, -1.0F, -15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.texOffs(0, 0).addBox(6.0F, -1.0F, -15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.texOffs(0, 0).addBox(-5.0F, -1.0F, -15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.texOffs(0, 0).addBox(-8.0F, -1.0F, -15.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.texOffs(0, 0).addBox(-8.0F, -1.0F, -11.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.texOffs(0, 0).addBox(-8.0F, -1.0F, -8.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.texOffs(0, 0).addBox(7.0F, -1.0F, -12.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.texOffs(0, 0).addBox(7.0F, -1.0F, -8.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		mouse.texOffs(0, 0).addBox(7.0F, -1.0F, -5.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		lace = new ModelRenderer(this);
		lace.setPos(0.0F, 0.0F, 2.0F);
		head.addChild(lace);
		setRotationAngle(lace, 1.1345F, 0.0F, 0.0F);
		lace.texOffs(151, 151).addBox(-11.0F, -3.0F, 0.0F, 22.0F, 3.0F, 3.0F, 0.0F, false);
		lace.texOffs(155, 126).addBox(-11.0F, -3.0F, -11.0F, 3.0F, 3.0F, 11.0F, 0.0F, false);
		lace.texOffs(152, 108).addBox(8.0F, -3.0F, -11.0F, 3.0F, 3.0F, 11.0F, 0.0F, false);
		lace.texOffs(102, 247).addBox(-11.0F, -3.0F, -14.0F, 22.0F, 3.0F, 3.0F, 0.0F, false);

		red_eyes = new ModelRenderer(this);
		red_eyes.setPos(-2.0F, -1.0F, -17.0F);
		head.addChild(red_eyes);
		red_eyes.texOffs(86, 249).addBox(-5.0F, -5.0F, 0.9F, 5.0F, 5.0F, 1.0F, 0.0F, false);
		red_eyes.texOffs(89, 241).addBox(4.0F, -5.0F, 0.9F, 5.0F, 5.0F, 1.0F, 0.0F, false);

		yellow_eyes = new ModelRenderer(this);
		yellow_eyes.setPos(-2.0F, -1.0F, -17.0F);
		head.addChild(yellow_eyes);
		yellow_eyes.texOffs(92, 231).addBox(-5.0F, -5.0F, 0.9F, 5.0F, 5.0F, 1.0F, 0.0F, false);
		yellow_eyes.texOffs(94, 222).addBox(4.0F, -5.0F, 0.9F, 5.0F, 5.0F, 1.0F, 0.0F, false);

		blue_eyes = new ModelRenderer(this);
		blue_eyes.setPos(-2.0F, -1.0F, -17.0F);
		head.addChild(blue_eyes);
		blue_eyes.texOffs(92, 202).addBox(-5.0F, -5.0F, 0.9F, 5.0F, 5.0F, 1.0F, 0.0F, false);
		blue_eyes.texOffs(91, 212).addBox(4.0F, -5.0F, 0.9F, 5.0F, 5.0F, 1.0F, 0.0F, false);

		left_arm = new ModelRenderer(this);
		left_arm.setPos(15.0F, -35.0F, 2.0F);
		up.addChild(left_arm);
		left_arm.texOffs(108, 180).addBox(-4.0F, -5.0F, -5.0F, 10.0F, 48.0F, 10.0F, 0.0F, false);

		right_arm = new ModelRenderer(this);
		right_arm.setPos(-16.0F, -35.0F, 2.0F);
		up.addChild(right_arm);
		right_arm.texOffs(106, 112).addBox(-5.0F, -5.0F, -5.0F, 10.0F, 48.0F, 10.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.red_eyes.visible = (entity.getRobotState() == EdgarRobotEntity.EdgarStates.FLAME);
		this.blue_eyes.visible = (entity.getRobotState() == EdgarRobotEntity.EdgarStates.ICE);
		this.yellow_eyes.visible = (! this.red_eyes.visible && ! this.blue_eyes.visible);
		if(entity.getRobotState() == EdgarRobotEntity.EdgarStates.FLAME || entity.getRobotState() == EdgarRobotEntity.EdgarStates.ICE) {
			this.mouse.xRot = AnimationUtil.getUp(entity.getAttackTime(), entity.getAnimShootCD(), 30);
			this.right_arm.xRot = 0;
			this.left_arm.xRot = 0;
		} else if(entity.getRobotState() == EdgarRobotEntity.EdgarStates.CAR) {
			this.right_arm.xRot = AnimationUtil.getUpDown(entity.getAttackTime(), entity.getAnimThrowCD(), - 60);
			this.mouse.xRot = 0;
			this.left_arm.xRot = 0;
		} else if(entity.getRobotState() == EdgarRobotEntity.EdgarStates.STEAL) {
			this.left_arm.xRot = AnimationUtil.getUpDown(entity.getAttackTime(), entity.getAnimStealCD(), - 120);
			this.right_arm.xRot = AnimationUtil.getUpDown(entity.getAttackTime(), entity.getAnimStealCD(), - 120);
			this.mouse.xRot = 0;
		} else {
			this.left_arm.xRot = 0;
			this.right_arm.xRot = 0;
			this.mouse.xRot = 0;
		}
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
	public void tickPartAnim(IBodyEntity entity, float limbSwing, float limbSwingAmount,
			float ageInTicks, float netHeadYaw, float headPitch) {
		
	}

	@Override
	public void renderBody(IBodyEntity entity, MatrixStack stack, IVertexBuilder buffer, int packedLight,
			int packedOverlay) {
		this.setAllInvis();
		switch (entity.getBodyType()) {
		case LEFT_LEG:{
			this.left_leg.visible = true;
			this.left_leg.setPos(0, 24, 0);
			this.left_leg.render(stack, buffer, packedLight, packedOverlay);
			break;
		}
		case RIGHT_LEG:{
			this.right_leg.visible = true;
			this.right_leg.setPos(0, 24, 0);
			this.right_leg.render(stack, buffer, packedLight, packedOverlay);
			break;
		}
		case LEFT_HAND:{
			this.left_arm.visible = true;
			this.left_arm.setPos(0, 24, 0);
			this.left_arm.render(stack, buffer, packedLight, packedOverlay);
			break;
		}
		case RIGHT_HAND:{
			this.right_arm.visible = true;
			this.right_arm.setPos(0, 24, 0);
			this.right_leg.render(stack, buffer, packedLight, packedOverlay);
		}
		case BODY:{
			this.body.visible = true;
			this.body.setPos(0, 24, 0);
			this.body.render(stack, buffer, packedLight, packedOverlay);
			break;
		}
		case HEAD:{
			this.head.visible = true;
			this.head.setPos(0, 24, 0);
			this.head.render(stack, buffer, packedLight, packedOverlay);
		}
		default:
			break;
		}
	}
	
	public void setAllInvis() {
		this.left_leg.visible = false;
		this.right_leg.visible = false;
		this.left_arm.visible = false;
		this.right_arm.visible = false;
		this.head.visible = false;
		this.body.visible = false;
	}

	@Override
	public EntityModel<T> getZombieModel() {
		return this;
	}
}
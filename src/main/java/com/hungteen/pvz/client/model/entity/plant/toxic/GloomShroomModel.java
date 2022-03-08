package com.hungteen.pvz.client.model.entity.plant.toxic;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.toxic.GloomShroomEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class GloomShroomModel extends PVZPlantModel<GloomShroomEntity> {
	private final ModelRenderer total;
	private final ModelRenderer head;
	private final ModelRenderer face;
	private final ModelRenderer shoot;
	private final ModelRenderer shoot1;
	private final ModelRenderer shoot2;
	private final ModelRenderer cube_r1;
	private final ModelRenderer shoot3;
	private final ModelRenderer cube_r2;
	private final ModelRenderer shoot4;
	private final ModelRenderer shoot5;
	private final ModelRenderer shoot6;
	private final ModelRenderer cube_r3;
	private final ModelRenderer shoot7;
	private final ModelRenderer cube_r4;
	private final ModelRenderer shoot8;

	public GloomShroomModel() {
		texWidth = 128;
		texHeight = 128;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(46, 28).addBox(-6.0F, -5.0F, -6.0F, 12.0F, 5.0F, 12.0F, 0.0F, false);
		total.texOffs(42, 48).addBox(-6.0F, -3.0F, -6.0F, 12.0F, 3.0F, 12.0F, 0.1F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(head);
		head.texOffs(0, 40).addBox(-6.5F, -12.0F, -6.5F, 13.0F, 7.0F, 13.0F, -0.3F, false);
		head.texOffs(45, 8).addBox(-6.5F, -11.0F, -6.5F, 13.0F, 6.0F, 13.0F, 0.0F, false);
		head.texOffs(0, 0).addBox(-5.5F, -11.0F, -7.5F, 11.0F, 6.0F, 15.0F, -0.2F, false);
		head.texOffs(0, 21).addBox(-7.5F, -11.0F, -5.5F, 15.0F, 6.0F, 11.0F, -0.2F, false);

		face = new ModelRenderer(this);
		face.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(face);
		face.texOffs(0, 4).addBox(-5.0F, -12.0F, 0.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);
		face.texOffs(0, 0).addBox(2.0F, -12.0F, 0.0F, 3.0F, 1.0F, 3.0F, 0.0F, false);

		shoot = new ModelRenderer(this);
		shoot.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(shoot);
		

		shoot1 = new ModelRenderer(this);
		shoot1.setPos(0.0F, -8.5F, -7.25F);
		shoot.addChild(shoot1);
		shoot1.texOffs(50, 28).addBox(-0.5F, -0.5F, -1.75F, 1.0F, 1.0F, 3.0F, 0.2F, false);
		shoot1.texOffs(41, 45).addBox(-1.5F, -1.5F, -3.25F, 3.0F, 3.0F, 2.0F, 0.0F, false);

		shoot2 = new ModelRenderer(this);
		shoot2.setPos(-6.25F, -8.5F, -6.25F);
		shoot.addChild(shoot2);
		setRotationAngle(shoot2, 0.0F, -0.7854F, 0.0F);
		shoot2.texOffs(0, 8).addBox(-3.25F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(0.25F, 0.5F, -0.5F);
		shoot2.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 1.5708F, 0.0F);
		cube_r1.texOffs(5, 47).addBox(-1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 3.0F, 0.2F, false);

		shoot3 = new ModelRenderer(this);
		shoot3.setPos(-7.25F, -8.5F, 0.0F);
		shoot.addChild(shoot3);
		shoot3.texOffs(0, 40).addBox(-3.25F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(0.25F, 0.5F, -0.5F);
		shoot3.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 1.5708F, 0.0F);
		cube_r2.texOffs(0, 46).addBox(-1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 3.0F, 0.2F, false);

		shoot4 = new ModelRenderer(this);
		shoot4.setPos(-6.25F, -8.5F, 6.25F);
		shoot.addChild(shoot4);
		setRotationAngle(shoot4, 0.0F, -0.7854F, 0.0F);
		shoot4.texOffs(56, 4).addBox(-0.5F, -0.5F, -1.25F, 1.0F, 1.0F, 3.0F, 0.2F, false);
		shoot4.texOffs(43, 0).addBox(-1.5F, -1.5F, 1.25F, 3.0F, 3.0F, 2.0F, 0.0F, false);

		shoot5 = new ModelRenderer(this);
		shoot5.setPos(0.0F, -8.5F, 7.25F);
		shoot.addChild(shoot5);
		shoot5.texOffs(56, 0).addBox(-0.5F, -0.5F, -1.25F, 1.0F, 1.0F, 3.0F, 0.2F, false);
		shoot5.texOffs(43, 5).addBox(-1.5F, -1.5F, 1.25F, 3.0F, 3.0F, 2.0F, 0.0F, false);

		shoot6 = new ModelRenderer(this);
		shoot6.setPos(6.25F, -8.5F, 6.25F);
		shoot.addChild(shoot6);
		setRotationAngle(shoot6, 0.0F, -0.7854F, 0.0F);
		shoot6.texOffs(0, 27).addBox(1.25F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(0.75F, 0.5F, -0.5F);
		shoot6.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 1.5708F, 0.0F);
		cube_r3.texOffs(45, 27).addBox(-1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 3.0F, 0.2F, false);

		shoot7 = new ModelRenderer(this);
		shoot7.setPos(7.25F, -8.5F, 0.0F);
		shoot.addChild(shoot7);
		shoot7.texOffs(0, 21).addBox(1.25F, -1.5F, -1.5F, 2.0F, 3.0F, 3.0F, 0.0F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(0.75F, 0.5F, -0.5F);
		shoot7.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 1.5708F, 0.0F);
		cube_r4.texOffs(7, 11).addBox(-1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 3.0F, 0.2F, false);

		shoot8 = new ModelRenderer(this);
		shoot8.setPos(6.25F, -8.5F, -6.25F);
		shoot.addChild(shoot8);
		setRotationAngle(shoot8, 0.0F, -0.7854F, 0.0F);
		shoot8.texOffs(51, 2).addBox(-0.5F, -0.5F, -1.75F, 1.0F, 1.0F, 3.0F, 0.2F, false);
		shoot8.texOffs(43, 10).addBox(-1.5F, -1.5F, -3.25F, 3.0F, 3.0F, 2.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(GloomShroomEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<GloomShroomEntity> getPlantModel() {
		return this;
	}
}
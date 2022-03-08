package com.hungteen.pvz.client.model.entity.plant.magic;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.magic.CoffeeBeanEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.2
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class CoffeeBeanModel extends PVZPlantModel<CoffeeBeanEntity> {
	private final ModelRenderer total;
	private final ModelRenderer body;
	private final ModelRenderer bone11;
	private final ModelRenderer left_wing;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer right_wing;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;

	public CoffeeBeanModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 16.0F, 4.0F);
		setRotationAngle(total, 1.2217F, 0.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setPos(0.0F, 0.0F, 1.0F);
		total.addChild(body);
		body.texOffs(1, 39).addBox(-6.0F, -8.0F, -9.0F, 12.0F, 8.0F, 16.0F, 0.0F, false);

		bone11 = new ModelRenderer(this);
		bone11.setPos(0.0F, -4.0F, 7.0F);
		body.addChild(bone11);
		setRotationAngle(bone11, 0.2618F, 0.0F, 0.0F);
		bone11.texOffs(51, 48).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 4.0F, 0.0F, false);

		left_wing = new ModelRenderer(this);
		left_wing.setPos(6.0F, -4.0F, 3.0F);
		total.addChild(left_wing);
		setRotationAngle(left_wing, 0.0F, -0.4363F, 0.0F);
		left_wing.texOffs(45, 42).addBox(-2.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(5.0F, 0.0F, 0.0F);
		left_wing.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.1745F, 0.0F);
		bone.texOffs(47, 33).addBox(-2.0F, -1.0F, 0.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(5.0F, 0.0F, 0.0F);
		left_wing.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.6981F, 0.0F);
		bone2.texOffs(33, 31).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(5.0F, 0.0F, 0.0F);
		left_wing.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 1.6581F, 0.0F);
		bone3.texOffs(16, 31).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(5.0F, 0.0F, 0.0F);
		left_wing.addChild(bone4);
		setRotationAngle(bone4, 0.0F, 2.7053F, 0.0F);
		bone4.texOffs(0, 46).addBox(-0.3572F, -1.0F, -0.766F, 2.0F, 1.0F, 6.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(4.0F, 0.0F, 0.0F);
		left_wing.addChild(bone5);
		setRotationAngle(bone5, 0.0F, -2.7925F, 0.0F);
		bone5.texOffs(1, 28).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);

		right_wing = new ModelRenderer(this);
		right_wing.setPos(-6.0F, -4.0F, 3.0F);
		total.addChild(right_wing);
		setRotationAngle(right_wing, 0.0F, 0.4363F, 0.0F);
		right_wing.texOffs(28, 26).addBox(-5.0F, -1.0F, -1.0F, 7.0F, 2.0F, 2.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(-5.0F, 0.0F, 0.0F);
		right_wing.addChild(bone6);
		setRotationAngle(bone6, 0.0F, -0.1745F, 0.0F);
		bone6.texOffs(47, 23).addBox(0.0F, -1.0F, 0.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setPos(-5.0F, 0.0F, 0.0F);
		right_wing.addChild(bone7);
		setRotationAngle(bone7, 0.0F, -0.6981F, 0.0F);
		bone7.texOffs(12, 19).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setPos(-5.0F, 0.0F, 0.0F);
		right_wing.addChild(bone8);
		setRotationAngle(bone8, 0.0F, -1.6581F, 0.0F);
		bone8.texOffs(47, 14).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setPos(-5.0F, 0.0F, 0.0F);
		right_wing.addChild(bone9);
		setRotationAngle(bone9, 0.0F, -2.7053F, 0.0F);
		bone9.texOffs(29, 15).addBox(-1.6428F, -1.0F, -0.766F, 2.0F, 1.0F, 6.0F, 0.0F, false);

		bone10 = new ModelRenderer(this);
		bone10.setPos(-4.0F, 0.0F, 0.0F);
		right_wing.addChild(bone10);
		setRotationAngle(bone10, 0.0F, 2.7925F, 0.0F);
		bone10.texOffs(1, 11).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 6.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(CoffeeBeanEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.left_wing.zRot = MathHelper.sin(ageInTicks / 10) * 3.14159f / 4;
		this.right_wing.zRot = - MathHelper.sin(ageInTicks / 10) * 3.14159f / 4;
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<CoffeeBeanEntity> getPlantModel() {
		return this;
	}
}
package com.hungteen.pvz.client.model.entity.plant.light;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.light.GoldLeafEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class GoldLeafModel extends PVZPlantModel<GoldLeafEntity> {
	private final ModelRenderer total;
	private final ModelRenderer cube_r1;
	private final ModelRenderer cube_r2;
	private final ModelRenderer cube_r3;
	private final ModelRenderer cube_r4;
	private final ModelRenderer hair;
	private final ModelRenderer outer;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer face;
	private final ModelRenderer cube_r5;
	private final ModelRenderer cube_r6;
	private final ModelRenderer left;
	private final ModelRenderer right;

	public GoldLeafModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(40, 41).addBox(-5.0F, -10.0F, -1.0F, 10.0F, 10.0F, 2.0F, 0.0F, false);

		cube_r1 = new ModelRenderer(this);
		cube_r1.setPos(6.132F, -7.6264F, 0.0F);
		total.addChild(cube_r1);
		setRotationAngle(cube_r1, 0.0F, 0.0F, -0.5236F);
		cube_r1.texOffs(24, 45).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, -0.05F, false);

		cube_r2 = new ModelRenderer(this);
		cube_r2.setPos(-6.1439F, -7.7346F, 0.0F);
		total.addChild(cube_r2);
		setRotationAngle(cube_r2, 0.0F, 0.0F, 0.5236F);
		cube_r2.texOffs(24, 45).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, -0.05F, false);

		cube_r3 = new ModelRenderer(this);
		cube_r3.setPos(-4.25F, -2.0F, 0.0F);
		total.addChild(cube_r3);
		setRotationAngle(cube_r3, 0.0F, 0.0F, -0.2182F);
		cube_r3.texOffs(24, 45).addBox(-2.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, -0.05F, false);

		cube_r4 = new ModelRenderer(this);
		cube_r4.setPos(3.75F, -2.0F, 0.0F);
		total.addChild(cube_r4);
		setRotationAngle(cube_r4, 0.0F, 0.0F, 0.2182F);
		cube_r4.texOffs(32, 47).addBox(0.5F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, -0.05F, false);

		hair = new ModelRenderer(this);
		hair.setPos(-1.0F, -11.0F, 0.0F);
		total.addChild(hair);
		hair.texOffs(0, 58).addBox(-2.0F, -3.0F, -1.0F, 6.0F, 4.0F, 2.0F, 0.0F, false);
		hair.texOffs(16, 60).addBox(0.0F, -4.75F, -1.0F, 2.0F, 2.0F, 2.0F, -0.05F, false);
		hair.texOffs(18, 57).addBox(-3.0F, 0.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		hair.texOffs(14, 56).addBox(4.0F, 0.0F, -1.0F, 1.0F, 1.0F, 2.0F, 0.0F, false);
		hair.texOffs(58, 56).addBox(0.5052F, -5.4466F, -1.15F, 1.0F, 1.0F, 2.0F, -0.15F, false);
		hair.texOffs(54, 55).addBox(0.5052F, -5.4466F, -0.85F, 1.0F, 1.0F, 2.0F, -0.15F, false);

		outer = new ModelRenderer(this);
		outer.setPos(0.0F, 0.0F, 0.0F);
		hair.addChild(outer);
		

		bone = new ModelRenderer(this);
		bone.setPos(6.0F, 5.75F, 0.0F);
		outer.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, -0.1745F);
		bone.texOffs(24, 56).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(-4.0F, 5.75F, 0.0F);
		outer.addChild(bone2);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.1745F);
		bone2.texOffs(32, 56).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(-2.0F, 1.0F, 0.0F);
		outer.addChild(bone3);
		setRotationAngle(bone3, 0.0F, 0.0F, 0.5236F);
		bone3.texOffs(40, 58).addBox(-2.3489F, -4.1825F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(4.0F, 1.0F, 0.0F);
		outer.addChild(bone4);
		setRotationAngle(bone4, 0.0F, 0.0F, -0.5236F);
		bone4.texOffs(48, 58).addBox(0.3489F, -4.1825F, -1.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setPos(-2.0F, -2.0F, 0.0F);
		outer.addChild(bone5);
		setRotationAngle(bone5, 0.0F, 0.0F, 1.0472F);
		bone5.texOffs(56, 59).addBox(-1.5274F, -3.9477F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setPos(4.0F, -2.0F, 0.0F);
		outer.addChild(bone6);
		setRotationAngle(bone6, 0.0F, 0.0F, -1.0472F);
		bone6.texOffs(0, 53).addBox(-0.4726F, -3.9477F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);

		face = new ModelRenderer(this);
		face.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(face);
		face.texOffs(18, 48).addBox(-3.0F, -5.75F, -1.5F, 2.0F, 3.0F, 1.0F, -0.4F, false);
		face.texOffs(0, 49).addBox(1.0F, -5.75F, -1.5F, 2.0F, 3.0F, 1.0F, -0.4F, false);
		face.texOffs(10, 50).addBox(-1.0F, -1.75F, -1.5F, 2.0F, 1.0F, 1.0F, -0.4F, false);

		cube_r5 = new ModelRenderer(this);
		cube_r5.setPos(0.6707F, -1.2725F, -1.0F);
		face.addChild(cube_r5);
		setRotationAngle(cube_r5, 0.0F, 0.0F, -0.2618F);
		cube_r5.texOffs(8, 56).addBox(-0.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, -0.4F, false);

		cube_r6 = new ModelRenderer(this);
		cube_r6.setPos(-1.6366F, -1.5313F, -1.0F);
		face.addChild(cube_r6);
		setRotationAngle(cube_r6, 0.0F, 0.0F, 0.2618F);
		cube_r6.texOffs(26, 54).addBox(-0.5F, -0.5F, -0.5F, 2.0F, 1.0F, 1.0F, -0.4F, false);

		left = new ModelRenderer(this);
		left.setPos(6.0F, 0.0F, 0.0F);
		total.addChild(left);
		setRotationAngle(left, 0.0F, 0.0F, -0.7854F);
		left.texOffs(40, 53).addBox(-0.7071F, -2.7071F, -1.0F, 5.0F, 2.0F, 2.0F, -0.05F, false);

		right = new ModelRenderer(this);
		right.setPos(-6.0F, -1.0F, 0.0F);
		total.addChild(right);
		setRotationAngle(right, 0.0F, 0.0F, 0.7854F);
		right.texOffs(9, 52).addBox(-3.5858F, -2.0F, -1.0F, 5.0F, 2.0F, 2.0F, -0.05F, false);
	}

	@Override
	public void setupAnim(GoldLeafEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		this.total.yRot = entity.getAttackTime() * 0.5F;
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<GoldLeafEntity> getPlantModel() {
		return this;
	}
}
package com.hungteen.pvz.client.model.entity.plant.arma;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.arma.MelonPultEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class MelonPultModel extends PVZPlantModel<MelonPultEntity> {
	private final ModelRenderer total;
	private final ModelRenderer nw_r1;
	private final ModelRenderer sw_r1;
	private final ModelRenderer se_r1;
	private final ModelRenderer ne_r1;
	private final ModelRenderer pult;
	private final ModelRenderer bone;
	private final ModelRenderer melon;

	public MelonPultModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		total.texOffs(0, 0).addBox(-7.0F, -10.5F, -5.0F, 14.0F, 10.0F, 10.0F, 0.0F, false);
		total.texOffs(32, 44).addBox(-7.0F, -8.0F, -5.2F, 14.0F, 2.0F, 0.0F, 0.0F, false);

		nw_r1 = new ModelRenderer(this);
		nw_r1.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(nw_r1);
		setRotationAngle(nw_r1, -0.0873F, 0.0F, -0.0873F);
		nw_r1.texOffs(29, 0).addBox(-1.0F, 0.0F, -8.0F, 11.0F, 0.0F, 9.0F, 0.0F, false);

		sw_r1 = new ModelRenderer(this);
		sw_r1.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(sw_r1);
		setRotationAngle(sw_r1, 0.0873F, 0.0F, -0.0873F);
		sw_r1.texOffs(13, 47).addBox(-1.0F, 0.0F, -1.0F, 11.0F, 0.0F, 9.0F, 0.0F, false);

		se_r1 = new ModelRenderer(this);
		se_r1.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(se_r1);
		setRotationAngle(se_r1, 0.0873F, 0.0F, 0.0873F);
		se_r1.texOffs(-9, 47).addBox(-10.0F, 0.0F, -1.0F, 11.0F, 0.0F, 9.0F, 0.0F, false);

		ne_r1 = new ModelRenderer(this);
		ne_r1.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(ne_r1);
		setRotationAngle(ne_r1, -0.0873F, 0.0F, 0.0873F);
		ne_r1.texOffs(39, 18).addBox(-10.0F, 0.0F, -8.0F, 11.0F, 0.0F, 9.0F, 0.0F, false);

		pult = new ModelRenderer(this);
		pult.setPos(0.0F, -7.0F, 0.0F);
		total.addChild(pult);
		setRotationAngle(pult, 0.3927F, 0.0F, 0.0F);
		pult.texOffs(38, 51).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 11.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 0.0F, 9.0F);
		pult.addChild(bone);
		setRotationAngle(bone, -0.2182F, 0.0F, 0.0F);
		bone.texOffs(48, 9).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 5.0F, 0.0F, false);
		bone.texOffs(0, 20).addBox(-5.0F, -2.0F, 3.0F, 10.0F, 5.0F, 10.0F, 0.0F, false);
		bone.texOffs(0, 44).addBox(-4.0F, -2.0F, 4.0F, 8.0F, 4.0F, 8.0F, 0.0F, false);

		melon = new ModelRenderer(this);
		melon.setPos(0.0F, -5.5F, 8.0F);
		bone.addChild(melon);
		setRotationAngle(melon, -0.6545F, 0.0F, 0.0F);
		melon.texOffs(32, 27).addBox(-4.0F, -3.5F, -3.0F, 8.0F, 9.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(MelonPultEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if(entity.getAttackTime() > 0) {
			float percent = 1 - entity.getAttackTime() * 1.0F / entity.getPultAnimTime();
			pult.xRot = (1F - MathHelper.abs(MathHelper.cos(percent * 3.14159F))) * 1.5F;
			this.melon.visible = (percent < 0.5);
		} else {
			pult.xRot = MathHelper.sin(ageInTicks / 10) / 8;
			this.melon.visible = true;
		}
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<MelonPultEntity> getPlantModel() {
		return this;
	}
}
package com.hungteen.pvz.client.model.entity.plant.appease;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.appease.PeaShooterEntity;
import com.hungteen.pvz.utils.AnimationUtil;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class PeaShooterModel extends PVZPlantModel<PeaShooterEntity> {
	private final ModelRenderer total;
	private final ModelRenderer down;
	private final ModelRenderer body;
	private final ModelRenderer head;
	private final ModelRenderer hair;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;

	public PeaShooterModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);
		

		down = new ModelRenderer(this);
		down.setPos(1.0F, 0.0F, 0.0F);
		total.addChild(down);
		down.texOffs(14, 30).addBox(0.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.texOffs(13, 36).addBox(-2.0F, -1.0F, 1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.texOffs(1, 30).addBox(-2.0F, -1.0F, -3.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.texOffs(1, 35).addBox(-4.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		down.texOffs(2, 45).addBox(2.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		down.texOffs(0, 55).addBox(-3.0F, -1.0F, 3.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		down.texOffs(41, 1).addBox(-7.0F, -1.0F, -2.0F, 3.0F, 1.0F, 4.0F, 0.0F, false);
		down.texOffs(42, 30).addBox(-3.0F, -1.0F, -6.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setPos(0.0F, -1.0F, 0.0F);
		total.addChild(body);
		body.texOffs(25, 36).addBox(-1.0F, -14.0F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.texOffs(24, 40).addBox(0.0F, -14.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.texOffs(23, 44).addBox(-2.0F, -14.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.texOffs(25, 31).addBox(-1.0F, -14.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		body.texOffs(55, 45).addBox(-1.0F, -15.0F, -1.0F, 2.0F, 16.0F, 2.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -14.0F, 0.0F);
		body.addChild(head);
		head.texOffs(38, 15).addBox(-2.0F, -4.0F, -7.0F, 4.0F, 4.0F, 2.0F, 0.0F, false);
		head.texOffs(1, 19).addBox(-1.0F, -3.0F, -5.0F, 2.0F, 2.0F, 1.0F, 0.0F, false);
		head.texOffs(0, 1).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		hair = new ModelRenderer(this);
		hair.setPos(0.0F, -7.0F, 4.0F);
		head.addChild(hair);
		setRotationAngle(hair, 0.6109F, 0.0F, 0.0F);
		hair.texOffs(3, 26).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, 0.8192F, 0.4264F);
		hair.addChild(bone2);
		setRotationAngle(bone2, -0.48F, 0.0F, 0.0F);
		bone2.texOffs(54, 10).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, 0.0F, 2.0F);
		bone2.addChild(bone3);
		setRotationAngle(bone3, 0.3927F, 0.0F, 0.0F);
		bone3.texOffs(33, 57).addBox(-2.0F, -1.0F, 0.0F, 4.0F, 6.0F, 1.0F, 0.0F, false);
	}
	
	@Override
	public void setupAnim(PeaShooterEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if(entity.isPlantInSuperMode()) {
			
		} else {
			final int tick = entity.getShootTick();
		    final int T = entity.getShootCD();
		    this.body.xRot = AnimationUtil.getUpDownUpDown(tick, T, - 15);
		    this.head.xRot = AnimationUtil.getUpDownUpDown(tick, T, 15);
		}
		
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<PeaShooterEntity> getPlantModel() {
		return this;
	}
	
}
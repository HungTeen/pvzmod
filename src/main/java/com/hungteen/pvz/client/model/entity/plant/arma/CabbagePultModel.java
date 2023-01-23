package com.hungteen.pvz.client.model.entity.plant.arma;

import com.hungteen.pvz.client.model.entity.plant.PVZPlantModel;
import com.hungteen.pvz.common.entity.plant.arma.CabbagePultEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class CabbagePultModel extends PVZPlantModel<CabbagePultEntity> {
	private final ModelRenderer total;
	private final ModelRenderer cabbage;
	private final ModelRenderer dicoration;
	private final ModelRenderer pult;
	private final ModelRenderer out;
	private final ModelRenderer bullet;

	public CabbagePultModel() {
		texWidth = 64;
		texHeight = 64;

		total = new ModelRenderer(this);
		total.setPos(0.0F, 24.0F, 0.0F);


		cabbage = new ModelRenderer(this);
		cabbage.setPos(0.0F, 0.0F, 0.0F);
		total.addChild(cabbage);
		cabbage.texOffs(0, 0).addBox(-7.0F, -1.0F, -7.0F, 14.0F, 1.0F, 14.0F, 0.0F, false);
		cabbage.texOffs(4, 47).addBox(-6.0F, -0.1F, -6.0F, 12.0F, 1.0F, 12.0F, 0.0F, false);
		cabbage.texOffs(0, 28).addBox(-4.5F, -8.0F, -4.5F, 9.0F, 8.0F, 9.0F, 0.0F, false);
		cabbage.texOffs(32, 20).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);

		dicoration = new ModelRenderer(this);
		dicoration.setPos(0.0F, 0.0F, 0.0F);
		cabbage.addChild(dicoration);
		dicoration.texOffs(0, 15).addBox(-5.0F, -7.0F, -5.0F, 10.0F, 3.0F, 10.0F, 0.0F, false);

		pult = new ModelRenderer(this);
		pult.setPos(0.0F, -9.0F, 0.0F);
		total.addChild(pult);
		setRotationAngle(pult, -0.4363F, 0.0F, 0.0F);
		pult.texOffs(8, 0).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 7.0F, 1.0F, 0.0F, false);

		out = new ModelRenderer(this);
		out.setPos(0.0F, -6.0F, -1.0F);
		pult.addChild(out);
		setRotationAngle(out, -1.309F, 0.0F, 0.0F);
		out.texOffs(0, 0).addBox(-1.0F, -6.0F, 0.0F, 2.0F, 6.0F, 2.0F, 0.0F, false);
		out.texOffs(42, 0).addBox(-3.0F, -12.0F, 0.0F, 6.0F, 6.0F, 4.0F, 0.0F, false);

		bullet = new ModelRenderer(this);
		bullet.setPos(0.0F, -9.0F, 1.0F);
		out.addChild(bullet);
		setRotationAngle(bullet, 1.5708F, 0.0F, 0.0F);
		bullet.texOffs(0, 45).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		bullet.texOffs(0, 53).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, 0.5F, false);
	}

	@Override
	public void setupAnim(CabbagePultEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if(entity.getAttackTime() > 0) {
			float percent = 1 - entity.getAttackTime() * 1.0F / entity.getPultAnimTime();
			pult.xRot = (1F - MathHelper.abs(MathHelper.cos(percent * 3.14159F))) * 1.5F;
			this.bullet.visible = (percent < 0.5);
		} else {
			pult.xRot = MathHelper.sin(ageInTicks / 10) / 8;
			this.bullet.visible = true;
		}
	}

	@Override
	public ModelRenderer getPlantWholeBody() {
		return this.total;
	}

	@Override
	public EntityModel<CabbagePultEntity> getPlantModel() {
		return this;
	}
}
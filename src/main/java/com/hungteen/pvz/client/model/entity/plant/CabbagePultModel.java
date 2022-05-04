package com.hungteen.pvz.client.model.entity.plant;// Made with Blockbench 4.2.2
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.hungteen.pvz.common.entity.plant.CabbagePult;
import com.hungteen.pvz.common.entity.plant.base.PultPlant;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class CabbagePultModel<T extends CabbagePult> extends EntityModel<T> {

	private final ModelPart total;
	private final ModelPart pult;
	private final ModelPart bullet;

	public CabbagePultModel(ModelPart root) {
		this.total = root.getChild("total");
		this.pult = this.total.getChild("pult");
		this.bullet = this.pult.getChild("out").getChild("bullet");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition total = partdefinition.addOrReplaceChild("total", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cabbage = total.addOrReplaceChild("cabbage", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -1.0F, -7.0F, 14.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(4, 47).addBox(-6.0F, -0.1F, -6.0F, 12.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 28).addBox(-4.5F, -8.0F, -4.5F, 9.0F, 8.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(32, 20).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition decoration = cabbage.addOrReplaceChild("decoration", CubeListBuilder.create().texOffs(0, 15).addBox(-5.0F, -7.0F, -5.0F, 10.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition pult = total.addOrReplaceChild("pult", CubeListBuilder.create().texOffs(8, 0).addBox(-1.0F, -6.0F, -1.0F, 2.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, 0.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition out = pult.addOrReplaceChild("out", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -6.0F, 0.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(42, 0).addBox(-3.0F, -12.0F, 0.0F, 6.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.0F, -1.0F, -1.309F, 0.0F, 0.0F));

		PartDefinition bullet = out.addOrReplaceChild("bullet", CubeListBuilder.create().texOffs(0, 45).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 53).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offsetAndRotation(0.0F, -9.0F, 1.0F, 1.5708F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		final int total = (int) entity.getCurrentAttackCD();
		final int attackTick = entity.getAttackTick();
		final int tick = attackTick + PultPlant.PULT_ANIM_CD - total;
		if(tick >= 0){
			pult.xRot = (1F - Mth.abs(Mth.cos(tick * 3.14159F / PultPlant.PULT_ANIM_CD))) * 1.5F;
			this.bullet.visible = tick * 2 < PultPlant.PULT_ANIM_CD;
		} else{
			this.pult.xRot = Mth.sin(ageInTicks / 10) / 8;
			this.bullet.visible = true;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		total.render(poseStack, buffer, packedLight, packedOverlay);
	}
}
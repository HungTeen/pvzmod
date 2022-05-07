package com.hungteen.pvz.client.model.entity.misc;// Made with Blockbench 4.2.4
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class FrozenModel<T extends Entity> extends EntityModel<T> {

	private final ModelPart total;

	public FrozenModel(ModelPart root) {
		this.total = root.getChild("total");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition total = partdefinition.addOrReplaceChild("total", CubeListBuilder.create().texOffs(24, 39).addBox(0.0F, -8.0F, 0.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-5.0F, -2.0F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 12).addBox(-4.0F, -6.0F, -4.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(12, 34).addBox(2.0F, -4.0F, -3.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(40, 35).addBox(-4.0F, -7.0F, -4.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube5_r1 = total.addOrReplaceChild("cube5_r1", CubeListBuilder.create().texOffs(28, 30).addBox(-2.0F, -3.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -2.0F, 4.0F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube3_r1 = total.addOrReplaceChild("cube3_r1", CubeListBuilder.create().texOffs(0, 38).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -2.0F, 2.0F, 0.0F, -0.2618F, 0.6981F));

		PartDefinition cube2_r1 = total.addOrReplaceChild("cube2_r1", CubeListBuilder.create().texOffs(40, 40).addBox(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, -4.0F, -0.7854F, -0.2618F, 0.0F));

		PartDefinition cube1_r1 = total.addOrReplaceChild("cube1_r1", CubeListBuilder.create().texOffs(32, 12).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition nw_r1 = total.addOrReplaceChild("nw_r1", CubeListBuilder.create().texOffs(32, 21).addBox(-2.0F, -5.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -2.0F, -2.0F, 0.7854F, -0.7854F, 0.0F));

		PartDefinition ne_r1 = total.addOrReplaceChild("ne_r1", CubeListBuilder.create().texOffs(16, 24).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -2.0F, -2.0F, 0.7854F, 0.7854F, 0.0F));

		PartDefinition se_r1 = total.addOrReplaceChild("se_r1", CubeListBuilder.create().texOffs(30, 0).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -2.0F, 2.0F, -0.7854F, -0.7854F, 0.0F));

		PartDefinition sw_r1 = total.addOrReplaceChild("sw_r1", CubeListBuilder.create().texOffs(0, 24).addBox(-2.0F, -7.0F, -2.0F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -2.0F, 2.0F, -0.7854F, 0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		total.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
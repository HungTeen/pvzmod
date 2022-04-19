package com.hungteen.pvz.client.model.item;// Made with Blockbench 4.2.2
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class BucketArmorModel<T extends LivingEntity> extends HumanoidModel<T> {

	private final ModelPart total;

	public BucketArmorModel(ModelPart root) {
		super(root);
		this.total = this.head.getChild("total");
	}

	public static LayerDefinition createBodyLayer(CubeDeformation deformation) {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(deformation, 0);
		PartDefinition partdefinition = meshdefinition.getRoot();

		final PartDefinition head = partdefinition.getChild("head");

		head.addOrReplaceChild("total", CubeListBuilder.create().texOffs(0, 39).addBox(-4.5F, -2.0F, -4.5F, 9.0F, 8.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(28, 33).addBox(-4.5F, -8.0F, -4.5F, 9.0F, 6.0F, 9.0F, new CubeDeformation(0.0F))
				.texOffs(32, 48).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

//	@Override
//	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
//		super.renderToBuffer(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//	}

}
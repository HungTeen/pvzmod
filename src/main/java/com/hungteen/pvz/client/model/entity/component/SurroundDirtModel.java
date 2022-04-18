package com.hungteen.pvz.client.model.entity.component;// Made with Blockbench 4.2.1
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.hungteen.pvz.client.model.entity.misc.ComponentModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class SurroundDirtModel<T extends Entity> extends ComponentModel<T> {

	private final ModelPart dirt;

	public SurroundDirtModel(ModelPart root) {
		this.dirt = root.getChild("dirt");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition dirt = partdefinition.addOrReplaceChild("dirt", CubeListBuilder.create(), PartPose.offset(0.0F, 25.0F, 0.0F));

		PartDefinition ne = dirt.addOrReplaceChild("ne", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-6.5F, -4.0F, -6.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 0).mirror().addBox(-4.5F, -2.0F, -6.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 0).mirror().addBox(-2.5F, -2.0F, -6.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 3).mirror().addBox(-6.5F, -3.0F, -4.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition sw = dirt.addOrReplaceChild("sw", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.5F, -2.0F, 4.5F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(2, 5).mirror().addBox(3.5F, -3.0F, 3.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 0).mirror().addBox(4.5F, -2.0F, 0.5F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition se = dirt.addOrReplaceChild("se", CubeListBuilder.create().texOffs(2, 6).mirror().addBox(-6.5F, -4.0F, 3.5F, 4.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 0).mirror().addBox(-6.5F, -2.0F, -0.5F, 3.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 0).mirror().addBox(-2.5F, -2.0F, 4.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition nw = dirt.addOrReplaceChild("nw", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(4.5F, -3.0F, -6.5F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 0).mirror().addBox(1.5F, -2.0F, -6.5F, 3.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 0).mirror().addBox(5.5F, -2.0F, -3.5F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		dirt.render(poseStack, buffer, packedLight, packedOverlay);
	}

	@Override
	public ModelPart getTotalModel() {
		return this.dirt;
	}
}
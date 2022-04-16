package com.hungteen.pvz.client.model.plant;// Made with Blockbench 4.1.5
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.hungteen.pvz.common.entity.plant.PeaShooter;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class SnowPeaModel<T extends PeaShooter> extends EntityModel<T> {

	private final ModelPart total;
	private final ModelPart body;
	private final ModelPart head;

	public SnowPeaModel(ModelPart root) {
		this.total = root.getChild("total");
		this.body = this.total.getChild("body");
		this.head = this.body.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition total = partdefinition.addOrReplaceChild("total", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = total.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 31).addBox(-1.0F, -12.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(30, 0).addBox(-2.0F, -5.0F, -8.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(44, 0).addBox(-3.0F, -6.0F, -9.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-5.0F, -10.0F, -5.0F, 10.0F, 10.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(40, 8).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -12.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition hair = head.addOrReplaceChild("hair", CubeListBuilder.create().texOffs(0, 56).addBox(-2.0F, -3.5F, 0.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(2, 49).addBox(-1.0F, 0.5F, -1.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -4.5F, 5.0F));

		PartDefinition u_r1 = hair.addOrReplaceChild("u_r1", CubeListBuilder.create().texOffs(16, 55).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.0F, 0.0F, 0.2618F, -0.1745F, 0.0873F));

		PartDefinition m_r1 = hair.addOrReplaceChild("m_r1", CubeListBuilder.create().texOffs(32, 49).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -1.0F, 0.0F, -0.1745F, 0.2618F, 0.0F));

		PartDefinition wu_r1 = hair.addOrReplaceChild("wu_r1", CubeListBuilder.create().texOffs(48, 56).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -3.0F, 0.0F, 0.5236F, 0.2618F, 0.0F));

		PartDefinition wd_r1 = hair.addOrReplaceChild("wd_r1", CubeListBuilder.create().texOffs(48, 48).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, 2.0F, 0.0F, -0.5236F, 0.5236F, 0.0F));

		PartDefinition ed_r1 = hair.addOrReplaceChild("ed_r1", CubeListBuilder.create().texOffs(16, 47).addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 2.0F, 0.0F, -0.4363F, -0.5236F, 0.0F));

		PartDefinition eu_r1 = hair.addOrReplaceChild("eu_r1", CubeListBuilder.create().texOffs(34, 57).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -3.0F, 0.0F, 0.5236F, -0.5236F, 0.0F));

		PartDefinition down = total.addOrReplaceChild("down", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition n_r1 = down.addOrReplaceChild("n_r1", CubeListBuilder.create().texOffs(4, 20).addBox(-2.0F, -1.0F, -7.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.8F, 0.0F, -0.1745F, -0.7854F, 0.0F));

		PartDefinition w_r1 = down.addOrReplaceChild("w_r1", CubeListBuilder.create().texOffs(6, 27).addBox(1.0F, -1.0F, -2.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.8F, 0.0F, 0.1745F, -0.7854F, -0.1745F));

		PartDefinition e_r1 = down.addOrReplaceChild("e_r1", CubeListBuilder.create().texOffs(26, 27).addBox(-7.0F, -1.0F, -2.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.8F, 0.0F, -0.1745F, -0.7854F, 0.1745F));

		PartDefinition s_r1 = down.addOrReplaceChild("s_r1", CubeListBuilder.create().texOffs(24, 20).addBox(-2.0F, -1.0F, 1.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.8F, 0.0F, 0.1745F, -0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		total.render(poseStack, buffer, packedLight, packedOverlay);
	}

}
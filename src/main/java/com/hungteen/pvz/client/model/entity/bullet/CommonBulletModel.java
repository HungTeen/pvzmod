package com.hungteen.pvz.client.model.entity.bullet;// Made with Blockbench 4.1.5
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.hungteen.pvz.common.entity.bullet.PVZProjectile;
import com.hungteen.pvz.common.entity.bullet.PultProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class CommonBulletModel<T extends PVZProjectile> extends EntityModel<T> {

	private final ModelPart total;

	public CommonBulletModel(ModelPart root) {
		this.total = root.getChild("total");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition total = partdefinition.addOrReplaceChild("total", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 16).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, 20.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		if(entity instanceof PultProjectile){
			this.total.xRot = ageInTicks * 0.1F;
		} else{
			this.total.xRot = 0;
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		total.render(poseStack, buffer, packedLight, packedOverlay);
	}
}
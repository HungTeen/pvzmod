package com.hungteen.pvz.api;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public interface IZombieModel<T extends LivingEntity & IZombieEntity> {

	/**
	 * use for drop part entity to render.
	 * not for current entity.
	 * {link @ZombieBodyRender}
	 */
	void tickPartAnim(IBodyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch);

	/**
	 * render drop body part.
	 * {link @ZombieBodyRender}
	 */
	void renderBody(IBodyEntity entity, MatrixStack stack, IVertexBuilder buffer, int packedLight, int packedOverlay);

	EntityModel<T> getZombieModel();
}
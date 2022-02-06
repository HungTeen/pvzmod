package com.hungteen.pvz.api.paz;

import com.hungteen.pvz.api.interfaces.IBodyEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

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

	@OnlyIn(Dist.CLIENT)
	EntityModel<T> getZombieModel();
}

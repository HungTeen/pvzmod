package com.hungteen.pvz.client.model.entity.zombie;

import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.body.ZombieDropBodyEntity;
import com.hungteen.pvz.common.entity.zombie.body.ZombieDropBodyEntity.BodyType;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;

public interface IZombieModel<T extends PVZZombieEntity> {

	/**
	 * use for drop part entity to render.
	 * not for current entity.
	 * {link @ZombieBodyRender}
	 */
	void tickPartAnim(ZombieDropBodyEntity entity, BodyType type, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch);

	/**
	 * render drop body part.
	 * {link @ZombieBodyRender}
	 */
	void renderBody(ZombieDropBodyEntity entity, MatrixStack stack, IVertexBuilder buffer, int packedLight, int packedOverlay, BodyType type);

	EntityModel<T> getZombieModel();
}

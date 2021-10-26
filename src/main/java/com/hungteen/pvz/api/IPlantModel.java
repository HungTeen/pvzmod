package com.hungteen.pvz.api;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;

import net.minecraft.client.renderer.entity.model.EntityModel;

public interface IPlantModel<T extends PVZPlantEntity> {

//	/**
//	 * use for drop part entity to render.
//	 * not for current entity.
//	 * {link @ZombieBodyRender}
//	 */
//	void tickPartAnim(ZombieDropBodyEntity entity, BodyType type, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch);
//
//	/**
//	 * render drop body part.
//	 * {link @ZombieBodyRender}
//	 */
//	void renderBody(ZombieDropBodyEntity entity, MatrixStack stack, IVertexBuilder buffer, int packedLight, int packedOverlay, BodyType type);

	EntityModel<T> getPlantModel();
}

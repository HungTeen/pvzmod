package com.hungteen.pvz.client.model.baked;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;

public class BowlingGloveBakedModel extends PVZBakedModel {
	
    public BowlingGloveBakedModel(IBakedModel existingModel) {
       super(existingModel);
    }

	@Override
    public IBakedModel handlePerspective(ItemCameraTransforms.TransformType cameraTransformType, MatrixStack mat) {
        if (cameraTransformType == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) return this;
        return this.existingModel.handlePerspective(cameraTransformType, mat);
    }
}

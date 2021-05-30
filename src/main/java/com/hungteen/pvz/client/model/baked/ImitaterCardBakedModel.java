package com.hungteen.pvz.client.model.baked;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;

public class ImitaterCardBakedModel extends PVZBakedModel {
	
    public ImitaterCardBakedModel(IBakedModel existingModel) {
       super(existingModel);
    }

	@Override
    public IBakedModel handlePerspective(ItemCameraTransforms.TransformType cameraTransformType, MatrixStack mat) {
        if (cameraTransformType == ItemCameraTransforms.TransformType.GUI) return this;
        return this.existingModel.handlePerspective(cameraTransformType, mat);
    }
}

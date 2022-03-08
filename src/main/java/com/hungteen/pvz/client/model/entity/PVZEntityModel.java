package com.hungteen.pvz.client.model.entity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.world.entity.Entity;

public abstract class PVZEntityModel<T extends Entity> extends EntityModel<T>{

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
	
}

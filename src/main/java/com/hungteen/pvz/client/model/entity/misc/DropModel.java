package com.hungteen.pvz.client.model.entity.misc;

import com.hungteen.pvz.common.entity.misc.drop.DropEntity;
import com.hungteen.pvz.common.entity.misc.drop.SunEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class DropModel<T extends DropEntity> extends EntityModel<T> {
	private final ModelRenderer body;

	public DropModel() {
		texWidth = 128;
		texHeight = 128;

		body = new ModelRenderer(this);
		body.setPos(0.0F, -8.0F, 0.0F);
		body.texOffs(0, 0).addBox(-32.0F, -32.0F, 0.0F, 64.0F, 64.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if(entity instanceof SunEntity) {
			this.body.zRot = ageInTicks / 50;
		} else {
			this.body.zRot = 0;
		}
		this.body.xRot = 0;
		this.body.yRot = 0;
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
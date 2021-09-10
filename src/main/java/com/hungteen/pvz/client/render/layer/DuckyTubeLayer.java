package com.hungteen.pvz.client.render.layer;

import com.hungteen.pvz.client.model.entity.zombie.pool.DuckyTubeModel;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;

public class DuckyTubeLayer<T extends MobEntity> extends LayerRenderer<T, EntityModel<T>>{

	public static final ResourceLocation TEXTURE = StringUtil.prefix("textures/entity/zombie/pool/ducky_tube.png");
	private DuckyTubeModel model = new DuckyTubeModel();
	
	public DuckyTubeLayer(IEntityRenderer<T, EntityModel<T>> entityRendererIn) {
		super(entityRendererIn);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn,
			T zombie, float limbSwing, float limbSwingAmount, float partialTicks,
			float ageInTicks, float netHeadYaw, float headPitch) {
		if(! zombie.isInWater() || zombie.isInvisible()) {
			return ;
		}
		matrixStackIn.pushPose();
		IVertexBuilder builder = bufferIn.getBuffer(RenderType.entitySolid(TEXTURE));
		matrixStackIn.translate(0, -1.3f, 0);
		this.model.render(matrixStackIn, builder, packedLightIn, OverlayTexture.NO_OVERLAY);
		matrixStackIn.popPose();
	}
	
}

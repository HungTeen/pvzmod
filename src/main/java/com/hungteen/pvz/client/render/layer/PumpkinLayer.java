package com.hungteen.pvz.client.render.layer;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.model.entity.plant.defence.PumpkinModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.impl.plant.PVZPlants;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class PumpkinLayer<T extends PVZPlantEntity> extends LayerRenderer<T, EntityModel<T>>{
	
	private PumpkinModel model = new PumpkinModel();
	private PVZPlantRender<T> plantRender;
	
	public PumpkinLayer(IEntityRenderer<T, EntityModel<T>> entityRendererIn) {
		super(entityRendererIn);
		if(entityRendererIn instanceof PVZPlantRender) {
			this.plantRender = (PVZPlantRender<T>) entityRendererIn;
		}
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn,
			T plant, float limbSwing, float limbSwingAmount, float partialTicks,
			float ageInTicks, float netHeadYaw, float headPitch) {
		if(plant.getPumpkinLife() == 0 || plant.isInvisible()) {
			return ;
		}
		matrixStackIn.pushPose();
		IVertexBuilder builder = bufferIn.getBuffer(RenderType.entitySolid(this.getRenderTexture(plant)));
		if(this.plantRender != null) {
			float scale = 0.6f;
			float plantScale = this.plantRender.getScaleByEntity(plant);
		    matrixStackIn.scale(scale / plantScale, scale / plantScale, scale / plantScale);
		    double offsetH = 1.501D;
		    matrixStackIn.translate(0, plantScale / scale * offsetH - offsetH, 0);
		} else {
			PVZMod.LOGGER.debug("pumpkin render wrong !");
		}
		this.model.render(matrixStackIn, builder, packedLightIn, OverlayTexture.NO_OVERLAY);
		matrixStackIn.popPose();
	}
	
	protected ResourceLocation getRenderTexture(T plant) {
		return PVZPlants.PUMPKIN.getRenderResource();
	}
	
}

package com.hungteen.pvz.render.entity.misc;

import com.hungteen.pvz.entity.misc.GardenRakeEntity;
import com.hungteen.pvz.model.entity.misc.GardenRakeModel;
import com.hungteen.pvz.render.entity.PVZEntityRender;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GardenRakeRender extends PVZEntityRender<GardenRakeEntity> {

	public GardenRakeRender(EntityRendererManager renderManager) {
		super(renderManager, new GardenRakeModel());
	}

	@Override
	public void render(GardenRakeEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.push();
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F - entityYaw));
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.pop();
	}
	
	@Override
	protected float getScaleByEntity(GardenRakeEntity entity) {
		return 0.9F;
	}

	@Override
	public ResourceLocation getEntityTexture(GardenRakeEntity entity) {
		return StringUtil.prefix("textures/entity/misc/garden_rake.png");
	}

}

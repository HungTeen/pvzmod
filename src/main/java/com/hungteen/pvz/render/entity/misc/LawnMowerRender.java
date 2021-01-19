package com.hungteen.pvz.render.entity.misc;

import com.hungteen.pvz.entity.misc.LawnMowerEntity;
import com.hungteen.pvz.model.entity.misc.LawnMowerModel;
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
public class LawnMowerRender extends PVZEntityRender<LawnMowerEntity> {

	public LawnMowerRender(EntityRendererManager renderManager) {
		super(renderManager, new LawnMowerModel());
	}

	@Override
	public void render(LawnMowerEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.push();
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F - entityYaw));
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
        matrixStackIn.pop();
	}
	
	@Override
	protected float getScaleByEntity(LawnMowerEntity entity) {
		return 0.9F;
	}

	@Override
	public ResourceLocation getEntityTexture(LawnMowerEntity entity) {
		return StringUtil.prefix("textures/entity/misc/lawn_mower.png");
	}

}

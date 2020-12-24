package com.hungteen.pvz.render.entity.bullet;

import com.hungteen.pvz.entity.bullet.StarEntity;
import com.hungteen.pvz.entity.bullet.StarEntity.StarStates;
import com.hungteen.pvz.model.entity.bullet.StarModel;
import com.hungteen.pvz.render.entity.PVZEntityRender;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class StarRender extends PVZEntityRender<StarEntity> {

	public StarRender(EntityRendererManager renderManager) {
		super(renderManager, new StarModel());
	}

	@Override
	public void render(StarEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.push();
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) + 180.0F));
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.pop();
	}
	
	@Override
	protected float getRenderSize(StarEntity entity) {
		return 0.8F;
	}

	@Override
	public ResourceLocation getEntityTexture(StarEntity entity) {
		if(entity.getStarState() == StarStates.PINK) return StringUtil.prefix("textures/entity/misc/pink_star.png");
		return StringUtil.prefix("textures/entity/misc/star.png");
	}

}

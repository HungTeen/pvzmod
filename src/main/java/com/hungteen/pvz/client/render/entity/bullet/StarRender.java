package com.hungteen.pvz.client.render.entity.bullet;

import com.hungteen.pvz.client.model.entity.bullet.StarModel;
import com.hungteen.pvz.client.render.entity.PVZEntityRender;
import com.hungteen.pvz.common.entity.bullet.StarEntity;
import com.hungteen.pvz.common.entity.bullet.StarEntity.StarStates;
import com.hungteen.pvz.common.entity.bullet.StarEntity.StarTypes;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StarRender extends PVZEntityRender<StarEntity> {

	private static final ResourceLocation RES1 = StringUtil.prefix("textures/entity/layer/sun_light.png");
	private static final ResourceLocation RES2 = StringUtil.prefix("textures/entity/layer/heal_light.png");
	
	public StarRender(EntityRendererManager renderManager) {
		super(renderManager, new StarModel());
	}

	@Override
	public void render(StarEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.pushPose();
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.yRotO, entityIn.yRot) + 180.0F));
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.scale(-1, -1, 1);
		float f = getScaleByEntity(entityIn);
		matrixStackIn.scale(f, f, f);
		matrixStackIn.translate(0.0, -1.501, 0.0);
		ResourceLocation res = (entityIn.getStarState() == StarStates.PINK ? RES2 : RES1);
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.energySwirl(res, 0, 0));
		this.model.setupAnim(entityIn, 0, 0, entityIn.tickCount + partialTicks, 0, 0);
		this.model.renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 0.5F, 0.5F, 0.5F, 1.0F);
		matrixStackIn.popPose();
	}
	
	@Override
	protected float getScaleByEntity(StarEntity entity) {
		if(entity.getStarType() == StarTypes.BIG) return 1.5F;
		if(entity.getStarType() == StarTypes.HUGE) return 2F;
		return 0.8F;
	}

	@Override
	public ResourceLocation getTextureLocation(StarEntity entity) {
		if(entity.getStarState() == StarStates.PINK) return StringUtil.prefix("textures/entity/misc/pink_star.png");
		return StringUtil.prefix("textures/entity/misc/star.png");
	}

}

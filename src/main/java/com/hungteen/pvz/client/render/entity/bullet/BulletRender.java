package com.hungteen.pvz.client.render.entity.bullet;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("deprecation")
@OnlyIn(Dist.CLIENT)
public abstract class BulletRender<T extends Entity & IRendersAsItem> extends EntityRenderer<T> {
	
	private final ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
	private final boolean fullBright;

	public BulletRender(EntityRendererManager p_i226035_1_, boolean p_i226035_4_) {
		super(p_i226035_1_);
		this.fullBright = p_i226035_4_;
	}

	public BulletRender(EntityRendererManager renderManagerIn) {
		this(renderManagerIn, false);
	}

	@Override
	protected int getBlockLightLevel(T entityIn, BlockPos pos) {
		return this.fullBright ? 15 : super.getBlockLightLevel(entityIn, pos);
	}

	public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.pushPose();
		float scale = getScaleByEntity(entityIn);
		matrixStackIn.scale(scale, scale, scale);
		matrixStackIn.mulPose(this.entityRenderDispatcher.cameraOrientation());
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F));
		this.itemRenderer.renderStatic(((IRendersAsItem) entityIn).getItem(), ItemCameraTransforms.TransformType.GROUND,
				packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
		matrixStackIn.popPose();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	protected abstract float getScaleByEntity(T entity);

	/**
	 * Returns the location of an entity's texture.
	 */
	public ResourceLocation getTextureLocation(Entity entity) {
		return AtlasTexture.LOCATION_BLOCKS;
	}
}

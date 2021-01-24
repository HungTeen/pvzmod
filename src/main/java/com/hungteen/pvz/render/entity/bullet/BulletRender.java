package com.hungteen.pvz.render.entity.bullet;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("deprecation")
@OnlyIn(Dist.CLIENT)
public abstract class BulletRender<T extends Entity & IRendersAsItem> extends EntityRenderer<T> {
	private final ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
	private final boolean field_229126_f_;

	public BulletRender(EntityRendererManager p_i226035_1_, boolean p_i226035_4_) {
		super(p_i226035_1_);
		this.field_229126_f_ = p_i226035_4_;
	}

	public BulletRender(EntityRendererManager renderManagerIn) {
		this(renderManagerIn, false);
	}

	protected int getBlockLight(T entityIn, float partialTicks) {
		return this.field_229126_f_ ? 15 : super.getBlockLight(entityIn, partialTicks);
	}

	public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.push();
		float scale = getScaleByEntity(entityIn);
		matrixStackIn.scale(scale, scale, scale);
		matrixStackIn.rotate(this.renderManager.getCameraOrientation());
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F));
		this.itemRenderer.renderItem(((IRendersAsItem) entityIn).getItem(), ItemCameraTransforms.TransformType.GROUND,
				packedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);
		matrixStackIn.pop();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	protected abstract float getScaleByEntity(T entity);

	/**
	 * Returns the location of an entity's texture.
	 */
	public ResourceLocation getEntityTexture(Entity entity) {
		return AtlasTexture.LOCATION_BLOCKS_TEXTURE;
	}
}

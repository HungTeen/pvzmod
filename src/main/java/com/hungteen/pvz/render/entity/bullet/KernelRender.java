package com.hungteen.pvz.render.entity.bullet;

import com.hungteen.pvz.entity.bullet.KernelEntity;
import com.hungteen.pvz.model.entity.bullet.KernelModel;
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
public class KernelRender extends PVZEntityRender<KernelEntity> {

	private static final ResourceLocation TEX = StringUtil.prefix("textures/entity/misc/kernel.png");
	
	public KernelRender(EntityRendererManager renderManager) {
		super(renderManager, new KernelModel());
	}

	@Override
	public void render(KernelEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		matrixStackIn.push();
		matrixStackIn.rotate(Vector3f.XP.rotationDegrees(entityIn.ticksExisted * 15));
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.pop();
	}

	@Override
	protected float getScaleByEntity(KernelEntity entity) {
		return 1F;
	}

	@Override
	public ResourceLocation getEntityTexture(KernelEntity entity) {
		return TEX;
	}

}

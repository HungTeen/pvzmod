package com.hungteen.pvz.render.entity.bullet;

import com.hungteen.pvz.entity.bullet.ThornEntity;
import com.hungteen.pvz.entity.bullet.ThornEntity.ThornStates;
import com.hungteen.pvz.model.entity.bullet.ThornModel;
import com.hungteen.pvz.render.entity.PVZEntityRender;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class ThornRender extends PVZEntityRender<ThornEntity> {

	public ThornRender(EntityRendererManager renderManager) {
		super(renderManager, new ThornModel());
	}

	@Override
	public void render(ThornEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
//		System.out.println(entityIn.rotationYaw);
		matrixStackIn.push();
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) + 180.0F));
		matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch)));
//		matrixStackIn.rotate(Vector3f.XP.rotationDegrees(45.0F));
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.pop();
	}

	@Override
	protected float getRenderSize(ThornEntity entity) {
		if(entity.getThornState() == ThornStates.POWER) return 2.5F;
		return 1F;
	}

	@Override
	public ResourceLocation getEntityTexture(ThornEntity entity) {
		return StringUtil.prefix("textures/entity/misc/thorn.png");
	}

}

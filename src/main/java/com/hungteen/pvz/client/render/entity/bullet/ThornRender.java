package com.hungteen.pvz.client.render.entity.bullet;

import com.hungteen.pvz.client.model.entity.bullet.ThornModel;
import com.hungteen.pvz.client.render.entity.PVZEntityRender;
import com.hungteen.pvz.common.entity.bullet.ThornEntity;
import com.hungteen.pvz.common.entity.bullet.ThornEntity.ThornStates;
import com.hungteen.pvz.common.entity.bullet.ThornEntity.ThornTypes;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ThornRender extends PVZEntityRender<ThornEntity> {

	public ThornRender(EntityRendererManager renderManager) {
		super(renderManager, new ThornModel());
	}

	@Override
	public void render(ThornEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
//		System.out.println(entityIn.rotationYaw);
		matrixStackIn.pushPose();
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.yRotO, entityIn.yRot) + 180.0F));
		matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.xRotO, entityIn.xRot)));
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		matrixStackIn.popPose();
	}

	@Override
	protected float getScaleByEntity(ThornEntity entity) {
		if(entity.getThornType() == ThornTypes.AUTO) return 2.5F;
		return 1.2F;
	}

	@Override
	public ResourceLocation getTextureLocation(ThornEntity entity) {
		if(entity.getThornState() == ThornStates.POWER) return StringUtil.prefix("textures/entity/misc/power_thorn.png");
		return StringUtil.prefix("textures/entity/misc/thorn.png");
	}

}

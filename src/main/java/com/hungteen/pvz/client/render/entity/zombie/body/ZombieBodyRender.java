package com.hungteen.pvz.client.render.entity.zombie.body;

import com.hungteen.pvz.client.model.entity.zombie.ZombieModelHandler;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.client.render.entity.zombie.ZombieRenderHandler;
import com.hungteen.pvz.common.entity.zombie.body.ZombieDropBodyEntity;
import com.hungteen.pvz.common.entity.zombie.body.ZombieDropBodyEntity.BodyType;
import com.hungteen.pvz.remove.Zombies;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class ZombieBodyRender extends EntityRenderer<ZombieDropBodyEntity> {

	public ZombieBodyRender(EntityRendererManager p_i46179_1_) {
		super(p_i46179_1_);
	}
	
	@Override
	public void render(ZombieDropBodyEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		Zombies zombie = entityIn.getZombieType();
		BodyType part = entityIn.getBodyType(); 
		matrixStackIn.pushPose();
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.yRotO, entityIn.yRot) + 180.0F));
		if(part != BodyType.BODY) {
		    if(entityIn.isOnGround()) {
			    matrixStackIn.mulPose(Vector3f.XN.rotationDegrees(part == BodyType.HEAD ? entityIn.HEAD_ROT : 90));
		    } else {
			    matrixStackIn.mulPose(Vector3f.XN.rotationDegrees(- entityIn.tickCount * 15));
		    }
		}
		matrixStackIn.scale(-1, -1, 1);
	    final float sz = entityIn.isMini() ? ZombieRenderHandler.getZombieScale(zombie) * PVZZombieRender.MINI_SCALE : ZombieRenderHandler.getZombieScale(zombie) ;
	    matrixStackIn.scale(sz, sz, sz); 
		matrixStackIn.translate(0.0, (part != BodyType.BODY ? -1.501 : -1.7), 0.0);
		ZombieRenderHandler.getZombieTex(zombie).ifPresent(tex -> {
			if(part != BodyType.BODY) {
			    ZombieModelHandler.getPart1Model(zombie).ifPresent(m -> {
			        final IVertexBuilder ivertexbuilder = bufferIn.getBuffer(m.getZombieModel().renderType(tex));
			        m.tickPartAnim(entityIn, part, 0, 0, entityIn.tickCount + partialTicks, 0, 0);
			        m.renderBody(entityIn, matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, part);
			    });
			} else {
				ZombieModelHandler.getPart2Model(zombie).ifPresent(m -> {
			        final IVertexBuilder ivertexbuilder = bufferIn.getBuffer(m.getZombieModel().renderType(tex));
			        m.tickPartAnim(entityIn, part, 0, 0, entityIn.tickCount + partialTicks, 0, 0);
			        m.renderBody(entityIn, matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, part);
			    });
			}
		});
		matrixStackIn.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(ZombieDropBodyEntity p_110775_1_) {
		return null;//no use
	}
 
}

package com.hungteen.pvz.client.render.entity.zombie;

import com.hungteen.pvz.api.types.base.IPAZType;
import com.hungteen.pvz.common.entity.zombie.drop.ZombieDropPart;
import com.hungteen.pvz.common.impl.type.zombie.PVZZombies;
import com.hungteen.pvz.utils.enums.DropPartTypes;
import com.hungteen.pvz.utils.interfaces.IDropPartModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-05 09:11
 **/
public class ZombieDropPartRender extends EntityRenderer<ZombieDropPart> {

    private static final Map<IPAZType, EntityRenderer<?>> RENDER = new HashMap<>();
    private static final Set<IPAZType> SET = new HashSet<>();
    protected EntityRenderer<?> entityRenderer;

    public ZombieDropPartRender(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(ZombieDropPart dropEntity, float yaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferSource, int packedLightIn) {
        if(this.canRender(dropEntity)){
            this.maintainMap(dropEntity);

            if(this.entityRenderer instanceof PVZZombieRender){
            	
                final ResourceLocation resource = dropEntity.getOwnerType().getDefaultResource();
                matrixStackIn.pushPose();
                matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTicks, dropEntity.yRotO, dropEntity.getYRot()) + 180.0F));
                if(! dropEntity.getDropPartType().equals(DropPartTypes.WHOLE_BODY)) {
                    if(dropEntity.isOnGround()) {
                        matrixStackIn.mulPose(Vector3f.XN.rotationDegrees(dropEntity.getDropPartType().equals(DropPartTypes.HEAD) ? dropEntity.headRotation : 90));
                    } else {
                        matrixStackIn.mulPose(Vector3f.XN.rotationDegrees(- dropEntity.tickCount * 15));
                    }
                }
                matrixStackIn.scale(-1, -1, 1);
                final float sz = dropEntity.isMini() ? PVZZombieRender.MINI_SCALE : 1F;
                matrixStackIn.scale(sz, sz, sz);
                matrixStackIn.translate(0.0, (! dropEntity.getDropPartType().equals(DropPartTypes.WHOLE_BODY) ? -1.501 : -1.7), 0.0);
                if(! dropEntity.getDropPartType().equals(DropPartTypes.WHOLE_BODY)) {
                    IDropPartModel dropPartModel = ((PVZZombieRender) this.entityRenderer).dropPartModel;
                    final VertexConsumer buffer = bufferSource.getBuffer(dropPartModel.getModel().renderType(resource));
                    dropPartModel.tickPartAnim(dropEntity, 0, 0, dropEntity.tickCount + partialTicks, 0, 0);
                    dropPartModel.renderBody(dropEntity, matrixStackIn, buffer, packedLightIn, OverlayTexture.NO_OVERLAY);
                } else {
                    IDropPartModel dropBodyModel = ((PVZZombieRender) this.entityRenderer).dropBodyModel;
                    final VertexConsumer buffer = bufferSource.getBuffer(dropBodyModel.getModel().renderType(resource));
                    dropBodyModel.tickPartAnim(dropEntity, 0, 0, dropEntity.tickCount + partialTicks, 0, 0);
                    dropBodyModel.renderBody(dropEntity, matrixStackIn, buffer, packedLightIn, OverlayTexture.NO_OVERLAY);
                }
                matrixStackIn.popPose();
            }
        }
        super.render(dropEntity, yaw, partialTicks, matrixStackIn, bufferSource, packedLightIn);
    }

    protected boolean canRender(ZombieDropPart dropPart){
//    	dropPart.setDropPartType(DropPartTypes.HEAD);
//    	dropPart.setOwnerType(PVZZombies.NORMAL_ZOMBIE);
//    	if(dropPart.getExistTime() % 10 == 0) System.out.println(dropPart.getDropPartType());
        return dropPart.getOwnerType() != null && dropPart.getOwnerType().getEntityType().isPresent() && ! SET.contains(dropPart);
    }

    protected void maintainMap(ZombieDropPart dropPart){
        if(this.entityRenderer != null){
            return;
        }
        if(RENDER.containsKey(dropPart.getOwnerType())){
            entityRenderer = RENDER.get(dropPart.getOwnerType());
            return;
        }
        final Entity entity = dropPart.getOwnerType().getEntityType().get().create(Minecraft.getInstance().level);
        this.entityRenderer = this.entityRenderDispatcher.getRenderer(entity);
        entity.discard();
        if(this.entityRenderer == null){
            SET.add(dropPart.getOwnerType());
        } else{
            RENDER.put(dropPart.getOwnerType(), this.entityRenderer);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(ZombieDropPart p_114482_) {
        return null;//no use.
    }
}

package com.hungteen.pvz.client.render.entity;

import com.hungteen.pvz.utils.AnimationUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3d;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Mob;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-28 18:15
 **/
public abstract class PVZMobRender <T extends Mob> extends MobRenderer<T, EntityModel<T>> {

    private static final int BREATH_ANIM_CD = 40;

    public PVZMobRender(EntityRendererProvider.Context rendererManager, EntityModel<T> entityModelIn, float shadowSizeIn) {
        super(rendererManager, entityModelIn, shadowSizeIn);
    }

    @Override
    protected void scale(T entity, PoseStack matrixStackIn, float partialTickTime) {
        final float sz = getScaleByEntity(entity);
        final Vector3d vec = getTranslateVec(entity);
        matrixStackIn.scale(sz, sz, sz);
        matrixStackIn.translate(vec.x, vec.y, vec.z);
    }

    public float getScaleByEntity(T entity) {
        return entity.getScale();
    }

    public Vector3d getTranslateVec(T entity) {
        return new Vector3d(0, 0, 0);
    }

}
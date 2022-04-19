package com.hungteen.pvz.utils.interfaces;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-05 08:23
 **/
public interface IDropPartModel<T extends LivingEntity> {

    EntityModel<T> getModel();

    /**
     * use for drop part entity to render.
     * not for current entity.
     * {link @ZombieBodyRender}
     */
    void tickPartAnim(IEntityDropPart entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch);

    /**
     * render drop body part.
     * {link @ZombieBodyRender}
     */
    void renderBody(IEntityDropPart entity, PoseStack stack, VertexConsumer buffer, int packedLight, int packedOverlay);

    ModelPart getPart(String part);

    default ModelPart getHead(){
        return getPart("head");
    }

    default ModelPart getLeftHand(){
        return getPart("left_hand");
    }

    default ModelPart getRightHand(){
        return getPart("right_hand");
    }

    default ModelPart getLeftLeg(){
        return getPart("left_leg");
    }

    default ModelPart getRightLeg(){
        return getPart("right_leg");
    }
}

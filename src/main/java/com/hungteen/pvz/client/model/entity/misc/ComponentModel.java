package com.hungteen.pvz.client.model.entity.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-01 19:45
 **/
public abstract class ComponentModel<T extends Entity> extends EntityModel<T> {

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
    }

    public void render(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay){
        this.getTotalModel().render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public abstract ModelPart getTotalModel();

}

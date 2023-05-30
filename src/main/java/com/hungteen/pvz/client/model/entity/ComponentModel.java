package com.hungteen.pvz.client.model.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * @program: pvzmod-1.16.5
 * @author: HungTeen
 * @create: 2022-03-02 17:16
 **/
public abstract class ComponentModel<T extends Entity> extends PVZEntityModel<T>{


    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
    }

    public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay){
        this.getTotalModel().render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public abstract ModelRenderer getTotalModel();

}

package com.hungteen.pvz.client.render.entity.layer;

import com.hungteen.pvz.client.model.entity.creature.GrassCarpModel;
import com.hungteen.pvz.common.entity.creature.GrassCarp;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.item.ItemStack;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-28 23:32
 **/
public class GrassCarpItemLayer<T extends GrassCarp> extends RenderLayer<T, GrassCarpModel<T>> {

    public GrassCarpItemLayer(RenderLayerParent<T, GrassCarpModel<T>> p_116884_) {
        super(p_116884_);
    }

    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int p_116899_, T grassCarp, float p_116901_, float p_116902_, float p_116903_, float p_116904_, float p_116905_, float p_116906_) {
        poseStack.pushPose();
        poseStack.translate(0, 0.75, 0);

        ItemStack itemstack = grassCarp.getMainHandItem();
        Minecraft.getInstance().getItemInHandRenderer().renderItem(grassCarp, itemstack, ItemTransforms.TransformType.GROUND, false, poseStack, bufferSource, p_116899_);
        poseStack.popPose();
    }
}

package com.hungteen.pvz.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 18:04
 **/
public class RenderUtil {

    public static void setTexture(ResourceLocation texture){
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, texture);
    }

    public static void drawScaledString(PoseStack stack, Font render, String string, int x, int y, int color, float scale) {
        stack.pushPose();
        stack.scale(scale, scale, scale);
        render.draw(stack, string, x / scale, y / scale, color);
        stack.popPose();
    }

    public static void drawCenteredString(PoseStack stack, Font render, String string, int x, int y, int color) {
        final int width = render.width(string);
        render.draw(stack, string, x - width / 2, y, color);
    }

    public static void drawCenteredScaledString(PoseStack stack, Font render, String string, int x, int y, int color,
                                                float scale) {
        int width = render.width(string);
        stack.pushPose();
        stack.scale(scale, scale, scale);
        render.draw(stack, string, (x - width / 2 * scale) / scale, y / scale, color);
        stack.popPose();
    }

}

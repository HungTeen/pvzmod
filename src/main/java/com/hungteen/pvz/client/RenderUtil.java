package com.hungteen.pvz.client;

import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.Util;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-10 18:04
 **/
public class RenderUtil {

    /**
     * Render Entity on GUI.
     */
    public static void renderLivingOnGUI(LivingEntity entity, int posX, int posY, float scale, float mouseX, float mouseY, double xRot, double yRot, double zRot) {
        final float followRotX = (float) Math.atan((posX - mouseX) / 5.0F) * 1.5F;
        final float followRotY = - (float) Math.atan((posY - mouseY) / 40.0F);

        RenderSystem.applyModelViewMatrix();
        final PoseStack stack = new PoseStack();
        stack.translate(posX, posY, 120.0D);
        stack.scale(scale, scale, scale);

        final Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
        Quaternion quaternion1 = Vector3f.XP.rotationDegrees(followRotY * 20.0F);
        quaternion.mul(quaternion1);
        stack.mulPose(quaternion);

        stack.mulPose(Vector3f.XP.rotationDegrees((float) xRot));
        stack.mulPose(Vector3f.YP.rotationDegrees((float) yRot));
        stack.mulPose(Vector3f.ZP.rotationDegrees((float) zRot));

        //storage rotation.
        final float f1 = entity.getYRot();
        final float f2 = entity.getXRot();
        final float f3 = entity.yBodyRot;
        final float f4 = entity.yBodyRotO;
        final float f5 = entity.yHeadRot;
        final float f6 = entity.yHeadRotO;

        final float yaw = - followRotX * 20.0F + (float) yRot;
        entity.setYRot(yaw);
        entity.setXRot(followRotY * 20.0F);
        entity.yBodyRot = yaw;
        entity.yBodyRotO = yaw;
        entity.yHeadRot = yaw;
        entity.yHeadRotO = yaw;

        quaternion1 = Vector3f.XP.rotationDegrees(- followRotY * 20.0F);
        quaternion.mul(quaternion1);

        Lighting.setupForEntityInInventory();
        EntityRenderDispatcher dispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
        quaternion1.conj();

        dispatcher.overrideCameraOrientation(quaternion1);
        dispatcher.setRenderShadow(false);
        MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
        RenderSystem.runAsFancy(() -> {
            dispatcher.render(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, stack, bufferSource, 15728880);
        });
        bufferSource.endBatch();
        dispatcher.setRenderShadow(false);
        dispatcher.setRenderHitBoxes(false);

        //restore rotation.
        entity.setYRot(f1);
        entity.setXRot(f2);
        entity.yBodyRot = f3;
        entity.yBodyRotO = f4;
        entity.yHeadRot = f5;
        entity.yHeadRotO = f6;

        RenderSystem.applyModelViewMatrix();
        Lighting.setupFor3DItems();
    }

    public static void renderScrollBar(Screen screen, PoseStack stack, int leftPos, int topPos, int currentPos, int totalCount, int countPerPage, int barLen){
        RenderSystem.setShaderTexture(0, Util.WIDGETS);
        stack.pushPose();
        if(totalCount > countPerPage){
            final int len = MathUtil.getBarLen(currentPos, totalCount - countPerPage, barLen - 15);
            screen.blit(stack, leftPos, topPos + len, 15, 0, 12, 15);
        } else{
            screen.blit(stack, leftPos, topPos, 27, 0, 12, 15);
        }
        stack.popPose();
    }

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

    public static float getSpin(int tick, float partial) {
        return (tick + partial) / 20.0F;
    }

}

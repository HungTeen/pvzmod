package com.hungteen.pvz.client.gui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-22 12:29
 **/
public abstract class PVZButton extends Button {

    protected final ResourceLocation widgets;

    public PVZButton(ResourceLocation location, int x, int y, int width, int height, Button.OnPress onPress) {
        this(location, x, y, width, height, "", onPress);
    }

    public PVZButton(ResourceLocation location, int x, int y, int width, int height, String text, Button.OnPress onPress) {
        super(x, y, width, height, new TextComponent(text), onPress);
        this.widgets = location;
    }

    public boolean isHovered(){
        return this.isHovered;
    }

    @Deprecated
    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partial) {
        if (this.visible) {
            this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, this.widgets);
            final Pair<Integer,Integer> xy = this.getButtonUV();
            final Pair<Integer,Integer> xyOffset = this.getButtonUVOffset();
            if(this.isHovered){
                this.blit(stack, this.x, this.y, xy.getFirst() + xyOffset.getFirst(), xy.getSecond() + xyOffset.getSecond(), width, height);
            } else{
                this.blit(stack, this.x, this.y, xy.getFirst(), xy.getSecond(), width, height);
            }
        }
    }

    protected abstract Pair<Integer,Integer> getButtonUV();

    protected abstract Pair<Integer,Integer> getButtonUVOffset();

}

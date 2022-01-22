package com.hungteen.pvz.client.gui.widget;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

public abstract class PVZButton extends Button{

	protected final ResourceLocation WIDGETS;
	
	public PVZButton(ResourceLocation location, int x, int y, int width, int height, IPressable onPress) {
		this(location, x, y, width, height, "", onPress);
	}
	
	public PVZButton(ResourceLocation location, int x, int y, int width, int height, String text, IPressable onPress) {
		super(x, y, width, height, new StringTextComponent(text), onPress);
		this.WIDGETS = location;
	}

	@Deprecated
	@Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partial) {
        if (this.visible) {
        	this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft.getInstance().getTextureManager().bind(this.WIDGETS);
            final Pair<Integer,Integer> xy = this.getButtonUV();
			final Pair<Integer,Integer> xyOffset = this.getButtonUVOffset();
			if(this.isHovered()){
				this.blit(stack, this.x, this.y, xy.getFirst() + xyOffset.getFirst(), xy.getSecond() + xyOffset.getSecond(), width, height);
			} else{
				this.blit(stack, this.x, this.y, xy.getFirst(), xy.getSecond(), width, height);
			}
        }
    }
	
	protected abstract Pair<Integer,Integer> getButtonUV();

	protected abstract Pair<Integer,Integer> getButtonUVOffset();
}

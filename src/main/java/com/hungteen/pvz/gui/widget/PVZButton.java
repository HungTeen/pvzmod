package com.hungteen.pvz.gui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;

public abstract class PVZButton extends Button{

	protected final ResourceLocation WIDGETS;
	protected final boolean right;
	
	public PVZButton(ResourceLocation location, int x, int y, int width, int height, boolean right, IPressable onPress) {
		this(location, x, y, width, height, "", right, onPress);
	}
	
	public PVZButton(ResourceLocation location, int x, int y, int width, int height, String text, boolean right, IPressable onPress) {
		super(x, y, width, height, text, onPress);
		this.right = right;
		this.WIDGETS = location;
	}

	@Override
    public void render(int mouseX, int mouseY, float partial) {
        if (this.visible) {
        	this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            Minecraft.getInstance().getTextureManager().bindTexture(this.WIDGETS);
            Pair<Integer,Integer> xy = this.getButtonUV();
            this.blit(this.x, this.y, xy.getFirst(), xy.getSecond(), width, height);
        }
    }
	
	protected abstract Pair<Integer,Integer> getButtonUV();
}

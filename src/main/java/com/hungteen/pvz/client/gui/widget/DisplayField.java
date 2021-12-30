package com.hungteen.pvz.client.gui.widget;

import java.util.List;

import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DisplayField {

	private final int x;
	private final int y;
	private final int texX;
	private final int texY;
	private final int width;
	private final int height;
	private final List<ITextComponent> components;
	
	public DisplayField(int x, int y, int texX, int texY, int width, int height, List<ITextComponent> text) {
		this.x = x;
		this.y = y;
		this.texX = texX;
		this.texY = texY;
		this.width = width;
		this.height = height;
		this.components = text;
	}
	
	public boolean isInField(int posX, int posY) {
		return posX >= this.x && posX <= this.x + this.width && posY >= this.y && posY <= this.y + this.height;
	}
	
	public List<ITextComponent> getTexts() {
		return this.components;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getTexX() {
		return this.texX;
	}
	
	public int getTexY() {
		return this.texY;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public static class TipField extends DisplayField {

		public TipField(int x, int y, List<ITextComponent> text) {
			super(x, y, 0, 0, 12, 12, text);
		}
		
	}
	
}

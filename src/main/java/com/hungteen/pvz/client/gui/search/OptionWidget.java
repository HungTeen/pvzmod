package com.hungteen.pvz.client.gui.search;

import com.hungteen.pvz.client.gui.screen.AbstractOptionScreen;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class OptionWidget extends Widget {

	private final AbstractOptionScreen<?> screen;
	private SearchOption option;

	public OptionWidget(AbstractOptionScreen<?> screen) {
		super(0, 0, 25, 25, StringUtil.EMPTY);
		this.screen = screen;
	}

	public void init(SearchOption a) {
		this.option = a;
	}

	public void renderButton(MatrixStack stack, int p_renderButton_1_, int p_renderButton_2_, float p_renderButton_3_) {
		stack.pushPose();
		Minecraft minecraft = Minecraft.getInstance();
		minecraft.getTextureManager().bind(OptionSearchGui.TEXTURE);
		int posX = this.isOptionUnLocked() ? 29 : 29 + 25;
		int posY = 206;
		this.blit(stack, this.x, this.y, posX, posY, this.width, this.height);
		minecraft.getItemRenderer().renderAndDecorateItem(SearchOption.getItemStackByOption(this.option), this.x + 4,
				this.y + 4);
		if(! this.isOptionUnLocked()){
			this.blit(stack, this.x + 8, this.y + 8, 81, 225, 7, 7);
		}
		stack.popPose();
	}
	
	private boolean isOptionUnLocked() {
		return this.screen.isOptionUnLocked(option);
	}

	public List<ITextComponent> getToolTipText(Screen screen) {
		List<ITextComponent> list = new ArrayList<>();
		list.add(option.getType().getText());
		return list;
	}

	public void setPosition(int xx, int yy) {
		this.x = xx;
		this.y = yy;
	}

	public SearchOption getSearchOption() {
		return this.option;
	}

}

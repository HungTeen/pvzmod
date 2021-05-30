package com.hungteen.pvz.client.gui.search;

import java.util.ArrayList;
import java.util.List;

import com.hungteen.pvz.client.gui.screen.AbstractOptionScreen;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

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
//		int posX = ClientPlayerResources.isSearchOptionUnLocked(this.option) ? 29 : 29 + 25;
		int posX = this.isOptionUnLocked() ? 29 : 29 + 25;
		int posY = 206;
		this.blit(stack, this.x, this.y, posX, posY, this.width, this.height);
		minecraft.getItemRenderer().renderAndDecorateItem(SearchOption.getItemStackByOption(this.option), this.x + 4,
				this.y + 4);
		stack.popPose();
	}
	
	private boolean isOptionUnLocked() {
		return this.screen.isOptionUnLocked(option);
	}

	public List<ITextComponent> getToolTipText(Screen p_191772_1_) {
		List<ITextComponent> list = new ArrayList<>();
		list.add(SearchOption.getOptionName(option));
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

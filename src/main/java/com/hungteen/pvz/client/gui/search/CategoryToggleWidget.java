package com.hungteen.pvz.client.gui.search;

import com.hungteen.pvz.client.ClientProxy;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.widget.ToggleWidget;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CategoryToggleWidget extends ToggleWidget {

	protected SearchCategories category;
	
	public CategoryToggleWidget(SearchCategories category) {
		super(0, 0, 35, 26, false);
		this.category = category;
		this.initTextureValues(153, 2, 35, 0, OptionSearchGui.TEXTURE);
	}

	public void renderButton(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		stack.pushPose();
		ClientProxy.MC.getTextureManager().bind(this.resourceLocation);
		RenderSystem.disableDepthTest();
		int posX = this.isStateTriggered ? this.x - 2 : this.x;
		int posY = this.y;
		int texX = this.isStateTriggered ? this.xTexStart + this.xDiffTex : this.xTexStart;
		int texY = this.isStateTriggered ? this.yTexStart + this.yDiffTex : this.yTexStart;
		this.blit(stack, posX, posY, texX, texY, this.width, this.height);
		RenderSystem.enableDepthTest();
		this.renderCategoryIcon(stack);
		stack.popPose();
	}

	private void renderCategoryIcon(MatrixStack stack) {
		final int posX = this.x + 9 + (this.isStateTriggered ? -2 : 0);
		final int posY = this.y + 5;
		if(this.category == SearchCategories.ALL){
			ClientProxy.MC.getItemRenderer().renderAndDecorateItem(new ItemStack(Items.COMPASS), posX, posY);
		} else if(this.category == SearchCategories.PLANTS) {
			this.blit(stack, posX, posY, 152, 32, 16, 16);
		} else if(this.category == SearchCategories.ZOMBIES){
			this.blit(stack, posX, posY, 168, 32, 16, 16);
		}
	}

	public SearchCategories getCategory() {
		return this.category;
	}

	public enum SearchCategories {

		ALL,
		PLANTS,
		ZOMBIES;

	}
}

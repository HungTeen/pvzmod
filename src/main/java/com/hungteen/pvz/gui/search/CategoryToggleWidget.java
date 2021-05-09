package com.hungteen.pvz.gui.search;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.ToggleWidget;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
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

	public void startAnimation(Minecraft mc) {
	}

	public void renderButton(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
		stack.pushPose();
		Minecraft minecraft = Minecraft.getInstance();
		minecraft.getTextureManager().bind(this.resourceLocation);
		RenderSystem.disableDepthTest();
		int posX = this.isStateTriggered ? this.x - 2 : this.x;
		int posY = this.y;
		int texX = this.isStateTriggered ? this.xTexStart + this.xDiffTex : this.xTexStart;
		int texY = this.isStateTriggered ? this.yTexStart + this.yDiffTex : this.yTexStart;
		this.blit(stack, posX, posY, texX, texY, this.width, this.height);
		RenderSystem.enableDepthTest();
		this.renderIcon(minecraft.getItemRenderer());
		stack.popPose();
	}

	private void renderIcon(ItemRenderer renderer) {
		int i = this.isStateTriggered ? -2 : 0;
		renderer.renderAndDecorateItem(getRenderItemStack(), this.x + 9 + i, this.y + 5);
	}
	
	private ItemStack getRenderItemStack() {
		return SearchCategories.getRenderItemStack(this.category);
	}

	public SearchCategories getCategory() {
		return this.category;
	}

//	public boolean updateVisibility(ClientRecipeBook p_199500_1_) {
//		List<RecipeList> list = p_199500_1_.getRecipes(this.category);
//		this.visible = false;
//		if (list != null) {
//			for (RecipeList recipelist : list) {
//				if (recipelist.isNotEmpty() && recipelist.containsValidRecipes()) {
//					this.visible = true;
//					break;
//				}
//			}
//		}
//
//		return this.visible;
//	}

}

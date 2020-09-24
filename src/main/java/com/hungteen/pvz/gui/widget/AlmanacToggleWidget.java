package com.hungteen.pvz.gui.widget;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.gui.AlmanacScreen;
import com.hungteen.pvz.register.ItemRegister;
import com.hungteen.pvz.utils.enums.Almanacs;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.ToggleWidget;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class AlmanacToggleWidget extends ToggleWidget {

	protected Almanacs.Categories category;
	private float animationTime;
	
	public AlmanacToggleWidget(Almanacs.Categories category) {
		super(0, 0, 35, 26, false);
		this.category = category;
		this.initTextureValues(153, 2, 35, 0, AlmanacScreen.TEXTURE);
	}

	public void startAnimation(Minecraft mc) {
//		ClientRecipeBook clientrecipebook = mc.player.getRecipeBook();
//		List<RecipeList> list = clientrecipebook.getRecipes(this.category);
		this.animationTime = 15.0F;
	}

	public void renderButton(int mouseX, int mouseY, float partialTicks) {
		if (this.animationTime > 0.0F) {
			float f = 1.0F + 0.1F * (float) Math.sin((double) (this.animationTime / 15.0F * (float) Math.PI));
			RenderSystem.pushMatrix();
			RenderSystem.translatef((float) (this.x + 8), (float) (this.y + 12), 0.0F);
			RenderSystem.scalef(1.0F, f, 1.0F);
			RenderSystem.translatef((float) (-(this.x + 8)), (float) (-(this.y + 12)), 0.0F);
		}

		Minecraft minecraft = Minecraft.getInstance();
		minecraft.getTextureManager().bindTexture(this.resourceLocation);
		RenderSystem.disableDepthTest();
		int i = this.xTexStart;
		int j = this.yTexStart;
		if (this.stateTriggered) {
			i += this.xDiffTex;
		}

		if (this.isHovered()) {
			j += this.yDiffTex;
		}

		int k = this.x;
		if (this.stateTriggered) {
			k -= 2;
		}

		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.blit(k, this.y, i, j, this.width, this.height);
		RenderSystem.enableDepthTest();
		this.renderIcon(minecraft.getItemRenderer());
		if (this.animationTime > 0.0F) {
			RenderSystem.popMatrix();
			this.animationTime -= partialTicks;
		}

	}

	private void renderIcon(ItemRenderer renderer) {
		int i = this.stateTriggered ? -2 : 0;
		renderer.renderItemAndEffectIntoGUI(getRenderItemStack(), this.x + 9 + i, this.y + 5);
	}
	
	private ItemStack getRenderItemStack() {
		switch (this.category) {
		case ALL: return new ItemStack(Items.COMPASS);
		case PLANTS: return new ItemStack(ItemRegister.PEA_SHOOTER_CARD.get());
		case ZOMBIES: return new ItemStack(ItemRegister.BUCKET_HEAD.get());
		default:{
			PVZMod.LOGGER.debug("Category ERROR !");
			return ItemStack.EMPTY;
		}
		}
	}

	public Almanacs.Categories getCategory() {
		return this.category;
	}

//	public boolean func_199500_a(ClientRecipeBook p_199500_1_) {
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

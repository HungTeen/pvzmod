package com.hungteen.pvz.gui.search;

import java.util.List;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.datafixers.util.Pair;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RecipeManager {

	private final List<RecipeIngredient> ingredients = Lists.newArrayList();
	private float time;

	public void render(Minecraft mc, int left, int top, boolean p_194188_4_, float p_194188_5_) {
		RenderSystem.pushMatrix();
		if (! Screen.hasControlDown()) {
			this.time += p_194188_5_;
		}
		for (int i = 0; i < this.ingredients.size(); ++i) {
			RecipeIngredient ingredient = this.ingredients.get(i);
			int x = ingredient.getX() + left;
			int y = ingredient.getY() + top;
			AbstractGui.fill(x, y, x + 16, y + 16, 822018048);
			ItemStack itemstack = ingredient.getItem();
			ItemRenderer itemrenderer = mc.getItemRenderer();
			itemrenderer.renderItemAndEffectIntoGUI(mc.player, itemstack, x, y);
			RenderSystem.depthFunc(516);
			AbstractGui.fill(x, y, x + 16, y + 16, 822083583);
			RenderSystem.depthFunc(515);
			if (i == 0) {
				itemrenderer.renderItemOverlays(mc.fontRenderer, itemstack, x, y);
			}
		}
		RenderSystem.popMatrix();
	}

	public void clear() {
		this.ingredients.clear();
		this.time = 0.0F;
	}

	public void setRecipe(List<Pair<Ingredient, Slot>> list) {
		list.forEach((pair) -> {
			this.addIngredient(pair.getFirst(), pair.getSecond().xPos, pair.getSecond().yPos);
		});
	}
	
	public void addIngredient(Ingredient i, int x, int y) {
		this.ingredients.add(new RecipeIngredient(i, x, y));
	}

	public RecipeIngredient get(int pos) {
		return this.ingredients.get(pos);
	}

	public int size() {
		return this.ingredients.size();
	}

	@OnlyIn(Dist.CLIENT)
	public class RecipeIngredient {
		private final Ingredient ingredient;
		private final int x;
		private final int y;

		public RecipeIngredient(Ingredient p_i47604_2_, int x, int y) {
			this.ingredient = p_i47604_2_;
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return this.x;
		}

		public int getY() {
			return this.y;
		}

		public ItemStack getItem() {
			ItemStack[] aitemstack = this.ingredient.getMatchingStacks();
			return aitemstack[MathHelper.floor(RecipeManager.this.time / 30.0F) % aitemstack.length];
		}
	}
}

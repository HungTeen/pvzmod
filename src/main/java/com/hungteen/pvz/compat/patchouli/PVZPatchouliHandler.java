package com.hungteen.pvz.compat.patchouli;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import vazkii.patchouli.api.PatchouliAPI;

import java.util.Optional;

public class PVZPatchouliHandler {

	public static final String PATCHOULI = "patchouli";
	public static final ResourceLocation GUIDE_BOOK = prefix("guide_book");
	
	/**
	 */
	public static void giveInitialGuideBook(PlayerEntity player) {
		if(isPatchouliLoaded()) {
			Optional.ofNullable(ForgeRegistries.ITEMS.getValue(GUIDE_BOOK)).ifPresent(item -> {
				final ItemStack book = new ItemStack(item, 1);
				book.getOrCreateTag().putString("patchouli:book", "pvz:pvz_guide");
				player.addItem(book);
			});
		}
	}

	public static ItemStack getPatchouliGuide(){
		if(isPatchouliLoaded() && ForgeRegistries.ITEMS.containsKey(GUIDE_BOOK)) {
			final ItemStack book = new ItemStack(ForgeRegistries.ITEMS.getValue(GUIDE_BOOK), 1);
			book.getOrCreateTag().putString("patchouli:book", "pvz:pvz_guide");
			return book;
		}
		return ItemStack.EMPTY;
	}

	public static PatchouliAPI.IPatchouliAPI getPatchouliAPI(){
		return PatchouliAPI.instance;
	}
	
	public static ResourceLocation prefix(String res) {
		return new ResourceLocation(PATCHOULI, res);
	}
	
	public static boolean isPatchouliLoaded() {
		return ModList.get().isLoaded(PATCHOULI);
	}
}

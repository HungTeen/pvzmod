package com.hungteen.pvz.compat.patchouli;

import java.util.Optional;

import com.hungteen.pvz.common.event.PVZPlayerEvents;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

public class PVZPatchouliHandler {

	public static final String PATCHOULI = "patchouli";
	public static final ResourceLocation GUIDE_BOOK = prefix("guide_book");
	
	/**
	 * {@link PVZPlayerEvents#onPlayerGetAdvancement(net.minecraftforge.event.entity.player.AdvancementEvent)}
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
	
	public static ResourceLocation prefix(String res) {
		return new ResourceLocation(PATCHOULI, res);
	}
	
	public static boolean isPatchouliLoaded() {
		return ModList.get().isLoaded(PATCHOULI);
	}
}

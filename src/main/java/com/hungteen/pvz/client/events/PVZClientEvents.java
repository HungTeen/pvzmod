package com.hungteen.pvz.client.events;

import java.util.Arrays;
import java.util.HashSet;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.events.handler.PVZEntityRenderHandler;
import com.hungteen.pvz.client.gui.screen.PVZMainMenuScreen;
import com.hungteen.pvz.common.block.BlockRegister;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID, value = Dist.CLIENT)
public class PVZClientEvents {
	
	@SubscribeEvent 
	public static void onLivingRender(@SuppressWarnings("rawtypes") RenderLivingEvent.Pre ev) {
		final MatrixStack stack = ev.getMatrixStack();
		final IRenderTypeBuffer buffer = ev.getBuffers();
		final int light = ev.getLight();
//		ev.getMatrixStack().rotate(Vector3f.ZP.rotationDegrees(180F));
		PVZEntityRenderHandler.checkBungeeHandStand(ev.getEntity(), stack);
		PVZEntityRenderHandler.checkAndRenderFrozenIce(ev.getEntity(), stack, buffer, light);
		PVZEntityRenderHandler.checkAndRenderButter(ev.getRenderer(), ev.getEntity(), stack, buffer, light);
	}
	
	@SubscribeEvent
	public static void addToolTips(ItemTooltipEvent event) {
		HashSet<Item> set = new HashSet<>(Arrays.asList(
				BlockRegister.STEEL_LADDER.get().asItem(), BlockRegister.SUN_CONVERTER.get().asItem()
		));
		if(set.contains(event.getItemStack().getItem())) {
			event.getToolTip().add(new TranslationTextComponent("tooltip.pvz." + event.getItemStack().getItem().getRegistryName().getPath()).withStyle(TextFormatting.ITALIC));
		}
	}
	
	@SubscribeEvent
	public static void onGuiOpened(GuiOpenEvent event) {
		if(PVZConfig.CLIENT_CONFIG.OtherSettings.ShowPVZMainMenu.get()) {//show pvz menu.
		    if (event.getGui() instanceof MainMenuScreen && ! (event.getGui() instanceof PVZMainMenuScreen)) {
			    event.setGui(new PVZMainMenuScreen());
		    }
		}
	}
	
}

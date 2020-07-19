package com.hungteen.pvzmod.client.gui.render;

import com.hungteen.pvzmod.client.gui.mainwindow.PVZGuiTabPlayerData;
import com.hungteen.pvzmod.event.KeyBind;
import com.hungteen.pvzmod.util.ConfigurationUtil;
import com.hungteen.pvzmod.util.PlayerUtil;
import com.hungteen.pvzmod.util.Reference;
import com.hungteen.pvzmod.util.RenderUtil;
import com.hungteen.pvzmod.util.StringUtil;
import com.hungteen.pvzmod.util.enums.Enums;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ResourcesRenderer extends Gui {

	private final Minecraft mc;
	private static final ResourceLocation SUN_ENERGY = new ResourceLocation(Reference.MODID,
			"textures/gui/overlay/sun_energy.png");
	private final int tex_width = 89, tex_height = 28;

	public ResourcesRenderer() {
		mc = Minecraft.getMinecraft();
	}

//	@SubscribeEvent(priority = EventPriority.LOW)
//	public void onRenderTick(final TickEvent.RenderTickEvent ev) {
//		if (mc.currentScreen == null && !mc.gameSettings.hideGUI && !mc.player.isSpectator()) {
//			GlStateManager.pushMatrix();
//			GlStateManager.disableDepth();
//			GlStateManager.scale(0.5f, 0.5f, 0.5f);
//			GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
//			GlStateManager.enableAlpha();
//			final ScaledResolution res = new ScaledResolution(mc);
//			renderResources();
//			GlStateManager.disableAlpha();
//			GlStateManager.popMatrix();
//		}
//	}

//	private void renderResources(){
//		if (KeyBind.statusPlayerStats){
//			mc.getTextureManager().bindTexture(SUN_ENERGY);
//			int lvl=PVZGuiTabPlayerData.level;
//			int maxNum=PlayerUtil.getPlayerMaxEnergyNum(lvl);
//			int num=PVZGuiTabPlayerData.energyNum;
//			int percent=1;//(int) Math.floor(num*1.0f/maxNum);
//			System.out.println(mc.displayWidth);
//			drawTexturedModalRect(0, mc.displayHeight-tex_height-1, 0, 0, tex_width, tex_height);
//			drawTexturedModalRect(26, mc.displayHeight-tex_height-1+7, 0, 60, percent*60, 14);
//		}
//		else{
//			RenderUtil.drawCenteredScaledString(mc.fontRenderer, StringUtil.getLocaleStringWithArguments("gui.resources.showTip", KeyBind.keyPlayerStats.getDisplayName()),150,2, 1.5f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
//		}
//	}

	@SubscribeEvent
	public void onrenderSun(RenderGameOverlayEvent event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT && mc.currentScreen == null
				&& !mc.gameSettings.hideGUI && !mc.player.isSpectator()) {
			if (KeyBind.statusPlayerStats&&ConfigurationUtil.MainConfig.displaySettings.showSunNum) {
				mc.getTextureManager().bindTexture(SUN_ENERGY);
				// System.out.println(mc.displayHeight);
				int w = event.getResolution().getScaledWidth();
				int h = event.getResolution().getScaledHeight();
				this.drawSunBar();
			}
		}
	}
	
	@SubscribeEvent
	public void onrenderEnergy(RenderGameOverlayEvent event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT && mc.currentScreen == null
				&& !mc.gameSettings.hideGUI && !mc.player.isSpectator()) {
			if (KeyBind.statusPlayerStats&&ConfigurationUtil.MainConfig.displaySettings.showEnergyNum) {
				mc.getTextureManager().bindTexture(SUN_ENERGY);
				// System.out.println(mc.displayHeight);
				int w = event.getResolution().getScaledWidth();
				int h = event.getResolution().getScaledHeight();
				this.drawEnergyBar(w, h);
			}
		}
	}
	
	@SubscribeEvent
	public void onrenderMoney(RenderGameOverlayEvent event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT && mc.currentScreen == null
				&& !mc.gameSettings.hideGUI && !mc.player.isSpectator()) {
			if (KeyBind.statusPlayerStats&&ConfigurationUtil.MainConfig.displaySettings.showMoneyNum) {
				mc.getTextureManager().bindTexture(SUN_ENERGY);
				// System.out.println(mc.displayHeight);
				int w = event.getResolution().getScaledWidth();
				int h = event.getResolution().getScaledHeight();
				this.drawMoneyBar(w, h);
			}
		}
	}

	private void drawEnergyBar(int w, int h) {
		int lvl = PVZGuiTabPlayerData.level;
		long maxNum = PlayerUtil.getPlayerMaxEnergyNum(lvl);
		long num = PVZGuiTabPlayerData.energyNum;
		double percent = Math.min(num * 1.0d / maxNum, 1.0d);
		int len = MathHelper.floor(percent * 61);
		if (num != 0 && len == 0)
			len = 1;
		if (len == 61 && num < maxNum)
			len = 60;
		drawTexturedModalRect(0, h - tex_height - 1, 0, 0, tex_width, tex_height);
		drawTexturedModalRect(26, h - tex_height + 6, 0, 60, len, 14);
		RenderUtil.drawCenteredScaledString(mc.fontRenderer, PVZGuiTabPlayerData.energyNum + "", 0+55, h - tex_height - 1+8,
				1.5f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
	}

	private void drawSunBar() {
		// 0 140 sun 26 7
		int lvl = PVZGuiTabPlayerData.level;
		long maxNum = PlayerUtil.getPlayerMaxSunNum(lvl);
		long num = PVZGuiTabPlayerData.sunNum;
		double percent = Math.min(num * 1.0d / maxNum, 1.0d);

		int len = MathHelper.floor(percent * 61);
		if (num != 0 && len == 0)
			len = 1;
		if (len == 61 && num < maxNum)
			len = 60;
		drawTexturedModalRect(0, 0, 0, 140, tex_width, tex_height);
		drawTexturedModalRect(26, 7, 0, 174, len, 14);
		RenderUtil.drawCenteredScaledString(mc.fontRenderer, PVZGuiTabPlayerData.sunNum + "", 0+55, 0+8, 1.5f,
				Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
//		mc.fontRenderer.drawString(PVZGuiTabPlayerData.sunNum + "", 0, 0, Enums.RGBIntegers.WHITE);
	}

	private void drawMoneyBar(int w, int h) {
		// 0 200 sun bar
		drawTexturedModalRect(w - tex_width - 1, h - tex_height - 1, 0, 200, tex_width, tex_height);
//		System.out.println(PVZGuiTabPlayerData.money);
		RenderUtil.drawCenteredScaledString(mc.fontRenderer, PVZGuiTabPlayerData.money + "", w - tex_width - 1+35,
				h - tex_height - 1 +8, 1.5f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
//		mc.fontRenderer.drawString(PVZGuiTabPlayerData.money + "", w - tex_width - 1,h - tex_height - 1, Enums.RGBIntegers.WHITE);
	}
}

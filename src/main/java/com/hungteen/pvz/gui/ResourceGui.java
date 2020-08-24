package com.hungteen.pvz.gui;

import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.capabilities.player.PVZGuiTabPlayerData;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.Resources;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID,value = Dist.CLIENT)
public class ResourceGui{

	private static Minecraft mc = Minecraft.getInstance();
	private static final ResourceLocation RESOURCE_BAR = StringUtil.prefix("textures/gui/overlay/resource_bar.png");
	private static final int tex_width = 89, tex_height = 28;
	
	@SubscribeEvent
	public static void onRenderSunNumBar(RenderGameOverlayEvent ev){
		if(ev.getType()!=RenderGameOverlayEvent.ElementType.ALL) {
			return ;
		}
		PlayerEntity player = mc.player;
		if(player==null||mc.gameSettings.hideGUI||player.isSpectator()) {
			return ;
		}
		if(mc.currentScreen==null) {
			mc.getTextureManager().bindTexture(RESOURCE_BAR);
			drawSunNumBar();
		}
	}
	
	@SubscribeEvent
	public static void onRenderMoneyBar(RenderGameOverlayEvent ev){
		if(ev.getType()!=RenderGameOverlayEvent.ElementType.ALL) {
			return ;
		}
		PlayerEntity player = mc.player;
		if(player==null||mc.gameSettings.hideGUI||player.isSpectator()) {
			return ;
		}
		if(mc.currentScreen==null) {
			mc.getTextureManager().bindTexture(RESOURCE_BAR);
			drawMoneyBar(ev.getWindow().getScaledWidth(),ev.getWindow().getScaledHeight());
		}
	}
	
	@SubscribeEvent
	public static void onRenderEnergyNumBar(RenderGameOverlayEvent ev){
		if(ev.getType()!=RenderGameOverlayEvent.ElementType.ALL) {
			return ;
		}
		PlayerEntity player = mc.player;
		if(player==null||mc.gameSettings.hideGUI||player.isSpectator()) {
			return ;
		}
		if(mc.currentScreen==null) {
			mc.getTextureManager().bindTexture(RESOURCE_BAR);
			drawEnergyNumBar(ev.getWindow().getScaledWidth(),ev.getWindow().getScaledHeight());
		}
	}
	
	protected static void drawSunNumBar(){
		int lvl = PVZGuiTabPlayerData.getPlayerStats(Resources.TREE_LVL);
		int maxNum = PlayerUtil.getPlayerMaxSunNum(lvl);
		int num = PVZGuiTabPlayerData.getPlayerStats(Resources.SUN_NUM);
		double percent = Math.min(num * 1.0d / maxNum, 1.0d);

		int len = MathHelper.floor(percent * 61);
		if (num != 0 && len == 0)
			len = 1;
		if (len == 61 && num < maxNum)
			len = 60;
		mc.ingameGUI.blit(0, 0, 0, 140, tex_width, tex_height);
		mc.ingameGUI.blit(26, 7, 0, 174, len, 14);
//		drawTexturedModalRect(0, 0, 0, 140, tex_width, tex_height);
//		drawTexturedModalRect(26, 7, 0, 174, len, 14);
//		RenderUtil.drawCenteredScaledString(mc.fontRenderer, PVZGuiTabPlayerData.sunNum + "", 0+55, 0+8, 1.5f,
//				Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
//		StringUtil.drawCenteredString(mc.fontRenderer, num + "", 0+55, 0+8, Colors.WHITE);
		StringUtil.drawCenteredScaledString(mc.fontRenderer, num + "", 0+55, 0+8, Colors.WHITE,1.5f);
	}
	
	protected static void drawMoneyBar(int w,int h){
		mc.ingameGUI.blit(w - tex_width - 1, h - tex_height - 1, 0, 200, tex_width, tex_height);
		StringUtil.drawCenteredScaledString(mc.fontRenderer, PVZGuiTabPlayerData.getPlayerStats(Resources.MONEY) + "",w - tex_width - 1+35,
				h - tex_height - 1 +8, Colors.WHITE, 1.5f);
//		drawTexturedModalRect(w - tex_width - 1, h - tex_height - 1, 0, 200, tex_width, tex_height);
////		System.out.println(PVZGuiTabPlayerData.money);
//		RenderUtil.drawCenteredScaledString(mc.fontRenderer, PVZGuiTabPlayerData.money + "", w - tex_width - 1+35,
//				h - tex_height - 1 +8, 1.5f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
	}
	
	protected static void drawEnergyNumBar(int w,int h){
		int lvl=PVZGuiTabPlayerData.getPlayerStats(Resources.TREE_LVL);
		int maxNum = PlayerUtil.getPlayerMaxEnergyNum(lvl);
		int num = PVZGuiTabPlayerData.getPlayerStats(Resources.ENERGY_NUM);
		double percent = Math.min(num * 1.0d / maxNum, 1.0d);
		int len = MathHelper.floor(percent * 61);
		if (num != 0 && len == 0)
			len = 1;
		if (len == 61 && num < maxNum)
			len = 60;
		mc.ingameGUI.blit(0, h - tex_height - 1, 0, 0, tex_width, tex_height);
		mc.ingameGUI.blit(26, h - tex_height + 6, 0, 60, len, 14);
		StringUtil.drawCenteredScaledString(mc.fontRenderer, num + "", 0+55, h - tex_height - 1+8, Colors.WHITE,1.5f);
//		drawTexturedModalRect(0, h - tex_height - 1, 0, 0, tex_width, tex_height);
//		drawTexturedModalRect(26, h - tex_height + 6, 0, 60, len, 14);
//		RenderUtil.drawCenteredScaledString(mc.fontRenderer, PVZGuiTabPlayerData.energyNum + "", 0+55, h - tex_height - 1+8,
//				1.5f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
	}
}

package com.hungteen.pvz.gui;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.capabilities.player.ClientPlayerResources;
import com.hungteen.pvz.register.KeyBindRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.RenderUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid=PVZMod.MOD_ID,value = Dist.CLIENT)
public class ResourceGui{

	private static Minecraft mc = Minecraft.getInstance();
//	private static final ResourceLocation RESOURCE_BAR = StringUtil.prefix("textures/gui/overlay/resource_bar.png");
	private static final ResourceLocation RESOURCE = StringUtil.prefix("textures/gui/overlay/resource.png");
//	private static final int tex_width = 89, tex_height = 28;
	private static final int W = 160;
	private static final int H = 32;
	private static final int BAR_LEN = 123;
	private static final int BAR_H = 26;
	
	@SubscribeEvent
	public static void onRenderSunNumBar(RenderGameOverlayEvent ev){
		if(!KeyBindRegister.showPlayerResources||ev.getType()!=RenderGameOverlayEvent.ElementType.ALL||!PVZConfig.CLIENT_CONFIG.ResourceRender.RenderSunNumBar.get()) {
			return ;
		}
		PlayerEntity player = mc.player;
		if(player==null||mc.gameSettings.hideGUI||player.isSpectator()) {
			return ;
		}
		if(mc.currentScreen==null) {
			mc.getTextureManager().bindTexture(RESOURCE);
			drawSunNumBar();
		}
	}
	
	@SubscribeEvent
	public static void onRenderMoneyBar(RenderGameOverlayEvent ev){
		if(!KeyBindRegister.showPlayerResources||ev.getType()!=RenderGameOverlayEvent.ElementType.ALL||!PVZConfig.CLIENT_CONFIG.ResourceRender.RenderMoneyBar.get()) {
			return ;
		}
		PlayerEntity player = mc.player;
		if(player==null||mc.gameSettings.hideGUI||player.isSpectator()) {
			return ;
		}
		if(mc.currentScreen==null) {
			mc.getTextureManager().bindTexture(RESOURCE);
			drawMoneyBar(ev.getWindow().getScaledWidth(),ev.getWindow().getScaledHeight());
		}
	}
	
	@SubscribeEvent
	public static void onRenderEnergyNumBar(RenderGameOverlayEvent ev){
		if(!KeyBindRegister.showPlayerResources||ev.getType()!=RenderGameOverlayEvent.ElementType.ALL||!PVZConfig.CLIENT_CONFIG.ResourceRender.RenderEnergyNumBar.get()) {
			return ;
		}
		PlayerEntity player = mc.player;
		if(player==null||mc.gameSettings.hideGUI||player.isSpectator()) {
			return ;
		}
		if(mc.currentScreen==null) {
			mc.getTextureManager().bindTexture(RESOURCE);
			drawEnergyNumBar(ev.getWindow().getScaledWidth(),ev.getWindow().getScaledHeight());
		}
	}
	
	protected static void drawSunNumBar(){
		int lvl = ClientPlayerResources.getPlayerStats(Resources.TREE_LVL);
		int maxNum = PlayerUtil.getPlayerMaxSunNum(lvl);
		int num = ClientPlayerResources.getPlayerStats(Resources.SUN_NUM);
		int len = RenderUtil.getRenderBarLen(num, maxNum, BAR_LEN);
		RenderSystem.pushMatrix();
		RenderSystem.enableBlend();
		RenderSystem.scalef(0.6f, 0.6f, 0.6f);
		mc.ingameGUI.blit(0, 0, 0, 0, W, H);
		mc.ingameGUI.blit(0, 3, 0, 35, 34+len, BAR_H);
		StringUtil.drawCenteredScaledString(mc.fontRenderer, num + "", 95, 5, Colors.WHITE,3f);
		RenderSystem.popMatrix();
//		mc.ingameGUI.blit(0, 0, 0, 140, tex_width, tex_height);
//		mc.ingameGUI.blit(26, 7, 0, 174, len, 14);
		
	}
	
	protected static void drawMoneyBar(int w,int h){
		RenderSystem.pushMatrix();
		RenderSystem.enableBlend();
		RenderSystem.scalef(0.5f, 0.5f, 0.5f);
		mc.ingameGUI.blit(2*w - W, 2*h - H, 0, 96, W, H);
		StringUtil.drawCenteredScaledString(mc.fontRenderer, ClientPlayerResources.getPlayerStats(Resources.MONEY) + "",2*w - 95,
				2*h - H + 5, Colors.WHITE, 3f);
		RenderSystem.popMatrix();
	}
	
	protected static void drawEnergyNumBar(int w,int h){
		int maxNum = ClientPlayerResources.getPlayerStats(Resources.MAX_ENERGY_NUM);
		int num = ClientPlayerResources.getPlayerStats(Resources.ENERGY_NUM);
//		mc.ingameGUI.blit(0, h - tex_height - 1, 0, 0, tex_width, tex_height);
//		mc.ingameGUI.blit(26, h - tex_height + 6, 0, 60, len, 14);
		RenderSystem.pushMatrix();
		RenderSystem.enableBlend();
		RenderSystem.scalef(0.5f, 0.5f, 0.5f);
		mc.ingameGUI.blit(0, 2*h-H, 0, 64, 35, H);//render head
		int currentX = 35;
		for(int i=0;i<maxNum;i++) {
			if(num>0) {
				num--;
				mc.ingameGUI.blit(currentX, 2*h-H, 35, 64, 26, H);
			}else {
				mc.ingameGUI.blit(currentX, 2*h-H, 61, 64, 26, H);
			}
			currentX+=26;
		}
		mc.ingameGUI.blit(currentX, 2*h-H, 156, 64, 4, H);//render tail
		RenderSystem.popMatrix();
	}
}

package com.hungteen.pvz.event;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.capability.player.ClientPlayerResources;
import com.hungteen.pvz.register.KeyBindRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.RenderUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.Resources;
import com.hungteen.pvz.world.WaveManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID, value = Dist.CLIENT)
public class OverlayEvents {

	private static Minecraft mc = Minecraft.getInstance();
	private static final ResourceLocation RESOURCE = StringUtil.prefix("textures/gui/overlay/resource.png");
	private static final ResourceLocation FOG = StringUtil.prefix("textures/gui/overlay/fog.png");
	private static final ResourceLocation INVASION = StringUtil.prefix("textures/gui/overlay/invasion.png");
	private static final int W = 160;
	private static final int H = 32;
	private static final int BAR_LEN = 123;
	private static final int BAR_H = 26;

	@SubscribeEvent
	public static void onPostRenderOverlay(RenderGameOverlayEvent.Post ev) {
		if (ev.getType() != RenderGameOverlayEvent.ElementType.ALL
				|| mc.player == null || mc.gameSettings.hideGUI || mc.player.isSpectator()) {
			return;
		}
		if (mc.currentScreen == null) {
			if(KeyBindRegister.showPlayerResources) {
				if (PVZConfig.CLIENT_CONFIG.ResourceRender.RenderSunNumBar.get()) {
				    
				}
				if(checkCurrentPos(KeyBindRegister.currentResourcePos)) {
					if(KeyBindRegister.currentResourcePos == 0) {
						drawSunNumBar();
					} else if(KeyBindRegister.currentResourcePos == 1) {
						drawMoneyBar();
					} else if(KeyBindRegister.currentResourcePos == 2) {
						drawGemBar();
					}
				}
			    if (PVZConfig.CLIENT_CONFIG.ResourceRender.RenderEnergyNumBar.get()) {
				    drawEnergyNumBar(ev.getWindow().getScaledWidth(), ev.getWindow().getScaledHeight());
			    }
			}
			if(KeyBindRegister.showInvasionProgress) {
				if(PlayerUtil.isPlayerSurvival(mc.player)  && PVZConfig.CLIENT_CONFIG.InvasionRender.RenderInvasionProgress.get()) {
					renderInvasionProgress(ev.getWindow().getScaledWidth(), ev.getWindow().getScaledHeight());
				}
			}
		}
	}

	@SubscribeEvent
	public static void onRenderFog(RenderGameOverlayEvent.Pre ev) {
		if (ev.getType() != RenderGameOverlayEvent.ElementType.ALL || mc.player == null || mc.player.isSpectator()) {
			return;
		}
		if (PVZConfig.CLIENT_CONFIG.EnvironmentRender.RenderFog.get()) {
			int tick = ClientPlayerResources.getPlayerStats(Resources.NO_FOG_TICK);
			if(tick < 0) {
				renderFog(ev.getWindow().getScaledWidth(), ev.getWindow().getScaledHeight(), Math.min(- tick * 1f / 100, 1f));
			}
		}
	}
	
	private static void renderInvasionProgress(int w, int h) {
		if(ClientPlayerResources.totalWaveCount == 0) return ;
		RenderSystem.pushMatrix();
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		float scale = 0.5F;
		int offsetY = 0;
		int offsetX = 0;
		RenderSystem.scaled(scale, scale, scale);
		mc.getTextureManager().bindTexture(INVASION);
		final int WIDTH = 157, HEIGHT = 21;
		mc.ingameGUI.blit(w * 2 - WIDTH + offsetX, h * 2 - HEIGHT + offsetY, 0, 0, WIDTH, HEIGHT);
		final int P_WIDTH = 143, P_HEIGHT = 7;
		int barlen = RenderUtil.getRenderBarLen((int)(mc.player.world.getDayTime() % 24000L), 24000, P_WIDTH);
		mc.ingameGUI.blit(w * 2 - barlen - 7 + offsetX, h * 2 - HEIGHT + 7 + offsetY, 149 - barlen + 1, 28, barlen, P_HEIGHT);
		for(int i = 0; i < ClientPlayerResources.totalWaveCount; ++ i) {
			boolean rise = (ClientPlayerResources.zombieWaveTime[i] >= WaveManager.FINISH_OFFSET);
			int waveTime = (rise ? ClientPlayerResources.zombieWaveTime[i] - WaveManager.FINISH_OFFSET : ClientPlayerResources.zombieWaveTime[i]);
			int waveLen = RenderUtil.getRenderBarLen(waveTime, 24000, P_WIDTH);
			mc.ingameGUI.blit(w * 2 - waveLen - 11 + offsetX, h * 2 - HEIGHT + (rise ? - 9 : - 4) + offsetY, 27, 42, 20, (rise ? 27 : 19));
		}
		mc.ingameGUI.blit(w * 2 - barlen - 11 + offsetX, h * 2 - HEIGHT - 4 + offsetY, 0, 42, 24, 23);
		RenderSystem.popMatrix();
	}

	private static void renderFog(int w, int h, float dep) {
		RenderSystem.pushMatrix();
		RenderSystem.enableBlend();
		RenderSystem.color4f(1f, 1f, 1f, dep);
		mc.getTextureManager().bindTexture(FOG);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(0.0D, h, -90.0D).tex(0.0F, 1.0F).endVertex();
		bufferbuilder.pos(w, h, -90.0D).tex(1.0F, 1.0F).endVertex();
		bufferbuilder.pos(w, 0.0D, -90.0D).tex(1.0F, 0.0F).endVertex();
		bufferbuilder.pos(0.0D, 0.0D, -90.0D).tex(0.0F, 0.0F).endVertex();
		tessellator.draw();
		RenderSystem.popMatrix();
	}

	private static void drawSunNumBar() {
		int lvl = ClientPlayerResources.getPlayerStats(Resources.TREE_LVL);
		int maxNum = PlayerUtil.getPlayerMaxSunNum(lvl);
		int num = ClientPlayerResources.getPlayerStats(Resources.SUN_NUM);
		int len = RenderUtil.getRenderBarLen(num, maxNum, BAR_LEN);
		RenderSystem.pushMatrix();
		RenderSystem.enableBlend();
		RenderSystem.scalef(0.6f, 0.6f, 0.6f);
		mc.getTextureManager().bindTexture(RESOURCE);
		mc.ingameGUI.blit(0, 0, 0, 0, W, H);
		mc.ingameGUI.blit(0, 3, 0, 35, 34 + len, BAR_H);
		StringUtil.drawCenteredScaledString(mc.fontRenderer, num + "", 95, 5, Colors.WHITE, 3f);
		RenderSystem.popMatrix();
	}

	private static void drawMoneyBar() {
		RenderSystem.pushMatrix();
		RenderSystem.enableBlend();
		RenderSystem.scalef(0.6f, 0.6f, 0.6f);
		mc.getTextureManager().bindTexture(RESOURCE);
		mc.ingameGUI.blit(0, 0, 0, 96, W, H);
		StringUtil.drawCenteredScaledString(mc.fontRenderer, ClientPlayerResources.getPlayerStats(Resources.MONEY) + "", 95, 5, Colors.WHITE, 3f);
		RenderSystem.popMatrix();
	}
	
	private static void drawGemBar() {
		RenderSystem.pushMatrix();
		RenderSystem.enableBlend();
		RenderSystem.scalef(0.6f, 0.6f, 0.6f);
		mc.getTextureManager().bindTexture(RESOURCE);
		mc.ingameGUI.blit(0, 0, 0, 128, W, H);
		StringUtil.drawCenteredScaledString(mc.fontRenderer, ClientPlayerResources.getPlayerStats(Resources.GEM_NUM) + "", 95, 5, Colors.WHITE, 3f);
		RenderSystem.popMatrix();
	}

	private static void drawEnergyNumBar(int w, int h) {
		int maxNum = ClientPlayerResources.getPlayerStats(Resources.MAX_ENERGY_NUM);
		int num = ClientPlayerResources.getPlayerStats(Resources.ENERGY_NUM);
		mc.getTextureManager().bindTexture(RESOURCE);
		RenderSystem.pushMatrix();
		RenderSystem.enableBlend();
		RenderSystem.scalef(0.5f, 0.5f, 0.5f);
		mc.ingameGUI.blit(0, 2 * h - H, 0, 64, 35, H);// render head
		int currentX = 35;
		for (int i = 0; i < maxNum; i++) {
			if (num > 0) {
				num--;
				mc.ingameGUI.blit(currentX, 2 * h - H, 35, 64, 26, H);
			} else {
				mc.ingameGUI.blit(currentX, 2 * h - H, 61, 64, 26, H);
			}
			currentX += 26;
		}
		mc.ingameGUI.blit(currentX, 2 * h - H, 156, 64, 4, H);// render tail
		RenderSystem.popMatrix();
	}
	
	public static boolean checkCurrentPos(int pos) {
		if(pos == 0) return PVZConfig.CLIENT_CONFIG.ResourceRender.RenderSunNumBar.get();
		if(pos == 1) return PVZConfig.CLIENT_CONFIG.ResourceRender.RenderMoneyBar.get();
		if(pos == 2) return PVZConfig.CLIENT_CONFIG.ResourceRender.RenderGemBar.get();
		System.out.println("Error : Wrong Resource Render Pos !");
		return false;
	}

}

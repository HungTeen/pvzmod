package com.hungteen.pvz.client;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.cache.ClientPlayerResources;
import com.hungteen.pvz.common.entity.plant.explosion.CobCannonEntity;
import com.hungteen.pvz.common.world.invasion.FogManager;
import com.hungteen.pvz.common.world.invasion.WaveManager;
import com.hungteen.pvz.register.KeyBindRegister;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.RenderUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.blaze3d.matrix.MatrixStack;
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
	private static final ResourceLocation TARGET = StringUtil.prefix("textures/gui/overlay/target.png");
	private static final int W = 160;
	private static final int H = 32;
	private static final int BAR_LEN = 123;
	private static final int BAR_H = 26;

	@SubscribeEvent
	public static void onPostRenderOverlay(RenderGameOverlayEvent.Post ev) {
		if (ev.getType() != RenderGameOverlayEvent.ElementType.ALL
				|| mc.player == null || mc.options.hideGui || mc.player.isSpectator()) {
			return;
		}
		if (mc.screen == null) {
			if(KeyBindRegister.showPlayerResources) {
				if (PVZConfig.CLIENT_CONFIG.ResourceRender.RenderSunNumBar.get()) {
				    
				}
				if(checkCurrentPos(KeyBindRegister.currentResourcePos)) {
					if(KeyBindRegister.currentResourcePos == 0) {
						drawSunNumBar(ev.getMatrixStack());
					} else if(KeyBindRegister.currentResourcePos == 1) {
						drawMoneyBar(ev.getMatrixStack());
					} else if(KeyBindRegister.currentResourcePos == 2) {
						drawGemBar(ev.getMatrixStack());
					}
				}
			    if (PVZConfig.CLIENT_CONFIG.ResourceRender.RenderEnergyNumBar.get()) {
				    drawEnergyNumBar(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight());
			    }
			}
			if(KeyBindRegister.showInvasionProgress) {
				if(PlayerUtil.isPlayerSurvival(mc.player)  && PVZConfig.CLIENT_CONFIG.InvasionRender.RenderInvasionProgress.get()) {
					renderInvasionProgress(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight());
				}
			}
			if(mc.player.getVehicle() instanceof CobCannonEntity) {
				CobCannonEntity cob = (CobCannonEntity) mc.player.getVehicle();
				if(cob.getCornNum() > 0) {
					renderTargetAim(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight());
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
				renderFog(ev.getMatrixStack(), ev.getWindow().getGuiScaledWidth(), ev.getWindow().getGuiScaledHeight(), Math.min(- tick * 1F / FogManager.CD, 1F));
			}
		}
	}
	
	private static void renderInvasionProgress(MatrixStack stack, int w, int h) {
		if(ClientPlayerResources.totalWaveCount == 0) {
			return ;
		}
		stack.pushPose();
		float scale = 0.5F;
		int offsetY = 0;
		int offsetX = 0;
		stack.scale(scale, scale, scale);
		mc.getTextureManager().bind(INVASION);
		final int WIDTH = 157, HEIGHT = 21;
		mc.gui.blit(stack, w * 2 - WIDTH + offsetX, h * 2 - HEIGHT + offsetY, 0, 0, WIDTH, HEIGHT);
		final int P_WIDTH = 143, P_HEIGHT = 7;
		int barlen = RenderUtil.getRenderBarLen((int)(mc.player.level.getDayTime() % 24000L), 24000, P_WIDTH);
		mc.gui.blit(stack, w * 2 - barlen - 7 + offsetX, h * 2 - HEIGHT + 7 + offsetY, 149 - barlen + 1, 28, barlen, P_HEIGHT);
		for(int i = 0; i < ClientPlayerResources.totalWaveCount; ++ i) {
			boolean rise = (ClientPlayerResources.zombieWaveTime[i] >= WaveManager.FINISH_OFFSET);
			int waveTime = (rise ? ClientPlayerResources.zombieWaveTime[i] - WaveManager.FINISH_OFFSET : ClientPlayerResources.zombieWaveTime[i]);
			int waveLen = RenderUtil.getRenderBarLen(waveTime, 24000, P_WIDTH);
			mc.gui.blit(stack, w * 2 - waveLen - 11 + offsetX, h * 2 - HEIGHT + (rise ? - 9 : - 4) + offsetY, 27, 42, 20, (rise ? 27 : 19));
		}
		mc.gui.blit(stack, w * 2 - barlen - 11 + offsetX, h * 2 - HEIGHT - 4 + offsetY, 0, 42, 24, 23);
		stack.popPose();
	}
	
	private static void renderTargetAim(MatrixStack stack, int w, int h) {
		stack.pushPose();
		float scale = 1F;
		stack.scale(scale, scale, scale);
		mc.getTextureManager().bind(TARGET);
		final int WIDTH = 32, HEIGHT = 32;
		mc.gui.blit(stack, (w - WIDTH) / 2 - 0, (h - HEIGHT) / 2 - 0, 0, 0, WIDTH, HEIGHT);
		stack.popPose();
	}

	@SuppressWarnings("deprecation")
	private static void renderFog(MatrixStack stack, int w, int h, float dep) {
		stack.pushPose();
		RenderSystem.enableBlend();
		RenderSystem.color4f(1f, 1f, 1f, dep);
		mc.getTextureManager().bind(FOG);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuilder();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.vertex(0.0D, h, -90.0D).uv(0.0F, 1.0F).endVertex();
		bufferbuilder.vertex(w, h, -90.0D).uv(1.0F, 1.0F).endVertex();
		bufferbuilder.vertex(w, 0.0D, -90.0D).uv(1.0F, 0.0F).endVertex();
		bufferbuilder.vertex(0.0D, 0.0D, -90.0D).uv(0.0F, 0.0F).endVertex();
		tessellator.end();
		stack.popPose();
	}

	private static void drawSunNumBar(MatrixStack stack) {
		int lvl = ClientPlayerResources.getPlayerStats(Resources.TREE_LVL);
		int maxNum = PlayerUtil.getPlayerMaxSunNum(lvl);
		int num = ClientPlayerResources.getPlayerStats(Resources.SUN_NUM);
		int len = RenderUtil.getRenderBarLen(num, maxNum, BAR_LEN);
		stack.pushPose();
		RenderSystem.enableBlend();
		stack.scale(0.6f, 0.6f, 0.6f);
		mc.getTextureManager().bind(RESOURCE);
		mc.gui.blit(stack, 0, 0, 0, 0, W, H);
		mc.gui.blit(stack, 0, 3, 0, 35, 34 + len, BAR_H);
		StringUtil.drawCenteredScaledString(stack, mc.font, num + "", 95, 5, Colors.WHITE, 3f);
		stack.popPose();
	}

	private static void drawMoneyBar(MatrixStack stack) {
		stack.pushPose();
		RenderSystem.enableBlend();
		stack.scale(0.6f, 0.6f, 0.6f);
		mc.getTextureManager().bind(RESOURCE);
		mc.gui.blit(stack, 0, 0, 0, 96, W, H);
		StringUtil.drawCenteredScaledString(stack, mc.font, ClientPlayerResources.getPlayerStats(Resources.MONEY) + "", 95, 5, Colors.WHITE, 3f);
		stack.popPose();
	}
	
	private static void drawGemBar(MatrixStack stack) {
		stack.pushPose();
		RenderSystem.enableBlend();
		stack.scale(0.6f, 0.6f, 0.6f);
		mc.getTextureManager().bind(RESOURCE);
		mc.gui.blit(stack, 0, 0, 0, 128, W, H);
		StringUtil.drawCenteredScaledString(stack, mc.font, ClientPlayerResources.getPlayerStats(Resources.GEM_NUM) + "", 95, 5, Colors.WHITE, 3f);
		stack.popPose();
	}

	private static void drawEnergyNumBar(MatrixStack stack, int w, int h) {
		int maxNum = ClientPlayerResources.getPlayerStats(Resources.MAX_ENERGY_NUM);
		int num = ClientPlayerResources.getPlayerStats(Resources.ENERGY_NUM);
		stack.pushPose();
		mc.getTextureManager().bind(RESOURCE);
		RenderSystem.enableBlend();
		stack.scale(0.5f, 0.5f, 0.5f);
		mc.gui.blit(stack, 0, 2 * h - H, 0, 64, 35, H);// render head
		int currentX = 35;
		for (int i = 0; i < maxNum; i++) {
			if (num > 0) {
				num--;
				mc.gui.blit(stack, currentX, 2 * h - H, 35, 64, 26, H);
			} else {
				mc.gui.blit(stack, currentX, 2 * h - H, 61, 64, 26, H);
			}
			currentX += 26;
		}
		mc.gui.blit(stack, currentX, 2 * h - H, 156, 64, 4, H);// render tail
		stack.popPose();
	}
	
	public static boolean checkCurrentPos(int pos) {
		if(pos == 0) return PVZConfig.CLIENT_CONFIG.ResourceRender.RenderSunNumBar.get();
		if(pos == 1) return PVZConfig.CLIENT_CONFIG.ResourceRender.RenderMoneyBar.get();
		if(pos == 2) return PVZConfig.CLIENT_CONFIG.ResourceRender.RenderGemBar.get();
		System.out.println("Error : Wrong Resource Render Pos !");
		return false;
	}

}

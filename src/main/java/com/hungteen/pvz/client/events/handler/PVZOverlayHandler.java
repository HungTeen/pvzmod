package com.hungteen.pvz.client.events.handler;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.client.ClientProxy;
import com.hungteen.pvz.client.cache.ClientPlayerResources;
import com.hungteen.pvz.common.item.card.SummonCardItem;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.toserver.PVZMouseScrollPacket;
import com.hungteen.pvz.common.world.invasion.WaveManager;
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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PVZOverlayHandler {

	private static Minecraft mc = Minecraft.getInstance();
	private static final ResourceLocation RESOURCE = StringUtil.prefix("textures/gui/overlay/resource.png");
	private static final ResourceLocation FOG = StringUtil.prefix("textures/gui/overlay/fog.png");
	private static final ResourceLocation INVASION = StringUtil.prefix("textures/gui/overlay/invasion.png");
	private static final ResourceLocation TARGET = StringUtil.prefix("textures/gui/overlay/target.png");
	private static final int W = 160;
	private static final int H = 32;
	private static final int BAR_LEN = 123;
	private static final int BAR_H = 26;
	private static final int SLOT_SIDE = 22;
	private static final int SLOT_DELAY_CD = 20;
	private static int SlotDelay = 0;
	
	@SuppressWarnings("deprecation")
	public static void drawCardInventory(PlayerEntity player, MatrixStack stack, int w, int h) {
		if(player.getMainHandItem().getItem() instanceof SummonCardItem) {
			if(! ClientPlayerResources.SUMMON_CARDS.get(ClientPlayerResources.emptySlot).equals(player.getMainHandItem())) {
				PVZPacketHandler.CHANNEL.sendToServer(new PVZMouseScrollPacket(0));
			}
			if(SlotDelay < SLOT_DELAY_CD) {
				++ SlotDelay;
			}
		} else {
			if(SlotDelay > 0) {
				-- SlotDelay;
			}
		}
		if(SlotDelay > 0) {
			final int maxSlot = ClientPlayerResources.getPlayerStats(Resources.SLOT_NUM) + 1;
			final int totHeight = (SLOT_SIDE - 2) * maxSlot;
			final int startHeight = (h - totHeight) / 2;
			final float offset = - 10 + 10F * SlotDelay / SLOT_DELAY_CD;
			stack.pushPose();
			RenderSystem.enableBlend();
			stack.translate(offset, 0, 0);
			/* render slots */
			for(int i = 0; i < maxSlot; ++ i) {
				if(i == ClientPlayerResources.emptySlot) {
					mc.gui.blit(stack, 0, startHeight + i * (SLOT_SIDE - 2), 162, 22, SLOT_SIDE, SLOT_SIDE);
				} else {
					mc.gui.blit(stack, 0, startHeight + i * (SLOT_SIDE - 2), 162, 0, SLOT_SIDE, SLOT_SIDE);
				}
			}
			/* render itemstack */
			for(int i = 0; i < Math.min(ClientPlayerResources.SUMMON_CARDS.size(), maxSlot); ++ i) {
				RenderSystem.pushMatrix();
				RenderSystem.translated(offset, 0, 0);
				ClientProxy.MC.getItemRenderer().renderGuiItem(ClientPlayerResources.SUMMON_CARDS.get(i), 3, startHeight + 3 + i * (SLOT_SIDE - 2));
			    RenderSystem.popMatrix();
			}
			stack.popPose();
		}
//		StringUtil.drawCenteredScaledString(stack, mc.font, num + "", 95, 5, Colors.WHITE, 3f);
	}
	
	public static void renderInvasionProgress(MatrixStack stack, int w, int h) {
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
	
	public static void renderTargetAim(MatrixStack stack, int w, int h) {
		stack.pushPose();
		float scale = 1F;
		stack.scale(scale, scale, scale);
		mc.getTextureManager().bind(TARGET);
		final int WIDTH = 32, HEIGHT = 32;
		mc.gui.blit(stack, (w - WIDTH) / 2 - 0, (h - HEIGHT) / 2 - 0, 0, 0, WIDTH, HEIGHT);
		stack.popPose();
	}

	@SuppressWarnings("deprecation")
	public static void renderFog(MatrixStack stack, int w, int h, float dep) {
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

	public static void drawSunNumBar(MatrixStack stack) {
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

	public static void drawMoneyBar(MatrixStack stack) {
		stack.pushPose();
		RenderSystem.enableBlend();
		stack.scale(0.6f, 0.6f, 0.6f);
		mc.getTextureManager().bind(RESOURCE);
		mc.gui.blit(stack, 0, 0, 0, 96, W, H);
		StringUtil.drawCenteredScaledString(stack, mc.font, ClientPlayerResources.getPlayerStats(Resources.MONEY) + "", 95, 5, Colors.WHITE, 3f);
		stack.popPose();
	}
	
	public static void drawGemBar(MatrixStack stack) {
		stack.pushPose();
		RenderSystem.enableBlend();
		stack.scale(0.6f, 0.6f, 0.6f);
		mc.getTextureManager().bind(RESOURCE);
		mc.gui.blit(stack, 0, 0, 0, 128, W, H);
		StringUtil.drawCenteredScaledString(stack, mc.font, ClientPlayerResources.getPlayerStats(Resources.GEM_NUM) + "", 95, 5, Colors.WHITE, 3f);
		stack.popPose();
	}

	public static void drawEnergyNumBar(MatrixStack stack, int w, int h) {
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

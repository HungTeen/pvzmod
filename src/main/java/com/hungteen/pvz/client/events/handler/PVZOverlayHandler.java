package com.hungteen.pvz.client.events.handler;

import com.hungteen.pvz.client.ClientProxy;
import com.hungteen.pvz.client.cache.ClientPlayerResources;
import com.hungteen.pvz.client.events.OverlayEvents;
import com.hungteen.pvz.client.events.PVZInputEvents;
import com.hungteen.pvz.common.capability.player.PlayerDataManager;
import com.hungteen.pvz.common.item.spawn.card.SummonCardItem;
import com.hungteen.pvz.common.network.PVZPacketHandler;
import com.hungteen.pvz.common.network.toserver.PVZMouseScrollPacket;
import com.hungteen.pvz.common.world.invasion.MissionManager;
import com.hungteen.pvz.utils.ConfigUtil;
import com.hungteen.pvz.utils.MathUtil;
import com.hungteen.pvz.utils.PlayerUtil;
import com.hungteen.pvz.utils.StringUtil;
import com.hungteen.pvz.utils.enums.Colors;
import com.hungteen.pvz.utils.enums.Resources;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PVZOverlayHandler {

	private static final ResourceLocation RESOURCE = StringUtil.prefix("textures/gui/overlay/resources.png");
	private static final ResourceLocation FOG = StringUtil.prefix("textures/gui/overlay/fog.png");
	private static final ResourceLocation INVASION = StringUtil.prefix("textures/gui/overlay/invasion.png");
	private static final ResourceLocation TARGET = StringUtil.prefix("textures/gui/overlay/target.png");
	private static final int SUN_BAR_W1 = 157;
	private static final int SUN_BAR_H1 = 32;
	private static final int SUN_BAR_W2 = 122;
	private static final int EACH_W = 26;
	private static final int SLOT_SIDE = 22;
	private static final int SLOT_DELAY_CD = 20;
	private static int SlotDelay = 0;
	
	/**
	 * {@link OverlayEvents#onPostRenderOverlay(net.minecraftforge.client.event.RenderGameOverlayEvent.Post)}
	 */
	public static void renderResources(MatrixStack stack, int width, int height) {
		if(PVZInputEvents.ShowOverlay) {
			final int pos = PVZInputEvents.CurrentResourcePos;
			if(pos == 0 && ConfigUtil.renderSunBar()) {
				renderSunBar(stack, width, height);
			} else if(pos == 1 && ConfigUtil.renderMoneyBar()) {
				renderMoneyBar(stack, width, height);
			} else if(pos == 2 && ConfigUtil.renderGemBar()) {
				renderGemBar(stack, width, height);
			} else if(pos == 3 && ConfigUtil.renderTreeLevel()) {
				renderTreeLevel(stack, width, height);
			}
		}
	}
	
	/**
	 * {@link OverlayEvents#onPostRenderOverlay(net.minecraftforge.client.event.RenderGameOverlayEvent.Post)}
	 */
	public static void renderPlantFood(MatrixStack stack, int w, int h) {
		final int maxNum = PlayerUtil.getResource(ClientProxy.MC.player, Resources.MAX_ENERGY_NUM);
		int num = PlayerUtil.getResource(ClientProxy.MC.player, Resources.ENERGY_NUM);
		final float sz = 0.5F;
		stack.pushPose();
		RenderSystem.enableBlend();
		stack.scale(sz, sz, 1F);
		ClientProxy.MC.getTextureManager().bind(RESOURCE);
		
		/* render head */
		ClientProxy.MC.gui.blit(stack, 0, h * 2 - SUN_BAR_H1, 0, 64, 34, SUN_BAR_H1);
		if(num > 0) {// light.
			ClientProxy.MC.gui.blit(stack, 0, h * 2 - SUN_BAR_H1, 162, 45, SUN_BAR_H1, SUN_BAR_H1);
		}
		
		/* render body */
		int currentX = 35;
		for (int i = 0; i < maxNum; i++) {
			if (num -- > 0) {
				ClientProxy.MC.gui.blit(stack, currentX, h * 2 - SUN_BAR_H1, 35, 64, 26, SUN_BAR_H1);
			} else {
				ClientProxy.MC.gui.blit(stack, currentX, h * 2 - SUN_BAR_H1, 61, 64, 26, SUN_BAR_H1);
			}
			currentX += EACH_W;
		}
		
		/* render tail */
		ClientProxy.MC.gui.blit(stack, currentX, h * 2 - SUN_BAR_H1, 153, 64, 4, SUN_BAR_H1);
		
		RenderSystem.disableBlend();
		stack.popPose();
	}
	
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
			final int maxSlot = PlayerUtil.getResource(ClientProxy.MC.player, Resources.SLOT_NUM) + 1;
			final int totHeight = (SLOT_SIDE - 2) * maxSlot;
			final int startHeight = (h - totHeight) / 2;
			final float offset = - 10 + 10F * SlotDelay / SLOT_DELAY_CD;
			stack.pushPose();
			RenderSystem.enableBlend();
			
			stack.translate(offset, 0, 0);
			/* render slots */
			for(int i = 0; i < maxSlot; ++ i) {
				if(i == ClientPlayerResources.emptySlot) {
					ClientProxy.MC.gui.blit(stack, 0, startHeight + i * (SLOT_SIDE - 2), 162, 22, SLOT_SIDE, SLOT_SIDE);
				} else {
					ClientProxy.MC.gui.blit(stack, 0, startHeight + i * (SLOT_SIDE - 2), 162, 0, SLOT_SIDE, SLOT_SIDE);
				}
			}
			/* render itemstack */
			for(int i = 0; i < Math.min(ClientPlayerResources.SUMMON_CARDS.size(), maxSlot); ++ i) {
				RenderSystem.pushMatrix();
				RenderSystem.translated(offset, 0, 0);
				ClientProxy.MC.getItemRenderer().renderGuiItem(ClientPlayerResources.SUMMON_CARDS.get(i), 3, startHeight + 3 + i * (SLOT_SIDE - 2));
			    RenderSystem.popMatrix();
			}
			
			RenderSystem.disableBlend();
			stack.popPose();
		}
		
		renderPlantFood(stack, w, h);
	}
	
	public static void renderInvasionProgress(MatrixStack stack, int w, int h) {
		final PlayerDataManager manager = PlayerUtil.getManager(ClientProxy.MC.player);
		final int count = manager.getTotalWaveCount();
		if(count == 0){
			return;
		}
		stack.pushPose();
		
		ClientProxy.MC.getTextureManager().bind(INVASION);
		
		final float sz = 0.7F;
		stack.scale(sz, sz, sz);
		
		final int WIDTH = 158, HEIGHT = 21;
		ClientProxy.MC.gui.blit(stack, (int) (w / sz) - WIDTH, (int)(h / sz) - HEIGHT, 0, 0, WIDTH, HEIGHT);
		
		final int P_WIDTH = 144, P_HEIGHT = 7;
		final int dayTime = (int)(ClientProxy.MC.level.getDayTime() % 24000L);
		final int barlen = MathUtil.getBarLen(dayTime, 24000, P_WIDTH);
		ClientProxy.MC.gui.blit(stack, (int) (w / sz) - barlen - 7, (int)(h / sz) - HEIGHT + 7, 149 - barlen + 1, 31, barlen, P_HEIGHT);
		
		for(int i = 0; i < count; ++ i) {
			final int time = manager.getWaveTime(i);
			final int waveLen = MathUtil.getBarLen(time, 24000, P_WIDTH);
			if(time > dayTime) {
				ClientProxy.MC.gui.blit(stack, (int) (w / sz) - waveLen - 7, (int)(h / sz) - HEIGHT + 4, 1, 49, 14, 11);
			} else {
				if(manager.getWaveTriggered(i)) {
					ClientProxy.MC.gui.blit(stack, (int) (w / sz) - waveLen - 7, (int)(h / sz) - HEIGHT, 1, 49, 14, 15);
				} else {
					ClientProxy.MC.gui.blit(stack, (int) (w / sz) - waveLen - 7, (int)(h / sz) - HEIGHT, 1, 65, 14, 15);
				}
			}
		}
		
		ClientProxy.MC.gui.blit(stack, (int) (w / sz) - barlen - 11, (int)(h / sz) - HEIGHT + 4, 17, 52, 15, 12);

		stack.popPose();
	}

	public static void renderMission(MatrixStack stack, int w, int h) {
		final MissionManager.MissionType type = MissionManager.getPlayerMission(ClientProxy.MC.player);
		if(type == MissionManager.MissionType.EMPTY){
			return;
		}

		stack.pushPose();

		ClientProxy.MC.getTextureManager().bind(INVASION);

		final int WIDTH = 72, HEIGHT = 24;
		ClientProxy.MC.gui.blit(stack, w - WIDTH, h - HEIGHT - 16, 88, 48, WIDTH, HEIGHT);

		final int stage = PlayerUtil.getResource(ClientProxy.MC.player, Resources.MISSION_STAGE);
		final int now = PlayerUtil.getResource(ClientProxy.MC.player, Resources.MISSION_VALUE);
		final int need = MissionManager.getRequireMissionValue(type, stage);
		final int barLen = MathUtil.getBarLen(now, need, 66);
		ClientProxy.MC.gui.blit(stack, w - WIDTH + 3, h - HEIGHT + 3, 91, 75, barLen, 2);
		
		final String progress = now + "/" + need;
				
		switch(type) {
		case COLLECT_SUN:{
			ClientProxy.MC.gui.blit(stack, w - WIDTH + 3, h - HEIGHT - 14, 40, 48, 8, 8);
			break;
		}
		case KILL:
		case INSTANT_KILL:{
			ClientProxy.MC.gui.blit(stack, w - WIDTH + 3, h - HEIGHT - 14, 48, 48, 8, 8);
			break;
		}
		}
		
		StringUtil.drawScaledString(stack, ClientProxy.MC.font, new TranslationTextComponent("invasion.pvz.mission." + type.toString().toLowerCase(), need).getString(), w - WIDTH + 12, h - HEIGHT - 13, Colors.WHITE, 0.8F);
		StringUtil.drawScaledString(stack, ClientProxy.MC.font, progress, w - WIDTH + 4, h - HEIGHT - 3, Colors.WHITE, 0.7F);

		stack.popPose();
	}
	
	public static void renderTargetAim(MatrixStack stack, int w, int h) {
		stack.pushPose();
		final float scale = 1F;
		stack.scale(scale, scale, 1F);
		ClientProxy.MC.getTextureManager().bind(TARGET);
		final int WIDTH = 32, HEIGHT = 32;
		ClientProxy.MC.gui.blit(stack, (w - WIDTH) / 2 - 0, (h - HEIGHT) / 2 - 0, 0, 0, WIDTH, HEIGHT);
		stack.popPose();
	}

	@SuppressWarnings("deprecation")
	public static void renderFog(MatrixStack stack, int w, int h, float dep) {
		stack.pushPose();
		RenderSystem.enableBlend();
		RenderSystem.color4f(1f, 1f, 1f, dep);
		ClientProxy.MC.getTextureManager().bind(FOG);
		
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuilder();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.vertex(0.0D, h, -90.0D).uv(0.0F, 1.0F).endVertex();
		bufferbuilder.vertex(w, h, -90.0D).uv(1.0F, 1.0F).endVertex();
		bufferbuilder.vertex(w, 0.0D, -90.0D).uv(1.0F, 0.0F).endVertex();
		bufferbuilder.vertex(0.0D, 0.0D, -90.0D).uv(0.0F, 0.0F).endVertex();
		tessellator.end();
		
		RenderSystem.disableBlend();
		stack.popPose();
	}

	/**
	 * {@link #renderResources(MatrixStack, int, int)}
	 */
	private static void renderSunBar(MatrixStack stack, int width, int height) {
		final int max = PlayerUtil.getPlayerMaxSunNum(PlayerUtil.getResource(ClientProxy.MC.player, Resources.TREE_LVL));
		final int now = PlayerUtil.getResource(ClientProxy.MC.player, Resources.SUN_NUM);
		final int len = MathUtil.getBarLen(now, max, SUN_BAR_W2);
		final float sz = 0.7F;
		stack.pushPose();
		RenderSystem.enableBlend();
		stack.scale(sz, sz, 1F);
		ClientProxy.MC.getTextureManager().bind(RESOURCE);
		
		ClientProxy.MC.gui.blit(stack, 0, 0, 0, 0, SUN_BAR_W1, SUN_BAR_H1);
		ClientProxy.MC.gui.blit(stack, 0, 0, 0, 32, 32 + len, SUN_BAR_H1);
		
		StringUtil.drawCenteredScaledString(stack, ClientProxy.MC.font, now + "", 95 + 1, 5 + 1, 6698496, 1.5f);
		StringUtil.drawCenteredScaledString(stack, ClientProxy.MC.font, now + "", 95, 5, Colors.WHITE, 1.5f);
		
		RenderSystem.disableBlend();
		stack.popPose();
	}

	/**
	 * {@link #renderResources(MatrixStack, int, int)}
	 */
	public static void renderMoneyBar(MatrixStack stack, int width, int height) {
		final int now = PlayerUtil.getResource(ClientProxy.MC.player, Resources.MONEY);
		final float sz = 0.7F;
		stack.pushPose();
		RenderSystem.enableBlend();
		stack.scale(sz, sz, 1F);
		ClientProxy.MC.getTextureManager().bind(RESOURCE);
		
		ClientProxy.MC.gui.blit(stack, 0, 0, 0, 96, SUN_BAR_W1, SUN_BAR_H1);
		
		StringUtil.drawCenteredScaledString(stack, ClientProxy.MC.font, now + "", 95 + 1, 5 + 1, 3610880, 1.5f);
		StringUtil.drawCenteredScaledString(stack, ClientProxy.MC.font, now + "", 95, 5, Colors.WHITE, 1.5f);
		
		RenderSystem.disableBlend();
		stack.popPose();
	}
	
	/**
	 * {@link #renderResources(MatrixStack, int, int)}
	 */
	public static void renderGemBar(MatrixStack stack, int width, int height) {
		final int now = PlayerUtil.getResource(ClientProxy.MC.player, Resources.GEM_NUM);
		final float sz = 0.7F;
		stack.pushPose();
		RenderSystem.enableBlend();
		stack.scale(sz, sz, 1F);
		ClientProxy.MC.getTextureManager().bind(RESOURCE);
		
		ClientProxy.MC.gui.blit(stack, 0, 0, 0, 128, SUN_BAR_W1, SUN_BAR_H1);
		StringUtil.drawCenteredScaledString(stack, ClientProxy.MC.font, now + "", 95 + 1, 5, 46545, 1.5f);
		StringUtil.drawCenteredScaledString(stack, ClientProxy.MC.font, now + "", 95, 5, Colors.WHITE, 1.5f);
		
		RenderSystem.disableBlend();
		stack.popPose();
	}
	
	/**
	 * {@link #renderResources(MatrixStack, int, int)}
	 */
	private static void renderTreeLevel(MatrixStack stack, int width, int height) {
		final int level = PlayerUtil.getResource(ClientProxy.MC.player, Resources.TREE_LVL);
		final int max = PlayerUtil.getPlayerLevelUpXp(level);
		final int now = PlayerUtil.getResource(ClientProxy.MC.player, Resources.TREE_XP);
		final int len = MathUtil.getBarLen(now, max, 120);
		final float sz = 0.7F;
		stack.pushPose();
		RenderSystem.enableBlend();
		stack.scale(sz, sz, 1F);
		ClientProxy.MC.getTextureManager().bind(RESOURCE);
		
		ClientProxy.MC.gui.blit(stack, 0, 0, 0, 160, 157, 34);
		ClientProxy.MC.gui.blit(stack, 34, 3, 34, 194, len, 8);
		
		StringUtil.drawCenteredScaledString(stack, ClientProxy.MC.font, "Lv." + level, 52, 18, Colors.WHITE, 1f);
		
		RenderSystem.disableBlend();
		stack.popPose();
	}

}

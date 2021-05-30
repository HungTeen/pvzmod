package com.hungteen.pvz.common.event;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.client.gui.screen.PVZMainMenuScreen;
import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.common.entity.zombie.roof.BungeeZombieEntity;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID, value = Dist.CLIENT)
public class PVZClientEvents {
	
	public static final BlockState ICE_BLOCK = Blocks.FROSTED_ICE.defaultBlockState();
	
	@SubscribeEvent 
	public static void onLivingRender(@SuppressWarnings("rawtypes") RenderLivingEvent.Pre ev) {
		MatrixStack stack = ev.getMatrixStack();
		IRenderTypeBuffer buffer = ev.getBuffers();
		int light = ev.getLight();
//		ev.getMatrixStack().rotate(Vector3f.ZP.rotationDegrees(180F));
		checkBungeeHandStand(ev.getEntity(), stack);
		checkAndRenderFrozenIce(ev.getEntity(), stack, buffer, light);
		checkAndRenderButter(ev.getRenderer(), ev.getEntity(), stack, buffer, light);
	}
	
	@SubscribeEvent
	public static void onGuiOpened(GuiOpenEvent event) {
		if(PVZConfig.CLIENT_CONFIG.OtherSettings.ShowPVZMainMenu.get()) {//show pvz menu
		    if (event.getGui() instanceof MainMenuScreen && ! (event.getGui() instanceof PVZMainMenuScreen)) {
			    event.setGui(new PVZMainMenuScreen());
		    }
		}
	}
	
	private static void checkBungeeHandStand(LivingEntity entity, MatrixStack stack) {
		if(entity instanceof PVZPlantEntity || entity instanceof PVZZombieEntity) return ;
		if(entity.getVehicle() instanceof BungeeZombieEntity) {
			stack.mulPose(Vector3f.ZP.rotationDegrees(180F));
		}
	}
	
	@SuppressWarnings("deprecation")
	private static void checkAndRenderFrozenIce(LivingEntity entity, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		if(! EntityUtil.isEntityValid(entity) || ! EntityUtil.isEntityFrozen(entity)) return ;
		matrixStackIn.pushPose();
		float scale = 0.5F;
		matrixStackIn.scale(scale, scale, scale);
		matrixStackIn.pushPose();
		Minecraft.getInstance().getBlockRenderer().renderSingleBlock(ICE_BLOCK, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
		matrixStackIn.popPose();
		matrixStackIn.pushPose();
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90F));
		Minecraft.getInstance().getBlockRenderer().renderSingleBlock(ICE_BLOCK, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
		matrixStackIn.popPose();
		matrixStackIn.pushPose();
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180F));
		Minecraft.getInstance().getBlockRenderer().renderSingleBlock(ICE_BLOCK, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
		matrixStackIn.popPose();
		matrixStackIn.pushPose();
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(- 90F));
		Minecraft.getInstance().getBlockRenderer().renderSingleBlock(ICE_BLOCK, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
		matrixStackIn.popPose();
		matrixStackIn.popPose();
	}
	
	@SuppressWarnings({ "deprecation", "rawtypes" })
	private static void checkAndRenderButter(LivingRenderer r, LivingEntity entity, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		if(! EntityUtil.isEntityValid(entity) || ! EntityUtil.isEntityButter(entity)) return ;
		matrixStackIn.pushPose();
		float scale = 0.7F;
		matrixStackIn.scale(scale, scale, scale);
		matrixStackIn.translate(- 0.5F, entity.getBbHeight() / scale - 0.5F, - 0.5F);
		Minecraft.getInstance().getBlockRenderer().renderSingleBlock(BlockRegister.BUTTER_BLOCK.get().defaultBlockState(), matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
		matrixStackIn.popPose();
	}
	
}

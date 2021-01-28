package com.hungteen.pvz.event;

import com.hungteen.pvz.PVZConfig;
import com.hungteen.pvz.PVZMod;
import com.hungteen.pvz.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.entity.zombie.roof.BungeeZombieEntity;
import com.hungteen.pvz.gui.screen.PVZMainMenuScreen;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.utils.EntityUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PVZMod.MOD_ID, value = Dist.CLIENT)
public class PVZClientEvents {
	
	public static final BlockState ICE_BLOCK = Blocks.FROSTED_ICE.getDefaultState();
	
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
		if(entity instanceof PVZPlantEntity) return ;
		if(entity.getRidingEntity() instanceof BungeeZombieEntity) {
			stack.rotate(Vector3f.ZP.rotationDegrees(180F));
		}
	}
	
	@SuppressWarnings("deprecation")
	private static void checkAndRenderFrozenIce(LivingEntity entity, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		if(! EntityUtil.isEntityValid(entity) || ! EntityUtil.isEntityFrozen(entity)) return ;
		matrixStackIn.push();
		float scale = 0.5F;
		matrixStackIn.scale(scale, scale, scale);
		matrixStackIn.push();
		Minecraft.getInstance().getBlockRendererDispatcher().renderBlock(ICE_BLOCK, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
		matrixStackIn.pop();
		matrixStackIn.push();
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(90F));
		Minecraft.getInstance().getBlockRendererDispatcher().renderBlock(ICE_BLOCK, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
		matrixStackIn.pop();
		matrixStackIn.push();
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180F));
		Minecraft.getInstance().getBlockRendererDispatcher().renderBlock(ICE_BLOCK, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
		matrixStackIn.pop();
		matrixStackIn.push();
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(- 90F));
		Minecraft.getInstance().getBlockRendererDispatcher().renderBlock(ICE_BLOCK, matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
		matrixStackIn.pop();
		matrixStackIn.pop();
	}
	
	@SuppressWarnings({ "deprecation", "rawtypes" })
	private static void checkAndRenderButter(LivingRenderer r, LivingEntity entity, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		if(! EntityUtil.isEntityValid(entity) || ! EntityUtil.isEntityButter(entity)) return ;
		matrixStackIn.push();
		float scale = 0.7F;
		matrixStackIn.scale(scale, scale, scale);
		matrixStackIn.translate(- 0.5F, entity.getHeight() / scale - 0.5F, - 0.5F);
		Minecraft.getInstance().getBlockRendererDispatcher().renderBlock(BlockRegister.BUTTER_BLOCK.get().getDefaultState(), matrixStackIn, bufferIn, packedLightIn, OverlayTexture.NO_OVERLAY);
		matrixStackIn.pop();
	}
	
}

package com.hungteen.pvzmod.render.layers;

import com.hungteen.pvzmod.entities.plants.base.EntityPlantBase;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.registry.PotionRegister;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class LayerIceBlock implements LayerRenderer<EntityLiving> {
	private static final ResourceLocation SUPER = new ResourceLocation(Reference.MODID + ":" + "textures/entity/layer/ice_armor.png");
	private final RenderLiving livingRenderer;
	private final ModelBase livingModel;

	public LayerIceBlock(RenderLiving RendererIn) {
		this.livingRenderer = RendererIn;
		livingModel = this.livingRenderer.getMainModel();
	}

	public void doRenderLayer(EntityLiving entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if(!((EntityZombieBase) entitylivingbaseIn).getIsFrozen()) {
			return ;
		}
		boolean flag = false;
		GlStateManager.depthMask(!flag);
		this.livingRenderer.bindTexture(SUPER);
		GlStateManager.matrixMode(5890);
		GlStateManager.loadIdentity();
		float f = (float) entitylivingbaseIn.ticksExisted + partialTicks;
		//GlStateManager.translate(f * 0.01F, f * 0.01F, 0.0F);
		GlStateManager.matrixMode(5888);
		GlStateManager.enableBlend();
		float f1 = 0.5F;
		GlStateManager.color(0.5F, 0.5F, 0.5F, 1.0F);
		GlStateManager.enableLighting();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
		Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
		this.livingModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch,
				scale);
		Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
		GlStateManager.matrixMode(5890);
		GlStateManager.loadIdentity();
		GlStateManager.matrixMode(5888);
		GlStateManager.disableLighting();
		GlStateManager.disableBlend();
		GlStateManager.depthMask(flag);
	}

	public boolean shouldCombineTextures() {
		return false;
	}
}
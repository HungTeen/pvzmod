package com.hungteen.pvzmod.render.layers;

import com.hungteen.pvzmod.entities.zombies.EntityZomBoss;
import com.hungteen.pvzmod.render.entities.zombies.RenderZomBoss;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class LayerZombossYellowEyes implements LayerRenderer<EntityZomBoss> {
	private static final ResourceLocation SUPER1 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/layer/zomboss_yellow.png");
	private static final ResourceLocation SUPER2 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/layer/zomboss_red.png");
	private static final ResourceLocation SUPER3 = new ResourceLocation(Reference.MODID + ":" + "textures/entity/layer/zomboss_aqua.png");
	
	private final RenderLiving livingRenderer;
	private final ModelBase livingModel;

	public LayerZombossYellowEyes(RenderZomBoss RendererIn) {
		this.livingRenderer = RendererIn;
		livingModel = this.livingRenderer.getMainModel();
	}

	public void doRenderLayer(EntityZomBoss entity, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		boolean flag = entity.isInvisible();
		GlStateManager.depthMask(!flag);
		if(entity.getBossState()==EntityZomBoss.State.NORMAL) {
		    this.livingRenderer.bindTexture(SUPER1);
		}else if(entity.getBossState()==EntityZomBoss.State.ICE) {
		    this.livingRenderer.bindTexture(SUPER3);
		}else if(entity.getBossState()==EntityZomBoss.State.FLAME) {
		    this.livingRenderer.bindTexture(SUPER2);
		}else {
	        this.livingRenderer.bindTexture(SUPER1);
		}
		GlStateManager.matrixMode(5890);
		GlStateManager.loadIdentity();
		float f = (float) entity.ticksExisted + partialTicks;
		//GlStateManager.translate(f * 0.01F, f * 0.01F, 0.0F);
		GlStateManager.matrixMode(5888);
		GlStateManager.enableBlend();
		float f1 = 0.5F;
		GlStateManager.color(0.5F, 0.5F, 0.5F, 1.0F);
		GlStateManager.disableLighting();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
		Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
		this.livingModel.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch,
				scale);
		Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
		GlStateManager.matrixMode(5890);
		GlStateManager.loadIdentity();
		GlStateManager.matrixMode(5888);
		GlStateManager.enableLighting();
		GlStateManager.disableBlend();
		GlStateManager.depthMask(flag);
	}

	public boolean shouldCombineTextures() {
		return false;
	}
}

package com.hungteen.pvzmod.render.layers;

import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.entities.zombies.normal.EntityNormalZombie;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LayerZombieBeard implements LayerRenderer<EntityNormalZombie> {
	private static final ResourceLocation BEARD = new ResourceLocation(
			Reference.MODID + ":" + "textures/entity/layer/zombie_beard.png");
	private final RenderLiving livingRenderer;
	private final ModelBase livingModel;

	public LayerZombieBeard(RenderLiving RendererIn) {
		this.livingRenderer = RendererIn;
		livingModel = this.livingRenderer.getMainModel();
	}

	public void doRenderLayer(EntityNormalZombie entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		//System.out.println(entitylivingbaseIn.getZombieType());
		if (entitylivingbaseIn.getZombieType() != EntityZombieBase.Type.BEARD)
			return;
		if(((EntityZombieBase) entitylivingbaseIn).getIsInivs()) {//“˛–Œ≤ª‰÷»æ
			return ;
		}
		//System.out.println("1");
		this.livingRenderer.bindTexture(BEARD);
		//GlStateManager.enableBlend();
		//GlStateManager.disableAlpha();
		//GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
		//GlStateManager.disableLighting();
		//GlStateManager.depthMask(!entitylivingbaseIn.isInvisible());
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 61680.0F, 0.0F);
		//GlStateManager.enableLighting();
		//GlStateManager.color(0F, 0F, 0F, 1.0F);
		//Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
		this.livingModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks,
				netHeadYaw, headPitch, scale);
		//Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
		//this.livingRenderer.setLightmap(entitylivingbaseIn);
		//GlStateManager.depthMask(true);
		//GlStateManager.disableBlend();
		//GlStateManager.enableAlpha();
	}

	public boolean shouldCombineTextures() {
		return false;
	}
}
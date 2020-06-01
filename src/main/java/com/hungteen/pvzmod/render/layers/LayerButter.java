package com.hungteen.pvzmod.render.layers;

import java.util.Random;

import com.hungteen.pvzmod.entities.bullets.EntityButter;
import com.hungteen.pvzmod.entities.zombies.base.EntityZombieBase;
import com.hungteen.pvzmod.registry.PotionRegister;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class LayerButter implements LayerRenderer<EntityZombieBase> {

	private final RenderLiving renderer;

	public LayerButter(RenderLiving RendererIn) {
		this.renderer = RendererIn;
	}

	public void doRenderLayer(EntityZombieBase entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if(!entitylivingbaseIn.getIsButter()) {
//			System.out.println(entitylivingbaseIn.isPotionActive(PotionRegister.FROZEN_EFFECT));
			return ;
		}
//		System.out.println("gogogo!");
		Entity entity = new EntityButter(entitylivingbaseIn.world);
		entity.setPosition(entitylivingbaseIn.posX,
				entitylivingbaseIn.posY, entitylivingbaseIn.posZ);
		Random random = new Random((long) entitylivingbaseIn.getEntityId());
		RenderHelper.disableStandardItemLighting();
		
		GlStateManager.pushMatrix();
		ModelRenderer modelrenderer = this.renderer.getMainModel().getRandomModelBox(random);
		ModelBox modelbox = modelrenderer.cubeList.get(random.nextInt(modelrenderer.cubeList.size()));
		modelrenderer.postRender(0.0625F);
		float f = random.nextFloat();
		float f1 = random.nextFloat();
		float f2 = random.nextFloat();
		float f3 = (modelbox.posX1 + (modelbox.posX2 - modelbox.posX1) * f) / 16.0F;
		float f4 = (modelbox.posY1 + (modelbox.posY2 - modelbox.posY1) * f1) / 16.0F;
		float f5 = (modelbox.posZ1 + (modelbox.posZ2 - modelbox.posZ1) * f2) / 16.0F;
		GlStateManager.translate(f3, f4, f5);
		f = f * 2.0F - 1.0F;
		f1 = f1 * 2.0F - 1.0F;
		f2 = f2 * 2.0F - 1.0F;
		f = f * -1.0F;
		f1 = f1 * -1.0F;
		f2 = f2 * -1.0F;
		float f6 = MathHelper.sqrt(f * f + f2 * f2);
		entity.rotationYaw = (float) (Math.atan2((double) f, (double) f2) * (180D / Math.PI));
		entity.rotationPitch = (float) (Math.atan2((double) f1, (double) f6) * (180D / Math.PI));
		entity.prevRotationYaw = entity.rotationYaw;
		entity.prevRotationPitch = entity.rotationPitch;
		double d0 = 0.0D;
		double d1 = 0.0D;
		double d2 = 0.0D;
		this.renderer.getRenderManager().renderEntity(entity, 0.0D, 0.0D, 0.0D, 0.0F, partialTicks, false);
		GlStateManager.popMatrix();

		RenderHelper.enableStandardItemLighting();
	}

	public boolean shouldCombineTextures() {
		return false;
	}
}

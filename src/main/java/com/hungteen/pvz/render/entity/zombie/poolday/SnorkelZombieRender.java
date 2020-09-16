package com.hungteen.pvz.render.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.poolday.SnorkelZombieEntity;
import com.hungteen.pvz.model.entity.zombie.poolday.SnorkelZombieModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SnorkelZombieRender extends PVZZombieRender<SnorkelZombieEntity>{

	public SnorkelZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new SnorkelZombieModel(), 0.5f);
	}

	@Override
	protected void preRenderCallback(SnorkelZombieEntity entitylivingbaseIn, MatrixStack matrixStackIn,
			float partialTickTime) {
		super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
		matrixStackIn.translate(0, -2f, 0);
	}
	
	@Override
	protected float getScaleByEntity(SnorkelZombieEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getEntityTexture(SnorkelZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/poolday/snorkel_zombie.png");
	}

}

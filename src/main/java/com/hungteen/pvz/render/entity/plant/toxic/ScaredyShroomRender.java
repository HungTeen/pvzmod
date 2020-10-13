package com.hungteen.pvz.render.entity.plant.toxic;

import com.hungteen.pvz.entity.plant.toxic.ScaredyShroomEntity;
import com.hungteen.pvz.model.entity.plant.toxic.ScaredyShroomModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ScaredyShroomRender extends PVZPlantRender<ScaredyShroomEntity>{

	public ScaredyShroomRender(EntityRendererManager rendererManager) {
		super(rendererManager, new ScaredyShroomModel(), 0.4f);
	}

	@Override
	protected void preRenderCallback(ScaredyShroomEntity entity, MatrixStack matrixStackIn,
			float partialTickTime) {
		float sz = this.getScaleByEntity(entity);
		matrixStackIn.scale(sz, sz, sz);
		double percent = entity.getScareTime() * 1.0 / ScaredyShroomEntity.ANIM_TIME;
		double change = 1.38;
		matrixStackIn.translate(0, change * percent, 0);
	}
	
	@Override
	protected float getScaleByEntity(ScaredyShroomEntity entity) {
		return 0.7f;
	}

	@Override
	public ResourceLocation getEntityTexture(ScaredyShroomEntity entity) {
		return StringUtil.prefix("textures/entity/plant/toxic/scaredy_shroom.png");
	}

}

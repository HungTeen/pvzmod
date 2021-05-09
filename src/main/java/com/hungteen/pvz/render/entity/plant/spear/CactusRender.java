package com.hungteen.pvz.render.entity.plant.spear;

import com.hungteen.pvz.entity.plant.spear.CactusEntity;
import com.hungteen.pvz.model.entity.plant.spear.CactusModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CactusRender extends PVZPlantRender<CactusEntity> {

	public CactusRender(EntityRendererManager rendererManager) {
		super(rendererManager, new CactusModel(), 0.5F);
	}

	@Override
	protected void scale(CactusEntity entitylivingbaseIn, MatrixStack matrixStackIn,
			float partialTickTime) {
		super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
//		float hScale = 1F;
//		matrixStackIn.scale(1, hScale, 1);
	}
	
	@Override
	public float getScaleByEntity(CactusEntity entity) {
		return 1F;
	}
	
	@Override
	public Vector3d getTranslateVec(CactusEntity entity) {
		float h = entity.getCactusHeight();
		return new Vector3d(0, - h, 0);
	}

	@Override
	public ResourceLocation getTextureLocation(CactusEntity entity) {
		if(entity.isCactusPowered()) return StringUtil.prefix("textures/entity/plant/spear/power_cactus.png");
		return StringUtil.prefix("textures/entity/plant/spear/cactus.png");
	}

}

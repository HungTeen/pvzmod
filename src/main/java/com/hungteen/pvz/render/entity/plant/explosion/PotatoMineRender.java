package com.hungteen.pvz.render.entity.plant.explosion;

import com.hungteen.pvz.entity.plant.explosion.PotatoMineEntity;
import com.hungteen.pvz.model.entity.plant.explosion.PotatoMineModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PotatoMineRender extends PVZPlantRender<PotatoMineEntity>{

	public PotatoMineRender(EntityRendererManager rendererManager) {
		super(rendererManager, new PotatoMineModel() ,0.3f);
	}

	@Override
	protected void preRenderCallback(PotatoMineEntity entitylivingbaseIn, MatrixStack matrixStackIn,
			float partialTickTime) {
		float sz = this.getScaleByEntity(entitylivingbaseIn);
		matrixStackIn.scale(sz,sz,sz);
		if(!entitylivingbaseIn.isMineReady()) {
			matrixStackIn.translate(0, 0.6f, 0);
		}
	}
	
	@Override
	public ResourceLocation getEntityTexture(PotatoMineEntity entity) {
		if(entity.sign_red) return StringUtil.prefix("textures/entity/plant/explosion/potato_mine2.png");
		return StringUtil.prefix("textures/entity/plant/explosion/potato_mine1.png");
	}

	@Override
	protected float getScaleByEntity(PotatoMineEntity entity) {
		return 0.6f;
	}

}

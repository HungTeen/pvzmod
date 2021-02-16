package com.hungteen.pvz.render.entity.plant.explosion;

import com.hungteen.pvz.entity.plant.explosion.BambooLordEntity;
import com.hungteen.pvz.model.entity.plant.explosion.BambooLordModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BambooLordRender extends PVZPlantRender<BambooLordEntity> {

	private static final ResourceLocation BAMBOO_LORD_TEX = StringUtil.prefix("textures/entity/plant/explosion/bamboo_lord.png");
	
	public BambooLordRender(EntityRendererManager rendererManager) {
		super(rendererManager, new BambooLordModel(), 0.4F);
	}

	@Override
	public float getScaleByEntity(BambooLordEntity entity) {
		return 1F;
	}

	@Override
	public ResourceLocation getEntityTexture(BambooLordEntity entity) {
		return BAMBOO_LORD_TEX;
	}

}

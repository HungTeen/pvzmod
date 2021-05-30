package com.hungteen.pvz.client.render.entity.plant.magic;

import com.hungteen.pvz.client.model.entity.plant.magic.ImitaterModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.magic.ImitaterEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ImitaterRender extends PVZPlantRender<ImitaterEntity> {

	private static final ResourceLocation IMITATER_TEX = StringUtil.prefix("textures/entity/plant/magic/imitater.png");
	
	
	public ImitaterRender(EntityRendererManager rendererManager) {
		super(rendererManager, new ImitaterModel(), 0);
	}

	@Override
	public float getScaleByEntity(ImitaterEntity entity) {
		return 0.9F;
	}

	@Override
	public ResourceLocation getTextureLocation(ImitaterEntity entity) {
		return IMITATER_TEX;
	}

}

package com.hungteen.pvz.render.entity.plant.explosion;

import com.hungteen.pvz.entity.plant.explosion.DoomShroomEntity;
import com.hungteen.pvz.model.entity.plant.explosion.DoomShroomModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DoomShroomRender extends PVZPlantRender<DoomShroomEntity>{

	public DoomShroomRender(EntityRendererManager rendererManager) {
		super(rendererManager, new DoomShroomModel(), 0.5f);
	}

	@Override
	public float getScaleByEntity(DoomShroomEntity entity) {
		float plus = entity.getAttackTime() * 1f / entity.getReadyTime();
		return 1f + plus * plus * 0.5f;
	}

	@Override
	public ResourceLocation getTextureLocation(DoomShroomEntity entity) {
		return StringUtil.prefix("textures/entity/plant/explosion/doom_shroom.png");
	}

}

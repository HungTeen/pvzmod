package com.hungteen.pvz.render.entity.plant.toxic;

import com.hungteen.pvz.entity.plant.toxic.ScaredyShroomEntity;
import com.hungteen.pvz.model.entity.plant.toxic.ScaredyShroomModel;
import com.hungteen.pvz.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ScaredyShroomRender extends PVZPlantRender<ScaredyShroomEntity>{

	public ScaredyShroomRender(EntityRendererManager rendererManager) {
		super(rendererManager, new ScaredyShroomModel(), 0.4f);
	}

	@Override
	public Vector3d getTranslateVec(ScaredyShroomEntity entity) {
		double percent = entity.getScareTime() * 1.0 / ScaredyShroomEntity.ANIM_TIME;
		double change = 1.38;
		return new Vector3d(0, change * percent, 0);
	}
	
	@Override
	public float getScaleByEntity(ScaredyShroomEntity entity) {
		return 0.7f;
	}

	@Override
	public ResourceLocation getTextureLocation(ScaredyShroomEntity entity) {
		return StringUtil.prefix("textures/entity/plant/toxic/scaredy_shroom.png");
	}

}

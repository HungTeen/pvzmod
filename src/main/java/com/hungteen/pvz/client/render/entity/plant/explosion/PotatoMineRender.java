package com.hungteen.pvz.client.render.entity.plant.explosion;

import com.hungteen.pvz.client.model.entity.plant.explosion.PotatoMineModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.explosion.PotatoMineEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PotatoMineRender extends PVZPlantRender<PotatoMineEntity>{

	public PotatoMineRender(EntityRendererManager rendererManager) {
		super(rendererManager, new PotatoMineModel(), 0.3F);
	}

	@Override
	public float getScaleByEntity(PotatoMineEntity entity) {
		final float sz = super.getScaleByEntity(entity);
		if(entity.isMineReady()) {
			final float scale = 0.15F;
			return sz + entity.getAttackTime() * scale / entity.getAttackCD();
		}
		return sz;
	}
	
	@Override
	public Vector3d getTranslateVec(PotatoMineEntity entity) {
		final float offsetY = 0.6F;
		if(entity.isRisingFromDirt()) {
			final int time = entity.getPrepareCD() - entity.getExistTick();
			return new Vector3d(0, time * offsetY / PotatoMineEntity.RISING_ANIM_CD, 0);
		}
		return entity.isMineReady() ? new Vector3d(0, 0, 0) : new Vector3d(0, offsetY, 0);
	}
	
}

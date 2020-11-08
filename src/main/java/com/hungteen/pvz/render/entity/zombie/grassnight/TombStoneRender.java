package com.hungteen.pvz.render.entity.zombie.grassnight;

import com.hungteen.pvz.entity.zombie.grassnight.TombStoneEntity;
import com.hungteen.pvz.model.entity.zombie.grassnight.TombStoneModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TombStoneRender extends PVZZombieRender<TombStoneEntity>{

	public TombStoneRender(EntityRendererManager rendererManager) {
		super(rendererManager, new TombStoneModel(), 0.4f);
	}

	@Override
	protected float getScaleByEntity(TombStoneEntity entity) {
		return 0.6f;
	}
	
	@Override
	public Vec3d getTranslateVec(TombStoneEntity entity) {
		float height = 1f;
		float downOffset = (1 - entity.getSpawnTick() * 1.0f / TombStoneEntity.SPAWN_TIME) * height;
		return new Vec3d(0, downOffset, 0);
	}

	@Override
	public ResourceLocation getEntityTexture(TombStoneEntity entity) {
		double hp = entity.getHealth();
		double max = entity.getMaxHealth();
		if(hp <= max / 3) return StringUtil.prefix("textures/entity/zombie/grassnight/tomb_stone3.png");
		if(hp <= max * 2 / 3) return StringUtil.prefix("textures/entity/zombie/grassnight/tomb_stone2.png");
		return StringUtil.prefix("textures/entity/zombie/grassnight/tomb_stone1.png");
	}

}

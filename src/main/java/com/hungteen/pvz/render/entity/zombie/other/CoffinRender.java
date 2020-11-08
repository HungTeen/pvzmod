package com.hungteen.pvz.render.entity.zombie.other;

import com.hungteen.pvz.entity.zombie.other.CoffinEntity;
import com.hungteen.pvz.model.entity.zombie.other.CoffinModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CoffinRender extends PVZZombieRender<CoffinEntity>{

	public CoffinRender(EntityRendererManager rendererManager) {
		super(rendererManager, new CoffinModel(), 0f);
	}

	@Override
	protected float getScaleByEntity(CoffinEntity entity) {
		return 0.5f;
	}
	
	@Override
	public Vec3d getTranslateVec(CoffinEntity entity) {
		float height = 2.5f;
		float downOffset = (1 - entity.getSpawnTick() * 1.0f / CoffinEntity.SPAWN_TIME) * height;
		return new Vec3d(0, downOffset, 0);
	}

	@Override
	public ResourceLocation getEntityTexture(CoffinEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/other/coffin.png");
	}

}

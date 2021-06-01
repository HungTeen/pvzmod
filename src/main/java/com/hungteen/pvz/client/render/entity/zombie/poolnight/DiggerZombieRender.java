package com.hungteen.pvz.client.render.entity.zombie.poolnight;

import com.hungteen.pvz.client.model.entity.zombie.poolnight.DiggerZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.OldPVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.poolnight.DiggerZombieEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DiggerZombieRender extends OldPVZZombieRender<DiggerZombieEntity>{

	public DiggerZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new DiggerZombieModel(), 0.3f);
	}

	@Override
	protected float getScaleByEntity(DiggerZombieEntity entity) {
		if(entity.isMiniZombie()) return 0.15F;
		return 0.5f;
	}

	@Override
	public Vector3d getTranslateVec(DiggerZombieEntity entity) {
		double maxOffset = 4D;
		int now = entity.getAttackTime();
		int max = DiggerZombieEntity.MAX_OUT_TIME;
		return new Vector3d(0, maxOffset * ( 1 - now * 1.0D / max), 0);
	}
	
	@Override
	public ResourceLocation getTextureLocation(DiggerZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/poolnight/digger_zombie.png");
	}

}

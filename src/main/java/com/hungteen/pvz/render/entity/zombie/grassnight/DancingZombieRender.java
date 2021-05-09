package com.hungteen.pvz.render.entity.zombie.grassnight;

import com.hungteen.pvz.entity.zombie.grassnight.DancingZombieEntity;
import com.hungteen.pvz.model.entity.zombie.grassnight.DancingZombieModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.render.layer.DancerLightLayer;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DancingZombieRender extends PVZZombieRender<DancingZombieEntity>{

	public DancingZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new DancingZombieModel(), 0.5f);
	}

	@Override
	protected void addZombieLayers() {
		super.addZombieLayers();
		this.addLayer(new DancerLightLayer<>(this));
	}
	
	@Override
	protected float getScaleByEntity(DancingZombieEntity entity) {
		if(entity.isMiniZombie()) return 0.15F;
		return 0.5f;
	}

	@Override
	public ResourceLocation getTextureLocation(DancingZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/grassnight/dancing_zombie.png");
	}

}

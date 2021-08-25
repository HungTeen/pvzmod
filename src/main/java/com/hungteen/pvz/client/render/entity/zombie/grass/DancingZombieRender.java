package com.hungteen.pvz.client.render.entity.zombie.grass;

import com.hungteen.pvz.client.model.entity.zombie.grass.DancingZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.client.render.layer.DancerLightLayer;
import com.hungteen.pvz.common.entity.zombie.grass.DancingZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
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
	
}

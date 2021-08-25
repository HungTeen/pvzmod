package com.hungteen.pvz.client.render.entity.zombie.roof;

import com.hungteen.pvz.client.model.entity.zombie.roof.GargantuarModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.roof.GigaGargantuarEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GigaGargantuarRender extends PVZZombieRender<GigaGargantuarEntity> {

	public GigaGargantuarRender(EntityRendererManager rendererManager) {
		super(rendererManager, new GargantuarModel<>(), 1F);
	}

}

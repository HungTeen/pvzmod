package com.hungteen.pvz.client.render.entity.zombie.poolday;

import com.hungteen.pvz.client.model.entity.zombie.poolday.SnorkelZombieModel;
import com.hungteen.pvz.common.entity.zombie.poolday.SnorkelZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SnorkelZombieRender extends AbstractSwimmerRender<SnorkelZombieEntity>{

	public SnorkelZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new SnorkelZombieModel(), 0.5f);
	}
	
}

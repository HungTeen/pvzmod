package com.hungteen.pvz.client.render.entity.zombie.grassnight;

import com.hungteen.pvz.client.model.entity.zombie.grassnight.OldZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.grassnight.OldZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OldZombieRender extends PVZZombieRender<OldZombieEntity>{

	public OldZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new OldZombieModel(), 0.5F);
	}

}

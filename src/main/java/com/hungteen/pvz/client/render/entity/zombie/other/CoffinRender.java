package com.hungteen.pvz.client.render.entity.zombie.other;

import com.hungteen.pvz.client.model.entity.zombie.other.CoffinModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.other.CoffinEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CoffinRender extends PVZZombieRender<CoffinEntity>{

	public CoffinRender(EntityRendererManager rendererManager) {
		super(rendererManager, new CoffinModel(), 0f);
	}

	@Override
	protected float getOffsetRisingHeight() {
		return 2.5f;
	}

}

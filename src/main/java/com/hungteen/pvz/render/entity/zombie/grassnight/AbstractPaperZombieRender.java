package com.hungteen.pvz.render.entity.zombie.grassnight;

import com.hungteen.pvz.entity.zombie.grassnight.NewspaperZombieEntity;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractPaperZombieRender<T extends NewspaperZombieEntity> extends PVZZombieRender<T>{

	public AbstractPaperZombieRender(EntityRendererManager rendererManager, EntityModel<T> entityModelIn) {
		super(rendererManager, entityModelIn, 0.5f);
	}

	@Override
	protected float getScaleByEntity(T entity) {
		if(entity.isMiniZombie()) return 0.15F;
		return 0.5f;
	}

}

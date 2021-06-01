package com.hungteen.pvz.client.render.entity.zombie.grassnight;

import com.hungteen.pvz.client.render.entity.zombie.OldPVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.grassnight.NewspaperZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractPaperZombieRender<T extends NewspaperZombieEntity> extends OldPVZZombieRender<T>{

	public AbstractPaperZombieRender(EntityRendererManager rendererManager, EntityModel<T> entityModelIn) {
		super(rendererManager, entityModelIn, 0.5f);
	}

	@Override
	protected float getScaleByEntity(T entity) {
		if(entity.isMiniZombie()) return 0.15F;
		return 0.5f;
	}

}

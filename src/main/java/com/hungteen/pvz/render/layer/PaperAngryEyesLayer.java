package com.hungteen.pvz.render.layer;

import com.hungteen.pvz.entity.zombie.grassnight.NewspaperZombieEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;

public class PaperAngryEyesLayer<T extends NewspaperZombieEntity> extends PVZFullSkinLayer<T, EntityModel<T>>{

	public PaperAngryEyesLayer(IEntityRenderer<T, EntityModel<T>> entityRendererIn) {
		super(entityRendererIn);
	}

	@Override
	protected boolean canRender(T entity) {
		return entity.isAngry();
	}

	@Override
	protected ResourceLocation getResourceLocation(T entity) {
		return StringUtil.prefix("textures/entity/layer/paper_angry.png");
	}

}

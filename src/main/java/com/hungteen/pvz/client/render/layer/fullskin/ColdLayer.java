package com.hungteen.pvz.client.render.layer.fullskin;

import com.hungteen.pvz.utils.EntityUtil;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ColdLayer<T extends LivingEntity, M extends EntityModel<T>> extends PVZFullSkinLayer<T,M>{


	public ColdLayer(IEntityRenderer<T, M> entityRendererIn) {
		super(entityRendererIn);
	}

	@Override
	protected boolean canRender(T entity) {
		if(entity.isInvisible()) return false;
		return EntityUtil.isEntityCold(entity);
	}

	@Override
	protected ResourceLocation getResourceLocation(T entity) {
		return StringUtil.prefix("textures/entity/layer/cold_layer.png");
	}

}

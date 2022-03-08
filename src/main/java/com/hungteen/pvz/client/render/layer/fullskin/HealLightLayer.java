package com.hungteen.pvz.client.render.layer.fullskin;

import com.hungteen.pvz.common.entity.plant.appease.AngelStarFruitEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HealLightLayer<T extends LivingEntity, M extends EntityModel<T>> extends PVZFullSkinLayer<T,M>{

	public HealLightLayer(IEntityRenderer<T, M> entityRendererIn) {
		super(entityRendererIn);
	}

	@Override
	protected boolean canRender(T entity) {
		if(entity instanceof AngelStarFruitEntity && ((AngelStarFruitEntity) entity).lightTick > 0) {
			return true;
		}
		return false;
	}

	@Override
	protected ResourceLocation getResourceLocation(T entity) {
		return StringUtil.prefix("textures/entity/layer/heal_light.png");
	}

}

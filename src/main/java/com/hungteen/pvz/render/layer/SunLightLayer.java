package com.hungteen.pvz.render.layer;

import com.hungteen.pvz.entity.plant.light.SunFlowerEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SunLightLayer<T extends LivingEntity, M extends EntityModel<T>> extends PVZFullSkinLayer<T,M>{

	public SunLightLayer(IEntityRenderer<T, M> entityRendererIn) {
		super(entityRendererIn);
	}

	@Override
	protected boolean canRender(T entity) {
		if(entity instanceof SunFlowerEntity) {
			return ((SunFlowerEntity) entity).getIsGenTime();
		}
		return false;
	}

	@Override
	protected ResourceLocation getResourceLocation(T entity) {
		return StringUtil.prefix("textures/entity/layer/sun_light.png");
	}

}

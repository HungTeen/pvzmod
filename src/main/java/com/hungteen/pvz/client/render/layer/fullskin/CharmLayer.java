package com.hungteen.pvz.client.render.layer.fullskin;

import com.hungteen.pvz.common.entity.plant.PVZPlantEntity;
import com.hungteen.pvz.common.entity.plant.magic.HypnoShroomEntity;
import com.hungteen.pvz.common.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

public class CharmLayer <T extends LivingEntity, M extends EntityModel<T>> extends PVZFullSkinLayer<T,M>{

	public CharmLayer(IEntityRenderer<T, M> entityRendererIn) {
		super(entityRendererIn);
	}

	@Override
	protected boolean canRender(T entity) {
		if(entity.isInvisible()) return false;
		if(entity instanceof HypnoShroomEntity) {
			return ((HypnoShroomEntity) entity).isPlantInSuperMode();
		}
	    if(entity instanceof PVZZombieEntity) {
	    	return ((PVZZombieEntity) entity).isCharmed();
	    }
	    if(entity instanceof PVZPlantEntity) {
	    	return ((PVZPlantEntity) entity).isCharmed();
	    }
		return false;
	}
	
	@Override
	protected float getU(float f) {
		return 0.02f * f;
	}
	
	@Override
	protected float getV(float f) {
		return 0.02f * f;
	}

	@Override
	protected ResourceLocation getResourceLocation(T entity) {
		return StringUtil.prefix("textures/entity/layer/charm.png");
	}

}

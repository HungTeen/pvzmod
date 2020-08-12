package com.hungteen.pvz.render.layer;

import com.hungteen.pvz.entity.zombie.PVZZombieEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ColdLayer<T extends LivingEntity> extends PVZFullSkinLayer<T>{


	public ColdLayer(IEntityRenderer<T, EntityModel<T>> entityRendererIn) {
		super(entityRendererIn);
	}

	@Override
	protected boolean canRender(LivingEntity entity) {
		if(entity instanceof PVZZombieEntity) {
			PVZZombieEntity zombie = (PVZZombieEntity) entity;
			if(zombie.getIsFrozen()||zombie.getIsInivs()) return false;//frozen or invis not render
			if(zombie.getIsCold()) return true;
		}
		return false;
	}

	@Override
	protected ResourceLocation getResourceLocation(LivingEntity entity) {
		return StringUtil.prefix("textures/entity/layer/cold_layer.png");
	}

}

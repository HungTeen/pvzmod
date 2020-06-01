package com.hungteen.pvzmod.render.entities.plants.magic;

import com.hungteen.pvzmod.entities.plants.magic.EntityCoffeeBean;
import com.hungteen.pvzmod.model.entities.plants.magic.ModelCoffeeBean;
import com.hungteen.pvzmod.render.entities.plants.RenderPlantBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderCoffeeBean extends RenderPlantBase<EntityCoffeeBean>{

	private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/plant/magic/coffee_bean.png");
	
	public RenderCoffeeBean(RenderManager renderManager) {
		super(renderManager, new ModelCoffeeBean(), 0.2f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCoffeeBean entity) {
		return TEXTURE;
	}

}

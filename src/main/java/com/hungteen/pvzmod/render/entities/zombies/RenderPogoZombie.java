package com.hungteen.pvzmod.render.entities.zombies;

import com.hungteen.pvzmod.entities.zombies.EntityPogoZombie;
import com.hungteen.pvzmod.model.entities.zombies.ModelPogoZombie;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderPogoZombie extends RenderZombieBase<EntityPogoZombie>{

	private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/pole/pogo_zombie.png");
	
	public RenderPogoZombie(RenderManager renderManager) {
		super(renderManager, new ModelPogoZombie(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPogoZombie entity) {
		return TEXTURE;
	}

}

package com.hungteen.pvzmod.render.entities.zombies.poolnight;

import com.hungteen.pvzmod.entities.zombies.poolnight.EntityDiggerZombie;
import com.hungteen.pvzmod.model.entities.zombies.poolnight.ModelDiggerZombie;
import com.hungteen.pvzmod.render.entities.zombies.RenderZombieBase;
import com.hungteen.pvzmod.util.Reference;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderDiggerZombie extends RenderZombieBase<EntityDiggerZombie>{

	private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID + ":" + "textures/entity/zombie/pool_night/digger_zombie.png");
	
	public RenderDiggerZombie(RenderManager renderManager) {
		super(renderManager, new ModelDiggerZombie(), 0.5f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityDiggerZombie entity) {
		return TEXTURE;
	}

}


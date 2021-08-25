package com.hungteen.pvz.client.render.entity.zombie.roof;

import com.hungteen.pvz.client.model.entity.zombie.roof.Edgar090505Model;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.roof.Edgar090505Entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class Edgar090505Render extends PVZZombieRender<Edgar090505Entity> {

	public Edgar090505Render(EntityRendererManager rendererManager) {
		super(rendererManager, new Edgar090505Model(), 0);
	}
	
}

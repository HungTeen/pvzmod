package com.hungteen.pvzmod.render.entities.zombies;

import com.hungteen.pvzmod.render.layers.LayerGargantuarSadEyes;

import net.minecraft.client.renderer.entity.RenderManager;

public class RenderSadGargantuar extends RenderGargantuar{

	public RenderSadGargantuar(RenderManager renderManager) {
		super(renderManager);
		this.addLayer(new LayerGargantuarSadEyes(this));
	}

}

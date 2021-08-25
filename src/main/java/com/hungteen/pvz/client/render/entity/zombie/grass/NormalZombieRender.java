package com.hungteen.pvz.client.render.entity.zombie.grass;

import com.hungteen.pvz.client.model.entity.zombie.grass.NormalZombieModel;
import com.hungteen.pvz.common.entity.zombie.grass.NormalZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NormalZombieRender extends AbstractNormalRender<NormalZombieEntity>{

	public NormalZombieRender(EntityRendererManager rendererManager){
		super(rendererManager, new NormalZombieModel(), 0.5f);
	}

}

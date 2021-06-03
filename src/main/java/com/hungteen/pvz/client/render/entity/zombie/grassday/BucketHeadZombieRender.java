package com.hungteen.pvz.client.render.entity.zombie.grassday;

import com.hungteen.pvz.client.model.entity.zombie.grassday.BucketHeadZombieModel;
import com.hungteen.pvz.common.entity.zombie.grassday.BucketHeadZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BucketHeadZombieRender extends AbstractNormalRender<BucketHeadZombieEntity>{

	public BucketHeadZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new BucketHeadZombieModel() ,0.5f);
	}

}


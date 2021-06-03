package com.hungteen.pvz.client.render.entity.zombie.grassnight;

import com.hungteen.pvz.client.model.entity.zombie.grassnight.BackupDancerModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.grassnight.BackupDancerEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BackupDancerRender extends PVZZombieRender<BackupDancerEntity>{

	public BackupDancerRender(EntityRendererManager rendererManager) {
		super(rendererManager, new BackupDancerModel(), 0.5f);
	}

}

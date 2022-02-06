package com.hungteen.pvz.client.render.entity.zombie.roof;

import com.hungteen.pvz.client.model.entity.zombie.roof.EdgarRobotModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.base.EdgarRobotEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class EdgarRobotRender<T extends EdgarRobotEntity> extends PVZZombieRender<T> {

	public EdgarRobotRender(EntityRendererManager rendererManager) {
		super(rendererManager, new EdgarRobotModel(), 0);
	}
	
}

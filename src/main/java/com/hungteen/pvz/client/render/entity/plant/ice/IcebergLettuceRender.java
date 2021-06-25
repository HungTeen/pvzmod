package com.hungteen.pvz.client.render.entity.plant.ice;

import com.hungteen.pvz.client.model.entity.plant.ice.IcebergLettuceModel;
import com.hungteen.pvz.client.render.entity.plant.PVZPlantRender;
import com.hungteen.pvz.common.entity.plant.ice.IcebergLettuceEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IcebergLettuceRender extends PVZPlantRender<IcebergLettuceEntity> {

	public IcebergLettuceRender(EntityRendererManager rendererManager) {
		super(rendererManager, new IcebergLettuceModel(), 0.3F);
	}

}

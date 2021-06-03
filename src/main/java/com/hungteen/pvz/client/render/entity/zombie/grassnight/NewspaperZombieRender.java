package com.hungteen.pvz.client.render.entity.zombie.grassnight;

import com.hungteen.pvz.client.model.entity.zombie.grassnight.NewspaperZombieModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.grassnight.NewspaperZombieEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NewspaperZombieRender extends PVZZombieRender<NewspaperZombieEntity>{
	
	public NewspaperZombieRender(EntityRendererManager rendererManager){
		super(rendererManager, new NewspaperZombieModel(), 0.5F);
	}

}

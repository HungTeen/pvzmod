package com.hungteen.pvz.render.entity.zombie.grassnight;

import com.hungteen.pvz.entity.zombie.grassnight.NewspaperZombieEntity;
import com.hungteen.pvz.model.entity.zombie.grassnight.NewspaperZombieModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NewspaperZombieRender extends PVZZombieRender<NewspaperZombieEntity>{

	public NewspaperZombieRender(EntityRendererManager rendererManager){
		super(rendererManager, new NewspaperZombieModel(), 0.5f);
	}

	@Override
	protected float getScaleByEntity(NewspaperZombieEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getEntityTexture(NewspaperZombieEntity entity) {
		if(entity.shouldAngry()) {
			return StringUtil.prefix("textures/entity/zombie/grassnight/newspaper_zombie2.png");
		}else {
			return StringUtil.prefix("textures/entity/zombie/grassnight/newspaper_zombie1.png");
		}
	}

}

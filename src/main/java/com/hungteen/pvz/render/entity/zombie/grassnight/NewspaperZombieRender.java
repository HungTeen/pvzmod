package com.hungteen.pvz.render.entity.zombie.grassnight;

import com.hungteen.pvz.entity.zombie.grassnight.NewspaperZombieEntity;
import com.hungteen.pvz.model.entity.zombie.grassnight.NewspaperZombieModel;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NewspaperZombieRender extends AbstractPaperZombieRender<NewspaperZombieEntity>{

	public NewspaperZombieRender(EntityRendererManager rendererManager){
		super(rendererManager, new NewspaperZombieModel());
	}

	@Override
	public ResourceLocation getTextureLocation(NewspaperZombieEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/grassnight/newspaper_zombie.png");
	}

}

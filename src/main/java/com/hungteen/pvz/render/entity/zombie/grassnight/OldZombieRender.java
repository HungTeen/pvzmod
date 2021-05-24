package com.hungteen.pvz.render.entity.zombie.grassnight;

import com.hungteen.pvz.entity.zombie.grassnight.OldZombieEntity;
import com.hungteen.pvz.model.entity.zombie.grassnight.OldZombieModel;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OldZombieRender extends AbstractPaperZombieRender<OldZombieEntity>{

	public static final ResourceLocation TEX = StringUtil.prefix("textures/entity/zombie/grassnight/old_zombie.png");
	
	public OldZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new OldZombieModel());
	}

	@Override
	public ResourceLocation getTextureLocation(OldZombieEntity entity) {
		return TEX;
	}

}

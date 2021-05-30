package com.hungteen.pvz.client.render.entity.zombie.grassnight;

import com.hungteen.pvz.client.model.entity.zombie.grassnight.SundayEditionZombieModel;
import com.hungteen.pvz.common.entity.zombie.grassnight.SundayEditionZombieEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SundayEditionZombieRender extends AbstractPaperZombieRender<SundayEditionZombieEntity>{

	public static final ResourceLocation TEX = StringUtil.prefix("textures/entity/zombie/grassnight/sunday_edition_zombie.png");
	
	public SundayEditionZombieRender(EntityRendererManager rendererManager) {
		super(rendererManager, new SundayEditionZombieModel());
	}

	@Override
	public ResourceLocation getTextureLocation(SundayEditionZombieEntity entity) {
		return TEX;
	}

	
}

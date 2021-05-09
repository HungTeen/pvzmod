package com.hungteen.pvz.render.entity.zombie.roof;

import com.hungteen.pvz.entity.zombie.roof.ImpEntity;
import com.hungteen.pvz.model.entity.zombie.roof.ImpModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ImpRender extends PVZZombieRender<ImpEntity> {

	private static final ResourceLocation IMP_TEX = StringUtil.prefix("textures/entity/zombie/roof/imp.png");
	
	public ImpRender(EntityRendererManager rendererManager) {
		super(rendererManager, new ImpModel(), 0.3F);
	}

	@Override
	protected float getScaleByEntity(ImpEntity entity) {
		if(entity.isMiniZombie()) return 0.2F;
		return 0.5F;
	}

	@Override
	public ResourceLocation getTextureLocation(ImpEntity entity) {
		return IMP_TEX;
	}

}

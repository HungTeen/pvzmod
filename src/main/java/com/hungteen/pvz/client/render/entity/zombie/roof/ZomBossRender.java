package com.hungteen.pvz.client.render.entity.zombie.roof;

import com.hungteen.pvz.client.model.entity.zombie.roof.ZomBossModel;
import com.hungteen.pvz.client.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.common.entity.zombie.roof.ZomBossEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZomBossRender extends PVZZombieRender<ZomBossEntity> {

	private static final ResourceLocation ZOMBOSS_TEX = StringUtil.prefix("textures/entity/zombie/roof/zomboss.png");
	
	public ZomBossRender(EntityRendererManager rendererManager) {
		super(rendererManager, new ZomBossModel(), 0);
	}
	
	@Override
	protected float getScaleByEntity(ZomBossEntity entity) {
		return 2F;
	}

	@Override
	public ResourceLocation getTextureLocation(ZomBossEntity entity) {
		return ZOMBOSS_TEX;
	}

}

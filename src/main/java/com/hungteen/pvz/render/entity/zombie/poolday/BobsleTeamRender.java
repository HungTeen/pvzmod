package com.hungteen.pvz.render.entity.zombie.poolday;

import com.hungteen.pvz.entity.zombie.poolday.BobsleTeamEntity;
import com.hungteen.pvz.model.entity.zombie.poolday.BobsleTeamModel;
import com.hungteen.pvz.render.entity.zombie.PVZZombieRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BobsleTeamRender extends PVZZombieRender<BobsleTeamEntity>{

	public BobsleTeamRender(EntityRendererManager rendererManager) {
		super(rendererManager, new BobsleTeamModel(), 0.6f);
	}

	@Override
	protected float getScaleByEntity(BobsleTeamEntity entity) {
		return 0.5f;
	}

	@Override
	public ResourceLocation getTextureLocation(BobsleTeamEntity entity) {
		return StringUtil.prefix("textures/entity/misc/bobsle_car.png");
	}

}

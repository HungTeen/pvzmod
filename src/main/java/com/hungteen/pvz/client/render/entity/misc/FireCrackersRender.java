package com.hungteen.pvz.client.render.entity.misc;

import com.hungteen.pvz.client.model.entity.misc.FireCrackersModel;
import com.hungteen.pvz.client.render.entity.PVZEntityRender;
import com.hungteen.pvz.common.entity.misc.FireCrackersEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FireCrackersRender extends PVZEntityRender<FireCrackersEntity> {

	private static final ResourceLocation FIRE_CRACKERS_TEX = StringUtil.prefix("textures/entity/misc/fire_crackers.png");
	public FireCrackersRender(EntityRendererManager renderManager) {
		super(renderManager, new FireCrackersModel());
	}

	@Override
	protected float getScaleByEntity(FireCrackersEntity entity) {
		return 1F + 0.1F * MathHelper.sin(entity.getFuse() * 0.1F);
	}

	@Override
	public ResourceLocation getTextureLocation(FireCrackersEntity entity) {
		return FIRE_CRACKERS_TEX;
	}

}

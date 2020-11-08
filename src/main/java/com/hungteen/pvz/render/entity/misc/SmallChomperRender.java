package com.hungteen.pvz.render.entity.misc;

import com.hungteen.pvz.entity.misc.SmallChomperEntity;
import com.hungteen.pvz.model.entity.misc.SmallChomperModel;
import com.hungteen.pvz.render.entity.PVZCreatureRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SmallChomperRender extends PVZCreatureRender<SmallChomperEntity>{

	public SmallChomperRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new SmallChomperModel(), 0.1f);
	}

	@Override
	protected float getScaleByEntity(SmallChomperEntity entity) {
		return 1f;
	}
	
	@Override
	public Vec3d getTranslateVec(SmallChomperEntity entity) {
		int tick = entity.getTick();//1 - 20
		return new Vec3d(0, 1f - 0.05f * tick, 0);
	}
	
	@Override
	public ResourceLocation getEntityTexture(SmallChomperEntity entity) {
		return StringUtil.prefix("textures/entity/plant/enforce/chomper.png");
	}

}

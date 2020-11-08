package com.hungteen.pvz.render.entity.misc;

import com.hungteen.pvz.entity.misc.ZombieHandEntity;
import com.hungteen.pvz.model.entity.misc.ZombieHandModel;
import com.hungteen.pvz.render.entity.PVZCreatureRender;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZombieHandRender extends PVZCreatureRender<ZombieHandEntity>{

	public ZombieHandRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ZombieHandModel(), 0f);
	}
	
    @Override
	protected float getScaleByEntity(ZombieHandEntity entity) {
		return 0.75f;
	}
    
    @Override
    public Vec3d getTranslateVec(ZombieHandEntity entity) {
    	int tick = entity.getTick();//1 - 40
		return new Vec3d(0, 0.05f * tick, 0);
    }
    
	@Override
	public ResourceLocation getEntityTexture(ZombieHandEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/misc/zombie_hand.png");
	}

}

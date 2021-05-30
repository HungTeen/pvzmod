package com.hungteen.pvz.client.render.entity.misc;

import com.hungteen.pvz.client.model.entity.misc.ZombieHandModel;
import com.hungteen.pvz.client.render.entity.PVZEntityRender;
import com.hungteen.pvz.common.entity.misc.ZombieHandEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZombieHandRender extends PVZEntityRender<ZombieHandEntity>{

	public ZombieHandRender(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ZombieHandModel());
	}
	
    @Override
	protected float getScaleByEntity(ZombieHandEntity entity) {
		return 0.75f;
	}
    
    @Override
    public Vector3d getTranslateVec(ZombieHandEntity entity) {
    	int tick = entity.getTick();//1 - 40
		return new Vector3d(0, 0.05f * tick, 0);
    }
    
	@Override
	public ResourceLocation getTextureLocation(ZombieHandEntity entity) {
		return StringUtil.prefix("textures/entity/zombie/misc/zombie_hand.png");
	}

}

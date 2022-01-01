package com.hungteen.pvz.client.render.entity.misc.drop;

import com.hungteen.pvz.common.entity.misc.drop.DropEntity.DropStates;
import com.hungteen.pvz.client.model.entity.misc.DropModel;
import com.hungteen.pvz.common.entity.misc.drop.SunEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SunRender extends DropRender<SunEntity>{

	public SunRender(EntityRendererManager renderManager) {
		super(renderManager, new DropModel<>());
	}

	@Override
	public ResourceLocation getTextureLocation(SunEntity entity) {
		return entity.getDropState() == DropStates.STEAL ? StringUtil.prefix("textures/entity/drop/sun2.png") : StringUtil.prefix("textures/entity/drop/sun.png");
	}

	@Override
	protected float getScaleByEntity(SunEntity entity) {
		int amount = entity.getAmount();//default size 4 blocks high
		//100 <-> 0.4,10 <-> 0.1
		return amount * 0.003f + 0.1f;
	}
}

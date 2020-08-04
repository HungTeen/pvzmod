package com.hungteen.pvz.render.entity.drop;

import com.hungteen.pvz.entity.drop.SunEntity;
import com.hungteen.pvz.model.entity.drop.SunModel;
import com.hungteen.pvz.utils.StringUtil;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SunRender extends DropRender<SunEntity>{

	
	public SunRender(EntityRendererManager renderManager) {
		super(renderManager,new SunModel());
	}

	@Override
	public ResourceLocation getEntityTexture(SunEntity entity) {
		return StringUtil.prefix("textures/entity/drop/sun.png");
	}

	@Override
	protected float getRenderSize(SunEntity entity) {
		int amount = entity.getAmount();//default size 4 blocks high
		//100 <-> 0.4,10 <-> 0.1
		return amount*0.003f+0.1f;
	}
}

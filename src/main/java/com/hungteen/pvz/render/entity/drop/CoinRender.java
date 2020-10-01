package com.hungteen.pvz.render.entity.drop;

import com.hungteen.pvz.entity.drop.CoinEntity;
import com.hungteen.pvz.model.entity.drop.CoinModel;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CoinRender extends DropRender<CoinEntity>{

	public CoinRender(EntityRendererManager renderManager) {
		super(renderManager, new CoinModel());
	}

	@Override
	protected float getRenderSize(CoinEntity entity) {
		if(entity.getAmount()==1) return 0.15f;
		if(entity.getAmount()==10) return 0.18f;
		if(entity.getAmount()==100) return 0.23f;
		if(entity.getAmount()==1000) return 0.3f;
//		PVZMod.LOGGER.debug("Coin Type Error!!!");
		return 0.15f;
	}

	@Override
	public ResourceLocation getEntityTexture(CoinEntity entity) {
		if(entity.getAmount()==1) return StringUtil.prefix("textures/entity/drop/copper_coin.png");
		if(entity.getAmount()==10) return StringUtil.prefix("textures/entity/drop/silver_coin.png");
		if(entity.getAmount()==100) return StringUtil.prefix("textures/entity/drop/gold_coin.png");
		if(entity.getAmount()==1000) return StringUtil.prefix("textures/entity/drop/jewel.png");
//		PVZMod.LOGGER.debug("Coin Type Error!!!");
		return StringUtil.prefix("textures/entity/drop/copper_coin.png");
	}

}

package com.hungteen.pvz.client.render.entity.misc.drop;

import com.hungteen.pvz.client.model.entity.misc.DropModel;
import com.hungteen.pvz.common.entity.misc.drop.CoinEntity;
import com.hungteen.pvz.utils.StringUtil;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CoinRender extends DropRender<CoinEntity>{

	public CoinRender(EntityRendererManager renderManager) {
		super(renderManager, new DropModel<>());
	}

	@Override
	public ResourceLocation getTextureLocation(CoinEntity entity) {
		final int amount = entity.getAmount();
		return amount < 10 ? StringUtil.prefix("textures/entity/drop/copper_coin.png") : 
			   amount < 100 ? StringUtil.prefix("textures/entity/drop/silver_coin.png") :
			   StringUtil.prefix("textures/entity/drop/gold_coin.png");
	}

	@Override
	protected float getScaleByEntity(CoinEntity entity) {
		final int amount = entity.getAmount();
		return amount < 10 ? 0.15F : 
			   amount < 100 ? 0.18F :
			   0.23F;
	}

}

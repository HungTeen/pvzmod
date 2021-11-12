package com.hungteen.pvz.client.render.entity.bullet;

import com.hungteen.pvz.client.render.entity.EntityBlockRender;
import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.entity.bullet.ButterEntity;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ButterRender extends EntityBlockRender<ButterEntity> {

	public ButterRender(EntityRendererManager renderManager) {
		super(renderManager);
	}

	@Override
	public float getScaleByEntity(ButterEntity entity) {
		return 0.5F;
	}

	@Override
	public BlockState getBlockByEntity(ButterEntity entity) {
		return BlockRegister.BUTTER_BLOCK.get().defaultBlockState();
	}

}

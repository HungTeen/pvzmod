package com.hungteen.pvz.client.render.entity.bullet;

import com.hungteen.pvz.client.render.entity.EntityBlockRender;
import com.hungteen.pvz.common.block.BlockRegister;
import com.hungteen.pvz.common.entity.bullet.MelonEntity;
import com.hungteen.pvz.common.entity.bullet.MelonEntity.MelonStates;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MelonRender extends EntityBlockRender<MelonEntity> {

	public MelonRender(EntityRendererManager renderManager) {
		super(renderManager);
	}

	@Override
	public float getScaleByEntity(MelonEntity entity) {
		return 0.8F;
	}

	@Override
	public BlockState getBlockByEntity(MelonEntity entity) {
		if(entity.getMelonState() == MelonStates.ICE) return BlockRegister.FROZEN_MELON.get().defaultBlockState();
		return Blocks.MELON.defaultBlockState();
	}

}

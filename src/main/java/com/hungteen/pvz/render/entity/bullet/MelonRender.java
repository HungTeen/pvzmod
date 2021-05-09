package com.hungteen.pvz.render.entity.bullet;

import com.hungteen.pvz.entity.bullet.MelonEntity;
import com.hungteen.pvz.entity.bullet.MelonEntity.MelonStates;
import com.hungteen.pvz.register.BlockRegister;
import com.hungteen.pvz.render.entity.EntityBlockRender;

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
